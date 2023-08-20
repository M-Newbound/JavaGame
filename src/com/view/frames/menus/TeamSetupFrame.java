package com.view.frames.menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.controller.Application;
import com.controller.enums.PositionTypes;
import com.util.AthleteUtilities;
import com.view.frames.GameFrameBase;
import com.view.frames.gamehub.GameHubFrame;
import com.view.panels.AthleteSummaryPanel;
import com.view.panels.PositionTagPanel;



public class TeamSetupFrame extends GameFrameBase {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<AthleteSummaryPanel> athleteOptions;
	private ArrayList<PositionTagPanel> positionOptions;
	private JPanel positionsPanel;
	private JPanel athletesPanel;
	
	private JButton continueButton;
	JLabel balanceAmmountLabel;
	private int balance = 400;
	
	private EnumMap<PositionTypes, AthleteSummaryPanel> positionMappings;
	private Map<PositionTagPanel, JPanel> positionTagSlots;
	
	private static final Color DRAFTED_COLOR = new Color(167, 244, 142);
	private static final Color TOO_EXPENSIVE_COLOR = new Color(250, 140, 140);

	private boolean isTooExpensive(AthleteSummaryPanel athlete)
	{
		if (athlete.getAthlete().getEstimatedCost() > balance) return true;
		return false;
	}
	
	private void shadeTooExpensiveAthletes()
	{
		for (AthleteSummaryPanel athlete : athleteOptions)
		{
			if (positionMappings.values().contains(athlete)) continue;
			if (isTooExpensive(athlete)) athlete.colorIn(TOO_EXPENSIVE_COLOR);
			else athlete.reset();
		}
	}

	private void addMapping(PositionTagPanel position, AthleteSummaryPanel athlete)
	{
		positionMappings.put(position.getType(), athlete);
		checkContinueCondition();
		athlete.colorIn(TeamSetupFrame.DRAFTED_COLOR);
		
		int athleteX = athlete.getX() + athlete.getParent().getX();
        int athleteY = athlete.getY() + athlete.getParent().getY();
        
        int yOffset = 150;
        setBalance(balance - athlete.getAthlete().getEstimatedCost());
        
        
        position.setLocation(athleteX, athleteY + yOffset);
        

	}
	
	private void checkContinueCondition()
	{
		Set<PositionTypes> reserves = Set.of(PositionTypes.RESERVE_ONE, PositionTypes.RESERVE_TWO, PositionTypes.RESERVE_THREE);
		for (PositionTypes type : PositionTypes.values())
		{
			if (reserves.contains(type)) continue;
			
			if (positionMappings.get(type) == null)
			{
				continueButton.setEnabled(false);
				return;
				
			}
		}
		
		continueButton.setEnabled(true);
	}
		
	private void createAthletePanels()
	{
		athleteOptions = new ArrayList<AthleteSummaryPanel>();
	       getContentPane().setLayout(null);

	       athletesPanel = new JPanel();
	       athletesPanel.setBounds(244, 166, 943, 376);
	       getContentPane().add(athletesPanel);
	       athletesPanel.setLayout(new GridLayout(2, 6, 10, 10)); // 2 rows, 6 columns, 10px horizontal and vertical gap

	       	ArrayList<Integer> costs = new ArrayList<Integer>();
	       	
	        final int OPTIONS_NUM = 12;
	        for (int i = 0; i < OPTIONS_NUM; i++) {
	            AthleteSummaryPanel athlete = new AthleteSummaryPanel(AthleteUtilities.generateAthlete(-0.2f), true);
	            athlete.setPreferredSize(new Dimension(150, 175));
	            athletesPanel.add(athlete);
	            athleteOptions.add(athlete);
	            
	            costs.add(athlete.getAthlete().getEstimatedCost());	            
	        }
	        Collections.sort(costs, Collections.reverseOrder());
	        
	        int sum = costs.get(2) / 4;
	        for(int i = 3; i<costs.size(); i++)
	        {
	        	sum += costs.get(i);
	        }
	        balance = sum;
		
		
	}
	
