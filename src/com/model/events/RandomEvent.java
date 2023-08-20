package com.model.events;

import java.util.Random;

/**
 * 
 * This class handles random events
 *
 */
public abstract class RandomEvent {

	protected String description;
	protected int odds;
	
	
	public abstract void execute();
	
	public abstract void calculateOdds();
	
	/**
	 * retrieves a random number that effects whether the execution of the random event will occur
	 * @return true if random number is equal to 0, otherwise false
	 */
	public boolean shouldExecute()
	{
		calculateOdds();
		Random random = new Random();
		return random.nextInt(odds) == 0;
	}
	
	/**
	 * retrieves the description of the random event that will occur
	 * @return the description of the random event that will occur
	 */
	public String getDescription()
	{
		return description;
	}
	
}
