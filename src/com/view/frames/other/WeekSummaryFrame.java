package com.view.frames.other;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controller.Application;
import com.util.RandomEventUtilities;
import com.view.frames.GameFrameBase;
import com.view.frames.gamehub.GameHubFrame;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class WeekSummaryFrame extends GameFrameBase {


	public WeekSummaryFrame() {
		getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("end of week: " + (Application.app.getGameState().getWeek() + 1));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(287, 33, 698, 69);
		getContentPane().add(titleLabel);
		
		JButton continueButton = new JButton("continue");
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Application.app.getGameState().loadNextWeek();
				Application.app.getUserInterfaceState().changeFrameState(new GameHubFrame());
				
			}
		});
		continueButton.setBounds(506, 509, 264, 60);
		getContentPane().add(continueButton);
		
		
		ArrayList<String> events = RandomEventUtilities.applyRandomEvents();
		
		
		JPanel randomEventsPanel = new JPanel();
		randomEventsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		randomEventsPanel.setBounds(345, 218, 581, 220);
		
		
		if (events.size() == 0)
		{
			randomEventsPanel.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel noEventsLabel = new JLabel("No Random Events This Week");
			noEventsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			noEventsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			randomEventsPanel.add(noEventsLabel);

		}
		else
		{
			randomEventsPanel.setLayout(new GridLayout(events.size(), 1, 0, 0));
			
			for (String event : events)
			{
				JLabel eventLabel = new JLabel(event);
				eventLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				eventLabel.setHorizontalAlignment(SwingConstants.CENTER);
				randomEventsPanel.add(eventLabel);
			}			
		}
		
		
		getContentPane().add(randomEventsPanel);

		int balance = Application.app.getGameState().getGamePlayer().getBalance();
		JLabel balanceLabel = new JLabel("current balance: $" + NumberFormat.getIntegerInstance().format(balance));
		balanceLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		balanceLabel.setBounds(287, 113, 698, 69);
		getContentPane().add(balanceLabel);


	}
}
