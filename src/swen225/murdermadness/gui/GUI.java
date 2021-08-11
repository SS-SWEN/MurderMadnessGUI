package swen225.murdermadness.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

import swen225.murdermadness.MurderMadness;
import swen225.murdermadness.Player;
import swen225.murdermadness.cards.Card;

/**
 * The main GUI which contains the board and all the players and other obects on the board
 * Main frame is split into panels that controls player action,
 * a panel to display eliminations and a panel to display the user HUD. Finally a menu is added to the frame, which allows
 * the player to quit or start a new game.
 */
public class GUI {

	private MurderMadness model;
	private GameSetupFrame setupFrame;
	private JFrame refuteAccuse;

	private JFrame frame;
	private ActionPanel actionControl;

	private JLabel playerTurnLabel;

	private JPanel drawing;
	private JPanel userHUD;

	private static final int DFLT_GUI_WIDTH = 900;
	private static final int DFLT_GUI_HEIGHT = 840;

	private static final int DFLT_DRAWING_WIDTH = 700;
	private static final int DFLT_DRAWING_HEIGHT = 600;

	private static Color BACKGROUND_COLOR = new Color(120, 116, 119);

	public GUI(MurderMadness model) {
		this.model = model;
		setupFrame = new GameSetupFrame(model);
	}

