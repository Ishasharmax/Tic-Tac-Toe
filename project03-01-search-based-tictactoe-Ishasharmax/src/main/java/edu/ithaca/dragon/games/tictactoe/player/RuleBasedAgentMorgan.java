package edu.ithaca.dragon.games.tictactoe.player;
import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;
import java.util.Random; 
import org.javatuples.Pair;

public class RuleBasedAgentMorgan implements TicTacToePlayer{
    @Override
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
       //vertical
       for(int x=0; x<3; x++){
            if (curBoard.checkSquare(new Pair<>(x,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(x,1)) == yourSymbol && curBoard.isSquareOpen(new Pair<>(x,2)) ){
                return new Pair<>(x,2);
        
            }
            if (curBoard.checkSquare(new Pair<>(x,0)) == yourSymbol  && curBoard.checkSquare(new Pair<>(x,2)) == yourSymbol && curBoard.isSquareOpen(new Pair<>(x,1)) ){
                return new Pair<>(x,1);
            }
            if ( curBoard.checkSquare(new Pair<>(x,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(x,2)) == yourSymbol && curBoard.isSquareOpen(new Pair<>(x,0)) ){
                return new Pair<>(x,0);
            }
       }

       
        //horizontal
        for(int y=0; y<3; y++){
            if (curBoard.checkSquare(new Pair<>(0,y)) == yourSymbol && curBoard.checkSquare(new Pair<>(1,y)) == yourSymbol && curBoard.isSquareOpen(new Pair<>(2,y)) ){
                return new Pair<>(2,y);
            }
            if (curBoard.checkSquare(new Pair<>(0,y)) == yourSymbol  && curBoard.checkSquare(new Pair<>(2,y)) == yourSymbol && curBoard.isSquareOpen(new Pair<>(1,y))){
                return new Pair<>(1,y);
            }
            if ( curBoard.checkSquare(new Pair<>(1,y)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,y)) == yourSymbol && curBoard.isSquareOpen(new Pair<>(0,y))){
                return new Pair<>(0,y);
            }
        }

        //diagonal 
        if (curBoard.checkSquare(new Pair<>(0,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol  && curBoard.isSquareOpen(new Pair<>(2,2))){
            return new Pair<>(2,2);
        }
        if (curBoard.checkSquare(new Pair<>(0,0)) == yourSymbol  && curBoard.checkSquare(new Pair<>(2,2)) == yourSymbol && curBoard.isSquareOpen(new Pair<>(1,1))){
            return new Pair<>(1,1);
        }
        if ( curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,2)) == yourSymbol && curBoard.isSquareOpen(new Pair<>(0,0))){
            return new Pair<>(0,0);
        }

        if (curBoard.checkSquare(new Pair<>(2,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol  && curBoard.isSquareOpen(new Pair<>(0,2))){
            return new Pair<>(0,2);
        }
        if (curBoard.checkSquare(new Pair<>(2,0)) == yourSymbol  && curBoard.checkSquare(new Pair<>(0,2)) == yourSymbol && curBoard.isSquareOpen(new Pair<>(1,1))){
            return new Pair<>(1,1);
        }
        if ( curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,2)) == yourSymbol && curBoard.isSquareOpen(new Pair<>(2,0))){
            return new Pair<>(2,0);
        }
    
        //vertical opponent block  
       for(int x=0; x<3; x++){
        if ((curBoard.checkSquare(new Pair<>(x,0)) != yourSymbol) && curBoard.checkSquare(new Pair<>(x,0)) != ' ' && curBoard.checkSquare(new Pair<>(x,1)) != yourSymbol && curBoard.checkSquare(new Pair<>(x,1)) != ' ' && curBoard.isSquareOpen(new Pair<>(x,2)) ){
            return new Pair<>(x,2);
        }
        if (curBoard.checkSquare(new Pair<>(x,0)) != yourSymbol  && curBoard.checkSquare(new Pair<>(x,0)) != ' ' && curBoard.checkSquare(new Pair<>(x,2)) != yourSymbol &&  curBoard.isSquareOpen(new Pair<>(x,1)) && curBoard.checkSquare(new Pair<>(x,2)) != ' '){
            return new Pair<>(x,1);
        }
        if ( curBoard.checkSquare(new Pair<>(x,1)) != yourSymbol && curBoard.checkSquare(new Pair<>(x,1)) != ' ' && curBoard.checkSquare(new Pair<>(x,2)) != yourSymbol && curBoard.isSquareOpen(new Pair<>(x,0)) && curBoard.checkSquare(new Pair<>(x,2)) != ' ' ){
            return new Pair<>(x,0);
        }
   }
   //horizontal opponent block
   for(int x=0; x<3; x++){
    if ((curBoard.checkSquare(new Pair<>(0,x)) != yourSymbol) && curBoard.checkSquare(new Pair<>(0,x)) != ' ' && curBoard.checkSquare(new Pair<>(1,x)) != yourSymbol && curBoard.checkSquare(new Pair<>(1,x)) != ' ' && curBoard.isSquareOpen(new Pair<>(2,x)) ){
        return new Pair<>(2,x);
    }
    if (curBoard.checkSquare(new Pair<>(0,x)) != yourSymbol  && curBoard.checkSquare(new Pair<>(0,x)) != ' ' && curBoard.checkSquare(new Pair<>(2,x)) != yourSymbol &&  curBoard.isSquareOpen(new Pair<>(1,x)) && curBoard.checkSquare(new Pair<>(2,x)) != ' '){
        return new Pair<>(1,x);
    }
    if ( curBoard.checkSquare(new Pair<>(1,x)) != yourSymbol && curBoard.checkSquare(new Pair<>(1,x)) != ' ' && curBoard.checkSquare(new Pair<>(2,x)) != yourSymbol && curBoard.isSquareOpen(new Pair<>(0,x)) && curBoard.checkSquare(new Pair<>(2,x)) != ' ' ){
        return new Pair<>(0,x);
    }
}

    //diagonal opponent block
    if (curBoard.checkSquare(new Pair<>(0,0)) != ' ' && curBoard.checkSquare(new Pair<>(0,0)) != yourSymbol && curBoard.checkSquare(new Pair<>(0,0)) == curBoard.checkSquare(new Pair<>(1,1)) && curBoard.isSquareOpen(new Pair<>(2,2))){
        return new Pair<>(2,2);
    }
    if (curBoard.checkSquare(new Pair<>(0,0)) != ' ' && curBoard.checkSquare(new Pair<>(0,0)) != yourSymbol  && curBoard.checkSquare(new Pair<>(2,2)) == curBoard.checkSquare(new Pair<>(0,0))  && curBoard.isSquareOpen(new Pair<>(1,1))){
        return new Pair<>(1,1);
    }
    if (curBoard.checkSquare(new Pair<>(1,1)) != ' ' && curBoard.checkSquare(new Pair<>(1,1)) != yourSymbol && curBoard.checkSquare(new Pair<>(2,2)) ==  curBoard.checkSquare(new Pair<>(1,1)) && curBoard.isSquareOpen(new Pair<>(0,0))){
        return new Pair<>(0,0);
    }

    if (curBoard.checkSquare(new Pair<>(2,0)) != ' ' && curBoard.checkSquare(new Pair<>(2,0)) != yourSymbol && curBoard.checkSquare(new Pair<>(1,1)) == curBoard.checkSquare(new Pair<>(2,0))  && curBoard.isSquareOpen(new Pair<>(0,2))){
        return new Pair<>(0,2);
    }
    if (curBoard.checkSquare(new Pair<>(2,0)) != ' ' && curBoard.checkSquare(new Pair<>(2,0)) != yourSymbol  && curBoard.checkSquare(new Pair<>(0,2)) == curBoard.checkSquare(new Pair<>(2,0)) && curBoard.isSquareOpen(new Pair<>(1,1))){
        return new Pair<>(1,1);
    }
    if (curBoard.checkSquare(new Pair<>(1,1)) != ' ' && curBoard.checkSquare(new Pair<>(1,1)) != yourSymbol && curBoard.checkSquare(new Pair<>(0,2)) == curBoard.checkSquare(new Pair<>(1,1)) && curBoard.isSquareOpen(new Pair<>(2,0))){
        return new Pair<>(2,0);
    }

    //Opposite corner placements
    if (curBoard.checkSquare(new Pair<>(0,0)) != yourSymbol && curBoard.checkSquare(new Pair<>(0,0)) != ' ' && curBoard.isSquareOpen(new Pair<>(2,2))){
        return new Pair<>(2,2);
    }
    if (curBoard.checkSquare(new Pair<>(0,2)) != yourSymbol && curBoard.checkSquare(new Pair<>(0,2)) != ' ' && curBoard.isSquareOpen(new Pair<>(2,0))){
        return new Pair<>(2,0);
    }
    if (curBoard.checkSquare(new Pair<>(2,0)) != yourSymbol && curBoard.checkSquare(new Pair<>(2,0)) != ' ' && curBoard.isSquareOpen(new Pair<>(0,2))){
        return new Pair<>(0,2);
    }
    if (curBoard.checkSquare(new Pair<>(2,2)) != yourSymbol && curBoard.checkSquare(new Pair<>(2,2)) != ' ' && curBoard.isSquareOpen(new Pair<>(0,0))){
        return new Pair<>(0,0);
    }
    
    
    
    
    

    //place in corner
    if (curBoard.isSquareOpen(new Pair<>(0,0))){
        return new Pair<>(0,0);
    }
    if (curBoard.isSquareOpen(new Pair<>(0,2))){
        return new Pair<>(0,2);
    }
    if (curBoard.isSquareOpen(new Pair<>(2,0))){
        return new Pair<>(2,0);
    }
    if (curBoard.isSquareOpen(new Pair<>(2,2))){
        return new Pair<>(2,2);
    }

    //place in center
    if (curBoard.isSquareOpen(new Pair<>(1,1))){
        return new Pair<>(1,1);
    }
    

    //place in non corners
    if (curBoard.isSquareOpen(new Pair<>(0,2))){
        return new Pair<>(0,2);
    }
    if (curBoard.isSquareOpen(new Pair<>(1,0))){
        return new Pair<>(1,0);
    }
    if (curBoard.isSquareOpen(new Pair<>(1,2))){
        return new Pair<>(1,2);
    }
    if (curBoard.isSquareOpen(new Pair<>(2,1))){
        return new Pair<>(2,1);
    }
    Random rand = new Random();
       while (true){
            int x = rand.nextInt(3);
            int y = rand.nextInt(3);
            if (curBoard.isSquareOpen(new Pair<>(x,y))){
                return new Pair<>(x,y);
            }
        }
    }
}
