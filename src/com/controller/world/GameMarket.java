package com.controller.world;

import com.model.athletes.Athlete;
import com.model.inventory.Inventory;
import com.model.items.GameItem;
import com.util.AthleteUtilities;
import com.util.ItemUtilities;

/**
 * This class represents the market in the game 
 * Items and athletes can be sold and purchased
 * Manages the market inventories for items and athletes
 */
public class GameMarket {
	
	private Inventory<GameItem> items;
	private Inventory<Athlete> athletes;

	
	private static final int ITEMS_COUNT = 6;
	private static final int ATHLETES_COUNT = 3;

	/**
	 * creates new instance of a market by initializing inventories for items and athletes
	 */
	public GameMarket()
	{
		items = new Inventory<GameItem>(GameMarket.getItemsCount());
		athletes = new Inventory<Athlete>(GameMarket.getAthletesCount());
		refreshMarket();
		
	}
	

	
	/**
	 * refreshes the current market by clearing the athletes and items
	 * refills market with new instances of items
	 */
	public void refreshMarket()
	{
		items.clearItems();
		athletes.clearItems();
		
		for(int i=0; i<GameMarket.getItemsCount(); i++)
		{
			GameItem newItem = ItemUtilities.getRandomGameItem();
			items.setItemAtIndex(i, newItem);
		}
		
		for(int i=0; i<GameMarket.getAthletesCount(); i++)
		{
			// TODO: figure out weights
			Athlete newAthlete = AthleteUtilities.generateAthlete(0.5f);
			athletes.setItemAtIndex(i, newAthlete);
		}
	}


	/**
	 * retrieves an inventory of all possible items
	 * @return an inventory of items
	 */
	public Inventory<GameItem> getItemInventory()
	{
		return items;
	}
	
	/**
	 * retrieves current athletes
	 * @return current athletes
	 */
	public Inventory<Athlete> getAthleteInventory()
	{
		return athletes;
	}


	/**
	 * retrieves the amount of items in the market
	 * @return the amount of items in the market
	 */
	public static int getItemsCount() {
		return ITEMS_COUNT;
	}


	/**
	 * the number of athletes in the market
	 * @return the number of athletes in the market
	 */
	public static int getAthletesCount() {
		return ATHLETES_COUNT;
	}

}
