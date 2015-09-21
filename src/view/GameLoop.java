package view;

import javax.swing.JOptionPane;

public class GameLoop {
    
    public GameLoop(){
    	ChessGui newChessGame = new ChessGui();
        String player1 = JOptionPane.showInputDialog(null, "Please Enter the name of the first player");
        String player2 = JOptionPane.showInputDialog(null, "Please Enter the name of the second player");
        newChessGame.game.setPlayers(player1, player2);
        newChessGame.currPlayer = newChessGame.game.player1;
        JOptionPane.showMessageDialog(null, newChessGame.currPlayer.name + " goes first!");
    }
}
