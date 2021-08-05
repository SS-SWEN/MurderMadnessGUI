package swen225.murdermadness.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import swen225.murdermadness.MurderMadness;
import swen225.murdermadness.MurderMadness.Direction;

public class ActionPanel extends JPanel {

	private MurderMadness model;
	private GUI view;
	
	private JButton accuse, suggest, roll, hand;
	private JButton west, east, north, south;
	
	public ActionPanel(MurderMadness model, GUI view) {
		this.model = model;
		this.view = view;
		initialise();
	}
	
	public void enableMove(boolean status) {
		west.setEnabled(status);
		east.setEnabled(status);
		north.setEnabled(status);
		south.setEnabled(status);
	}
	
	public void enableRoll(boolean status) {
		roll.setEnabled(status);
	}
	
	public void initialise() {
		
		// Accuse Button
    	accuse = new JButton("Accuse");
		accuse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {	
				
			}
		});
		accuse.setEnabled(false);

		// Guess Button
		suggest = new JButton("Guess");
		suggest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

			}
		});
		suggest.setEnabled(false);
		
		// Direction
    	west = new JButton("\u2190");
		west.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				model.onPlayerMove(Direction.LEFT);
			}
		});west.setEnabled(false);
		east = new JButton("\u2192");
		east.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				model.onPlayerMove(Direction.RIGHT);
			}
		});east.setEnabled(false);
		north = new JButton("\u2191");
		north.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				model.onPlayerMove(Direction.UP);
			}
		});north.setEnabled(false);
		south = new JButton("\u2193");
		south.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				model.onPlayerMove(Direction.DOWN);
			}
		});south.setEnabled(false);
	
		// Roll Button
		roll = new JButton("Roll");
		roll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				view.roll();
			}
		});

		// Hand Button
		hand = new JButton("Hand");
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
