package controller;

import piece.Piece;

public class Step{
	public int rank_i;
	public int file_i;
	public int rank_f;
	public int file_f;
	public Piece eatenPiece;
	/**
	 * @param rank_i
	 * @param file_i
	 * @param rank_f
	 * @param file_f
	 * @param eatenPiece
	 */
	public Step(int rank_i, int file_i, int rank_f, int file_f, Piece eatenPiece
			) {
		super();
		this.rank_i = rank_i;
		this.file_i = file_i;
		this.rank_f = rank_f;
		this.file_f = file_f;
		this.eatenPiece = eatenPiece;
	}
	public Step() {
		// TODO Auto-generated constructor stub
	}
}