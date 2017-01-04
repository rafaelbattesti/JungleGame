package pieces;
import pieces.SwimmingPiece;
import pieces.StrongestPiece;
import logic.piece.Piece;

/**
 * This Class intends to define the methods to instantiate a WeakestPiece.<br>
 * A WeakestPiece can take an opponent's WeakestPiece and StrongestPiece, if not leaving the Water<br>
 * (validated by super.canTakePiece(Piece pieceToBeTaken).
 * 
 * @author  Rafael Battesti for Jungle Project 2015 - PROG10082 - Java 1
 * @version 1.0
 * @since   2015-04-19
 */
public class WeakestPiece extends SwimmingPiece{


	/**
	 * Serialized Field
	 * @serialField
	 */
	private static final long serialVersionUID = -3041059891006973875L;

	/**
	 * Constructor inherited from SwimmingPiece as is.
	 * @param side Side which the Piece belongs. Check ConstantValues class.
	 * @param speed Integer representing the speed a Piece can move.
	 * @param usualRank Piece's usual rank. Reset after zeroed in the TrapTile
	 * @param name Name of the Piece.
	 */
	public WeakestPiece(int side, int speed, int usualRank, String name) {
		super(side, speed, usualRank, name);
	}
	
	/**
	 * Method to validate whether a WeakestPiece can take another Piece.<br>
	 * Depends on the returning value of the superclass method, which determines whether a 
	 * SwimmingPiece can take a piece or not.<br>
	 * In this project, WeakestPiece extends SwimmingPiece.
	 * @return True if Piece can be taken.
	 */
	public boolean canTakePiece(Piece pieceToBeTaken) {
		
		/* If there is a Piece to be taken, validate. Otherwise, return true. */
		if (pieceToBeTaken != null) {
			
			/* Validates whether a Piece can be taken or not. Calls super to validate. */
			if (pieceToBeTaken instanceof WeakestPiece 
							|| pieceToBeTaken instanceof StrongestPiece) {
				return super.canTakePiece(pieceToBeTaken) && true;
			} else {
				return false;
			}
			
		} else {
			return true;
		}
		
	}
}
