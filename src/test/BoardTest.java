package test;

import static org.junit.Assert.*;

import java.awt.Color;

import model.Board;
import logic.BoardLogic;
import piece.*;

import org.junit.*;

public class BoardTest {

static Board board;
	
	@Before
	public void setUp() {
		board = new Board();
	}
	
	@Test
	public void emptyConstructor() {
		board = new Board(null);
		assertNull("Board should be null.", BoardLogic.getPiece(board, 0,0));
	}
	@Test
	public void notInCheck() {
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new King(), 7, 7, Color.BLACK);
		assertFalse("White is not in check.",BoardLogic.inCheck(board, Color.WHITE));
		assertFalse("Black is not in check.",BoardLogic.inCheck(board, Color.BLACK));
	}
	
	@Test
	public void notInOwnCheck() {
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 0, 1, Color.WHITE);
		assertFalse("Not in check with own piece.",BoardLogic.inCheck(board, Color.WHITE));
	}
	
	@Test
	public void opponentInCheck() {
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 0, 1, Color.BLACK);
		assertTrue("White is in check.",BoardLogic.inCheck(board, Color.WHITE));
	}
	
	@Test
	public void moveIntoCheck() {
		Piece king = new King();
		BoardLogic.setPiece(board, king, 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 1, 1, Color.BLACK);
		assertFalse("Can't move into check.",BoardLogic.movePiece(board, king, 1, 0));
	}
	
	@Test
	public void moveOtherPieceInCheck() {
		Piece rook = new Rook();
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 0, 1, Color.BLACK);
		BoardLogic.setPiece(board, rook, 7, 7, Color.WHITE);
		assertTrue("White in check.",BoardLogic.inCheck(board, Color.WHITE));
		assertFalse("Need to move king out of check.",BoardLogic.movePiece(board, rook, 6, 7));
	}
	
	@Test
	public void captureOutofCheck() {
		Piece rook = new Rook();		
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 0, 1, Color.BLACK);
		BoardLogic.setPiece(board, rook, 1, 1, Color.WHITE);
		assertTrue("White is in check.",BoardLogic.inCheck(board, Color.WHITE));
		assertTrue("Captured out of check.",BoardLogic.movePiece(board, rook,0,1));
		assertFalse("White is no longer in check.",BoardLogic.inCheck(board, Color.WHITE));
	}
	
	@Test
	public void captureIntoCheck() {
		Piece king = new King();
		BoardLogic.setPiece(board, king, 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 1, 0, Color.BLACK);
		BoardLogic.setPiece(board, new Rook(), 1, 1, Color.BLACK);
		assertFalse("Can't capture into check.",BoardLogic.movePiece(board, king, 1, 0));	
	}
	
	@Test
	public void emptyBoardCheck() {
		assertFalse("White is not in check.",BoardLogic.inCheck(board, Color.WHITE));
		assertFalse("Black is not in check.",BoardLogic.inCheck(board, Color.BLACK));
	}
	
	@Test
	public void notInFriendlyCheckmate() {
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 0, 2, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 1, 2, Color.WHITE);
		assertFalse("Not in checkmate with friendly pieces.",BoardLogic.inCheckmate(board, Color.WHITE));
	}

	@Test
	public void opponentInCheckmate() {
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 0, 2, Color.BLACK);
		BoardLogic.setPiece(board, new Rook(), 1, 2, Color.BLACK);
		assertTrue("White is in check.",BoardLogic.inCheck(board, Color.WHITE));
		assertTrue("White in checkmate.",BoardLogic.inCheckmate(board, Color.WHITE));
	}
	
	@Test
	public void notInCheckOrCheckMate() {
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new King(), 7, 7, Color.BLACK);
		assertFalse("White is not in checkmate.",BoardLogic.inCheckmate(board, Color.WHITE));
		assertFalse("Black is not in checkmate.",BoardLogic.inCheckmate(board, Color.BLACK));
	}
	
	@Test
	public void notInCheckMate() {
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 5, 0, Color.BLACK);
		assertTrue("White is in check.",BoardLogic.inCheck(board, Color.WHITE));
		assertFalse("White is not in checkmate.",BoardLogic.inCheckmate(board, Color.WHITE));
	}
	
	@Test
	public void empytBoardCheckMate() {
		assertFalse("White is not in checkmate.",BoardLogic.inCheckmate(board, Color.WHITE));
		assertFalse("Black is not in checkmate.",BoardLogic.inCheckmate(board, Color.BLACK));
	}
	
	@Test
	public void inStalemate() {
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new King(), 1, 2, Color.BLACK);
		BoardLogic.setPiece(board, new Queen(), 2, 1, Color.BLACK);
		assertTrue("White is in a stalemate.",BoardLogic.inStalemate(board, Color.WHITE));
	}
	
	@Test
	public void emptyBoardStalemate() {
		assertFalse("No one is in stalemate.",BoardLogic.inStalemate(board, Color.WHITE));
		assertFalse("No one is in stalemate.",BoardLogic.inStalemate(board, Color.BLACK));
	}
	
	@Test
	public void noStalemateInCheck() {
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		BoardLogic.setPiece(board, new Rook(), 0, 1, Color.BLACK);
		assertFalse("White is not in stalemate.",BoardLogic.inStalemate(board, Color.WHITE));
	}
	
	@Test
	public void noCheckNoStalemate() {
		BoardLogic.setPiece(board, new King(), 0, 0, Color.WHITE);
		assertFalse("Not in check.",BoardLogic.inCheck(board, Color.WHITE));
		assertFalse("Not in stalemate, in check.",BoardLogic.inStalemate(board, Color.WHITE));
	}

}
