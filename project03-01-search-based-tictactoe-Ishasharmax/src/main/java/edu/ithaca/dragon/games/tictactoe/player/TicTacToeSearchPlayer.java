package edu.ithaca.dragon.games.tictactoe.player;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public interface TicTacToeSearchPlayer extends TicTacToePlayer{
    /**
     * A function that calculates the overall utility of a next available move in tic-tac-toe
     * @param curBoard - the current board to check the given move on
     * @param curSymbolToMove - the symbol that will make the next move, regardless of whether it is yours or not
     * @param move - an open place on the board to judge
     * @param yourSymbol - this is how we can tell which symbol the agent wants to win
     * @return a positive number if the move is good (leads to a win), 0 if the move is neutral (leads to a tie), and a negative number if the move is bad (leads to a loss) 
     */
    public double calcScoreForMove(TicTacToeBoard curBoard, char curSymbolToMove, Pair<Integer, Integer> move, char yourSymbol);
}
