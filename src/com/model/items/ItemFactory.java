package com.model.items;

import com.controller.enums.ItemTypes;
import com.model.items.ingameitems.AppleItem;
import com.model.items.ingameitems.BurgerItem;
import com.model.items.ingameitems.CoffeeItem;
import com.model.items.ingameitems.EnergyDrinkItem;
import com.model.items.ingameitems.FriesItem;
import com.model.items.ingameitems.JuiceItem;
import com.model.items.ingameitems.No1ArmourItem;
import com.model.items.ingameitems.No1StickItem;
import com.model.items.ingameitems.OrangeItem;
import com.model.items.ingameitems.PizzaItem;

/**
 * This class is responsible for creating different types of items
 * Provides a method that takes an enum case and returns a new associated item
 */
public class ItemFactory {
	

	/**
	 * creates a new item based on the desired case
	 * @param item the item to be created
	 * @return a new instance of an item
	 */
	public static GameItem createItem(ItemTypes item) {
        switch (item) {
        
            case JUICE:
            	return new JuiceItem();
            case APPLE:
            	return new AppleItem();
            case ORANGE:
            	return new OrangeItem();
            case COFFEE:
            	return new CoffeeItem();
            case ENERGYDRINK:
            	return new EnergyDrinkItem();
            case PIZZA:
            	return new PizzaItem();
            case FRIES:
            	return new FriesItem();
            case BURGER:
            	return new BurgerItem();
            case NO1STICK:
            	return new No1StickItem();
            case NO1ARMOUR:
            	return new No1ArmourItem();
            
            default:
                throw new IllegalArgumentException("Non implemented item type: " + item);
        }
    }

}
