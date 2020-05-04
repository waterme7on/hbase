<%--
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
--%>
<%@ page contentType="text/html;charset=UTF-8"
         import="java.time.Instant"
         import="java.time.ZoneId"
         import="java.util.Date"
         import="java.util.List"
         import="java.util.Map"
         import="java.util.stream.Collectors"
         import="java.time.ZonedDateTime"
         import="java.time.format.DateTimeFormatter"
%>
<%@ page import="org.apache.hadoop.fs.Path" %>
<%@ page import="org.apache.hadoop.hbase.client.RegionInfo" %>
<%@ page import="org.apache.hadoop.hbase.master.HbckChore" %>
<%@ page import="org.apache.hadoop.hbase.master.HMaster" %>
<%@ page import="org.apache.hadoop.hbase.master.ServerManager" %>
<%@ page import="org.apache.hadoop.hbase.ServerName" %>
<%@ page import="org.apache.hadoop.hbase.util.Bytes" %>
<%@ page import="org.apache.hadoop.hbase.util.Pair" %>
<%@ page import="org.apache.hadoop.hbase.master.CatalogJanitor" %>
<%@ page import="org.apache.hadoop.hbase.master.CatalogJanitor.Report" %>
<%
  final HMaster master = (HMaster) getServletContext().getAttribute(HMaster.MASTER);
  pageContext.setAttribute("pageTitle", "HBase Master HBCK Report: " + master.getServerName());
  HbckChore hbckChore = master.getHbckChore();
  Map<String, Pair<ServerName, List<ServerName>>> inconsistentRegions = null;
  Map<String, ServerName> orphanRegionsOnRS = null;
  Map<String, Path> orphanRegionsOnFS = null;
  long startTimestamp = 0;
  long endTimestamp = 0;
  if (hbckChore != null) {
    inconsistentRegions = hbckChore.getInconsistentRegions();
    orphanRegionsOnRS = hbckChore.getOrphanRegionsOnRS();
    orphanRegionsOnFS = hbckChore.getOrphanRegionsOnFS();
    startTimestamp = hbckChore.getCheckingStartTimestamp();
    endTimestamp = hbckChore.getCheckingEndTimestamp();
  }
  ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(startTimestamp),
    ZoneId.systemDefault());
  String iso8601start = startTimestamp == 0? "-1": zdt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(endTimestamp),
    ZoneId.systemDefault());
  String iso8601end = startTimestamp == 0? "-1": zdt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  CatalogJanitor cj = master.getCatalogJanitor();
  CatalogJanitor.Report report = cj == null? null: cj.getLastReport();
  final ServerManager serverManager = master.getServerManager();
%>
<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="${pageTitle}"/>
</jsp:include>

