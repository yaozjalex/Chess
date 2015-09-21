package test;

import static org.junit.Assert.*;

import java.awt.Color;

import model.Board;
import piece.*;
import logic.BoardLogic;

import org.junit.*;


public class BishopTest {
	Board board;
	Piece bishop1;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		bishop1 = new Bishop();
		BoardLogic.setPiece(board, bishop1, 0, 0, Color.WHITE);
	}

	@Test
	public void bishopMove() {
		System.out.println(bishop1.getRank() + " " +  bishop1.getFile());
		assertTrue("Bishop move successful.", BoardLogic.movePiece(board, bishop1, 2, 2));
		System.out.println(bishop1.getRank() + " " +  bishop1.getFile());
		assertNotNull("New piece placed.", BoardLogic.getPiece(board, 2, 2));
		assertNull("Original square empty.", BoardLogic.getPiece(board, 0, 0));
		assertTrue("Bishop moves back.", BoardLogic.movePiece(board, bishop1, 1, 1));
		System.out.println(bishop1.getRank() + " " +  bishop1.getFile());
		assertTrue("Bishop moves down-left.",BoardLogic.movePiece(board, bishop1, 0, 2));
		System.out.println(bishop1.getRank() + " " +  bishop1.getFile());
		assertTrue("Bishop moves up-right.",BoardLogic.movePiece(board, bishop1, 1, 1));
	}
	
	@Test
	public void illegalMove() {
		assertFalse("Horizontal move fails.",BoardLogic.movePiece(board, bishop1, 3, 0));
		assertFalse("Vertical move fails", BoardLogic.movePiece(board, bishop1, 0, 3));
		assertFalse("Knight move fails", BoardLogic.movePiece(board, bishop1, 2, 1));
		assertNotNull("Piece not moved.",BoardLogic.getPiece(board, 0, 0));
	}
		
	@Test
	public void captureOwnPieces() {
		BoardLogic.setPiece(board, new Rook(),1,1,Color.WHITE);
		assertFalse("Failed to capture friendly piece.",BoardLogic.movePiece(board, bishop1, 1, 1));
	}

	@Test
	public void jumpOwnPieces() {
		BoardLogic.setPiece(board, new Rook(),1,1,Color.WHITE);
		assertFalse("Failed to jump friendly piece.",BoardLogic.movePiece(board, bishop1, 2, 2));
	}
	
	@Test
	public void captureEnemy() {
		BoardLogic.setPiece(board, new Rook(),1,1,Color.BLACK);
		assertTrue("Captured enemy piece.",BoardLogic.movePiece(board, bishop1, 1, 1));

		assertSame("Bishop moved to enemy square.",Color.WHITE,BoardLogic.getPiece(board, 1, 1).getColor());
	}
	
	@Test
	public void jumpEnemyPieces() {
		BoardLogic.setPiece(board, new Rook(),1,1,Color.BLACK);
		assertFalse("Failed to jump enemy piece.",BoardLogic.movePiece(board, bishop1, 2, 2));
	}

}