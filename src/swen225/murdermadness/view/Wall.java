package swen225.murdermadness.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * A Tile representing a pillar or a wall of an estate. Are obstructions the player cannot
 * walk through
 */
public class Wall implements Tile{
	
	/**
	 * Stores the Position of the Tile in the board.
	 */
	private Position wallPosition;
	private String character;
	
	
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
	
	/**
	 * Draws 2D tile on board.
	 *
	 * @param Graphics2D.
	 */
	@Override
	public void redraw(Graphics2D g) {
		// TODO Auto-generated method stub

		
		
		g.setPaint(new Color(131, 104,83));
		g.fillRect((wallPosition.getX()*TILE_WIDTH)+LEFT_PADDING, (wallPosition.getY()*TILE_HEIGHT)-TOP_PADDING, TILE_WIDTH, TILE_HEIGHT);
		g.setPaint(Color.BLACK);
		g.drawRect((wallPosition.getX()*TILE_WIDTH)+LEFT_PADDING, (wallPosition.getY()*TILE_HEIGHT)-TOP_PADDING, TILE_WIDTH, TILE_HEIGHT);
	}

}