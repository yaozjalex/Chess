package piece;
import java.util.Vector;

import logic.PieceLogic;
import model.Board;
import model.Space;


public class Bishop extends Piece {

	/* (non-Javadoc)
	 * @see Piece#updateMoves(Piece[][], int, int)
	 */
	public void updateMoves(Board board) {
		moves = new Vector<Space>();
		int rank = this.rank;
		int file = this.file;
		for (int r = rank+1,f = file+1; r < board.spaces.length && f < board.spaces[0].length; r++,f++) {
			if (PieceLogic.addMove(this, board.spaces[r][f], r, f))
				break;
		}
		
		for (int r = rank-1,f = file+1; r >= 0 && f < board.spaces[0].length; r--,f++) {
			if (PieceLogic.addMove(this, board.spaces[r][f], r, f))
				break;
		}
		
		for (int r = rank+1,f = file-1; r < board.spaces.length && f >= 0; r++,f--) {
			if (PieceLogic.addMove(this, board.spaces[r][f], r, f))
				break;
		}
		
		for (int r = rank-1,f = file-1; r >= 0 && f >= 0; r--,f--) {
			if (PieceLogic.addMove(this, board.spaces[r][f],r, f))
				break;
		}
	}
}