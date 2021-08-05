package swen225.murdermadness.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

import swen225.murdermadness.MurderMadness;
import swen225.murdermadness.Player;

public class GUI {  

	private MurderMadness model;
	private GameSetupFrame setupFrame;
	
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
	
	
    public void initMainGUI() {

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
    			model.updateHUD(g2d);
    		}
    	};
    	
    	JSplitPane innerPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	innerPanel.setEnabled(false);
    	innerPanel.setTopComponent(drawing);
    	innerPanel.setBottomComponent(userHUD);

    	JPanel rightPanel = new JPanel();
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
   		menuTab.add(quitItem);
   		menuBar.add(menuTab);
   		
    	frame.setJMenuBar(menuBar);
    	frame.setSize(DFLT_GUI_WIDTH, DFLT_GUI_HEIGHT);
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
	}
    
    /*
     * Rolls the dice
     */
    public void roll() {
    	Animate.rollDice();
    	Graphics2D g = (Graphics2D)this.userHUD.getGraphics();
    	// Start Drawing the dice - Temporary of course
    	BufferedImage img = model.grabAsset("assets/dice-placeholder.jpg");
    	g.drawImage(img, 0, 0, 100, 100, null);
    	
    	
    	int roll = (int) (((Math.random() * 6) + 1) + ((Math.random() * 6) + 1));
    	model.setPlayerSteps(roll);
    	System.out.println("Rolled "+roll);
    	actionControl.enableMove(true);
    }
    
    /*
     * Prompt Window for turns
     */
    public void onPlayerTurn(Player p) {
		String[] options = {"Continue"};
		JPanel panel = new JPanel();
		JLabel label = new JLabel("It is currently "+p.getUsername()+"'s Turn as "+p.getName());
		panel.add(label);
		JOptionPane.showOptionDialog(null, panel, "CURRENT TURN!", JOptionPane.DEFAULT_OPTION,
		JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		this.playerTurnLabel.setText(p.getUsername()+"'s Turn as "+p.getName());
		actionControl.enableMove(false);
    }
    
    public void errorPrompt(String msg) {
		String[] options = {"Continue"};
		JPanel panel = new JPanel();
		JLabel label = new JLabel(msg);
		panel.add(label);
		JOptionPane.showOptionDialog(null, panel, "INFO", JOptionPane.DEFAULT_OPTION,
		JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }
    
    /*
     * Get drawing pane of the main GUI;
     */
    public Graphics2D getGraphics() {
    	return (Graphics2D)this.drawing.getGraphics();
    }
    
}
