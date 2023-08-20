package com.view.frames.gamehub;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import com.controller.Application;
import com.controller.enums.PositionTypes;
import com.view.panels.ItemPanel;
import com.view.panels.PositionSummaryPanel;
import com.view.frames.GameFrameBase;
import com.view.frames.other.ClubhouseInspectorPanel;
import com.view.panels.AthleteSummaryPanel;
import com.model.items.GameItem;
import javax.swing.SwingConstants;


//TODO: add the player's team name

public class ClubhouseFrame extends GameFrameBase {

	private JPanel ghostPanel;
	private Point ghostPanelOffset = new Point(0, 0);
	
	private JPanel currentFocus = null;
	private ClubhouseInspectorPanel inspector;
	JTabbedPane infoTabbedPane;
	
	public ClubhouseFrame() {
		getContentPane().setLayout(null);
		
		
		setupInventoryPanel();
		setupPositionsPanel();
				

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application.app.getUserInterfaceState().changeFrameState(new GameHubFrame());
				
			}
		});
		backButton.setBounds(29, 16, 124, 29);
		getContentPane().add(backButton);
		
		JLabel Title = new JLabel("Clubhouse");
		Title.setFont(new Font("Tahoma", Font.BOLD, 20));
		Title.setBounds(199, 9, 343, 34);
		getContentPane().add(Title);
		
		infoTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		infoTabbedPane.setBounds(10, 486, 543, 184);
		getContentPane().add(infoTabbedPane);
		
		JPanel instructionsPanel = new JPanel();
		infoTabbedPane.addTab("Instructions", null, instructionsPanel, null);
		instructionsPanel.setLayout(null);
		
		JPanel itemInformationPanel = new JPanel();
		infoTabbedPane.addTab("Info", null, itemInformationPanel, null);
		
		inspector = new ClubhouseInspectorPanel();
		inspector.setPreferredSize(new Dimension(500, 130));
		itemInformationPanel.add(inspector);
		
		JLabel teamNameLabel = new JLabel("TEAM " + Application.app.getGameState().getPlayerTeam().getTeamName());
		teamNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		teamNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameLabel.setBounds(701, 0, 498, 45);
		getContentPane().add(teamNameLabel);


	}

	private void setupInventoryPanel()
	{
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBounds(20, 56, 522, 419);
		getContentPane().add(inventoryPanel);
		inventoryPanel.setLayout(new FlowLayout());

		for (int i=0; i < Application.app.getGameState().getGamePlayer().getInventory().getSize(); i++)
		{
			GameItem item = Application.app.getGameState().getGamePlayer().getInventory().getItemAtIndex(i);
			ItemPanel itemPanel = new ItemPanel(item, i);
			itemPanel.setPreferredSize(new Dimension(76, 76));
			inventoryPanel.add(itemPanel);
			
			setupItemListeners(itemPanel);	
		}
	}
	
	private void setupPositionsPanel()
	{
		JPanel positionsPanel = new JPanel();
		positionsPanel.setBounds(701, 56, 505, 614);
		positionsPanel.setLayout(new FlowLayout());
		getContentPane().add(positionsPanel);
				
		for (PositionTypes type : PositionTypes.values())
		{
			AthleteSummaryPanel athlete = new AthleteSummaryPanel(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(type));
			PositionSummaryPanel position = new PositionSummaryPanel(type, athlete);
			setupPositionListeners(position);
			
			 positionsPanel.add(position);	
		}	
	}
	
	private void setupItemListeners(ItemPanel itemPanel)
	{
		itemPanel.addMouseListener(new MouseAdapter() {
			
            @Override
            public void mouseEntered(MouseEvent e) {
                currentFocus = itemPanel;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                currentFocus = null;
            }
            
            @Override
            public void mousePressed(MouseEvent e)
            {

				if (SwingUtilities.isRightMouseButton(e))
				{
					if (itemPanel.getItem() == null) return;
					
	                JPopupMenu popupMenu = new JPopupMenu();
	                JMenuItem inspectMenuItem = new JMenuItem("Inspect");
	                
	                inspectMenuItem.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {

	                    	inspector.loadItem(itemPanel.getItem());
	                    	infoTabbedPane.setSelectedIndex(1);
	                    	
	                    }
	                });
	                
	                popupMenu.add(inspectMenuItem);

	                popupMenu.show(e.getComponent(), e.getX(), e.getY());
	                return;
				}
            	
            	
            	if (e.getButton() != MouseEvent.BUTTON1) return;
            	if (itemPanel.getItem() == null) {ghostPanel = null; return;}
            	
                ghostPanel = itemPanel.getImageCopy();

                getContentPane().add(ghostPanel);
                getContentPane().setComponentZOrder(ghostPanel, 0);
                ghostPanel.setVisible(true);
                
                ghostPanelOffset.x = e.getX() - itemPanel.getX();
                ghostPanelOffset.y = e.getY() - itemPanel.getY();
                
                Point localLocation = new Point(e.getX() - ghostPanelOffset.x, e.getY() - ghostPanelOffset.y);       
                Point constentPaneLocation = SwingUtilities.convertPoint(itemPanel.getParent(), localLocation, getContentPane());
                ghostPanel.setLocation(constentPaneLocation);
                
                ghostPanel.revalidate();
                ghostPanel.repaint();
                getContentPane().repaint();
                
            }
                  
            public void mouseReleased(MouseEvent e) {
            	if (e.getButton() != MouseEvent.BUTTON1) return;
            	if (ghostPanel == null) return;
                
                getContentPane().remove(ghostPanel);
                ghostPanel = null;
                

                if (currentFocus != null && currentFocus instanceof PositionSummaryPanel)
                {
                	PositionSummaryPanel focus = (PositionSummaryPanel) currentFocus;

                	if (focus.getAthletePanel().getAthlete() != null)
                	{

	                	focus.getAthletePanel().getAthlete().getAttributeComponent().addMappings(itemPanel.getItem().getPositiveAttributeMapping());
	                	focus.getAthletePanel().getAthlete().getAttributeComponent().subtractMappings(itemPanel.getItem().getNegativeAttributeMapping());
	                	
	                	Application.app.getGameState().getGamePlayer().getInventory().clearItemAtIndex(itemPanel.id);
	                	itemPanel.loadItem(null);
	                
	                	itemPanel.revalidate();
	                	itemPanel.repaint();
	                	
	                	AthleteSummaryPanel athlete = new AthleteSummaryPanel(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(focus.getType()));
	                	focus.loadAthlete(athlete);
	                	focus.revalidate();
	                	focus.repaint();
                	}
                	
                }
                
                else if (currentFocus != null && currentFocus instanceof ItemPanel)
                {
                	ItemPanel focus = (ItemPanel) currentFocus;
                	
                	Application.app.getGameState().getGamePlayer().getInventory().swapItemsAtIndexes(itemPanel.id, focus.id);
                	itemPanel.loadItem(Application.app.getGameState().getGamePlayer().getInventory().getItemAtIndex(itemPanel.id));
                	focus.loadItem(Application.app.getGameState().getGamePlayer().getInventory().getItemAtIndex(focus.id));
                
                	itemPanel.revalidate();
                	itemPanel.repaint();
                	
                	focus.revalidate();
                	focus.repaint();
                }
                
                getContentPane().repaint();
            }
            
            

		});
		
		itemPanel.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseDragged(MouseEvent e) {
            	
            	
		    	if (ghostPanel == null) return;
		    	
		        int newX = e.getX() - ghostPanelOffset.x;
		        int newY = e.getY() - ghostPanelOffset.y;
		        
		        Point newLocation = SwingUtilities.convertPoint(itemPanel.getParent(), new Point(newX, newY), getContentPane());

                ghostPanel.setLocation(newLocation);

                getContentPane().repaint();
		    }
		    
		    });

	}

	private void setupPositionListeners(PositionSummaryPanel position)
	{
		position.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
            	
            	if (position.getAthletePanel().isVisible() == false || position.getAthletePanel().getAthlete() == null)
            	{
            		ghostPanel = null;
            		return;
            	}
            	
  
                ghostPanel = new AthleteSummaryPanel(position.getAthletePanel().getAthlete());

                getContentPane().add(ghostPanel);
                getContentPane().setComponentZOrder(ghostPanel, 0);
                ghostPanel.setVisible(true);
                
                ghostPanelOffset.x = e.getX() - position.getX();
                ghostPanelOffset.y = e.getY() - position.getY();
                
                Point startLocationLocal = new Point(e.getX() - ghostPanelOffset.x, e.getY() - ghostPanelOffset.y);       
                Point startLocation = SwingUtilities.convertPoint(position.getParent(), startLocationLocal, getContentPane());
                ghostPanel.setLocation(startLocation);
                
                position.getAthletePanel().setVisible(false);
      
                ghostPanel.revalidate();
                ghostPanel.repaint();
                getContentPane().repaint();
                
            }
            
            public void mouseReleased(MouseEvent e) {
            	if (ghostPanel == null) return;
                
                getContentPane().remove(ghostPanel);
                ghostPanel = null;

                position.getAthletePanel().setVisible(true);
                
                
                if (currentFocus != null && currentFocus instanceof PositionSummaryPanel)
                {
                	PositionSummaryPanel other = (PositionSummaryPanel) currentFocus;
                	
                	PositionTypes otherPosition = other.getType();
                	PositionTypes thisPosition = position.getType();
                	
                	Application.app.getGameState().getGamePlayer().getTeam().swapPositionAssignments(otherPosition, thisPosition);
                	
                	AthleteSummaryPanel thisAthlete = new AthleteSummaryPanel(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(position.getType()));
                	AthleteSummaryPanel otherAthlete = new AthleteSummaryPanel(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(other.getType()));
                	
                	position.loadAthlete(thisAthlete);
                	other.loadAthlete(otherAthlete);
                	
                	position.revalidate();
                	position.repaint();
                	other.revalidate();
                	other.repaint();
                }
                
                
                getContentPane().repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                currentFocus = position;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                currentFocus = null;
            }
		});
		
		position.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseDragged(MouseEvent e) {
		    	if (ghostPanel == null) return;
		    	
		        int newX = e.getX() - ghostPanelOffset.x;
		        int newY = e.getY() - ghostPanelOffset.y;
		        
		        Point newLocation = SwingUtilities.convertPoint(position.getParent(), new Point(newX, newY), getContentPane());

                ghostPanel.setLocation(newLocation);

                getContentPane().repaint();
		    }
		    
		    });
	}

}
