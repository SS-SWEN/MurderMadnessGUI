package swen225.murdermadness;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;

import swen225.murdermadness.cards.*;
import swen225.murdermadness.view.Position;

/**
 * Represent the Player of a particular round
 * @author grantrona
 */

public class Player {
	
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
    
    public void setImg(BufferedImage img) {
    	this.playerIcon = img;
    }
    
	public void redraw(Graphics g) {
		g.drawImage(playerIcon, 50, 50, null);
	}

}
