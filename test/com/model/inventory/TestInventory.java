package com.model.inventory;


import com.model.inventory.Inventory;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class TestInventory {
	
	private int size = 10;
	private Inventory<String> inv = new Inventory<>(size); //Supposedly valid size value
	
	@Test
	public void testValidInventorySize() {
		assertEquals(10, inv.getSize());
		
		int size = 10;
		Inventory<String> inv = new Inventory<>(size);
		assertEquals(10, inv.getSize());
	}
	@Test
	public void testInvalidInventorySize() {
        int size = -5;
        assertThrows(IllegalArgumentException.class, () -> new Inventory<>(size));
	}
	
	@Test
	public void testItemAtIndex() {
        int size = 10;
        Inventory<Integer> inv = new Inventory<>(size);
        int index = 1;
        Integer item = 10;
        inv.setItemAtIndex(index, item);

        assertEquals(item, inv.getItemAtIndex(index));
	}
	
	
	@Test
	public void testGetItemAtIndex() {
        int size = 10;
        Inventory<Integer> inv = new Inventory<>(size);
        int index = 1;
        Integer item = 10;
        inv.setItemAtIndex(index, item);
        
        assertEquals(10, inv.getItemAtIndex(1));
        assertNotEquals(9, inv.getItemAtIndex(2));
        
	}
	
	@Test
	public void testIsIndexValid() {
        int size = 10;
        Inventory<Integer> inv = new Inventory<>(size);
 
        
        assertTrue(inv.isIndexValid(1));
        assertFalse(inv.isIndexValid(11));
        assertFalse(inv.isIndexValid(-1));
	}
	
    @Test
    void testIsIndexOccupied() {
        assertFalse(inv.isIndexOccupied(2));

        inv.setItemAtIndex(2, "Item");
        assertTrue(inv.isIndexOccupied(2));
    }
    
	@Test
	public void testClearItems() {
        int size = 10;
        Inventory<Integer> inv = new Inventory<>(size);
        int index = 1;
        Integer item = 10;
        inv.setItemAtIndex(index, item);
        
        assertEquals(10, inv.getItemAtIndex(1));
        inv.clearItems();
        assertEquals(null, inv.getItemAtIndex(1));
        
	}
	
	@Test
	public void testInventoryFull() {
        int size = 1;
        Inventory<Integer> inv = new Inventory<>(size);
        assertEquals(1, inv.getSize());
        
        assertFalse(inv.isFull());
        
        int index = 0;
        Integer item = 1;
        inv.setItemAtIndex(index, item);
        
        assertTrue(inv.isFull());
	}
	
	@Test
	public void testNextItemlessIndex() {
        int size = 3;
        Inventory<Integer> inv = new Inventory<>(size);
        int index = 0;
        Integer item = 1;
        inv.setItemAtIndex(index, item);
        
        assertEquals(1, inv.getNextItemlessIndex());
        
        int index2 = 1;
        Integer item2 = 2;
        inv.setItemAtIndex(index2, item2);
        assertEquals(2, inv.getNextItemlessIndex());
        
        inv.clearItems();
        assertEquals(0, inv.getNextItemlessIndex());
        
        int size1 = 1;
        Inventory<Integer> inv1 = new Inventory<>(size1);
        int index3 = 0;
        Integer item3 = 1;
        inv1.setItemAtIndex(index3, item3);

        assertThrows(IllegalStateException.class, () -> {
            inv1.getNextItemlessIndex();
        });
	}
	
	@Test
	public void testSwapItems() {
        int size = 3;
        Inventory<Integer> inv = new Inventory<>(size);
        int index = 0;
        Integer item = 1;
        inv.setItemAtIndex(index, item);
        
        int index2 = 1;
        Integer item2 = 2;
        inv.setItemAtIndex(index2, item2);
        
        assertEquals(2, inv.getItemAtIndex(index2));
        inv.swapItemsAtIndexes(0, 1);
        assertEquals(1, inv.getItemAtIndex(index2));
	}
	
	@Test
	public void testClearItemAtIndex() {
        int size = 3;
        Inventory<Integer> inv = new Inventory<>(size);
        
        int index2 = 1;
        Integer item2 = 2;
        inv.setItemAtIndex(index2, item2);
        
        assertTrue(inv.isIndexOccupied(index2));
        inv.clearItemAtIndex(index2);
        assertFalse(inv.isIndexOccupied(index2));
	}
	
	
}
