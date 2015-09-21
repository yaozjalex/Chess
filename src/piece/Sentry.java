package piece;
import java.util.Vector;

import model.Board;
import model.Space;


public class Sentry extends Piece {
	
	public void updateMoves(Board board) {
		moves = new Vector<Space>();
		int rank = this.rank;
		int file = this.file;
		
		Piece queen = new Queen();
		queen.setColor(color);
		queen.setRank(rank);
		queen.setFile(file);
		queen.updateMoves(board);
		
		int rankdiff, filediff;
		Space curSpace;
		
		for (int it = 0; it < queen.moves.size(); it++) {
			curSpace = queen.moves.elementAt(it);
			filediff = Math.abs(curSpace.getFile() - file);
			rankdiff = Math.abs(curSpace.getRank() - rank);
			
			if (filediff <= 2 && rankdiff <= 2)
				moves.add(curSpace);
		}
		
	}

}