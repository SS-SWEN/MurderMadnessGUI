package swen225.murdermadness;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Random;

import java.util.Set;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import swen225.murdermadness.cards.Card;
import swen225.murdermadness.cards.CharacterCard;
import swen225.murdermadness.cards.EstateCard;
import swen225.murdermadness.cards.WeaponCard;
import swen225.murdermadness.gui.GUI;
import swen225.murdermadness.view.*;

/*
 * MurderMadness 
 * - Main class for all the logic in the game.
 */
public class MurderMadness {
	
	private static List<String> characterNames = new ArrayList<String>(Arrays.asList("Lucilla", "Bert",
            "Melina", "Percy")); // this also indicates the order of turns
	
	private List<String> weaponNames = new ArrayList<String>(Arrays.asList("Broom", "Scissors",
            "Knife", "Shovel", "Ipad"));
	
	private List<String> estateNames = new ArrayList<String>(Arrays.asList("Haunted House", "Manic Manor", 
			"Villa Celia", "Calamity Castle", "Peril Palace"));
	
	private boolean isOngoing;
	public static enum Direction {UP, RIGHT, DOWN, LEFT}
	
	private static Board board;
	private static GUI view;
	
	private Set<Card> murderSolution;
	private Map<String, Card> allCards;
	private List<Player> players;
	private int currentPlayer;
	
    public MurderMadness() {
    	isOngoing = true;
    	board = new Board(this);
    	view = new GUI(this);
    }
    
    /**
     * Setup the initial game (Players, Board and Cards)
     */
    public void setup(Map<String, String> playerList) {
    	if (playerList.isEmpty()) return;
    	view.initMainGUI();
    	players = new ArrayList<Player>();
    	for (Entry<String, String> et: playerList.entrySet()) {
    		Player p = new Player(et.getKey(), et.getValue());
    		players.add(p);
    		p.setImg(this.grabAsset("assets/player-placeholder.png"));
			switch(et.getValue()){
				case("Lucilla"):
					p.setPos(new Position(11,1));
					break;
				case("Percy"):
					p.setPos(new Position(22,14));
					break;
				case("Malina"):
					p.setPos(new Position(9,22));
					break;
				case("Bert"):
					p.setPos(new Position(1,9));
					break;
			}
    	}
    	currentPlayer = 0;
    	initializeCards();
    	view.onPlayerTurn(players.get(currentPlayer));
    }
    
    /*
     * Redraws the Board
     */
    public void updateBoard(Graphics2D g) {
    	board.show(g);
    }
    
    public boolean endOfCycle() {
    	if (currentPlayer == players.size()) {
    		// Cycled through all the players
    		currentPlayer = 0;
    		return true;
    	}
    	return false;
    }

    
    /**
	 * Ask the player which direction to move. Player is then
	 * moved across the board according to input
     */
    public void onPlayerMove(Direction dir) {
    	Player p = players.get(currentPlayer);
    	if (!p.hasRemainingSteps()) {
    		if (dir != null) view.onPrompt("INFO", "You have run out of Steps!");
    		else view.onPrompt("INFO", "Your turn has ended!");
    		p.resetPrev();

    		currentPlayer++; // Player cannot move, go next player
    		endOfCycle(); // Check if Cycle ended
    		view.onPlayerTurn(players.get(currentPlayer));
    		return;
    	}
		switch (dir) {
	        case UP:
	            board.movePlayer(p, Direction.UP, 1);
	            break;
	        case RIGHT:
	            board.movePlayer(p, Direction.RIGHT, 1);
	            break;
	        case DOWN:
	            board.movePlayer(p, Direction.DOWN, 1);
	            break;
	        case LEFT:
	            board.movePlayer(p, Direction.LEFT, 1);
	            break;
	        default:
	            break;
		}
		
		for (Card card : allCards.values()) {
			if(card instanceof EstateCard) {
				EstateCard es = (EstateCard)card;
				if(es.getEstate().within(p.getPos())) {
					p.setEstate(es.getEstate());
					view.onEstatePrompt("ESTATE PROMPT", "You have entered the "+es);
				}
			}
    	}
    }
    
