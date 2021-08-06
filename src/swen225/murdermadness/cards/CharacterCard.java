package swen225.murdermadness.cards;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

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

	/*
	 * Gets the image of the card.
	 */
	@Override
	public BufferedImage getCardImage() {
		// TODO Auto-generated method stub
		try {
			BufferedImage img = ImageIO.read(new File("assets/"+character+"-card.png"));
			return img;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
}
