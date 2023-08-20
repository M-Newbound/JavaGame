package com.model.items.ingameitems;

import com.controller.enums.AttributeTypes;
import com.model.items.GameItem;
import com.model.attributes.AttributeMapping;

/**
 * 
 * This class represents a juice item in the game
 * This item changes an athlete's attribute value
 *
 */
public class JuiceItem extends GameItem {

	private static String iDescription = "Looks Tasty! \nRaises Strength by 10 but lowers Precision by 5";
	private static String iName = "Juice";
	private static String iFile = "/resources/images/JuiceItemImage.png";
	private static int iCost = 100;
	
	private static AttributeMapping iPositiveEffects = new AttributeMapping();
	private static AttributeMapping iNegativeEffects = new AttributeMapping();
	
	/**
	 * creates a juice item that changes an athletes attributes
	 */
	public JuiceItem()
	{
		super(iName, iDescription, iFile, iPositiveEffects, iNegativeEffects, iCost);
		
		iPositiveEffects.setAttribute(AttributeTypes.STRENGTH, 10);
		iNegativeEffects.setAttribute(AttributeTypes.PRECISION, 5);
		
	}

}
