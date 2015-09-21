package piece;
import java.util.Vector;

import model.Board;
import model.Space;


public class Queen extends Piece{

	/* (non-Javadoc)
	 * @see Piece#updateMoves(Piece[][], int, int)
	 */
	public void updateMoves(Board board) {
		moves = new Vector<Space>();
		
		Piece rook = new Rook();
		rook.setColor(color);
		rook.setRank(rank);
		rook.setFile(file);
		rook.updateMoves(board);
		
		Piece bishop = new Bishop();
		bishop.setColor(color);
		bishop.setRank(rank);
		bishop.setFile(file);
		bishop.updateMoves(board);
		
		for (int it = 0; it < rook.moves.size(); it++) {
			moves.add(rook.moves.elementAt(it));
		}
		
		for (int it = 0; it < bishop.moves.size(); it++) {
			moves.add(bishop.moves.elementAt(it));
		}
	}

}