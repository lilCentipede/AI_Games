import java.util.List;

public class QueenStrategy {
    private final QueensBoard board;
    QueenStrategy(QueensBoard board){
        this.board = board;
    }

    public void solve(){
        int i =0;
        Queen previous_queen = new Queen(0,0,board.getSize());
        while(! board.isSolved()){
            Queen queen = queenMaxConflicts(previous_queen);
            board.removeQueen(queen);
            previous_queen = choosePositionWithMinConflicts(queen);
            board.placeQueen(queen);
            board.setTheirConflicts();
            ++i;

            //board.visualize();
        }
        System.out.println(i);
        board.visualize();
    }

    public Queen queenMaxConflicts(Queen previous_queen ){
        List<Queen> all_queens = board.getAll_queens();
        int max_conflicts = -1;
        Queen maxQueen = new Queen(0,0,board.getSize());
        for(var queen : all_queens) {
            if(queen != previous_queen){
                queen.setConflicts(all_queens);
                if (max_conflicts < queen.getConflicts()) {
                    maxQueen = queen;
                    max_conflicts = queen.getConflicts();
                }
            }
        }
        return maxQueen;
    }
    public Queen choosePositionWithMinConflicts(Queen queen){
        int minConflicts = queen.getConflicts();
        int bestX = 0;
        int currentY = queen.getY();
        int currentX = queen.getX();
        List<Queen> all_queens = board.getAll_queens();

        for(int x = 0; x < board.getSize(); x++){
            if(x != currentX) {
                //setting another position just to calculate the queen's conflicts on this position
                queen.setPosition(x, currentY);
                int conflicts = queen.calculateConflicts(all_queens);
                if (minConflicts >= conflicts) {
                    bestX = x;
                    minConflicts = conflicts;
                }
            }
        }
        queen.setPosition(bestX,currentY);
        //setting the conflicts of each queen to be able to see if it is solved
        return queen;
    }

}
