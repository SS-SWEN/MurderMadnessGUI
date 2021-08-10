package swen225.murdermadness.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import swen225.murdermadness.MurderMadness;
import swen225.murdermadness.cards.Card;

public class RefuteAccuseFrame extends JFrame {
	
	private MurderMadness model;
	
	public RefuteAccuseFrame(MurderMadness model) {
		this.model = model;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(300,160);
		this.setLocationRelativeTo(null);
	}

	public void showCards(List<Card> cards) {
		this.getContentPane().removeAll();
		this.repaint();
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

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
			    		resetVisiblity();
			    		model.triggerChoose();
			    	} else return;
				}
			});
			card.setIcon(icon);
			cardGroup.add(card);
			this.add(card);
		}
		
		this.pack();
		this.setVisible(true);
	}
	
	public void resetVisiblity() {this.setVisible(false);}
}
