package swen225.murdermadness.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

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
				model.updateBoard(view.getGraphics());
			}
		});west.setEnabled(false);

		east = new JButton("\u2192");
		east.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				model.onPlayerMove(Direction.RIGHT);
				model.updateBoard(view.getGraphics());
			}
		});east.setEnabled(false);

		north = new JButton("\u2191");
		north.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				model.onPlayerMove(Direction.UP);
				model.updateBoard(view.getGraphics());
			}
		});north.setEnabled(false);

		south = new JButton("\u2193");
		south.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				model.onPlayerMove(Direction.DOWN);
				model.updateBoard(view.getGraphics());
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
				view.showHand();
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
		initKeyBindings();
	}

	/**
	 * Associate keys with actions using ActionMaps/InputMaps
	 */
	public void initKeyBindings(){
		ActionMap am = this.getActionMap();
		InputMap im = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

		// associate inputs w,s,a,d and keyboard arrows with directions
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),"VK_LEFT");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),"VK_RIGHT");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),"VK_UP");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),"VK_DOWN");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0),"VK_A");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0),"VK_D");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0),"VK_W");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0),"VK_S");

		// bind the directions with actions
		am.put("VK_LEFT", new bindKey("LEFT"));
		am.put("VK_RIGHT", new bindKey("RIGHT"));
		am.put("VK_UP", new bindKey("UP"));
		am.put("VK_DOWN", new bindKey("DOWN"));
		am.put("VK_A", new bindKey("LEFT"));
		am.put("VK_D", new bindKey("RIGHT"));
		am.put("VK_W", new bindKey("UP"));
		am.put("VK_S", new bindKey("DOWN"));
	}

	/**
	 * Binds a particular command key with an action, then the key may be pressed to perform the action
	 */
	private class bindKey extends AbstractAction{
		public bindKey(String action){
			putValue(ACTION_COMMAND_KEY, action);
		}
		@Override
		public void actionPerformed(ActionEvent ev) {
			if(!roll.isEnabled()) {
				switch (ev.getActionCommand()) {
					case ("LEFT"):
						if (west.isEnabled()) { model.onPlayerMove(Direction.LEFT); }
						break;
					case ("RIGHT"):
						if (east.isEnabled()) { model.onPlayerMove(Direction.RIGHT); }
						break;
					case ("UP"):
						if (north.isEnabled()) { model.onPlayerMove(Direction.UP); }
						break;
					case ("DOWN"):
						if (south.isEnabled()) { model.onPlayerMove(Direction.DOWN); }
						break;
				}
				model.updateBoard(view.getGraphics());
			}
		}
	}

}
