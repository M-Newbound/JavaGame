package com.model.items;
import com.controller.enums.AttributeTypes;
import com.model.attributes.AttributeMapping;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameItem {

    private final int cost = 100;
    private final String validName = "Item";
    private final String invalidName = "Word Word Word Word Word Word Word Word Word Word Word Word Word Word Word Word  ";
    private final String validDescription = "an item.";
    private final String validFilePath = "/";
   
    
    private final AttributeMapping positiveEffects = new AttributeMapping();
    private final AttributeMapping negativeEffects = new AttributeMapping();

    @Test
    public void testValidGameItem() {
        GameItem gameItem = new GameItemTest(validName, validDescription, validFilePath, positiveEffects, negativeEffects, cost);
        assertNotNull(gameItem);
        assertEquals(validName, gameItem.getItemName());
        assertEquals(validDescription, gameItem.getItemDescription());
        assertEquals(validFilePath, gameItem.getFilePath());
        assertEquals(cost, gameItem.getiCost());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongNameThrowsException() {
        new GameItemTest(invalidName, validDescription, validFilePath, positiveEffects, negativeEffects, cost);
    }

    @Test
    public void testGetAttributeEffects() {
        AttributeMapping positiveEffects = new AttributeMapping();
        positiveEffects.setAttribute(AttributeTypes.ATTACK, 10);
        AttributeMapping negativeEffects = new AttributeMapping();
        negativeEffects.setAttribute(AttributeTypes.ATTACK, 5);
        GameItem gameItem = new GameItemTest(validName, validDescription, validFilePath, positiveEffects, negativeEffects, cost);

        int expectedHealthEffect = 5;
        int actualHealthEffect = gameItem.getAttrivuteEffects(AttributeTypes.ATTACK);
        assertEquals(expectedHealthEffect, actualHealthEffect);
    }

    private static class GameItemTest extends GameItem {
        public GameItemTest(String name, String description, String filePath, AttributeMapping positiveEffects, AttributeMapping negativeEffects, int cost) {
            super(name, description, filePath, positiveEffects, negativeEffects, cost);
        }
    }
}
