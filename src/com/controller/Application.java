package com.controller;

import com.controller.ui.UserInterface;
import com.controller.world.GameWorld;


/**
 * This class represents the starting place for the game
 * It is where you run the game from
 */
public class Application {
	public static Application app; // singleton
	private GameWorld gameWorld;
	private UserInterface ui;
	
	/**
	 * launches the application
	 */
	public void launch()
	{
		Application.app = this;
		gameWorld = null;
		ui = new UserInterface();
		
	}
	
	
	public static void main(String[] args)
	{	

		Application app = new Application();
		app.launch();
	}
	
	/**
	 * retrieves current game state 
	 * @return the current game state
	 */
	public GameWorld getGameState()
	{
		return gameWorld;
	}


	/**
	 * sets the game state as a new game state
	 * @param newState the new game state to be set
	 */
	public void setGameState(GameWorld newState)
	{
		gameWorld = newState;
	}
	
	/**
	 * retrieves the current user interface 
	 * @return the current user interface
	 */
	public UserInterface getUserInterfaceState()
	{
		return ui;
	}
	
	
}