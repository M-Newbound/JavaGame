package com.model.athletes;
import com.controller.enums.AttributeTypes;
import com.model.attributes.AttributeMapping;

import java.util.Random;

/**
 * This class represents an athlete
 * Athlete has name and attributes
 * methods include the ability to retrieve the name, specific attribute, cost,
 * and whether the athlete is injured
 */
public class Athlete {
	private final String name;
	private final AttributeMapping attributeComponent;
	
	/**
	 * creates an Athlete object with the derived name
	 * @param name the name of the Athlete
	 */
	public Athlete(String name)
	{
		this.name = name;
		this.attributeComponent = new AttributeMapping();
	}
	
	/**
	 * retrieves the AttributeComponent associated with the current Athlete
	 * @return the AttributeComponent of the current Athlete
	 */
	public AttributeMapping getAttributeComponent()
	{
		return attributeComponent;
	}
	
	/**
	 * Changes the attributes within a range
	 * @param lowerBound The lower bound of the mutation range
	 * @param upperBound The upper bound of the mutation range
	 * @param isNegative Determines whether the mutation is negative or positive overall
	 */
	public void mutateAttributes(int lowerBound, int upperBound, boolean isNegative)
	{
		Random random = new Random();
		for (AttributeTypes attribute : AttributeTypes.values())
		{
			int delta = random.nextInt(upperBound - lowerBound + 1) + lowerBound; 
			int current = attributeComponent.getAttributeValue(attribute);
			if (isNegative) attributeComponent.setAttribute(attribute, current - delta);
			else attributeComponent.setAttribute(attribute, current + delta);
		}
	}
	
	/**
	 * restores the stamina for an athlete
	 */
	public void restoreStamina()
	{
		attributeComponent.setStamina(AttributeMapping.ATTRIBUTE_MAXIMUM);
	}
	
	/**
	 * retrieves the name of the Athlete
	 * @return the name of the Athlete
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Determine if an athlete is injured or not by checking if their stamina is at it's minimum.
	 * @return	athletes injured state, if stamina is 0 then injured else then not
	 */
	public boolean isInjured()
	{
		return attributeComponent.isStaminaMin();
	}
	
	/**
	 * retrieves the cost for the Athlete from a modeled formula based on their attributes
	 * @return the cost of the Athlete
	 */
	public int getEstimatedCost()
	{
		return 2 * attributeComponent.getAttributeSummation() + attributeComponent.getStamina();
	}
	
	
}
