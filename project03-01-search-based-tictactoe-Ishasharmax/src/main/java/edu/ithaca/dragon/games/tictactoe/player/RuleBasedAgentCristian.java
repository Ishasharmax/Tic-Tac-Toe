package edu.ithaca.dragon.games.tictactoe.player;

import java.util.stream.IntStream;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.GameStatus;
import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public class RuleBasedAgentCristian implements TicTacToePlayer {

    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
        
        char opponetsSymbol;

        if(yourSymbol == 'X'){
            opponetsSymbol = 'O';
        }else{
            opponetsSymbol = 'X';
        }


        //taking winning move
        for(int x=0; x<3; x++){
            for(int y=0; y<3; y++){
                TicTacToeBoard nextTurn = curBoard.copyBoard();
                if(nextTurn.isSquareOpen(new Pair<>(x,y))){
                    nextTurn.setSquare(new Pair<>(x,y), yourSymbol);
                    if(nextTurn.checkForWin(yourSymbol)){
                        return new Pair<>(x,y);
                    }
                }
            }
        }

        //blocking move
        for(int x=0; x<3; x++){
            for(int y=0; y<3; y++){
                TicTacToeBoard nextTurn = curBoard.copyBoard();
                if(nextTurn.isSquareOpen(new Pair<>(x,y))){
                    nextTurn.setSquare(new Pair<>(x,y), opponetsSymbol);
                    if(nextTurn.checkForWin(opponetsSymbol)){
                        return new Pair<>(x,y);
                    }
                }
            }
        }
        
        


        //Rule  if not winning or blocking go crazy 
        if(curBoard.isSquareOpen(new Pair<>(1,1))){
            return new Pair<>(1,1);   
        }else if(curBoard.calcGameStatus() == GameStatus.PLAYING) {

                if(curBoard.isSquareOpen(new Pair<>(0,0))){
                    return new Pair<>(0,0);
                }else if(curBoard.isSquareOpen(new Pair<>(0,1))){
                    return new Pair<>(0,1);
                }
                else if(curBoard.isSquareOpen(new Pair<>(2,2))){
                    return new Pair<>(2,2);
                }else if(curBoard.isSquareOpen(new Pair<>(2,1))){
                    return new Pair<>(2,1);

                }else if(curBoard.isSquareOpen(new Pair<>(2,0))){
                    return new Pair<>(2,0);
                }else if(curBoard.isSquareOpen(new Pair<>(1,0))){
                    return new Pair<>(1,0);
                }
                else if(curBoard.isSquareOpen(new Pair<>(0,2))){
                    return new Pair<>(0,2);
                }else{
                    return new Pair<>(1,2);
                }
        
        }
        
        return null;
    }
    
}
