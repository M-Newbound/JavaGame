package com.util;

import java.util.HashMap;
import java.util.Set;
import java.util.Random;

import com.controller.enums.PositionTypes;
import com.controller.enums.AttributeTypes;
import com.model.athletes.Athlete;
import com.model.attributes.AttributeMapping;
import com.model.teams.Team;

/**
 * This class is for team operations
 */
public class TeamUtilities {
	
	/**
	 * creates a team with athletes from weight
	 * @param weight the weight for the team
	 * @return the created team
	 */
	public static Team generateTeam(float weight)
	{
		Team team = new Team("Generated Team");
		Random random = new Random();
		for (PositionTypes type : PositionTypes.values())
		{
			Athlete athlete = AthleteUtilities.generateAthlete(weight);
			team.assignAthleteToPosition(type, athlete);
			
			int max = AttributeMapping.ATTRIBUTE_MAXIMUM;
			int stamina = (int)(0.6f * max) + random.nextInt(max);
			athlete.getAttributeComponent().setStamina(stamina);
		}
		
		return team;
	}
	
	/**
	 * Retrieves the best athlete from the given team based on attribute values and so the cost
	 * 
	 * @param team The given team to search for the best athlete
	 * @return The best athlete in the team
	 */
	public static Athlete getBestAthlete(Team team)
	{
		Athlete best = null;
		for (PositionTypes position : PositionTypes.values())
		{
			Athlete athlete = team.findAthleteAtPosition(position);
			if (best == null) best = athlete;
			else if (athlete == null) continue;
			else if (athlete.getEstimatedCost() > best.getEstimatedCost()) best = athlete;
		}
		
		return best;
	}

	
	/**
	 * Retrieves the worst athlete from the given team based on attribute values and so the cost
	 * 
	 * @param team The given team to search for the worst athlete
	 * @return The worst athlete in the team
	 */
	public static Athlete getWorstAthlete(Team team)
	{
		Athlete worst = null;
		for (PositionTypes position : PositionTypes.values())
		{
			Athlete athlete = team.findAthleteAtPosition(position);
			if (worst == null) worst = athlete;
			else if (athlete == null) continue;
			else if (athlete.getEstimatedCost() < worst.getEstimatedCost()) worst = athlete;
		}
		
		return worst;
	}
	
	/**
	 * Plays a match between two teams
	 * Compares athletes on the team and whichever respective athlete has higher attributes will win
	 * Uses stamina for athletes
	 * 
	 * @param teamA The first team.
	 * @param teamB The second team.
	 */
	public static void playTeamMatch(Team teamA, Team teamB)
	{
		Set<PositionTypes> reserves = Set.of(PositionTypes.RESERVE_ONE, PositionTypes.RESERVE_TWO, PositionTypes.RESERVE_THREE);
		for (PositionTypes position : PositionTypes.values())
		{
			if (reserves.contains(position)) continue;
			
			Athlete athleteA = teamA.findAthleteAtPosition(position);
			Athlete athleteB = teamB.findAthleteAtPosition(position);
			
			float scoreA = TeamUtilities.getAthleteScoreBasedOnPosition(position, athleteA);
			float scoreB = TeamUtilities.getAthleteScoreBasedOnPosition(position, athleteB);
			
			int range = AttributeMapping.ATTRIBUTE_MAXIMUM - AttributeMapping.ATTRIBUTE_MINIMUM;
			float staminaA = ((float) athleteA.getAttributeComponent().getStamina()) / range;
			float staminaB = ((float) athleteB.getAttributeComponent().getStamina()) / range;
			
			float finalScoreA = scoreA * staminaA;
			float finalScoreB = scoreB * staminaB;
			
			
			
			if (finalScoreA < finalScoreB)
			{
				finalScoreB -= finalScoreA;
				finalScoreA = 0;
			}
			else
			{
				finalScoreA -= finalScoreB;
				finalScoreB = 0;
			}
			
			athleteA.getAttributeComponent().setStamina((int) (finalScoreA * range));
			athleteB.getAttributeComponent().setStamina((int) (finalScoreB * range));
		}
		
	}
	
