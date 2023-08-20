package com.model.items;


import com.controller.enums.AttributeTypes;
import com.model.attributes.AttributeMapping;

/**
 * 
 * This class represents an item in the game
 * Each item has a name, description, cost, image file path, and attribute effects.
 *
 */
public abstract class GameItem {
	private final String NAME;
	private final String DESCRIPTION;
	private final int cost;
	
	protected final String IMAGE_FILE_PATH;
	
	protected AttributeMapping positiveEffects;
	protected AttributeMapping negativeEffects;
	

	private static final int MAX_NAME_LENGTH = 25;
	
	/**
	 * creates an ItemBase object with a name and description
	 * @param name the name of the item
	 * @param description the description of the item
	 * @param filePath the location to the image file
	 * @param positiveEffects the items positive effects
	 * @param negativeEffects the items negative effects
	 * @param cost the cost of the item in money
	 * @throws IllegalArgumentException if name or description is not valid
	 */
	public GameItem(String name, String description, String filePath, AttributeMapping positiveEffects, AttributeMapping negativeEffects, int cost) throws IllegalArgumentException
	{
		this.NAME = name;
		this.DESCRIPTION = description;
		this.IMAGE_FILE_PATH = filePath;
		this.positiveEffects = positiveEffects;
		this.negativeEffects = negativeEffects;
		this.cost = cost;

		validateItem();
	}
	
	/**
	 * retrieves the positive effects that the item causes
	 * @return
	 */
	public AttributeMapping getPositiveAttributeMapping()
	{
		return positiveEffects;
	}
	
	/**
	 * retrieves the negative effects that the item causes
	 * @return
	 */
	public AttributeMapping getNegativeAttributeMapping()
	{
		return negativeEffects;
	}
	
	/**
	 * retrieves the overall attribute effects that the item causes
	 * @param type the type of the attribute effected
	 * @return a new value for the attribute type based on item causes
	 */
	public int getAttrivuteEffects(AttributeTypes type)
	{
		return positiveEffects.getAttributeValue(type) - negativeEffects.getAttributeValue(type);
	}
	
	/**
	 * retrieves the file path for the images used for each item in the GUI
	 * @return the file path for the images
	 */
	public String getFilePath()
	{
		return IMAGE_FILE_PATH;
	}
	
	/**
	 * retrieves the name of the item
	 * @return the name of the item
	 */
	public String getItemName()
	{
		return NAME;
	}
	
	/**
	 * retrieves the description of the item
	 * @return the description of the item
	 */
	public String getItemDescription()
	{
		return DESCRIPTION;
	}

	/**
	 * checks if item exceeds maximum character limit set
	 * @throws IllegalArgumentException if the item name exceeds the character limit
	 */
	private void validateItem() throws IllegalArgumentException
	{
		if (NAME.length() > GameItem.MAX_NAME_LENGTH) throw new IllegalArgumentException("Item " + NAME + " exceeds name character limit");
	}

	/**
	 * retrieves the cost of an item
	 * @return the cost of an item
	 */
	public int getiCost() {
		return cost;
	}
}
