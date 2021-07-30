package swen225.murdermadness.view;

import swen225.murdermadness.MurderMadness.Direction;
import swen225.murdermadness.cards.CharacterCard;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import swen225.murdermadness.Estate;
import swen225.murdermadness.MurderMadness;
import swen225.murdermadness.Player;

public class Board {
	
	private MurderMadness model;
	
	List<Tile> hauntedHouse = new ArrayList<Tile>();
    List<Tile> manicManor = new ArrayList<Tile>();
    List<Tile> villaCelia = new ArrayList<Tile>();
    List<Tile> calamitycastle = new ArrayList<Tile>();
    List<Tile> perilPalace = new ArrayList<Tile>();
	
	private Tile[][] board;
	String layout =
	        ". . . . . . . . . . . . . . . . . . . . . . . ." + // 00
			". . . . . . . . . . . . . . . . . . . . . . . ." + // 01
			". . H H H H H . . . . . . . . . . M M M M M . ." + // 02
			". . H o o o o . . . . . . . . . . M o o o M . ." + // 03
			". . H o o o H . . . . . . . . . . M o o o M . ." + // 04
			". . H o o o H . . . . G G . . . . o o o o M . ." + // 05
			". . H H H o H . . . . G G . . . . M M M o M . ." + // 06
			". . . . . . . . . . . . . . . . . . . . . . . ." + // 07
			". . . . . . . . . . . . . . . . . . . . . . . ." + // 08
			". . . . . . . . . . . . . . . . . . . . . . . ." + // 09
			". . . . . . . . . V V o V V V . . . . . . . . ." + // 10
			". . . . . G G . . o o o o o o . . G G . . . . ." + // 11
			". . . . . G G . . V o o o o V . . G G . . . . ." + // 12
			". . . . . . . . . V V o V V V . . . . . . . . ." + // 13
			". . . . . . . . . . . . . . . . . . . . . . . ." + // 14
			". . . . . . . . . . . . . . . . . . . . . . . ." + // 15
			". . . . . . . . . . . . . . . . . . . . . . . ." + // 16
			". . C o C C C . . . . G G . . . . P o P P P . ." + // 17
			". . C o o o o . . . . G G . . . . P o o o P . ." + // 18
			". . C o o o C . . . . . . . . . . P o o o P . ." + // 19
			". . C o o o C . . . . . . . . . . o o o o P . ." + // 20
			". . C C C C C . . . . . . . . . . P P P P P . ." + // 21
			". . . . . . . . . . . . . . . . . . . . . . . ." + // 22
			". . . . . . . . . . . . . . . . . . . . . . . ." ; // 23
	
	/**
	 * Redraws the board onto the console
	 */
	public void show(Graphics2D g) {		
		for (int y = 0; y <= 23; y++) {
			for (int x = 0; x <= 23; x++) {
				System.out.print(" ");
				System.out.print(this.board[x][y].getCharacter());
				System.out.print(" ");
			}
		}
		

		for (Player p: model.getPlayers()) {
			p.redraw(g);
		}
	}
	
