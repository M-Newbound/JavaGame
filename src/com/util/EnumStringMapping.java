package com.util;

/**
 * This class provides methods for converting enums to associating Strings
 */
public class EnumStringMapping {
	
	/**
	 * Converts a position enum to its associate String
	 * 
	 * @param type the position enum value.
	 * @return The String associate with the position enum
	 */
	public static String positionTypeToText(com.controller.enums.PositionTypes type)
	{
		switch(type)
		{
		
		case GOAL_KEPPER:
			return "Goal Keeper";
		case LEFT_DEFENCE:
			return "Left Defence";

		case RIGHT_DEFENCE:
			return "Right Defence";

		case LEFT_WING:
			return "Left Wing";

		case RIGHT_WING:
			return "Right Wing";

		case CENTER:
			return "Center";

		case RESERVE_ONE:
			return "Reserve One";

		case RESERVE_TWO:
			return "Reserve Two";

		case RESERVE_THREE:
			return "Reserve Three";

		default:
			return "ERROR POSITION";
		}
	}
	
	/**
	 * Converts an attribute enum to its associate String
	 * 
	 * @param type the attribute enum value.
	 * @return The String associate with the attribute enum
	 */
	public static String attributeTypeToText(com.controller.enums.AttributeTypes type)
	{
		switch(type)
		{
		case ATTACK:
			return "Attack";
		case DEFENCE:
			return "Defence";
		case INTERCEPTION:
			return "Interception";
		case PRECISION:
			return "Precision";
		case STRENGTH:
			return "Strength";
		default:
			return "ERROR ATTRIBUTE";
		
		}
	}

}
