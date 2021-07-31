package swen225.murdermadness.view;

import java.awt.Graphics2D;
import java.lang.reflect.Field;



public interface Tile {
	
	public static final int TILE_WIDTH = 25;
	public static final int TILE_HEIGHT = 25;
	public static final int LEFT_PADDING= (getBoardWidth() - (24 * TILE_WIDTH))/2;
	public static final int TOP_PADDING = (getBoardHeight() - (24 * TILE_HEIGHT))/2;
	
	/**
	 * Gets the default width from the GUI class.
	 *
	 * @return int.
	 */
	static private int getBoardWidth() {
		try
	    {
	        Class<?> forName = Class.forName("swen225.murdermadness.gui.GUI");;

	        Field f = forName.getDeclaredField("DFLT_DRAWING_WIDTH");
	        Class<?> type = f.getType();
	        f.setAccessible(true);
	        return ((int) f.get(type));
	        
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
		 return 0;
	}
	
	
	/**
	 * Gets the default height from the GUI class.
	 *
	 * @return int.
	 */
	static private int getBoardHeight() {
		try
	    {
	        Class<?> forName = Class.forName("swen225.murdermadness.gui.GUI");

	        Field f = forName.getDeclaredField("DFLT_DRAWING_HEIGHT");
	        Class<?> type = f.getType();
	        f.setAccessible(true);
	        return ((int) f.get(type));
	        
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
		 return 0;
	}
	
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
	
	public void redraw(Graphics2D g);
	
}