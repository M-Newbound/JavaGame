package com.controller.world;

import com.controller.world.GameMarket;
import com.model.athletes.Athlete;
import com.model.inventory.Inventory;
import com.model.items.GameItem;
import com.util.AthleteUtilities;
import com.util.ItemUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameMarket {

    private GameMarket gameMarket;

    @BeforeEach
    public void testGameMarketSet() {
        gameMarket = new GameMarket();
    }

    @Test
    public void testRefreshMarket() {

        gameMarket.refreshMarket();

        Inventory<GameItem> itemInventory = gameMarket.getItemInventory();
        Inventory<Athlete> athleteInventory = gameMarket.getAthleteInventory();

        assertTrue(itemInventory.isFull());
        assertTrue(athleteInventory.isFull());

        gameMarket.refreshMarket();
        assertTrue(itemInventory.isFull());
        assertTrue(athleteInventory.isFull());
    }

    @Test
    public void testGetItemInventory() {
        Inventory<GameItem> itemInventory = gameMarket.getItemInventory();
        assertNotNull(itemInventory);
    }

    @Test
    public void testGetAthleteInventory() {
        Inventory<Athlete> athleteInventory = gameMarket.getAthleteInventory();
        assertNotNull(athleteInventory);
    }

    @Test
    public void testGetItemsCount() {
        int itemsCount = GameMarket.getItemsCount();
        assertEquals(6, itemsCount);
    }

    @Test
    public void testGetAthletesCount() {
        int athletesCount = GameMarket.getAthletesCount();
        assertEquals(3, athletesCount);
    }
}
