package swen225.murdermadness.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
	private ArrayList<String> newPlayers;
	
	public GameSetupFrame(MurderMadness model) {
		this.model = model;
		initNumPlayers();
	}
	
	public void initNumPlayers() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("SETUP");
		this.setResizable(false);
		this.setSize(300,130);
		this.setLocationRelativeTo(null);
		
		JLabel welcomeMsg = new JLabel("Welcome to MurderMadness!");
		
		String[] options = {"One Player","Two Players","Three Players","Four Players"};
		JComboBox playerCount = new JComboBox(options);
		
		JButton confirmBtn = new JButton("Continue");
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				players = playerCount.getSelectedIndex()+1;
				System.out.println("Number of Players: "+players);
				initCharSelection();
			}
		});
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		this.add(welcomeMsg);
		this.add(playerCount);
		this.add(confirmBtn);
		this.setVisible(true);
	}

	private int count;
	private JRadioButton lucillaChar;
	private JRadioButton bertChar;
	private JRadioButton melinaChar;
	private JRadioButton percyChar;
	
	public void initCharSelection() {
		count = 1;
		newPlayers = new ArrayList<String>();
		
		this.setVisible(false);
		this.getContentPane().removeAll();
		this.repaint();
		this.setTitle("Character Selection: Player 1");
		
		// Create CharacterCard Radio Options
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
		return;
	}
	
	/*
	 * Assign Character to Specific Player
	 */
	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource()==lucillaChar) {
			lucillaChar.setEnabled(false);
			newPlayers.add("Lucilla");
		}
		else if (e.getSource()==bertChar) {
			bertChar.setEnabled(false);
			newPlayers.add("Bert");
		}
		else if (e.getSource()==melinaChar) {
			melinaChar.setEnabled(false);
			newPlayers.add("Melina");
		}
		else if (e.getSource()==percyChar) {
			percyChar.setEnabled(false);
			newPlayers.add("Percy");
		}
		
		count++;
		this.setTitle("Character Selection: Player "+count);
		if (count > players) {
			this.setVisible(false);
			model.setup(newPlayers);
			this.dispose();
			return;
		}
	}
	
	public ArrayList<String> getPlayers() {
		return this.newPlayers;
	}
}