package edu.ithaca.dragon.games.tictactoe.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import edu.ithaca.dragon.games.tictactoe.board.TwoDArrayBoard;

public class SearchPlayerTest {
    
    @Test
    public void testWinLossBehavior(){
        TicTacToeSearchPlayer player = null;
        assertEquals(new Pair<>(2, 2), player.chooseSquare(new TwoDArrayBoard("OOXOOX   "), 'X'));
        assertEquals(new Pair<>(0, 2), player.chooseSquare(new TwoDArrayBoard(
            "OOX"+
            "OOX"+
            "   "), 'O'));
    }

    @Test
    public void testScore(){
        TicTacToeSearchPlayer player = null;

        assertEquals(1, player.calcScoreForMove(new TwoDArrayBoard(
            "OOX"+
            " OX"+
            "   "), 'X', new Pair<>(2,2), 'X'));
        
        assertEquals(-1, player.calcScoreForMove(new TwoDArrayBoard(
            "OXX"+
            " OX"+
            "   "), 'O', new Pair<>(2,2),'X'));
        //TODO: more tests for your agent!
    }
}
