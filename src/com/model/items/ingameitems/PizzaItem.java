package com.model.items.ingameitems;

import com.controller.enums.AttributeTypes;
import com.model.items.GameItem;
import com.model.attributes.AttributeMapping;

/**
 * 
 * This class represents a pizza item in the game
 * This item changes an athlete's attribute value
 *
 */
public class PizzaItem extends GameItem {

	private static String iDescription = "This doesn't look healthy... \nRaises Interception by 15 but lowers Strength by 10";
	private static String iName = "Pizza";
	private static String iFile = "/resources/images/Pizza.png";
	private static int iCost = 150;
	
	private static AttributeMapping iPositiveEffects = new AttributeMapping();
	private static AttributeMapping iNegativeEffects = new AttributeMapping();
	
	/**
	 * creates a pizza item that changes an athletes attributes
	 */
	public PizzaItem()
	{
		super(iName, iDescription, iFile, iPositiveEffects, iNegativeEffects, iCost);
		
		iPositiveEffects.setAttribute(AttributeTypes.INTERCEPTION, 15);
		iNegativeEffects.setAttribute(AttributeTypes.STRENGTH, 10);
		
	}

}