	/**
	 * Initialise the main GUI
	 */
    public void initMainGUI() {
    	
    	// Initialize Refute & Accuse Window
    	refuteAccuse = new JFrame();
    	refuteAccuse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	refuteAccuse.setResizable(false);
    	refuteAccuse.setSize(300,160);
    	refuteAccuse.setLocationRelativeTo(null);
    	
    	// Action Buttons
    	actionControl = new ActionPanel(model, this);

		playerTurnLabel = new JLabel();
		playerTurnLabel.setText("A Game of MurderMadness has Started!");
		playerTurnLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

		JLabel eliminationLabel = new JLabel();
		eliminationLabel.setText("Elimination Sheet");
		eliminationLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

		JPanel controls = new JPanel();
    	controls.setLayout(new BoxLayout(controls, BoxLayout.LINE_AXIS));

    	Border edge = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    	controls.setBorder(edge);

    	controls.setLayout(new GridLayout(1, 6));
    	controls.add(actionControl);

    	JPanel labels = new JPanel();
    	labels.setLayout(new BorderLayout());
    	labels.add(playerTurnLabel, BorderLayout.WEST);
    	labels.add(eliminationLabel, BorderLayout.EAST);

    	// Top Horizontal Row Panels
    	JSplitPane topPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    	topPanel.setEnabled(false);
    	topPanel.setLeftComponent(controls);
    	topPanel.setRightComponent(labels);

    	// Inner Board Graphics Pane
    	drawing = new JPanel() {
    		@Override
    		public void paintComponent(Graphics g) {
    			Graphics2D g2d = (Graphics2D) g;
    			model.updateBoard(g2d);
    		}
    	};
    	drawing.setPreferredSize(new Dimension(DFLT_DRAWING_WIDTH,DFLT_DRAWING_HEIGHT));

    	// Inner UserHud Graphics Pane
    	userHUD = new JPanel() {
    		@Override
    		public void paintComponent(Graphics g) {
    			Graphics2D g2d = (Graphics2D) g;
    		}
    	};

    	JSplitPane innerPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	innerPanel.setEnabled(false);
    	innerPanel.setTopComponent(drawing);
    	innerPanel.setBottomComponent(userHUD);

    	// Right Panel Elimination Sheet Graphics Pane
    	JPanel rightPanel = new JPanel() {
    		@Override
    		public void paintComponent(Graphics g) {
    			Graphics2D g2d = (Graphics2D) g;
    			model.updateElimination(g2d);
    		}
    	};
    	rightPanel.setBackground(BACKGROUND_COLOR);

    	JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    	split.setEnabled(false);
    	split.setBorder(BorderFactory.createEmptyBorder());
    	split.setLeftComponent(innerPanel);
    	split.setRightComponent(rightPanel);

    	frame = new JFrame("MurderMadness");
    	frame.setResizable(false);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLayout(new BorderLayout());
    	frame.add(topPanel, BorderLayout.NORTH);
    	frame.add(split, BorderLayout.CENTER);

    	JMenuBar menuBar = new JMenuBar();
    	JMenu menuTab = new JMenu("Menu");

   		JMenuItem newGameItem = new JMenuItem("New Game");
   		JMenuItem restartItem = new JMenuItem("Restart Game");
   		JMenuItem quitItem = new JMenuItem("Quit");

   		newGameItem.addActionListener(new ActionListener() {
   			public void actionPerformed(ActionEvent ev) {
   				setupFrame.dispose();
   				String[] options = { "Yes", "No" };
   				JPanel panel = new JPanel();
    			JLabel label = new JLabel("Are you sure you want to start a new game?");
   				panel.add(label);
   				int selection = JOptionPane.showOptionDialog(null, panel, "WARNING!", JOptionPane.DEFAULT_OPTION,
    					JOptionPane.WARNING_MESSAGE, null, options, options[1]);
   				if (selection == 0) {
   					setupFrame = new GameSetupFrame(model);
   					frame.dispose();
   				}
   			}
   		});

   		restartItem.addActionListener(ev -> {
			String[] options = { "Restart", "Cancel" };
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Are you sure you want to restart game?");
			panel.add(label);
			int selection = JOptionPane.showOptionDialog(null, panel, "WARNING!", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (selection == 0) {
				model.reset();
			}
		});

   		quitItem.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ev) {
   				String[] options = { "Exit", "Cancel" };
   				JPanel panel = new JPanel();
    			JLabel label = new JLabel("Are you sure you want to stop playing?");
   				panel.add(label);
   				int selection = JOptionPane.showOptionDialog(null, panel, "WARNING!", JOptionPane.DEFAULT_OPTION,
    					JOptionPane.WARNING_MESSAGE, null, options, options[1]);
   				if (selection == 0) {
    				System.exit(0);
   				}
   			}
   		});

   		menuTab.add(newGameItem);
   		menuTab.add(restartItem);
   		menuTab.add(quitItem);
   		menuBar.add(menuTab);

    	frame.setJMenuBar(menuBar);
    	frame.setSize(DFLT_GUI_WIDTH, DFLT_GUI_HEIGHT);
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
	}

	private List<String> dicePicPath = new ArrayList<String>(Arrays.asList("assets/dice_1.png",
			"assets/dice_2.png",
			"assets/dice_3.png",
			"assets/dice_4.png",
			"assets/dice_5.png",
			"assets/dice_6.png"));

	
	/*
	 * Rolls the dice
	 */
	public void roll() {
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		BufferedImage img3 = null;
		Graphics2D g = (Graphics2D)this.userHUD.getGraphics();
		g.clearRect(0, 0, userHUD.getWidth(), userHUD.getHeight()); // Clears the panel before drawing
		// Start Drawing the dice - Temporary of course
		int roll1 = (int) (((Math.random() * 6) + 1));
		int roll2 = (int) (((Math.random() * 6) + 1));
		int x = 0;
		for(int i = 0; i<200;i++) {
			x = i%6;
			img3 = model.grabAsset(dicePicPath.get(x));
			g.clearRect(0, 0, userHUD.getWidth(), userHUD.getHeight());
			g.drawImage(img3, 0, 0, 100, 100, null);
			g.drawImage(img3, 100, 0, 100, 100, null);
			g.drawString("Rolling......... ",220, 50);
		}
		switch(roll1) {
			case 1:
				img1 = model.grabAsset("assets/dice_1.png");
				break;
			case 2:
				img1 = model.grabAsset("assets/dice_2.png");
				break;
			case 3:
				img1 = model.grabAsset("assets/dice_3.png");
				break;
			case 4:
				img1 = model.grabAsset("assets/dice_4.png");
				break;
			case 5:
				img1 = model.grabAsset("assets/dice_5.png");
				break;
			case 6:
				img1 = model.grabAsset("assets/dice_6.png");
				break;
			default:
				// code block
		}
		switch(roll2) {
			case 1:
				img2 = model.grabAsset("assets/dice_1.png");
				break;
			case 2:
				img2 = model.grabAsset("assets/dice_2.png");
				break;
			case 3:
				img2 = model.grabAsset("assets/dice_3.png");
				break;
			case 4:
				img2 = model.grabAsset("assets/dice_4.png");
				break;
			case 5:
				img2 = model.grabAsset("assets/dice_5.png");
				break;
			case 6:
				img2 = model.grabAsset("assets/dice_6.png");
				break;
			default:
		}

		g.drawImage(img1, 0, 0, 100, 100, null);
		g.drawImage(img2, 100, 0, 100, 100, null);
		g.setColor(new Color(255,255,255));
		g.fillRect(200, 35, 100, 20);
		g.setColor(new Color(0,0,0));
		Font font = new Font("Helvetica", Font.BOLD, 15);
		g.setFont(font);
		g.drawString("Rolled "+((int)roll1+(int)roll2), 220, 50);
		model.setPlayerSteps(roll1+roll2);
		actionControl.setMove(true);
		actionControl.setRoll(false);
	}
    
    /*
     * Prompt Window for turns
     */
    public void onPlayerTurn(Player p) {
		userHUD.getGraphics().clearRect(0,0, userHUD.getWidth(), userHUD.getHeight());

		String[] options = {"Continue"};
		JPanel panel = new JPanel();
		JLabel label = new JLabel("It is currently "+p.getUsername()+"'s Turn as "+p.getName());
		panel.add(label);
		JOptionPane.showOptionDialog(null, panel, "CURRENT TURN!", JOptionPane.DEFAULT_OPTION,
		JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		this.setStatus(p.getUsername()+"'s Turn as "+p.getName());
		actionControl.setMove(false);
		actionControl.setRoll(true);
    }
    public void setStatus(String msg) {this.playerTurnLabel.setText(msg);}
    
    /*
     * Shows Player useful information
     */
    public void onPrompt(String title, String msg) {
    	String[] options = {"Continue"};
		JPanel panel = new JPanel();
		JLabel label = new JLabel(msg);
		panel.add(label);
		JOptionPane.showOptionDialog(null, panel, title, JOptionPane.DEFAULT_OPTION,
		JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }
    
    /*
     * Prompt to show when player enters an estate
     */
    public boolean onEstatePrompt(String title, String msg) {
    	String[] options = {"Refute", "Accuse", "Keep moving"};
    	JPanel panel = new JPanel();
    	JLabel label = new JLabel(msg);
    	panel.add(label);
    	int selection = JOptionPane.showOptionDialog(null, panel, title, JOptionPane.DEFAULT_OPTION, 
    	JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    	if (selection == 0) {
    		setStatus("You are currently Refuting");
    		setMode("Refute");
    		model.gatherPossibleCards();
    		model.triggerChoose();
    		return false;
    	} else if (selection == 1) {
    		setStatus("You are currently Accusing");
    		setMode("Accuse");
    		model.gatherPossibleCards();
    		model.triggerChoose();
    		return false;
    	}
    	return true;
    }


    public void checkLogic() {
    	if (refuteAccuse.getTitle().equals("Refute")) {
    		model.onRefute();
    	} else if (refuteAccuse.getTitle().equals("Accuse")) {
    		model.onAccuse();
    	}
    }

	/**
	 * Show cards on Refute & Accuse State
	 * @param cards
	 */
	public void showCards(List<Card> cards) {
		refuteAccuse.getContentPane().removeAll();
		refuteAccuse.repaint();
		refuteAccuse.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		ButtonGroup cardGroup = new ButtonGroup();
		for (Card c: cards) {
			ImageIcon icon = new ImageIcon(c.getCardImage());
			JRadioButton card = new JRadioButton();
			card.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
			    	String[] options = {"YES", "NO"};
			    	JPanel panel = new JPanel();
			    	JLabel label = new JLabel("Are you sure you want to pick "+c);
			    	panel.add(label);
			    	int selection = JOptionPane.showOptionDialog(null, panel, "Confirm Decision", JOptionPane.DEFAULT_OPTION, 
			    	JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			    	if (selection == 0) {
			    		model.setChosenCard(c);
			    		refuteAccuse.setVisible(false);
			    		model.triggerChoose();
			    	} else return;
				}
			});
			card.setIcon(icon);
			cardGroup.add(card);
			refuteAccuse.add(card);
		}
		
		refuteAccuse.pack();
		refuteAccuse.setVisible(true);
    }

    public void setMode(String mode) {this.refuteAccuse.setTitle(mode);}
    public String getMode() {return this.refuteAccuse.getTitle();}

    /*
     * Shows the hand of the current play.
     */
    public void showHand() {
    	Graphics2D g = (Graphics2D)this.userHUD.getGraphics();
    	g.clearRect(0, 0, userHUD.getWidth(), userHUD.getHeight()); // Clears the panel before drawing
    	Player p = model.getCurrentPlayer();
    	List<Card> hand = p.getHand();
    	int padding = 50;
    	
    	// Start drawing
    	for (Card c : hand) {
    		g.drawImage(c.getCardImage(), padding, 0, 100, 100, null);
    		padding += 110;
    	}
    }
    
    /*
     * Get drawing pane of the main GUI;
     */
    public Graphics2D getGraphics() {
    	return (Graphics2D)this.drawing.getGraphics();
    }
    
    public void disableGUI() {
    	this.setStatus("Game has Ended");
    	actionControl.setRoll(false);
    	actionControl.setMove(false);
    }
    
}
