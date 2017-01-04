package pieces;
import logic.piece.Piece;
import logic.piece.StandardJunglePiece;

/**
 * This Class intends to define the methods to instantiate a StrongestPiece.<br>
 * A StrongestPiece can take any opponent's Piece but the Rat.
 * 
 * @author  Rafael Battesti for Jungle Project 2015 - PROG10082 - Java 1
 * @version 1.0
 * @since   2015-04-19
 */
public class StrongestPiece extends StandardJunglePiece{

	/**
	 * Serialized Field
	 * @serialField
	 */
	private static final long serialVersionUID = 7088673361333456249L;

	/**
	 * Constructor inherited from Piece as is.
	 * @param side Side which the Piece belongs. Check ConstantValues class.
	 * @param speed Integer representing the speed a Piece can move.
	 * @param usualRank Piece's usual rank. Reset after zeroed in the TrapTile
	 * @param name Name of the Piece.
	 */
	public StrongestPiece(int side, int speed, int usualRank, String name) {
		super(side, speed, usualRank, name);
	}

	/**
	 * Method to validate whether a StrongestPiece can take another Piece.<br>
	 * In this project, StrongestPiece extends StandardJunglePiece and represents the Elephant.<br>
	 * The Elephant can take any opponent's Piece apart from the Rat.
	 * @return True if Piece can be taken.
	 */
	public boolean canTakePiece(Piece pieceToBeTaken) {
		
		/* If there is a Piece to be taken, validate. Otherwise, return true. */
		if (pieceToBeTaken != null) {
			
			/* Validates whether a Piece can be taken or not */
			if (pieceToBeTaken.getSide() == _side || pieceToBeTaken instanceof WeakestPiece) {
				return false;
			} else {
				return true;
			}
			
		} else {
			return true;
		}
	}	
	
}
