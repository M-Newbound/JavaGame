package com.view.frames.gamehub;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.controller.Application;
import com.controller.enums.PositionTypes;
import com.model.athletes.Athlete;
import com.model.items.GameItem;
import com.view.panels.PositionSummaryPanel;
import com.view.frames.menus.MainMenuFrame;
import com.view.frames.other.ForcedGameEndFrame;
import com.view.frames.other.GameEndFrame;
import com.view.panels.AthleteSummaryPanel;


public class GameHubFrame extends JFrame {

	private final Color injuredColor = new Color(255, 200, 200);
	

	private void checkWinLooseConditions()
	{
		
		// checking the season duration && the current week
		if (Application.app.getGameState().getWeek() >= Application.app.getGameState().getSeasonDuration())
		{
			
			Application.app.getUserInterfaceState().changeFrameState(new GameEndFrame());
			return;
		}
		
		final int REQUIRED_NUM_OF_ATHLETES = 6;
		
		// checking if the player has lost
		int athletesCount = Application.app.getGameState().getPlayerTeam().getAthletes().length;
		if (athletesCount >= REQUIRED_NUM_OF_ATHLETES) return;
		
		int athletesNeeded = REQUIRED_NUM_OF_ATHLETES - athletesCount;
		int totalWorth = Application.app.getGameState().getGamePlayer().getBalance();
		
		for (GameItem item : Application.app.getGameState().getPlayerInventory())
		{
			if (item == null) continue;
			totalWorth += item.getiCost();
		}
		
		ArrayList<Athlete> marketOptions = new ArrayList<>();
		for (Athlete athlete : Application.app.getGameState().getMarket().getAthleteInventory())
		{
			if (athlete == null) continue;
			marketOptions.add(athlete);
		}
		
		if (marketOptions.size() < athletesNeeded)
		{
			Application.app.getUserInterfaceState().changeFrameState(new ForcedGameEndFrame());
			return;
		}
		
		marketOptions = (ArrayList<Athlete>) marketOptions.stream()
                .sorted(Comparator.comparingInt(Athlete::getEstimatedCost))
                .collect(Collectors.toList());

		
		int minCost = 0;
		for (int i=0; i<athletesNeeded; i++)
		{
			minCost += marketOptions.get(i).getEstimatedCost();
		}
		

		
		if (minCost > totalWorth)
		{
			Application.app.getUserInterfaceState().changeFrameState(new ForcedGameEndFrame());
		}
		
	}
	
	
	private void generateAthleteSummaries(JPanel activePlayersPanel, JPanel reservePlayersPanel)
	{
		
		Set<PositionTypes> reserves = Set.of(PositionTypes.RESERVE_ONE, PositionTypes.RESERVE_TWO, PositionTypes.RESERVE_THREE);
		for (PositionTypes type : PositionTypes.values())
		{
			
			AthleteSummaryPanel athlete = new AthleteSummaryPanel(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(type));
			if(reserves.contains(type)) reservePlayersPanel.add(new PositionSummaryPanel(type, athlete));
			else activePlayersPanel.add(new PositionSummaryPanel(type, athlete));
			
			if (athlete.getAthlete() != null && athlete.getAthlete().isInjured())
			{
				athlete.colorIn(injuredColor);
			}
		}
		
	}
	
	/**
	 * Create the frame.
	 */
	public GameHubFrame() {
		getContentPane().setLayout(null);
		
		
		
		JPanel athletesPanel = new JPanel();
		athletesPanel.setBounds(0, 141, 1264, 484);
		getContentPane().add(athletesPanel);
		athletesPanel.setLayout(new BoxLayout(athletesPanel, BoxLayout.Y_AXIS));
		
		JPanel activeAthletesPanel = new JPanel();
		athletesPanel.add(activeAthletesPanel);
		activeAthletesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel reserveAthletesPanel = new JPanel();
		athletesPanel.add(reserveAthletesPanel);
		reserveAthletesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		generateAthleteSummaries(activeAthletesPanel, reserveAthletesPanel);
		
		JPanel buttonPannel = new JPanel();
		buttonPannel.setBounds(0, 623, 1264, 58);
		getContentPane().add(buttonPannel);
		buttonPannel.setLayout(new GridLayout(0, 5, 100, 0));
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Application.app.getUserInterfaceState().changeFrameState(new MainMenuFrame());
				
			}
		});
		buttonPannel.add(quitButton);
		
		JButton marketButton = new JButton("Market");
		marketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Application.app.getUserInterfaceState().changeFrameState(new MarketplaceFrame());
				
			}
		});
		buttonPannel.add(marketButton);
		
		JButton clubhouseButton = new JButton("Clubhouse");
		clubhouseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Application.app.getUserInterfaceState().changeFrameState(new ClubhouseFrame());
			}
		});
		buttonPannel.add(clubhouseButton);
		
		JButton stadiumButton = new JButton("Stadium");
		
		stadiumButton.setEnabled(Application.app.getGameState().getGamePlayer().getTeam().canPlayMatch());
		
		stadiumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Application.app.getUserInterfaceState().changeFrameState(new StadiumFrame());
			}
		});
		buttonPannel.add(stadiumButton);
		
		JButton byeButton = new JButton("Take a Bye");
		byeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Application.app.getUserInterfaceState().changeFrameState(new TakeABuyFrame());
			}
		});
		buttonPannel.add(byeButton);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(0, 0, 1264, 50);
		getContentPane().add(infoPanel);
		infoPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel weekPanel = new JPanel();
		infoPanel.add(weekPanel);
		weekPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		String weekMessage = "Week " + (Application.app.getGameState().getWeek() + 1) + "/" + Application.app.getGameState().getSeasonDuration();
		JLabel weekLabel = new JLabel(weekMessage);
		weekPanel.add(weekLabel);
		weekLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		weekLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JPanel bufferPanel = new JPanel();
		infoPanel.add(bufferPanel);
		bufferPanel.setLayout(null);
		
		JPanel balancePanel = new JPanel();
		infoPanel.add(balancePanel);
		balancePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		String balanceMessage = "Balance $" + NumberFormat.getIntegerInstance().format(Application.app.getGameState().getGamePlayer().getBalance());
		JLabel balanceLabel = new JLabel(balanceMessage);
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		balancePanel.add(balanceLabel);
		
		
        int delay = 1; // Delay in milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

            	checkWinLooseConditions();
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.setRepeats(false);
        timer.start();
		
		

	}


}