    public void setPlayerSteps(int steps) {
    	players.get(currentPlayer).setStepsRemaining(steps);;
    }

    
    // Redundant - delete later
    private void onPlayerMove() {
    	Player player = null;
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    	board.show(view.getGraphics());
    	System.out.println("==============================================================");
    	System.out.println(player.getName()+"'s Turn");
    	System.out.println("Hand: "+player.getHand()+" Eliminations: "+player.getEliminations());
    	System.out.println("==============================================================");
    	
    	//reset players previous steps for this turn
    	player.resetPrev();
    	boolean askAccuse = true; 
    	
    	outer: {
    		while (player.hasRemainingSteps()) {
    	
    		try {	    		
	    		System.out.println("Steps remaining: "+player.getStepsRemaining());
	    		System.out.println("W = UP | A = LEFT | S = DOWN | D = RIGHT");
	    		String moveSummary = "invalid move!";
	    		
	    		System.out.println("-------------------------------------------------------------");
	    		System.out.print("Direction: ");
	    		String dir = input.readLine().toLowerCase();

				System.out.print("Number of Steps: ");
	    		int steps = Integer.parseInt(input.readLine());
	    		System.out.println("-------------------------------------------------------------");
	    		if(steps > player.getStepsRemaining()) { System.out.println("You don't have enough steps!");}
	    		
	    		else {
	    			switch (dir) {
	                    case "w":
	                        if (board.movePlayer(player, Direction.UP, steps)) { 
	                            moveSummary = "Player " + player + " moved UP: "+steps; }
	                        break;
	                    case "d":
	                        if (board.movePlayer(player, Direction.RIGHT, steps)) { 
	                            moveSummary = "Player " + player + " moved RIGHT: "+steps; }
	                        break;
	                    case "s":
	                        if (board.movePlayer(player, Direction.DOWN, steps)) { 
	                            moveSummary = "Player " + player + " moved DOWN: "+steps; }
	                        break;
	                    case "a":
	                        if (board.movePlayer(player, Direction.LEFT, steps)) {
	                            moveSummary = "Player " + player + " moved LEFT: "+steps; }
	                        break;
	                    default:
	                        moveSummary = "Invalid input!";
	                        break;
	            	}
    			}

	    		System.out.println("==============================================================");
	    		System.out.println(moveSummary);
	    		System.out.println("-------------------------------------------------------------");
	    		if(askAccuse) {
	    			for (Card card : allCards.values()) {
	    				if(card instanceof EstateCard) {
	    					EstateCard es = (EstateCard)card;
	    					if(es.getEstate().within(player.getPos())) {
	    						player.setEstate(es.getEstate());
	    						System.out.println("You have entered "+es.getEstate().getName());

	    						// ask if player wishes to make an guess/accusation now
	    						System.out.println("Hand: "+player.getHand()+" Eliminations: "+player.getEliminations());
	    						System.out.print("Would you like to make a guess/accuse now? (y/n): ");
	    						String confirm = input.readLine();
	    						// break and go to refute/accuse
	    						if(confirm.equalsIgnoreCase("y")) { break outer; } 
	    						// else ask player if they want to keep moving
	    						else {
	    							player.setEstate(null);
	    							System.out.print("Would you like to keep moving? (y/n): ");
	    							confirm = input.readLine();
	    							if(confirm.equalsIgnoreCase("y")) { 
	    								askAccuse = false; 
	    								break;  
	    							}
	    							else { 
	    								break outer;
	    							}	
	    						}
	    					} else {
	    						player.setEstate(null);
	    					}
	    				}    
	    			}
	    		}
    		} catch(Exception e) {
    			System.out.println("Invalid Input: "+e);
    			continue;
    		}
    	}
    	}
    	//board.removeTrail(player);
    }
   
    
    
