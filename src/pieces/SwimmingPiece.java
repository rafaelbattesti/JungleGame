package pieces;
import logic.piece.Piece;

/**
 * This Class intends to define the methods to instantiate a Swimming piece.<br>
 * A SwimmingPiece cannot take any opponent's Piece when leaving the WaterTile.<br>
 * A SwimmingPiece can take another SwimmingPiece while both are on WaterTile.<br>
 * Please refer to subclass WeakestPiece for details about the Rat taking the Elephant.
 * 
 * @author  Rafael Battesti for Jungle Project 2015 - PROG10082 - Java 1
 * @version 1.0
 * @since   2015-04-19
 */
public class SwimmingPiece extends Piece{


	/**
	 * Serialized Field.
	 * @serialField
	 */
	private static final long serialVersionUID = 5044392497340057309L;

	/**
	 * Constructor inherited from Piece as is.
	 * @param side Side which the Piece belongs. Check ConstantValues class.
	 * @param speed Integer representing the speed a Piece can move.
	 * @param usualRank Piece's usual rank. Reset after zeroed in the TrapTile
	 * @param name Name of the Piece.
	 */
	public SwimmingPiece(int side, int speed, int usualRank, String name) {
		super(side, speed, usualRank, name);
	}

	/**
	 * Method to validate whether a SwimmingPiece can take another Piece.<br>
	 * A SwimmingPiece cannot take any opponent's Piece when leaving the WaterTile.<br>
	 * A SwimmingPiece can take another SwimmingPiece while both are on WaterTile.
	 * @return True if Piece can be taken.
	 */
	public boolean canTakePiece(Piece pieceToBeTaken) {
		
		/* If there is a piece to be taken, validate. Otherwise return true. */
		if (pieceToBeTaken != null) {
			
			/* Defines variables to hold pieces details */
			int thisRow = _location.getRow();
			int thisCol = _location.getColumn();
			int pieceToBeTakenRow = pieceToBeTaken.getLocation().getRow();
			int pieceToBeTakenCol = pieceToBeTaken.getLocation().getColumn();
			boolean thisOnWater = (thisRow > 2 && thisRow < 6)
							&& (thisCol > 0 && thisCol < 6 && thisCol != 3);
			boolean pieceIsOut = !((pieceToBeTakenRow > 2 && pieceToBeTakenRow < 6)
							&& (pieceToBeTakenCol > 0 && pieceToBeTakenCol < 6 && pieceToBeTakenCol != 3));
			
			/* Validates whether a piece can be taken or not */
			if ((thisOnWater && pieceIsOut) || pieceToBeTaken.getSide() == _side) {
				return false;
			} else {
				return true;
			}
	
		} else {
			return true;
		}
	}
}
