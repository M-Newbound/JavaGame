package com.view.frames.other;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.controller.Application;
import com.controller.enums.PositionTypes;
import com.util.TeamUtilities;
import com.view.frames.GameFrameBase;
import com.view.panels.AthleteSummaryPanel;
import com.view.panels.PositionSummaryPanel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ArenaFrame extends GameFrameBase {

	private int pointer;
		
	ArrayList<PositionSummaryPanel> playerAthletes = new ArrayList<>();
	ArrayList<PositionSummaryPanel> opponentAthletes = new ArrayList<>();
	
	private Color selectionColor = new Color(170, 210, 230);
	private Color wonColor = new Color(145, 240, 145);
	private Color lostColor = new Color(255, 160, 122);
	
	private int playerScore = 0;
	private int opponentScore = 0;
	
	private JLabel scoreLabel;
	
	private void updateScoreLabel()
	{
		scoreLabel.setText(playerScore + " - " + opponentScore);
	}
	
	private void initPointer()
	{
		pointer = 0;
		
		playerAthletes.get(pointer).getAthletePanel().colorIn(selectionColor);
		opponentAthletes.get(pointer).getAthletePanel().colorIn(selectionColor);
	}
	
	
	private void movePointer()
	{
		PositionTypes position = playerAthletes.get(pointer).getType();
		
		AthleteSummaryPanel playerAthlete = new AthleteSummaryPanel(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(position));
		AthleteSummaryPanel oppositionAthlete = new AthleteSummaryPanel(Application.app.getGameState().getStadium().getOpposition().findAthleteAtPosition(position));

		if(playerAthlete.getAthlete().isInjured()) playerAthlete.colorIn(lostColor);
		else 
			{
			playerAthlete.colorIn(wonColor);
			playerScore++;
			}
		
		if(oppositionAthlete.getAthlete().isInjured()) oppositionAthlete.colorIn(lostColor);
		else 
			{
			oppositionAthlete.colorIn(wonColor);
			opponentScore++;
			}
		
		
		updateScoreLabel();
		
		playerAthletes.get(pointer).loadAthlete(playerAthlete);
		opponentAthletes.get(pointer).loadAthlete(oppositionAthlete);
		
		playerAthletes.get(pointer).revalidate();
		playerAthletes.get(pointer).repaint();
		
		opponentAthletes.get(pointer).revalidate();
		opponentAthletes.get(pointer).repaint();
		
		

		pointer += 1;
		if (pointer == playerAthletes.size()) return;
		
		playerAthletes.get(pointer).getAthletePanel().colorIn(selectionColor);
		opponentAthletes.get(pointer).getAthletePanel().colorIn(selectionColor);
	}
	
	public ArenaFrame() {
		getContentPane().setLayout(null);
		
		
		JPanel basePanel = new JPanel();
		basePanel.setLayout(new FlowLayout());
		basePanel.setBounds(35, 45, 1200, 560);
		
		getContentPane().add(basePanel);
		
		scoreLabel = new JLabel("0 - 0");
		scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setPreferredSize(new Dimension(1175, 30));
		basePanel.add(scoreLabel);
		
		JPanel teamsPanel = new JPanel();
		teamsPanel.setPreferredSize(new Dimension(1175, 500));
		basePanel.add(teamsPanel);
		teamsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JPanel lhs = new JPanel();
		JPanel middle = new JPanel();
		JPanel rhs = new JPanel();
		
		final int HEIGHT = 500;
		lhs.setPreferredSize(new Dimension(500, HEIGHT));
		middle.setPreferredSize(new Dimension(100, HEIGHT));
		rhs.setPreferredSize(new Dimension(500, HEIGHT));
		
		teamsPanel.add(lhs);
		teamsPanel.add(middle);
		teamsPanel.add(rhs);
		
		
		lhs.setLayout(new FlowLayout());
		rhs.setLayout(new FlowLayout());
		

		
		Set<PositionTypes> reserves = Set.of(PositionTypes.RESERVE_ONE, PositionTypes.RESERVE_TWO, PositionTypes.RESERVE_THREE);
		for (PositionTypes position : PositionTypes.values())
		{
			if (reserves.contains(position)) continue;
			
			AthleteSummaryPanel playerAthlete = new AthleteSummaryPanel(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(position));
			AthleteSummaryPanel oppositionAthlete = new AthleteSummaryPanel(Application.app.getGameState().getStadium().getOpposition().findAthleteAtPosition(position));
			

			PositionSummaryPanel playerPosition = new PositionSummaryPanel(position, playerAthlete);
			PositionSummaryPanel oppositionPosition = new PositionSummaryPanel(position, oppositionAthlete);
			playerAthletes.add(playerPosition);
			opponentAthletes.add(oppositionPosition);
			
			
			lhs.add(playerPosition);
			rhs.add(oppositionPosition);
			
		}
		
		initPointer();
		
		JButton btnNewButton = new JButton("Continue");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (pointer < playerAthletes.size())
				{
					movePointer();
					
					if (pointer < playerAthletes.size()) return;
					
					btnNewButton.setText("END MATCH");
					return;
				}

				payoutWinner();
				Application.app.getUserInterfaceState().changeFrameState(new WeekSummaryFrame());
				
			}
		});
		btnNewButton.setBounds(438, 607, 432, 43);
		getContentPane().add(btnNewButton);
		
		JLabel titleLabel = new JLabel("Ice Hockey Arena");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(35, 0, 1194, 34);
		getContentPane().add(titleLabel);
		
		
		TeamUtilities.playTeamMatch(Application.app.getGameState().getPlayerTeam(), Application.app.getGameState().getStadium().getOpposition());
				
		
	}
	
	private void payoutWinner()
	{
		int reward = Application.app.getGameState().getStadium().getReward();
		int balance = Application.app.getGameState().getGamePlayer().getBalance();
		
		if (playerScore > opponentScore)
		{
			Application.app.getGameState().getGamePlayer().setBalance(balance + reward);
			return;
		}
		else if (playerScore == opponentScore)
		{
			reward = reward / 2;
			Application.app.getGameState().getGamePlayer().setBalance(balance + reward);
			return;
		}
		
	}
	
}