    /*
     * Initialize the cards, the murder scenario and the hands that the players
     * will be dealt
     */
    public void initializeCards() {
    	// Initialize & Store Respective Cards
    	allCards = new HashMap<String, Card>();
    	murderSolution = new HashSet<Card>();
    	
		List<WeaponCard> weapons = new ArrayList<WeaponCard>();
		for(String w : weaponNames) { 
			WeaponCard weapon = new WeaponCard(w);
			weapons.add(weapon);
			allCards.put(w, weapon);
		}
		
		List<CharacterCard> characters = new  ArrayList<CharacterCard>();
		// Adds the relevant player characters that have been selected by the players (characters that are
		// actually in the game)
		for(Player p : players) {
			CharacterCard character = new CharacterCard(p.getName(), p);
			characters.add(character);
			if (p.getName().equalsIgnoreCase("lucilla")) {
				p.setPos(new Position(11,1));
			} else if (p.getName().equalsIgnoreCase("percy")) {
				p.setPos(new Position(22,14));
			} else if (p.getName().equalsIgnoreCase("melina")) {
				p.setPos(new Position(9,22));
			} else if (p.getName().equalsIgnoreCase("bert")) {
				p.setPos(new Position(1,9));
			}
			allCards.put(p.getName(),character);
		}
		
		List<EstateCard> estates = new  ArrayList<EstateCard>();
		for(String es : estateNames) {
			Position topLeft = null;
			Position botRight = null;
			if (es.equals("Haunted House")) {
				topLeft = new Position(2,2);
				botRight = new Position(6,6);
			} else if (es.equals("Manic Manor")) {
				topLeft = new Position(17,2);
				botRight = new Position(21,6);
			} else if (es.equals("Villa Celia")) {
				topLeft = new Position(9,10);
				botRight = new Position(14,13);
			} else if (es.equals("Calamity Castle")) {
				topLeft = new Position(2,17);
				botRight = new Position(6,21);
			} else if (es.equals("Peril Palace")) {
				topLeft = new Position(17,17);
				botRight = new Position(21,21);
			}
				
			Estate estate = new Estate(es, topLeft, botRight);
			EstateCard estateCard = new EstateCard(estate);
			estates.add(estateCard);
			allCards.put(es, estateCard);
		}
		
		Random ran = new Random();
		Collections.shuffle(weapons);Collections.shuffle(characters);Collections.shuffle(estates);
		
		//Distribute the evenly weapons to the estates
		int i = 0;
		while(i != weapons.size()) {
			for(EstateCard e : estates) {
				if(i == weapons.size()) { break; }
				e.getEstate().addWeapon(weapons.get(i));
				i++;
			}
		}
				
		// Set up Murder Circumstances
		murderSolution.add(weapons.remove(ran.nextInt(weapons.size())));
		murderSolution.add(characters.remove(ran.nextInt(characters.size())));
		murderSolution.add(estates.remove(ran.nextInt(estates.size())));
		
		// Distribute Remaining Cards to Players
		List<Card> remaining = new ArrayList<Card>();
		remaining.addAll(weapons);remaining.addAll(characters);remaining.addAll(estates);
		
		while(!remaining.isEmpty()) {
			for(Player p : players) {
				if(remaining.isEmpty()) { break; }
				p.addToHand(remaining.get(0));
				remaining.remove(0);
			}
		}
    }

    /*
     * A player attempts to solve the murder scenario.
     */
    public void onAccusation(Player p) {
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    	while (true) {
    		try {
    			Estate currentEstate = p.getEstate();
    			EstateCard estateCard = (EstateCard)allCards.get(currentEstate.getName());
    			
    			System.out.println("==============================================================");
    			System.out.println("YOU ARE CURRENTLY ACCUSING");
    			System.out.println("You are inside "+currentEstate.getName());
	    		System.out.println("Pick two cards that you think may be in the murder scenario");
	    		System.out.println("==============================================================");
	    		
	    		List<Card> weapons = new ArrayList<Card>();
	    		List<Card> characters = new ArrayList<Card>();
	    		for (Card c: allCards.values()) {
	    			if (!p.getEliminations().contains(c) && !p.getHand().contains(c)) {
	    				if (c instanceof WeaponCard)
	    					weapons.add(c);
	    				if (c instanceof CharacterCard)
	    					characters.add(c);
	    			}
	    		}
	    		
	    		Display.displayPossibleCards(weapons);
	    		System.out.print("Choose a WeaponCard: ");
	    		Card weaponCard = allCards.get(Display.capitalize(input.readLine())); 
	    		
	    		if (weaponCard == null) throw new NoSuchElementException("Card does not exist");
	    		System.out.println("-------------------------------------------------------------");
	    		
	    		Display.displayPossibleCards(characters);
	    		System.out.print("Choose a CharacterCard: ");
	    		Card characterCard = allCards.get(Display.capitalize(input.readLine()));
	    		
	    		if (characterCard == null) throw new NoSuchElementException("Card does not exist");
	    		System.out.println("-------------------------------------------------------------");
	    		System.out.println("YOUR ACCUSATION: "+weaponCard+" & "+characterCard+" inside the "+currentEstate.getName()); 
	    		
	    		String msg = "RESULT: You have failed to choose the right murder scenario, you are now out of this game";
	    		Set<Card> proposedSolution = new HashSet<Card>(Set.of(weaponCard, characterCard, estateCard));
	    		System.out.println("PROPOSED: "+proposedSolution);
	    		System.out.println("SOLUTION: "+murderSolution);
	    		//correct murder solution, player wins
	    		if (murderSolution.containsAll(proposedSolution)) {
	    			msg = "RESULT: Congratulations you were correct!";
	    			isOngoing = false;
	    		}
	    		//player has made a wrong accusation and now cannot make another
	    		else p.setInGame(false);
	    		
	    		System.out.println("==============================================================");
	    		System.out.println(msg);
	    		System.out.println("==============================================================");
	    		System.out.print("Enter any key to proceed..");
	    		input.readLine();
	    		break;
    		} catch(Exception e) {
    			System.out.println("Invalid Input: "+e.getMessage());
    			continue;
    		}
    	}
    }
    
    
    private WeaponCard chosenWeapon = null;
    private CharacterCard chosenCharacter = null;
    private Pair<List<Card>, List<Card>> possibleCards;
    
