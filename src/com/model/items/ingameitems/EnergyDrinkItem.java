package com.model.items.ingameitems;

import com.controller.enums.AttributeTypes;
import com.model.items.GameItem;
import com.model.attributes.AttributeMapping;

/**
 * 
 * This class represents an energy drink item in the game
 * This item changes an athlete's attribute value
 *
 */
public class EnergyDrinkItem extends GameItem {

	private static String iDescription = "Pure energy! \nRaises Strength by 15 but lowers Defence by 10";
	private static String iName = "Energy Drink";
	private static String iFile = "/resources/images/Energy.png";
	private static int iCost = 140;
	
	private static AttributeMapping iPositiveEffects = new AttributeMapping();
	private static AttributeMapping iNegativeEffects = new AttributeMapping();
	
	/**
	 * creates an energy drink item that changes an athletes attributes
	 */
	public EnergyDrinkItem()
	{
		super(iName, iDescription, iFile, iPositiveEffects, iNegativeEffects, iCost);
		
		iPositiveEffects.setAttribute(AttributeTypes.STRENGTH, 15);
		iNegativeEffects.setAttribute(AttributeTypes.DEFENCE, 10);
		
	}

}