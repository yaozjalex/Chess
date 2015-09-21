package controller;

import model.*;
import logic.*;
import piece.*;

import java.awt.Color;
import java.util.Stack;

public class Game {
//	//
//	 public static void main(String[] args) {
//		 	Game a = new Game();
//	    }
	
	
	public Board gameBoard;
	public Player player1, player2;
	public Stack<Step> move = new Stack<Step>();
	
	private static final Piece [] blackInitList = {new Rook(), new Knight(), new Bishop(), new Queen(),
	   new King(), new Bishop(), new Knight(), new Rook()};
	
	private static final Piece [] whiteInitList = {new Rook(), new Knight(), new Bishop(), new Queen(),
		   new King(), new Bishop(), new Knight(), new Rook()};
	
	public Game(){
		
		setBoard();
	}	
	
	public void setBoard(){
		//initialize GameBoard
		gameBoard = new Board();
		for (int i = 0; i < 8; i++) {			
			BoardLogic.setPiece(this.gameBoard, new Pawn(), 1,i,Color.BLACK);
			BoardLogic.setPiece(this.gameBoard, new Pawn(),6,i,Color.WHITE);
			BoardLogic.setPiece(this.gameBoard, blackInitList[i],0,i,Color.BLACK);
			BoardLogic.setPiece(this.gameBoard, whiteInitList[i],7,i,Color.WHITE);
		}
	}
	
		//create players
	public void setPlayers(String player1, String player2){
		this.player1 = new Player(player1, Color.WHITE, true);
		this.player2 = new Player(player2, Color.BLACK, false);
		
		
	}
	
	public Player nextPlayer(Player currPlayer){
		if(currPlayer == player1)
			return player2;
		return player1;
	}
	
	public void restart(Player currPlayer){
		setBoard();
		currPlayer.moveFirst = !currPlayer.moveFirst;
		currPlayer.pieceColor = currPlayer.changeColor(currPlayer.pieceColor);
		Player nextPlayer = nextPlayer(currPlayer);
		nextPlayer.moveFirst = !nextPlayer.moveFirst;
		nextPlayer.pieceColor = currPlayer.changeColor(nextPlayer.pieceColor);
	}
}

