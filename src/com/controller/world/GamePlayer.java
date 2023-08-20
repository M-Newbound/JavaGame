package com.controller.world;

import com.controller.enums.ItemTypes;
import com.model.inventory.Inventory;
import com.model.items.GameItem;
import com.model.items.ItemFactory;
import com.model.teams.Team;


/**
 * This class represents the player of the game
 * Manages the player's team, inventory, and balance
 */

public class GamePlayer {
	private Team team;
	private Inventory<GameItem> inventory;
	private int balance;
	
	public static final int INVENTORY_SIZE = 30;
	
	/**
	 * instantiates a new inventory for the new player
	 * sets player's money balance to 0
	 */
	public GamePlayer()
	{
		balance = 0;
		inventory = new Inventory<GameItem>(GamePlayer.INVENTORY_SIZE);
		
	}
	
	/**
	 * Retrieves the player's team
	 * @return the player's team
	 */
	public Team getTeam()
	{
		return team;
	}
	
	/**
	 * retrieves the player's money balance
	 * @return the player's money balance
	 */
	public int getBalance()
	{
		return balance;
	}
	
	/**
	 * sets the player's money balance at a new value
	 * @param newBalance the value for the player's money balance to be sat at
	 */
	public void setBalance(int newBalance)
	{
		balance = newBalance;
	}
	
	/**
	 * creates new instance of player's team
	 * @param teamName the name that the player gives their team
	 */
	public void setupTeam(String teamName)
	{
		team = new Team(teamName);
	}
	
	/**
	 * retrieves the player's inventory of items
	 * @return the player's inventory of items
	 */
	public Inventory<GameItem> getInventory()
	{
		return inventory;
	}


}