<div class="container-fluid content">
  <% if (!master.isInitialized()) { %>
  <div class="row">
    <div class="page-header">
      <h1>Master is not initialized</h1>
    </div>
  </div>
  <jsp:include page="redirect.jsp" />
  <% } else { %>

  <div class="row">
    <div class="page-header">
      <p><span>This page displays two reports: the <em>HBCK Chore Report</em> and the <em>CatalogJanitor Consistency Issues</em> report. Only report titles show if there are no problems to list. Note some conditions are <strong>transitory</strong> as regions migrate. See below for how to run reports. ServerNames will be links if server is live, italic if dead, and plain if unknown.</span></p>
    </div>
  </div>
  <div class="row">
    <div class="page-header">
      <h1>HBCK Chore Report</h1>
      <p>
        <% if (hbckChore.isDisabled()) { %>
          <span>HBCK chore is currently disabled. Set hbase.master.hbck.chore.interval > 0 in the config & do a rolling-restart to enable it.</span>
        <% } else if (startTimestamp == 0 && endTimestamp == 0){ %>
          <span>No report created. Execute <i>hbck_chore_run</i> in hbase shell to generate a new sub-report.</span>
        <% } else if (startTimestamp > 0 && endTimestamp == 0){ %>
          <span>Checking started at <%= iso8601start %>. Please wait for checking to generate a new sub-report.</span>
        <% } else { %>
          <span>Checking started at <%= iso8601start %> and generated report at <%= iso8601end %>. Execute <i>hbck_chore_run</i> in hbase shell to generate a new sub-report.</span>
        <% } %>
      </p>
    </div>
  </div>

  <% if (inconsistentRegions != null && inconsistentRegions.size() > 0) { %>
  <div class="row">
    <div class="page-header">
      <h2>Inconsistent Regions</h2>
    </div>
  </div>
      <p>
        <span>
        There are three cases: 1. Master thought this region opened, but no regionserver reported it (Fix: use assign
        command); 2. Master thought this region opened on Server1, but regionserver reported Server2 (Fix:
        need to check the server still exists. If not, schedule <em>ServerCrashProcedure</em> for it. If exists,
        restart Server2 and Server1):
        3. More than one regionserver reports opened this region (Fix: restart the RegionServers).
        Notice: the reported online regionservers may be not right when there are regions in transition.
        Please check them in regionserver's web UI.
        </span>
      </p>

  <table class="table table-striped">
    <tr>
      <th>Region Name</th>
      <th>Location in META</th>
      <th>Reported Online RegionServers</th>
    </tr>
    <% for (Map.Entry<String, Pair<ServerName, List<ServerName>>> entry : inconsistentRegions.entrySet()) {%>
    <tr>
      <td><%= entry.getKey() %></td>
      <td><%= formatServerName(master, serverManager, entry.getValue().getFirst()) %></td>
      <td><%= entry.getValue().getSecond().stream().map(s -> formatServerName(master, serverManager, s)).
        collect(Collectors.joining(", ")) %></td>
    </tr>
    <% } %>
    <p><%= inconsistentRegions.size() %> region(s) in set.</p>
  </table>
  <% } %>

  <% if (orphanRegionsOnRS != null && orphanRegionsOnRS.size() > 0) { %>
  <div class="row">
    <div class="page-header">
      <h2>Orphan Regions on RegionServer</h2>
    </div>
  </div>

  <table class="table table-striped">
    <tr>
      <th>Region Name</th>
      <th>Reported Online RegionServer</th>
    </tr>
    <% for (Map.Entry<String, ServerName> entry : orphanRegionsOnRS.entrySet()) { %>
    <tr>
      <td><%= entry.getKey() %></td>
      <td><%= formatServerName(master, serverManager, entry.getValue()) %></td>
    </tr>
    <% } %>
    <p><%= orphanRegionsOnRS.size() %> region(s) in set.</p>
  </table>
  <% } %>

  <% if (orphanRegionsOnFS != null && orphanRegionsOnFS.size() > 0) { %>
  <div class="row">
    <div class="page-header">
      <h2>Orphan Regions on FileSystem</h2>
    </div>
  </div>
      <p>
        <span>
          The below are Regions we've lost account of. To be safe, run bulk load of any data found in these Region orphan directories back into the HBase cluster.
          First make sure <em>hbase:meta</em> is in a healthy state, that there are not holes, overlaps or inconsistencies (else bulk load may complain);
          run <em>hbck2 fixMeta</em>. Once this is done, per Region below, run a bulk
          load -- <em>$ hbase completebulkload REGION_DIR_PATH TABLE_NAME</em> -- and then delete the desiccated directory content (HFiles are removed upon
          successful load; all that is left are empty directories and occasionally a seqid marking file).
        </span>
      </p>
  <table class="table table-striped">
    <tr>
      <th>Region Encoded Name</th>
      <th>FileSystem Path</th>
    </tr>
    <% for (Map.Entry<String, Path> entry : orphanRegionsOnFS.entrySet()) { %>
    <tr>
      <td><%= entry.getKey() %></td>
      <td><%= entry.getValue() %></td>
    </tr>
    <% } %>

    <p><%= orphanRegionsOnFS.size() %> region(s) in set.</p>
  </table>
  <% } %>

  <%
    zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
      ZoneId.systemDefault());
    String iso8601Now = zdt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    String iso8601reportTime = "-1";
    if (report != null) {
      zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(report.getCreateTime()),
        ZoneId.systemDefault());
      iso8601reportTime = zdt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
  %>
  <div class="row inner_header">
    <div class="page-header">
      <h1>CatalogJanitor <em>hbase:meta</em> Consistency Issues</h1>
      <p>
        <% if (report != null) { %>
          <span>Report created: <%= iso8601reportTime %> (now=<%= iso8601Now %>). Run <i>catalogjanitor_run</i> in hbase shell to generate a new sub-report.</span></p>
        <% } else { %>
          <span>No report created. Run <i>catalogjanitor_run</i> in hbase shell to generate a new sub-report.</span>
        <% } %>
    </div>
  </div>
  <% if (report != null && !report.isEmpty()) { %>
      <% if (!report.getHoles().isEmpty()) { %>
          <div class="row inner_header">
            <div class="page-header">
              <h2>Holes</h2>
            </div>
          </div>
          <table class="table table-striped">
            <tr>
              <th>RegionInfo</th>
              <th>RegionInfo</th>
            </tr>
            <% for (Pair<RegionInfo, RegionInfo> p : report.getHoles()) { %>
            <tr>
              <td><span title="<%= p.getFirst() %>"><%= p.getFirst().getEncodedName() %></span></td>
              <td><span title="<%= p.getSecond() %>"><%= p.getSecond().getEncodedName() %></span></td>
            </tr>
            <% } %>

            <p><%= report.getHoles().size() %> hole(s).</p>
          </table>
      <% } %>
      <% if (!report.getOverlaps().isEmpty()) { %>
            <div class="row inner_header">
              <div class="page-header">
                <h2>Overlaps</h2>
              </div>
            </div>
            <table class="table table-striped">
              <tr>
                <th>RegionInfo</th>
                <th>Other RegionInfo</th>
              </tr>
              <% for (Pair<RegionInfo, RegionInfo> p : report.getOverlaps()) { %>
              <tr>
                <td><span title="<%= p.getFirst() %>"><%= p.getFirst().getEncodedName() %></span></td>
                <td><span title="<%= p.getSecond() %>"><%= p.getSecond().getEncodedName() %></span></td>
              </tr>
              <% } %>

              <p><%= report.getOverlaps().size() %> overlap(s).</p>
            </table>
      <% } %>
      <% if (!report.getUnknownServers().isEmpty()) { %>
            <div class="row inner_header">
              <div class="page-header">
                <h2>Unknown Servers</h2>
              </div>
            </div>
            <table class="table table-striped">
              <tr>
                <th>RegionInfo</th>
                <th>ServerName</th>
              </tr>
              <% for (Pair<RegionInfo, ServerName> p: report.getUnknownServers()) { %>
              <tr>
                <td><span title="<%= p.getFirst() %>"><%= p.getFirst().getEncodedName() %></span></td>
                <td><%= p.getSecond() %></td>
              </tr>
              <% } %>

              <p><%= report.getUnknownServers().size() %> unknown servers(s).</p>
            </table>
      <% } %>
      <% if (!report.getEmptyRegionInfo().isEmpty()) { %>
            <div class="row inner_header">
              <div class="page-header">
                <h2>Empty <em>info:regioninfo</em></h2>
              </div>
            </div>
            <table class="table table-striped">
              <tr>
                <th>Row</th>
              </tr>
              <% for (byte [] row: report.getEmptyRegionInfo()) { %>
              <tr>
                <td><%= Bytes.toStringBinary(row) %></td>
              </tr>
              <% } %>

              <p><%= report.getEmptyRegionInfo().size() %> emptyRegionInfo(s).</p>
            </table>
      <% } %>
  <% } %>

  <% } %>
</div>

<jsp:include page="footer.jsp"/>

<%!
/**
 * Format serverName for display.
 * If a live server reference, make it a link.
 * If dead, make it italic.
 * If unknown, make it plain.
 */
private static String formatServerName(HMaster master,
   ServerManager serverManager, ServerName serverName) {
  String sn = serverName.toString();
  if (serverManager.isServerOnline(serverName)) {
    int infoPort = master.getRegionServerInfoPort(serverName);
    if (infoPort > 0) {
      return "<a href=" + "//" + serverName.getHostname() + ":" +
        infoPort + "/rs-status>" + sn + "</a>";
    } else {
      return "<b>" + sn + "</b>";
    }
  } else if (serverManager.isServerDead(serverName)) {
    return "<i>" + sn + "</i>";
  }
  return sn;
}
%>
