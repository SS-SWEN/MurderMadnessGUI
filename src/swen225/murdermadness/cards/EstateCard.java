package swen225.murdermadness.cards;
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

}
