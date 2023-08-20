package com.view.frames.menus;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.controller.Application;
import com.controller.world.GameWorld;
import com.view.frames.GameFrameBase;

public class GameSetupFrame extends GameFrameBase {

	
	private void updateDifficultyMessage(JSlider difficultySlider, JLabel difficultyMessage)
	{
		if(difficultySlider.getValue() <= 33)
		{
			difficultyMessage.setText("easy");
		}
		else if (difficultySlider.getValue() <= 66)
		{
			difficultyMessage.setText("medium");
		}
		else
		{
			difficultyMessage.setText("hard");
		}
	}

	
	private void updateSeasonDurationMessage(JSlider seasonDurationSlider, JLabel seasonDurationMessage)
	{
		seasonDurationMessage.setText(seasonDurationSlider.getValue() + " weeks");
	}
	
	private void processTeamTextFeild(JTextField teamTextField, JLabel message, JButton continueButton)
	{
		if (Pattern.matches("[a-zA-Z][a-zA-Z\s]{2,14}", teamTextField.getText()))
		{
			continueButton.setEnabled(true);
			message.setText("");
		}
		else
		{
			continueButton.setEnabled(false);
			message.setText("please enter a valid team name");

		}
	}
	
	/**
	 * Create the frame.
	 */
	public GameSetupFrame() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{189, 75, 188, 58, 239, 46, 244, 203, 0};
		gridBagLayout.rowHeights = new int[]{42, 110, 32, 45, 32, 52, 32, 244, 68, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel newGameSetupBanner = new JLabel("New Game Setup");
		newGameSetupBanner.setFont(new Font("Tahoma", Font.BOLD, 30));
		newGameSetupBanner.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_newGameSetupBanner = new GridBagConstraints();
		gbc_newGameSetupBanner.fill = GridBagConstraints.BOTH;
		gbc_newGameSetupBanner.insets = new Insets(0, 0, 5, 0);
		gbc_newGameSetupBanner.gridwidth = 8;
		gbc_newGameSetupBanner.gridx = 0;
		gbc_newGameSetupBanner.gridy = 0;
		getContentPane().add(newGameSetupBanner, gbc_newGameSetupBanner);
		
		JLabel difficultyLabel = new JLabel("Difficulty");
		difficultyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		difficultyLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_difficultyLabel = new GridBagConstraints();
		gbc_difficultyLabel.fill = GridBagConstraints.BOTH;
		gbc_difficultyLabel.insets = new Insets(0, 0, 5, 5);
		gbc_difficultyLabel.gridx = 2;
		gbc_difficultyLabel.gridy = 2;
		getContentPane().add(difficultyLabel, gbc_difficultyLabel);
		
		JLabel difficultyMessage = new JLabel("NOT IMPLEMENTED");
		GridBagConstraints gbc_difficultyMessage = new GridBagConstraints();
		gbc_difficultyMessage.gridwidth = 2;
		gbc_difficultyMessage.anchor = GridBagConstraints.WEST;
		gbc_difficultyMessage.insets = new Insets(0, 0, 5, 5);
		gbc_difficultyMessage.gridx = 5;
		gbc_difficultyMessage.gridy = 2;
		getContentPane().add(difficultyMessage, gbc_difficultyMessage);
		
		JSlider difficultySlider = new JSlider();
		
		
		GridBagConstraints gbc_difficultySlider = new GridBagConstraints();
		gbc_difficultySlider.fill = GridBagConstraints.BOTH;
		gbc_difficultySlider.insets = new Insets(0, 0, 5, 5);
		gbc_difficultySlider.gridx = 4;
		gbc_difficultySlider.gridy = 2;
		getContentPane().add(difficultySlider, gbc_difficultySlider);
		
		updateDifficultyMessage(difficultySlider, difficultyMessage);
		
		
		JLabel seasonDurationLabel = new JLabel("Season Duration");
		seasonDurationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		seasonDurationLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_seasonDurationLabel = new GridBagConstraints();
		gbc_seasonDurationLabel.fill = GridBagConstraints.BOTH;
		gbc_seasonDurationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_seasonDurationLabel.gridx = 2;
		gbc_seasonDurationLabel.gridy = 4;
		getContentPane().add(seasonDurationLabel, gbc_seasonDurationLabel);
		
		JSlider seasonDurationSlider = new JSlider();

		seasonDurationSlider.setPaintLabels(true);
		seasonDurationSlider.setSnapToTicks(true);
		seasonDurationSlider.setValue(10);
		seasonDurationSlider.setMaximum(15);
		seasonDurationSlider.setMinimum(5);
		GridBagConstraints gbc_seasonDurationSlider = new GridBagConstraints();
		gbc_seasonDurationSlider.fill = GridBagConstraints.BOTH;
		gbc_seasonDurationSlider.insets = new Insets(0, 0, 5, 5);
		gbc_seasonDurationSlider.gridx = 4;
		gbc_seasonDurationSlider.gridy = 4;
		getContentPane().add(seasonDurationSlider, gbc_seasonDurationSlider);
		
		JLabel seasonDurationMessage = new JLabel("NOT IMPLEMENTED");
		GridBagConstraints gbc_seasonDurationMessage = new GridBagConstraints();
		gbc_seasonDurationMessage.gridwidth = 2;
		gbc_seasonDurationMessage.anchor = GridBagConstraints.WEST;
		gbc_seasonDurationMessage.insets = new Insets(0, 0, 5, 5);
		gbc_seasonDurationMessage.gridx = 5;
		gbc_seasonDurationMessage.gridy = 4;
		getContentPane().add(seasonDurationMessage, gbc_seasonDurationMessage);
		
		
		JLabel teamNameLabel = new JLabel("Team Name");
		teamNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		teamNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_teamNameLabel = new GridBagConstraints();
		gbc_teamNameLabel.fill = GridBagConstraints.BOTH;
		gbc_teamNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_teamNameLabel.gridx = 2;
		gbc_teamNameLabel.gridy = 6;
		getContentPane().add(teamNameLabel, gbc_teamNameLabel);
		
		JTextField teamNameTextField = new JTextField();


		teamNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_teamNameTextField = new GridBagConstraints();
		gbc_teamNameTextField.anchor = GridBagConstraints.SOUTH;
		gbc_teamNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_teamNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_teamNameTextField.gridx = 4;
		gbc_teamNameTextField.gridy = 6;
		getContentPane().add(teamNameTextField, gbc_teamNameTextField);
		teamNameTextField.setColumns(10);
		
		JLabel teamNameMessage = new JLabel("NOT IMPLEMENTED");
		GridBagConstraints gbc_teamNameMessage = new GridBagConstraints();
		gbc_teamNameMessage.gridwidth = 2;
		gbc_teamNameMessage.anchor = GridBagConstraints.WEST;
		gbc_teamNameMessage.insets = new Insets(0, 0, 5, 5);
		gbc_teamNameMessage.gridx = 5;
		gbc_teamNameMessage.gridy = 6;
		getContentPane().add(teamNameMessage, gbc_teamNameMessage);
		
		JButton continueButton = new JButton("Continue");
		JButton backButton = new JButton("Back");

		GridBagConstraints gbc_backButton = new GridBagConstraints();
		gbc_backButton.fill = GridBagConstraints.BOTH;
		gbc_backButton.insets = new Insets(0, 0, 0, 5);
		gbc_backButton.gridx = 0;
		gbc_backButton.gridy = 8;
		getContentPane().add(backButton, gbc_backButton);
		GridBagConstraints gbc_continueButton = new GridBagConstraints();
		gbc_continueButton.fill = GridBagConstraints.BOTH;
		gbc_continueButton.gridx = 7;
		gbc_continueButton.gridy = 8;
		getContentPane().add(continueButton, gbc_continueButton);
		
		
		difficultySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				updateDifficultyMessage(difficultySlider, difficultyMessage);
				
			}
		});
		
		seasonDurationSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateSeasonDurationMessage(seasonDurationSlider, seasonDurationMessage);
			}
		});
		updateSeasonDurationMessage(seasonDurationSlider, seasonDurationMessage);
		
		
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				// LOAD GAMESTATE
				GameWorld newGameState = new GameWorld(seasonDurationSlider.getValue(), (float)difficultySlider.getValue() / difficultySlider.getMaximum());
				Application.app.setGameState(newGameState);
				
				// LOAD GAMEPLAYER
				Application.app.getGameState().getGamePlayer().setupTeam(teamNameTextField.getText());
				
				// CHANGE FRAME
				Application.app.getUserInterfaceState().changeFrameState(new TeamSetupFrame());
				
					
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// CHANGE FRAME
				Application.app.getUserInterfaceState().changeFrameState(new MainMenuFrame());
				
			}
		});
		
		processTeamTextFeild(teamNameTextField, teamNameMessage, continueButton);
		
		teamNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {

            	processTeamTextFeild(teamNameTextField, teamNameMessage, continueButton);
            }

			@Override
			public void insertUpdate(DocumentEvent e) {
				processTeamTextFeild(teamNameTextField, teamNameMessage, continueButton);
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				processTeamTextFeild(teamNameTextField, teamNameMessage, continueButton);
				
			}
		
	
		});

	}

}
