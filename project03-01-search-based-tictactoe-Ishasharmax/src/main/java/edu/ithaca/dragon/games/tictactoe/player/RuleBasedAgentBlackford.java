package edu.ithaca.dragon.games.tictactoe.player;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

public class RuleBasedAgentBlackford implements TicTacToePlayer {
    
    private int[][] preferredMoves = {
        {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
        {0, 1}, {1, 0}, {1, 2}, {2, 1}};
    
    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
        
        char oppSymbol = 'X';
        if(yourSymbol == 'X'){
            oppSymbol = 'O';
        }
        Pair<Integer,Integer> check = new Pair<>(-1,-1);
        Pair<Integer,Integer> winHorz = winningHorizontal(curBoard, yourSymbol);
        Pair<Integer,Integer> blockHorz = blockedMoveHorz(curBoard, oppSymbol);
        Pair<Integer,Integer> winVert = winningVertical(curBoard, yourSymbol);
        Pair<Integer,Integer> blockVert =  blockedMoveVertical(curBoard, oppSymbol);
        Pair<Integer,Integer> blockDiaL = blockedMoveDiagonalL(curBoard, oppSymbol);
        Pair<Integer,Integer> blockDiaR = blockedMoveDiagonalR(curBoard, oppSymbol);
        Pair<Integer,Integer> winDiaL =  winningDiagonalL(curBoard, yourSymbol);
        Pair<Integer,Integer> winDiaR =  winningDiagonalR(curBoard, yourSymbol);
        Pair<Integer,Integer> blockForkingOpp = blockedForkingOpp(curBoard, oppSymbol);
        
        if(!blockForkingOpp.equals(check)){
            return blockForkingOpp;
        }
        else if(!winHorz.equals(check)){
            return winHorz;
        } 
        else if(!winDiaL.equals(check)){
            return winDiaL;
        } 
        else if(!winDiaR.equals(check)){
            return winDiaR;
        }
        else if(!winVert.equals(check)){
            return winVert;
        }
        else if(!blockHorz.equals(check)){
            return blockHorz;
        }
        else if(!blockDiaL.equals(check)){
            return blockDiaL;
        }
        else if(!blockDiaR.equals(check)){
            return blockDiaR;
        }
        else if(!blockVert.equals(check)){
            return blockVert;
        }
        
       for(int x = 0; x < 9; x++){
           for(int y = 0; y < 1; y++){
                if(curBoard.isSquareOpen(new Pair<>(preferredMoves[x][y], preferredMoves[x][y+1]))){
                    return new Pair<>(preferredMoves[x][y], preferredMoves[x][y+1]);
                }
            }
        }
        throw new IllegalArgumentException("Board with no moves given to player:\n" + curBoard.displayString());
    }
    private String[][] boardLayout(TicTacToeBoard curBoard){
        String[] boardArr = curBoard.buildSquaresString().split("");
        String[][] board = new String[3][3];
       
        int count = 0;
        int countTwo = 0;
        for(int i = 0; i < boardArr.length; i++){
            board[count][countTwo] = boardArr[i];
            if(i== 2 || i == 5){
                count++;
                countTwo = -1;
            }
            countTwo++;

        }
        return board;
    }

    private Pair<Integer,Integer> winningHorizontal(TicTacToeBoard curBoard, char yourSymbol){
        String[][] board = boardLayout(curBoard);
        for(int i = 0; i < 3; i++){
            int iLoc = -1;
            int jLoc = -1;
            int count = 0;
            int occ = 0;
            for(int j = 0; j < 3; j++){
                if(curBoard.isSquareOpen(new Pair<>(j, i))){
                    iLoc = j;
                    jLoc = i;
                    //System.out.println("loc i : " + iLoc + " " + "loc j: " + jLoc);
                }else{
                    occ++;
                }
                if(board[i][j].equals(yourSymbol + "")){
                    count++;
                }
            }
            if(count == 2 && occ != 3){
                //System.out.println("Winner Horizontal");
                return new Pair<>(iLoc,jLoc);
            }
        }
        return new Pair<>(-1,-1);
    }
    private Pair<Integer, Integer> blockedMoveHorz(TicTacToeBoard curBoard, char oppSymbol){
        String[][] board = boardLayout(curBoard);
        for(int i = 0; i < 3; i++){
            int iLoc = -1;
            int jLoc = -1;
            int count = 0;
            int occ = 0;
            for(int j = 0; j < 3; j++){
                if(curBoard.isSquareOpen(new Pair<>(j, i))){
                    iLoc = j;
                    jLoc = i;
                
                }else{
                    occ++;
                }
                if(board[i][j].equals(oppSymbol + "")){
                    count++;
                }
            }
            if(count == 2 && occ != 3){
                //System.out.println("Blocked Horizontal");
                return new Pair<>(iLoc,jLoc);
            }
        }
        return new Pair<>(-1,-1);
    }
    private Pair<Integer,Integer> winningVertical(TicTacToeBoard curBoard, char yourSymbol){
        String[][] board = boardLayout(curBoard);
        for(int i = 0; i < 3; i++){
            int iLoc = -1;
            int jLoc = -1;
            int count = 0;
            int occ = 0;
            for(int j = 0; j < 3; j++){
                
                if(curBoard.isSquareOpen(new Pair<>(i, j))){
                    iLoc = i;
                    jLoc = j;
                }else{
                    occ++;
                }
               
                if(board[j][i].equals(yourSymbol + "")){
                    count++;
                }
            }
            if(count == 2 && occ != 3){
                //System.out.println("Winner Vertical");
                return new Pair<>(iLoc,jLoc);
            }
            
        }
        return new Pair<>(-1,-1);
    }


