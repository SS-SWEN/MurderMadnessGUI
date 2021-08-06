package swen225.murdermadness.cards;

import java.awt.image.BufferedImage;

public interface Card {
	
	/**
	 * @return a description of the corresponding card.
	 */
	public String getDescription();
	
	public BufferedImage getCardImage();
	
}
