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
public class AppleItem extends GameItem {

	private static String iDescription = "It does something right? \nRaises Precision by 10 but lowers Defence by 5";
	private static String iName = "Apple";
	private static String iFile = "/resources/images/Apple.png";
	private static int iCost = 100;
	
	private static AttributeMapping iPositiveEffects = new AttributeMapping();
	private static AttributeMapping iNegativeEffects = new AttributeMapping();
	
	/**
	 * Creates an `AppleItem` object.
	 * The apple item changes an athlete's attributes by raising Precision by 10 and lowering Defence by 5.
	 */
	public AppleItem()
	{
		super(iName, iDescription, iFile, iPositiveEffects, iNegativeEffects, iCost);
		
		iPositiveEffects.setAttribute(AttributeTypes.PRECISION, 10);
		iNegativeEffects.setAttribute(AttributeTypes.DEFENCE, 5);
	}
}


