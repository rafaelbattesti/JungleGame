package tiles;
import logic.ConstantValues;
import logic.Coordinate;
import logic.piece.Piece;
import logic.tile.Tile;

/**
 * This Class intends to define the methods to instantiate a DenTile.<br>
 * If your opponent occupies your Den, you are finished!<br>
 * A DenTile do not allow a Piece of the same side to enter it.
 * 
 * @author  Rafael Battesti for Jungle Project 2015 - PROG10082 - Java 1
 * @version 1.0
 * @since   2015-04-19
 */
public class DenTile extends Tile {

	/**
	 * Serialized Field.
	 * @serialField
	 */
	private static final long serialVersionUID = -2728136878603966483L;

	/**
	 * Constructor inherited from Tile as is.
	 * @param location Instance of a Coordinate containing a row and a column.
	 * @param name Name of the Tile to be created.
	 */
	public DenTile(Coordinate location, String name) {
		super(location, name);
	}
	
	/**
	 * Method to determine if a piece can enter the Den. RED_SIDE pieces cannot enter the Red Den 
	 * and BLACK_SIDE cannot enter the Blue Den.
	 * @return True if Piece can enter the DenTile.
	 */
	public boolean canPieceEnter (Piece pieceToPlace) {
		if (this.getLocation().getRow() == 0) {
			return pieceToPlace.getSide() == ConstantValues.RED_SIDE ? true : false;
		} else {
			return pieceToPlace.getSide() == ConstantValues.BLACK_SIDE ? true : false;
		}
	}

}
