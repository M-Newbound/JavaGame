package com.model.attributes;

import com.model.attributes.AttributeMapping;
import com.controller.enums.AttributeTypes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAttributeMapping {

	
    private AttributeMapping attributeMap;

    @BeforeEach
    public void setUp() {
        attributeMap = new AttributeMapping();
    }
    
    @Test
    public void testSetAttribute() {
        attributeMap.setAttribute(AttributeTypes.ATTACK, 80);
        assertEquals(80, attributeMap.getAttributeValue(AttributeTypes.ATTACK));
    }
    
    @Test
    public void testGetAttributeValue() {
        int value = attributeMap.getAttributeValue(AttributeTypes.ATTACK);
        assertEquals(AttributeMapping.ATTRIBUTE_MINIMUM, value);
        
        attributeMap.setAttribute(AttributeTypes.ATTACK, 100);
        int value1 = attributeMap.getAttributeValue(AttributeTypes.ATTACK);
        assertEquals(AttributeMapping.ATTRIBUTE_MAXIMUM, value1);
    }
    
    @Test
    public void testSetStamina() {
        attributeMap.setStamina(80);
        assertEquals(80, attributeMap.getStamina());
    }
    
    @Test
    public void testIsAttributeAtMax() {
        boolean max = attributeMap.isAttributeAtMax(AttributeTypes.ATTACK);
        assertFalse(max);
        
        
        attributeMap.setAttribute(AttributeTypes.ATTACK, 100);
        boolean max1 = attributeMap.isAttributeAtMax(AttributeTypes.ATTACK);
        assertTrue(max1);
    }
    
    @Test
    public void testIsAttributeAtMin() {
    	boolean min = attributeMap.isAttributeAtMin(AttributeTypes.ATTACK);
        assertTrue(min);
    	
    	
    	attributeMap.setAttribute(AttributeTypes.ATTACK, 0);
        boolean min1 = attributeMap.isAttributeAtMin(AttributeTypes.ATTACK);
        assertTrue(min1);
    }
    
    @Test
    public void testIsStaminaMax() {
        boolean max = attributeMap.isStaminaMax();
        assertTrue(max);
    }
    
    @Test
    public void testIsStaminaMin() {
        boolean min = attributeMap.isStaminaMin();
        assertFalse(min);
        
        
        attributeMap.setStamina(0);
        boolean min1 = attributeMap.isStaminaMin();
        assertTrue(min1);
    }
    
    @Test
    public void testGetStamina() {
        int stamina = attributeMap.getStamina();
        assertEquals(AttributeMapping.ATTRIBUTE_MAXIMUM, stamina);
    }
    
    @Test
    public void testGetAttributeSummation() {
        int sum = attributeMap.getAttributeSummation();
        assertEquals(0, sum);
        
        attributeMap.setAttribute(AttributeTypes.ATTACK, 80);
        int sum1 = attributeMap.getAttributeSummation();
        assertEquals(80, sum1);
        
        attributeMap.setAttribute(AttributeTypes.STRENGTH, 50);
        int sum2 = attributeMap.getAttributeSummation();
        assertEquals(130, sum2);
    }
  
    @Test
    void addMappings() {
        AttributeMapping newMap = new AttributeMapping();
        newMap.setAttribute(AttributeTypes.ATTACK, 10);
        newMap.setAttribute(AttributeTypes.DEFENCE, 9);

        attributeMap.setAttribute(AttributeTypes.ATTACK, 10);
        attributeMap.setAttribute(AttributeTypes.DEFENCE, 10);

        attributeMap.addMappings(newMap);

        assertEquals(20, attributeMap.getAttributeValue(AttributeTypes.ATTACK));
        assertEquals(19, attributeMap.getAttributeValue(AttributeTypes.DEFENCE));
    }
    
    @Test
    void subtractMappings() {
        AttributeMapping newMap = new AttributeMapping();
        newMap.setAttribute(AttributeTypes.ATTACK, 10);
        newMap.setAttribute(AttributeTypes.DEFENCE, 9);

        attributeMap.setAttribute(AttributeTypes.ATTACK, 10);
        attributeMap.setAttribute(AttributeTypes.DEFENCE, 10);

        attributeMap.subtractMappings(newMap);

        assertEquals(0, attributeMap.getAttributeValue(AttributeTypes.ATTACK));
        assertEquals(1, attributeMap.getAttributeValue(AttributeTypes.DEFENCE));
    }
    

}
    

