package swen225.murdermadness.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;





public class GUI implements Runnable{  

	private JFrame frame;
	private JPanel controls;
	private JComponent drawing; 
	private JTextArea textOutputArea;


	private static final int DEFAULT_DRAWING_HEIGHT = 400;
	private static final int DEFAULT_DRAWING_WIDTH = 600;
	public static final int BOARD_WIDTH = 24, BOARD_HEIGHT = 20;
	private static final int TEXT_OUTPUT_ROWS = 5;
	
	int x = 0;
	int y = 0;
	int z = 0;
	public static Color BACKGROUND_COLOR = new Color(120, 116, 119);
	public static Color dice;
	
	private static final int BUFFER_SIZE = 10;
	
	public static String diceRoll = "";
	public static String dice1 = "";
	public static String dice2 = "";
	
	
	public void update(){
		int m = 5;
		int r1 = (int) (Math.random()*m);
		int r2 = (int) (Math.random()*m);
		int r3 = (int) (Math.random()*m);
		if(this.x>250) {
			x=0;
		}
		if(this.y>250) {
			y=0;
		}
		if(this.z>250) {
			z=0;
		}
		this.x+=r1;
		this.y+=r2;
		this.z+=r3;
		
		
	}
	
	@Override
    public void run() {
        while (true){
            update();
            redraw();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	
	
	

	protected void redraw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
	
		g2d.setColor(BACKGROUND_COLOR);//colour of the entire drawing space
		g2d.fillRect(0,0 , DEFAULT_DRAWING_WIDTH, DEFAULT_DRAWING_HEIGHT);
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		g2d.setColor(dice);
		
		dice = new Color(x, y, z);
			switch (diceRoll) {
			case "null":
				break;
			case "-1":
				break;
			default:
				g2d.drawString(diceRoll, BOARD_WIDTH * 13 - BUFFER_SIZE / 4,
						BOARD_HEIGHT * 13);
				switch (dice1) {
				case "1":
					g2d.fillOval(BOARD_WIDTH * 13 - BUFFER_SIZE / 4, BOARD_HEIGHT * 10 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					break;
				case "2":
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 11 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 9 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					break;
				case "3":
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 11 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 13 - BUFFER_SIZE / 4, BOARD_HEIGHT * 10 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 9 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					break;
				case "4":
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 11 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 9 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 11 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 9 + BUFFER_SIZE / 4,
						    BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					break;
				case "5":
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 11 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 9 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 13 - BUFFER_SIZE / 4, BOARD_HEIGHT * 10 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 11 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 9 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					break;
				case "6":
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 11 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 10 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 9 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 11 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 10 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 9 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2,BOARD_WIDTH / 2);
					break;
				}

				switch (dice2) {
				case "1":
					g2d.fillOval(BOARD_WIDTH * 13 - BUFFER_SIZE / 4, BOARD_HEIGHT * 14 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);

					g2d.fillOval(BOARD_WIDTH * 13 - BUFFER_SIZE / 4, BOARD_HEIGHT * 10 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					break;
				case "2":
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 15 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 13 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					break;
				case "3":
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 15 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 13 - BUFFER_SIZE / 4, BOARD_HEIGHT * 14 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 13 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					break;
				case "4":
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 15 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 13 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 15 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 13 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					break;
				case "5":
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 15 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 13 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 13 - BUFFER_SIZE / 4, BOARD_HEIGHT * 14 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 15 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 13 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					break;
				case "6":
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 15 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 14 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 14 - BUFFER_SIZE / 4, BOARD_HEIGHT * 13 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 15 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 14 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					g2d.fillOval(BOARD_WIDTH * 12 - BUFFER_SIZE / 4, BOARD_HEIGHT * 13 + BUFFER_SIZE / 4,
							BOARD_WIDTH / 2, BOARD_WIDTH / 2);
					break;
				}
				break;
			}
		}
		
		
	public void redraw() {
		frame.repaint();
	}

	GUI () {
		initialise();
	}
	
	/**
     * Rolls the two dice and sets the players remaining steps
     */
    public void rollDice() {
    	int roll1 = ((int)(Math.random() * 6) + 1) ;
    	int roll2 = ((int)(Math.random() * 6) + 1) ;
    	int rollsAdded = roll1 + roll2;
    	diceRoll = String.valueOf(roll1+ roll2);
    	dice1 = String.valueOf(roll1);
    	dice2 = String.valueOf(roll2);
    	redraw();
    }
	



        private void initialise() {
        	JButton accuse = new JButton("accuse");
    		accuse.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ev) {
    				
    				

    			}
    		});

