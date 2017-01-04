package tiles;
import logic.ConstantValues;
import logic.Coordinate;
import logic.piece.Piece;
import logic.tile.Tile;

/**
 * This Class intends to define the methods to instantiate a TrapTile.<br>
 * If one of your pieces is on a trap, take care, anyone can take it!<br>
 * Likewise, if your opponent's have a piece on a trap, it is the perfect opportunity to 
 * take it using a lower rank piece!
 * 
 * @author  Rafael Battesti for Jungle Project 2015 - PROG10082 - Java 1
 * @version 1.0
 * @since   2015-04-19
 */
public class TrapTile extends Tile{

	/**
	 * Serialized Field
	 * @serialField
	 */
	private static final long serialVersionUID = 5671030759352267034L;

	/**
	 * Constructor inherited from Tile as is.
	 * @param location Instance of a Coordinate containing a row and a column.
	 * @param name Name of the Tile to be created.
	 */
	public TrapTile (Coordinate location, String name) {
		super(location, name);
	}

	/**
	 * Method to set the Trap.<br>
	 * Runs when user clicks on the piece next to the Trap.<br>
	 * A piece willing to enter the Trap tile will set any opponent's piece on the 
	 * Trap rank to 1.<br>
	 * If the user decides not to take the piece on the trap, 
	 * next round the rank is resetRank() by the removePiece() method.
	 * @return True if Piece can enter the TrapTile.
	 */
	public boolean canPieceEnter (Piece pieceToPlace) {
		
		/* Validates whether a Piece can enter the trap or not. Sets the rank to the Weakest */
		if (getPlacedPiece() == null) {
			return true;
		} else if (getPlacedPiece().getSide() != pieceToPlace.getSide()) {
			getPlacedPiece().setRank(ConstantValues.WEAKEST_PIECE_RANK);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method inherited from Tile class to remove pieces from tiles when they are taken.<br> 
	 * Adds the ability of a Piece to reestablish its usual rank when on the Trap before leaving.<br>
	 * Runs after every round. 
	 * @return super.removePiece() after reseting Trap Piece rank.
	 */
	public Piece removePiece() {
		
		/* If there is a piece on the Trap tile, reset it's rank to usual */
		if (getPlacedPiece() != null) {
			getPlacedPiece().resetRank();
		}
		
		return super.removePiece();
	}
}
