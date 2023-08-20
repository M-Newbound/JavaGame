package com.model.events;

import java.util.ArrayList;
import java.util.Random;

import com.controller.Application;
import com.controller.enums.PositionTypes;
import com.model.athletes.Athlete;

/**
 * 
 * This class handles a random event that causes an athlete to quit 
 * includes method to calculate the odds for an athlete to quit
 *
 */
public class AthleteQuitEvent extends RandomEvent {

	/**
	 * description of the athlete quit random event
	 */
	public AthleteQuitEvent()
	{
		description = "An athlete has quit your team -- view changes in the clubroom";
	}
	
	/**
	 * will make an athlete leave the team
	 */
	@Override
	public void execute() {
		
		ArrayList<PositionTypes> validPositions = new ArrayList<>();
		for (PositionTypes position : PositionTypes.values())
		{
			Athlete athlete = Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(position);
			if (athlete != null) validPositions.add(position);
		}

		if (validPositions.size() == 0)
		{
			description = "";
			return;
		}
		
		Random random = new Random();
		PositionTypes selection = validPositions.get(random.nextInt(validPositions.size()));
		
		Athlete athlete = Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(selection);
		
		description = athlete.getName() + " has quit your team!";
		Application.app.getGameState().getPlayerTeam().unassignAthleteAtPosition(selection);
		
		

	}

	/**
	 * calculates the chance that an athlete has to quit the team
	 */
	@Override
	public void calculateOdds() {
		
		int sum = 0;
		int count = 0;
		
		for (PositionTypes position : PositionTypes.values())
		{
			Athlete athlete = Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(position);
			if (athlete == null) continue;
			
			sum += athlete.getAttributeComponent().getStamina();
			count++;
		}
		
		odds = (int) (sum / count) + 10;

	}

}
