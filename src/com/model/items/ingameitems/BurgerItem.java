package com.model.items.ingameitems;

import com.controller.enums.AttributeTypes;
import com.model.items.GameItem;
import com.model.attributes.AttributeMapping;

/**
 * 
 * This class represents a burger item in the game
 * This item changes an athlete's attribute value
 *
 */
public class BurgerItem extends GameItem {

	private static String iDescription = "No pickles \nRaises Defence by 15 but lowers Attack by 10";
	private static String iName = "Burger";
	private static String iFile = "/resources/images/Burger.png";
	private static int iCost = 140;
	
	private static AttributeMapping iPositiveEffects = new AttributeMapping();
	private static AttributeMapping iNegativeEffects = new AttributeMapping();
	
	/**
	 * creates a burger item that changes an athletes attributes
	 */
	public BurgerItem()
	{
		super(iName, iDescription, iFile, iPositiveEffects, iNegativeEffects, iCost);
		
		iPositiveEffects.setAttribute(AttributeTypes.DEFENCE, 15);
		iNegativeEffects.setAttribute(AttributeTypes.ATTACK, 10);
		
	}

}