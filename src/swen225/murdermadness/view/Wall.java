package swen225.murdermadness.view;

public class Wall implements Tile{
	
	/**
	 * Stores the Position of the Tile in the board.
	 */
	private Position wallPosition;
	public String character;
	
	/**
	 * Construct the tile on board
	 *
	 * @param p Position of tile
	 */
	public Wall(Position p , String c) {
		this.wallPosition = p;
		this.character = c;
	}
	
	public String getCharacter() {
		return character;
		
	}
	
	
	/**
	 * Gets the position of the tile.
	 *
	 * @return Position.
	 */
	public Position getPosition() {
		return this.wallPosition;
	}
	
	/**
	 * Sets the position of the tile.
	 *
	 */
	public void setPosition(Position p) {
		this.wallPosition = p;
	}
	
	@Override
	public boolean isObstruction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return this.wallPosition;
	}

	@Override
	public void setCharacter(String character) {
		// TODO Auto-generated method stub
		
	}

}