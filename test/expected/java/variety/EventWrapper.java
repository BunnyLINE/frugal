/**
 * Autogenerated by Frugal Compiler (1.9.0)
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package variety.java;

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

@Generated(value = "Autogenerated by Frugal Compiler (1.9.0)", date = "2015-11-24")
public class EventWrapper implements org.apache.thrift.TBase<EventWrapper, EventWrapper._Fields>, java.io.Serializable, Cloneable, Comparable<EventWrapper> {
	private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("EventWrapper");

	private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("ID", org.apache.thrift.protocol.TType.I64, (short)1);
	private static final org.apache.thrift.protocol.TField EV_FIELD_DESC = new org.apache.thrift.protocol.TField("Ev", org.apache.thrift.protocol.TType.STRUCT, (short)2);
	private static final org.apache.thrift.protocol.TField EVENTS_FIELD_DESC = new org.apache.thrift.protocol.TField("Events", org.apache.thrift.protocol.TType.LIST, (short)3);
	private static final org.apache.thrift.protocol.TField EVENTS2_FIELD_DESC = new org.apache.thrift.protocol.TField("Events2", org.apache.thrift.protocol.TType.SET, (short)4);
	private static final org.apache.thrift.protocol.TField EVENT_MAP_FIELD_DESC = new org.apache.thrift.protocol.TField("EventMap", org.apache.thrift.protocol.TType.MAP, (short)5);
	private static final org.apache.thrift.protocol.TField NUMS_FIELD_DESC = new org.apache.thrift.protocol.TField("Nums", org.apache.thrift.protocol.TType.LIST, (short)6);
	private static final org.apache.thrift.protocol.TField ENUMS_FIELD_DESC = new org.apache.thrift.protocol.TField("Enums", org.apache.thrift.protocol.TType.LIST, (short)7);

	private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
	static {
		schemes.put(StandardScheme.class, new EventWrapperStandardSchemeFactory());
		schemes.put(TupleScheme.class, new EventWrapperTupleSchemeFactory());
	}

	public long ID; // optional
	public Event Ev; // required
	public java.util.List<Event> Events; // required
	public java.util.Set<Event> Events2; // required
	public java.util.Map<Long, Event> EventMap; // required
	public java.util.List<java.util.List<Integer>> Nums; // required
	public java.util.List<ItsAnEnum> Enums; // required
	/** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
	public enum _Fields implements org.apache.thrift.TFieldIdEnum {
		ID((short)1, "ID"),
		EV((short)2, "Ev"),
		EVENTS((short)3, "Events"),
		EVENTS2((short)4, "Events2"),
		EVENT_MAP((short)5, "EventMap"),
		NUMS((short)6, "Nums"),
		ENUMS((short)7, "Enums")
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
				case 1: // ID
					return ID;
				case 2: // EV
					return EV;
				case 3: // EVENTS
					return EVENTS;
				case 4: // EVENTS2
					return EVENTS2;
				case 5: // EVENT_MAP
					return EVENT_MAP;
				case 6: // NUMS
					return NUMS;
				case 7: // ENUMS
					return ENUMS;
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
	private byte __isset_bitfield = 0;
	private static final _Fields optionals[] = {_Fields.ID};
	public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
	static {
		Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
		tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("ID", org.apache.thrift.TFieldRequirementType.OPTIONAL,
				new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64, "id")));
		tmpMap.put(_Fields.EV, new org.apache.thrift.meta_data.FieldMetaData("Ev", org.apache.thrift.TFieldRequirementType.REQUIRED,
				new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Event.class)));
		tmpMap.put(_Fields.EVENTS, new org.apache.thrift.meta_data.FieldMetaData("Events", org.apache.thrift.TFieldRequirementType.DEFAULT,
				new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST,
						new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Event.class))));
		tmpMap.put(_Fields.EVENTS2, new org.apache.thrift.meta_data.FieldMetaData("Events2", org.apache.thrift.TFieldRequirementType.DEFAULT,
				new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET,
						new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Event.class))));
		tmpMap.put(_Fields.EVENT_MAP, new org.apache.thrift.meta_data.FieldMetaData("EventMap", org.apache.thrift.TFieldRequirementType.DEFAULT,
				new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP,
						new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64, "id"),
						new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Event.class))));
		tmpMap.put(_Fields.NUMS, new org.apache.thrift.meta_data.FieldMetaData("Nums", org.apache.thrift.TFieldRequirementType.DEFAULT,
				new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST,
						new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST,
								new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32, "int")))));
		tmpMap.put(_Fields.ENUMS, new org.apache.thrift.meta_data.FieldMetaData("Enums", org.apache.thrift.TFieldRequirementType.DEFAULT,
				new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST,
						new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, ItsAnEnum.class))));
		metaDataMap = Collections.unmodifiableMap(tmpMap);
		org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(EventWrapper.class, metaDataMap);
	}

	public EventWrapper() {
	}

	public EventWrapper(
		Event Ev,
		java.util.List<Event> Events,
		java.util.Set<Event> Events2,
		java.util.Map<Long, Event> EventMap,
		java.util.List<java.util.List<Integer>> Nums,
		java.util.List<ItsAnEnum> Enums) {
		this();
		this.Ev = Ev;
		this.Events = Events;
		this.Events2 = Events2;
		this.EventMap = EventMap;
		this.Nums = Nums;
		this.Enums = Enums;
	}

	/**
	 * Performs a deep copy on <i>other</i>.
	 */
	public EventWrapper(EventWrapper other) {
		__isset_bitfield = other.__isset_bitfield;
		this.ID = other.ID;
		if (other.isSetEv()) {
			this.Ev = new Event(other.Ev);
		}
		if (other.isSetEvents()) {
			this.Events = new ArrayList<Event>(other.Events.size());
			for (Event elem53 : other.Events) {
				Event elem54 = new Event(elem53);
				this.Events.add(elem54);
			}
		}
		if (other.isSetEvents2()) {
			this.Events2 = new HashSet<Event>(other.Events2.size());
			for (Event elem55 : other.Events2) {
				Event elem56 = new Event(elem55);
				this.Events2.add(elem56);
			}
		}
		if (other.isSetEventMap()) {
			this.EventMap = new HashMap<Long,Event>(other.EventMap.size());
			for (Map.Entry<Long, Event> elem57 : other.EventMap.entrySet()) {
				long elem59 = elem57.getKey();
				Event elem58 = new Event(elem57.getValue());
				this.EventMap.put(elem59, elem58);
			}
		}
		if (other.isSetNums()) {
			this.Nums = new ArrayList<java.util.List<Integer>>(other.Nums.size());
			for (java.util.List<Integer> elem60 : other.Nums) {
				java.util.List<Integer> elem61 = new ArrayList<Integer>(elem60.size());
				for (int elem62 : elem60) {
					int elem63 = elem62;
					elem61.add(elem63);
				}
				this.Nums.add(elem61);
			}
		}
		if (other.isSetEnums()) {
			this.Enums = new ArrayList<ItsAnEnum>(other.Enums.size());
			for (ItsAnEnum elem64 : other.Enums) {
				ItsAnEnum elem65 = elem64;
				this.Enums.add(elem65);
			}
		}
	}

	public EventWrapper deepCopy() {
		return new EventWrapper(this);
	}

	@Override
	public void clear() {
		setIDIsSet(false);
		this.ID = 0;

		this.Ev = null;

		this.Events = null;

		this.Events2 = null;

		this.EventMap = null;

		this.Nums = null;

		this.Enums = null;

	}

	public long getID() {
		return this.ID;
	}

	public EventWrapper setID(long ID) {
		this.ID = ID;
		setIDIsSet(true);
		return this;
	}

	public void unsetID() {
		__isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
	}

	/** Returns true if field ID is set (has been assigned a value) and false otherwise */
	public boolean isSetID() {
		return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
	}

	public void setIDIsSet(boolean value) {
		__isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
	}

	public Event getEv() {
		return this.Ev;
	}

	public EventWrapper setEv(Event Ev) {
		this.Ev = Ev;
		return this;
	}

	public void unsetEv() {
		this.Ev = null;
	}

	/** Returns true if field Ev is set (has been assigned a value) and false otherwise */
	public boolean isSetEv() {
		return this.Ev != null;
	}

	public void setEvIsSet(boolean value) {
		if (!value) {
			this.Ev = null;
		}
	}

	public int getEventsSize() {
		return (this.Events == null) ? 0 : this.Events.size();
	}

	public java.util.Iterator<Event> getEventsIterator() {
		return (this.Events == null) ? null : this.Events.iterator();
	}

	public void addToEvents(Event elem) {
		if (this.Events == null) {
			this.Events = new ArrayList<Event>();
		}
		this.Events.add(elem);
	}

	public java.util.List<Event> getEvents() {
		return this.Events;
	}

	public EventWrapper setEvents(java.util.List<Event> Events) {
		this.Events = Events;
		return this;
	}

	public void unsetEvents() {
		this.Events = null;
	}

	/** Returns true if field Events is set (has been assigned a value) and false otherwise */
	public boolean isSetEvents() {
		return this.Events != null;
	}

	public void setEventsIsSet(boolean value) {
		if (!value) {
			this.Events = null;
		}
	}

	public int getEvents2Size() {
		return (this.Events2 == null) ? 0 : this.Events2.size();
	}

	public java.util.Iterator<Event> getEvents2Iterator() {
		return (this.Events2 == null) ? null : this.Events2.iterator();
	}

	public void addToEvents2(Event elem) {
		if (this.Events2 == null) {
			this.Events2 = new HashSet<Event>();
		}
		this.Events2.add(elem);
	}

	public java.util.Set<Event> getEvents2() {
		return this.Events2;
	}

	public EventWrapper setEvents2(java.util.Set<Event> Events2) {
		this.Events2 = Events2;
		return this;
	}

	public void unsetEvents2() {
		this.Events2 = null;
	}

	/** Returns true if field Events2 is set (has been assigned a value) and false otherwise */
	public boolean isSetEvents2() {
		return this.Events2 != null;
	}

	public void setEvents2IsSet(boolean value) {
		if (!value) {
			this.Events2 = null;
		}
	}

	public int getEventMapSize() {
		return (this.EventMap == null) ? 0 : this.EventMap.size();
	}

	public void putToEventMap(long key, Event val) {
		if (this.EventMap == null) {
			this.EventMap = new HashMap<Long,Event>();
		}
		this.EventMap.put(key, val);
	}

	public java.util.Map<Long, Event> getEventMap() {
		return this.EventMap;
	}

	public EventWrapper setEventMap(java.util.Map<Long, Event> EventMap) {
		this.EventMap = EventMap;
		return this;
	}

	public void unsetEventMap() {
		this.EventMap = null;
	}

	/** Returns true if field EventMap is set (has been assigned a value) and false otherwise */
	public boolean isSetEventMap() {
		return this.EventMap != null;
	}

	public void setEventMapIsSet(boolean value) {
		if (!value) {
			this.EventMap = null;
		}
	}

	public int getNumsSize() {
		return (this.Nums == null) ? 0 : this.Nums.size();
	}

	public java.util.Iterator<java.util.List<Integer>> getNumsIterator() {
		return (this.Nums == null) ? null : this.Nums.iterator();
	}

	public void addToNums(java.util.List<Integer> elem) {
		if (this.Nums == null) {
			this.Nums = new ArrayList<java.util.List<Integer>>();
		}
		this.Nums.add(elem);
	}

	public java.util.List<java.util.List<Integer>> getNums() {
		return this.Nums;
	}

	public EventWrapper setNums(java.util.List<java.util.List<Integer>> Nums) {
		this.Nums = Nums;
		return this;
	}

	public void unsetNums() {
		this.Nums = null;
	}

	/** Returns true if field Nums is set (has been assigned a value) and false otherwise */
	public boolean isSetNums() {
		return this.Nums != null;
	}

	public void setNumsIsSet(boolean value) {
		if (!value) {
			this.Nums = null;
		}
	}

	public int getEnumsSize() {
		return (this.Enums == null) ? 0 : this.Enums.size();
	}

	public java.util.Iterator<ItsAnEnum> getEnumsIterator() {
		return (this.Enums == null) ? null : this.Enums.iterator();
	}

	public void addToEnums(ItsAnEnum elem) {
		if (this.Enums == null) {
			this.Enums = new ArrayList<ItsAnEnum>();
		}
		this.Enums.add(elem);
	}

	public java.util.List<ItsAnEnum> getEnums() {
		return this.Enums;
	}

	public EventWrapper setEnums(java.util.List<ItsAnEnum> Enums) {
		this.Enums = Enums;
		return this;
	}

	public void unsetEnums() {
		this.Enums = null;
	}

	/** Returns true if field Enums is set (has been assigned a value) and false otherwise */
	public boolean isSetEnums() {
		return this.Enums != null;
	}

	public void setEnumsIsSet(boolean value) {
		if (!value) {
			this.Enums = null;
		}
	}

	public void setFieldValue(_Fields field, Object value) {
		switch (field) {
		case ID:
			if (value == null) {
				unsetID();
			} else {
				setID((Long)value);
			}
			break;

		case EV:
			if (value == null) {
				unsetEv();
			} else {
				setEv((Event)value);
			}
			break;

		case EVENTS:
			if (value == null) {
				unsetEvents();
			} else {
				setEvents((java.util.List<Event>)value);
			}
			break;

		case EVENTS2:
			if (value == null) {
				unsetEvents2();
			} else {
				setEvents2((java.util.Set<Event>)value);
			}
			break;

		case EVENT_MAP:
			if (value == null) {
				unsetEventMap();
			} else {
				setEventMap((java.util.Map<Long, Event>)value);
			}
			break;

		case NUMS:
			if (value == null) {
				unsetNums();
			} else {
				setNums((java.util.List<java.util.List<Integer>>)value);
			}
			break;

		case ENUMS:
			if (value == null) {
				unsetEnums();
			} else {
				setEnums((java.util.List<ItsAnEnum>)value);
			}
			break;

		}
	}

	public Object getFieldValue(_Fields field) {
		switch (field) {
		case ID:
			return getID();

		case EV:
			return getEv();

		case EVENTS:
			return getEvents();

		case EVENTS2:
			return getEvents2();

		case EVENT_MAP:
			return getEventMap();

		case NUMS:
			return getNums();

		case ENUMS:
			return getEnums();

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
			return isSetID();
		case EV:
			return isSetEv();
		case EVENTS:
			return isSetEvents();
		case EVENTS2:
			return isSetEvents2();
		case EVENT_MAP:
			return isSetEventMap();
		case NUMS:
			return isSetNums();
		case ENUMS:
			return isSetEnums();
		}
		throw new IllegalStateException();
	}

	@Override
	public boolean equals(Object that) {
		if (that == null)
			return false;
		if (that instanceof EventWrapper)
			return this.equals((EventWrapper)that);
		return false;
	}

	public boolean equals(EventWrapper that) {
		if (that == null)
			return false;

		boolean this_present_ID = true && this.isSetID();
		boolean that_present_ID = true && that.isSetID();
		if (this_present_ID || that_present_ID) {
			if (!(this_present_ID && that_present_ID))
				return false;
			if (this.ID != that.ID)
				return false;
		}

		boolean this_present_Ev = true && this.isSetEv();
		boolean that_present_Ev = true && that.isSetEv();
		if (this_present_Ev || that_present_Ev) {
			if (!(this_present_Ev && that_present_Ev))
				return false;
			if (!this.Ev.equals(that.Ev))
				return false;
		}

		boolean this_present_Events = true && this.isSetEvents();
		boolean that_present_Events = true && that.isSetEvents();
		if (this_present_Events || that_present_Events) {
			if (!(this_present_Events && that_present_Events))
				return false;
			if (!this.Events.equals(that.Events))
				return false;
		}

		boolean this_present_Events2 = true && this.isSetEvents2();
		boolean that_present_Events2 = true && that.isSetEvents2();
		if (this_present_Events2 || that_present_Events2) {
			if (!(this_present_Events2 && that_present_Events2))
				return false;
			if (!this.Events2.equals(that.Events2))
				return false;
		}

		boolean this_present_EventMap = true && this.isSetEventMap();
		boolean that_present_EventMap = true && that.isSetEventMap();
		if (this_present_EventMap || that_present_EventMap) {
			if (!(this_present_EventMap && that_present_EventMap))
				return false;
			if (!this.EventMap.equals(that.EventMap))
				return false;
		}

		boolean this_present_Nums = true && this.isSetNums();
		boolean that_present_Nums = true && that.isSetNums();
		if (this_present_Nums || that_present_Nums) {
			if (!(this_present_Nums && that_present_Nums))
				return false;
			if (!this.Nums.equals(that.Nums))
				return false;
		}

		boolean this_present_Enums = true && this.isSetEnums();
		boolean that_present_Enums = true && that.isSetEnums();
		if (this_present_Enums || that_present_Enums) {
			if (!(this_present_Enums && that_present_Enums))
				return false;
			if (!this.Enums.equals(that.Enums))
				return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		List<Object> list = new ArrayList<Object>();

		boolean present_ID = true && (isSetID());
		list.add(present_ID);
		if (present_ID)
			list.add(ID);

		boolean present_Ev = true && (isSetEv());
		list.add(present_Ev);
		if (present_Ev)
			list.add(Ev);

		boolean present_Events = true && (isSetEvents());
		list.add(present_Events);
		if (present_Events)
			list.add(Events);

		boolean present_Events2 = true && (isSetEvents2());
		list.add(present_Events2);
		if (present_Events2)
			list.add(Events2);

		boolean present_EventMap = true && (isSetEventMap());
		list.add(present_EventMap);
		if (present_EventMap)
			list.add(EventMap);

		boolean present_Nums = true && (isSetNums());
		list.add(present_Nums);
		if (present_Nums)
			list.add(Nums);

		boolean present_Enums = true && (isSetEnums());
		list.add(present_Enums);
		if (present_Enums)
			list.add(Enums);

		return list.hashCode();
	}

	@Override
	public int compareTo(EventWrapper other) {
		if (!getClass().equals(other.getClass())) {
			return getClass().getName().compareTo(other.getClass().getName());
		}

		int lastComparison = 0;

		lastComparison = Boolean.valueOf(isSetID()).compareTo(other.isSetID());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetID()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ID, other.ID);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetEv()).compareTo(other.isSetEv());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetEv()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Ev, other.Ev);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetEvents()).compareTo(other.isSetEvents());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetEvents()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Events, other.Events);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetEvents2()).compareTo(other.isSetEvents2());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetEvents2()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Events2, other.Events2);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetEventMap()).compareTo(other.isSetEventMap());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetEventMap()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.EventMap, other.EventMap);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetNums()).compareTo(other.isSetNums());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetNums()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Nums, other.Nums);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetEnums()).compareTo(other.isSetEnums());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetEnums()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Enums, other.Enums);
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
		StringBuilder sb = new StringBuilder("EventWrapper(");
		boolean first = true;

		if (isSetID()) {
			sb.append("ID:");
			sb.append(this.ID);
			first = false;
		}
		if (!first) sb.append(", ");
		sb.append("Ev:");
		if (this.Ev == null) {
			sb.append("null");
		} else {
			sb.append(this.Ev);
		}
		first = false;
		if (!first) sb.append(", ");
		sb.append("Events:");
		if (this.Events == null) {
			sb.append("null");
		} else {
			sb.append(this.Events);
		}
		first = false;
		if (!first) sb.append(", ");
		sb.append("Events2:");
		if (this.Events2 == null) {
			sb.append("null");
		} else {
			sb.append(this.Events2);
		}
		first = false;
		if (!first) sb.append(", ");
		sb.append("EventMap:");
		if (this.EventMap == null) {
			sb.append("null");
		} else {
			sb.append(this.EventMap);
		}
		first = false;
		if (!first) sb.append(", ");
		sb.append("Nums:");
		if (this.Nums == null) {
			sb.append("null");
		} else {
			sb.append(this.Nums);
		}
		first = false;
		if (!first) sb.append(", ");
		sb.append("Enums:");
		if (this.Enums == null) {
			sb.append("null");
		} else {
			sb.append(this.Enums);
		}
		first = false;
		sb.append(")");
		return sb.toString();
	}

	public void validate() throws org.apache.thrift.TException {
		// check for required fields
		if (Ev == null) {
			throw new org.apache.thrift.protocol.TProtocolException("Required field 'Ev' was not present! Struct: " + toString());
		}
		// check for sub-struct validity
		if (Ev != null) {
			Ev.validate();
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

	private static class EventWrapperStandardSchemeFactory implements SchemeFactory {
		public EventWrapperStandardScheme getScheme() {
			return new EventWrapperStandardScheme();
		}
	}

	private static class EventWrapperStandardScheme extends StandardScheme<EventWrapper> {

		public void read(org.apache.thrift.protocol.TProtocol iprot, EventWrapper struct) throws org.apache.thrift.TException {
			org.apache.thrift.protocol.TField schemeField;
			iprot.readStructBegin();
			while (true) {
				schemeField = iprot.readFieldBegin();
				if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
					break;
				}
				switch (schemeField.id) {
					case 1: // ID
						if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
							struct.ID = iprot.readI64();
							struct.setIDIsSet(true);
						} else {
							org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
						}
						break;
					case 2: // EV
						if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
							struct.Ev = new Event();
							struct.Ev.read(iprot);
							struct.setEvIsSet(true);
						} else {
							org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
						}
						break;
					case 3: // EVENTS
						if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
							org.apache.thrift.protocol.TList elem66 = iprot.readListBegin();
							struct.Events = new ArrayList<Event>(elem66.size);
							for (int elem67 = 0; elem67 < elem66.size; ++elem67) {
								Event elem68 = new Event();
								elem68.read(iprot);
								struct.Events.add(elem68);
							}
							iprot.readListEnd();
							struct.setEventsIsSet(true);
						} else {
							org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
						}
						break;
					case 4: // EVENTS2
						if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
							org.apache.thrift.protocol.TSet elem69 = iprot.readSetBegin();
							struct.Events2 = new HashSet<Event>(2*elem69.size);
							for (int elem70 = 0; elem70 < elem69.size; ++elem70) {
								Event elem71 = new Event();
								elem71.read(iprot);
								struct.Events2.add(elem71);
							}
							iprot.readSetEnd();
							struct.setEvents2IsSet(true);
						} else {
							org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
						}
						break;
					case 5: // EVENT_MAP
						if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
							org.apache.thrift.protocol.TMap elem72 = iprot.readMapBegin();
							struct.EventMap = new HashMap<Long,Event>(2*elem72.size);
							for (int elem73 = 0; elem73 < elem72.size; ++elem73) {
								long elem75 = iprot.readI64();
								Event elem74 = new Event();
								elem74.read(iprot);
								struct.EventMap.put(elem75, elem74);
							}
							iprot.readMapEnd();
							struct.setEventMapIsSet(true);
						} else {
							org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
						}
						break;
					case 6: // NUMS
						if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
							org.apache.thrift.protocol.TList elem76 = iprot.readListBegin();
							struct.Nums = new ArrayList<java.util.List<Integer>>(elem76.size);
							for (int elem77 = 0; elem77 < elem76.size; ++elem77) {
								org.apache.thrift.protocol.TList elem79 = iprot.readListBegin();
								java.util.List<Integer> elem78 = new ArrayList<Integer>(elem79.size);
								for (int elem80 = 0; elem80 < elem79.size; ++elem80) {
									int elem81 = iprot.readI32();
									elem78.add(elem81);
								}
								iprot.readListEnd();
								struct.Nums.add(elem78);
							}
							iprot.readListEnd();
							struct.setNumsIsSet(true);
						} else {
							org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
						}
						break;
					case 7: // ENUMS
						if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
							org.apache.thrift.protocol.TList elem82 = iprot.readListBegin();
							struct.Enums = new ArrayList<ItsAnEnum>(elem82.size);
							for (int elem83 = 0; elem83 < elem82.size; ++elem83) {
								ItsAnEnum elem84 = ItsAnEnum.findByValue(iprot.readI32());
								struct.Enums.add(elem84);
							}
							iprot.readListEnd();
							struct.setEnumsIsSet(true);
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

		public void write(org.apache.thrift.protocol.TProtocol oprot, EventWrapper struct) throws org.apache.thrift.TException {
			struct.validate();

			oprot.writeStructBegin(STRUCT_DESC);
			if (struct.isSetID()) {
				oprot.writeFieldBegin(ID_FIELD_DESC);
				oprot.writeI64(struct.ID);
				oprot.writeFieldEnd();
			}
			if (struct.Ev != null) {
				oprot.writeFieldBegin(EV_FIELD_DESC);
				struct.Ev.write(oprot);
				oprot.writeFieldEnd();
			}
			if (struct.Events != null) {
				oprot.writeFieldBegin(EVENTS_FIELD_DESC);
				oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.Events.size()));
				for (Event elem85 : struct.Events) {
					elem85.write(oprot);
				}
				oprot.writeListEnd();
				oprot.writeFieldEnd();
			}
			if (struct.Events2 != null) {
				oprot.writeFieldBegin(EVENTS2_FIELD_DESC);
				oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRUCT, struct.Events2.size()));
				for (Event elem86 : struct.Events2) {
					elem86.write(oprot);
				}
				oprot.writeSetEnd();
				oprot.writeFieldEnd();
			}
			if (struct.EventMap != null) {
				oprot.writeFieldBegin(EVENT_MAP_FIELD_DESC);
				oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I64, org.apache.thrift.protocol.TType.STRUCT, struct.EventMap.size()));
				for (Map.Entry<Long, Event> elem87 : struct.EventMap.entrySet()) {
					oprot.writeI64(elem87.getKey());
					elem87.getValue().write(oprot);
				}
				oprot.writeMapEnd();
				oprot.writeFieldEnd();
			}
			if (struct.Nums != null) {
				oprot.writeFieldBegin(NUMS_FIELD_DESC);
				oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.LIST, struct.Nums.size()));
				for (java.util.List<Integer> elem88 : struct.Nums) {
					oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, elem88.size()));
					for (int elem89 : elem88) {
						oprot.writeI32(elem89);
					}
					oprot.writeListEnd();
				}
				oprot.writeListEnd();
				oprot.writeFieldEnd();
			}
			if (struct.Enums != null) {
				oprot.writeFieldBegin(ENUMS_FIELD_DESC);
				oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, struct.Enums.size()));
				for (ItsAnEnum elem90 : struct.Enums) {
					oprot.writeI32(elem90.getValue());
				}
				oprot.writeListEnd();
				oprot.writeFieldEnd();
			}
			oprot.writeFieldStop();
			oprot.writeStructEnd();
		}

	}

	private static class EventWrapperTupleSchemeFactory implements SchemeFactory {
		public EventWrapperTupleScheme getScheme() {
			return new EventWrapperTupleScheme();
		}
	}

	private static class EventWrapperTupleScheme extends TupleScheme<EventWrapper> {

		@Override
		public void write(org.apache.thrift.protocol.TProtocol prot, EventWrapper struct) throws org.apache.thrift.TException {
			TTupleProtocol oprot = (TTupleProtocol) prot;
			struct.Ev.write(oprot);
			BitSet optionals = new BitSet();
			if (struct.isSetID()) {
				optionals.set(0);
			}
			if (struct.isSetEvents()) {
				optionals.set(1);
			}
			if (struct.isSetEvents2()) {
				optionals.set(2);
			}
			if (struct.isSetEventMap()) {
				optionals.set(3);
			}
			if (struct.isSetNums()) {
				optionals.set(4);
			}
			if (struct.isSetEnums()) {
				optionals.set(5);
			}
			oprot.writeBitSet(optionals, 6);
			if (struct.isSetID()) {
				oprot.writeI64(struct.ID);
			}
			if (struct.isSetEvents()) {
				oprot.writeI32(struct.Events.size());
				for (Event elem91 : struct.Events) {
					elem91.write(oprot);
				}
			}
			if (struct.isSetEvents2()) {
				oprot.writeI32(struct.Events2.size());
				for (Event elem92 : struct.Events2) {
					elem92.write(oprot);
				}
			}
			if (struct.isSetEventMap()) {
				oprot.writeI32(struct.EventMap.size());
				for (Map.Entry<Long, Event> elem93 : struct.EventMap.entrySet()) {
					oprot.writeI64(elem93.getKey());
					elem93.getValue().write(oprot);
				}
			}
			if (struct.isSetNums()) {
				oprot.writeI32(struct.Nums.size());
				for (java.util.List<Integer> elem94 : struct.Nums) {
					oprot.writeI32(elem94.size());
					for (int elem95 : elem94) {
						oprot.writeI32(elem95);
					}
				}
			}
			if (struct.isSetEnums()) {
				oprot.writeI32(struct.Enums.size());
				for (ItsAnEnum elem96 : struct.Enums) {
					oprot.writeI32(elem96.getValue());
				}
			}
		}

		@Override
		public void read(org.apache.thrift.protocol.TProtocol prot, EventWrapper struct) throws org.apache.thrift.TException {
			TTupleProtocol iprot = (TTupleProtocol) prot;
			struct.Ev = new Event();
			struct.Ev.read(iprot);
			struct.setEvIsSet(true);
			BitSet incoming = iprot.readBitSet(6);
			if (incoming.get(0)) {
				struct.ID = iprot.readI64();
				struct.setIDIsSet(true);
			}
			if (incoming.get(1)) {
				org.apache.thrift.protocol.TList elem97 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
				struct.Events = new ArrayList<Event>(elem97.size);
				for (int elem98 = 0; elem98 < elem97.size; ++elem98) {
					Event elem99 = new Event();
					elem99.read(iprot);
					struct.Events.add(elem99);
				}
				struct.setEventsIsSet(true);
			}
			if (incoming.get(2)) {
				org.apache.thrift.protocol.TSet elem100 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
				struct.Events2 = new HashSet<Event>(2*elem100.size);
				for (int elem101 = 0; elem101 < elem100.size; ++elem101) {
					Event elem102 = new Event();
					elem102.read(iprot);
					struct.Events2.add(elem102);
				}
				struct.setEvents2IsSet(true);
			}
			if (incoming.get(3)) {
				org.apache.thrift.protocol.TMap elem103 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I64, org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
				struct.EventMap = new HashMap<Long,Event>(2*elem103.size);
				for (int elem104 = 0; elem104 < elem103.size; ++elem104) {
					long elem106 = iprot.readI64();
					Event elem105 = new Event();
					elem105.read(iprot);
					struct.EventMap.put(elem106, elem105);
				}
				struct.setEventMapIsSet(true);
			}
			if (incoming.get(4)) {
				org.apache.thrift.protocol.TList elem107 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.LIST, iprot.readI32());
				struct.Nums = new ArrayList<java.util.List<Integer>>(elem107.size);
				for (int elem108 = 0; elem108 < elem107.size; ++elem108) {
					org.apache.thrift.protocol.TList elem110 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, iprot.readI32());
					java.util.List<Integer> elem109 = new ArrayList<Integer>(elem110.size);
					for (int elem111 = 0; elem111 < elem110.size; ++elem111) {
						int elem112 = iprot.readI32();
						elem109.add(elem112);
					}
					struct.Nums.add(elem109);
				}
				struct.setNumsIsSet(true);
			}
			if (incoming.get(5)) {
				org.apache.thrift.protocol.TList elem113 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, iprot.readI32());
				struct.Enums = new ArrayList<ItsAnEnum>(elem113.size);
				for (int elem114 = 0; elem114 < elem113.size; ++elem114) {
					ItsAnEnum elem115 = ItsAnEnum.findByValue(iprot.readI32());
					struct.Enums.add(elem115);
				}
				struct.setEnumsIsSet(true);
			}
		}

	}

}
