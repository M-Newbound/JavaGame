package com.view.panels;


import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.controller.enums.PositionTypes;
import com.util.EnumStringMapping;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.SwingConstants;
import java.awt.Font;

public class PositionTagPanel extends JPanel {

	
	private PositionTypes type;
	private JLabel text;

	public int offsetX = 0;
	public int offsetY = 0;
	
	public Point defaultLocation = new Point(0, 0);
	
	
 	public PositionTagPanel(PositionTypes type) {
 		setBackground(new Color(224, 220, 201));
		this.type = type;
		
		
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		setBounds(0, 0, 140, 20);
		
		JLabel lblNewLabel = new JLabel("ERROR");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		text = lblNewLabel;
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		setName();

	}
	
	private void setName()
	{
		text.setText(EnumStringMapping.positionTypeToText(type));
	}

	public PositionTypes getType()
	{
		return type;
	}
	
	public void setDefaultLocation(int x, int y)
	{
		this.defaultLocation = new Point(x, y);
	}
	
	public Point getDefaultLocation()
	{
		return defaultLocation;
	}
	
	public void setOffset(int x, int y)
	{
		offsetX = x;
		offsetY = y;
	}
	
	public int getOffsetX() {return offsetX;}
	public int getOffsetY() {return offsetY;}
}
