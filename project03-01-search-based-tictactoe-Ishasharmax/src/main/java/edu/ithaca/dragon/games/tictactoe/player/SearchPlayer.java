package edu.ithaca.dragon.games.tictactoe.player;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;
import org.javatuples.Pair;

public class SearchPlayer implements TicTacToeSearchPlayer {

    /**
     * A function that calculates the overall utility of a next available move in tic-tac-toe
     * @param curBoard - the current board to check the given move on
     * @param curSymbolToMove - the symbol that will make the next move, regardless of whether it is yours or not
     * @param move - an open place on the board to judge
     * @param yourSymbol - this is how we can tell which symbol the agent wants to win
     * @return a positive number if the move is good (leads to a win), 0 if the move is neutral (leads to a tie), and a negative number if the move is bad (leads to a loss)
     */

    @Override
    public double calcScoreForMove(TicTacToeBoard curBoard, char curSymbolToMove, Pair<Integer, Integer> move, char yourSymbol){
        double maxNum = 1.0;
        double minNum = -1.0;

        double score=0.0;

        if(!curBoard.checkForWin(opponentSymbol(yourSymbol))){
            return maxNum;
        }else if(curBoard.checkForWin(opponentSymbol(yourSymbol))){
            return minNum;
        }

        if(curSymbolToMove==yourSymbol){
            double bestScore = score;
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    if (!curBoard.isSquareOpen(new Pair<>(x, y)))
                        continue;
                    bestScore = calcScoreForMove(curBoard, curSymbolToMove, move, yourSymbol);
//                    curBoard.setSquare(new Pair<>(x, y), yourSymbol);
                }
            }return bestScore;
        }
        else{
            double bestScore = score;
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    if (!curBoard.isSquareOpen(new Pair<>(x, y)))
                        continue;
                    bestScore = calcScoreForMove(curBoard, curSymbolToMove, move, yourSymbol);
                }
            }return bestScore;
        }
        //if you played the move the next would be of the other player, so symbol would change
    }

    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
        double bestScore = 0.0;

        TicTacToeBoard newBoard = curBoard.copyBoard();

        int newX=0, newY=0;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                newX=x;
                newY=y;
                if (newBoard.isSquareOpen(new Pair<>(newX, newY))) {
                    double score = calcScoreForMove(newBoard, opponentSymbol(yourSymbol), new Pair<>(x, y), yourSymbol);
                    if (score > bestScore) {
                        bestScore = score;
                    }
                }
            }
        }return new Pair<>(newX, newY);
    }

    public static char opponentSymbol(char oppSymbol) {
        if (oppSymbol == 'X')
            return 'O';
        else
            return 'X';
    }
}




