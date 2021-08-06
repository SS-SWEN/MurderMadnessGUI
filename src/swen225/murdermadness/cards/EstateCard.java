package swen225.murdermadness.cards;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import swen225.murdermadness.*;

/*
 * Card that corresponds to an Estate
 */

public class EstateCard implements Card  {
	
	private final Estate estate;
	
	public EstateCard(Estate estate) {
		this.estate = estate;
	}

	public Estate getEstate() {
		return this.estate;
	}
	
	@Override
	public String getDescription() {
		return "Estate: "+this.estate.getName();
	}
	
	@Override
	public String toString() {
		return "\""+this.estate.getName()+"\"";
	}
	
	/*
	 * Gets the image of the card.
	 */
	@Override
	public BufferedImage getCardImage() {
		// TODO Auto-generated method stub
		try {
			BufferedImage img = ImageIO.read(new File("assets/"+this.estate.getName()+"-card.png"));
			return img;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}

}