	/**
	 * Construct initial board.
	 */
	public Board(MurderMadness model) {
		this.model = model;
		this.board = new Tile[24][24]; //changed to 24 from 23 
		layout = layout.replaceAll(" ", "");
		layout = layout.replaceAll("o", " ");
		int counter = 0;
		
		for (int x = 0; x <= 23; x++) {
			for (int y = 0; y <= 23; y++) {
				String c = Character.toString(layout.charAt(counter));
				this.board[x][y] = new NormalTile(new Position(x, y) , c);	
			}
		}
		//playerStartingPositions
		this.board[11][1] = new NormalTile(new Position(11,1),"L"); // lucilla
		this.board[22][14] = new NormalTile(new Position(22,14),"P"); // Percy
		this.board[9][22] = new NormalTile(new Position(9,22),"M"); // Malina
		this.board[1][9] = new NormalTile(new Position(1,9),"B"); // Bert
		
		// Haunted House walls

				// First row
				this.board[2][2] = new Wall(new Position(2,2),"H");
				this.board[3][2] = new Wall(new Position(3,2),"H");
				this.board[4][2] = new Wall(new Position(4,2),"H");
				this.board[5][2] = new Wall(new Position(5,2),"H");
				this.board[6][2] = new Wall(new Position(6,2),"H");

				// Second row
				this.board[2][3] = new Wall(new Position(2,3),"H");

				// Third row
				this.board[2][4] = new Wall(new Position(2,4),"H");
				this.board[6][4] = new Wall(new Position(6,4),"H");

				// Fourth row
				this.board[2][5] = new Wall(new Position(2,5),"H");
				this.board[6][5] = new Wall(new Position(6,5),"H");

				// Fifth row
				this.board[2][6] = new Wall(new Position(2,6),"H");
				this.board[3][6] = new Wall(new Position(3,6),"H");
				this.board[4][6] = new Wall(new Position(4,6),"H");
				this.board[6][6] = new Wall(new Position(6,6),"H");

				// Manic Manor walls

				// First row
				this.board[17][2] = new Wall(new Position(17,2),"M");
				this.board[18][2] = new Wall(new Position(18,2),"M");
				this.board[19][2] = new Wall(new Position(19,2),"M");
				this.board[20][2] = new Wall(new Position(20,2),"M");
				this.board[21][2] = new Wall(new Position(21,2),"M");

				// Second row
				this.board[17][3] = new Wall(new Position(17,3),"M");
				this.board[21][3] = new Wall(new Position(21,3),"M");

				// Third row
				this.board[17][4] = new Wall(new Position(17,4),"M");
				this.board[21][4] = new Wall(new Position(21,4),"M");

				// Fourth row
				this.board[21][5] = new Wall(new Position(21,5),"M");


				// Fifth row
				this.board[17][6] = new Wall(new Position(17,6),"M");
				this.board[18][6] = new Wall(new Position(18,6),"M");
				this.board[19][6] = new Wall(new Position(19,6),"M");
				this.board[21][6] = new Wall(new Position(21,6),"M");

				// Villa Celia

				// First row
				this.board[9][10] = new Wall(new Position(9,10),"V");
				this.board[10][10] = new Wall(new Position(10,10),"V");
				this.board[11][10] = new Wall(new Position(11,10),"V");
				this.board[13][10] = new Wall(new Position(13,10),"V");
				this.board[14][10] = new Wall(new Position(14,10),"V");

				// Second row
				this.board[9][11] = new Wall(new Position(9,11),"V");

				// Third row
				this.board[14][12] = new Wall(new Position(14,12),"V");

				// Fourth row
				this.board[9][13] = new Wall(new Position(9,13),"V");
				this.board[10][13] = new Wall(new Position(10,13),"V");
				this.board[12][13] = new Wall(new Position(12,13),"V");
				this.board[13][13] = new Wall(new Position(13,13),"V");
				this.board[14][13] = new Wall(new Position(14,13),"V");

				// Calamity Castle

				// First row
				this.board[2][17] = new Wall(new Position(2,17),"C");
				this.board[4][17] = new Wall(new Position(4,17),"C");
				this.board[5][17] = new Wall(new Position(5,17),"C");
				this.board[6][17] = new Wall(new Position(6,17),"C");

				// Second row
				this.board[2][18] = new Wall(new Position(2,18),"C");

				// Third row
				this.board[2][19] = new Wall(new Position(2,19),"C");
				this.board[6][19] = new Wall(new Position(6,19),"C");

				// Fourth row
				this.board[2][20] = new Wall(new Position(2,20),"C");
				this.board[6][20] = new Wall(new Position(6,20),"C");

				// Fifth row
				this.board[2][21] = new Wall(new Position(2,21),"C");
				this.board[3][21] = new Wall(new Position(3,21),"C");
				this.board[4][21] = new Wall(new Position(4,21),"C");
				this.board[5][21] = new Wall(new Position(5,21),"C");
				this.board[6][21] = new Wall(new Position(6,21),"C");

				// Peril Palace

				// First row
				this.board[17][17] = new Wall(new Position(17,17),"P");
				this.board[19][17] = new Wall(new Position(19,17),"P");
				this.board[20][17] = new Wall(new Position(20,17),"P");
				this.board[21][17] = new Wall(new Position(21,17),"P");

				// Second row
				this.board[17][18] = new Wall(new Position(17,18),"P");
				this.board[21][18] = new Wall(new Position(21,18),"P");

				// Third row
				this.board[17][19] = new Wall(new Position(17,19),"P");
				this.board[21][19] = new Wall(new Position(21,19),"P");

				// Fourth row
				this.board[21][20] = new Wall(new Position(21,20),"P");

				// Fifth row
				this.board[17][21] = new Wall(new Position(17,21),"P");
				this.board[18][21] = new Wall(new Position(18,21),"P");
				this.board[19][21] = new Wall(new Position(19,21),"P");
				this.board[20][21] = new Wall(new Position(20,21),"P");
				this.board[21][21] = new Wall(new Position(21,21),"P");
				
				
				hauntedHouse.add(this.board[2][2]);// top left
				hauntedHouse.add(this.board[6][6]);// bottom Right
				
				manicManor.add(this.board[17][2]);// top left
				manicManor.add(this.board[21][6]);// bottom Right
				
				villaCelia.add(this.board[9][10]);// top left
				villaCelia.add(this.board[14][13]);// bottom Right
				
				calamitycastle.add(this.board[2][17]);// top left
				calamitycastle.add(this.board[6][21]);// bottom Right
				
				perilPalace.add(this.board[17][17]);// top left
				perilPalace.add(this.board[21][21]);// bottom Right
			}

