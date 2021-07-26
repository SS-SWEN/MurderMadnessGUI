package swen225.murdermadness.view;

public interface Tile {

	/**
	 * Check whether this tile is an obstruction which prevents the character from
	 * moving. For example, walls prevent the character from moving, if the tile (excluding Estates) is occupied.
	 *
	 * @return True if this tile does obstruct movement.
	 */
	public boolean isObstruction(); 
	
	public Position getPos();
	
	String getCharacter();
	
	String getPlayer();

	public void setCharacter(String character);
	
}