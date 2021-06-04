package edu.ithaca.dragon.games.tictactoe.player;

import java.util.ArrayList;
import java.util.Random;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public class RuleBasedAgentScott implements TicTacToePlayer {

    private boolean yourSymbolIsAt(String boardString, int[][] symbolMap, int x, int y, char yourSymbol) {
        return boardString.charAt(symbolMap[x][y]) == yourSymbol;
    }

    private boolean isCorner(int y, int x) {
        return (y == 0 && x == 0) ||
               (y == 2 && x == 0) ||
               (y == 0 && x == 2) ||
               (y == 2 && x == 2);
    }

    private Pair<Integer, Integer> attemptMiddle(String boardString, int[][] symbolMap, char yourSymbol) {
        return boardString.isBlank() ? new Pair<>(1,1) : null;
    }

    private Pair<Integer,Integer> winHorizontally(TicTacToeBoard curBoard, String curBoardString, int[][] symbolMap, char yourSymbol) {
        Pair<Integer, Integer> coords = null;

        for (int x = 0; x < 3; x++){
            int count = 0;
            for(int y = 0; y < 3 && count < 2; y++) {
                if (yourSymbolIsAt(curBoardString, symbolMap, x, y, yourSymbol)) {
                    count++;
                }
            }
            if (count == 2) {
                // find empty spot in row
                for (int i = 0; i < 3; i++) {
                    if (curBoard.isSquareOpen(new Pair<>(i,x))) {
                        coords = new Pair<>(i,x);
                        x = 3;
                        break;
                    }
                }
            }
        }
        return coords;
    }

    private Pair<Integer,Integer> winVertically(TicTacToeBoard curBoard, String curBoardString, int[][] symbolMap, char yourSymbol) {
        Pair<Integer,Integer> coords = null;

        for (int y = 0; y < 3; y++){
            int count = 0;
            for(int x = 0; x < 3 && count < 2; x++) {
                if (yourSymbolIsAt(curBoardString, symbolMap, x, y, yourSymbol)) {
                    count++;
                }
            }
            if (count == 2) {
                // find empty spot in column
                for (int i = 0; i < 3; i++) {
                    if (curBoard.isSquareOpen(new Pair<>(y,i))) {
                        coords = new Pair<>(y,i);
                        y = 3;
                        break;
                    }
                }
            }
        }
        return coords;
    }

    private Pair<Integer,Integer> winDiagonallyRight(TicTacToeBoard curBoard, String curBoardString, int[][] symbolMap, char yourSymbol) {
        Pair<Integer,Integer> coords = null;
        int count = 0;

        for (int x1 = 0, y1 = 0; x1 < 3 && y1 < 3; x1++, y1++)  {
            if (yourSymbolIsAt(curBoardString, symbolMap, x1, y1, yourSymbol)) {
                count++;
            }
            if (count == 2) {
                // find empty spot
                for (int x2 = 0, y2 = 0; x2 < 3 && y2 < 3; x2++, y2++)  {
                    if (curBoard.isSquareOpen(new Pair<>(y2,x2))) {
                        coords = new Pair<>(y2,x2);
                        x1 = y1 = 3;
                        break;
                    } 
                }
            }
        }
        return coords;
    }

    private Pair<Integer,Integer> winDiagonallyLeft(TicTacToeBoard curBoard, String curBoardString, int[][] symbolMap, char yourSymbol) {
        Pair<Integer,Integer> coords = null;
        int count = 0;

        for (int x1 = 0, y1 = 2; x1 < 3 && y1 >= 0; x1++, y1--)  {
            if (yourSymbolIsAt(curBoardString, symbolMap, x1, y1, yourSymbol)) {
                count++;
            }
            if (count == 2) {
                // find empty spot
                for (int x2 = 0, y2 = 2; x2 < 3 && y2 >= 0; x2++, y2--)  {
                    if (curBoard.isSquareOpen(new Pair<>(y2,x2))) {
                        coords = new Pair<>(y2,x2);
                        x1 = 3; y1 = -1;
                        break;
                    } 
                }
            }
        }
        return coords;
    }

    private ArrayList<Pair<Integer, Integer>> calculateWinningMove(TicTacToeBoard curBoard, String curBoardString, int[][] symbolMap, char yourSymbol) {
        ArrayList<Pair<Integer,Integer>> moveCoords = new ArrayList<>();
        moveCoords.add(winHorizontally(curBoard, curBoardString, symbolMap, yourSymbol));
        moveCoords.add(winVertically(curBoard, curBoardString, symbolMap, yourSymbol));
        moveCoords.add(winDiagonallyRight(curBoard, curBoardString, symbolMap, yourSymbol));
        moveCoords.add(winDiagonallyLeft(curBoard, curBoardString, symbolMap, yourSymbol));
        return moveCoords;
    }

    private Pair<Integer, Integer> randomMove(TicTacToeBoard curBoard) {
        // otherwise, be random
        Random rand = new Random();
        int x;
        int y;
        do {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        } while (!curBoard.isSquareOpen(new Pair<>(y,x)));
        return new Pair<>(y,x);
    }

    private Pair<Integer, Integer> attemptCorners(TicTacToeBoard curBoard, String curBoardString, int[][] symbolMap, char yourSymbol) {
        Random rand = new Random();
        ArrayList<Pair<Integer,Integer>> availCorners = new ArrayList<>();
        Pair<Integer, Integer> coords = null;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (curBoard.isSquareOpen(new Pair<>(y,x)) && isCorner(y, x)) {
                    availCorners.add(new Pair<>(y,x));
                }
            }
        }
        if (!availCorners.isEmpty()) 
            coords = availCorners.get(rand.nextInt(availCorners.size()));

        return coords;
    }

    private Pair<Integer, Integer> attemptWin(TicTacToeBoard curBoard, String curBoardString, int[][] symbolMap, char yourSymbol) {
        for (Pair<Integer, Integer> move: calculateWinningMove(curBoard, curBoardString, symbolMap, yourSymbol)) {
            if (move != null)
                return move;
        }
        return null;
    }

    private Pair<Integer, Integer> stopWin(TicTacToeBoard curBoard, String curBoardString, int[][] symbolMap, char yourSymbol) {
        return attemptWin(curBoard, curBoardString, symbolMap, yourSymbol == 'X' ? 'O' : 'X');
    }


    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
        String curBoardString = curBoard.buildSquaresString();
        int[][] symbolMap = {
            {0,1,2},
            {3,4,5},
            {6,7,8}
        };

        Pair<Integer, Integer> winningMove = attemptWin(curBoard, curBoardString, symbolMap, yourSymbol);
        if (winningMove != null)
            return winningMove;

        Pair<Integer, Integer> defensiveMove = stopWin(curBoard, curBoardString, symbolMap, yourSymbol);
        if (defensiveMove != null)
            return defensiveMove;

        Pair<Integer, Integer> middleMove = attemptMiddle(curBoardString, symbolMap, yourSymbol);
        if (middleMove != null)
            return middleMove;

        Pair<Integer, Integer> cornersMove = attemptCorners(curBoard, curBoardString, symbolMap, yourSymbol);
        if (cornersMove != null)
            return cornersMove;

        return randomMove(curBoard);
    }
}
