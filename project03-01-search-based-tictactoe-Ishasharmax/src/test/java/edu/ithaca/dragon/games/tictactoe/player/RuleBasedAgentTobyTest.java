package edu.ithaca.dragon.games.tictactoe.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import edu.ithaca.dragon.games.tictactoe.board.TwoDArrayBoard;

public class RuleBasedAgentTobyTest {
    
    @Test
    public void testWinLossBehavior(){
        RuleBasedAgentToby player = new RuleBasedAgentToby();
        assertEquals(new Pair<>(2, 2), player.chooseSquare(new TwoDArrayBoard("OOXOOX   "), 'X'));
        assertEquals(new Pair<>(0, 2), player.chooseSquare(new TwoDArrayBoard("OOXOOX   "), 'O'));
    }
}
