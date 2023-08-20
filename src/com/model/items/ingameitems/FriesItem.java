package com.model.items.ingameitems;

import com.controller.enums.AttributeTypes;
import com.model.items.GameItem;
import com.model.attributes.AttributeMapping;

/**
 * 
 * This class represents a fries item in the game
 * This item changes an athlete's attribute value
 *
 */
public class FriesItem extends GameItem {

	private static String iDescription = "F for Fries \nRaises Attack by 15 but lowers Defence by 10";
	private static String iName = "Fries";
	private static String iFile = "/resources/images/Fries.png";
	private static int iCost = 140;
	
	private static AttributeMapping iPositiveEffects = new AttributeMapping();
	private static AttributeMapping iNegativeEffects = new AttributeMapping();
	
	/**
	 * creates a fries item that changes an athletes attributes
	 */
	public FriesItem()
	{
		super(iName, iDescription, iFile, iPositiveEffects, iNegativeEffects, iCost);
		
		iPositiveEffects.setAttribute(AttributeTypes.ATTACK, 15);
		iNegativeEffects.setAttribute(AttributeTypes.DEFENCE, 10);
		
	}

}