package com.controller.world;


import com.controller.Application;
import com.controller.ui.UserInterface;
import com.controller.world.GameWorld;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;



public class TestApplication {
    private Application app;

    @Before
    /**
     * Instantiates new application
     */
    public void testMain() {
        app = new Application();
    }

    @Test
    /**
     * launches application and tests whether game state and UI state exist
     */
    public void testLaunchApplication() {
        app.launch();
        Assert.assertEquals(app, Application.app);
        Assert.assertNull(app.getGameState());
        Assert.assertNotNull(app.getUserInterfaceState());
    }

    @Test
    /**
     * launches instance of game state
     * and tests whether game state is set
     */
    public void testGetSetGameState() {
        GameWorld gameState = new GameWorld(0, 0);
        app.setGameState(gameState);
        Assert.assertEquals(gameState, app.getGameState());
    }

    /**
     * launches UI state
     * tests whether UI state exists
     */
    @Test
    public void testGetUserInterfaceState() {
        UserInterface uiState = app.getUserInterfaceState();
        Assert.assertNull(uiState);

    }

}
