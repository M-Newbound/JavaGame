package com.model.items.ingameitems;

import com.controller.enums.AttributeTypes;
import com.model.items.GameItem;
import com.model.attributes.AttributeMapping;

/**
 * 
 * This class represents an armour item in the game
 * This item changes an athlete's attribute value
 *
 */
public class No1ArmourItem extends GameItem {

	private static String iDescription = "This is definitely cheating \nRaises Defence by 5";
	private static String iName = "No1 Armour";
	private static String iFile = "/resources/images/Armour.png";
	private static int iCost = 90;
	
	private static AttributeMapping iPositiveEffects = new AttributeMapping();
	private static AttributeMapping iNegativeEffects = new AttributeMapping();
	
	/**
	 * creates an armour item that changes an athletes attributes
	 */
	public No1ArmourItem()
	{
		super(iName, iDescription, iFile, iPositiveEffects, iNegativeEffects, iCost);
		
		iPositiveEffects.setAttribute(AttributeTypes.DEFENCE, 5);

		
	}

}