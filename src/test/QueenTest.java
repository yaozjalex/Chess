package test;

import static org.junit.Assert.*;

import java.awt.Color;

import model.Board;
import logic.BoardLogic;
import piece.*;

import org.junit.Before;
import org.junit.Test;


public class QueenTest {
	
	Board board;
	Piece queen1;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		queen1 = new Queen();
		BoardLogic.setPiece(board, queen1, 0, 0, Color.WHITE);
	}
	
	@Test
	public void diagonalMove() {
		assertTrue("Queen can move diagonally.",BoardLogic.movePiece(board, queen1,4,4));
	}
	
	@Test
	public void horizontalMove() {
		assertTrue("Queen can move horizontally.",BoardLogic.movePiece(board, queen1,4,0));
	}
	
	@Test
	public void verticalMove() {
		assertTrue("Queen can move vertically.",BoardLogic.movePiece(board, queen1,4,4));
	}
	
	@Test
	public void wierdMove() {
		assertFalse("Queen cannot move in an L-shape.",BoardLogic.movePiece(board, queen1,4,1));
	}

	@Test
	public void captureFriendly() {
		BoardLogic.setPiece(board, new Rook(),1,1,Color.WHITE);
		assertFalse("Queen cannot capture friendly pieces.",BoardLogic.movePiece(board, queen1,1,1));
	}
	
	@Test
	public void captureOpponent() {
		BoardLogic.setPiece(board, new Rook(),1,1,Color.BLACK);
		assertTrue("Queen captured enemy piece.",BoardLogic.movePiece(board, queen1,1,1));
		assertSame("Queen moved to enemy square.",Color.WHITE,BoardLogic.getPiece(board, 1, 1).getColor());
	}
	
	@Test
	public void jumpFriendly() {
		BoardLogic.setPiece(board, new Rook(),1,1,Color.WHITE);
		assertFalse("Queen cannot jump friendly pieces.",BoardLogic.movePiece(board, queen1,2,2));
	}
	
	@Test
	public void jumpEnemy() {
		BoardLogic.setPiece(board, new Rook(), 2, 2, Color.BLACK);
		assertFalse("Queen cannot jump enemy pieces.",BoardLogic.movePiece(board, queen1,4,4));
	}
}