package com.model.events;

import java.util.Random;

import com.controller.Application;
import com.model.athletes.Athlete;

/**
 * 
 * This class handles a random event that causes an athlete's attribute value to change 
 * includes method to calculate the odds for an attribute change to occur
 *
 */
public class AthleteMutateEvent extends RandomEvent {

	/**
	 * description of the athlete mutate random event
	 */
	public AthleteMutateEvent()
	{
		description = "an athlete's stats have mutated -- view the changes in the clubhouse";
	}
	
	/**
	 * Will change an Athlete's attribute values
	 */
	@Override
	public void execute() {
	
		Athlete[] athletes = Application.app.getGameState().getPlayerTeam().getAthletes();

		if (athletes.length == 0)
		{
			description = "";
			return;
		}
		
		Random random = new Random();
		Athlete selection = athletes[random.nextInt(athletes.length)];
		
		description = selection.getName() + " stats have mutated -- view changes in clubhouse";
		selection.mutateAttributes(0, 20, false);
	}
	
	/**
	 * calculates the chance that an attribute change will occur
	 */
	@Override
	public void calculateOdds() {
		odds = 3;
		
	}

}
