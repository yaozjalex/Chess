package test;

import static org.junit.Assert.*;

import java.awt.Color;

import model.Board;
import logic.BoardLogic;
import piece.*;

import org.junit.Before;
import org.junit.Test;


public class SentryTest {

	Board board;
	Piece sentry1;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		sentry1 = new Sentry();
		BoardLogic.setPiece(board, sentry1, 4,4,Color.WHITE);
	}

	@Test
	public void setSentry() {
		assertTrue("Sentry set on the BoardLogic.",BoardLogic.setPiece(board, new Sentry(), 7,7,Color.WHITE));
		assertNotNull("Sentry is on the BoardLogic.",BoardLogic.getPiece(board, 7,7));
	}
	
	@Test
	public void wierdMove() {
		assertFalse("Sentry cannot move in L-shapes.",BoardLogic.movePiece(board, sentry1,6,5));
	}
	
	@Test
	public void moveOneDiagonal() {
		assertTrue("Sentry can move 1 space up-left.",BoardLogic.movePiece(board, sentry1,3,3));
		assertTrue("Sentry can move 1 space down-right.",BoardLogic.movePiece(board, sentry1,4,4));
		assertTrue("Sentry can move 1 space up-right.",BoardLogic.movePiece(board, sentry1,5,3));
		assertTrue("Sentry can move 1 space down-left.",BoardLogic.movePiece(board, sentry1,4,4));
	}
	
	@Test
	public void moveTwoDiagonal() {
		assertTrue("Sentry can move 2 spaces up-left.",BoardLogic.movePiece(board, sentry1,2,2));
		assertTrue("Sentry can move 2 spaces down-right.",BoardLogic.movePiece(board, sentry1,4,4));
		assertTrue("Sentry can move 2 spaces up-right.",BoardLogic.movePiece(board, sentry1,6,6));
		assertTrue("Sentry can move 2 spaces down-left.",BoardLogic.movePiece(board, sentry1,4,4));
	}
	
	@Test
	public void moveOneHorizontal() {
		assertTrue("Sentry can move 1 left.",BoardLogic.movePiece(board, sentry1,3,4));
		assertTrue("Sentry can move 1 right.",BoardLogic.movePiece(board, sentry1,4,4));
	}
	
	@Test
	public void moveOneVertical() {
		assertTrue("Sentry can move 1 up.",BoardLogic.movePiece(board, sentry1,4,3));
		assertTrue("Sentry can move 1 down.",BoardLogic.movePiece(board, sentry1,4,4));
	}
	
	@Test
	public void moveTwoHorizontal() {
		assertTrue("Sentry can move 2 left.",BoardLogic.movePiece(board, sentry1,2,4));
		assertTrue("Sentry can move 2 right.",BoardLogic.movePiece(board, sentry1,4,4));	
	}
	
	@Test
	public void moveTwoVertical() {
			assertTrue("Sentry can move 2 up.",BoardLogic.movePiece(board, sentry1,4,2));
			assertTrue("Sentry can move 2 down.",BoardLogic.movePiece(board, sentry1,4,4));
	}
}