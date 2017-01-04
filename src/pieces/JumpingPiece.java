package pieces;
import logic.piece.Piece;

/**
 * This Class intends to define the methods to instantiate a JumpingPiece.<br>
 * A JumpingPiece can jump across WaterTile as long as there is no SwimmingPiece in front of it.<br>
 * It can take any Piece on the other side of WaterTile, including the Rat, if it is out of the Water.
 * 
 * @author  Rafael Battesti for Jungle Project 2015 - PROG10082 - Java 1
 * @version 1.0
 * @since   2015-04-19
 */
public class JumpingPiece extends Piece{

	/**
	 * Serialized Field
	 * @serialField
	 */
	private static final long serialVersionUID = 5292212374071096287L;

	/**
	 * Method inherited from Piece as is.
	 * @param side Side which the Piece belongs. Check ConstantValues class.
	 * @param speed Integer representing the speed a Piece can move.
	 * @param usualRank Piece's usual rank. Reset after zeroed in the TrapTile
	 * @param name Name of the Piece.
	 */
	public JumpingPiece(int side, int speed, int usualRank, String name) {
		super(side, speed, usualRank, name);
	}

	/**
	 * Method to validate whether a JumpingPiece can take another Piece.<br>
	 * A JumpingPiece can take any opponent's piece with a lower rank.
	 * @return True if Piece can be taken.
	 */
	public boolean canTakePiece(Piece pieceToBeTaken) {
		
		/* If there is a Piece to be taken, validate. Otherwise, return true. */
		if (pieceToBeTaken != null) {
			
			/* Validates whether a Piece can be taken or not */
			if (pieceToBeTaken.getSide() == _side || pieceToBeTaken.getRank() > _rank) {
				return false;
			} else {
				return true;
			}
			
		} else {
			return true;
		}
		
	}	
}
