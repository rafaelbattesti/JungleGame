package tiles;
import pieces.SwimmingPiece;
import pieces.JumpingPiece;
import logic.ConstantValues;
import logic.Coordinate;
import logic.piece.Piece;
import logic.tile.Tile;

/**
 * This Class intends to define the methods to instantiate a WaterTile.<br>
 * The WaterTile allows a SwimmingPiece to enter.<br>
 * The WaterTile also allows a JumpingPiece to jump over it, as long as there are no SwimmingPiece on it.
 * 
 * @author  Rafael Battesti for Jungle Project 2015 - PROG10082 - Java 1
 * @version 1.0
 * @since   2015-04-19
 */
public class WaterTile extends Tile {

	/**
	 * Serialized Field
	 * @serialField
	 */
	private static final long serialVersionUID = 318835319922887463L;

	/**
	 * Method inherited from Tile as is.
	 * @param location Instance of a Coordinate containing a row and a column.
	 * @param name Name of the Tile to be created.
	 */
	public WaterTile(Coordinate location, String name) {
		super(location, name);
	}
	
	/**
	 * Method to determine whether a Piece can jump over it or not.<br>
	 * A JumpingPiece is allowed to jump over it, as long as there is not a SwimmingPiece on it.
	 * @return True if the Piece can Jump over.
	 */
	public boolean canJumpOver (Piece pieceToJump) {
		if (pieceToJump instanceof JumpingPiece 
						&& (getPlacedPiece() == null || getPlacedPiece().getRank() != ConstantValues.WEAKEST_PIECE_RANK)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method to determine whether a Piece can enter it or not.<br>
	 * A SwimmingPiece is allowed to enter the WaterTile with no restrictions.
	 * @return True if Piece can enter the WaterTile.
	 */
	public boolean canPieceEnter (Piece pieceToSwim) {
		return pieceToSwim instanceof SwimmingPiece ? true : false;
	}

	
}
