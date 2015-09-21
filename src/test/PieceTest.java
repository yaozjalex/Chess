package test;

import static org.junit.Assert.*;

import java.awt.Color;

import model.Board;
import logic.BoardLogic;
import logic.PieceLogic;
import piece.*;

import org.junit.Before;
import org.junit.Test;

public class PieceTest {

	static Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}
	
	@Test 
	public void outofBoundsSetPiece() {
		assertFalse("Out-of-bounds set attempted.",BoardLogic.setPiece(board, new Rook(),-1,0,Color.WHITE));
		assertFalse("Out-of-bounds set attempted.",BoardLogic.setPiece(board, new Rook(),0,-1,Color.WHITE));
		assertFalse("Out-of-bounds set attempted.",BoardLogic.setPiece(board, new Rook(),0,100,Color.WHITE));
		assertFalse("Out-of-bounds set attempted.",BoardLogic.setPiece(board, new Rook(),100,0,Color.WHITE));
	}
	
	@Test
	public void validSetPiece() {
		assertTrue(BoardLogic.setPiece(board, new Rook(), 0, 0, Color.WHITE));
		assertNotNull(BoardLogic.getPiece(board, 0,0));
	}
	
	
	@Test
	public void doubleSetPiece() {
		BoardLogic.setPiece(board, new Rook(), 0, 0, Color.WHITE);
		assertFalse("Double set failed",BoardLogic.setPiece(board, new Rook(), 0, 0, Color.WHITE));
	}

	@Test
	public void emptyPieceMove() {
		Piece piece = new Rook();
		assertFalse("Invalid move on unplaced piece.",PieceLogic.isValidMove(piece, -1, -1));
	}
}