    public void gatherPossibleCards() {
        chosenWeapon = null;
        chosenCharacter = null;
    	Player p = players.get(currentPlayer);
		List<Card> weapons = new ArrayList<Card>();
		List<Card> characters = new ArrayList<Card>();
		for (Card c: allCards.values()) {
			if (!p.getEliminations().contains(c) && !p.getHand().contains(c)) {
				if (c instanceof WeaponCard)
					weapons.add(c);
				if (c instanceof CharacterCard)
					characters.add(c);
			}
		}
		possibleCards = new Pair<List<Card>, List<Card>>(weapons, characters);
    }
    
    /*
     * A player attempts to guess a card that may or may not exist in the murder scenario.
     */
    public void onRefute() {
    	Player p = players.get(currentPlayer);
    	Estate currentEstate = p.getEstate();
    	EstateCard estateCard = (EstateCard)allCards.get(currentEstate.getName());

		if (chosenWeapon == null && chosenCharacter == null) return;
		view.onPrompt("Chosen Cards to Refute", chosenWeapon+" & "+chosenCharacter+" in the "+estateCard);
		
		// Move Cards into Current Estate
		// remove weapon from one estate, and move it to this estate
		if(!currentEstate.hasThisWeapon(chosenWeapon)) {
    		for(Card c: allCards.values()) {
    			if(c instanceof EstateCard) {
    				((EstateCard) c).getEstate().removeWeapon(chosenWeapon);
    			}
    		}
    		currentEstate.addWeapon(chosenWeapon);
		}

		// Move guessed player to this estate
		if(!currentEstate.within(chosenCharacter.getPlayer().getPos())) {
			board.moveTo(chosenCharacter, currentEstate);
			updateBoard(view.getGraphics());
		}
		
		Card refutedCard = null;
		List<String> order = characterNames.stream()
				.filter(character -> !character.equalsIgnoreCase(p.getName()))
				.collect(Collectors.toList()); // Filter current player & Ensures the fixed order for guessing
		
		// Check if a player can refute the guess
		List<Card> options = new ArrayList<Card>();
		for (String pName: order) {
			CharacterCard pChar = (CharacterCard)allCards.get(pName);
			if (pChar == null) continue;
			Player otherPlayer = pChar.getPlayer();
			options = otherPlayer.countRefutableCards(chosenWeapon, chosenCharacter, estateCard);
			
    		if (!options.isEmpty()) {
    			// Grab the first card that is refutable
    			refutedCard = options.get(0);
    			view.onPrompt("Refuting", otherPlayer+" has refuted one of your guesses: \n"+refutedCard+" is no longer part of the MurderSolution");
    			// TODO: Update Elimination Sheet
    			
		    	break;
    		}
		}
		if (refutedCard == null) view.onPrompt("GUESS: "+chosenWeapon+" & "+chosenCharacter+" in "+estateCard, "No one could refute your guesses");
		p.setStepsRemaining(0);
		this.onPlayerMove(null); // Force End Of Turn
    }
    
    /*
     * Trigger Event to choose a Weapon &/OR Character Card
     * 
     * - Used for refute and accusations
     */
    public void triggerChoose() {
    	if (chosenWeapon == null) {
    		view.onPrompt("INFO", "Choose a Weapon to Refute");
    		view.showCards(possibleCards.getLeft());
    	} else if (chosenCharacter == null) {
    		view.onPrompt("INFO", "Choose a Character to Refute");
    		view.showCards(possibleCards.getRight());   		
    	} else {
    		view.checkLogic();;
    	}
    }
    
    /*
     * Set the selected Card
     * 
     * - Used for refute and accusations
     */
    public void setChosenCard(Card card) {
    	if (card instanceof WeaponCard) {
    		this.chosenWeapon = (WeaponCard)card;
    	} else {
    		this.chosenCharacter = (CharacterCard)card;
    	}
    }
   
    public List<Player> getPlayers() {
    	return Collections.unmodifiableList(this.players);
    }
    
    public BufferedImage grabAsset(String path) {
		try {
			BufferedImage img = ImageIO.read(new File(path));
			return img;
		} catch (Exception e) {e.printStackTrace();}
		return null;
    }
    
    /*
     * Gets the current Player.
     */
    public Player getCurrentPlayer() {
    	return players.get(currentPlayer);
    }
    
    // Small Implementation of a Pair Class
    class Pair<K,V> {
    	private K a; 
    	private V b;
    	public Pair(K left, V right) {
    		this.a = left;
    		this.b = right;
    	}
    	public K getLeft() {return this.a;}
    	public V getRight() {return this.b;}
    }
    
	public static void main(String[] args) {
		new MurderMadness();
	}
}
