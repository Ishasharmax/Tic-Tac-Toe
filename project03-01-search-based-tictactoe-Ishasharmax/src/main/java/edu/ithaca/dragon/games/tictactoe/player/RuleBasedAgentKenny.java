package edu.ithaca.dragon.games.tictactoe.player;

import org.javatuples.Pair;

import java.util.Random;
import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public class RuleBasedAgentKenny implements TicTacToePlayer {

    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {

        char otherSymbol;
        if(yourSymbol == 'X'){
            otherSymbol = 'O';
        }
        else{
            otherSymbol = 'X';
        }
        
        // Rule 1: If I have a winning move, take it.
        for (int y=0; y<3; y++){
            for(int x=0; x<3;x++){
                TicTacToeBoard simBoard = curBoard.copyBoard();
                if (simBoard.isSquareOpen(new Pair<>(x,y))){
                    simBoard.setSquare(new Pair<>(x,y), yourSymbol);

                    if(simBoard.checkForWin(yourSymbol)){
                        return new Pair<>(x,y);
                    }
                }
            }
        }

        // Rule 2: If the opponent has a winning move, block it.
        for (int y=0; y<3; y++){
            for(int x=0; x<3;x++){
                TicTacToeBoard simBoard = curBoard.copyBoard();
                if (simBoard.isSquareOpen(new Pair<>(x,y))){
                    simBoard.setSquare(new Pair<>(x,y), otherSymbol);

                    if(simBoard.checkForWin(otherSymbol)){
                        return new Pair<>(x,y);
                    }
                }
            }
        }

        // Unsure how to implement the other 2 rules...


        // Rule 5: Place in such a way to maximize winning potential.
        if(curBoard.isSquareOpen(new Pair<>(1,1))){
            return new Pair<>(1,1);
        }

        // If no winning move and center unavailable, take a random open space.
        Random rand = new Random();
        boolean foundNextMove = false;
        while(foundNextMove == false){
            int xMove = rand.nextInt(3);
            int yMove = rand.nextInt(3);

            if(curBoard.isSquareOpen(new Pair<>(xMove,yMove))){
                foundNextMove = true;
                return new Pair<>(xMove,yMove);
            }
        }

        throw new IllegalArgumentException("Board with no moves given to player:\n" + curBoard.displayString());
    }
}