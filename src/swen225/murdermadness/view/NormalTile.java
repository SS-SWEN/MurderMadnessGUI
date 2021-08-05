package swen225.murdermadness.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import swen225.murdermadness.Player;

public class NormalTile implements Tile{
	
	/**
	 * Stores the Position of the Tile in the board.
	 */
	private Position normalTilePosition;
	public String character;
	public Player player;
	
	/**
	 * Construct the tile on board
	 *
	 * @param p Position of tile.
	 */
	public NormalTile(Position p , String c) {
		this.normalTilePosition = p;
		this.character = c;
	}

	public String getCharacter() {
		return character;
		
	}

	/**
	 * Sets the character of the tile.
	 *
	 *@param c Character of the tile in the board.
	 */

	public void setCharacter(String c) {
		this.character = c;
	}
	
	public String getPlayerName(Player p) {
		return p.getName();
	}

	public void setPlayer(Player p) {
		this.player = p;
		
	}

	
	/**
	 * Gets the position of the tile.
	 *
	 * @return Position.
	 */
	public Position getPosition() {
		return this.normalTilePosition;
	}
	
	/**
	 * Sets the position of the tile.
	 *
	 *@param p Position in the board.
	 */
	public void setPosition(Position p) {
		this.normalTilePosition = p;
	}

	@Override 
	public boolean isObstruction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getPos() {
		return this.normalTilePosition;
	}

	public boolean containsCharacter(){
		return player == null;
	}


	/**
	 * Draws 2D tile on board.
	 *
	 * @param
	 */
	@Override
	public void redraw(Graphics2D g) {
		g.setPaint(Color.WHITE);
		g.fillRect((normalTilePosition.getX()*TILE_WIDTH)+LEFT_PADDING, (normalTilePosition.getY()*TILE_HEIGHT)-TOP_PADDING, TILE_WIDTH, TILE_HEIGHT);
		g.setPaint(Color.BLACK);
		g.drawRect((normalTilePosition.getX()*TILE_WIDTH)+LEFT_PADDING, (normalTilePosition.getY()*TILE_HEIGHT)-TOP_PADDING, TILE_WIDTH, TILE_HEIGHT);
	}
	
	
	

}