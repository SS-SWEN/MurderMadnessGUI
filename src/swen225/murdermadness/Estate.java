package swen225.murdermadness;
import java.awt.*;
import java.util.ArrayList;

import java.util.List;

import swen225.murdermadness.cards.WeaponCard;
import swen225.murdermadness.view.Board;
import swen225.murdermadness.view.Position;


/**
 * Represents an Estate on the board. Contains a list of Weapons that are also within the Estate
 */
public class Estate {
	private final String estateName;
	public List<WeaponCard> weapons = new ArrayList<WeaponCard>();
	
	// maximum x,y value, represents the top left of the estate 
	private Position topLeft;
	// maximum x,y value, represents the bottom right of the estate
	private Position botRight;
	
	/**
	 * Check if a Position in within the Estate (within its walls)
	 */
	public boolean within(Position pos) {
		return pos.getX() >= topLeft.getX() 
				&& pos.getX() <= botRight.getX()
				&& pos.getY() >= topLeft.getY() 
				&& pos.getY() <= botRight.getY();
	}
	
	public String getName() {
		return this.estateName;
	}
	
	public Estate(String estateName, Position topLeft, Position botRight) {
		this.estateName = estateName;
		this.topLeft = topLeft;
		this.botRight = botRight;
	}
	
	/**
	 * Find the next position within the estate which is free
	 */
	public Position nextAvailablePosition(Board board) {
		for(int x = topLeft.getX()+1; x < botRight.getX(); x++) {
			for(int y = topLeft.getY()+1; y < botRight.getY(); y++) {
				Position pos = new Position(x, y);
				if(!board.getTile(pos).isObstruction() && board.getTile(pos).getCharacter().equals(".")) { return pos; }
			}
		}
		return null;
	}
	
	public void addWeapon(WeaponCard weapon) {
		this.weapons.add(weapon);
	}
	
	public boolean hasThisWeapon(WeaponCard weapon) {
		return this.weapons.contains(weapon);
	}
	
	public void removeWeapon(WeaponCard weapon) {
		this.weapons.remove(weapon);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Estate) { 
			Estate e = (Estate)o;
			return this.estateName.equals(e.estateName)
					&& this.weapons.equals(e.weapons)
					&& this.topLeft.equals(e.topLeft)
					&& this.botRight.equals(e.botRight);
		} 
		return false;
	}

	public void redraw(Graphics2D g){

	}
}
