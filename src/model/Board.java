package model;
import logic.PieceLogic;
import piece.*;

/**
 * @author Will
 *
 */
public class Board {
	//private static final long serialVersionUID = 9195029973755808176L;
	public Piece [][] spaces = null;
	
	public Board() {
		spaces = new Piece[8][8];
	}

	/**
	 * Copy constructor
	 * @param other the board to copy
	 */
	public Board(Board other) {
		if(other == null){
			spaces = new Piece[8][8];
			return;
		}
		spaces = new Piece[8][8];
		copy(other);
	}
	
	/** 
	 * A copy helper function that performs
	 * a deep copy of every element in the other board.
	 * @param other
	 */
	public void copy(Board other) {
		for (int rank = 0; rank < spaces.length; rank++) {
			for (int file = 0; file < spaces[0].length; file++) {
				Piece piece = other.spaces[rank][file];
				if(piece == null) continue;
				if (piece instanceof King) 	this.spaces[rank][file] = new King();
				if (piece instanceof Queen) 	this.spaces[rank][file] = new Queen();
				if (piece instanceof Bishop) this.spaces[rank][file] = new Bishop();
				if (piece instanceof Knight) this.spaces[rank][file] = new Knight();
				if (piece instanceof Rook)	this.spaces[rank][file] = new Rook();
				if (piece instanceof Pawn)	this.spaces[rank][file] = new Pawn();
				if (piece instanceof Rock)	this.spaces[rank][file] = new Rock();
				if (piece instanceof Sentry) this.spaces[rank][file] = new Sentry();
				this.spaces[rank][file] = PieceLogic.copyPiece(this.spaces[rank][file], piece);
			}
		}
	}
	
	public Piece[][] getSpaces() {
		return spaces;
	}

	public void setSpaces(Piece[][] spaces) {
		this.spaces = spaces;
	}

	/**
	 * Restarts the current board to the 
	 * unitialized state with no pieces.
	 */
	public void reset() {
		for (int rank = 0; rank < spaces.length; rank++) {
			for (int file = 0; file < spaces[0].length; file++) {
				spaces[rank][file] = null;
			}
		}
	}
}