	/**
	 * Get the tile at a given position on the board. If the position is outside the
	 * board dimensions, it just returns empty air.
	 *
	 * @param position Board position to get tile from
	 * @return Tile at given position
	 */
	public Tile getTile(Position position) {
		if(!position.isValid()) {return null;}
		else {
			return board[position.getX()][position.getY()];
		}
	}
	
	/**
     * Gets the adjacent tile of the new location to move the gamePiece to
     */
    public Tile getNewLocation(Position current, Direction direction) {
    	final int x = current.getX();
		final int y = current.getY();

		switch (direction) {
	        case UP:
	            return getTile(new Position(x, y-1)); 
	        case RIGHT:
	            return getTile(new Position(x+1, y)); 
	        case DOWN:
	            return getTile(new Position(x, y+1)); 
	        case LEFT:
	            return getTile(new Position(x-1, y)); 
	        default:
	            return null;
		}
    }
    
    /**
     * Move the player on the board, returns false if the movement was invalid
     */
    public boolean movePlayer(Player player, Direction direction, int steps) {
    	for (int i = 0;i < steps;i++) {
    		Tile next = getNewLocation(player.getPos(), direction);
    		
    		// return false to state an invalid movement of player
    		if(next == null) { 
    			return false; 
    			}
    		if(next.isObstruction()) {
    			System.out.println("There is a wall there!");
    			return false; 
    		}
    		if(player.getPrevPos().contains(next.getPos())) {
    			System.out.println("This position has already been visited this turn!");
    			return false;
    		}	
    		if(!next.getCharacter().equals(".")) { 
    			System.out.println("A character is already in this spot!");
    			return false;
    		}
    		
    		//move player to next tile, reset last tile to x, indicating they cannot go back there this turn
	        this.board[next.getPos().getX()][next.getPos().getY()].setCharacter(board[player.getPos().getX()][player.getPos().getY()].getCharacter());
	        this.board[player.getPos().getX()][player.getPos().getY()].setCharacter("x");
	    	player.updateLocation(next.getPos());
	    	player.decrementStep();
    	}
    	return true;
	}
    
    /**
     * Moves a character located outside a stated estate into the estate
     */
    public void moveTo(CharacterCard card, Estate estate){
		Player p = card.getPlayer();
		Position pos = estate.nextAvailablePosition(this);
		this.board[pos.getX()][pos.getY()].setCharacter(board[p.getPos().getX()][p.getPos().getY()].getCharacter());
        this.board[p.getPos().getX()][p.getPos().getY()].setCharacter(".");
        p.updateLocation(pos);
        
        System.out.println(p.getName()+" has been moved to "+estate.getName());
    }
    
    /**
     * Removes player's trail of visited tiles (x) during their turn
     */
    public void removeTrail(Player p) {
    	for(Position pos : p.getPrevPos()) {
    		this.board[pos.getX()][pos.getY()].setCharacter(".");
    	}
    }
    
    /**
     * Removes character from board if no Player controls it.
     */
    public void removeCharacter(String name) {
    	if (name.equals("Lucilla")) {
    		this.board[11][1] = new NormalTile(new Position(11,1), "."); //
    	}
    	else if(name.equals("Percy")) {
    		this.board[22][14] = new NormalTile(new Position(22,14), ".");
    	}
    	else if(name.equals("Melina")) {
    		this.board[9][22] = new NormalTile(new Position(9,22), ".");
    	}
    	else {
    		this.board[1][9] = new NormalTile(new Position(1,9), ".");
    	}
    }

    
    
    

}
