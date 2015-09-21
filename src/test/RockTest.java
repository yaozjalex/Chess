package test;

import static org.junit.Assert.*;

import java.awt.Color;

import model.Board;
import logic.BoardLogic;
import piece.*;

import org.junit.Before;
import org.junit.Test;


public class RockTest {

	Board board;
	Piece rock1;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		rock1 = new Rock();
		BoardLogic.setPiece(board, rock1, 5,5,Color.WHITE);
	}

	@Test
	public void setRock() {
		assertTrue("Rock was set on the BoardLogic.",BoardLogic.setPiece(board, new Rock(),2,2,Color.WHITE));
		assertNotNull("Rock is on the BoardLogic.",BoardLogic.getPiece(board, 2,2));
	}
	
	@Test
	public void cantMove() {
		assertFalse("Rocks can't move.",BoardLogic.movePiece(board, rock1,4,5));
	}

}