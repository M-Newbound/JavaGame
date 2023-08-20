package com.util;

import java.util.ArrayList;

import com.model.events.AthleteJoinEvent;
import com.model.events.AthleteMutateEvent;
import com.model.events.AthleteQuitEvent;
import com.model.events.RandomEvent;

/**
 * 
 * This class provides methods for random events
 * These include athletes joining, quitting, and changing attribute values
 * 
 */
public class RandomEventUtilities {
	
	/**
	 * Applies random events to the game.
	 * Executes random events such as athlete joining, quitting, and mutation.
	 * 
	 * @return An ArrayList of random events that happened
	 */
	public static ArrayList<String> applyRandomEvents()
	{
		ArrayList<String> descriptions = new ArrayList<>();
		
		RandomEvent athleteJoin = new AthleteJoinEvent();
		RandomEvent athleteQuit = new AthleteQuitEvent();
		RandomEvent athleteMutate = new AthleteMutateEvent();
		
		RandomEvent[] events = new RandomEvent[]{athleteJoin, athleteQuit, athleteMutate};
		for (RandomEvent event : events)
		{
			if (event.shouldExecute() == false) continue;
			
			event.execute();
			descriptions.add(event.getDescription());
			
		}
		
		return descriptions;
	}

}
