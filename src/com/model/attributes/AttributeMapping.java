package com.model.attributes;


import java.util.EnumMap;
import com.controller.enums.AttributeTypes;

/**
 * This class represents the mapping of attribute types to values
 * Provides methods to set and get a specific attribute value, set stamina, check whether attribute is at minimum or maximum value,
 * and add map attributes
 */
public class AttributeMapping {
	private final EnumMap<AttributeTypes, ClampedInt> attributes;
	private ClampedInt stamina;
	
	
	public static final int ATTRIBUTE_MINIMUM = 0;
	public static int ATTRIBUTE_MAXIMUM = 100;
	
	/**
	 * when an attribute component is constructed: 
	 * 		stamina is defaulted to a value of ATTRIBUTE_MAXIMUM
	 * 		each attribute in AttributeTypes is mapped to a default value of ATTRIBUTE_MINIMUM
	 * 
	 * once constructed the new instance is a blank slate, any specific attribute values must
	 * be assigned after construction by using the setAttribute method
	 */
	public AttributeMapping()
	{
		this.attributes = new EnumMap<AttributeTypes, ClampedInt>(AttributeTypes.class);
		this.stamina = new ClampedInt(AttributeMapping.ATTRIBUTE_MAXIMUM, AttributeMapping.ATTRIBUTE_MINIMUM, AttributeMapping.ATTRIBUTE_MAXIMUM);
		for (AttributeTypes attribute : AttributeTypes.values()) { attributes.put(attribute, new ClampedInt(AttributeMapping.ATTRIBUTE_MINIMUM, AttributeMapping.ATTRIBUTE_MINIMUM, AttributeMapping.ATTRIBUTE_MAXIMUM)); }
	}
	
	/**
	 * assign a new value to the ClampedInt which represents an AttributeType.
	 * this new value will be clamped within bounds of ATTRIBUTE_MINIMUM and ATTRIBUTE_MAXIMUM
	 * @param type		target attribute key
	 * @param value		new value to represent the attribute
	 */
	public void setAttribute(AttributeTypes type, int value)
	{
		attributes.get(type).setValue(value);
	}
		
	/**
	 * Get the value of the attribute which represents the specified AttributeType
	 * @param type 		target attribute key
	 * @return			value of the attribute
	 */
	public int getAttributeValue(AttributeTypes type)
	{
		return attributes.get(type).getValue();
	}
	
	/**
	 * assign the stamina to a new value, this value
	 * is then clamped
	 * 
	 * @param val new stamina value
	 */
	public void setStamina(int val)
	{
		stamina.setValue(val);;
	}
	
	/**
	 * determine if a certain attribute's value is currently at
	 * it's maximum allowed value
	 * 
	 * @param type	the type of attribute to query
	 * @return		true if attribute is maximal, otherwise false
	 */
	public boolean isAttributeAtMax(AttributeTypes type)
	{
		return attributes.get(type).isMax();
	}
	
	/**
	 * determine if a certain attribute's value is currently at
	 * it's minimum allowed value
	 * 
	 * @param type	the type of attribute to query
	 * @return		true if attribute is minimal, otherwise false
	 */
	public boolean isAttributeAtMin(AttributeTypes type)
	{
		return attributes.get(type).isMin();
	}
	
	/**
	 * determine if stamina is currently at
	 * it's maximum allowed value
	 * 
	 * @return		true if attribute is maximal, otherwise false
	 */
	public boolean isStaminaMax()
	{
		return stamina.isMax();
	}
	
	/**
	 * determine if stamina is currently at
	 * it's minimum allowed value
	 * 
	 * @return		true if attribute is minimal, otherwise false
	 */
	public boolean isStaminaMin()
	{
		return stamina.isMin();
	}
	
	/**
	 * get the current value of stamina
	 * @return value of stamina
	 */
	public int getStamina()
	{
		return stamina.getValue();
	}
	
	/**
	 * Calculates the sum of attribute values in the map
	 * 
	 * @return the sum of attribute values
	 */
	public int getAttributeSummation()
	{
		int sum = 0;
		for (ClampedInt attribute : attributes.values()) sum += attribute.getValue();
		return sum;
	}

	/**
	 * Adds the attribute values from another attribute map to the current attribute map
	 * The values of same attributes are added
	 * 
	 * @param otherMapping the attribute map to add to the current attribute map
	 */
	public void addMappings(AttributeMapping otherMapping)
	{
		for (AttributeTypes attribute : AttributeTypes.values()) 
		{
			int thisValue = getAttributeValue(attribute);
			int otherValue = otherMapping.getAttributeValue(attribute);
			
			setAttribute(attribute, thisValue + otherValue);
		}
	}

	/**
	 * Subtracts the attribute values from another attribute map to the current attribute map
	 * The values of same attributes are subtracted
	 * 
	 * @param otherMapping the attribute map to subtract the current attribute map
	 */
	public void subtractMappings(AttributeMapping otherMapping)
	{
		for (AttributeTypes attribute : AttributeTypes.values()) 
		{
			int thisValue = getAttributeValue(attribute);
			int otherValue = otherMapping.getAttributeValue(attribute);
			
			setAttribute(attribute, thisValue - otherValue);
		}
	}
	

}


class ClampedInt
{
	private final int MIN;
	private final int MAX;
	private int value;
	
	
	/**
	 * create new object ClampedInt that adheres to given initial, minimum, and maximum possible values
	 * @param startValue the initial value for the ClampedInt
	 * @param min the minimum possible value for the ClampedInt
	 * @param max the maximum possible value for the ClampedInt
	 * @throws IllegalArgumentException if initial value is bigger than maximum or smaller than minimum possible values
	 */
	public ClampedInt(int startValue, int min, int max) throws IllegalArgumentException
	{
		if (startValue < min || startValue > max) throw new IllegalArgumentException("start value is outside of clampedInt bounds");
		if (min > max) throw new IllegalArgumentException("clampedInt bounds illegal (" + min + ">" + max + ") is not valid");
		MIN = min;
		MAX = max;
		value = startValue;
	}
	
	private void clamp()
	{
		value = value < MIN ? MIN : value;
		value = value > MAX ? MAX : value;
	}
	
	/**
	 * checks if current value of ClampedInt is the same as the maximum value
	 * @return if current value same as maximum value then return True, otherwise return False
	 */
	public boolean isMax()
	{
		return value == MAX;
	}
	
	/**
	 * checks if current value of ClampedInt is the same as the minimum value
	 * @return if current value same as minimum value then return True, otherwise return False
	 */
	public boolean isMin()
	{
		return value == MIN;
	}
	
	/**
	 * sets ClampedInt to a new value 
	 * @param newValue the new value set for ClampedInt
	 */
	public void setValue(int newValue)
	{
		value = newValue;
		clamp();
	}
	
	/**
	 * finds current value of ClampedInt
	 * @return returns the current value of ClampedInt
	 */
	public int getValue()
	{
		return value;
	}

}

