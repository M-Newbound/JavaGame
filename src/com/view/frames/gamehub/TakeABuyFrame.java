package com.view.frames.gamehub;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.controller.Application;
import com.controller.enums.PositionTypes;
import com.view.frames.GameFrameBase;
import com.view.frames.other.WeekSummaryFrame;
import com.view.panels.AthleteSummaryPanel;


public class TakeABuyFrame extends GameFrameBase {

	private AthleteSummaryPanel selection = null;
	private JButton continueButton;
	
	private static final Color SELECTED_COLOR = new Color(167, 244, 142);
	
	private void colorSelection()
	{
		if (selection == null) return;
		selection.colorIn(TakeABuyFrame.SELECTED_COLOR);
	}
	
	private void setSelection(AthleteSummaryPanel newSelection)
	{
		if (newSelection == null) return;
		if (newSelection.getAthlete() == null) return;
		if (selection != null) selection.reset();
		selection = newSelection;
		
		continueButton.setEnabled(selection != null);
		colorSelection();
	}
	
	public TakeABuyFrame() {
		getContentPane().setLayout(null);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Application.app.getUserInterfaceState().changeFrameState(new GameHubFrame());
				
			}
		});
		backButton.setBounds(723, 389, 320, 30);
		getContentPane().add(backButton);
		
		JLabel title = new JLabel("Bye Taker");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
		title.setBounds(23, 11, 504, 51);
		getContentPane().add(title);
		
		JTextPane instructionsText = new JTextPane();
		instructionsText.setBackground(UIManager.getColor("Button.background"));
		instructionsText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		instructionsText.setText("Welcome to the bye taker. When taking a bye you opt out of a week and in return can choose to train one of your athletes. Also all athletes will regain stamina.\r\n\r\nTo select your athlete to train simpily click on the athlete you want, it will turn green to indicate what athlete has been selected.");
		instructionsText.setBounds(587, 78, 628, 206);
		getContentPane().add(instructionsText);
		
		continueButton = new JButton("Continue and take a bye");
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				for (PositionTypes position : PositionTypes.values())
				{
					if(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(position) == null) continue;
					Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(position).restoreStamina();
				}
				
				selection.getAthlete().mutateAttributes(10, 30, false);
				
				Application.app.getUserInterfaceState().changeFrameState(new WeekSummaryFrame());
			}
		});
		continueButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		continueButton.setBounds(679, 327, 401, 51);
		getContentPane().add(continueButton);
		
		continueButton.setEnabled(false);
		
		JPanel athletesPanel = new JPanel();
		athletesPanel.setBounds(10, 68, 532, 572);
		getContentPane().add(athletesPanel);
		

		for (PositionTypes type : PositionTypes.values())
		{
			
			AthleteSummaryPanel athlete = new AthleteSummaryPanel(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(type));
			athletesPanel.add(athlete);
			
			athlete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setSelection(athlete);
				}
			});
				
		}
	}

}
