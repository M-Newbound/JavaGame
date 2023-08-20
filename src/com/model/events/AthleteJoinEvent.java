package com.model.events;

import java.util.ArrayList;
import java.util.Random;

import com.controller.Application;

import com.controller.enums.PositionTypes;
import com.model.athletes.Athlete;
import com.util.AthleteUtilities;

/**
 * 
 * This class handles a random event that causes an athlete to join a team position if 
 * includes method to calculate the odds for an athlete to join
 *
 */
public class AthleteJoinEvent extends RandomEvent {

	
	/**
	 * description of the athlete join random event
	 */
	public AthleteJoinEvent()
	{
		description = "An athlete has join your team -- view them in the clubhouse";
	}
	
	/**
	 * Will assign an Athlete to a team if their is an open position
	 * otherwise the method will return with no changes
	 */
	@Override
	public void execute() {
		ArrayList<PositionTypes> validPositions = new ArrayList<>();
		for (PositionTypes position : PositionTypes.values())
		{
			Athlete athlete = Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(position);
			if (athlete == null) validPositions.add(position);
		}
		
		if (validPositions.size() == 0)
		{
			description = "";
			return;
		}
		
		Random random = new Random();
		PositionTypes selection = validPositions.get(random.nextInt(validPositions.size()));
		
		Athlete newAthlete = AthleteUtilities.generateAthlete(random.nextFloat(0.3f));
		Application.app.getGameState().getPlayerTeam().assignAthleteToPosition(selection, newAthlete);
		
		description = newAthlete.getName() + " has jointed your team!!";

	}

	/**
	 * calculates the chance that an athlete has to join the team 
	 */
	@Override
	public void calculateOdds() {
		int emptyCount = 0;
		
		for (PositionTypes position : PositionTypes.values())
		{
			Athlete athlete = Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(position);
			if (athlete == null) emptyCount++;
		}
		odds = 5 + (PositionTypes.values().length + 1 - emptyCount);
	}

}