	/**
	 * Retrieves the attribute weights for a specific position
	 * 
	 * @param position The position that will retrieve attribute weights
	 * @return A map of attribute weights for a specific position
	 */
	public static HashMap<AttributeTypes, Integer> getAttributeWeightsForPosition(PositionTypes position)
	{
		HashMap<AttributeTypes, Integer> weights;
		
		switch (position)
		{
		case CENTER:
			weights = new HashMap<>()
			{
				
				{
					put(AttributeTypes.PRECISION, 50);
					put(AttributeTypes.INTERCEPTION, 50);
					put(AttributeTypes.STRENGTH, 50);
					put(AttributeTypes.ATTACK, 50);
					put(AttributeTypes.DEFENCE, 50);
				}
			};
			break;
			
		case GOAL_KEPPER:
			weights = new HashMap<>()
			{
				
				{
					put(AttributeTypes.PRECISION, 50);
					put(AttributeTypes.INTERCEPTION, 50);
					put(AttributeTypes.STRENGTH, 50);
					put(AttributeTypes.ATTACK, 10);
					put(AttributeTypes.DEFENCE, 90);
				}
			};
			break;
		case LEFT_DEFENCE:
			weights = new HashMap<>()
			{
				
				{
					put(AttributeTypes.PRECISION, 30);
					put(AttributeTypes.INTERCEPTION, 70);
					put(AttributeTypes.STRENGTH, 40);
					put(AttributeTypes.ATTACK, 30);
					put(AttributeTypes.DEFENCE, 80);
				}
			};
			break;
		case LEFT_WING:
			weights = new HashMap<>()
			{
				
				{
					put(AttributeTypes.PRECISION, 70);
					put(AttributeTypes.INTERCEPTION, 30);
					put(AttributeTypes.STRENGTH, 40);
					put(AttributeTypes.ATTACK, 80);
					put(AttributeTypes.DEFENCE, 30);
				}
			};
			break;
			
		case RIGHT_DEFENCE:
			weights = new HashMap<>()
			{
				
				{
					put(AttributeTypes.PRECISION, 30);
					put(AttributeTypes.INTERCEPTION, 70);
					put(AttributeTypes.STRENGTH, 40);
					put(AttributeTypes.ATTACK, 30);
					put(AttributeTypes.DEFENCE, 80);
				}
			};
			break;
		case RIGHT_WING:
			weights = new HashMap<>()
			{
				
				{
					put(AttributeTypes.PRECISION, 70);
					put(AttributeTypes.INTERCEPTION, 30);
					put(AttributeTypes.STRENGTH, 40);
					put(AttributeTypes.ATTACK, 80);
					put(AttributeTypes.DEFENCE, 30);
				}
			};
			break;
		
		default:
			weights = new HashMap<>()
			{
				
				{
					put(AttributeTypes.PRECISION, 0);
					put(AttributeTypes.INTERCEPTION, 0);
					put(AttributeTypes.STRENGTH, 0);
					put(AttributeTypes.ATTACK, 0);
					put(AttributeTypes.DEFENCE, 0);
				}
			};
			break;
		
		}
		
		return weights;
	}
	
	/**
	 * Calculates an athlete's score based on how the position effects their attributes
	 * 
	 * @param position The position of the specific athlete
	 * @param athlete The athlete that's score will be found
	 * @return The athlete's score based on how the position effects their attributes
	 */
	public static float getAthleteScoreBasedOnPosition(PositionTypes position, Athlete athlete)
	{
		float score = 0;
		HashMap<AttributeTypes, Integer> weights = getAttributeWeightsForPosition(position);

		
		if (athlete == null) return 0;
		
		int weightSum = 0;
		for (AttributeTypes type : AttributeTypes.values())
		{
			int range = AttributeMapping.ATTRIBUTE_MAXIMUM - AttributeMapping.ATTRIBUTE_MINIMUM;
			int weight = weights.get(type);
			weightSum += weight;
			
			float basePercent = ((float) athlete.getAttributeComponent().getAttributeValue(type)) / range;
			score += basePercent * weight;
		}
		
		return score / weightSum;
		
	}
}