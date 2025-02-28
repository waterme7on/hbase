/**
 * Autogenerated by Thrift Compiler (0.14.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hadoop.hbase.thrift2.generated;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.1)", date = "2022-07-05")
public class THRegionLocation implements org.apache.thrift.TBase<THRegionLocation, THRegionLocation._Fields>, java.io.Serializable, Cloneable, Comparable<THRegionLocation> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("THRegionLocation");

  private static final org.apache.thrift.protocol.TField SERVER_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("serverName", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField REGION_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("regionInfo", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new THRegionLocationStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new THRegionLocationTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable TServerName serverName; // required
  public @org.apache.thrift.annotation.Nullable THRegionInfo regionInfo; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SERVER_NAME((short)1, "serverName"),
    REGION_INFO((short)2, "regionInfo");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SERVER_NAME
          return SERVER_NAME;
        case 2: // REGION_INFO
          return REGION_INFO;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SERVER_NAME, new org.apache.thrift.meta_data.FieldMetaData("serverName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TServerName.class)));
    tmpMap.put(_Fields.REGION_INFO, new org.apache.thrift.meta_data.FieldMetaData("regionInfo", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, THRegionInfo.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(THRegionLocation.class, metaDataMap);
  }

  public THRegionLocation() {
  }

  public THRegionLocation(
    TServerName serverName,
    THRegionInfo regionInfo)
  {
    this();
    this.serverName = serverName;
    this.regionInfo = regionInfo;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public THRegionLocation(THRegionLocation other) {
    if (other.isSetServerName()) {
      this.serverName = new TServerName(other.serverName);
    }
    if (other.isSetRegionInfo()) {
      this.regionInfo = new THRegionInfo(other.regionInfo);
    }
  }

  public THRegionLocation deepCopy() {
    return new THRegionLocation(this);
  }

  @Override
  public void clear() {
    this.serverName = null;
    this.regionInfo = null;
  }

  @org.apache.thrift.annotation.Nullable
  public TServerName getServerName() {
    return this.serverName;
  }

  public THRegionLocation setServerName(@org.apache.thrift.annotation.Nullable TServerName serverName) {
    this.serverName = serverName;
    return this;
  }

  public void unsetServerName() {
    this.serverName = null;
  }

  /** Returns true if field serverName is set (has been assigned a value) and false otherwise */
  public boolean isSetServerName() {
    return this.serverName != null;
  }

  public void setServerNameIsSet(boolean value) {
    if (!value) {
      this.serverName = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public THRegionInfo getRegionInfo() {
    return this.regionInfo;
  }

  public THRegionLocation setRegionInfo(@org.apache.thrift.annotation.Nullable THRegionInfo regionInfo) {
    this.regionInfo = regionInfo;
    return this;
  }

  public void unsetRegionInfo() {
    this.regionInfo = null;
  }

  /** Returns true if field regionInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetRegionInfo() {
    return this.regionInfo != null;
  }

  public void setRegionInfoIsSet(boolean value) {
    if (!value) {
      this.regionInfo = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case SERVER_NAME:
      if (value == null) {
        unsetServerName();
      } else {
        setServerName((TServerName)value);
      }
      break;

    case REGION_INFO:
      if (value == null) {
        unsetRegionInfo();
      } else {
        setRegionInfo((THRegionInfo)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case SERVER_NAME:
      return getServerName();

    case REGION_INFO:
      return getRegionInfo();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case SERVER_NAME:
      return isSetServerName();
    case REGION_INFO:
      return isSetRegionInfo();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof THRegionLocation)
      return this.equals((THRegionLocation)that);
    return false;
  }

  public boolean equals(THRegionLocation that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_serverName = true && this.isSetServerName();
    boolean that_present_serverName = true && that.isSetServerName();
    if (this_present_serverName || that_present_serverName) {
      if (!(this_present_serverName && that_present_serverName))
        return false;
      if (!this.serverName.equals(that.serverName))
        return false;
    }

    boolean this_present_regionInfo = true && this.isSetRegionInfo();
    boolean that_present_regionInfo = true && that.isSetRegionInfo();
    if (this_present_regionInfo || that_present_regionInfo) {
      if (!(this_present_regionInfo && that_present_regionInfo))
        return false;
      if (!this.regionInfo.equals(that.regionInfo))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetServerName()) ? 131071 : 524287);
    if (isSetServerName())
      hashCode = hashCode * 8191 + serverName.hashCode();

    hashCode = hashCode * 8191 + ((isSetRegionInfo()) ? 131071 : 524287);
    if (isSetRegionInfo())
      hashCode = hashCode * 8191 + regionInfo.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(THRegionLocation other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetServerName(), other.isSetServerName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServerName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.serverName, other.serverName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetRegionInfo(), other.isSetRegionInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRegionInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.regionInfo, other.regionInfo);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("THRegionLocation(");
    boolean first = true;

    sb.append("serverName:");
    if (this.serverName == null) {
      sb.append("null");
    } else {
      sb.append(this.serverName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("regionInfo:");
    if (this.regionInfo == null) {
      sb.append("null");
    } else {
      sb.append(this.regionInfo);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (serverName == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'serverName' was not present! Struct: " + toString());
    }
    if (regionInfo == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'regionInfo' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (serverName != null) {
      serverName.validate();
    }
    if (regionInfo != null) {
      regionInfo.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class THRegionLocationStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public THRegionLocationStandardScheme getScheme() {
      return new THRegionLocationStandardScheme();
    }
  }

  private static class THRegionLocationStandardScheme extends org.apache.thrift.scheme.StandardScheme<THRegionLocation> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, THRegionLocation struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SERVER_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.serverName = new TServerName();
              struct.serverName.read(iprot);
              struct.setServerNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // REGION_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.regionInfo = new THRegionInfo();
              struct.regionInfo.read(iprot);
              struct.setRegionInfoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, THRegionLocation struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.serverName != null) {
        oprot.writeFieldBegin(SERVER_NAME_FIELD_DESC);
        struct.serverName.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.regionInfo != null) {
        oprot.writeFieldBegin(REGION_INFO_FIELD_DESC);
        struct.regionInfo.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class THRegionLocationTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public THRegionLocationTupleScheme getScheme() {
      return new THRegionLocationTupleScheme();
    }
  }

  private static class THRegionLocationTupleScheme extends org.apache.thrift.scheme.TupleScheme<THRegionLocation> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, THRegionLocation struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.serverName.write(oprot);
      struct.regionInfo.write(oprot);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, THRegionLocation struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.serverName = new TServerName();
      struct.serverName.read(iprot);
      struct.setServerNameIsSet(true);
      struct.regionInfo = new THRegionInfo();
      struct.regionInfo.read(iprot);
      struct.setRegionInfoIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

