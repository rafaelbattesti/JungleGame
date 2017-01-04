package game;
import pieces.WeakestPiece;
import pieces.StrongestPiece;
import pieces.JumpingPiece;
import java.util.*;
import view.GameView;
import logic.*;
import logic.piece.*;
import logic.tile.Tile;
import tiles.DenTile;
import tiles.TrapTile;
import tiles.WaterTile;

/**
 * This Class intends to define the methods to initialize a Jungle game.<br>
 * It contains a Main method that instantiates the GameExtended itself and passes it to a GameView 
 * instance.
 * 
 * @author  Rafael Battesti for Jungle Project 2015 - PROG10082 - Java 1
 * @version 1.0
 * @since   2015-04-19
 */
public class GameExtended extends Game {

	/**
	 * Serialized Field.
	 * @serialField
	 */
	private static final long serialVersionUID = -5340769819219089012L;

	/**
	 * Constant with pieces names, used in initPieces().
	 */
	public static final String[] PIECE_NAMES = {
		"Rat",
		"Cat",
		"Dog",
		"Wolf",
		"Leopard",
		"Tiger",
		"Lion",
		"Elephant"
	};
	
    /**
     * Constant for the A.I.<br>
     * Used in the getNextMoveBlack() method to define which index of the ArrayList is the source tile.
     */
	public static final int SOURCE_TILE      = 0;
	
	/**
	 * Constant for the A.I.<br>
	 * Used in the getNextMoveBlack() method to define which index of the ArrayList is the destination tile.
	 */
	public static final int DESTINATION_TILE = 0;

	/* Global Variables */
	Tile[][] tiles;
	Piece[][] pieces;

	/**
	 * Main method. Performs 2 actions.<br>
	 * 1. Instantiate a new GameExtended;<br>
	 * 2. Instantiate a new GameView passing the GameExtended as argument.<br>
	 * @param args Optional command line list of arguments. Not used.
	 */
	public static void main(String[] args) {

		/* Initialize Game */
		GameExtended game = new GameExtended();

		/* Initialize view */
		GameView view = new GameView(game);

	}

	/**
	 * Constructor inherited from the superclass as is.<br>
	 * <strong>This is the method used in my version of the Game.</strong><br>
	 * The superclass constructor call the methods initBoard() and initPieces().
	 */
	public GameExtended() {
		super();
	}

	/**
	 * Method that runs after every turn and checks for winning conditions.<br>
	 * Winner will be declared if one of the following conditions is satisfied.<br>
	 * 1. Player has reached the opponent's Den.<br>
	 * 2. Player has captured all the opponent's Pieces.<br>
	 * @return 1 for Blue, -1 for Red and 0 for Continue.
	 */
	public int whoWon() {

		/* Validates whether either side has won the game by reaching the opponent's DenTile */
		if (getTile(0, 3).getPlacedPiece() != null) {
			
			boolean redWon = getTile(0, 3).getPlacedPiece().getSide() == ConstantValues.RED_SIDE;
			return redWon ? -1 : 0;
			
		} else if (getTile(8, 3).getPlacedPiece() != null){
			
			boolean blueWon = getTile(8, 3).getPlacedPiece().getSide() == ConstantValues.BLACK_SIDE;
			return blueWon ? 1 : 0;
			
		} else {
			
			/* Validates whether either side has won the game by exterminating the opponent's Pieces */
			if (_blackPieces.size() == 0) {
				return -1;
			} else if (_redPieces.size() == 0) {
				return 1;
			} else {
				return 0;
			}
			
		}
	}

