package swen225.murdermadness.gui;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * JTextField Window that lets a user input their name into the text field to be used in
 * the game
 *
 */
public class NameInput extends JFrame {

		private JPanel panel = new JPanel();
		private JLabel label = new JLabel();
		private JTextField input = new JTextField("Enter name...",30);
		private JButton selectButton = new JButton("Select");
		private String playerName = "";
		private List<String> usedNames = new ArrayList<String>();
		
		public NameInput() {
			setTitle("Enter Your Name");
			panel.add(input);
			setSize(400, 100);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			// clear default text when text field is clicked on
			input.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(input.getText().equals("Enter name...")) {
						input.setText("");
					}
				}
			});

			/**
			 * clear default text when a key is pressed
			 */
			input.addKeyListener(new KeyListener(){
				@Override
				public void keyTyped(KeyEvent e) { }
				@Override
				public void keyPressed(KeyEvent e) {
					if(input.getText().equals("Enter name...")) {
						input.setText("");
					}
				}
				@Override
				public void keyReleased(KeyEvent e) { }
			});
			
			// let user input name by pressing 'enter' on the keyboard
			input.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = input.getText();
					if(usedNames.contains(name)) {
						label.setText("Name is already taken!"); return;
					}
					usedNames.add(name);
					playerName = name;
					label.setText("Select "+name+"'s character");
					selectButton.setVisible(false);
				}
			});
			
			// let user input name by Select on the window
			selectButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = input.getText();
					if(usedNames.contains(name)) {
						label.setText("Name is already taken!"); return;
					}
					usedNames.add(name);
					playerName = name;
					label.setText("Select "+name+"'s character");
					selectButton.setVisible(false);
				}
			});
			
			panel.add(selectButton);
			panel.add(label);
			add(panel);
			setVisible(true);
		}
		
		/**
		 * Reset the display default state
		 */
		public void resetDisplay() {
			input.setText("Enter name...");
			label.setText("");
			selectButton.setVisible(true);
		}
		
		/**
		 * Display if player does not enter a name
		 */
		public void displayRequire() {
			label.setText("Please input your name!");
		}
		
		public String getName() {
			return this.playerName;
		}
}
