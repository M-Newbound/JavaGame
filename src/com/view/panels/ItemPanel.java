package com.view.panels;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;


import com.model.items.GameItem;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.awt.Font;

public class ItemPanel extends JPanel {

	private static Color IN_FOCUS_COLOR = new Color(190, 180, 180);
	private static Color OUT_OF_FOCUS_COLOR = new Color(215, 210, 210);
	
	
	private GameItem item;
	public final int id;

	
	private JPanel pricePanel;
	private JPanel itemImagePanel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;

	public GameItem getItem()
	{
		return item;
	}
	
	public ItemPanel(GameItem item)
	{
		this(item, -1);
	}
	
	public JPanel getImageCopy()
	{
		JPanel imgPanel = new JPanel();
		imgPanel.setBounds(0, 0, 75, 75);
		imgPanel.setLayout(new GridLayout(0, 1, 0, 0));
		imgPanel.setBackground(new Color(0, 0, 0, 0));

		JLabel img = new JLabel("");
		img.setFont(new Font("Tahoma", Font.PLAIN, 9));
		img.setHorizontalAlignment(SwingConstants.CENTER);
		imgPanel.add(img);

		try
		{
			img.setIcon(new ImageIcon(ItemPanel.class.getResource(item.getFilePath())));
		}
		catch (Exception e)
		{
//				e.printStackTrace();
			img.setText("ITEM IMAGE ERROR : " + item.getItemName());
		}
		
		return imgPanel;
		
	}
	
	public void loadItem(GameItem item)
	{
		this.item = item;
		
		if (item == null)
		{
			lblNewLabel_1.setVisible(false);
			lblNewLabel.setVisible(false);
		}
		else
		{
			lblNewLabel_1.setVisible(true);
			lblNewLabel.setVisible(true);
			try
			{
				lblNewLabel.setIcon(new ImageIcon(ItemPanel.class.getResource(item.getFilePath())));
				lblNewLabel_1.setText("$" + NumberFormat.getIntegerInstance().format(item.getiCost()));
			}
			catch (Exception e)
			{
//				e.printStackTrace();
				lblNewLabel.setText("ITEM IMAGE ERROR : " + item.getItemName());
			}
		}
		
		revalidate();
		repaint();
	}
	
	public ItemPanel(GameItem item, int id) {
		
		this.id = id;
		this.item = item;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(ItemPanel.OUT_OF_FOCUS_COLOR);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(ItemPanel.IN_FOCUS_COLOR);
			}
		});

		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBackground(ItemPanel.OUT_OF_FOCUS_COLOR);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		pricePanel = new JPanel();
		pricePanel.setBounds(0, 55, 75, 20);
		add(pricePanel);
		pricePanel.setLayout(new GridLayout(0, 1, 0, 0));
		pricePanel.setBackground(new Color(0, 0, 0, 0));
		
		itemImagePanel = new JPanel();
		itemImagePanel.setBounds(0, 0, 75, 75);
		add(itemImagePanel);
		itemImagePanel.setLayout(new GridLayout(0, 1, 0, 0));
		itemImagePanel.setBackground(new Color(0, 0, 0, 0));
		
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		pricePanel.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		itemImagePanel.add(lblNewLabel);

		if (item == null)
		{
			lblNewLabel_1.setVisible(false);
			lblNewLabel.setVisible(false);
		}
		else
		{
			try
			{
				lblNewLabel.setIcon(new ImageIcon(ItemPanel.class.getResource(item.getFilePath())));
				lblNewLabel_1.setText("$" + NumberFormat.getIntegerInstance().format(item.getiCost()));
			}
			catch (Exception e)
			{
//				e.printStackTrace();
				lblNewLabel.setText("ITEM IMAGE ERROR : " + item.getItemName());
			}
		}
		
	}
}
