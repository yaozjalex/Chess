package piece;
import java.awt.Color;
import java.util.Vector;

import logic.BoardLogic;
import model.Board;
import model.Space;


public class Pawn extends Piece {
	
	public void updateMoves(Board board) {
		moves = new Vector<Space>();
		int rank = this.rank;
		int file = this.file;
		
		if (color == Color.BLACK) {
			if (this.getRank() == 1) {
				if (!BoardLogic.outOfBounds(board,rank+2,file) && board.spaces[rank+1][file] == null && board.spaces[rank+2][file] == null)
					this.getMoves().add(new Space(rank+2, file));
			}
			if (!BoardLogic.outOfBounds(board,rank+1,file+1) && board.spaces[rank+1][file+1] != null && board.spaces[rank+1][file+1].getColor() == Color.WHITE) 
				this.getMoves().add(new Space(rank+1, file+1));
			if (!BoardLogic.outOfBounds(board,rank+1,file-1) && board.spaces[rank+1][file-1] != null && board.spaces[rank+1][file-1].getColor() == Color.WHITE) 
				this.getMoves().add(new Space(rank+1, file-1));
			if (!BoardLogic.outOfBounds(board,rank+1,file) && board.spaces[rank+1][file] == null)
				this.getMoves().add(new Space(rank+1, file));
		}
		else {
			if (this.getRank() == 6) {
				if (!BoardLogic.outOfBounds(board,rank-2,file) && board.spaces[rank-1][file] == null && board.spaces[rank-2][file] == null)
					this.getMoves().add(new Space(rank-2, file));
			}
			if (!BoardLogic.outOfBounds(board,rank-1,file+1) && board.spaces[rank-1][file+1] != null && board.spaces[rank-1][file+1].getColor() == Color.BLACK) 
				this.getMoves().add(new Space(rank-1, file+1));
			if (!BoardLogic.outOfBounds(board,rank-1,file-1) && board.spaces[rank-1][file-1] != null && board.spaces[rank-1][file-1].getColor() == Color.BLACK) 
				this.getMoves().add(new Space(rank-1, file-1));
			if (!BoardLogic.outOfBounds(board,rank-1,file) && board.spaces[rank-1][file] == null)
				this.getMoves().add(new Space(rank-1, file));
		}
	}

}