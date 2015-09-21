package test;

import static org.junit.Assert.*;

import java.awt.Color;

import model.Board;
import logic.BoardLogic;
import piece.*;

import org.junit.*;


public class RookTest {

	Board board;
	Piece rook1;
		
	@Before
	public void setUp() throws Exception {
		board = new Board();
		rook1 = new Rook();
		BoardLogic.setPiece(board, rook1,0,0,Color.WHITE);
	}
	
	@Test
	public void rookMove() {
		assertTrue("Move completed.",BoardLogic.movePiece(board, rook1, 6, 0));
		assertNull("Original piece deleted.",BoardLogic.getPiece(board, 0, 0));
		assertNotNull("New piece placed.",BoardLogic.getPiece(board, 6, 0));
	}
	
	@Test
	public void outOfBoundsMove() {
		assertFalse("Move did not complete.",BoardLogic.movePiece(board, rook1, -1, -1));
		assertFalse("Move did not complete.",BoardLogic.movePiece(board, rook1, 9, 9));
	}
	
	@Test
	public void diagonalMove() {
		assertFalse("Move failed.",BoardLogic.movePiece(board, rook1, 6, 6));
	}
	
	@Test
	public void weirdMove() {
		assertFalse("L-shaped path is invalid.",BoardLogic.movePiece(board, rook1, 4, 1));
	}
	
	@Test
	public void captureFriendly() {
		BoardLogic.setPiece(board, new Rook(), 1, 0, Color.WHITE);
		assertFalse("Capture failed.",BoardLogic.movePiece(board, rook1, 1, 0));
	}
	
	@Test
	public void captureOpponent() {
		BoardLogic.setPiece(board, new Rook(), 1, 0, Color.BLACK);
		assertTrue("Capture succeeded.",BoardLogic.movePiece(board, rook1, 1, 0));
	}
	
	@Test
	public void jumpFriendlyPieces() {
		BoardLogic.setPiece(board, new Rook(), 1, 0, Color.WHITE);
		assertFalse("Jumping failed.",BoardLogic.movePiece(board, rook1, 2, 0));
	}
	
	@Test
	public void jumpEnemyPiece() {
		BoardLogic.setPiece(board, new Rook(), 1, 0, Color.BLACK);
		BoardLogic.setPiece(board, new Rook(), 2, 0, Color.BLACK);
		assertFalse("Jump failed.",BoardLogic.movePiece(board, rook1
				, 2, 0));
	}
}