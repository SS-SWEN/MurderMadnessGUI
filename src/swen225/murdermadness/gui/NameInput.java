package swen225.murdermadness.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NameInput extends JFrame {

		private JPanel panel = new JPanel();
		private JLabel label = new JLabel();
		private JTextField input = new JTextField("Enter name...",30);
		private String playerName = "";
		
		public NameInput() {
			setTitle("Enter Your Name");
			panel.add(input);
			setSize(400, 100);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			setVisible(true);
			
			input.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(input.getText().equals("Enter name...")) {
						input.setText("");
					}
				}
			});
			
			input.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = input.getText();
					if(name.equals(playerName)) { 
						label.setText("Name is already taken!"); return;
					}
					playerName = name;
					label.setText("Select "+name+"'s character");
				}
			});
			
			panel.add(label);
			add(panel);
		}
		
		public void resetDisplay() {
			input.setText("Enter name...");
			label.setText("");
		}
		
		
		public void displayRequire() {
			label.setText("Please input your name!");
		}
		
		public String getName() {
			return this.playerName;
		}
}
