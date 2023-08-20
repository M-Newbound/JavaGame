package com.model.inventory;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An inventory is a custom collection of InventoryItems set within a specified size.
 * @param <InventoryItem> the type of items held in the inventory
 */
public class Inventory<InventoryItem extends Object> implements Iterable<InventoryItem> {
	
	private final int SIZE;
	private ArrayList<InventoryItem> items;
	
	
	/**
	 * Constructs an Inventory object with the specified size.
	 * @param size the size of the inventory
	 */
	public Inventory(int size) throws IllegalArgumentException
	{
		if (size < 0) throw new IllegalArgumentException("Inventory cannot exist with a negative size");
		
		this.SIZE = size;
		this.items = new ArrayList<InventoryItem>();
		
		for(int i=0; i<SIZE; i++) this.items.add(null);
	}

	/**
	 * 
	 * @param index
	 * @param item
	 * @throws IllegalArgumentException
	 */
	public void setItemAtIndex(int index, InventoryItem item) throws IllegalArgumentException
	{
		validateIndex(index);
		items.set(index, item);
	}

	/**
	 * 
	 * @param index
	 * @return
	 * @throws IllegalArgumentException
	 */
	public InventoryItem getItemAtIndex(int index) throws IllegalArgumentException
	{
		validateIndex(index);
		return items.get(index);
	}
	
	/**
	 * Returns the size of the inventory.
	 * @return the size of the inventory
	 */
	public int getSize()
	{
		return SIZE;
	}
	
	/**
	 * Checks if the specified index is valid within the inventory.
	 * @param index the index to check
	 * @return true if the index is valid, false otherwise
	 */
	public boolean isIndexValid(int index)
	{
		try {validateIndex(index);}
		
		catch(IllegalArgumentException ignore) {return false;}
		
		return true;
	}
	
	/**
	 * Checks if the specified index in the inventory has an assigned item.
	 * @param index the index to check
	 * @return true if the index is occupied, false otherwise
	 * @throws IllegalArgumentException if the index is invalid
	 */
	public boolean isIndexOccupied(int index) throws IllegalArgumentException
	{
		validateIndex(index);
		return getItemAtIndex(index) != null;
	}

	/**
	 * Unassigns all items from the inventory.
	 */
	public void clearItems()
	{
		for(int i=0; i<SIZE; i++) items.set(i, null);
	}

	/**
	 * Checks if the inventory is full (all indexes are occupied by items).
	 * @return true if the inventory is full, false otherwise
	 */
	public boolean isFull()
	{
		for(int i=0; i<SIZE; i++) {if (isIndexOccupied(i) == false) return false;}
		return true;
	}
	
	/**
	 * Retrieves the index of the next available (itemless) index in the inventory.
	 * @return the index of the next available index
	 * @throws IllegalStateException if the inventory is full (no unassigned index exists)
	 */
	public int getNextItemlessIndex() throws IllegalStateException
	{
		for(int i=0; i<SIZE; i++) {if (isIndexOccupied(i) == false) return i;}
		throw new IllegalStateException("Inventory is full: no index exists with an unassigned item");
	}

	/**
	 * swaps the items at each index with each other in the inventory
	 * @param indexA the index of first item
	 * @param indexB the index of second item
	 * @throws IllegalArgumentException if indexA or indexB is not valid
	 */
	public void swapItemsAtIndexes(int indexA, int indexB) throws IllegalArgumentException
	{
		validateIndex(indexA);
		validateIndex(indexB);
		
		InventoryItem itemA = getItemAtIndex(indexA);
		InventoryItem itemB = getItemAtIndex(indexB);
		
		//TODO: check if itemA or itemB are null
		
		setItemAtIndex(indexA, itemB);
		setItemAtIndex(indexB, itemA);
	}

	/**
	 * clears the index of the item
	 * @param index the index of the item
	 * @throws IllegalArgumentException if the index of the item is not valid
	 */
	public void clearItemAtIndex(int index) throws IllegalArgumentException
	{
		validateIndex(index);
		items.set(index, null);
	}

	/**
	 * returns an iterator over the items in the inventory
	 */
	@Override
	public Iterator<InventoryItem> iterator() {
		return items.iterator();
	}
	
	
	private void validateIndex(int index) throws IllegalArgumentException
	{
		if (index < 0) throw new IllegalArgumentException(String.format("Inventory index (%d) cannot be negative", index));
		if (index >= SIZE) throw new IllegalArgumentException(String.format("Inventory index (%d) cannot exceed inventory size (%d) ", index, SIZE));
	}
	
}
