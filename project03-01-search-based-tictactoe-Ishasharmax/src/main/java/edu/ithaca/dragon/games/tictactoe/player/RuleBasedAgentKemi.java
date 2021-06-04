package edu.ithaca.dragon.games.tictactoe.player;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;

import org.apache.commons.lang3.ArrayUtils;

public class RuleBasedAgentKemi implements TicTacToePlayer {
    String curBoardState;
    int count;
    int[] allStates;

    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
        curBoardState = curBoard.buildSquaresString();
        char otherSymbol;
        if(yourSymbol == 'X'){
            otherSymbol = 'O';
        }
        else{
            otherSymbol = 'X';
        }
        //Rule #1: Take the winning move if you can
        int playState = checkStates(curBoard, yourSymbol);
        if (playState!= -1){
            // System.out.println("Winning move: " + playState);
            if (playState == 0){
                return moveState1(curBoard);
            }
            if (playState == 1){
                return moveState2(curBoard);
            }
            if (playState == 2){
                return moveState3(curBoard);
            }
            if (playState == 3){
                return moveState4(curBoard);
            }
            if (playState == 4){
                return moveState5(curBoard);
            }
            if (playState == 5){
                return moveState6(curBoard);
            }
            if (playState == 6){
                return moveState7(curBoard);
            }
            if (playState == 7){
                return moveState8(curBoard);
            }
        }
        else{
            playState = checkStates(curBoard, otherSymbol);
            // System.out.println("Else statement for blocking: " + playState);
                if (playState!= -1){
                    if (playState == 0){
                    return moveState1(curBoard);
                    }
                    if (playState == 1){
                        return moveState2(curBoard);
                    }
                    if (playState == 2){
                        return moveState3(curBoard);
                    }
                    if (playState == 3){
                        return moveState4(curBoard);
                    }
                    if (playState == 4){
                        return moveState5(curBoard);
                    }
                    if (playState == 5){
                        return moveState6(curBoard);
                    }
                    if (playState == 6){
                        return moveState7(curBoard);
                    }
                    if (playState == 7){
                        return moveState8(curBoard);
                    }
                //Removes play state once its been blocked off
                allStates = ArrayUtils.remove(allStates, playState);
                }
            }
            // System.out.println("No wins or blocks: Play like Kemi");
            return KemiPlayer(curBoard);
        }
        
    public Pair<Integer, Integer> KemiPlayer(TicTacToeBoard curBoard) {
        if (curBoard.isSquareOpen(new Pair<>(1,1))== true){
            return new Pair<>(1,1);
        }
        else{
            Integer x = (int) Math.round(Math.random());
            Integer y = (int) Math.round(Math.random());
            if(x == 1){
                x = 2;
            }
            if (y==1){
                y=2;
            }
            while((curBoard.isSquareOpen(new Pair<>(0,0)) || curBoard.isSquareOpen(new Pair<>(0,2)) || curBoard.isSquareOpen(new Pair<>(2,0)) || curBoard.isSquareOpen(new Pair<>(2,2))) != false){
                x = (int) Math.round(Math.random());
                y = (int) Math.round(Math.random());
                if(x == 1){
                    x = 2;
                }
                if (y==1){
                    y=2;
                }    
            if(curBoard.isSquareOpen(new Pair<>(x,y)) == true){
                return new Pair<> (x,y);
            }
        }
        while(curBoard.isSquareOpen(new Pair<>(x,y))==false){
            for (y=0; y<3; y++){
                for(x=0; x<3;x++){
                    if (curBoard.isSquareOpen(new Pair<>(x,y))){
                        return new Pair<>(x,y);
                    }
                }
            }
        }
        throw new IllegalArgumentException("Board with no moves given to player:\n" + curBoard.displayString());
      }      
    }

    public int checkStates(TicTacToeBoard curBoard, char symbol){
        allStates = new int[]{winState1(curBoard, symbol), winState2(curBoard, symbol), winState3(curBoard, symbol), winState4(curBoard, symbol), winState5(curBoard, symbol), winState6(curBoard, symbol), winState7(curBoard, symbol), winState8(curBoard, symbol)};
        return findWin(allStates);
    }
    //Returns index of first instance of a state with a score of 2, Returns -1 if no state exists
    public int findWin(int[] allStates){
        int winVal = 2;
        int index = -1;
        for (int i=0; i<allStates.length; i++){
            if(allStates[i] >= winVal){
                index = i;
                return index;
            }
        }
        return index;
    }

    public int winState1(TicTacToeBoard curBoard, char symbol){
        count = 0;
        if(curBoardState.charAt(0) == symbol){
            count+=1;
        }
        if (curBoardState.charAt(1) == symbol){
            count +=1;
        }
        if (curBoardState.charAt(2) == symbol){
            count +=1;
        }
        return count;
    }

    public Pair<Integer, Integer> moveState4(TicTacToeBoard curBoard){
        for(int y=0; y<3; y++){
            if(curBoard.isSquareOpen(new Pair<>(0,y)) == true){
                return new Pair<>(0,y);
            }
    }
    return KemiPlayer(curBoard);
}
    public int winState2(TicTacToeBoard curBoard, char symbol){
            count = 0;
            if(curBoardState.charAt(3) == symbol){
                count+=1;
            }
            if (curBoardState.charAt(4) == symbol){
                count +=1;
            }
            if (curBoardState.charAt(5) == symbol){
                count +=1;
            }
            return count;
        }

        public Pair<Integer, Integer> moveState5(TicTacToeBoard curBoard){
            for(int y=0; y<3; y++){
                if(curBoard.isSquareOpen(new Pair<>(1,y)) == true){
                    return new Pair<>(1,y);
                }
        }
        return KemiPlayer(curBoard);
    }
    public int winState3(TicTacToeBoard curBoard, char symbol){
        count = 0;
        if(curBoardState.charAt(6) == symbol){
            count+=1;
        }
        if (curBoardState.charAt(7) == symbol){
            count +=1;
        }
        if (curBoardState.charAt(8) == symbol){
            count +=1;
        }        
        return count;
    }
    public Pair<Integer, Integer> moveState6(TicTacToeBoard curBoard){
        for(Integer y=0; y<3; y++){
            if(curBoard.isSquareOpen(new Pair<>(2,y)) == true){
                return new Pair<>(2,y);
            }
    }
    return KemiPlayer(curBoard);
}
    public int winState4(TicTacToeBoard curBoard, char symbol){
        count = 0;
        if(curBoardState.charAt(0) == symbol){
            count+=1;
        }
        if (curBoardState.charAt(3) == symbol){
            count +=1;
        }
        if (curBoardState.charAt(6) == symbol){
            count +=1;
        }        
        return count;
    }    
    public Pair<Integer, Integer> moveState1(TicTacToeBoard curBoard){
        for(int x=0; x<3; x++){
            if(curBoard.isSquareOpen(new Pair<>(x,0)) == true){
                return new Pair<>(x, 0);
            }
    }
    return KemiPlayer(curBoard);
}
    public int winState5(TicTacToeBoard curBoard, char symbol){
        count = 0;
        if(curBoardState.charAt(1) == symbol){
            count+=1;
        }
        if (curBoardState.charAt(4) == symbol){
            count +=1;
        }
        if (curBoardState.charAt(7) == symbol){
            count +=1;
        }
        return count;
    }
    public Pair<Integer, Integer> moveState2(TicTacToeBoard curBoard){
        for(int x=0; x<3; x++){
            if(curBoard.isSquareOpen(new Pair<>(x,1)) == true){
                return new Pair<>(x, 1);
            }
    }
    return KemiPlayer(curBoard);
}
    public int winState6(TicTacToeBoard curBoard, char symbol){
        count = 0;
        if(curBoardState.charAt(2) == symbol){
            count+=1;
        }
        if (curBoardState.charAt(5) == symbol){
            count +=1;
        }
        if (curBoardState.charAt(8) == symbol){
            count +=1;
        }
        return count;
    }
    public Pair<Integer, Integer> moveState3(TicTacToeBoard curBoard){
        for(int x=0; x<3; x++){
            if(curBoard.isSquareOpen(new Pair<>(x,2)) == true){
                return new Pair<>(x, 2);
            }
    }
    return KemiPlayer(curBoard);
}
    public int winState7(TicTacToeBoard curBoard, char symbol){
        count = 0;
        if(curBoardState.charAt(0) == symbol){
            count+=1;
        }
        if (curBoardState.charAt(4) == symbol){
            count +=1;
        }
        if (curBoardState.charAt(8) == symbol){
            count +=1;
        }
        return count;
    }
    public Pair<Integer, Integer> moveState7(TicTacToeBoard curBoard){
        for(int x=0; x<3; x++){
            if(curBoard.isSquareOpen(new Pair<>(x,x)) == true){
                return new Pair<>(x, x);
            }
    }
    return KemiPlayer(curBoard);
}
    public int winState8(TicTacToeBoard curBoard, char symbol){
        count = 0;
        if(curBoardState.charAt(2) == symbol){
            count+=1;
        }
        if (curBoardState.charAt(4) == symbol){
            count +=1;
        }
        if (curBoardState.charAt(6) == symbol){
            count +=1;
        }
        return count;
    }
    public Pair<Integer, Integer> moveState8(TicTacToeBoard curBoard){
        if(curBoard.isSquareOpen(new Pair<>(0,2)) == true){
            return new Pair<>(0, 2);
        }
        if(curBoard.isSquareOpen(new Pair<>(1,1)) == true){
            return new Pair<>(1, 1);
        }
        if(curBoard.isSquareOpen(new Pair<>(2,0)) == true){
            return new Pair<>(2, 0);
        }
        return KemiPlayer(curBoard);
    }
}