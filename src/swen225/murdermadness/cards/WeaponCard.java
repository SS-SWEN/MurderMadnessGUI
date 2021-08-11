package swen225.murdermadness.cards;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Card corresponding to a potential murder weapon
 */

public class WeaponCard implements Card{
	
	private final String weaponName;
	
	public WeaponCard(String weaponName) {
		this.weaponName = weaponName;
	}

	@Override
	public String getDescription() {
		return "Weapon: "+this.weaponName;
	}
	
	@Override
	public String toString() {
		return "\""+this.weaponName+"\"";
	}
	
	/*
	 * Gets the image of the card.
	 */
	@Override
	public BufferedImage getCardImage() {
		try {
			BufferedImage img = ImageIO.read(new File("assets/"+weaponName+"-card.png"));
			return img;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
}
