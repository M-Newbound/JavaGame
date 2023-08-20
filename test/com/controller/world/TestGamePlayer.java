package com.controller.world;

import com.model.teams.Team;
import com.controller.enums.ItemTypes;
import com.model.inventory.Inventory;
import com.model.items.GameItem;
import com.model.items.ItemFactory;
import com.controller.world.GamePlayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class TestGamePlayer {

	private Team team;
	private Inventory<GameItem> inventory;
	private int balance;
	GamePlayer gamePlayer = new GamePlayer();
	
	
	@Test
	public void testGetBalanceAndTeam() {
		assertEquals(0, gamePlayer.getBalance());
		assertEquals(team, gamePlayer.getTeam());
	}
	
	@Test
	public void testSetBalance() {
		assertEquals(0, gamePlayer.getBalance());
		int newBalance = 10;
		gamePlayer.setBalance(newBalance);
		assertEquals(10, gamePlayer.getBalance());
	}
	
	@Test
	public void testSetupTeam() {
        Team team = gamePlayer.getTeam();
        assertNull(team);

        gamePlayer.setupTeam("team1");
        team = gamePlayer.getTeam();
        assertNotNull(team);
        assertEquals("team1", team.getTeamName());
	}
	
    @Test
    public void testGetInventory() {
        assertNotNull(gamePlayer.getInventory());
        assertEquals(GamePlayer.INVENTORY_SIZE, gamePlayer.getInventory().getSize());
    }

}
