package swen225.murdermadness.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import swen225.murdermadness.MurderMadness;

public class ActionPanel extends JPanel {

	private GUI view;
	
	public ActionPanel(GUI view) {
		this.view = view;
		initialise();
	}
	
	public void initialise() {
		
		// Accuse Button
    	JButton accuse = new JButton("Accuse");
		accuse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {	
				
			}
		});
		accuse.setEnabled(false);

		// Guess Button
		JButton suggest = new JButton("Guess");
		suggest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

			}
		});
		suggest.setEnabled(false);
		
		// Direction
    	JButton west = new JButton("\u2190");
		west.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

			}
		});
		JButton east = new JButton("\u2192");
		east.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

			}
		});
		JButton north = new JButton("\u2191");
		north.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

			}
		});
		JButton south = new JButton("\u2193");
		south.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

			}
		});
	
		// Roll Button
		JButton roll = new JButton("Roll");
		roll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				view.roll();
			}
		});

		// Hand Button
		JButton hand = new JButton("Hand");
		hand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

			}
		});

		
		this.setMaximumSize(new Dimension(150, 60));
		this.setLayout(new GridLayout(2, 4));
		this.add(suggest);
		this.add(roll);
		this.add(north);
		this.add(hand);
		this.add(accuse);
		this.add(west);
		this.add(south);
		this.add(east);
	}
}
