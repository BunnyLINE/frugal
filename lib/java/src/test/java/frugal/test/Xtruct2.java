/**
 * Autogenerated by Frugal Compiler (3.14.1)
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package frugal.test;

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
import java.util.Objects;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Xtruct2 implements org.apache.thrift.TBase<Xtruct2, Xtruct2._Fields>, java.io.Serializable, Cloneable, Comparable<Xtruct2> {
	private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Xtruct2");

	private static final org.apache.thrift.protocol.TField BYTE_THING_FIELD_DESC = new org.apache.thrift.protocol.TField("byte_thing", org.apache.thrift.protocol.TType.BYTE, (short)1);
	private static final org.apache.thrift.protocol.TField STRUCT_THING_FIELD_DESC = new org.apache.thrift.protocol.TField("struct_thing", org.apache.thrift.protocol.TType.STRUCT, (short)2);
	private static final org.apache.thrift.protocol.TField I32_THING_FIELD_DESC = new org.apache.thrift.protocol.TField("i32_thing", org.apache.thrift.protocol.TType.I32, (short)3);

	public byte byte_thing;
	public Xtruct struct_thing;
	public int i32_thing;
	/** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
	public enum _Fields implements org.apache.thrift.TFieldIdEnum {
		BYTE_THING((short)1, "byte_thing"),
		STRUCT_THING((short)2, "struct_thing"),
		I32_THING((short)3, "i32_thing")
		;

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
				case 1: // BYTE_THING
					return BYTE_THING;
				case 2: // STRUCT_THING
					return STRUCT_THING;
				case 3: // I32_THING
					return I32_THING;
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
	private static final int __BYTE_THING_ISSET_ID = 0;
	private static final int __I32_THING_ISSET_ID = 1;
	private byte __isset_bitfield = 0;
	public Xtruct2() {
	}

	public Xtruct2(
		byte byte_thing,
		Xtruct struct_thing,
		int i32_thing) {
		this();
		this.byte_thing = byte_thing;
		setByte_thingIsSet(true);
		this.struct_thing = struct_thing;
		this.i32_thing = i32_thing;
		setI32_thingIsSet(true);
	}

	/**
	 * Performs a deep copy on <i>other</i>.
	 */
	public Xtruct2(Xtruct2 other) {
		__isset_bitfield = other.__isset_bitfield;
		this.byte_thing = other.byte_thing;
		if (other.isSetStruct_thing()) {
			this.struct_thing = new Xtruct(other.struct_thing);
		}
		this.i32_thing = other.i32_thing;
	}

	public Xtruct2 deepCopy() {
		return new Xtruct2(this);
	}

	@Override
	public void clear() {
		setByte_thingIsSet(false);
		this.byte_thing = (byte)0;

		this.struct_thing = null;

		setI32_thingIsSet(false);
		this.i32_thing = 0;

	}

	public byte getByte_thing() {
		return this.byte_thing;
	}

	public Xtruct2 setByte_thing(byte byte_thing) {
		this.byte_thing = byte_thing;
		setByte_thingIsSet(true);
		return this;
	}

	public void unsetByte_thing() {
		__isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __BYTE_THING_ISSET_ID);
	}

	/** Returns true if field byte_thing is set (has been assigned a value) and false otherwise */
	public boolean isSetByte_thing() {
		return EncodingUtils.testBit(__isset_bitfield, __BYTE_THING_ISSET_ID);
	}

	public void setByte_thingIsSet(boolean value) {
		__isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __BYTE_THING_ISSET_ID, value);
	}

	public Xtruct getStruct_thing() {
		return this.struct_thing;
	}

	public Xtruct2 setStruct_thing(Xtruct struct_thing) {
		this.struct_thing = struct_thing;
		return this;
	}

	public void unsetStruct_thing() {
		this.struct_thing = null;
	}

	/** Returns true if field struct_thing is set (has been assigned a value) and false otherwise */
	public boolean isSetStruct_thing() {
		return this.struct_thing != null;
	}

	public void setStruct_thingIsSet(boolean value) {
		if (!value) {
			this.struct_thing = null;
		}
	}

	public int getI32_thing() {
		return this.i32_thing;
	}

	public Xtruct2 setI32_thing(int i32_thing) {
		this.i32_thing = i32_thing;
		setI32_thingIsSet(true);
		return this;
	}

	public void unsetI32_thing() {
		__isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __I32_THING_ISSET_ID);
	}

	/** Returns true if field i32_thing is set (has been assigned a value) and false otherwise */
	public boolean isSetI32_thing() {
		return EncodingUtils.testBit(__isset_bitfield, __I32_THING_ISSET_ID);
	}

	public void setI32_thingIsSet(boolean value) {
		__isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __I32_THING_ISSET_ID, value);
	}

	public void setFieldValue(_Fields field, Object value) {
		switch (field) {
		case BYTE_THING:
			if (value == null) {
				unsetByte_thing();
			} else {
				setByte_thing((Byte)value);
			}
			break;

		case STRUCT_THING:
			if (value == null) {
				unsetStruct_thing();
			} else {
				setStruct_thing((Xtruct)value);
			}
			break;

		case I32_THING:
			if (value == null) {
				unsetI32_thing();
			} else {
				setI32_thing((Integer)value);
			}
			break;

		}
	}

	public Object getFieldValue(_Fields field) {
		switch (field) {
		case BYTE_THING:
			return getByte_thing();

		case STRUCT_THING:
			return getStruct_thing();

		case I32_THING:
			return getI32_thing();

		}
		throw new IllegalStateException();
	}

	/** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
	public boolean isSet(_Fields field) {
		if (field == null) {
			throw new IllegalArgumentException();
		}

		switch (field) {
		case BYTE_THING:
			return isSetByte_thing();
		case STRUCT_THING:
			return isSetStruct_thing();
		case I32_THING:
			return isSetI32_thing();
		}
		throw new IllegalStateException();
	}

	@Override
	public boolean equals(Object that) {
		if (that == null)
			return false;
		if (that instanceof Xtruct2)
			return this.equals((Xtruct2)that);
		return false;
	}

	public boolean equals(Xtruct2 that) {
		if (that == null)
			return false;
		if (this.byte_thing != that.byte_thing)
			return false;
		if (!Objects.equals(this.struct_thing, that.struct_thing))
			return false;
		if (this.i32_thing != that.i32_thing)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		List<Object> list = new ArrayList<Object>();

		boolean present_byte_thing = true;
		list.add(present_byte_thing);
		if (present_byte_thing)
			list.add(byte_thing);

		boolean present_struct_thing = true && (isSetStruct_thing());
		list.add(present_struct_thing);
		if (present_struct_thing)
			list.add(struct_thing);

		boolean present_i32_thing = true;
		list.add(present_i32_thing);
		if (present_i32_thing)
			list.add(i32_thing);

		return list.hashCode();
	}

	@Override
	public int compareTo(Xtruct2 other) {
		if (!getClass().equals(other.getClass())) {
			return getClass().getName().compareTo(other.getClass().getName());
		}

		int lastComparison = 0;

		lastComparison = Boolean.compare(isSetByte_thing(), other.isSetByte_thing());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetByte_thing()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.byte_thing, other.byte_thing);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.compare(isSetStruct_thing(), other.isSetStruct_thing());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetStruct_thing()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.struct_thing, other.struct_thing);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.compare(isSetI32_thing(), other.isSetI32_thing());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetI32_thing()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.i32_thing, other.i32_thing);
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
		if (iprot.getScheme() != StandardScheme.class) {
			throw new UnsupportedOperationException();
		}
		new Xtruct2StandardScheme().read(iprot, this);
	}

	public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
		if (oprot.getScheme() != StandardScheme.class) {
			throw new UnsupportedOperationException();
		}
		new Xtruct2StandardScheme().write(oprot, this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Xtruct2(");
		boolean first = true;

		sb.append("byte_thing:");
		sb.append(this.byte_thing);
		first = false;
		if (!first) sb.append(", ");
		sb.append("struct_thing:");
		sb.append(this.struct_thing);
		first = false;
		if (!first) sb.append(", ");
		sb.append("i32_thing:");
		sb.append(this.i32_thing);
		first = false;
		sb.append(")");
		return sb.toString();
	}

	public void validate() throws org.apache.thrift.TException {
		// check for required fields
		// check for sub-struct validity
		if (struct_thing != null) {
			struct_thing.validate();
		}
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

	private static class Xtruct2StandardScheme extends StandardScheme<Xtruct2> {

		public void read(org.apache.thrift.protocol.TProtocol iprot, Xtruct2 struct) throws org.apache.thrift.TException {
			org.apache.thrift.protocol.TField schemeField;
			iprot.readStructBegin();
			while (true) {
				schemeField = iprot.readFieldBegin();
				if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
					break;
				}
				switch (schemeField.id) {
					case 1: // BYTE_THING
						if (schemeField.type == org.apache.thrift.protocol.TType.BYTE) {
							struct.byte_thing = iprot.readByte();
							struct.setByte_thingIsSet(true);
						} else {
							org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
						}
						break;
					case 2: // STRUCT_THING
						if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
							struct.struct_thing = new Xtruct();
							struct.struct_thing.read(iprot);
							struct.setStruct_thingIsSet(true);
						} else {
							org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
						}
						break;
					case 3: // I32_THING
						if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
							struct.i32_thing = iprot.readI32();
							struct.setI32_thingIsSet(true);
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

		public void write(org.apache.thrift.protocol.TProtocol oprot, Xtruct2 struct) throws org.apache.thrift.TException {
			struct.validate();

			oprot.writeStructBegin(STRUCT_DESC);
			oprot.writeFieldBegin(BYTE_THING_FIELD_DESC);
			byte elem8 = struct.byte_thing;
			oprot.writeByte(elem8);
			oprot.writeFieldEnd();
			if (struct.isSetStruct_thing()) {
				oprot.writeFieldBegin(STRUCT_THING_FIELD_DESC);
				struct.struct_thing.write(oprot);
				oprot.writeFieldEnd();
			}
			oprot.writeFieldBegin(I32_THING_FIELD_DESC);
			int elem9 = struct.i32_thing;
			oprot.writeI32(elem9);
			oprot.writeFieldEnd();
			oprot.writeFieldStop();
			oprot.writeStructEnd();
		}

	}

}
