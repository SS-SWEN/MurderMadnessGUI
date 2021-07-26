package swen225.murdermadness.view;

import java.util.List;

import swen225.murdermadness.cards.Card;

/*
 * Helper Class to display formatted string to the console
 */
public final class Display {
	
	public static String capitalize(String input) {return input.substring(0, 1).toUpperCase() + input.substring(1);}
	
	public static void displayPossibleCards(List<Card> possibleCards) {
		StringBuilder display = new StringBuilder();
		display.append("  ");
		display.append(possibleCards.get(0)).append(" ");
		for (int i = 1 ; i < possibleCards.size(); i++) {
			display.append("  ");
			display.append(possibleCards.get(i)).append(" ");
		}
		System.out.println("Possible Cards: \n"+display);
	}
}
