package com.model.items.ingameitems;

import com.controller.enums.AttributeTypes;
import com.model.items.GameItem;
import com.model.attributes.AttributeMapping;

/**
 * 
 * This class represents a stick item in the game
 * This item changes an athlete's attribute value
 *
 */
public class No1StickItem extends GameItem {

	private static String iDescription = "Is this cheating? \nRaises attack by 5";
	private static String iName = "No1 Stick";
	private static String iFile = "/resources/images/Stick.png";
	private static int iCost = 90;
	
	private static AttributeMapping iPositiveEffects = new AttributeMapping();
	private static AttributeMapping iNegativeEffects = new AttributeMapping();
	
	/**
	 * creates a stick item that changes an athletes attributes
	 */
	public No1StickItem()
	{
		super(iName, iDescription, iFile, iPositiveEffects, iNegativeEffects, iCost);
		
		iPositiveEffects.setAttribute(AttributeTypes.ATTACK, 5);
		
	}

}