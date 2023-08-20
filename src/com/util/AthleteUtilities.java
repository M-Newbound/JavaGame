package com.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.controller.enums.AttributeTypes;
import com.model.athletes.Athlete;
import com.model.attributes.AttributeMapping;

/**
 * This class provides methods for generating athletes and names of athletes
 */
public class AthleteUtilities {
	

	/**
	 * creates an Athlete from weights and master weight statistics
	 * 
	 * @param weights		athlete attributes are generated over a statistical weight bounded between 0-1.
	 * @param masterWeight	athlete attribute weights are impacted 
	 * 
	 * @return

	 */
	public static Athlete generateAthlete(EnumMap<AttributeTypes, TuplePair<Float, Float>> weights, float masterWeight)
	{
		String name = AthleteUtilities.generateFullName();
		Athlete athlete = new Athlete(name);
		
		Random randomiser = new Random();
		for (AttributeTypes type : AttributeTypes.values())
		{
			int max = AttributeMapping.ATTRIBUTE_MAXIMUM;
			int value = (int) (max * weights.get(type).getA() + max * masterWeight);
			int delta = (int) (max * weights.get(type).getB());
			
			int upper = value + delta > max ? max : value + delta;
			int lower = value - delta < 0 ? 0 : value - delta;
			
			
			int finalValue = upper==lower ? upper : randomiser.nextInt(upper - lower) + lower;
			athlete.getAttributeComponent().setAttribute(type, finalValue);
			
		}
		
		return athlete;	
	}
	
	/**
	 * 
	 * @param masterWeight
	 * @return
	 */
	public static Athlete generateAthlete(float masterWeight)
	{
		String name = AthleteUtilities.generateFullName();
		Athlete athlete = new Athlete(name);
		
		Random randomiser = new Random();
		for (AttributeTypes type : AttributeTypes.values())
		{
			int max = AttributeMapping.ATTRIBUTE_MAXIMUM;
			int value = (int) (max * 0.1 + max * masterWeight);
			int delta = (int) (max * 0.5);
			
			int upper = value + delta > max ? max : value + delta;
			int lower = value - delta < 0 ? 0 : value - delta;
			
			
			int finalValue = upper==lower ? upper : randomiser.nextInt(upper - lower) + lower;
			athlete.getAttributeComponent().setAttribute(type, finalValue);
			
		}
		
		return athlete;
	}
	
	/**
	 * generates a new athlete with defaulted attributes
	 * @return a new athlete
	 */
	public static Athlete generateAthlete()
	{
		return AthleteUtilities.generateAthlete(0);
	}
	
	/**
	 * generates a new name for an athlete including both the first and last name
	 * does this by calling on randomly generated first and last name functions
	 * @return a new full name for an athlete
	 */
	public static String generateFullName()
	{
		return AthleteUtilities.generateFirstName() + " " + AthleteUtilities.generateLastName();
	}
	
	/**
	 * randomly generates a new first name based on a list of first names
	 * @return a randomly generated first name
	 */
	public static String generateFirstName()
	{
        String filePath = "/resources/names/firstNames.txt";

        InputStream inputStream = AthleteUtilities.class.getResourceAsStream(filePath);

        if (inputStream == null)
        {
            System.out.println("File " + filePath + " not found.");
            return "FIRST_NAME_ERROR";
        }
        
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        scanner.close();

        // Select a random option
        Random random = new Random();
        int randomIndex = random.nextInt(lines.size());
        return lines.get(randomIndex);


	}
	
	/**
	 * randomly generates a new last name based on a list of last names
	 * @return a randomly generated last name
	 */
	public static String generateLastName()
	{
        String filePath = "/resources/names/lastNames.txt";

        InputStream inputStream = AthleteUtilities.class.getResourceAsStream(filePath);

        if (inputStream == null)
        {
            System.out.println("File " + filePath + " not found.");
            return "LAST_NAME_ERROR";
        }
        
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        scanner.close();

        // Select a random option
        Random random = new Random();
        int randomIndex = random.nextInt(lines.size());
        return lines.get(randomIndex);

		
	}
	
}
