package edu.ithaca.dragon.games.tictactoe.player;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public class RuleBasedAgentToby extends BrainDeadPlayer {

    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
        //If there is a winning move
        for (int y=0; y<3; y++){
            for(int x=0; x<3;x++){
                if (curBoard.isSquareOpen(new Pair<>(x,y))){
                    TicTacToeBoard tempBoard = curBoard.copyBoard();
                    tempBoard.setSquare(new Pair<>(x, y), yourSymbol);
                    if (tempBoard.checkForWin(yourSymbol)){
                        return new Pair<>(x,y);
                    }
                }
            }
        }
        //If there is a losing move, block it
        char otherSymbol = getOpposingSymbol(yourSymbol);
        for (int y=0; y<3; y++){
            for(int x=0; x<3;x++){
                if (curBoard.isSquareOpen(new Pair<>(x,y))){
                    TicTacToeBoard tempBoard = curBoard.copyBoard();
                    tempBoard.setSquare(new Pair<>(x, y), otherSymbol);
                    if (tempBoard.checkForWin(otherSymbol)){
                        return new Pair<>(x,y);
                    }
                }
            }
        }
        return super.chooseSquare(curBoard, yourSymbol);
    }

    public static char getOpposingSymbol(char aSymbol){
        if(aSymbol == 'X'){
            return 'O';
        }
        else if (aSymbol == 'O'){
            return 'X';
        }
        else {
            throw new IllegalArgumentException("Bad symbol given: " + aSymbol);
        }
    }

    
}
