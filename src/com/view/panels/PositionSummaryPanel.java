package com.view.panels;

import javax.swing.JPanel;

import com.controller.Application;
import com.controller.enums.PositionTypes;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;

public class PositionSummaryPanel extends JPanel {

	
	private PositionTypes type;
	private AthleteSummaryPanel athlete;
	private PositionTagPanel position;
	private JPanel internalPanel;
	
	public PositionTypes getType()
	{
		return type;
	}
	
	public AthleteSummaryPanel getAthletePanel()
	{
		return athlete;
	}
	
	public void loadAthlete(AthleteSummaryPanel athlete)
	{
		this.athlete = athlete;
		
		internalPanel.removeAll();

		//athlete = new AthleteSummaryPanel(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(type), showValue);

		if (athlete.getAthlete() == null) athlete.setVisible(false);

		position = new PositionTagPanel(type);
		position.setPreferredSize(new Dimension(150, 20));
		
		internalPanel.add(athlete);
		internalPanel.add(position);
	}

	/**
	 * Create the panel.
	 */
	public PositionSummaryPanel(PositionTypes positionType, AthleteSummaryPanel athlete) {
		this.type = positionType;
		this.athlete = athlete;
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		internalPanel = new JPanel();
		internalPanel.setPreferredSize(new Dimension(150, 200));
		internalPanel.setLayout(new BoxLayout(internalPanel, BoxLayout.Y_AXIS));
		add(internalPanel);
		
		loadAthlete(athlete);		
	}


}
