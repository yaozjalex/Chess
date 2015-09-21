package piece;
import java.util.Vector;

import logic.*;
import model.Board;
import model.Space;


public class Knight extends Piece {
	private static final int [][] L_MOVES = {{ 2, 1},{ 2,-1},
											 {-2, 1},{-2,-1},
											 {-1, 2},{-1,-2},
											 { 1, 2},{ 1,-2}};
	
	/* (non-Javadoc)
	 * @see Piece#updateMoves(Piece[][], int, int)
	 */
	public void updateMoves(Board board) {
		moves = new Vector<Space>();
		int rank = this.rank;
		int file = this.file;
		
		for (int r,f,it = 0; it < L_MOVES.length; it++) {
			r = rank + L_MOVES[it][0];
			f = file + L_MOVES[it][1];
			
			if (!BoardLogic.outOfBounds(board,r,f))
				PieceLogic.addMove(this, board.spaces[r][f],r ,f);
		}
	}
}