package piece;
import java.util.Vector;

import logic.PieceLogic;
import model.Board;
import model.Space;


public class Rook extends Piece {
		
	/* (non-Javadoc)
	 * @see Piece#updateMoves(Piece[][], int, int)
	 */
	public void updateMoves(Board board) {
		moves = new Vector<Space>();
		int rank = this.rank;
		int file = this.file;
	
		for (int r = rank+1; r < board.spaces.length; r++) {
			if (PieceLogic.addMove(this, board.spaces[r][file], r, file)) 
				break;
		} // right
		
		for (int r = rank-1; r >= 0; r--) {
			if (PieceLogic.addMove(this, board.spaces[r][file], r, file)) 
				break;
		} // left
		
		for (int f = file+1; f < board.spaces[0].length; f++) {
			if (PieceLogic.addMove(this, board.spaces[rank][f], rank, f)) 
				break;
		} // down
		
		for (int f = file-1; f >= 0; f--) {
			if (PieceLogic.addMove(this, board.spaces[rank][f], rank, f)) 
				break;
		} // up
	}

}