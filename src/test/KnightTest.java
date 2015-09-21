package test;

import static org.junit.Assert.*;

import java.awt.Color;

import model.Board;
import logic.BoardLogic;
import piece.*;

import org.junit.Before;
import org.junit.Test;


public class KnightTest {
	
	Board board;
	Piece knight1;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		knight1 = new Knight();
		BoardLogic.setPiece(board, knight1, 4, 4, Color.WHITE);
	}

	@Test
	public void knightMove() {
		assertTrue("Knight moves up-left.",BoardLogic.movePiece(board, knight1, 3, 2));
		assertTrue("Knight moves down-right.",BoardLogic.movePiece(board, knight1, 4, 4));
		assertTrue("Knight moves down-left.",BoardLogic.movePiece(board, knight1, 5, 6));
		assertTrue("Knight moves up-right.",BoardLogic.movePiece(board, knight1, 4, 4));
		assertTrue("Knight moves left-up.",BoardLogic.movePiece(board, knight1, 2, 3));
		assertTrue("Knight moves right-down.",BoardLogic.movePiece(board, knight1, 4, 4));
		assertTrue("Knight moves right-up.",BoardLogic.movePiece(board, knight1, 6, 3));
		assertTrue("Knight moves left-down.",BoardLogic.movePiece(board, knight1, 4, 4));
	}
	
	@Test 
	public void horizontalMove() {
		assertFalse("Knight cannot move horizontally 1 space right.",BoardLogic.movePiece(board, knight1, 5, 4));
		assertFalse("Knight cannot move horizontally 2 spaces left.",BoardLogic.movePiece(board, knight1, 3, 4));
	}
	
	@Test
	public void verticalMove() {
		assertFalse("Knight cannot mvoe vertically 1 space up.",BoardLogic.movePiece(board, knight1, 4, 3));
		assertFalse("Knight cannot move vertically 2 space down.",BoardLogic.movePiece(board, knight1, 4, 5));
	}
	
	@Test
	public void diagonalMove() {
		assertFalse("Knight cannot move diagonally 1 space.",BoardLogic.movePiece(board, knight1, 3, 3));
		assertFalse("Knight cannot move diagonally 2 spaces.",BoardLogic.movePiece(board, knight1, 1, 5));
	}

	@Test
	public void captureFriendly() {
		BoardLogic.setPiece(board, new Rook(), 3, 2, Color.WHITE);
		assertFalse("Knight cannot capture friendly pieces.",BoardLogic.movePiece(board, knight1, 3, 2));
	}
	
	@Test
	public void captureOpponent() {
		BoardLogic.setPiece(board, new Rook(), 3, 2, Color.BLACK);
		assertTrue("Knight captured enemy piece.",BoardLogic.movePiece(board, knight1, 3, 2));
	}
	
	@Test
	public void jumpFriendly() {
		BoardLogic.setPiece(board, new Rook(), 3, 4, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 3, 3, Color.WHITE);
		assertTrue("Knight can jump friendly pieces.",BoardLogic.movePiece(board, knight1, 3, 2));
	}
	
	@Test
	public void jumpOpponent() {
		BoardLogic.setPiece(board, new Rook(), 3, 4, Color.BLACK);
		BoardLogic.setPiece(board, new Rook(), 3, 3, Color.BLACK);
		assertTrue("Knight can jump enemy pieces.",BoardLogic.movePiece(board, knight1, 3, 2));		
	}
}