	private void setBalance(int newBalance)
	{
		balance = newBalance;
		shadeTooExpensiveAthletes();
		balanceAmmountLabel.setText("$" + NumberFormat.getIntegerInstance().format(balance));
	}
	
	private void resetPositionTag(PositionTagPanel position)
	{
		nullifyMapping(position.getType());
		
		Point posSlot = positionTagSlots.get(position).getLocation();
		Point posSlotParent = positionTagSlots.get(position).getParent().getLocation();
	    position.setLocation(posSlot.x + posSlotParent.x, posSlot.y + posSlotParent.y);
	}
	
	private void setupPositionPanelListeners(PositionTagPanel position)
	{
		
		position.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getContentPane().setComponentZOrder(position, 0);
	
				position.setOffset(e.getX(), e.getY());
				nullifyMapping(position.getType());

			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				int mouseX = position.getX() + e.getX();
				int mouseY = position.getY() + e.getY();
				

				for (AthleteSummaryPanel athlete : athleteOptions)
				{
			        int athleteX = athlete.getX() + athlete.getParent().getX();
			        int athleteY = athlete.getY() + athlete.getParent().getY();
					
				    boolean inXbounds = (mouseX >= athleteX) && (mouseX <= (athleteX + athlete.getWidth()));
				    boolean inYbounds = (mouseY >= athleteY) && (mouseY <= (athleteY + athlete.getHeight()));

					if (!inXbounds || !inYbounds) {continue;}
					if (athlete.getAthlete().getEstimatedCost() > balance) continue;
					
					for (PositionTagPanel positionOther : positionOptions)
					{
						if (positionMappings.get(positionOther.getType()) != athlete) continue;
						resetPositionTag(positionOther);
					}
					
					addMapping(position, athlete);
					getContentPane().setComponentZOrder(position, 0);
					return;
				}
				
				resetPositionTag(position);
				getContentPane().setComponentZOrder(position, 0);
				
				
			}
		});

		position.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int newX = position.getX() + e.getX() - position.getOffsetX();
				int newY = position.getY() + e.getY() - position.getOffsetY();
				
				int gX = position.getX() + position.getParent().getX();
				int gY = position.getY() + position.getParent().getY();
				
				if (gX < getX()) gX += getX() - gX;
				else if (gX > getX() + getWidth()) gX -= newX - (getX() + getWidth());
				
				if (gY < getY()) gY += getY() - gY;
				else if (gY > getY() + getHeight()) gY -= newY - (getY() + getHeight());
				
				position.setLocation(newX, newY);
				position.repaint();
				
			}
		});
		
		

	}
	
	private void nullifyMapping(PositionTypes type)
	{
		if (positionMappings.get(type) != null) {
			
			setBalance(positionMappings.get(type).getAthlete().getEstimatedCost() + balance);
			positionMappings.get(type).reset();
			
		}
		
		positionMappings.put(type, null);
		checkContinueCondition();
		shadeTooExpensiveAthletes();
	}
	
	private void createPositionPanels()
	{
		JPanel positions = new JPanel();
		positions.setBounds(10, 166, 224, 376);
		positionsPanel = positions;
		getContentPane().add(positions);
		FlowLayout layout = new FlowLayout();
		layout.setVgap(20);
		layout.setHgap(50);
		positions.setLayout(layout);
		
		
		positionTagSlots = new HashMap<PositionTagPanel, JPanel>();
		positionOptions = new ArrayList<PositionTagPanel>();
		

		for (PositionTypes type : PositionTypes.values())
		{
			
			JPanel positionTagSlot = new JPanel();
			positionTagSlot.setPreferredSize(new Dimension(150, 20));
			positionTagSlot.setLayout(new GridLayout());

			PositionTagPanel position = new PositionTagPanel(type);
		    position.setPreferredSize(new Dimension(150, 20));
		    position.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    
		    
		    positionTagSlot.add(position);
		    positions.add(positionTagSlot);
		    
		    positionOptions.add(position);
		    positionTagSlots.put(position, positionTagSlot);
		    
		    setupPositionPanelListeners(position);
		}
		
		
	}
	
	public TeamSetupFrame()
	{
		
		positionMappings = new EnumMap<PositionTypes, AthleteSummaryPanel>(PositionTypes.class);
		for(PositionTypes type : PositionTypes.values()) positionMappings.put(type, null);
		
		createAthletePanels();
		createPositionPanels();
		
		JPanel balancePanel = new JPanel();
		balancePanel.setBounds(254, 618, 485, 52);
		getContentPane().add(balancePanel);
		balancePanel.setLayout(null);
		
		JLabel balanceBanner = new JLabel("Current Balance: ");
		balanceBanner.setFont(new Font("Tahoma", Font.PLAIN, 20));
		balanceBanner.setHorizontalAlignment(SwingConstants.RIGHT);
		balanceBanner.setBounds(0, 0, 260, 52);
		balancePanel.add(balanceBanner);
		
		balanceAmmountLabel = new JLabel("$1,000");
		balanceAmmountLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		balanceAmmountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		balanceAmmountLabel.setBounds(270, 0, 215, 52);
		balancePanel.add(balanceAmmountLabel);
		
		setBalance(balance);
		
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(244, 53, 812, 39);
		getContentPane().add(titlePanel);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel titleLabel = new JLabel("Team Picker");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setBounds(244, 103, 943, 52);
		getContentPane().add(descriptionPanel);
		descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
		
		JTextArea descriptionText = new JTextArea();
		descriptionText.setBackground(UIManager.getColor("Button.background"));
		descriptionText.setEditable(false);
		descriptionText.setWrapStyleWord(true);
		descriptionText.setLineWrap(true);
		descriptionText.setText("Welcome to the Ice Hockey Team Picker. To draft athletes into your team simpily drag and drop the team positions (located on the left) onto your desired athlete. You are not required to draft athletes into the reverse positions.");
		descriptionPanel.add(descriptionText);
		
		
		JPanel continuePanel = new JPanel();
		continuePanel.setBounds(749, 618, 438, 52);
		getContentPane().add(continuePanel);
		continuePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton continueButton = new JButton("Continue");

		continueButton.setEnabled(false);
		this.continueButton = continueButton;
		continuePanel.add(continueButton);
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBounds(40, 53, 160, 102);
		getContentPane().add(optionsPanel);
		optionsPanel.setLayout(new GridLayout(2, 1, 0, 15));
		
		JButton backButton = new JButton("Back");

		optionsPanel.add(backButton);
		
		JButton resetPositionsButton = new JButton("Reset Positions");

		optionsPanel.add(resetPositionsButton);
		
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Application.app.getUserInterfaceState().changeFrameState(new GameSetupFrame());
				
			}
		});
		shadeTooExpensiveAthletes();
		
		resetPositionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (PositionTagPanel position : positionOptions) 
					{
						nullifyMapping(position.getType());
						getContentPane().remove(position);
					}
				getContentPane().remove(positionsPanel);
				
				createPositionPanels();
				
				getContentPane().repaint();
				getContentPane().revalidate();
			}
		});
		
		
		
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// SET TEAM POSITIONS TO ATHLETES
				for (PositionTypes type : PositionTypes.values())
				{
					if (positionMappings.get(type) == null)
						{
						Application.app.getGameState().getGamePlayer().getTeam().assignAthleteToPosition(type, null);
						
						}
					else
						{
						Application.app.getGameState().getGamePlayer().getTeam().assignAthleteToPosition(type, positionMappings.get(type).getAthlete());
						
						}
					
				}
				
				
				// SEND OVER REMANING MONEY
				Application.app.getGameState().getGamePlayer().setBalance(balance);
				
				// CHANGE FRAME
				Application.app.getUserInterfaceState().changeFrameState(new GameHubFrame());
				
			}
		});
		
		
	}

}
