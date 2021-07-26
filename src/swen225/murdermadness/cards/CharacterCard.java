package swen225.murdermadness.cards;

import swen225.murdermadness.Player;

/**
 * Represents a Card for corresponding to a character
 * @author grantrona
 *
 */
public class CharacterCard implements Card{
	
	private final String character;
	private final Player player;
	
	public CharacterCard(String character, Player player) {
		this.character = character;
		this.player = player;
	}
	
	public String getCharacter() {
		return this.character;
	}

	public Player getPlayer() {
		return this.player;
	}
	
	@Override
	public String getDescription() {
		return "Character: "+ this.character;
	}
	
	@Override
	public String toString() {
		return "\""+this.character+"\"";
	}
}