	/**
	 * Method that initializes a new GameBoard, using global variable Tile[][] tiles.<br>
	 * After initializing the Tile[][] tiles:<br>
	 * Invokes the GameBoard constructor, passing Tile[][] tiles as argument, 
	 * and sets the new GameBoard to the _gameboard attribute from superclass.
	 */
	public void initBoard() {

		/* Initializes tiles depending on the Coordinate on the board */
		tiles = new Tile[9][7];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 7; j++) {
				if (i > 2 && i < 6) {
					if (j > 0 && j < 6 && j != 3) {
						tiles[i][j] = new WaterTile(new Coordinate(i, j), "water");
					} else {
						tiles[i][j] = new Tile(new Coordinate(i, j), "tile");
					}
				} else if (i == 0 || i == 8){
					if (j == 3) {
						tiles[i][j] = new DenTile(new Coordinate(i, j), "den");
					} else if (j == 2 || j == 4) {
						tiles[i][j] = new TrapTile(new Coordinate(i, j), "trap");
					} else {
						tiles[i][j] = new Tile(new Coordinate(i, j), "tile");
					}
				} else if (i == 1 || i == 7) {
					if (j == 3) {
						tiles[i][j] = new TrapTile(new Coordinate(i, j), "trap");
					} else {
						tiles[i][j] = new Tile(new Coordinate(i, j), "tile");
					}
				} else {
					tiles[i][j] = new Tile(new Coordinate(i, j), "tile");
				}
			}
		}

		/* Initializes board */
		_gameBoard = new GameBoard(tiles);
	}

	/**
	 * Method to initialize the pieces.<br>
	 * Uses the global variable Piece[][] pieces to determine the position of the pieces on the board.
	 * <p>
	 * Acts in three stages:<br>
	 * 1. Cleans up _blackPieces and _redPieces.<br>
	 * 2. Instantiates StringestPiece, WeakestPiece, StandardJunglePiece and JumpingPiece.<br>
	 * 3. Adds new Pieces to the _blackPieces and _redPieces ArrayLists.
	 */
	public void initPieces() {

		/* Clear the ArrayLists */
		_blackPieces.clear();
		_redPieces.clear();

		/* Set the pieces */
		pieces = new Piece[8][2];

		/* Instantiate Pieces and add them to ArrayLists */
		for (int i = 0; i < 8; i++) {
			switch(i) {
			case 0:
				pieces[i][0] = new WeakestPiece(ConstantValues.RED_SIDE, 1, i + 1, PIECE_NAMES[i]);
				pieces[i][1] = new WeakestPiece(ConstantValues.BLACK_SIDE, 1, i + 1, PIECE_NAMES[i]);
				_redPieces.add(pieces[i][0]);
				_blackPieces.add(pieces[i][1]);
				break;
			case 1:
			case 2:
			case 3:
			case 4:
				pieces[i][0] = new StandardJunglePiece(ConstantValues.RED_SIDE, 1, i + 1, PIECE_NAMES[i]);
				pieces[i][1] = new StandardJunglePiece(ConstantValues.BLACK_SIDE, 1, i + 1, PIECE_NAMES[i]);
				_redPieces.add(pieces[i][0]);
				_blackPieces.add(pieces[i][1]);
				break;
			case 7:
				pieces[i][0] = new StrongestPiece(ConstantValues.RED_SIDE, 1, i + 1, PIECE_NAMES[i]);
				pieces[i][1] = new StrongestPiece(ConstantValues.BLACK_SIDE, 1, i + 1, PIECE_NAMES[i]);
				_redPieces.add(pieces[i][0]);
				_blackPieces.add(pieces[i][1]);
				break;
			case 5:
			case 6:
				pieces[i][0] = new JumpingPiece(ConstantValues.RED_SIDE, 1, i + 1, PIECE_NAMES[i]);
				pieces[i][1] = new JumpingPiece(ConstantValues.BLACK_SIDE, 1, i + 1, PIECE_NAMES[i]);
				_redPieces.add(pieces[i][0]);
				_blackPieces.add(pieces[i][1]);
				break;	
			}
		}

		/* Position Pieces */

		/* Rats */
		pieces[0][0].setLocation(new Coordinate(6, 6));
		tiles[6][6].setPlacedPiece(pieces[0][0]);
		pieces[0][1].setLocation(new Coordinate(2, 0));
		tiles[2][0].setPlacedPiece(pieces[0][1]);

		/* Cats */
		pieces[1][0].setLocation(new Coordinate(7, 1));
		tiles[7][1].setPlacedPiece(pieces[1][0]);
		pieces[1][1].setLocation(new Coordinate(1, 5));
		tiles[1][5].setPlacedPiece(pieces[1][1]);

		/* Dogs */
		pieces[2][0].setLocation(new Coordinate(7, 5));
		tiles[7][5].setPlacedPiece(pieces[2][0]);
		pieces[2][1].setLocation(new Coordinate(1, 1));
		tiles[1][1].setPlacedPiece(pieces[2][1]);

		/* Wolves */
		pieces[3][0].setLocation(new Coordinate(6, 2));
		tiles[6][2].setPlacedPiece(pieces[3][0]);
		pieces[3][1].setLocation(new Coordinate(2, 4));
		tiles[2][4].setPlacedPiece(pieces[3][1]);

		/* Leopards */
		pieces[4][0].setLocation(new Coordinate(6, 4));
		tiles[6][4].setPlacedPiece(pieces[4][0]);
		pieces[4][1].setLocation(new Coordinate(2, 2));
		tiles[2][2].setPlacedPiece(pieces[4][1]);

		/* Tigers */
		pieces[5][0].setLocation(new Coordinate(8, 0));
		tiles[8][0].setPlacedPiece(pieces[5][0]);
		pieces[5][1].setLocation(new Coordinate(0, 6));
		tiles[0][6].setPlacedPiece(pieces[5][1]);

		/* Lions */
		pieces[6][0].setLocation(new Coordinate(8, 6));
		tiles[8][6].setPlacedPiece(pieces[6][0]);
		pieces[6][1].setLocation(new Coordinate(0, 0));
		tiles[0][0].setPlacedPiece(pieces[6][1]);

		/* Elephants */
		pieces[7][0].setLocation(new Coordinate(6, 0));
		tiles[6][0].setPlacedPiece(pieces[7][0]);
		pieces[7][1].setLocation(new Coordinate(2, 6));
		tiles[2][6].setPlacedPiece(pieces[7][1]);

	}

	/**
	 * The Game's AI. Works for the computer playing with the Blue Pieces.<br>
	 * To play against this AI, user should start the Game, then choose "New Game - then - Player vs Computer".<br>
	 */
	public ArrayList<Tile> getNextMoveBlack(){
		
		/* Variables to hold the Piece and the move */
		Piece pieceToMove = null;
		ArrayList<Tile> move = null;
		
		/* Assign to move the returning value of findAttack */
		move = findAttack();

		/* If it is not empty - attack opportunity has been found! Dead! */
		if (!move.isEmpty()) {
			
			/* Assign the Piece new location */
			pieceToMove = move.get(SOURCE_TILE).getPlacedPiece();
			pieceToMove.setLocation(move.get(DESTINATION_TILE).getLocation());

		} else {
			
			/* If no attack opportunities have been found, verify vulnerable pieces */
			move = findVulnerable();

			/* If it is not empty - vulnerable Piece found! Move away! */
			if (!move.isEmpty()) {
				
				/* Assign the Piece new location */
				pieceToMove = move.get(SOURCE_TILE).getPlacedPiece();
				pieceToMove.setLocation(move.get(DESTINATION_TILE).getLocation());
				
			} else {
				
				/* If no vulnerabilities have been found */
				move = randomMove();

				/* If it is not empty - move found! */
				if (!move.isEmpty()) {
					
					/* Assign the Piece new location */
					pieceToMove = move.get(SOURCE_TILE).getPlacedPiece();
					pieceToMove.setLocation(move.get(DESTINATION_TILE).getLocation());
				}

			}
		}
		
		/* Return the last tiles in the ArrayList move after it passed through the priority validation */
		return move;
	}

	/**
	 * Method that returns a movement in case there are no attacks or vulnerable pieces.<br> 
	 * It checks for "stupid" moves calling isMoveStupid(). If the only move available is stupid, it does it.
	 * @return ArrayList &lt;Tile&gt; randomMove[sourceTile, destinationTile]
	 */
	public ArrayList<Tile> randomMove () {
		
		/* Initializes variables */
		Random rand = new Random();
		ArrayList<Tile> randomMove = new ArrayList<Tile>();
		ArrayList<Tile> possibleMoves = new ArrayList<Tile>();

		Piece piece = null;
		int pieceRow = 0;
		int pieceCol = 0;

		Tile sourceTile = null;
		Tile destinationTile = null;
		
		/* count is increased as randomMove tries to reach another piece. */
		int count = 0;

		do {
			
			/* Get Piece's information to validate move */
			piece = (Piece) _blackPieces.get(rand.nextInt(_blackPieces.size()));
			pieceRow = piece.getLocation().getRow();
			pieceCol = piece.getLocation().getColumn();

			/* Assign possible moves validated by isMoveStupid() */
			possibleMoves = getValidMoves(pieceRow, pieceCol);
			possibleMoves = isMoveStupid(piece, possibleMoves);

			/* Check for possible conditions to assign tiles FROM and TO (avoid NullPointerException) */
			if (possibleMoves != null && !possibleMoves.isEmpty()) {
				sourceTile = _gameBoard.getTile(pieceRow, pieceCol);
				destinationTile = possibleMoves.get(rand.nextInt(possibleMoves.size()));
			}
			
			/* In case the only move available IS stupid, loop 50 times and surrender to stupidity */
			if (count > 50) {
				
				/* Get valid moves without quality validation */
				possibleMoves = getValidMoves(pieceRow, pieceCol);

				/* Repeat check conditions to avoid NPE */
				if (possibleMoves != null && !possibleMoves.isEmpty()) {
					System.out.println("chose alternative...");
					sourceTile = _gameBoard.getTile(pieceRow, pieceCol);
					destinationTile = possibleMoves.get(rand.nextInt(possibleMoves.size()));
				} 
			}
			
			/* Increase count */
			count++;

		} while (possibleMoves == null
						|| possibleMoves.isEmpty() 
						|| sourceTile.getPlacedPiece().getSide() == ConstantValues.RED_SIDE
						|| destinationTile instanceof TrapTile);
		
		/* Add the source and destination tiles to the returning ArrayList */
		randomMove.add(sourceTile);
		randomMove.add(destinationTile);
		
		/* Return the move */
		return randomMove;
	}

	/**
	 * Method to find a possible attack to the opponent.<br>
	 * @return ArrayList &lt;Tile&gt; attackFound[sourceTile, destinationTile], empty if no attack found.
	 */
	public ArrayList<Tile> findAttack () {
		
		/* Initializes variables */
		ArrayList<Tile> attackFound = new ArrayList<Tile>();
		ArrayList<Tile> possibleMoves = new ArrayList<Tile>();

		Piece piece = null;
		int pieceRow = 0;
		int pieceCol = 0;

		Tile sourceTile = null;
		Tile destinationTile = null;
		
		/* Scan across all _blackPieces */
		for (int i = 0; i < _blackPieces.size(); i++) {

			/* Assign the piece information for further testing */
			piece = (Piece) _blackPieces.get(i);
			pieceRow = piece.getLocation().getRow();
			pieceCol = piece.getLocation().getColumn();

			/* If there are valid movements available for that piece */
			if (getValidMoves(pieceRow, pieceCol) != null) {

				/* Get the moves */
				possibleMoves = getValidMoves(pieceRow, pieceCol);

				/* Scan across the valid moves for a tile that contains a piece */
				for (int j = 0; j < possibleMoves.size(); j++) {

					/* If j-th valid move contains a piece, the piece is RED */
					if (possibleMoves.get(j).getPlacedPiece() != null) {

						/* Mark the positions to take the Piece */
						sourceTile = _gameBoard.getTile(pieceRow, pieceCol);
						destinationTile = possibleMoves.get(j);

						/* Add the source and destination tiles to the returning ArrayList */
						attackFound.add(sourceTile);
						attackFound.add(destinationTile);
						
						/* Return the attackFound, breaking the execution */
						return attackFound;

					}
				}
			}

		}
		/* In case no attack is found, return this empty attackFound */
		return attackFound;
	}


	/**
	 * Method to find a possible vulnerable piece to move away from opponent.
	 * @return ArrayList &lt;Tile&gt; attackFound[sourceTile, destinationTile], empty if no vulnerability found.
	 */
	public ArrayList<Tile> findVulnerable () {

		/* Initializes variables */
		Random rand = new Random();
		ArrayList<Tile> vulnerableFound = new ArrayList<Tile>();
		ArrayList<Tile> possibleMoves = new ArrayList<Tile>();
		ArrayList<Tile> redPossibleMoves = new ArrayList<Tile>();

		Piece redPiece = null;
		int redPieceRow = 0;
		int redPieceCol = 0;

		Tile sourceTile = null;
		Tile destinationTile = null;

		/* Scan across all _redPieces */
		for (int k = 0; k < _redPieces.size(); k++) {

			/* Assign the piece information for further testing */
			redPiece = (Piece) _redPieces.get(k);
			redPieceRow = redPiece.getLocation().getRow();
			redPieceCol = redPiece.getLocation().getColumn();

			/* If there are moves available for the k-th redPiece*/
			if (getValidMoves(redPieceRow, redPieceCol) != null) {

				/* Get the moves */
				redPossibleMoves = getValidMoves(redPieceRow, redPieceCol);

				/* Scan the moves for a RED Piece */
				for (int l = 0; l < redPossibleMoves.size(); l++) {

					/* If l-th valid move contains a piece, the piece is BLACK */
					if (redPossibleMoves.get(l).getPlacedPiece() != null) {

						/* Mark the position of the Piece to move away and get its possibleMoves */
						sourceTile = redPossibleMoves.get(l);
						
						/* If there is a valid move for the threatened BLACK piece */
						if (getValidMoves(sourceTile.getLocation().getRow(),
										sourceTile.getLocation().getColumn()) != null) {

							/* Get the moves */
							possibleMoves = getValidMoves(sourceTile.getLocation().getRow(),
											sourceTile.getLocation().getColumn());

							/* Choose a random tile to move away from the threat */
							destinationTile = possibleMoves.get(rand.nextInt(possibleMoves.size()));
							
							/* Add the source and destination tiles to the returning ArrayList */
							vulnerableFound.add(sourceTile);
							vulnerableFound.add(destinationTile);
							
							/* Return the vulnerableFound, breaking the execution */
							return vulnerableFound;
						}

					}
				}
			}
		}
		/* In case no vulnerabilities have been found, return this empty vulnerableFound */
		return vulnerableFound;
	}

	/**
	 * Method to verify whether a chosen movement is "stupid", like giving away a piece on a silver
	 * platter. :-)<br>
	 * It removes from the possibleDestinations &lt;Tile&gt; all "stupid" movements.<br>
	 * In case it becomes empty, the randomMove() method will try another Piece until it finds a Piece 
	 * which contains a non-stupid valid move.<br>
	 * It has a recursive call to avoid ArrayOutOfBounds Exception.
	 * @param piece is the piece being tested in randomMove()
	 * @param possibleDestinations is the ArrayList &lt;Tile&gt; possibleMoves for the testing Piece.
	 * @return ArrayList &lt;Tile&gt; possibleDestinations [tile1, ... , tile4]. Returns empty if all
	 * movements are "stupid".
	 */
	public ArrayList<Tile> isMoveStupid (Piece piece, ArrayList<Tile> possibleDestinations) {
		
		/* Get Piece rank for comparisons */
		int pieceRank = piece.getRank();

		/* Get information about the _redPieces */
		Piece redPiece = null;
		int redPieceRank = 0;
		int redPieceRow = 0;
		int redPieceCol = 0;
		ArrayList<Tile> possibleRedMovements = new ArrayList<Tile>();

		/* Scan across all _redPieces */
		for (int i = 0; i < _redPieces.size(); i++) {

			/* Assign values for further testing */
			redPiece = (Piece) _redPieces.get(i);
			redPieceRank = redPiece.getRank();
			redPieceRow = redPiece.getLocation().getRow();
			redPieceCol = redPiece.getLocation().getColumn();

			/* If there are valid moves for this redPiece, analyze them. */
			if (getValidMoves(redPieceRow, redPieceCol) != null) {

				/* Get the moves */
				possibleRedMovements = getValidMoves(redPieceRow, redPieceCol);

				/* Check for null pointers */
				if (possibleDestinations != null) {
					
					/* Scan across the possible destinations for BLACK piece */
					for (int j = 0; j < possibleDestinations.size(); j++) {
						
						/* For each possibleDestination for the BLACK piece, check RED movements */
						for (int k = 0; k < possibleRedMovements.size(); k++) {
							
							/* Tile will be removed from possibleDestinations if tile is a target of a higher rank piece
							 * or if the BLACK piece is an Elephant and the RED is a Rat */
							if (possibleRedMovements.get(k).getLocation() == possibleDestinations.get(j).getLocation()
											&& (redPieceRank >= pieceRank || (piece instanceof StrongestPiece
															&& redPiece instanceof WeakestPiece))) {
								
								/* Remove the stupid Tile */
								possibleDestinations.remove(j);
								
								/* Recursive call to avoid ArrayOutOfBounds */
								isMoveStupid(piece, possibleDestinations);
								
								/* Return the possibleDestinations of the BLACK piece */
								return possibleDestinations;
							}
						}
					}
				}
			}
		}
		/* In case no stupid moves have been found, return the same possibleDestinations passed as argument */
		return possibleDestinations;
	}
}