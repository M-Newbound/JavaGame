package com.view.panels;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.util.EnumStringMapping;
import com.model.athletes.Athlete;
import com.controller.enums.AttributeTypes;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.Font;


public class AthleteSummaryPanel extends JPanel {
	
	private ArrayList<JPanel> childPanels;
	private Athlete athlete;

	
	public  AthleteSummaryPanel(Athlete setupAthlete)
	{
		this(setupAthlete, false);
	}
	
	
	public Athlete getAthlete()
	{
		return athlete;
	}

	@SuppressWarnings("static-access")
	public AthleteSummaryPanel(Athlete setupAthlete, boolean showValue) {


		athlete = setupAthlete;
		childPanels = new ArrayList<JPanel>();	
		
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setBounds(0,0,150,175);
		
		setPreferredSize(new Dimension(150, 175));
		setLayout(null);
		
		if (athlete == null) return;
		
		JProgressBar staminaBar = new JProgressBar();
		staminaBar.setBounds(4, 32, 10, 116);
		staminaBar.setMaximum(athlete.getAttributeComponent().ATTRIBUTE_MAXIMUM);
		staminaBar.setValue(athlete.getAttributeComponent().getStamina());
		staminaBar.setOrientation(SwingConstants.VERTICAL);
		add(staminaBar);
		
		JLabel athleteNameLabel = new JLabel(athlete.getName());
		athleteNameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		athleteNameLabel.setBounds(4, 5, 145, 16);
		athleteNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(athleteNameLabel);
		
		JPanel athleteAttributePanel = new JPanel();
		athleteAttributePanel.setBackground(new Color(0,0,0,0));
		athleteAttributePanel.setBounds(24, 32, 118, 116);
		add(athleteAttributePanel);
		athleteAttributePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		childPanels.add(athleteAttributePanel);

		for (AttributeTypes type : AttributeTypes.values())
		{
			JPanel attribute = new JPanel();
			athleteAttributePanel.add(attribute);
			attribute.setLayout(new FlowLayout());
			attribute.setBackground(new Color(0,0,0,0));
			
			JLabel typeLabel = new JLabel(EnumStringMapping.attributeTypeToText(type));
			attribute.add(typeLabel);
			
			JLabel valueLabel = new JLabel(athlete.getAttributeComponent().getAttributeValue(type) + "%");
			valueLabel.setHorizontalAlignment(SwingConstants.TRAILING);;
			attribute.add(valueLabel);
			
//			childPanels.add(attribute);
		
		}
		
		JPanel athleteValuePanel = new JPanel();
		athleteValuePanel.setBackground(new Color(0, 0, 0, 0));
		athleteValuePanel.setBounds(19, 148, 121, 25);
		athleteValuePanel.setBorder(new LineBorder(Color.black, 1));
		add(athleteValuePanel);
		athleteValuePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		childPanels.add(athleteValuePanel);
		
		JLabel athleteCostBanner = new JLabel("Value:");
		athleteCostBanner.setFont(new Font("Tahoma", Font.BOLD, 14));
		athleteValuePanel.add(athleteCostBanner);
		
		JLabel athleteCostLabel = new JLabel("$" + NumberFormat.getIntegerInstance().format(athlete.getEstimatedCost()));
		athleteCostLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		athleteValuePanel.add(athleteCostLabel);

		if (!showValue) athleteValuePanel.setVisible(false);
		childPanels.add(athleteValuePanel);
;

	}
	
	public void reset()
	{
		colorIn(new Color(240, 240, 240));
	}
	
	public void colorIn(Color color)
	{
		this.setBackground(color);
		
//		for (JPanel panel : childPanels) panel.setBackground(color);
		
	}
	
}
