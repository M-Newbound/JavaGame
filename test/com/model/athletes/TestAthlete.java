package com.model.athletes;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import com.controller.enums.AttributeTypes;
import com.model.athletes.Athlete;
import com.model.attributes.AttributeMapping;



class TestAthlete {
	Athlete athlete = new Athlete("Joe Schmo");
	
	@Test
	/**
	 * Tests that the new Athlete name set is equal to the Athlete name stored
	 */
	public void AthleteNameTest() {
		
		assertEquals("Joe Schmo", athlete.getName());
		assertNotEquals("Wrong Name", athlete.getName());
	
	}
	
	
	@Test
	/**
	 * Tests that Athlete has attributes
	 */
	public void AthleteAttributeTest() {
		assertNotNull(athlete.getAttributeComponent());
		
	}
	
    @Test
    public void testMutateAttributes() {
   
        athlete.mutateAttributes(1, 2, false);
        for (AttributeTypes attribute : AttributeTypes.values()) {
            int currentValue = athlete.getAttributeComponent().getAttributeValue(attribute);
            assertTrue(currentValue > 0);
        
        }
    }
    
	@Test
	/**
	 * tests that new athlete is not injured
	 * then tests if athlete with 0 stamina is injured
	 */
	public void AthleteStaminaTest() {
		
		assertFalse(athlete.isInjured()); //uninjured athlete
		
		athlete.getAttributeComponent().setStamina(0);
		
		assertTrue(athlete.isInjured()); //athlete should be injured at 0 Stamina
	}
	
    @Test
    public void TestGetEstimatedCost() {
        AttributeMapping attributeComponent = athlete.getAttributeComponent();
        attributeComponent.setAttribute(AttributeTypes.ATTACK, 100);
        attributeComponent.setAttribute(AttributeTypes.DEFENCE, 100);
        attributeComponent.setAttribute(AttributeTypes.INTERCEPTION, 100);
        attributeComponent.setAttribute(AttributeTypes.PRECISION, 100);
        attributeComponent.setAttribute(AttributeTypes.STRENGTH, 100);
        attributeComponent.setStamina(100);
        

        int estimatedCost = athlete.getEstimatedCost();
        assertEquals(1100, estimatedCost);
    }
    

    
    @Test
    void testRestoreStamina() {
        AttributeMapping attributeComponent = athlete.getAttributeComponent();
        attributeComponent.setStamina(AttributeMapping.ATTRIBUTE_MAXIMUM - 1);

        athlete.restoreStamina();
        assertEquals(AttributeMapping.ATTRIBUTE_MAXIMUM, attributeComponent.getStamina());
    }
}
