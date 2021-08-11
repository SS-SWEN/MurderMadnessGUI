package swen225.murdermadness;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

import swen225.murdermadness.cards.*;
import swen225.murdermadness.view.Position;

/**
 * Represents the Player of a particular round
 */

public class Player {

	//TODO Redraw methods (Tile AND Player) could be put a separate class?
	public static final int TILE_WIDTH = 25;
	public static final int TILE_HEIGHT = 25;
	public static final int LEFT_PADDING= (getBoardWidth() - (24 * TILE_WIDTH))/2;
	public static final int TOP_PADDING = (getBoardHeight() - (24 * TILE_HEIGHT))/2;
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

	private final String userName;
	private final String name;
	private Position pos;
	private List<Position> prevPositions;
    private int stepsRemaining = 0;
    private Estate estate;
    
    private BufferedImage playerIcon;
    
    public Estate getEstate() {
    	return this.estate;
    }
    
    public void setEstate(Estate e) {
    	this.estate = e;
    }
    
	
	private List<Card> hand;
	private Set<Card> eliminations;
	public boolean inGame;
	private boolean skipping = false;

	/**
	 * @return A String containing the descriptions of all the cards in the current players hand
	 */
	public String showHand(){
		if(hand.isEmpty()) { return name+"'s hand is empty!"; }
		
		StringBuilder sb = new StringBuilder();
		sb.append(name+"'s hand"+"\n");
		for(Card c : hand) {
			sb.append(c.getDescription()).append(", ");
		}
		
		return sb.toString();
	}
	
	/**
	 * @return the hand of Cards that the Character holds
	 */
	public List<Card> getHand() {
		return Collections.unmodifiableList(hand);
	}
	
	/**
	 * @return the elimination sheet of Cards that the Character holds
	 */
	public Set<Card> getEliminations() {
		return Collections.unmodifiableSet(eliminations);
	}
	
	public List<Card> countRefutableCards(Card weapon, Card character, Card estate) {
		List<Card> collate = new ArrayList<Card>();
		for (Card c: this.hand) {
			if (c.equals(weapon) || c.equals(character) || c.equals(estate)) {
				collate.add(c);
			}
		}
		return collate;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUsername() {
		return this.userName;
	}
	
	public void setInGame(boolean status) {
		this.inGame = status;
	}
	
	public void addToHand(Card c) {
		hand.add(c);
	}
	
	public void addToEliminations(Card c) {
		eliminations.add(c);
	}
	
	public void setHand(List<Card> cards) {
		this.hand = cards;
	}
	
	public Player(String userName, String name) {
		this.name = name;
		this.userName = userName;
		hand = new ArrayList<Card>();
		eliminations = new HashSet<Card>();
		prevPositions = new ArrayList<Position>();
		inGame = true;
	}
	
	/**
     * Player movement Below --------------------------------------------------------------
     */
	
	public String toString() {
		return this.getName();
	}
	
	/**
     * Shorthand representation of this player for
     * displaying the player on the board
     */
    public String display() {
    	String s = this.name;
        return "" + s.charAt(0);
    }
    
    /**
     * Check whether this player still has steps
     */
    public boolean hasRemainingSteps() {
        return this.stepsRemaining > 0;
    }
    
    /**
     * Set the number of steps this player rolled on the die
     */
    public void setStepsRemaining(int steps) {
        this.stepsRemaining = steps;
    }
    
    public void decrementStep() {
    	this.stepsRemaining--;
    }
    
    /**
     * Get the amount of steps this player has remaining
     * @return the integer number of steps
     */
    public int getStepsRemaining() {
        return stepsRemaining;
    }
     
    public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public Position getPos() {
		return this.pos;
	}
	
	public List<Position> getPrevPos() {
		return this.prevPositions;
	}
	
	public void resetPrev() {
		this.prevPositions = new ArrayList<Position>();
	}
    
    /**
     * Updates the x and y coordinates of this player
     */
    public void updateLocation(Position nextPos) {
    	this.prevPositions.add(this.pos);
    	this.pos = nextPos;
    }

    public void skippingGuess(boolean skipping){
    	this.skipping = skipping;
	}

	public boolean isSkippingGuess(){
    	return skipping;
	}
    
    public void setImg(BufferedImage img) {
    	this.playerIcon = img;
    }

	public void redraw(Graphics2D g) {
		g.drawImage(playerIcon, (pos.getX()*TILE_WIDTH)+LEFT_PADDING,
				(pos.getY()*TILE_HEIGHT)-TOP_PADDING,
				TILE_WIDTH,
				TILE_HEIGHT,
				null);
		for(Position p: prevPositions){
			g.setPaint(Color.RED);
			g.drawLine((p.getX()*TILE_WIDTH)+LEFT_PADDING,
					(p.getY()*TILE_HEIGHT)-TOP_PADDING,
					((p.getX()*TILE_WIDTH)+LEFT_PADDING)+TILE_WIDTH,
					((p.getY()*TILE_HEIGHT)-TOP_PADDING)+TILE_HEIGHT);
		}
	}
}
