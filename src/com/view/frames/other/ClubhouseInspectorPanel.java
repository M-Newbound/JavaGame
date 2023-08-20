package com.view.frames.other;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.controller.enums.AttributeTypes;
import com.model.items.GameItem;
import com.util.EnumStringMapping;
import com.view.panels.ItemPanel;

public class ClubhouseInspectorPanel extends JPanel {

	
	private JPanel basePanel;
	private ItemPanel itemSlot;
	private JPanel attributesPanel;
	private JLabel nameLabel;
	
	
	public ClubhouseInspectorPanel() {
		setLayout(null);
		
		basePanel = new JPanel();
		basePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		basePanel.setBounds(0, 0, 494, 125);
		basePanel.setPreferredSize(new Dimension(400, 400));
		
		
		itemSlot = new ItemPanel(null);
		itemSlot.setBounds(78, 11, 76, 76);
		
		attributesPanel = new JPanel();
		attributesPanel.setBounds(230, 11, 254, 103);
		
		add(basePanel);
		basePanel.setLayout(null);
		basePanel.add(itemSlot);
		basePanel.add(attributesPanel);
		
		nameLabel = new JLabel("No Item To Inspect");
		nameLabel.setBounds(15, 91, 205, 14);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		basePanel.add(nameLabel);
		
	}

	
	
	public void loadItem(GameItem item)
	{
		itemSlot.loadItem(item);
		itemSlot.revalidate();
		itemSlot.repaint();
		
		nameLabel.setText(item.getItemName());
		
		attributesPanel.removeAll();
		attributesPanel.setLayout(new BoxLayout(attributesPanel, BoxLayout.Y_AXIS));
		
		for (AttributeTypes attribute : AttributeTypes.values())
		{
			int effect = item.getAttrivuteEffects(attribute);
			String msg = EnumStringMapping.attributeTypeToText(attribute) + " " + Integer.toString(effect);
			
			JLabel attributeInfo = new JLabel(msg);
			attributeInfo.setHorizontalAlignment(SwingConstants.CENTER);
			attributesPanel.add(attributeInfo);
		}
	}

}
