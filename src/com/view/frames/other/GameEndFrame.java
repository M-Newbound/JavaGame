package com.view.frames.other;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.controller.Application;
import com.view.frames.GameFrameBase;
import com.view.frames.menus.MainMenuFrame;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameEndFrame extends GameFrameBase {

	public GameEndFrame() {
		getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("GAME COMPLETED");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(364, 72, 517, 86);
		getContentPane().add(titleLabel);
		
		JLabel descriptionLabel = new JLabel("Congratulations! You and your team made it through the entire tour....");
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setBounds(130, 240, 1033, 60);
		getContentPane().add(descriptionLabel);
		
		JButton mainMenuButton = new JButton("Main Menu");
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Application.app.getUserInterfaceState().changeFrameState(new MainMenuFrame());
				
			}
		});
		mainMenuButton.setBounds(501, 393, 269, 45);
		getContentPane().add(mainMenuButton);


	}

}
