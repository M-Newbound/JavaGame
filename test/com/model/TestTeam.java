package com.model;
import com.controller.Application;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import com.model.teams.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.model.athletes.*;
import com.controller.enums.PositionTypes;

class TestTeam {
	private Team team;
	Application app = new Application();
	
	@BeforeEach
	public void testMain() {
		team = new Team("Team1");
	}
	
	@Test
	public void testTeamName() {
		assertEquals("Team1", team.getTeamName());

	}
	
	@Test
	public void testPositions() {
	    assertFalse(team.areReservePositionsFilled());
	    assertFalse(team.areActivePositionsFilled());
	}
	
	@Test
	public void testPlay() {
		assertFalse(team.canPlayMatch()); //tests with an empty team
		
	    for (PositionTypes position : PositionTypes.values()) {
	    	team.assignAthleteToPosition(position, new Athlete("Athlete " + position));
	    }
	    assertTrue(team.canPlayMatch()); //tests with a full team
	}
	
	@Test
	public void testFull() {
		assertFalse(team.isFull()); //tests with empty team
		
		for (PositionTypes position : PositionTypes.values()) {
			team.assignAthleteToPosition(position, new Athlete("Athlete " + position));
		}
		 assertTrue(team.isFull()); //tests with full team
	}
	
	@Test
	public void testAssignment() {
		Athlete athlete = new Athlete("Joe Schmo");
		
		
		 team.assignAthleteToPosition(PositionTypes.CENTER, athlete);
		 assertNotEquals(athlete, team.findAthleteAtPosition(PositionTypes.LEFT_WING));
		 assertEquals(athlete, team.findAthleteAtPosition(PositionTypes.CENTER));
		 
		 team.unassignAthleteAtPosition(PositionTypes.CENTER);
		 assertNull(team.findAthleteAtPosition(PositionTypes.CENTER));
	}
	
	@Test
	public void testSwap() {
	    Athlete athleteA = new Athlete("Athlete1");
	    Athlete athleteB = new Athlete("Athlete1");
	    
	    team.assignAthleteToPosition(PositionTypes.CENTER, athleteA);
	    team.assignAthleteToPosition(PositionTypes.LEFT_DEFENCE, athleteB);
	    team.swapPositionAssignments(PositionTypes.CENTER, PositionTypes.LEFT_DEFENCE);
	    assertEquals(athleteA, team.findAthleteAtPosition(PositionTypes.LEFT_DEFENCE));
	    assertEquals(athleteB, team.findAthleteAtPosition(PositionTypes.CENTER));
	}
	
    @Test
    public void testFindAthleteAtPosition() {
    	assertNull(team.findAthleteAtPosition(PositionTypes.CENTER));
        Athlete athlete = new Athlete("Athlete");
        team.assignAthleteToPosition(PositionTypes.CENTER, athlete);
        assertEquals(athlete, team.findAthleteAtPosition(PositionTypes.CENTER));
    }
    
    @Test
    public void testGetActiveAthletes() {
        Athlete[] activeAthletes = team.getActiveAthletes();
        assertEquals(0, activeAthletes.length);
        
        Athlete athlete1 = new Athlete("jeff");
        Athlete athlete2 = new Athlete("joe");
        team.assignAthleteToPosition(PositionTypes.CENTER, athlete1);
        team.assignAthleteToPosition(PositionTypes.LEFT_DEFENCE, athlete2);
        
        activeAthletes = team.getActiveAthletes();
        assertEquals(2, activeAthletes.length);
        assertArrayEquals(new Athlete[]{athlete1, athlete2}, activeAthletes);
    }
    
    @Test
    public void testGetReserveAthletes() {
        Athlete[] reserveAthletes = team.getReserveAthletes();
        assertEquals(0, reserveAthletes.length);

        Athlete athlete1 = new Athlete("jeff");
        Athlete athlete2 = new Athlete("joe");
        
        team.assignAthleteToPosition(PositionTypes.RESERVE_ONE, athlete1);

        reserveAthletes = team.getReserveAthletes();
        assertEquals(1, reserveAthletes.length);
        assertArrayEquals(new Athlete[]{athlete1}, reserveAthletes);
    }
    
    @Test
    public void testGetAthletes() {
        Athlete[] allAthletes = team.getAthletes();
        assertEquals(0, allAthletes.length);

        Athlete athlete1 = new Athlete("jeff");
        Athlete athlete2 = new Athlete("joe");
        team.assignAthleteToPosition(PositionTypes.CENTER, athlete1);
        team.assignAthleteToPosition(PositionTypes.RESERVE_ONE, athlete2);

        allAthletes = team.getAthletes();
        assertEquals(2, allAthletes.length);
        assertArrayEquals(new Athlete[]{athlete2, athlete1}, allAthletes);
    }


}