    private Pair<Integer,Integer> blockedMoveVertical(TicTacToeBoard curBoard, char oppSymbol){
        String[][] board = boardLayout(curBoard);
        for(int i = 0; i < 3; i++){
            int iLoc = -1;
            int jLoc = -1;
            int count = 0;
            int occ = 0;
            for(int j = 0; j < 3; j++){
                
                if(curBoard.isSquareOpen(new Pair<>(i, j))){
                    iLoc = i;
                    jLoc = j;
                }else{
                    occ++;
                }
               
                if(board[j][i].equals(oppSymbol + "")){
                    count++;
                }
            }
            if(count == 2 && occ != 3){
                //System.out.println("Blocked Vertical");
                return new Pair<>(iLoc,jLoc);
            }
            
        }
        return new Pair<>(-1,-1);
    }
    private Pair<Integer,Integer> blockedMoveDiagonalL(TicTacToeBoard curBoard, char oppSymbol){
        String[][] board = boardLayout(curBoard);
        int iLoc = -1;
        int count = 0;
        int occ = 0;
        for(int i = 0; i < 3; i++){
            
            if(curBoard.isSquareOpen(new Pair<>(i, i))){
                    iLoc = i;
            }else{
                occ++;
            }
            if(board[i][i].equals(oppSymbol + "")){
                count++;
            }
        }
        if(count == 2 && occ != 3){
            //System.out.println("Blocked Diagonal Left");
            return new Pair<>(iLoc,iLoc);
        }
        return new Pair<>(-1,-1);
    }
    private Pair<Integer,Integer> blockedMoveDiagonalR(TicTacToeBoard curBoard, char oppSymbol){
        String[][] board = boardLayout(curBoard);
        int iLoc = -1;
        int count = 0;
        int occ = 0;
        int num = 2;
        for(int i = 0; i < 3; i++){
            
            if(curBoard.isSquareOpen(new Pair<>(i, i))){
                    iLoc = i;
            }else{
                occ++;
            }
            if(board[i][i].equals(oppSymbol + "")){
                count++;
                num--;
            }
        }
        if(count == 2 && occ != 3){
            //System.out.println("Blocked Diagonal Right");
            return new Pair<>(iLoc,iLoc);
        }
        return new Pair<>(-1,-1);
    }
    private Pair<Integer,Integer> winningDiagonalL(TicTacToeBoard curBoard, char yourSymbol){
        String[][] board = boardLayout(curBoard);
        int iLoc = -1;
        int count = 0;
        int occ = 0;
        for(int i = 0; i < 3; i++){
            
            if(curBoard.isSquareOpen(new Pair<>(i, i))){
                    iLoc = i;
            }else{
                occ++;
            }
            if(board[i][i].equals(yourSymbol + "")){
                count++;
            }
        }
        if(count == 2 && occ != 3){
            //System.out.println("Won Diagonal Left");
            return new Pair<>(iLoc,iLoc);
        }
        return new Pair<>(-1,-1);
    }
    private Pair<Integer,Integer> winningDiagonalR(TicTacToeBoard curBoard, char yourSymbol){
        String[][] board = boardLayout(curBoard);
        int iLoc = -1;
        int count = 0;
        int occ = 0;
        int num = 2;
        for(int i = 0; i < 3; i++){
            
            if(curBoard.isSquareOpen(new Pair<>(i, i))){
                    iLoc = i;
            }else{
                occ++;
            }
            if(board[i][i].equals(yourSymbol + "")){
                count++;
                num--;
            }
        }
        if(count == 2 && occ != 3){
            //System.out.println("Won Diagonal Right");
            return new Pair<>(iLoc,iLoc);
        }
        return new Pair<>(-1,-1);
    }


    private Pair<Integer,Integer> blockedForkingOpp(TicTacToeBoard curBoard, char oppSymbol){
        
        if(curBoard.checkSquare(new Pair<>(0, 0)) == oppSymbol){
            if(curBoard.isSquareOpen(new Pair<>(2, 2))){
                    return new Pair<>(2, 2);
            }     
        }
        else if(curBoard.checkSquare(new Pair<>(2, 0)) == oppSymbol){
            if(curBoard.isSquareOpen(new Pair<>(0, 2))){
                return new Pair<>(0, 2);
        }   
        }
        else if(curBoard.checkSquare(new Pair<>(0, 2)) == oppSymbol){
            if(curBoard.isSquareOpen(new Pair<>(2, 0))){
                return new Pair<>(2, 0);
        }   
        }
        else if(curBoard.checkSquare(new Pair<>(2, 2)) == oppSymbol){
            if(curBoard.isSquareOpen(new Pair<>(0, 0))){
                return new Pair<>(0, 0);
        }  
        }
        return new Pair<>(-1,-1);
    }
}
