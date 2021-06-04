package edu.ithaca.dragon.games.tictactoe.player;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public class RuleBasedAgentStephen implements TicTacToePlayer {

	@Override
	public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
        if(boardEmpty(curBoard)) return new Pair<>(0,0);
        String[] board = curBoard.buildSquaresString().split("");
        int numYou = countSymbol(board, ""+yourSymbol);
        int numOpp = countSymbol(board,"=") - numYou;
        String oppSym =  (""+yourSymbol).equals("X") ? "O":"X";
        boolean first = numOpp == numYou;
        if(countSymbol(board, ""+yourSymbol) == 0 && countSymbol(board, "=") == 1){
            if(curBoard.isSquareOpen(new Pair<>(1,1))){ 
                return new Pair<>(1,1);
            }
        }
        Pair<Integer, Integer> win = winLocation(board, ""+yourSymbol);
        if(win != null) return win;
        Pair<Integer, Integer> block = blockLocation(board);
        if(block != null) return block;
        if(countSymbol(board, ""+yourSymbol) == 1 && countSymbol(board, "=") == 3 && board[4].equals(""+yourSymbol)){
            if((board[0].equals(oppSym) && board[8].equals(oppSym))||(board[2].equals(oppSym) && board[6].equals(oppSym))){
                if(board[4].equals(""+yourSymbol)){
                    return new Pair<>(1,0);
                }
            }
            if(board[0].equals(oppSym))return new Pair<>(2,2);
            if(board[2].equals(oppSym))return new Pair<>(0,2);
            if(board[6].equals(oppSym))return new Pair<>(2,0);
            if(board[8].equals(oppSym))return new Pair<>(0,0);
            if(board[1].equals(oppSym) || board[3].equals(oppSym)) return new Pair<>(0,0);
        }
        if(first && numYou + numOpp == 2){
            if(!curBoard.isSquareOpen(new Pair<>(1,0))) return new Pair<>(0,1);
            else if(!curBoard.isSquareOpen(new Pair<>(0,1))) return new Pair<>(1,0);
            else if(!curBoard.isSquareOpen(new Pair<>(2,1))) return new Pair<>(2,0);
            else if(!curBoard.isSquareOpen(new Pair<>(1,2))) return new Pair<>(0,2);
        }
        else if(first && numYou+numOpp == 4){
            if(!board[8].equals(oppSym) && (board[1].equals(oppSym) || board[3].equals(oppSym))){
                if(curBoard.isSquareOpen(new Pair<>(1,1))) return new Pair<>(1,1);
            }
        }
        if(curBoard.isSquareOpen(new Pair<>(2,2))) return new Pair<>(2,2);
        if(curBoard.isSquareOpen(new Pair<>(0,2))) return new Pair<>(0,2);
        if(curBoard.isSquareOpen(new Pair<>(2,0))) return new Pair<>(2,0);
        if(curBoard.isSquareOpen(new Pair<>(2,2))) return new Pair<>(0,0);
        if(curBoard.isSquareOpen(new Pair<>(1,2))) return new Pair<>(1,2);
        if(curBoard.isSquareOpen(new Pair<>(1,0))) return new Pair<>(1,0);
        if(curBoard.isSquareOpen(new Pair<>(0,1))) return new Pair<>(0,1);
        if(curBoard.isSquareOpen(new Pair<>(2,1))) return new Pair<>(2,1);
        if(curBoard.isSquareOpen(new Pair<>(2,2))) return new Pair<>(1,1);
        return null;

    }
    
    public int countSymbol(String[] board, String symbol){
        int count = 0;
        if(symbol.equals("=")){
            for(int x = 0; x < 9; x ++){
                if(!board[x].equals(" ")) count++;
            }
        }
        else{
            for( int x = 0; x < 9; x ++){
                if(board[x].equals(symbol)) count++;
            }
        }
        return count;
    }

    public Pair<Integer, Integer> winLocation(String[] board, String y){
        for(int x = 0; x < 3; x ++){
            int offset = x * 3;
            if(board[offset].equals(board[offset + 1]) && board[offset + 2].equals(" ") && board[offset].equals(y)) return new Pair<>(2, x);
            else if(board[offset].equals(board[offset + 2]) && board[offset + 1].equals(" ") && board[offset].equals(y)) return new Pair<>(1, x);
            else if(board[offset + 2].equals(board[offset + 1]) && board[offset].equals(" ") && board[offset + 2].equals(y)) return new Pair<>(0, x);
            else if(board[x].equals(board[x + 3]) && board[x + 6].equals(" ") && board[x].equals(y))return new Pair<>(x, 2);
            else if(board[x].equals(board[x + 6]) && board[x + 3].equals(" ") && board[x].equals(y))return new Pair<>(x, 1);
            else if(board[x + 6].equals(board[x + 3]) && board[x].equals(" ") && board[x + 6].equals(y))return new Pair<>(x, 0);
        }
        if(board[0].equals(board[4]) && board[8].equals(" ") && board[4].equals(y))return new Pair<>(2,2);
        else if(board[8].equals(board[4]) && board[0].equals(" ") && board[4].equals(y))return new Pair<>(0,0);
        else if(board[2].equals(board[4]) && board[6].equals(" ") && board[4].equals(y))return new Pair<>(0,2);
        else if(board[6].equals(board[4]) && board[2].equals(" ") && board[4].equals(y))return new Pair<>(2,0);
        else if(board[0].equals(board[8]) && board[4].equals(" ") && board[0].equals(y))return new Pair<>(1,1);
        else if(board[2].equals(board[6]) && board[4].equals(" ") && board[2].equals(y))return new Pair<>(1,1);
        return null;
    }

    public Pair<Integer, Integer> blockLocation(String[] board){
        for(int x = 0; x < 3; x ++){
            int offset = x * 3;
            if(board[offset].equals(board[offset + 1]) && board[offset + 2].equals(" ") && !board[offset].equals(" ")) return new Pair<>(2, x);
            else if(board[offset].equals(board[offset + 2]) && board[offset + 1].equals(" ") && !board[offset].equals(" ")) return new Pair<>(1, x);
            else if(board[offset + 2].equals(board[offset + 1]) && board[offset].equals(" ") && !board[offset + 2].equals(" ")) return new Pair<>(0, x);
            else if(board[x].equals(board[x + 3]) && board[x + 6].equals(" ") && !board[x].equals(" "))return new Pair<>(x, 2);
            else if(board[x].equals(board[x + 6]) && board[x + 3].equals(" ") && !board[x].equals(" "))return new Pair<>(x, 1);
            else if(board[x + 6].equals(board[x + 3]) && board[x].equals(" ") && !board[x + 6].equals(" "))return new Pair<>(x, 0);
        }
        if(board[0].equals(board[4]) && board[8].equals(" ") && !board[4].equals(" "))return new Pair<>(2,2);
        else if(board[8].equals(board[4]) && board[0].equals(" ") && !board[4].equals(" "))return new Pair<>(0,0);
        else if(board[2].equals(board[4]) && board[6].equals(" ") && !board[4].equals(" "))return new Pair<>(0,2);
        else if(board[6].equals(board[4]) && board[2].equals(" ") && !board[4].equals(" "))return new Pair<>(2,0);
        else if(board[0].equals(board[8]) && board[4].equals(" ") && !board[0].equals(" "))return new Pair<>(1,1);
        else if(board[2].equals(board[6]) && board[4].equals(" ") && !board[2].equals(" "))return new Pair<>(1,1);
        return null;
    }

    public boolean boardEmpty(TicTacToeBoard board){
        for(int x = 0; x < 9; x ++){
            if(!board.isSquareOpen(new Pair<>(x % 3, (int)(x/3)))){
                return false;
            }
        }
        return true;
    }
}