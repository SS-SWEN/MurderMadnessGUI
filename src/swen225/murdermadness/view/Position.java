package swen225.murdermadness.view;

import swen225.murdermadness.Player;

/**
 * An x,y position on the board
 */
public class Position {
	
	private int x;
	private int y;
	
	public int getX() { return x; }

	public int getY() { return y; }
	
	public boolean isValid() {
		return x >= 0 && x <= 23 && y >= 0 && y <= 23;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Position) { 
			Position p = (Position)o;
			return (this.getX() == p.getX() && this.getY() == p.getY());
		} 
		return false;
	}

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int xySum() {
		return this.getX()+this.getY();
	}
	
	public String toString() {
		return "("+this.x+","+this.y+")";
	}
	
	//player movement --------------------
	/**
     * Not null if a player moves onto, or is on this tile
     */
    private Player player = null;
	
	/**
     * Set the current player on this tile, null if un-occupied
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
		
		
	
}
