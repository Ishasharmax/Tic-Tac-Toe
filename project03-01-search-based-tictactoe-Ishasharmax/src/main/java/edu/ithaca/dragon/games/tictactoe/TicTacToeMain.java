package edu.ithaca.dragon.games.tictactoe;

import java.text.RuleBasedCollator;
import java.util.Arrays;

import edu.ithaca.dragon.games.tictactoe.player.*;

public class TicTacToeMain {
    public static void main (String[] args){
         new TicTacToeGame( new HumanPlayer(), new SearchPlayer()).play();
        // System.out.println(TicTacToeTournament.runTournament(Arrays.asList(new SearchBasedPlayerToby(), new RuleBasedPlayerToby())));
        System.out.println(TicTacToeTournament.runTournament(Arrays.asList(
            new RuleBasedAgentCristian(), 
            new RuleBasedAgentIsha(), 
            new RuleBasedAgentKenny(), 
            new RuleBasedAgentKelsey(), 
            new RuleBasedAgentMike(), 
            new RuleBasedAgentStephen(), 
            new RuleBasedAgentMorgan(), 
            new RuleBasedAgentOwen(), 
            new RuleBasedAgentEmma(), 
            new RuleBasedAgentBlackford(),
            new RuleBasedAgentKemi(), 
            new RuleBasedAgentToby()
        ))); 
            
    }
    
}
