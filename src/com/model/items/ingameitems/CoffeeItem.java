package com.model.items.ingameitems;

import com.controller.enums.AttributeTypes;
import com.model.items.GameItem;
import com.model.attributes.AttributeMapping;

/**
 * 
 * This class represents a coffee item in the game
 * This item changes an athlete's attribute value
 *
 */
public class CoffeeItem extends GameItem {

	private static String iDescription = "You wont stop moving! \nRaises Attack by 15 but lowers Precision by 10";
	private static String iName = "Coffee";
	private static String iFile = "/resources/images/Coffee.png";
	private static int iCost = 140;
	
	private static AttributeMapping iPositiveEffects = new AttributeMapping();
	private static AttributeMapping iNegativeEffects = new AttributeMapping();
	
	/**
	 * creates a coffee item that changes an athletes attributes
	 */
	public CoffeeItem()
	{
		super(iName, iDescription, iFile, iPositiveEffects, iNegativeEffects, iCost);
		
		iPositiveEffects.setAttribute(AttributeTypes.ATTACK, 15);
		iNegativeEffects.setAttribute(AttributeTypes.PRECISION, 10);
		
	}

}