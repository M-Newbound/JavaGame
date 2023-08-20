package com.model.items;

import com.controller.enums.ItemTypes;
import com.model.items.GameItem;
import com.model.items.ingameitems.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestItemFactory {
	
    @Test
    public void testCreateItem_Juice() {
        ItemTypes itemType = ItemTypes.JUICE;
        GameItem item = ItemFactory.createItem(itemType);
        assertTrue(item instanceof JuiceItem);
    }

    @Test
    public void testCreateItem_Apple() {
        ItemTypes itemType = ItemTypes.APPLE;
        GameItem item = ItemFactory.createItem(itemType);
        assertTrue(item instanceof AppleItem);
    }

    @Test
    public void testCreateItem_Orange() {
        ItemTypes itemType = ItemTypes.ORANGE;
        GameItem item = ItemFactory.createItem(itemType);
        assertTrue(item instanceof OrangeItem);
    }

    @Test
    public void testCreateItem_Coffee() {
        ItemTypes itemType = ItemTypes.COFFEE;
        GameItem item = ItemFactory.createItem(itemType);
        assertTrue(item instanceof CoffeeItem);
    }

    @Test
    public void testCreateItem_EnergyDrink() {
        ItemTypes itemType = ItemTypes.ENERGYDRINK;
        GameItem item = ItemFactory.createItem(itemType);
        assertTrue(item instanceof EnergyDrinkItem);
    }

    @Test
    public void testCreateItem_Pizza() {
        ItemTypes itemType = ItemTypes.PIZZA;
        GameItem item = ItemFactory.createItem(itemType);
        assertTrue(item instanceof PizzaItem);
    }

    @Test
    public void testCreateItem_Fries() {
        ItemTypes itemType = ItemTypes.FRIES;
        GameItem item = ItemFactory.createItem(itemType);
        assertTrue(item instanceof FriesItem);
    }

    @Test
    public void testCreateItem_Burger() {
        ItemTypes itemType = ItemTypes.BURGER;
        GameItem item = ItemFactory.createItem(itemType);
        assertTrue(item instanceof BurgerItem);
    }

    @Test
    public void testCreateItem_No1Stick() {
        ItemTypes itemType = ItemTypes.NO1STICK;
        GameItem item = ItemFactory.createItem(itemType);
        assertTrue(item instanceof No1StickItem);
    }

    @Test
    public void testCreateItem_No1Armour() {
        ItemTypes itemType = ItemTypes.NO1ARMOUR;
        GameItem item = ItemFactory.createItem(itemType);
        assertTrue(item instanceof No1ArmourItem);
    }

}
