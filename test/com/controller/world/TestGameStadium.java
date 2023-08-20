package com.controller.world;


import com.model.teams.Team;
import com.controller.world.GameStadium;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGameStadium {
    private GameStadium stadium;

    @BeforeEach
    public void setUp() {
        stadium = new GameStadium(0.5f, 0.8f);
    }
    


    @Test
    public void testGetOptionsCount() {
        int count = 3;
        int count1 = stadium.getOptionsCount();
        assertEquals(count, count1);
    }

    @Test
    public void testGetTeamFromOption() {
        int index = 0;
        Team team = stadium.getTeamFromOption(index);
        assertNotNull(team);
    }

    @Test
    public void testRefreshAvailableMatches() {
        stadium.refreshAvailableMatches(3);
        for (int i = 0; i < stadium.getOptionsCount(); i++) {
            Team team = stadium.getTeamFromOption(i);
            assertNotNull(team);
        }
    }
    
    @Test
    public void testGetOpposition() {
        assertNull(stadium.getOpposition());
    }

    @Test
    public void testSetOpposition() {
        stadium.setOpposition(0);
        assertNotNull(stadium.getOpposition());
    }

}
