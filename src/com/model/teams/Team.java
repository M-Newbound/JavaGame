package com.model.teams;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.controller.Application;
import com.controller.enums.PositionTypes;
import com.model.athletes.Athlete;

/**
 * This class represents a team in the game. It contains active and reserve positions for athletes
 * It allows the assignment, unassignment, swapping of positions for athletes
 */
public class Team {
	
	private final String name;
	private final HashMap<PositionTypes, Athlete> activePositions;
	private final HashMap<PositionTypes, Athlete> reservePositions;

	/**
	 * Creates a Team object with given name
	 * @param name The name of the current Team
	 */
	public Team(String name)
	{
		activePositions = new HashMap<PositionTypes, Athlete>();
		reservePositions = new HashMap<PositionTypes, Athlete>();
		this.name = name;
	
		Set<PositionTypes> reserves = Set.of(PositionTypes.RESERVE_ONE, PositionTypes.RESERVE_TWO, PositionTypes.RESERVE_THREE);
		
		for (PositionTypes position : PositionTypes.values())
		{
		    if (reserves.contains(position)) {reservePositions.put(position, null);}
		    else {activePositions.put(position, null);}
		}
	}
	
	/**
	 * retrieves current name for the Team
	 * @return name of the Team
	 */
	public String getTeamName()
	{
		return name;
	}
	
	/**
	 * checks whether the Team can play a match
	 * @return False if not the Team cannot play a match, otherwise True
	 */
	public boolean canPlayMatch()
	{
		if (areActivePositionsFilled() == false) {return false;}
		for (Athlete athlete : activePositions.values()) {if (athlete.isInjured()) return false;}
		return true;
	}
	
	/**
	 * checks whether every active position is occupied by an Athlete
	 * @return True if all active positions are filled, otherwise False
	 */
	public boolean areActivePositionsFilled()
	{
		for (Athlete athlete : activePositions.values()) {if (athlete == null) return false;}
		return true;
	}
	
	/**
	 * checks whether every reserve position is occupied by an Athlete
	 * @return True if all reserve positions are filled, otherwise False
	 */
	public boolean areReservePositionsFilled()
	{
		for (Athlete athlete : reservePositions.values()) {if (athlete == null) return false;}
		return true;
	}
	
	/**
	 * checks whether all active positions and all reserve positions are occupied by an Athlete
	 * @return True if all active positions and all reserve positions are filled, otherwise False
	 */
	public boolean isFull()
	{
		return areActivePositionsFilled() && areReservePositionsFilled();
	}
	
	
	/**
	 * assigns an Athlete to a position in the Team
	 * @param position where the Athlete gets assigned
	 * @param athlete the individual Athlete to get assigned
	 */
	public void assignAthleteToPosition(PositionTypes position, Athlete athlete)
	{
		if (activePositions.containsKey(position)) activePositions.put(position, athlete);
		else if (reservePositions.containsKey(position)) reservePositions.put(position, athlete);
	}
	
	/**
	 * unassigns an Athlete from a position in the Team
	 * @param position where the Athlete gets unassigned
	 */
	public void unassignAthleteAtPosition(PositionTypes position)
	{
		assignAthleteToPosition(position, null);
	}
	
	/**
	 * swaps two positions with each other
	 * @param positionA first position to be swapped
	 * @param positionB	second position to be swapped
	 */
	public void swapPositionAssignments(PositionTypes positionA, PositionTypes positionB)
	{
		Athlete athleteA = findAthleteAtPosition(positionA);
		Athlete athleteB = findAthleteAtPosition(positionB);
		
		assignAthleteToPosition(positionA, athleteB);
		assignAthleteToPosition(positionB, athleteA);
	}

	
	/**
	 * finds the athlete at specific position if they are assigned to one
	 * @param position the position that the athlete is at
	 * @return the athlete at the position, if no athlete at position then returns nothing
	 */
	public Athlete findAthleteAtPosition(PositionTypes position)
	{
		if (activePositions.containsKey(position)) return activePositions.get(position);
		if (reservePositions.containsKey(position)) return reservePositions.get(position);
		return null;
	}
	
	/**
	 * retrieves the currently active athletes
	 * that is the athletes that are in a playing position
	 * @return the currently active athletes
	 */
	public Athlete[] getActiveAthletes()
	{
		ArrayList<Athlete> athletes = new ArrayList<>();
		for (Athlete athlete : activePositions.values())
		{
			if (athlete == null) continue;
			athletes.add(athlete);
		}
		
		return athletes.toArray(new Athlete[athletes.size()]);
	}
	
	/**
	 * retrieves the current reserve athletes
	 * @return the current reserve athletes
	 */
	public Athlete[] getReserveAthletes()
	{
		ArrayList<Athlete> athletes = new ArrayList<>();
		for (Athlete athlete : reservePositions.values())
		{
			if (athlete == null) continue;
			athletes.add(athlete);
		}
		
		return athletes.toArray(new Athlete[athletes.size()]);
	}
	
	/**
	 * retrieves all athletes on player's team
	 * @return athletes on player's team
	 */
	public Athlete[] getAthletes()
	{
		ArrayList<Athlete> athletes = new ArrayList<>();
		for (Athlete athlete : reservePositions.values())
		{
			if (athlete == null) continue;
			athletes.add(athlete);
		}
		
		for (Athlete athlete : activePositions.values())
		{
			if (athlete == null) continue;
			athletes.add(athlete);
		}
		
		return athletes.toArray(new Athlete[athletes.size()]);
	}
}
