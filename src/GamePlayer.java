import java.util.ArrayList;
import java.util.Random;

public class GamePlayer {

    private int maxDepth;
    private char playerLetter;

    public GamePlayer(int maxDepth, char playerLetter) {
        this.maxDepth = maxDepth;
        this.playerLetter = playerLetter;
    }

    public int getMaxDepth(){
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth){
        this.maxDepth = maxDepth;
    }

    public char getPlayerLetter(){
        return playerLetter;
    }

    public void setPlayerLetter(char playerLetter){
        this.playerLetter = playerLetter;
    }


    public Move MiniMax(Board gameBoard){

        if (playerLetter == 'X')
        {
            System.out.println("MiniMax1");
            return max(gameBoard, 0);
        }
        //If the O plays then it wants to MINimize the heuristics value
        else
        {
            System.out.println("MiniMax2");
            return min(gameBoard, 0);
        }

    }

    public Move max(Board board, int depth){

        Random r = new Random();
        /* If MAX is called on a state that is terminal or after a maximum depth is reached,
         * then a heuristic is calculated on the state and the move returned.
         */
        if((board.isTerminal()) || (depth == maxDepth)){
            Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
            return lastMove;
        }
        //The children-moves of the state are calculated
        ArrayList<Board> children = new ArrayList<Board>(board.getChildren('X'));
        Move maxMove = new Move(Integer.MIN_VALUE);
        for (Board child : children){
            //And for each child min is called, on a lower depth
            Move move = min(child, depth + 1);
            //The child-move with the greatest value is selected and returned by max
            if(move.getValue() >= maxMove.getValue()){
                if ((move.getValue() == maxMove.getValue())){
                    //If the heuristic has the save value then we randomly choose one of the two moves
                    if (r.nextInt(2) == 0) {
                        maxMove.setRow(child.getLastMove().getRow());
                        maxMove.setCol(child.getLastMove().getCol());
                        maxMove.setValue(move.getValue());
                    }
                }else{
                    maxMove.setRow(child.getLastMove().getRow());
                    maxMove.setCol(child.getLastMove().getCol());
                    maxMove.setValue(move.getValue());
                }
            }
        }
        return maxMove;
    }

    public Move min(Board board, int depth){
        Random r = new Random();
        if((board.isTerminal()) || (depth == maxDepth)){
            Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
            return lastMove;
        }
        ArrayList<Board> children = new ArrayList<Board>(board.getChildren('O'));
        Move minMove = new Move(Integer.MAX_VALUE);
        for (Board child : children){
            Move move = max(child, depth + 1);
            if(move.getValue() <= minMove.getValue()){
                if ((move.getValue() == minMove.getValue())){
                    if (r.nextInt(2) == 0){
                        minMove.setRow(child.getLastMove().getRow());
                        minMove.setCol(child.getLastMove().getCol());
                        minMove.setValue(move.getValue());
                    }
                }else{
                    minMove.setRow(child.getLastMove().getRow());
                    minMove.setCol(child.getLastMove().getCol());
                    minMove.setValue(move.getValue());
                }
            }
        }
        return minMove;
    }

}

