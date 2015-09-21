package logic;

import java.awt.Color;
import java.util.Vector;

import model.Board;
import model.Space;
import piece.King;
import piece.Piece;

public class BoardLogic {
	/**
	 * We need to maintain possible moves
	 * for each piece to detect game-ending conditions.
	 * @param board the board to update pieces
	 */
	public static void updatePieces(Board board) {
		for (int rank = 0; rank < board.spaces.length; rank++) {
			for (int file = 0; file < board.spaces[0].length; file++) {
				Piece curSpace = board.spaces[rank][file];
				if (curSpace != null) {
					curSpace.updateMoves(board);
				}
			}
		}
	}
	
	/**
	 * @param board the board to check whether the player is inCheck
	 * @param player the color of the player check for check
	 * @return true if the given player is in check
	 */
	public static boolean inCheck(Board board, Color player) {
		Piece king = findKing(board, player);
		
		if (king == null)
			return false;
		
		Piece piece;
		for (int rank = 0; rank < board.spaces.length; rank++) {
			for (int file = 0; file < board.spaces[0].length; file++) {
				piece = board.spaces[rank][file];
				if (piece != null && piece.getColor() != player && PieceLogic.isValidMove(piece, king.getRank(), king.getFile())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * @param board the board to to check whether the player is inCheckmate
	 * @param player the color of the player to check for checkmate
	 * @return true if the given player is in checkmate
	 */
	public static boolean inCheckmate(Board board, Color player) {
		
		if (!inCheck(board, player))
			return false;
		
		for (int rank = 0; rank < board.spaces.length; rank++) {
			for (int file = 0; file < board.spaces[0].length; file++) {
				Piece piece = board.spaces[rank][file];
				if (piece != null 
				&&  piece.getColor() == player
				&&	movesOnBoard(board, piece).size() != 0)
					return false;
			}
		}
		
		return true;
		
	}
	
	/**
	 * @param board the board to check whether the player is inStalemate
	 * @param player the color of player to check for stalemate
	 * @return true if in stalemate
	 */
	public static boolean inStalemate(Board board, Color player) {
		boolean flag = false;
		if (!inCheck(board, player)) {
			for (int rank = 0; rank < board.spaces.length; rank++) {
				for (int file = 0; file < board.spaces[0].length; file++) {
					Piece piece = board.spaces[rank][file];
					if(piece instanceof King){
						flag = true;
					}
					if (piece != null 
					&&  piece.getColor() == player
					&&	movesOnBoard(board, piece).size() != 0)
						return false;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @param board
	 * @param piece
	 * @return available moves of piece on board
	 */
	public static Vector<Space> movesOnBoard(Board board, Piece piece){
		Vector<Space> moves = piece.getMoves();
		int rank = piece.getRank();
		int file = piece.getFile();		
		for(int i = 0; i<moves.size();){
			Board copy = new Board(board);
			copy.spaces[moves.elementAt(i).getRank()][moves.elementAt(i).getFile()] = copy.spaces[piece.getRank()][piece.getFile()];
			copy.spaces[rank][file] = null;
			copy.spaces[moves.elementAt(i).getRank()][moves.elementAt(i).getFile()].setRank(moves.elementAt(i).getRank());
			copy.spaces[moves.elementAt(i).getRank()][moves.elementAt(i).getFile()].setFile(moves.elementAt(i).getFile());			
			updatePieces(copy);
			if (inCheck(copy, piece.getColor())) {
				moves.remove(i);
			}
			else{
				i++;
			}
		}
		return moves;
	}
	
	/**
	 * Moves a piece on the board from (rstart,fstart) to (rtarget,ftarget).
	 * @param board the board to move piece
	 * @param piece the piece to be moved
	 * @param rtarget the target rank (x position) of the move
	 * @param ftarget the target file (y position) of the move
	 * @return 1 if outOfBound, 2 if it's the same owner, 
	 * false otherwise
	 */
	public static boolean movePiece(Board board, Piece piece, int rtarget, int ftarget) {
		Vector<Space> availableMoves = movesOnBoard(board, piece);
		for(int i = 0; i<availableMoves.size(); i++){
			if(availableMoves.elementAt(i).getRank() == rtarget && availableMoves.elementAt(i).getFile() == ftarget){
				board.spaces[rtarget][ftarget] = piece;
				board.spaces[piece.getRank()][piece.getFile()] = null;				
				piece.setRank(rtarget);
				piece.setFile(ftarget);
				updatePieces(board);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Set piece on board
	 * @param board the board to set piece
	 * @param piece the piece to set 
	 * @param rank the x (rank) location of the piece
	 * @param file the y (file) location of the piece
	 * @param player the owner of the piece (WHITE,BLACK)
	 * @return true if the piece was set successfully
	 */
	public static boolean setPiece(Board board, Piece piece, int rank, int file, Color player) {
		if (outOfBounds(board, rank,file)) 
			return false;
		if (board.spaces[rank][file] != null)
			return false;
		piece = PieceLogic.setUpPiece(piece,player);
		piece.setRank(rank);
		piece.setFile(file);
		piece.setMoves(new Vector<Space>());
		board.spaces[rank][file] = piece;		
		updatePieces(board);		
		return true;
	}
		
	/**
	 * Get piece on board
	 * @param board the board to get piece
	 * @param rank the x (rank) location of the piece
	 * @param file the y (file) location of the piece
	 * @return the piece at a given space
	 */
	public static Piece getPiece(Board board, int rank, int file) {
		return board.spaces[rank][file];
	}
	
	/**
	 * Helper function that checks whether a given (rank,file) pair is within the board range.
	 * @param board the board to check
	 * @param rank the x (rank) location of the piece
	 * @param file the y (file) location of the piece
	 * @return true if the position given is 
	 * not a valid board position.
	 */
	public static boolean outOfBounds(Board board, int rank, int file) {
		return (rank < 0) || (file < 0) || (rank >= board.spaces.length) || (file >= board.spaces[0].length);
	}

	/**
	 * Helper function that find the player's king's position on board
	 * @param board the board to find king
	 * @param player the color of the player to find the king for
	 * @return the space (rank,file) the player's king  is on
	 */
	private static Piece findKing(Board board, Color player) {
		Piece king = null;
		for (int rank = 0; rank < board.spaces.length; rank++) {
			for (int file = 0; file < board.spaces[0].length; file++) {
				Piece curPiece = board.spaces[rank][file];
				if (curPiece != null && curPiece instanceof King &&	curPiece.getColor() == player) {
					king = curPiece;
					return king;
				}
			}
		}
		return king;
	}
}
