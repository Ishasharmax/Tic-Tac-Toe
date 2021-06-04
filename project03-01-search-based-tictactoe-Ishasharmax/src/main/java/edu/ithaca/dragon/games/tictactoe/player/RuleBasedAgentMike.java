package edu.ithaca.dragon.games.tictactoe.player;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public class RuleBasedAgentMike implements TicTacToePlayer {

    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
    
        //getting opponent symbol
        char opponent;

        if (yourSymbol == 'X'){
            opponent = 'O';
        }
        else{
            opponent = 'X';
        }

        //checking for a winning move
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                TicTacToeBoard testBoard = curBoard.copyBoard();
                if (testBoard.isSquareOpen(new Pair<>(x,y))){
                    testBoard.setSquare(new Pair<>(x,y), yourSymbol);
                    if (testBoard.checkForWin(yourSymbol)){
                        return new Pair<>(x,y);
                    }
                }
                
            }
        }

        //block opponent win
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                TicTacToeBoard testBoard = curBoard.copyBoard();
                if (testBoard.isSquareOpen(new Pair<>(x,y))){
                    testBoard.setSquare(new Pair<>(x,y), opponent);
                    if (testBoard.checkForWin(opponent)){
                        return new Pair<>(x,y);
                    }
                }
                
            }
        }

        //failsafe, picks the next available spot
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                if (curBoard.isSquareOpen(new Pair<>(x,y))){
                    return new Pair<>(x,y);
                }
            }
        }
        throw new IllegalArgumentException("Board with no moves given to player:\n" + curBoard.displayString());
    }


}
