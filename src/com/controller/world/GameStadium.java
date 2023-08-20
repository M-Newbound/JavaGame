package com.controller.world;

import com.model.athletes.Athlete;
import com.model.teams.Team;
import com.util.TeamUtilities;
/**
 * This class represents a stadium in the game
 * The player can select another team to play a match against
 * Manages the available match options for the player.
 */
public class GameStadium {
	
	public static final int OPTIONS_NUMBER = 3;
	private Team[] options;
	private Integer[] rewards;
	
	private Team selectedOpposition;
	private int selectedOppositionReward;
	private float difficulty;
	
	/**
	 * creates new stadium instance
	 * refreshes matches to get first round of 
	 * @param difficulty the current game difficulty
	 * @param completion the current percentage of the season completed
	 */
	public GameStadium(float difficulty, float completion)
	{
		this.difficulty = difficulty;
		
		options = new Team[GameStadium.OPTIONS_NUMBER];
		rewards = new Integer[GameStadium.OPTIONS_NUMBER];
		selectedOpposition = null;
		refreshAvailableMatches(completion);
		
	}
	
	public int getReward()
	{
		return selectedOppositionReward;
	}
	
	/**
	 * retrieves the opposing team
	 * @return the opposing team
	 */
	public Team getOpposition()
	{
		return selectedOpposition;
	}
	
	/**
	 * sets a team as an opposing 
	 * @param index index to the current oppositions
	 */
	public void setOpposition(int index)
	{
		selectedOpposition = options[index];
		selectedOppositionReward = rewards[index];
	}
	
	/**
	 * retrieves the number of matches that you can play
	 * @return the number of matches
	 */
	public int getOptionsCount()
	{
		return GameStadium.OPTIONS_NUMBER;
	}
	
	/**
	 * retrieves the specific enemy team in a match option
	 * @param optionIndex the index of the specific team
	 * @return the enemy team
	 */
	public Team getTeamFromOption(int optionIndex)
	{
		return options[optionIndex];
	}
	
	public int getRewardOfOption(int optionIndex)
	{
		return rewards[optionIndex];
	}
	
	/**
	 * removes currently available matches
	 * creates new available matches
	 * @param completion the current percentage completion of the season
	 */
	public void refreshAvailableMatches(float completion)
	{
		
		for (int i=0; i<options.length; i++)
		{
			options[i] = TeamUtilities.generateTeam(-0.5f + difficulty * completion);
			
			int factor = 0;
			for (Athlete athlete : options[i].getAthletes())
			{
				factor += athlete.getAttributeComponent().getAttributeSummation();
			}
			
			rewards[i] = (int) ((1.0f + difficulty) * completion * factor);

		}
		
		selectedOpposition = null;
		selectedOppositionReward = 0;
	}

}