    		JButton suggest = new JButton("suggest");
    		suggest.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ev) {

    			}
    		});
        	JButton west = new JButton("\u2190");
    		west.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ev) {

    			}
    		});

    		JButton east = new JButton("\u2192");
    		east.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ev) {

    			}
    		});

    		JButton north = new JButton("\u2191");
    		north.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ev) {

    			}
    		});

    		JButton south = new JButton("\u2193");
    		south.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ev) {

    			}
    		});
    		
    		

    		
    		// roll button
    		JButton roll = new JButton("Roll");
    		roll.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				rollDice();
    			}
    		});
    		

    		JButton hand = new JButton("Hand");
    		hand.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ev) {

    			}
    		});

    		controls = new JPanel();
    		controls.setLayout(new BoxLayout(controls, BoxLayout.LINE_AXIS));

    		Border edge = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    		controls.setBorder(edge);


    		JPanel navigation = new JPanel();
    		navigation.setMaximumSize(new Dimension(150, 60));
    		navigation.setLayout(new GridLayout(2, 4));
    		navigation.add(suggest);
    		navigation.add(roll);
    		navigation.add(north);
    		navigation.add(hand);
    		navigation.add(accuse);
    		navigation.add(west);
    		navigation.add(south);
    		navigation.add(east);
    		controls.add(navigation);
    		controls.add(Box.createRigidArea(new Dimension(15, 0)));
    		controls.add(Box.createHorizontalGlue());


    		drawing = new JComponent() {
				protected void paintComponent(Graphics g) {
    				redraw(g);
    			}
    		};

    		drawing.setPreferredSize(new Dimension(DEFAULT_DRAWING_WIDTH,
    				DEFAULT_DRAWING_HEIGHT));

    		drawing.setVisible(true);

    		textOutputArea = new JTextArea(TEXT_OUTPUT_ROWS, 0);
    		textOutputArea.setLineWrap(true);
    		textOutputArea.setWrapStyleWord(true); // pretty line wrap.
    		textOutputArea.setEditable(false);
    		JScrollPane scroll = new JScrollPane(textOutputArea);
    		// these two lines make the JScrollPane always scroll to the bottom when
    		// text is appended to the JTextArea.
    		DefaultCaret caret = (DefaultCaret) textOutputArea.getCaret();
    		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);


    		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    		split.setDividerSize(5); // make the selectable area smaller
    		split.setContinuousLayout(true); // make the panes resize nicely
    		split.setResizeWeight(1); // always give extra space to drawings
    		// JSplitPanes have a default border that makes an ugly row of pixels at
    		// the top, remove it.
    		split.setBorder(BorderFactory.createEmptyBorder());
    		split.setTopComponent(drawing);
    		split.setBottomComponent(scroll);

    		frame = new JFrame("CLUEDO");
    		// this makes the program actually quit when the frame's close button is
    		// pressed.
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		frame.setLayout(new BorderLayout());
    		frame.add(controls, BorderLayout.NORTH);
    		frame.add(split, BorderLayout.CENTER);
    		
    		// Menu bar
    		JMenuBar menuBar = new JMenuBar();
    		JMenu menuTab = new JMenu("Game");

    		JMenuItem newGameItem = new JMenuItem("New Game");
    		JMenuItem quitItem = new JMenuItem("Quit");
    		
    		newGameItem.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ev) {
    				
    			}
    		});
    		
    		quitItem.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ev) {
    				String[] options = { "Close", "Cancel" };
    				JPanel panel = new JPanel();
    				JLabel label = new JLabel("Are you sure you want to exit?");
    				panel.add(label);
    				int selection = JOptionPane.showOptionDialog(null, panel, "Warning!", JOptionPane.DEFAULT_OPTION,
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

    		// always do these two things last, in this order.
    		frame.pack();
    		frame.setVisible(true);
    		
    		

	}


    		

		public static void main(String args[])  
        {  
         new GUI();
         Thread t1 = new Thread(new GUI());
         t1.start();
         
        }


		 
    }
