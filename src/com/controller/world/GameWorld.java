package com.controller.world;

import com.model.inventory.Inventory;
import com.model.items.GameItem;
import com.model.teams.Team;

/**
 * This class represents the game world
 * creates and manages the game state
 * It includes the stadium, market, player profile, difficulty, season duration, and current week
 */
public class GameWorld {
	private int week;
	private int seasonDuration;
	private float difficulty;
	
	private GameStadium stadium;
	private GameMarket market;
	private GamePlayer player;
	
	/**
	 * Instantiates the game state
	 * initializes the stadium, market, and the player
	 * @param seasonDuration the length that the game will run for
	 * @param difficulty a player choice that effects variables such as money gain in the game
	 */
	public GameWorld(int seasonDuration, float difficulty)
	{
		week = 0;
		this.seasonDuration = seasonDuration;
		this.difficulty = difficulty;
		
		stadium = new GameStadium(difficulty, getCompletion());
		market = new GameMarket();
		player = new GamePlayer();
		

	}
	
	private float getCompletion()
	{
		return ((float) week + 1.0f) / seasonDuration; 
	}
	
	/**
	 * sets the difficulty of the game as what the player chooses
	 * @param difficulty the variable that is set by the player
	 */
	public void setDifficulty(float difficulty)
	{
		this.difficulty = difficulty;
	}
	
	/**
	 * retrieves the current difficulty of the game
	 * @return the difficulty of the game
	 */
	public float getDifficuilty()

	{
		return difficulty;
	}
	
	/**
	 * retrieves the current day that the game is at
	 * @return the day that the game is currently at
	 */
	public int getWeek()
	{
		return week;
	}
	
	/**
	 * retrieves the season duration or amount of days that the game will run for
	 * @return the season duration or amount of days that the game will run for
	 */
	public int getSeasonDuration()
	{
		return seasonDuration;
	}
	
	/**
	 * retrieves the current player profile
	 * @return the current player profile
	 */
	public GamePlayer getGamePlayer()
	{
		return player;
	}

	/**
	 * Retrieve the player's team
	 * @return the player's team
	 */
	public Team getPlayerTeam()
	{
		return player.getTeam();
	}
	
	/**
	 * Retrieves the player's inventory
	 * @return the player's inventory
	 */
	public Inventory<GameItem> getPlayerInventory()
	{
		return player.getInventory();
	}
	
	/**
	 * retrieves the market instance
	 * @return the market instance
	 */
	public GameMarket getMarket()
	{
		return market;
	}
	
	/**
	 * retrieves the market instance
	 * @return the market instance
	 */
	public GameStadium getStadium()
	{
		return stadium;
	}
	
	/**
	 * Loads the next week in the game
	 * Increases the week count by 1
	 * Refreshes matches in the stadium
	 * Refreshes items and athletes in the market
	 */
	public void loadNextWeek()
	{
		week++;
		stadium.refreshAvailableMatches(getCompletion());
		market.refreshMarket();
	}

}
