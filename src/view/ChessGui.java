package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controller.*;

import java.awt.Color;
import java.util.Vector;

import logic.BoardLogic;
import model.Board;
import model.Space;
import piece.*;
 
 
public class ChessGui implements ActionListener{
 
	Game game;
	Player currPlayer;
    Piece selectedPiece;
    JButton[][] jButtonList;
    JButton selectedSquare;
    static GameLoop funnyGame;
    BoardWindow myPanel;
    JFrame window;
    
    //main method
    public static void main(String[] args) {
    	funnyGame = new GameLoop();
    }
    
    //initialize the Window
    public ChessGui(){
    	game = new Game();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        
        //set JFrame
        window = new JFrame("ChessGame");
        window.setLayout(new BorderLayout());
        myPanel = new BoardWindow();
        window.add(myPanel);
        setUpMenu(window);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    //setUpMenu
    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();
        JMenu game = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("NewGame");
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem forfeit = new JMenuItem("Forfeit");
        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem score = new JMenuItem("Score");
        JMenuItem customerPiece = new JMenuItem("CustomerPiece");

        newGame.addActionListener(this);
        restart.addActionListener(this);
        forfeit.addActionListener(this);
        undo.addActionListener(this);
        score.addActionListener(this);
        customerPiece.addActionListener(this);
        game.add(newGame);
        game.add(restart);
        game.add(forfeit);
        game.add(undo);
        game.add(score);
        game.add(customerPiece);
        menubar.add(game);
        window.setJMenuBar(menubar);
    }
 
    //create BoardGUI
    public class BoardWindow extends JPanel{
    	public BoardWindow(){
    		jButtonList = new JButton[8][8];
    		setLayout(new GridBagLayout());
    		GridBagConstraints blk = new GridBagConstraints();
    		for (int rank = 0; rank < 8; rank++) {
                for (int file = 0; file < 8; file++) {
                    blk.gridy = rank;
                    blk.gridx = file;
                                        
                    //Set chessPiece as JButton
                    JButton button = new JButton();
                    Image temp = null;   
                    if(game.gameBoard.spaces[rank][file] != null){
                    	temp = game.gameBoard.spaces[rank][file].getBufferedImage();
                        button.setIcon(new ImageIcon(temp));
                    }                    
                    button.setBorderPainted(true);
                    button.setPreferredSize(new Dimension(100, 100));
                    Border thickBorder = new LineBorder(Color.BLACK, 1); 
                    button.setBorder(thickBorder);
                    MyActionListener check = new MyActionListener();
                    button.addActionListener(check);
                    add(button, blk);
                    jButtonList[rank][file] = button;
                }
            }
      	}
    }
    
