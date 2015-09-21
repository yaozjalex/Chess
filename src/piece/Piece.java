package piece;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Vector;

import model.Board;
import model.Space;


/**
 * @author Alex
 * 
 */
abstract public class Piece{
		
    /**
	 * @param color
	 * @param moves
	 * @param bufferedImage
	 * @param rank
	 * @param file
	 */

	/**
     * The color of a piece. (BLACK, WHITE, NULL)
     */
    protected Color color = null;
    /**
     * The possible moves for a piece.
     */
    protected Vector <Space> moves = null;
    
    /**
     * The bufferedImage of a piece
     */
    protected BufferedImage bufferedImage;
    
    /**
     * rank of piece
     */
    protected int rank;
    
    /**
     * file of piece
     */
    protected int file;
    
	/**
	 * Upates the possible moves for a given piece.
	 * @param spaces The state of the chess board.	 
	 */
	abstract public void updateMoves(Board board);

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the moves
	 */
	public Vector<Space> getMoves() {
		return moves;
	}


	/**
	 * @param moves the moves to set
	 */
	public void setMoves(Vector<Space> moves) {
		this.moves = moves;
	}

	/**
	 * @return the bufferedImage
	 */
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	/**
	 * @param bufferedImage the bufferedImage to set
	 */
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * @return the file
	 */
	public int getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(int file) {
		this.file = file;
	}

}