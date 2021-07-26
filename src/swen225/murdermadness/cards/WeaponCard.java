package swen225.murdermadness.cards;

/**
 * Card corresponding to a potential murder weapon
 * @author grantrona
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
	
}