    //menuActionPerformance
    @Override
    public void actionPerformed(ActionEvent e) {
    	String command = e.getActionCommand();
    	if(command == "Score"){
    		JOptionPane.showMessageDialog(null, game.player1.name + ": " + game.player1.score + "\n" +
    				game.player2.name + ": " + game.player2.score);
    	}
    	else if(command =="Forfeit"){
    		int selection = JOptionPane.showConfirmDialog(null, "Are you sure?");
    		if(selection == JOptionPane.YES_OPTION){
    			JOptionPane.showMessageDialog(null, currPlayer.name + " forfeit, game restart.");
    			game.nextPlayer(currPlayer).score ++;
    			game.restart(currPlayer);
    			window.remove(myPanel);
    			myPanel = new BoardWindow();
    			window.add(myPanel);
    			window.setVisible(true);
    			if(game.player1.moveFirst)
    				currPlayer = game.player1;
    			else
    				currPlayer = game.player2;
    		}
	        JOptionPane.showMessageDialog(null, currPlayer.name + " goes first!");

    	}
    	else if(command == "Restart"){
    		int selection = JOptionPane.showConfirmDialog(null, currPlayer.name + " wants to restart to game, do you agree?");
    		if(selection == JOptionPane.YES_OPTION){
    			JOptionPane.showMessageDialog(null, "Game restart.");
    			game.nextPlayer(currPlayer).score ++;
    			currPlayer.score ++;
    			game.restart(currPlayer);
    			window.remove(myPanel);
    			myPanel = new BoardWindow();
    			window.add(myPanel);
    			window.setVisible(true);
    			if(game.player1.moveFirst)
    				currPlayer = game.player1;
    			else
    				currPlayer = game.player2;
    		}
    		else if(selection == JOptionPane.NO_OPTION){
    			JOptionPane.showMessageDialog(null, game.nextPlayer(currPlayer).name + " does not agree to restart.");
    		}
	        JOptionPane.showMessageDialog(null, currPlayer.name + " goes first!");		
    	}
    	else if(command == "Undo"){
    		int selection = JOptionPane.showConfirmDialog(null, game.nextPlayer(currPlayer).name + " wants to restart to game, do you agree?");
    		if(selection == JOptionPane.YES_OPTION){
	    		if(game.move.isEmpty())
	    			return;
	    		Step step = game.move.pop();
	    		//First part
	    		game.gameBoard.spaces[step.rank_i][step.file_i] = game.gameBoard.spaces[step.rank_f][step.file_f];
	    		game.gameBoard.spaces[step.rank_i][step.file_i].setRank(step.rank_i);
	    		game.gameBoard.spaces[step.rank_i][step.file_i].setFile(step.file_i);   		    		
	    		Icon img = jButtonList[step.rank_f][step.file_f].getIcon();
	    		jButtonList[step.rank_i][step.file_i].setIcon(img);
	            
	            //Second part
	    		if(step.eatenPiece == null){
	    			game.gameBoard.spaces[step.rank_f][step.file_f] = null;
	    			jButtonList[step.rank_f][step.file_f].setIcon(null);
	    		}
	    		else{
	    			game.gameBoard.spaces[step.rank_f][step.file_f] = (Piece)step.eatenPiece;
	    			Image temp = game.gameBoard.spaces[step.rank_f][step.file_f].getBufferedImage();
	    			jButtonList[step.rank_f][step.file_f].setIcon(new ImageIcon(temp));
	    		}
    			BoardLogic.updatePieces(game.gameBoard);
	    		currPlayer = game.nextPlayer(currPlayer);
    		}
    		else if(selection == JOptionPane.NO_OPTION){
    			JOptionPane.showMessageDialog(null, currPlayer.name + " does not agree to restart.");
    		}
    	}
    	else if(command == "NewGame"){
    		game.restart(currPlayer);
    		window.remove(myPanel);
			myPanel = new BoardWindow();
			window.add(myPanel);
			window.setVisible(true);
			if(game.player1.moveFirst)
				currPlayer = game.player1;
			else
				currPlayer = game.player2;
	        JOptionPane.showMessageDialog(null, currPlayer.name + " goes first!");
    	}
    	else if(command == "CustomerPiece"){
    	    String[] options = {"Rock", "Sentry"};
    		int piece = JOptionPane.showOptionDialog(window, 
    		         "Select customer piece you want to add on the board", 
    		         "Option Dialog Box", 0, JOptionPane.QUESTION_MESSAGE, 
    		         null, options, null);
			int add_rank = Integer.parseInt(JOptionPane.showInputDialog(null, "Rank (start from 0)"));
			int add_file = Integer.parseInt(JOptionPane.showInputDialog(null, "File (start from 0)"));

			if(!BoardLogic.outOfBounds(game.gameBoard, add_rank, add_file) && (game.gameBoard.spaces[add_rank][add_file] == null || game.gameBoard.spaces[add_rank][add_file].getColor() != game.nextPlayer(currPlayer).pieceColor)){
				game.gameBoard.spaces[add_rank][add_file] = null;
				if(piece == 0){
					BoardLogic.setPiece(game.gameBoard, new Rock(), add_rank, add_file, currPlayer.pieceColor);
	    			jButtonList[add_rank][add_file].setIcon(new ImageIcon(game.gameBoard.spaces[add_rank][add_file].getBufferedImage()));
				}
				else if(piece == 1){
					BoardLogic.setPiece(game.gameBoard, new Sentry(), add_rank, add_file, currPlayer.pieceColor);
	    			jButtonList[add_rank][add_file].setIcon(new ImageIcon(game.gameBoard.spaces[add_rank][add_file].getBufferedImage()));
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Can't replace the other player's pieces");
			}

    	}
    	else{	
    		JOptionPane.showMessageDialog(null,
                    "no such option");
    	}
    }
    
    //actionListener
    class MyActionListener implements ActionListener{
    	@Override
        public void actionPerformed(ActionEvent e){
    		JButton button = (JButton) e.getSource();
            Point rv = new Point();
            int rank = button.getLocation(rv).y/100;
            int file = button.getLocation(rv).x/100;
            Piece selection = game.gameBoard.spaces[rank][file];
            if(selectedPiece != null){
            	boolean reachable = false;
            	Vector<Space> showMoves = BoardLogic.movesOnBoard(game.gameBoard, selectedPiece);
            	for(int i = 0; i < showMoves.size(); i++){
            		if(showMoves.elementAt(i).getFile() == file && showMoves.elementAt(i).getRank() == rank)
            			reachable = true;
            	}
                if(reachable == false){
            		JOptionPane.showMessageDialog(null, "Selected space is unreachable");
            		JButton moves;
                	for(int i = 0; i < showMoves.size(); i++){
                		moves = jButtonList[showMoves.elementAt(i).getRank()][showMoves.elementAt(i).getFile()];
                		Border thickBorder = new LineBorder(Color.BLACK, 1); 
                        moves.setBorder(thickBorder);
                	}
            		selectedPiece = null;
            	}
            	else
            	{
               		game.move.push(new Step(selectedPiece.getRank(), selectedPiece.getFile(), rank, file, game.gameBoard.spaces[rank][file])); 
            		BoardLogic.movePiece(game.gameBoard, selectedPiece, rank, file);
            		JButton moves;
                	for(int i = 0; i < showMoves.size(); i++){
                		moves = jButtonList[showMoves.elementAt(i).getRank()][showMoves.elementAt(i).getFile()];
                		Border thickBorder = new LineBorder(Color.BLACK, 1); 
                        moves.setBorder(thickBorder);
                	}
                    Icon img = selectedSquare.getIcon();
                    button.setIcon(img);
                    selectedSquare.setIcon(null);
                    currPlayer = game.nextPlayer(currPlayer);
             		selectedPiece = null;
             		//memorization
             		
             		
             		if(BoardLogic.inCheckmate(game.gameBoard, currPlayer.pieceColor)){
                		JOptionPane.showMessageDialog(null, currPlayer.name + " is in CheckMate, " + game.nextPlayer(currPlayer).name + " win!");
                		game.nextPlayer(currPlayer).score ++;
             		}
             		else if(BoardLogic.inStalemate(game.gameBoard, currPlayer.pieceColor)){
             			JOptionPane.showMessageDialog(null, "The game is in StaleMate");
             			currPlayer.score ++;
                		game.nextPlayer(currPlayer).score ++;
             		}
             		else if(BoardLogic.inCheck(game.gameBoard, currPlayer.pieceColor)){
             			JOptionPane.showMessageDialog(null, currPlayer.name + " is in Check");             			
             		}

            	}
            }

            else if(selection.getColor() == currPlayer.pieceColor){
            	JButton moves;
            	selectedPiece = game.gameBoard.spaces[rank][file];
            	Vector<Space> showMoves = BoardLogic.movesOnBoard(game.gameBoard, selectedPiece);
            	for(int i = 0; i < showMoves.size(); i++){
            		moves = jButtonList[showMoves.elementAt(i).getRank()][showMoves.elementAt(i).getFile()];
            		Border thickBorder = new LineBorder(Color.YELLOW, 3); 
                    moves.setBorder(thickBorder);
            	}
                selectedSquare = button;
            }
    	}
    }
}