package com.model.items.ingameitems;

import com.controller.enums.AttributeTypes;
import com.model.items.GameItem;
import com.model.attributes.AttributeMapping;

/**
 * 
 * This class represents an apple item in the game
 * This item changes an athlete's attribute value
 *
 */
public class OrangeItem extends GameItem {

	private static String iDescription = "The classic \nRaises Defence by 10 but lowers Interception by 5";
	private static String iName = "Orange";
	private static String iFile = "/resources/images/Orange.png";
	private static int iCost = 100;
	
	private static AttributeMapping iPositiveEffects = new AttributeMapping();
	private static AttributeMapping iNegativeEffects = new AttributeMapping();
	
	/**
	 * creates an orange item that changes an athletes attributes
	 */
	public OrangeItem()
	{
		super(iName, iDescription, iFile, iPositiveEffects, iNegativeEffects, iCost);
		
		iPositiveEffects.setAttribute(AttributeTypes.DEFENCE, 10);
		iNegativeEffects.setAttribute(AttributeTypes.INTERCEPTION, 5);
		
	}

}