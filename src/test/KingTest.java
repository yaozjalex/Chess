package test;

import static org.junit.Assert.*;

import java.awt.Color;

import model.Board;
import logic.BoardLogic;
import piece.*;

import org.junit.Before;
import org.junit.Test;


public class KingTest {
	
	Board board;
	Piece king1;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		king1 = new King();
		BoardLogic.setPiece(board, king1, 4, 4, Color.WHITE);
	}

	@Test
	public void moveVertical() {
		assertTrue("King can move up.",BoardLogic.movePiece(board, king1, 4, 3));
		assertTrue("King can move down.",BoardLogic.movePiece(board, king1, 4, 4));
	}
	
	@Test
	public void moveHorizontal() {
		assertTrue("King can move left.",BoardLogic.movePiece(board, king1, 3, 4));
		assertTrue("King can move right.",BoardLogic.movePiece(board, king1, 4, 4));
	}
	
	@Test
	public void moveDiagonal() {
		assertTrue("King moves left-up.",BoardLogic.movePiece(board, king1, 3, 3));
		assertTrue("King moves right-up.",BoardLogic.movePiece(board, king1, 4, 2));
		assertTrue("King moves left-down.",BoardLogic.movePiece(board, king1, 3, 3));
		assertTrue("King moves right-down.",BoardLogic.movePiece(board, king1, 4, 4));
	}
	
	@Test
	public void moveMoreSpaces() {
		assertFalse("King cannot move 2 spaces diagonally.",BoardLogic.movePiece(board, king1, 6, 6));
		assertFalse("King cannot move 2 spaces horizontally.",BoardLogic.movePiece(board, king1, 4, 6));
		assertFalse("King cannot move 2 spaces vertically.",BoardLogic.movePiece(board, king1, 4, 8));
	}
	
	@Test
	public void captureFriendly() {
		BoardLogic.setPiece(board, new Rook(), 3, 3, Color.WHITE);
		assertFalse("King cannot capture a friendly piece.",BoardLogic.movePiece(board, king1, 3, 3));
	}
	
	@Test
	public void captureEnemy() {
		BoardLogic.setPiece(board, new Rook(), 3, 3, Color.BLACK);
		assertTrue("King captured an enemy piece.",BoardLogic.movePiece(board, king1, 3, 3));
		assertSame("King moved to enemy square.",Color.WHITE,BoardLogic.getPiece(board, 3, 3).getColor());
	}
}