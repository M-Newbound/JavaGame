package com.view.frames.menus;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.view.frames.GameFrameBase;

public class GameSummaryFrame extends GameFrameBase {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSummaryFrame frame = new GameSummaryFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameSummaryFrame() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
