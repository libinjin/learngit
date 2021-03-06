/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.youguu.quant.rpc.thrift.gen;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StrategyStockThreeProfitThrift implements org.apache.thrift.TBase<StrategyStockThreeProfitThrift, StrategyStockThreeProfitThrift._Fields>, java.io.Serializable, Cloneable, Comparable<StrategyStockThreeProfitThrift> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("StrategyStockThreeProfitThrift");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField STRATEGY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("strategyId", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField STOCK_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("stockCode", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PROFIT_FIELD_DESC = new org.apache.thrift.protocol.TField("profit", org.apache.thrift.protocol.TType.DOUBLE, (short)4);
  private static final org.apache.thrift.protocol.TField CREATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("createTime", org.apache.thrift.protocol.TType.I64, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new StrategyStockThreeProfitThriftStandardSchemeFactory());
    schemes.put(TupleScheme.class, new StrategyStockThreeProfitThriftTupleSchemeFactory());
  }

  public int id; // required
  public int strategyId; // required
  public String stockCode; // required
  public double profit; // required
  public long createTime; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    STRATEGY_ID((short)2, "strategyId"),
    STOCK_CODE((short)3, "stockCode"),
    PROFIT((short)4, "profit"),
    CREATE_TIME((short)5, "createTime");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // STRATEGY_ID
          return STRATEGY_ID;
        case 3: // STOCK_CODE
          return STOCK_CODE;
        case 4: // PROFIT
          return PROFIT;
        case 5: // CREATE_TIME
          return CREATE_TIME;
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
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private static final int __STRATEGYID_ISSET_ID = 1;
  private static final int __PROFIT_ISSET_ID = 2;
  private static final int __CREATETIME_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.STRATEGY_ID, new org.apache.thrift.meta_data.FieldMetaData("strategyId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.STOCK_CODE, new org.apache.thrift.meta_data.FieldMetaData("stockCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PROFIT, new org.apache.thrift.meta_data.FieldMetaData("profit", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.CREATE_TIME, new org.apache.thrift.meta_data.FieldMetaData("createTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(StrategyStockThreeProfitThrift.class, metaDataMap);
  }

  public StrategyStockThreeProfitThrift() {
  }

  public StrategyStockThreeProfitThrift(
    int id,
    int strategyId,
    String stockCode,
    double profit,
    long createTime)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.strategyId = strategyId;
    setStrategyIdIsSet(true);
    this.stockCode = stockCode;
    this.profit = profit;
    setProfitIsSet(true);
    this.createTime = createTime;
    setCreateTimeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public StrategyStockThreeProfitThrift(StrategyStockThreeProfitThrift other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    this.strategyId = other.strategyId;
    if (other.isSetStockCode()) {
      this.stockCode = other.stockCode;
    }
    this.profit = other.profit;
    this.createTime = other.createTime;
  }

  public StrategyStockThreeProfitThrift deepCopy() {
    return new StrategyStockThreeProfitThrift(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    setStrategyIdIsSet(false);
    this.strategyId = 0;
    this.stockCode = null;
    setProfitIsSet(false);
    this.profit = 0.0;
    setCreateTimeIsSet(false);
    this.createTime = 0;
  }

  public int getId() {
    return this.id;
  }

  public StrategyStockThreeProfitThrift setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int getStrategyId() {
    return this.strategyId;
  }

  public StrategyStockThreeProfitThrift setStrategyId(int strategyId) {
    this.strategyId = strategyId;
    setStrategyIdIsSet(true);
    return this;
  }

  public void unsetStrategyId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __STRATEGYID_ISSET_ID);
  }

  /** Returns true if field strategyId is set (has been assigned a value) and false otherwise */
  public boolean isSetStrategyId() {
    return EncodingUtils.testBit(__isset_bitfield, __STRATEGYID_ISSET_ID);
  }

  public void setStrategyIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __STRATEGYID_ISSET_ID, value);
  }

  public String getStockCode() {
    return this.stockCode;
  }

  public StrategyStockThreeProfitThrift setStockCode(String stockCode) {
    this.stockCode = stockCode;
    return this;
  }

  public void unsetStockCode() {
    this.stockCode = null;
  }

  /** Returns true if field stockCode is set (has been assigned a value) and false otherwise */
  public boolean isSetStockCode() {
    return this.stockCode != null;
  }

  public void setStockCodeIsSet(boolean value) {
    if (!value) {
      this.stockCode = null;
    }
  }

  public double getProfit() {
    return this.profit;
  }

  public StrategyStockThreeProfitThrift setProfit(double profit) {
    this.profit = profit;
    setProfitIsSet(true);
    return this;
  }

  public void unsetProfit() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PROFIT_ISSET_ID);
  }

  /** Returns true if field profit is set (has been assigned a value) and false otherwise */
  public boolean isSetProfit() {
    return EncodingUtils.testBit(__isset_bitfield, __PROFIT_ISSET_ID);
  }

  public void setProfitIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PROFIT_ISSET_ID, value);
  }

  public long getCreateTime() {
    return this.createTime;
  }

  public StrategyStockThreeProfitThrift setCreateTime(long createTime) {
    this.createTime = createTime;
    setCreateTimeIsSet(true);
    return this;
  }

  public void unsetCreateTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CREATETIME_ISSET_ID);
  }

  /** Returns true if field createTime is set (has been assigned a value) and false otherwise */
  public boolean isSetCreateTime() {
    return EncodingUtils.testBit(__isset_bitfield, __CREATETIME_ISSET_ID);
  }

  public void setCreateTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CREATETIME_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    case STRATEGY_ID:
      if (value == null) {
        unsetStrategyId();
      } else {
        setStrategyId((Integer)value);
      }
      break;

    case STOCK_CODE:
      if (value == null) {
        unsetStockCode();
      } else {
        setStockCode((String)value);
      }
      break;

    case PROFIT:
      if (value == null) {
        unsetProfit();
      } else {
        setProfit((Double)value);
      }
      break;

    case CREATE_TIME:
      if (value == null) {
        unsetCreateTime();
      } else {
        setCreateTime((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return Integer.valueOf(getId());

    case STRATEGY_ID:
      return Integer.valueOf(getStrategyId());

    case STOCK_CODE:
      return getStockCode();

    case PROFIT:
      return Double.valueOf(getProfit());

    case CREATE_TIME:
      return Long.valueOf(getCreateTime());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case STRATEGY_ID:
      return isSetStrategyId();
    case STOCK_CODE:
      return isSetStockCode();
    case PROFIT:
      return isSetProfit();
    case CREATE_TIME:
      return isSetCreateTime();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof StrategyStockThreeProfitThrift)
      return this.equals((StrategyStockThreeProfitThrift)that);
    return false;
  }

  public boolean equals(StrategyStockThreeProfitThrift that) {
    if (that == null)
      return false;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_strategyId = true;
    boolean that_present_strategyId = true;
    if (this_present_strategyId || that_present_strategyId) {
      if (!(this_present_strategyId && that_present_strategyId))
        return false;
      if (this.strategyId != that.strategyId)
        return false;
    }

    boolean this_present_stockCode = true && this.isSetStockCode();
    boolean that_present_stockCode = true && that.isSetStockCode();
    if (this_present_stockCode || that_present_stockCode) {
      if (!(this_present_stockCode && that_present_stockCode))
        return false;
      if (!this.stockCode.equals(that.stockCode))
        return false;
    }

    boolean this_present_profit = true;
    boolean that_present_profit = true;
    if (this_present_profit || that_present_profit) {
      if (!(this_present_profit && that_present_profit))
        return false;
      if (this.profit != that.profit)
        return false;
    }

    boolean this_present_createTime = true;
    boolean that_present_createTime = true;
    if (this_present_createTime || that_present_createTime) {
      if (!(this_present_createTime && that_present_createTime))
        return false;
      if (this.createTime != that.createTime)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(StrategyStockThreeProfitThrift other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStrategyId()).compareTo(other.isSetStrategyId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStrategyId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.strategyId, other.strategyId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStockCode()).compareTo(other.isSetStockCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStockCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.stockCode, other.stockCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetProfit()).compareTo(other.isSetProfit());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProfit()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.profit, other.profit);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCreateTime()).compareTo(other.isSetCreateTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCreateTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.createTime, other.createTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("StrategyStockThreeProfitThrift(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("strategyId:");
    sb.append(this.strategyId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("stockCode:");
    if (this.stockCode == null) {
      sb.append("null");
    } else {
      sb.append(this.stockCode);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("profit:");
    sb.append(this.profit);
    first = false;
    if (!first) sb.append(", ");
    sb.append("createTime:");
    sb.append(this.createTime);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class StrategyStockThreeProfitThriftStandardSchemeFactory implements SchemeFactory {
    public StrategyStockThreeProfitThriftStandardScheme getScheme() {
      return new StrategyStockThreeProfitThriftStandardScheme();
    }
  }

  private static class StrategyStockThreeProfitThriftStandardScheme extends StandardScheme<StrategyStockThreeProfitThrift> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, StrategyStockThreeProfitThrift struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // STRATEGY_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.strategyId = iprot.readI32();
              struct.setStrategyIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // STOCK_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.stockCode = iprot.readString();
              struct.setStockCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PROFIT
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.profit = iprot.readDouble();
              struct.setProfitIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // CREATE_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.createTime = iprot.readI64();
              struct.setCreateTimeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, StrategyStockThreeProfitThrift struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(STRATEGY_ID_FIELD_DESC);
      oprot.writeI32(struct.strategyId);
      oprot.writeFieldEnd();
      if (struct.stockCode != null) {
        oprot.writeFieldBegin(STOCK_CODE_FIELD_DESC);
        oprot.writeString(struct.stockCode);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PROFIT_FIELD_DESC);
      oprot.writeDouble(struct.profit);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CREATE_TIME_FIELD_DESC);
      oprot.writeI64(struct.createTime);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class StrategyStockThreeProfitThriftTupleSchemeFactory implements SchemeFactory {
    public StrategyStockThreeProfitThriftTupleScheme getScheme() {
      return new StrategyStockThreeProfitThriftTupleScheme();
    }
  }

  private static class StrategyStockThreeProfitThriftTupleScheme extends TupleScheme<StrategyStockThreeProfitThrift> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, StrategyStockThreeProfitThrift struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetStrategyId()) {
        optionals.set(1);
      }
      if (struct.isSetStockCode()) {
        optionals.set(2);
      }
      if (struct.isSetProfit()) {
        optionals.set(3);
      }
      if (struct.isSetCreateTime()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetStrategyId()) {
        oprot.writeI32(struct.strategyId);
      }
      if (struct.isSetStockCode()) {
        oprot.writeString(struct.stockCode);
      }
      if (struct.isSetProfit()) {
        oprot.writeDouble(struct.profit);
      }
      if (struct.isSetCreateTime()) {
        oprot.writeI64(struct.createTime);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, StrategyStockThreeProfitThrift struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.strategyId = iprot.readI32();
        struct.setStrategyIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.stockCode = iprot.readString();
        struct.setStockCodeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.profit = iprot.readDouble();
        struct.setProfitIsSet(true);
      }
      if (incoming.get(4)) {
        struct.createTime = iprot.readI64();
        struct.setCreateTimeIsSet(true);
      }
    }
  }

}

