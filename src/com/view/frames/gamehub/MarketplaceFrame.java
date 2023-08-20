package com.view.frames.gamehub;

import java.awt.Color;
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
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.controller.Application;
import com.controller.enums.PositionTypes;
import com.controller.world.GameMarket;
import com.model.athletes.Athlete;
import com.model.items.GameItem;
import com.view.frames.GameFrameBase;
import com.view.frames.other.MarketplaceInspectorPanel;
import com.view.panels.AthleteSummaryPanel;
import com.view.panels.ItemPanel;
import com.view.panels.PositionSummaryPanel;


public class MarketplaceFrame extends GameFrameBase {
	private HashMap<JButton, Integer> balanceDependantButtons = new HashMap<>();
	private ArrayList<ItemPanel> itemSlots = new ArrayList<>();
	
	private MarketplaceInspectorPanel inspector;
	private JTabbedPane marketTabbedPane;
	
	private JLabel balanceLabel;
	private JPanel currentFocus = null;
	
	private JPanel ghostPanel;
	private Point ghostPanelOffset = new Point(0, 0);
	
	public MarketplaceFrame() {
		
		getContentPane().setBackground(new Color(214, 241, 248));
		getContentPane().setLayout(null);
		
		JTabbedPane playerTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		playerTabbedPane.setBounds(62, 46, 525, 625);
		getContentPane().add(playerTabbedPane);

		
		setupPlayerItems(playerTabbedPane);
		setupPlayerAthletes(playerTabbedPane);
		
		
		marketTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		marketTabbedPane.setBounds(669, 46, 525, 625);
		getContentPane().add(marketTabbedPane);
		
		
		setupMarketItems(marketTabbedPane);
		setupMarketAthletes(marketTabbedPane);

		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Application.app.getUserInterfaceState().changeFrameState(new GameHubFrame());
				
			}
		});
		backButton.setBounds(989, 12, 205, 23);
		getContentPane().add(backButton);
		
		balanceLabel = new JLabel();
		balanceLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		balanceLabel.setBounds(62, 6, 238, 24);
		getContentPane().add(balanceLabel);
		updateBalanceLabel();
		
		JLabel titleLabel = new JLabel("Marketplace");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setBounds(686, 7, 306, 23);
		getContentPane().add(titleLabel);
		
		JPanel informationPanel = new JPanel();
		marketTabbedPane.addTab("Info", null, informationPanel, null);
		informationPanel.setLayout(null);
		
		inspector = new MarketplaceInspectorPanel();
		inspector.setBounds(59, 86, 410, 410);
		informationPanel.add(inspector);
		
		
		
		checkBalanceDependantButtons();

	}

	private void updateBalanceLabel()
	{
		int balance = Application.app.getGameState().getGamePlayer().getBalance();
		balanceLabel.setText("Balance: $" +  NumberFormat.getIntegerInstance().format(balance));
	}
	
	private void checkBalanceDependantButtons()
	{
		int balance = Application.app.getGameState().getGamePlayer().getBalance();
		for (Map.Entry<JButton, Integer> entry : balanceDependantButtons.entrySet()) {
            JButton button = entry.getKey();
            Integer minBal = entry.getValue();
            
            if (minBal > balance)
            {
            	button.setEnabled(false);
            }
            else
            {
            	button.setEnabled(true);
            }
            
        }
	}

	private void subtractFromBalance(int ammount)
	{
		int balance = Application.app.getGameState().getGamePlayer().getBalance();
		Application.app.getGameState().getGamePlayer().setBalance(balance - ammount);
		
		checkBalanceDependantButtons();
		updateBalanceLabel();

	}
	
 	private void setupPlayerItems(JTabbedPane playerTabbedPane)
	{
		
		
		JPanel playerItemsPanel = new JPanel();
		playerTabbedPane.addTab("Your Items", null, playerItemsPanel, null);
		playerItemsPanel.setLayout(null);
		
		JPanel itemSlotsPanel = new JPanel();
		itemSlotsPanel.setBounds(0, 150, 519, 446);
		playerItemsPanel.add(itemSlotsPanel);
		itemSlotsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextPane playerItemInstructions = new JTextPane();
		playerItemInstructions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		playerItemInstructions.setText("this is your item inventory. You can drag and drop items between inventory slots. Simpily right click to have the option of inspecting/selling an item.");
		playerItemInstructions.setBackground(UIManager.getColor("Button.background"));
		playerItemInstructions.setEditable(false);
		playerItemInstructions.setBounds(0, 0, 520, 150);
		playerItemsPanel.add(playerItemInstructions);
		
		for (int i=0; i < Application.app.getGameState().getGamePlayer().getInventory().getSize(); i++)
		{
			GameItem itemProfile = Application.app.getGameState().getGamePlayer().getInventory().getItemAtIndex(i);
			ItemPanel item = new ItemPanel(itemProfile, i);
			item.setPreferredSize(new Dimension(76, 76));
			setupItemListeners(item);
			itemSlotsPanel.add(item);
			itemSlots.add(item);
		}
		
	}

 	private void setupItemListeners(ItemPanel item)
 	{
 		
		item.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {

				if (SwingUtilities.isRightMouseButton(e))
				{
					if (item.getItem() == null) return;
					
	                JPopupMenu popupMenu = new JPopupMenu();
	                JMenuItem sellMenuItem = new JMenuItem("Sell");
	                JMenuItem inspectMenuItem = new JMenuItem("Inspect");
	                sellMenuItem.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {

	                    	
	                    	subtractFromBalance(-1 * item.getItem().getiCost());
	                    	Application.app.getGameState().getGamePlayer().getInventory().clearItemAtIndex(item.id);
		
	                    	item.loadItem(null);
	                    	item.revalidate();
	                    	item.repaint();
	                    	
	                    }
	                });
	                
	                inspectMenuItem.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {

	                    	inspectItem(item);
	                    	
	                    }
	                });
	                
	                
	                popupMenu.add(sellMenuItem);
	                popupMenu.add(inspectMenuItem);

	                popupMenu.show(e.getComponent(), e.getX(), e.getY());
	                return;
				}
				

           
            	if (e.getButton() != MouseEvent.BUTTON1) return;
            	
            	if (item.getItem() == null)
            	{
            		ghostPanel = null;
            		return;
            	}
            	
  
                ghostPanel = item.getImageCopy();

                getContentPane().add(ghostPanel);
                getContentPane().setComponentZOrder(ghostPanel, 0);
                ghostPanel.setVisible(true);
                
                ghostPanelOffset.x = e.getX() - item.getX();
                ghostPanelOffset.y = e.getY() - item.getY();
                
                Point startLocationLocal = new Point(e.getX() - ghostPanelOffset.x, e.getY() - ghostPanelOffset.y);       
                Point startLocation = SwingUtilities.convertPoint(item.getParent(), startLocationLocal, getContentPane());
                ghostPanel.setLocation(startLocation);
                
                
                ghostPanel.revalidate();
                ghostPanel.repaint();
                getContentPane().repaint();
                
            }
            
            
            public void mouseReleased(MouseEvent e) {
            	if (e.getButton() != MouseEvent.BUTTON1) return;
            	if (ghostPanel == null) return;
                
                getContentPane().remove(ghostPanel);
                ghostPanel = null;
                
                if (currentFocus != null && currentFocus instanceof ItemPanel)
                {
                	ItemPanel focus = (ItemPanel) currentFocus;
                	
                	Application.app.getGameState().getGamePlayer().getInventory().swapItemsAtIndexes(item.id, focus.id);
                	item.loadItem(Application.app.getGameState().getGamePlayer().getInventory().getItemAtIndex(item.id));
                	focus.loadItem(Application.app.getGameState().getGamePlayer().getInventory().getItemAtIndex(focus.id));
                
                	item.revalidate();
                	item.repaint();
                	
                	focus.revalidate();
                	focus.repaint();
                }
                
                getContentPane().repaint();
            }
            
            
            @Override
            public void mouseEntered(MouseEvent e) {
                currentFocus = item;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                currentFocus = null;
            }
		});
		
		item.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseDragged(MouseEvent e) {
            	
            	
		    	if (ghostPanel == null) return;
		    	
		        int newX = e.getX() - ghostPanelOffset.x;
		        int newY = e.getY() - ghostPanelOffset.y;
		        
		        Point newLocation = SwingUtilities.convertPoint(item.getParent(), new Point(newX, newY), getContentPane());

                ghostPanel.setLocation(newLocation);

                getContentPane().repaint();
		    }
		    
		    });

 	}
 	
	private void setupPlayerAthletes(JTabbedPane playerTabbedPane)
	{
		
		JPanel playerAthletesPanel = new JPanel();
		playerAthletesPanel.setPreferredSize(new Dimension(525, 850));
		
		JScrollPane playerAthletesScrollPane = new JScrollPane(playerAthletesPanel);
		playerAthletesPanel.setLayout(null);
		
		JTextPane playerAthletesInstructions = new JTextPane();
		playerAthletesInstructions.setEditable(false);
		playerAthletesInstructions.setBackground(UIManager.getColor("Button.background"));
		playerAthletesInstructions.setText("These are your currently employed athletes. To sell/draf back an athlete right click on them and select 'sell'. Atheletes can be also be purchased from the store.");
		playerAthletesInstructions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		playerAthletesInstructions.setBounds(0, 0, 520, 150);
		playerAthletesPanel.add(playerAthletesInstructions);
		
		JPanel playerAthletes = new JPanel();
		playerAthletes.setBounds(0, 150, 503, 700);
		playerAthletesPanel.add(playerAthletes);
		playerAthletes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		playerAthletesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		playerTabbedPane.addTab("Your Athletes", null, playerAthletesScrollPane, null);
		
		for (PositionTypes type : PositionTypes.values())
		{
			AthleteSummaryPanel athlete = new AthleteSummaryPanel(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(type), true);
			PositionSummaryPanel position = new PositionSummaryPanel(type, athlete);
			setupPlayerAthleteListeners(position);
			playerAthletes.add(position);
			
		}
	}

	private void setupPlayerAthleteListeners(PositionSummaryPanel position)
	{
		
		position.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (position.getAthletePanel().getAthlete() == null) return;
				if (!SwingUtilities.isRightMouseButton(e)) return;
				
                JPopupMenu popupMenu = new JPopupMenu();

                JMenuItem sellMenuItem = new JMenuItem("Sell");
                sellMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                    	
                    	subtractFromBalance(-1 * position.getAthletePanel().getAthlete().getEstimatedCost());
                    	Application.app.getGameState().getGamePlayer().getTeam().unassignAthleteAtPosition(position.getType());
	
                    	position.loadAthlete(new AthleteSummaryPanel(null));
                    	position.revalidate();
                    	position.repaint();
                    	
                    }
                });
                popupMenu.add(sellMenuItem);

                popupMenu.show(e.getComponent(), e.getX(), e.getY());
           
	        }
                
            @Override
            public void mouseEntered(MouseEvent e) {
                currentFocus = position;
            }
            
            public void mouseExited(MouseEvent e) {
                currentFocus = null;
            }
        });
	}
	
 	private void setupMarketItems(JTabbedPane marketTabbedPane)
	{
		JPanel marketItemsPanel = new JPanel();
		marketTabbedPane.addTab("Market Items", null, marketItemsPanel, null);
		marketItemsPanel.setLayout(null);
		
		JTextPane txtpnWelcomeToThe = new JTextPane();
		txtpnWelcomeToThe.setBounds(0, 0, 520, 150);
		txtpnWelcomeToThe.setText("Welcome to the Weekly Market. Each week a new supply of various items will be put up for sale, items are not guarenteed to be the same each week so spend with future weeks in mind!");
		txtpnWelcomeToThe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnWelcomeToThe.setEditable(false);
		txtpnWelcomeToThe.setBackground(UIManager.getColor("Button.background"));
		marketItemsPanel.add(txtpnWelcomeToThe);
		
		JPanel dailyItemsPanel = new JPanel();
		dailyItemsPanel.setBounds(0, 151, 520, 446);
		marketItemsPanel.add(dailyItemsPanel);
		dailyItemsPanel.setLayout(new FlowLayout());
		

		
		Color clear = new Color(0,0,0,0);
		
		for(int i=0; i<GameMarket.getItemsCount(); i++)
		{
			JPanel itemOffer = new JPanel();
			itemOffer.setBackground(clear);
			itemOffer.setPreferredSize(new Dimension(250, 120));
			itemOffer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			itemOffer.setLayout(new GridLayout(0, 2, 0, 0));
		
			dailyItemsPanel.add(itemOffer);
			
			GameItem itemProfile = Application.app.getGameState().getMarket().getItemInventory().getItemAtIndex(i);
			if (itemProfile == null)
			{
				constructItemNoStockPanel(itemOffer);
				continue;
			}
			

			JPanel lhs = new JPanel();
			lhs.setBackground(clear);
			itemOffer.add(lhs);
			lhs.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
			
			JPanel rhs = new JPanel();
			rhs.setBackground(clear);
			itemOffer.add(rhs);
			rhs.setLayout(new GridLayout(0, 1, 50, 25));
			
			ItemPanel marketItem = new ItemPanel(itemProfile, i);
			marketItem.setPreferredSize(new Dimension(76, 76));
			lhs.add(marketItem);
			
			JLabel itemName = new JLabel(itemProfile.getItemName());
			itemName.setPreferredSize(new Dimension(120, 10));
			itemName.setHorizontalAlignment(SwingConstants.CENTER);
			lhs.add(itemName);
			

			
			JButton purchaseButton = new JButton("Purchase");
			rhs.add(purchaseButton);
			
			balanceDependantButtons.put(purchaseButton, itemProfile.getiCost());

			
			purchaseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (Application.app.getGameState().getGamePlayer().getInventory().isFull())
					{
						JOptionPane.showMessageDialog(null, "Cannot add items to a full inventory", "Inventory Full", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					constructItemNoStockPanel(itemOffer);
					
					int toSlot = Application.app.getGameState().getGamePlayer().getInventory().getNextItemlessIndex();
					Application.app.getGameState().getGamePlayer().getInventory().setItemAtIndex(toSlot, itemProfile);
					itemSlots.get(toSlot).loadItem(itemProfile);
					Application.app.getGameState().getMarket().getItemInventory().clearItemAtIndex(marketItem.id);

					subtractFromBalance(itemProfile.getiCost());
					
					marketTabbedPane.revalidate();
					marketTabbedPane.repaint();
					
				}
			});
			
				
			JButton inspectButton = new JButton("Inspect");
			
	        inspectButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                inspectItem(marketItem);
	            }
	        });
			
			
			
			rhs.add(inspectButton);

		}
		
		
		
	}
 	
 	
 	private void inspectItem(ItemPanel item)
 	{
 		if (item.getItem() == null) return;
 		inspector.loadItem(item.getItem());
 		
 		marketTabbedPane.setSelectedIndex(2);
 	}
	
 	private void constructItemNoStockPanel(JPanel itemOffer)
 	{
 		itemOffer.removeAll();
 		itemOffer.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel notice = new JLabel("stall will restock next week");
		notice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		notice.setHorizontalAlignment(SwingConstants.CENTER);
		itemOffer.add(notice);
		
		itemOffer.revalidate();
		itemOffer.repaint();
 	}
 	
	private void constructAthleteNoStockPanel(JPanel athleteBuyOption)
	{
		athleteBuyOption.removeAll();
		
		JLabel notice = new JLabel("stall will restock next week");
		notice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		notice.setHorizontalAlignment(SwingConstants.CENTER);
		
		athleteBuyOption.setLayout(new GridLayout());
		athleteBuyOption.setBorder(new LineBorder(Color.black, 1));
		athleteBuyOption.add(notice);
		
		athleteBuyOption.revalidate();
		athleteBuyOption.repaint();
	}
	
	private void setupMarketAthletes(JTabbedPane marketTabbedPane)
	{
		JPanel marketAthletesPanel = new JPanel();
		marketTabbedPane.addTab("MarketAthletes", null, marketAthletesPanel, null);
		marketAthletesPanel.setLayout(new BoxLayout(marketAthletesPanel, BoxLayout.Y_AXIS));
		
		JLabel athleteInfoLabel = new JLabel("To purchase this athlete, drag and drop it into your athletes slots");
		marketAthletesPanel.add(athleteInfoLabel);
		
		for(int i=0; i<Application.app.getGameState().getMarket().getAthleteInventory().getSize(); i++)
		{
			final int index = i;
			
			JPanel athleteBuyOption = new JPanel();
			athleteBuyOption.setPreferredSize(new Dimension(550, 201));
			marketAthletesPanel.add(athleteBuyOption);
			
			
			Athlete athlete = Application.app.getGameState().getMarket().getAthleteInventory().getItemAtIndex(i);
			
			if (athlete == null)
			{
				constructAthleteNoStockPanel(athleteBuyOption);
				continue;
			}
			
			
			athleteBuyOption.setLayout(new FlowLayout());
			
			
			JPanel athleteSummaryContainer = new JPanel();
			athleteSummaryContainer.setBorder(new LineBorder(Color.black, 1));
			athleteSummaryContainer.setPreferredSize(new Dimension(150, 175));
			athleteSummaryContainer.setLayout(new GridLayout(1, 2));
			
			AthleteSummaryPanel athleteSummary = new AthleteSummaryPanel(athlete, true);
			
			athleteSummaryContainer.add(athleteSummary);
			athleteBuyOption.add(athleteSummaryContainer);
			
			athleteSummary.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e)
	            {
	            	if (!SwingUtilities.isLeftMouseButton(e)) return;
	                
	            	if (ghostPanel != null)
	            	{
		                getContentPane().remove(ghostPanel);
		                ghostPanel = null;

	            	}
	            	
	            	if (athleteSummary.getAthlete() == null)
	            	{
	            		ghostPanel = null;
	            		return;
	            	}
	            	

	                ghostPanel = new AthleteSummaryPanel(athleteSummary.getAthlete(), true);

	                getContentPane().add(ghostPanel);
	                getContentPane().setComponentZOrder(ghostPanel, 0);
	                ghostPanel.setVisible(true);
	                
	                ghostPanelOffset.x = e.getX() - athleteSummary.getX();
	                ghostPanelOffset.y = e.getY() - athleteSummary.getY();
	                
	                Point startLocationLocal = new Point(e.getX() - ghostPanelOffset.x, e.getY() - ghostPanelOffset.y);       
	                Point startLocation = SwingUtilities.convertPoint(athleteSummary.getParent(), startLocationLocal, getContentPane());
	                ghostPanel.setLocation(startLocation);
	                
	                
	                // specifics
	                athleteSummary.setVisible(false);
	      
	                
	                ghostPanel.revalidate();
	                ghostPanel.repaint();
	                getContentPane().repaint();

	            	
	            }
	            
	            @Override
	            public void mouseReleased(MouseEvent e) {
	            	
	            	
	            	if (ghostPanel != null)
	            	{
		                getContentPane().remove(ghostPanel);
		                getContentPane().repaint();
		                ghostPanel = null;
	            	}
	            	else return;
		                


	                athleteSummary.setVisible(true);
	                
	                if (!SwingUtilities.isLeftMouseButton(e)) return;
	                
	                if (athleteSummary.getAthlete().getEstimatedCost() > Application.app.getGameState().getGamePlayer().getBalance()) return;
	                
	                
	                if (currentFocus != null && currentFocus instanceof PositionSummaryPanel)
	                {
	                	
	                	PositionSummaryPanel slot = (PositionSummaryPanel) currentFocus;
	                	
	            
	                	if (slot.getAthletePanel().getAthlete() == null)
	                	{
                			Athlete athlete = athleteSummary.getAthlete();
                			Application.app.getGameState().getPlayerTeam().assignAthleteToPosition(slot.getType(), athlete);
                			AthleteSummaryPanel athleteSummary = new AthleteSummaryPanel(Application.app.getGameState().getGamePlayer().getTeam().findAthleteAtPosition(slot.getType()), true);
                			slot.loadAthlete(athleteSummary);
                			
                			slot.revalidate();
                			slot.repaint();
                			
                			Application.app.getGameState().getMarket().getAthleteInventory().clearItemAtIndex(index);
                			subtractFromBalance(athlete.getEstimatedCost());
                			
                			constructAthleteNoStockPanel(athleteBuyOption);
	                	}

                		
	                }
	                
	                
	                getContentPane().repaint();

	            }    

			});
			
			athleteSummary.addMouseMotionListener(new MouseMotionAdapter() {
			    @Override
			    public void mouseDragged(MouseEvent e) {
	            	if (!SwingUtilities.isLeftMouseButton(e)) return;
			    	
			    	if (ghostPanel == null) return;
			    	
			        int newX = e.getX() - ghostPanelOffset.x;
			        int newY = e.getY() - ghostPanelOffset.y;
			        
			        Point newLocation = SwingUtilities.convertPoint(athleteSummary.getParent(), new Point(newX, newY), getContentPane());

                    ghostPanel.setLocation(newLocation);

                    getContentPane().repaint();

			    }
			    
			    });
		}

	}

}
