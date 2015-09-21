package test;

import static org.junit.Assert.*;

import java.awt.Color;

import model.Board;
import logic.BoardLogic;
import piece.*;

import org.junit.Before;
import org.junit.Test;


public class PawnTest {
	
	Board board;
	Piece pawn1;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		pawn1 = new Pawn();
		BoardLogic.setPiece(board, pawn1, 1, 1, Color.BLACK);
	}

	@Test
	public void firstMoveTwoSquares() {
		assertTrue("Pawn can move 2 squares vertically on the first move.",BoardLogic.movePiece(board, pawn1, 3, 1));
	}
	
	@Test
	public void doubleSpecialMove() {
		BoardLogic.movePiece(board, pawn1, 1, 3);
		assertFalse("Pawn cannot move 2 squares forward after the first time.",BoardLogic.movePiece(board, pawn1, 5, 1));
	}
	
	@Test
	public void moveBackwards() {
		assertFalse("Pawns can't move backwards.",BoardLogic.movePiece(board, pawn1, 0, 1));
	}
	
	@Test
	public void moveHorizontal() {
		assertFalse("Pawns cannot move left.",BoardLogic.movePiece(board, pawn1, 1, 0));
		assertFalse("Pawns cannot move right.",BoardLogic.movePiece(board, pawn1, 1, 2));
	}
	
	@Test
	public void moveDiagonal() {
		assertFalse("Pawns cannot move diagonally.",BoardLogic.movePiece(board, pawn1, 2, 2));
		assertFalse("Pawns cannot move diagonally.",BoardLogic.movePiece(board, pawn1, 0, 0));
		assertFalse("Pawns cannot move diagonally.",BoardLogic.movePiece(board, pawn1, 0, 2));
		assertFalse("Pawns cannot move diagonally.",BoardLogic.movePiece(board, pawn1, 2, 0));
	}
	
	@Test
	public void captureBackwards() {
		BoardLogic.setPiece(board, new Pawn(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Pawn(), 0, 2, Color.WHITE);
		assertFalse("Pawns cannot capture backwards.",BoardLogic.movePiece(board, pawn1, 0, 0));
		assertFalse("Pawns cannot capture backwards.",BoardLogic.movePiece(board, pawn1, 0, 2));
	}
	
	@Test
	public void captureForwards() {
		BoardLogic.setPiece(board, new Pawn(), 2, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Pawn(), 2, 2, Color.WHITE);
		assertTrue("Pawn captured forwards.",BoardLogic.movePiece(board, pawn1, 2, 0));
		pawn1 = new Pawn();
		BoardLogic.setPiece(board, pawn1, 1, 1, Color.BLACK);
		assertTrue("Pawn captured forwards.",BoardLogic.movePiece(board, pawn1, 2, 2));
	}
}