package piece;

import java.util.Vector;

import model.Board;
import model.Space;


public class Rock extends Piece {

	/* (non-Javadoc)
	 * @see Piece#updateMoves(Piece[][], int, int)
	 */

	public void updateMoves(Board board) {
		moves = new Vector<Space>();
	}

}