package com.controller.ui;

import javax.swing.JFrame;
import com.view.frames.GameFrameBase;
import com.view.frames.menus.MainMenuFrame;

/**
 * 
 * This class represents the user interface for the game
 * It manages the main game frame and handles frame state changes
 *
 */
public class UserInterface {
	
	JFrame frame;
	
	/**
	 * creates a new user interface instance by creating a new frame
	 * changes frame state to the main menu of the game
	 */
	public UserInterface()
	{
		frame = new GameFrameBase();
		getFrame().setVisible(true);
		
		changeFrameState(new MainMenuFrame());
	}
	
	/**
	 * changes from current frame to a new desired frame
	 * @param newFrame the new frame to be changed to
	 */
	public void changeFrameState(JFrame newFrame)
	{
		getFrame().setContentPane(newFrame.getContentPane());
		getFrame().revalidate();
		getFrame().repaint();
	}

	/**
	 * retrieves the current frame
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
}
