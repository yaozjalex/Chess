package logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rock;
import piece.Rook;
import piece.Sentry;
import model.Space;

public class PieceLogic {
	/**
	 * @param target the (rank,file) pair on the board to move to
	 * @return true if a given move is possible for a piece
	 */
	public static boolean isValidMove(Piece piece, int rank, int file) {
		if (piece.getMoves() == null) 
			return false;
			
		for (int it = 0; it < piece.getMoves().size(); it++) {
			if (piece.getMoves().elementAt(it).getRank() == rank && piece.getMoves().elementAt(it).getFile() == file)
				return true;
		}
		
		return false;
	}
	
	/**
	 * @param other the piece to compare the current one with
	 * @return true if one piece has the same owner as the other
	 */
	public static boolean sameOwner(Piece piece, Piece other) {
		if (other == null)
			return false;
		return piece.getColor() == other.getColor();
	}
	
	/**
	 * Converts a string to an instance of a new piece.
	 * @param piece the type of piece to instantiate
	 * @param player the color of piece to initialize
	 * @return the initialized piece matching the string input
	 */
	public static Piece setUpPiece(Piece piece, Color player) {
		piece.setColor(player);
		piece.setBufferedImage(getPieceImage(piece));		
		return piece;
	}
	
	public static Piece copyPiece(Piece piece, Piece other){
		piece.setBufferedImage(other.getBufferedImage());
		piece.setColor(other.getColor());
		piece.setRank(other.getRank());
		piece.setFile(other.getFile());
		piece.setMoves(other.getMoves());
		return piece;
	}
	
	/**
	 * UpdateMoves helper that
	 * checks if a given square should be added.
	 * It must not have the same owner or 
	 * @return true if the piece cannot jump past the square
	 */
	public static boolean addMove(Piece piece, Piece curSpace, int rank, int file) {
		if (curSpace == null) {
			piece.getMoves().add(new Space(rank,file));
			return false;
		} 
		else if (sameOwner(piece, curSpace)) {
			return true;
		} 
		else {
			piece.getMoves().add(new Space(rank,file));
			return true;
		}
	}
	
	/**
	 * @return the image file on disk representing the piece
	 */
	public static BufferedImage getPieceImage(Piece piece) {
		BufferedImage pieceImage = null;
		String filename = "./images/";
		
		if (piece.getColor() == null) 			return pieceImage;
		if (piece.getColor() == Color.BLACK) 	filename += "black/";
		else if (piece.getColor() == Color.WHITE) 	filename += "white/";
		
		if (piece instanceof King) 	filename += "king.png";
		if (piece instanceof Queen) 	filename += "queen.png";
		if (piece instanceof Bishop) filename += "bishop.png";
		if (piece instanceof Knight) filename += "knight.png";
		if (piece instanceof Rook)	filename += "rook.png";
		if (piece instanceof Pawn)	filename += "pawn.png";
		if (piece instanceof Rock)	filename += "rock.png";
		if (piece instanceof Sentry) filename += "sentry.png";
		
		try {
			pieceImage = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.out.println("File read failed.");
		}
		
		return pieceImage;
	}
}
