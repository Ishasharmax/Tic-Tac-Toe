package edu.ithaca.dragon.games.tictactoe.player;


import java.util.*;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.GameStatus;
import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public class RuleBasedAgentEmma implements TicTacToePlayer{
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {

        String boardSpacesByRow = curBoard.buildSquaresString();
        String boardSpacesByCol = new String(new char[] {boardSpacesByRow.charAt(0), boardSpacesByRow.charAt(3), boardSpacesByRow.charAt(6), 
            boardSpacesByRow.charAt(1),boardSpacesByRow.charAt(4), boardSpacesByRow.charAt(7),
            boardSpacesByRow.charAt(2),boardSpacesByRow.charAt(5),boardSpacesByRow.charAt(8)});
        String diagonals = new String(new char[] {boardSpacesByRow.charAt(0), boardSpacesByRow.charAt(4), boardSpacesByRow.charAt(8),
            boardSpacesByRow.charAt(2), boardSpacesByRow.charAt(4), boardSpacesByRow.charAt(6)});
            
        char otherSymbol =' ';
        if(yourSymbol=='O')
            otherSymbol = 'X';
        else
            otherSymbol = 'O';

        try{
            return winOrBlock(boardSpacesByRow, boardSpacesByCol, diagonals, yourSymbol);
        }catch(RuntimeException e){}

        try{
            return winOrBlock(boardSpacesByRow, boardSpacesByCol, diagonals, otherSymbol);
        }catch(RuntimeException e){}

        for (int y=0; y<3; y++){
            for(int x=0; x<3;x++){
                if (curBoard.isSquareOpen(new Pair<>(x,y))){
                    return new Pair<>(x,y);
                }
            }
        }

        throw new IllegalArgumentException("No move to make");
        
    }

    private Pair<Integer,Integer> winOrBlock(String rows, String cols, String diagonals, char symbol){

        for(int i=0;i<=6;i+=3){
            if(rows.substring(i,i+3).compareTo(new String(new char[] {symbol, symbol, ' ' }))==0){
                return new Pair<Integer,Integer>(2,i/3);
            }
            if(rows.substring(i,i+3).compareTo(new String(new char[] {' ', symbol, symbol }))==0){
                return new Pair<Integer,Integer>(0,i/3);
            }
            if(rows.substring(i,i+3).compareTo(new String(new char[] {symbol, ' ' ,symbol }))==0){
                return new Pair<Integer,Integer>(1,i/3);
            }
        }
        for(int i=0;i<=6;i+=3){
            if(cols.substring(i,i+3).compareTo(new String(new char[] {symbol, symbol, ' ' }))==0){
                return new Pair<Integer,Integer>(i/3,2);
            }
            if(cols.substring(i,i+3).compareTo(new String(new char[] {' ', symbol, symbol }))==0){
                return new Pair<Integer,Integer>(i/3,0);
            }
            if(cols.substring(i,i+3).compareTo(new String(new char[] {symbol, ' ' ,symbol }))==0){
                return new Pair<Integer,Integer>(i/3,1);
            }
        }
        for(int i=0;i<6;i+=3){
            if(i<3){
                if(diagonals.substring(i,i+3).compareTo(new String(new char[] {symbol, symbol, ' ' }))==0){
                    return new Pair<Integer,Integer>(2,2);
                }
                if(diagonals.substring(i,i+3).compareTo(new String(new char[] {' ', symbol, symbol }))==0){
                    return new Pair<Integer,Integer>(0,0);
                }
            }
            else{
                if(diagonals.substring(i,i+3).compareTo(new String(new char[] {symbol, symbol, ' ' }))==0){
                    return new Pair<Integer,Integer>(0,2);
                }
                if(diagonals.substring(i,i+3).compareTo(new String(new char[] {' ', symbol, symbol }))==0){
                    return new Pair<Integer,Integer>(2,0);
                }
            }
            if(diagonals.substring(i,i+3).compareTo( new String(new char[] {symbol, ' ' ,symbol }))==0){
                return new Pair<Integer,Integer>(1,1);
            }
        }
        
        throw new RuntimeException("no winning/blocking move");
    }
    /*
    private Pair<Integer,Integer> createFork( TicTacToeBoard curBoard, char symbol){
        //String strSymbol = new String(new char[]{symbol});
        //String corners = new String(new char[]{board.charAt(0),board.charAt(2),board.charAt(6),board.charAt(9)});
        //String [] openOrBlockedCorners = 
        ArrayList <Pair<Integer,Integer>> openCorners = new ArrayList<>(4);
        openCorners.add(new Pair<Integer,Integer>(0,0));
        openCorners.add(new Pair<Integer,Integer>(2,0));
        openCorners.add(new Pair<Integer,Integer>(0,2));
        openCorners.add(new Pair<Integer,Integer>(2,2));

        ArrayList <Pair<Integer,Integer>> cornersWithOs = new ArrayList<>(4);
        for(int i=0; i<4;i++){
            Pair<Integer,Integer> corner = openCorners.get(i);
            if(curBoard.checkSquare(corner)!=' '){
                openCorners.remove(i);
                if(curBoard.checkSquare(corner)=='O')
                    cornersWithOs.add(corner);
            }
        }

        if(openCorners.size()>0 && openCorners.size()<=2){
            for(int i=0;i<2;i++){
                
            }
        }

        throw new RuntimeException("No fork can be produced");
    }*/

}
