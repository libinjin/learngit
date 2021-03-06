/**
 * Autogenerated by Thrift Compiler (0.9.3)
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
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2017-04-21")
public class StrategyBestStockThrift implements org.apache.thrift.TBase<StrategyBestStockThrift, StrategyBestStockThrift._Fields>, java.io.Serializable, Cloneable, Comparable<StrategyBestStockThrift> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("StrategyBestStockThrift");

  private static final org.apache.thrift.protocol.TField STRATEGY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("strategyId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField STOCK_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("stockCode", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField STOCK_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("stockName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField FIND_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("findTime", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField PROFIT_FIELD_DESC = new org.apache.thrift.protocol.TField("profit", org.apache.thrift.protocol.TType.DOUBLE, (short)5);
  private static final org.apache.thrift.protocol.TField UPDATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("updateTime", org.apache.thrift.protocol.TType.I64, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new StrategyBestStockThriftStandardSchemeFactory());
    schemes.put(TupleScheme.class, new StrategyBestStockThriftTupleSchemeFactory());
  }

  public int strategyId; // required
  public String stockCode; // required
  public String stockName; // required
  public String findTime; // required
  public double profit; // required
  public long updateTime; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    STRATEGY_ID((short)1, "strategyId"),
    STOCK_CODE((short)2, "stockCode"),
    STOCK_NAME((short)3, "stockName"),
    FIND_TIME((short)4, "findTime"),
    PROFIT((short)5, "profit"),
    UPDATE_TIME((short)6, "updateTime");

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
        case 1: // STRATEGY_ID
          return STRATEGY_ID;
        case 2: // STOCK_CODE
          return STOCK_CODE;
        case 3: // STOCK_NAME
          return STOCK_NAME;
        case 4: // FIND_TIME
          return FIND_TIME;
        case 5: // PROFIT
          return PROFIT;
        case 6: // UPDATE_TIME
          return UPDATE_TIME;
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
  private static final int __STRATEGYID_ISSET_ID = 0;
  private static final int __PROFIT_ISSET_ID = 1;
  private static final int __UPDATETIME_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.STRATEGY_ID, new org.apache.thrift.meta_data.FieldMetaData("strategyId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.STOCK_CODE, new org.apache.thrift.meta_data.FieldMetaData("stockCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.STOCK_NAME, new org.apache.thrift.meta_data.FieldMetaData("stockName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FIND_TIME, new org.apache.thrift.meta_data.FieldMetaData("findTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PROFIT, new org.apache.thrift.meta_data.FieldMetaData("profit", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.UPDATE_TIME, new org.apache.thrift.meta_data.FieldMetaData("updateTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(StrategyBestStockThrift.class, metaDataMap);
  }

  public StrategyBestStockThrift() {
  }

  public StrategyBestStockThrift(
    int strategyId,
    String stockCode,
    String stockName,
    String findTime,
    double profit,
    long updateTime)
  {
    this();
    this.strategyId = strategyId;
    setStrategyIdIsSet(true);
    this.stockCode = stockCode;
    this.stockName = stockName;
    this.findTime = findTime;
    this.profit = profit;
    setProfitIsSet(true);
    this.updateTime = updateTime;
    setUpdateTimeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public StrategyBestStockThrift(StrategyBestStockThrift other) {
    __isset_bitfield = other.__isset_bitfield;
    this.strategyId = other.strategyId;
    if (other.isSetStockCode()) {
      this.stockCode = other.stockCode;
    }
    if (other.isSetStockName()) {
      this.stockName = other.stockName;
    }
    if (other.isSetFindTime()) {
      this.findTime = other.findTime;
    }
    this.profit = other.profit;
    this.updateTime = other.updateTime;
  }

  public StrategyBestStockThrift deepCopy() {
    return new StrategyBestStockThrift(this);
  }

  @Override
  public void clear() {
    setStrategyIdIsSet(false);
    this.strategyId = 0;
    this.stockCode = null;
    this.stockName = null;
    this.findTime = null;
    setProfitIsSet(false);
    this.profit = 0.0;
    setUpdateTimeIsSet(false);
    this.updateTime = 0;
  }

  public int getStrategyId() {
    return this.strategyId;
  }

  public StrategyBestStockThrift setStrategyId(int strategyId) {
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

  public StrategyBestStockThrift setStockCode(String stockCode) {
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

  public String getStockName() {
    return this.stockName;
  }

  public StrategyBestStockThrift setStockName(String stockName) {
    this.stockName = stockName;
    return this;
  }

  public void unsetStockName() {
    this.stockName = null;
  }

  /** Returns true if field stockName is set (has been assigned a value) and false otherwise */
  public boolean isSetStockName() {
    return this.stockName != null;
  }

  public void setStockNameIsSet(boolean value) {
    if (!value) {
      this.stockName = null;
    }
  }

  public String getFindTime() {
    return this.findTime;
  }

  public StrategyBestStockThrift setFindTime(String findTime) {
    this.findTime = findTime;
    return this;
  }

  public void unsetFindTime() {
    this.findTime = null;
  }

  /** Returns true if field findTime is set (has been assigned a value) and false otherwise */
  public boolean isSetFindTime() {
    return this.findTime != null;
  }

  public void setFindTimeIsSet(boolean value) {
    if (!value) {
      this.findTime = null;
    }
  }

  public double getProfit() {
    return this.profit;
  }

  public StrategyBestStockThrift setProfit(double profit) {
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

  public long getUpdateTime() {
    return this.updateTime;
  }

  public StrategyBestStockThrift setUpdateTime(long updateTime) {
    this.updateTime = updateTime;
    setUpdateTimeIsSet(true);
    return this;
  }

  public void unsetUpdateTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __UPDATETIME_ISSET_ID);
  }

  /** Returns true if field updateTime is set (has been assigned a value) and false otherwise */
  public boolean isSetUpdateTime() {
    return EncodingUtils.testBit(__isset_bitfield, __UPDATETIME_ISSET_ID);
  }

  public void setUpdateTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __UPDATETIME_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
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

    case STOCK_NAME:
      if (value == null) {
        unsetStockName();
      } else {
        setStockName((String)value);
      }
      break;

    case FIND_TIME:
      if (value == null) {
        unsetFindTime();
      } else {
        setFindTime((String)value);
      }
      break;

    case PROFIT:
      if (value == null) {
        unsetProfit();
      } else {
        setProfit((Double)value);
      }
      break;

    case UPDATE_TIME:
      if (value == null) {
        unsetUpdateTime();
      } else {
        setUpdateTime((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case STRATEGY_ID:
      return getStrategyId();

    case STOCK_CODE:
      return getStockCode();

    case STOCK_NAME:
      return getStockName();

    case FIND_TIME:
      return getFindTime();

    case PROFIT:
      return getProfit();

    case UPDATE_TIME:
      return getUpdateTime();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case STRATEGY_ID:
      return isSetStrategyId();
    case STOCK_CODE:
      return isSetStockCode();
    case STOCK_NAME:
      return isSetStockName();
    case FIND_TIME:
      return isSetFindTime();
    case PROFIT:
      return isSetProfit();
    case UPDATE_TIME:
      return isSetUpdateTime();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof StrategyBestStockThrift)
      return this.equals((StrategyBestStockThrift)that);
    return false;
  }

  public boolean equals(StrategyBestStockThrift that) {
    if (that == null)
      return false;

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

    boolean this_present_stockName = true && this.isSetStockName();
    boolean that_present_stockName = true && that.isSetStockName();
    if (this_present_stockName || that_present_stockName) {
      if (!(this_present_stockName && that_present_stockName))
        return false;
      if (!this.stockName.equals(that.stockName))
        return false;
    }

    boolean this_present_findTime = true && this.isSetFindTime();
    boolean that_present_findTime = true && that.isSetFindTime();
    if (this_present_findTime || that_present_findTime) {
      if (!(this_present_findTime && that_present_findTime))
        return false;
      if (!this.findTime.equals(that.findTime))
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

    boolean this_present_updateTime = true;
    boolean that_present_updateTime = true;
    if (this_present_updateTime || that_present_updateTime) {
      if (!(this_present_updateTime && that_present_updateTime))
        return false;
      if (this.updateTime != that.updateTime)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_strategyId = true;
    list.add(present_strategyId);
    if (present_strategyId)
      list.add(strategyId);

    boolean present_stockCode = true && (isSetStockCode());
    list.add(present_stockCode);
    if (present_stockCode)
      list.add(stockCode);

    boolean present_stockName = true && (isSetStockName());
    list.add(present_stockName);
    if (present_stockName)
      list.add(stockName);

    boolean present_findTime = true && (isSetFindTime());
    list.add(present_findTime);
    if (present_findTime)
      list.add(findTime);

    boolean present_profit = true;
    list.add(present_profit);
    if (present_profit)
      list.add(profit);

    boolean present_updateTime = true;
    list.add(present_updateTime);
    if (present_updateTime)
      list.add(updateTime);

    return list.hashCode();
  }

  @Override
  public int compareTo(StrategyBestStockThrift other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

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
    lastComparison = Boolean.valueOf(isSetStockName()).compareTo(other.isSetStockName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStockName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.stockName, other.stockName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFindTime()).compareTo(other.isSetFindTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFindTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.findTime, other.findTime);
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
    lastComparison = Boolean.valueOf(isSetUpdateTime()).compareTo(other.isSetUpdateTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUpdateTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.updateTime, other.updateTime);
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
    StringBuilder sb = new StringBuilder("StrategyBestStockThrift(");
    boolean first = true;

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
    sb.append("stockName:");
    if (this.stockName == null) {
      sb.append("null");
    } else {
      sb.append(this.stockName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("findTime:");
    if (this.findTime == null) {
      sb.append("null");
    } else {
      sb.append(this.findTime);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("profit:");
    sb.append(this.profit);
    first = false;
    if (!first) sb.append(", ");
    sb.append("updateTime:");
    sb.append(this.updateTime);
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

  private static class StrategyBestStockThriftStandardSchemeFactory implements SchemeFactory {
    public StrategyBestStockThriftStandardScheme getScheme() {
      return new StrategyBestStockThriftStandardScheme();
    }
  }

  private static class StrategyBestStockThriftStandardScheme extends StandardScheme<StrategyBestStockThrift> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, StrategyBestStockThrift struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // STRATEGY_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.strategyId = iprot.readI32();
              struct.setStrategyIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // STOCK_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.stockCode = iprot.readString();
              struct.setStockCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // STOCK_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.stockName = iprot.readString();
              struct.setStockNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // FIND_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.findTime = iprot.readString();
              struct.setFindTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // PROFIT
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.profit = iprot.readDouble();
              struct.setProfitIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // UPDATE_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.updateTime = iprot.readI64();
              struct.setUpdateTimeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, StrategyBestStockThrift struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(STRATEGY_ID_FIELD_DESC);
      oprot.writeI32(struct.strategyId);
      oprot.writeFieldEnd();
      if (struct.stockCode != null) {
        oprot.writeFieldBegin(STOCK_CODE_FIELD_DESC);
        oprot.writeString(struct.stockCode);
        oprot.writeFieldEnd();
      }
      if (struct.stockName != null) {
        oprot.writeFieldBegin(STOCK_NAME_FIELD_DESC);
        oprot.writeString(struct.stockName);
        oprot.writeFieldEnd();
      }
      if (struct.findTime != null) {
        oprot.writeFieldBegin(FIND_TIME_FIELD_DESC);
        oprot.writeString(struct.findTime);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PROFIT_FIELD_DESC);
      oprot.writeDouble(struct.profit);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(UPDATE_TIME_FIELD_DESC);
      oprot.writeI64(struct.updateTime);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class StrategyBestStockThriftTupleSchemeFactory implements SchemeFactory {
    public StrategyBestStockThriftTupleScheme getScheme() {
      return new StrategyBestStockThriftTupleScheme();
    }
  }

  private static class StrategyBestStockThriftTupleScheme extends TupleScheme<StrategyBestStockThrift> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, StrategyBestStockThrift struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetStrategyId()) {
        optionals.set(0);
      }
      if (struct.isSetStockCode()) {
        optionals.set(1);
      }
      if (struct.isSetStockName()) {
        optionals.set(2);
      }
      if (struct.isSetFindTime()) {
        optionals.set(3);
      }
      if (struct.isSetProfit()) {
        optionals.set(4);
      }
      if (struct.isSetUpdateTime()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetStrategyId()) {
        oprot.writeI32(struct.strategyId);
      }
      if (struct.isSetStockCode()) {
        oprot.writeString(struct.stockCode);
      }
      if (struct.isSetStockName()) {
        oprot.writeString(struct.stockName);
      }
      if (struct.isSetFindTime()) {
        oprot.writeString(struct.findTime);
      }
      if (struct.isSetProfit()) {
        oprot.writeDouble(struct.profit);
      }
      if (struct.isSetUpdateTime()) {
        oprot.writeI64(struct.updateTime);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, StrategyBestStockThrift struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.strategyId = iprot.readI32();
        struct.setStrategyIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.stockCode = iprot.readString();
        struct.setStockCodeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.stockName = iprot.readString();
        struct.setStockNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.findTime = iprot.readString();
        struct.setFindTimeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.profit = iprot.readDouble();
        struct.setProfitIsSet(true);
      }
      if (incoming.get(5)) {
        struct.updateTime = iprot.readI64();
        struct.setUpdateTimeIsSet(true);
      }
    }
  }

}

