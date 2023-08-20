package com.controller.world;
import com.controller.world.GameWorld;


import junit.framework.Assert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestGameWorld {
	private GameWorld workingGame = new GameWorld(10, 2); //variables conform to desired inputs

	
	
	@Test
	public void testGameStateDifficulty() {
		assertEquals(2, workingGame.getDifficuilty());
		workingGame.setDifficulty(1);
		assertNotEquals(2, workingGame.getDifficuilty());
		assertEquals(1, workingGame.getDifficuilty());
		
	}
	
	@Test
	public void testGameStateDuration() {
		assertEquals(10, workingGame.getSeasonDuration());
		assertEquals(0, workingGame.getWeek());
	}
	
    @Test
    public void testGetGamePlayer() {
        assertNotNull(workingGame.getGamePlayer());
    }
    
    @Test
    public void testGetPlayerTeam() {
        assertNull(workingGame.getPlayerTeam());
    }
    
    @Test
    public void testGetPlayerInventory() {
        assertNotNull(workingGame.getPlayerInventory());
    }

    @Test
    public void testGetMarket() {
        assertNotNull(workingGame.getMarket());
    }

    @Test
    public void testGetStadium() {
        assertNotNull(workingGame.getStadium());
    }

    @Test
    public void testLoadNextWeek() {
        int currentWeek = workingGame.getWeek();
        workingGame.loadNextWeek();
        currentWeek += 1;
        assertEquals(currentWeek, workingGame.getWeek());
    }

}
