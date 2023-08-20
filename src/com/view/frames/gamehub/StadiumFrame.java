package com.view.frames.gamehub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.controller.Application;
import com.model.athletes.Athlete;
import com.util.TeamUtilities;
import com.view.frames.GameFrameBase;
import com.view.frames.other.ArenaFrame;
import com.view.panels.AthleteSummaryPanel;


public class StadiumFrame extends GameFrameBase {

	/**
	 * Create the frame.
	 */
	public StadiumFrame() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 225, 1228, 371);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
	
		
		
		for (int i=0; i<Application.app.getGameState().getStadium().getOptionsCount();i++)
		{
			final int index = i;

			JPanel opponentOption = new JPanel();
			opponentOption.setBorder(new LineBorder(new Color(0, 0, 0)));
			opponentOption.setPreferredSize(new Dimension(355, 350));
			
			
			JPanel opponentDescriptorPanel = new JPanel();
			JPanel athleteShowoffPanel = new JPanel();
			JPanel rewardPanel = new JPanel();
			JPanel playButtonPanel = new JPanel();
			
			final int WIDTH = 350;
			
			opponentDescriptorPanel.setPreferredSize(new Dimension(WIDTH, 25));
			athleteShowoffPanel.setPreferredSize(new Dimension(WIDTH, 190));
			rewardPanel.setPreferredSize(new Dimension(WIDTH, 50));
			playButtonPanel.setPreferredSize(new Dimension(WIDTH, 50));
			
			opponentOption.add(opponentDescriptorPanel);
			opponentOption.add(athleteShowoffPanel);
			opponentOption.add(rewardPanel);
			opponentOption.add(playButtonPanel);
			
			opponentDescriptorPanel.setLayout(new GridLayout());
			athleteShowoffPanel.setLayout(new GridLayout(1, 2));
			rewardPanel.setLayout(new GridLayout(2, 1));
			playButtonPanel.setLayout(new FlowLayout());
			
			// 65 for ASCII shift
			JLabel opponentDescriptor = new JLabel("Opponent " + Character.toString((char) i + 65));
			opponentDescriptor.setFont(new Font("Tahoma", Font.BOLD, 15));
			opponentDescriptor.setHorizontalAlignment(SwingConstants.CENTER);
			opponentDescriptorPanel.add(opponentDescriptor);


			JPanel lhs = new JPanel();
			lhs.setLayout(new FlowLayout());
			
			JPanel rhs = new JPanel();
			rhs.setLayout(new FlowLayout());
			

			JLabel lhsTitle = new JLabel("Their Best");	
			JLabel rhsTitle = new JLabel("Their Worst");
			lhsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			rhsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			
			Dimension titleDimension = new Dimension(100, 25);
			lhsTitle.setPreferredSize(titleDimension);
			rhsTitle.setPreferredSize(titleDimension);
			lhsTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
			rhsTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
			
			lhs.add(lhsTitle);
			rhs.add(rhsTitle);
			
			Dimension athleteDimension = new Dimension(150, 150);

			Athlete worstAthleteProfile = TeamUtilities.getWorstAthlete(Application.app.getGameState().getStadium().getTeamFromOption(i));
			AthleteSummaryPanel worstAthlete = new AthleteSummaryPanel(worstAthleteProfile);
			worstAthlete.setPreferredSize(athleteDimension);
			
			Athlete bestAthleteProfile = TeamUtilities.getBestAthlete(Application.app.getGameState().getStadium().getTeamFromOption(i));
			AthleteSummaryPanel bestAthlete = new AthleteSummaryPanel(bestAthleteProfile);
			bestAthlete.setPreferredSize(athleteDimension);
			
			lhs.add(bestAthlete);
			rhs.add(worstAthlete);
			
			athleteShowoffPanel.add(lhs);
			athleteShowoffPanel.add(rhs);
			
			
			// --------- rewardPanel
			
			JLabel rewardDescriptor = new JLabel("REWARD");
			JLabel rewardValue = new JLabel("$" + NumberFormat.getIntegerInstance().format(Application.app.getGameState().getStadium().getRewardOfOption(index)));
			
			rewardDescriptor.setHorizontalAlignment(SwingConstants.CENTER);
			rewardValue.setHorizontalAlignment(SwingConstants.CENTER);
			
			rewardPanel.add(rewardDescriptor);
			rewardPanel.add(rewardValue);
			
			// ------------ playButtonPanel
			
			JButton vsButton = new JButton();
			vsButton.setText("play opponent");
			vsButton.setPreferredSize(new Dimension(125, 30));
			playButtonPanel.add(vsButton);
			
			vsButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                Application.app.getGameState().getStadium().setOpposition(index);
		                Application.app.getUserInterfaceState().changeFrameState(new ArenaFrame());
		            }
		        });
			
			panel.add(opponentOption);
		}

		JLabel titleLabel = new JLabel("Stadium");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		titleLabel.setBounds(10, 11, 1244, 52);
		getContentPane().add(titleLabel);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application.app.getUserInterfaceState().changeFrameState(new GameHubFrame());
				
			}
		});
		backButton.setBounds(879, 607, 250, 29);
		getContentPane().add(backButton);
		
		JTextPane stadiumDescription = new JTextPane();
		stadiumDescription.setEditable(false);
		stadiumDescription.setBackground(UIManager.getColor("Button.background"));
		stadiumDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		stadiumDescription.setText("welcome to the stadium, every week you can choose to play an opponent in the arena. Matches are a all or nothing deal where the winner will take all. Each opponent display's their most expensive (and cheapest) athlete to date.... but this information may be deceiving as the athlete's position is not revealed.");
		stadiumDescription.setBounds(316, 112, 718, 104);
		getContentPane().add(stadiumDescription);


	}
}
