package controller;

import java.awt.Color;


public class Player {
	public String name;
	public Color pieceColor;
	public int score;
	public boolean moveFirst;
	
	public Player(String name, Color pieceColor, boolean moveFirst){
		this.name = name;
		this.pieceColor = pieceColor;
		this.moveFirst = moveFirst;
	}
	
	public Color changeColor(Color currColor){
		if(currColor == Color.BLACK)
			return Color.WHITE;
		return Color.BLACK;
	}

}
