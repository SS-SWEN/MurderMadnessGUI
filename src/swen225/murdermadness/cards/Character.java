package swen225.murdermadness.cards;

import swen225.murdermadness.view.Position;

/**
 * Character which is controlled by a Player, contains a unique name, its current position on the board
 * and the card in the Players hand
 * @author grantrona
 *
 */

public class Character {
	
	final private String name;
	private Position pos;
	private Position prevPos;
	
	public Character(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	//getters and setters
	
	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public void setPrevPos(Position prev) {
		this.prevPos = prev;
	}
	
	public Position getPos() {
		return this.pos;
	}
	
	public Position getPrevPos() {
		return this.prevPos;
	}
}