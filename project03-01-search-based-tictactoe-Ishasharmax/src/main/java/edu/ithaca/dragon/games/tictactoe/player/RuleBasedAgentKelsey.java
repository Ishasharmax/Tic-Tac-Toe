package edu.ithaca.dragon.games.tictactoe.player;

import java.util.*; 


//import org.graalvm.compiler.lir.gen.DiagnosticLIRGeneratorTool;
import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;


public class RuleBasedAgentKelsey implements TicTacToePlayer {

    Pair<Integer, Integer> topLeft = new Pair<>(0,0);
        Pair<Integer, Integer> topMiddle = new Pair<>(1,0);
        Pair<Integer, Integer> topRight = new Pair<>(2,0);
        Pair<Integer, Integer> middleLeft = new Pair<>(0,1);
        Pair<Integer, Integer> middleMiddle = new Pair<>(1,1);
        Pair<Integer, Integer> middleRight = new Pair<>(2,1);
        Pair<Integer, Integer> bottomLeft = new Pair<>(0,2);
        Pair<Integer, Integer> bottomMiddle = new Pair<>(1,2);
        Pair<Integer, Integer> bottomRight = new Pair<>(2,2);

    public Pair<Integer,Integer> checkForThree(String[] ch, String symbol, TicTacToeBoard curboard){

        if (ch[0].equals(symbol) && ch[1].equals(symbol) || ch[1].equals(symbol) && ch[2].equals(symbol) || ch[6].equals(symbol) && ch[4].equals(symbol)){
            if(curboard.isSquareOpen(topRight) == true){
                return topRight;
            }
        }
        else if (ch[0].equals(symbol) && ch[2].equals(symbol) || ch[4].equals(symbol) && ch[7].equals(symbol)){
            if(curboard.isSquareOpen(topMiddle) == true){
                return topMiddle;
            }
        }
        else if (ch[3].equals(symbol) && ch[6].equals(symbol) || ch[5].equals(symbol) && ch[8].equals(symbol) || ch[4].equals(symbol) && ch[8].equals(symbol)){
            if(curboard.isSquareOpen(topLeft) == true){
                return topLeft;
            }
        }
        else if (ch[3].equals(symbol) && ch[4].equals(symbol) || ch[2].equals(symbol) && ch[8].equals(symbol)){
            if(curboard.isSquareOpen(middleRight) == true){
                return middleRight;
            }
        }
        else if (ch[4].equals(symbol) && ch[5].equals(symbol) || ch[2].equals(symbol) && ch[8].equals(symbol) || ch[0].equals(symbol) && ch[6].equals(symbol)){
            if(curboard.isSquareOpen(middleLeft) == true){
                return middleLeft;
            }
        }
        else if (ch[3].equals(symbol) && ch[5].equals(symbol) || ch[1].equals(symbol) && ch[7].equals(symbol) || ch[0].equals(symbol) && ch[8].equals(symbol) || ch[6].equals(symbol) && ch[2].equals(symbol)){
            if(curboard.isSquareOpen(middleMiddle) == true){
                return middleMiddle;
            }
        }
        else if (ch[6].equals(symbol) && ch[7].equals(symbol) || ch[2].equals(symbol) && ch[5].equals(symbol) || ch[0].equals(symbol) && ch[4].equals(symbol)){
            if(curboard.isSquareOpen(bottomRight) == true){
                return bottomRight;
            }
        }
        else if (ch[6].equals(symbol) && ch[8].equals(symbol) || ch[1].equals(symbol) && ch[4].equals(symbol)){
            if(curboard.isSquareOpen(bottomMiddle) == true){
                return bottomMiddle;
            }
        }
        else if (ch[0].equals(symbol) && ch[3].equals(symbol) || ch[7].equals(symbol) && ch[8].equals(symbol) || ch[2].equals(symbol) && ch[4].equals(symbol)){
            if(curboard.isSquareOpen(bottomLeft) == true){
                return bottomLeft;
            }
        }
        else{
            return null;
        }
        return null;

    }

    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {

        //System.out.println("Your Player's Symbol : " + yourSymbol);


        String currentBoardString = curBoard.buildSquaresString();
        // Creating array of string length 
        String[] ch = new String[currentBoardString.length()]; 
  
        // Copy character by character into array 
        for (int i = 0; i < currentBoardString.length(); i++) { 
        
            ch[i] = Character.toString(currentBoardString.charAt(i)); 

        } 

        if (!(currentBoardString.contains("X")) && !(currentBoardString.contains("0"))){
            return topRight;
        }

        String yourSymbolString = Character.toString(yourSymbol);

        String opponentSymbol = "fat yoshi";
        if(yourSymbolString.equals("O")){
            opponentSymbol = "X";
        }
        else {
            opponentSymbol = "O";
        }

        List<Pair<Integer, Integer>> priorityMovesList= new ArrayList<>();

        priorityMovesList.add(topRight);
        priorityMovesList.add(topMiddle);
        priorityMovesList.add(middleMiddle);
        priorityMovesList.add(middleRight);
        priorityMovesList.add(topLeft);
        priorityMovesList.add(bottomLeft);
        priorityMovesList.add(bottomRight);
        priorityMovesList.add(middleLeft);
        priorityMovesList.add(bottomMiddle);

        //see if i can win
        Pair <Integer, Integer> winningMove = checkForThree(ch, yourSymbolString, curBoard);
        if(winningMove != null){
            return winningMove;
        }
        Pair <Integer, Integer> savingMove = checkForThree(ch, opponentSymbol, curBoard);
        if(savingMove != null){
            return savingMove;
        }
        else{
            for (int i = 0; i<priorityMovesList.size(); i++){
                Pair<Integer, Integer> nextMove = priorityMovesList.get(i);
                if (curBoard.isSquareOpen(nextMove)== true){
                        return nextMove;
                    }
                }
            }

        throw new IllegalArgumentException("Board with no moves given to player:\n" + curBoard.displayString());
    }
}

    
 


    
    


