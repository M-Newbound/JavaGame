package com.util;

import java.util.Random;

import com.controller.enums.ItemTypes;
import com.model.items.GameItem;
import com.model.items.ItemFactory;

/**
 * 
 * This class provides a method for getting a random item
 * 
 */
public class ItemUtilities {
	
	/**
	 * Generates a random item
	 * 
	 * @return A randomly generated item
	 */
	public static GameItem getRandomGameItem()
	{
		Random random = new Random();
		ItemTypes selection = ItemTypes.values()[random.nextInt(ItemTypes.values().length)];
		return ItemFactory.createItem(selection);
	}

}
