package edu.ithaca.dragon.games.tictactoe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Triplet;

import edu.ithaca.dragon.games.tictactoe.player.TicTacToePlayer;

public class TicTacToeTournament {

    public static Map<String, Triplet<Integer, Integer, Integer>> runTournament(List<TicTacToePlayer> players){
        Map<String, Triplet<Integer, Integer, Integer>> scores = new HashMap<>();
        for (TicTacToePlayer player : players){
            scores.put(player.getClass().getSimpleName(), new Triplet<>(0,0,0));
        }
        for (TicTacToePlayer xPlayer : players){
            for (TicTacToePlayer oPlayer : players){
                if (xPlayer != oPlayer){
                    for (int i=0; i<10; i++){
                        TicTacToeGame newGame = new TicTacToeGame(xPlayer, oPlayer);
                        newGame.play();
                        GameStatus gameStatus = newGame.calcGameStatus();
                        if(gameStatus == GameStatus.TIE){
                            incrementTie(scores, xPlayer);
                            incrementTie(scores, oPlayer);
                        }
                        else if (gameStatus == GameStatus.XWIN){
                            incrementWin(scores, xPlayer);
                            incrementLoss(scores, oPlayer);
                        }
                        else if (gameStatus == GameStatus.OWIN){
                            incrementWin(scores, oPlayer);
                            incrementLoss(scores, xPlayer);
                        }
                        else {
                            throw new RuntimeException("Unrecognized games state:" + gameStatus);
                        }
                    }
                }
            }
        }
        return scores;
    }

    public static void incrementTie(Map<String, Triplet<Integer, Integer, Integer>> scores, TicTacToePlayer player){
        Triplet<Integer, Integer, Integer> playerScores = scores.get(player.getClass().getSimpleName());
        playerScores = playerScores.setAt2(playerScores.getValue2()+1);
        scores.put(player.getClass().getSimpleName(), playerScores);
    }

    public static void incrementWin(Map<String, Triplet<Integer, Integer, Integer>> scores, TicTacToePlayer player){
        Triplet<Integer, Integer, Integer> playerScores = scores.get(player.getClass().getSimpleName());
        playerScores = playerScores.setAt0(playerScores.getValue0()+1);
        scores.put(player.getClass().getSimpleName(), playerScores);
    }

    public static void incrementLoss(Map<String, Triplet<Integer, Integer, Integer>> scores, TicTacToePlayer player){
        Triplet<Integer, Integer, Integer> playerScores = scores.get(player.getClass().getSimpleName());
        playerScores = playerScores.setAt1(playerScores.getValue1()+1);
        scores.put(player.getClass().getSimpleName(), playerScores);
    }
    
}
