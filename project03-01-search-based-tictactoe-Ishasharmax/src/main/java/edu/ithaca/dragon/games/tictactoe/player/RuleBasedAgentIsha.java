package edu.ithaca.dragon.games.tictactoe.player;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public class RuleBasedAgentIsha implements TicTacToePlayer {

    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
        //rule 1
        for (int y=0; y<3; y++){
            for(int x=0; x<3;x++){
                TicTacToeBoard newBoard = curBoard.copyBoard();
                if (!newBoard.isSquareOpen(new Pair<>(x, y)))
                    continue;
                newBoard.setSquare(new Pair<>(x,y), yourSymbol);
                if(newBoard.checkForWin(yourSymbol))
                    return new Pair<>(x,y);

            }
        }
        //rule 2
        for (int y=0;y<3;y++){
            for (int x=0;x<3;x++){
                TicTacToeBoard newBoard=curBoard.copyBoard();
                if (!newBoard.isSquareOpen(new Pair<>(x, y)))
                    continue;
                if(newBoard.checkSquare(new Pair<>(x,y)) != yourSymbol)
                    newBoard.setSquare(new Pair<>(x,y), yourSymbol);
                else if(newBoard.checkForWin(yourSymbol))
                    return new Pair<>(x,y);
            }
        }
        //rule 3
        for (int y=0;y<3;y++){
            for (int x=0;x<3;x++){
                TicTacToeBoard newBoard=curBoard.copyBoard();
                if (!newBoard.isSquareOpen(new Pair<>(x, y)))
                    continue;
                if(newBoard.checkSquare(new Pair<>(x,y))==yourSymbol){
                    newBoard.setSquare(new Pair<>(x,y), yourSymbol);
                    return new Pair<>(x,y);
                }
            }
        }
        //rule 4
        for (int y=0;y<3;y++){
            for (int x=0;x<3;x++){
                TicTacToeBoard newBoard=curBoard.copyBoard();
                if (!newBoard.isSquareOpen(new Pair<>(x, y)))
                    continue;
                if(newBoard.checkSquare(new Pair<>(x,y))!=yourSymbol){
                    return new Pair<>(x,y);
                }
            }
        }

        //rule 5
        for (int y=0;y<3;y++) {
            for (int x = 0; x < 3; x++) {
                TicTacToeBoard newBoard = curBoard.copyBoard();
                if (!newBoard.isSquareOpen(new Pair<>(x, y)))
                    continue;
                if (newBoard.checkSquare(new Pair<>(x, y)) != yourSymbol) {
                    return new Pair<>(x, y);
                }
            }
        }



        /*Rule 1: If I have a winning move, take it.
                Rule 2: If the opponent has a winning move, block it.
                Rule 3: If I can create a fork (two winning ways) after this move, do it.
                Rule 4: Do not let the opponent creating a fork after my move. (Opponent may block your winning move and create a fork.)
        Rule 5: Place in the position such as I may win in the most number of possible ways.
*/
        throw new IllegalArgumentException("Board with no moves given to player:\n" + curBoard.displayString());
    }
}
