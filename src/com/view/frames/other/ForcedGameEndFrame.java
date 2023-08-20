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

public class ForcedGameEndFrame extends GameFrameBase {


	public ForcedGameEndFrame() {
		getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("GAME OVER");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(184, 43, 824, 95);
		getContentPane().add(titleLabel);
		
		JLabel descriptionLabel = new JLabel("you do not have the athletes or money to continue playing");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		descriptionLabel.setBounds(184, 203, 824, 116);
		getContentPane().add(descriptionLabel);
		
		JButton mainMenuButton = new JButton("Main Menu");
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application.app.getUserInterfaceState().changeFrameState(new MainMenuFrame());
			}
		});
		mainMenuButton.setBounds(474, 403, 247, 41);
		getContentPane().add(mainMenuButton);


	}

}
