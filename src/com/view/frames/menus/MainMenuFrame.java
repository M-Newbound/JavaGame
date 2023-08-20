package com.view.frames.menus;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.controller.Application;
import com.view.frames.GameFrameBase;


public class MainMenuFrame extends GameFrameBase {


	public MainMenuFrame() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1264, 0};
		gridBagLayout.rowHeights = new int[]{54, 76, 112, 52, 46, 52, 240, 43, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JButton newGameBtn = new JButton("New Game");
		newGameBtn.setPreferredSize(new Dimension(150, 25));
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Application.app.getUserInterfaceState().changeFrameState(new GameSetupFrame());


			}
		});
		
		JLabel gameTitle = new JLabel("Ice Hockey 2023");
		gameTitle.setPreferredSize(new Dimension(80, 15));
		gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitle.setFont(new Font("Tahoma", Font.PLAIN, 60));
		GridBagConstraints gbc_gameTitle = new GridBagConstraints();
		gbc_gameTitle.fill = GridBagConstraints.BOTH;
		gbc_gameTitle.insets = new Insets(0, 0, 5, 0);
		gbc_gameTitle.gridx = 0;
		gbc_gameTitle.gridy = 1;
		getContentPane().add(gameTitle, gbc_gameTitle);
		GridBagConstraints gbc_newGameBtn = new GridBagConstraints();
		gbc_newGameBtn.fill = GridBagConstraints.VERTICAL;
		gbc_newGameBtn.insets = new Insets(0, 0, 5, 0);
		gbc_newGameBtn.gridx = 0;
		gbc_newGameBtn.gridy = 3;
		getContentPane().add(newGameBtn, gbc_newGameBtn);
		
		JButton exitGameBtn = new JButton("Exit Game");
		exitGameBtn.setPreferredSize(new Dimension(150, 25));
		exitGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		GridBagConstraints gbc_exitGameBtn = new GridBagConstraints();
		gbc_exitGameBtn.fill = GridBagConstraints.VERTICAL;
		gbc_exitGameBtn.insets = new Insets(0, 0, 5, 0);
		gbc_exitGameBtn.gridx = 0;
		gbc_exitGameBtn.gridy = 5;
		getContentPane().add(exitGameBtn, gbc_exitGameBtn);
		
		JLabel watermark = new JLabel("Developed by Martin N, and Finlay W");
		GridBagConstraints gbc_watermark = new GridBagConstraints();
		gbc_watermark.anchor = GridBagConstraints.SOUTH;
		gbc_watermark.fill = GridBagConstraints.HORIZONTAL;
		gbc_watermark.gridx = 0;
		gbc_watermark.gridy = 7;
		getContentPane().add(watermark, gbc_watermark);


	}


}
