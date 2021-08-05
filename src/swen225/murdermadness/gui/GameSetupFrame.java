package swen225.murdermadness.gui;

import java.awt.FlowLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import swen225.murdermadness.MurderMadness;

public class GameSetupFrame extends JFrame implements ActionListener {
	
	private MurderMadness model;
	
	private int players = 0;
	private Map<String, String> allPlayers;
	
	public GameSetupFrame(MurderMadness model) {
		this.model = model;
		initNumPlayers();
	}
	
	public void initNumPlayers() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("SETUP");
		this.setResizable(false);
		this.setSize(300,160);
		this.setLocationRelativeTo(null);
		
		JLabel welcomeMsg = new JLabel("Welcome to MurderMadness!");
		welcomeMsg.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JLabel promptMsg = new JLabel("How many players would like to play?");
		promptMsg.setFont(new Font("SansSerif", Font.ITALIC, 16));
		
		JButton helpBtn = new JButton("Help");
		helpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Help screen showing instructions.
			}
		});
		
		String[] options = {"2 Players","3 Players","4 Players"};
		JComboBox playerCount = new JComboBox(options);
		
		JButton confirmBtn = new JButton("Continue");
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = (String)playerCount.getSelectedItem();
				players = Integer.parseInt(num.replaceAll("\\D+", ""));
				System.out.println("Number of Players: "+players);
				initCharSelection();
			}
		});
		
		// Add Components to this Frame
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		this.add(welcomeMsg);
		this.add(promptMsg);
		this.add(helpBtn);
		this.add(playerCount);
		this.add(confirmBtn);	
		this.setVisible(true);
	}

	private int count;
	private JRadioButton lucillaChar;
	private JRadioButton bertChar;
	private JRadioButton melinaChar;
	private JRadioButton percyChar;
	private NameInput nameInput;
	
	public void initCharSelection() {
		count = 0;
		allPlayers = new HashMap<String, String>();
		
		this.setVisible(false);
		this.getContentPane().removeAll();
		this.repaint();
		this.setTitle("Character Selection: Player 1");
		
		// Create CharacterCard Radio Options
		// TODO: Images could potentially be a static global, so Player Classes can make reference to the images.
		ImageIcon lucillaImg = new ImageIcon("assets/card-placeholder.png"); 
		lucillaChar = new JRadioButton();
		lucillaChar.addActionListener(this);
		lucillaChar.setIcon(lucillaImg);
		
		ImageIcon bertImg = new ImageIcon("assets/card-placeholder.png");
		bertChar = new JRadioButton();
		bertChar.addActionListener(this);
		bertChar.setIcon(bertImg);
		
		ImageIcon melinaImg = new ImageIcon("assets/card-placeholder.png");
		melinaChar = new JRadioButton();
		melinaChar.addActionListener(this);
		melinaChar.setIcon(melinaImg);
		
		ImageIcon percy = new ImageIcon("assets/card-placeholder.png");
		percyChar = new JRadioButton();
		percyChar.addActionListener(this);
		percyChar.setIcon(percy);
	
		ButtonGroup characters = new ButtonGroup();
		characters.add(lucillaChar);
		characters.add(bertChar);
		characters.add(melinaChar);
		characters.add(percyChar);
		
		this.add(lucillaChar);
		this.add(bertChar);
		this.add(melinaChar);
		this.add(percyChar);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.nameInput = new NameInput();
		
		return;
	}
	
	/*
	 * Assign Character to Specific Player
	 */
	@Override
	public void actionPerformed(ActionEvent e) {	
		if(allPlayers.containsKey(nameInput.getName()) || nameInput.getName().equals("")) {
			nameInput.displayRequire();
			nameInput.toFront();
			return;
		}	
		
		if(e.getSource()==lucillaChar) {
			lucillaChar.setEnabled(false);
			allPlayers.put(nameInput.getName(), "Lucilla");
		}
		else if (e.getSource()==bertChar) {
			bertChar.setEnabled(false);
			allPlayers.put(nameInput.getName(), "Bert");
		}
		else if (e.getSource()==melinaChar) {
			melinaChar.setEnabled(false);
			allPlayers.put(nameInput.getName(), "Melina");
		}
		else if (e.getSource()==percyChar) {
			percyChar.setEnabled(false);
			allPlayers.put(nameInput.getName(), "Percy");
		}

		count++;
		this.setTitle("Character Selection: Player "+count);
		if (count == players) {
			this.setVisible(false);
			model.setup(allPlayers);
			this.dispose();
			nameInput.dispose();
			return;
		}
		
		nameInput.resetDisplay();
		nameInput.toFront();
	}
	
	public Map<String, String> getPlayers(){
		return this.allPlayers;
	}
}
