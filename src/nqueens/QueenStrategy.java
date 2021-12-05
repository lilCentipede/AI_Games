package nqueens;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueenStrategy {
    private QueensBoard board;
    QueenStrategy(QueensBoard board){
        this.board = board;
    }

    public boolean solve(){
        int i = 0;
        int k = 2;
        Queen previous_queen = new Queen(board.getSize());
        while(!board.isSolved() ){
            List<Queen> maxQueens = queenMaxConflicts(previous_queen);
            board = bestOptionForBoard(maxQueens);
            //board.viewQueensPositions();
            ++i;
           // board.visualize();
            if( i > k*board.getSize()){
                return false;
            }
        }
        System.out.println("On " + i + "th try");
        board.visualize();
        return true;
    }

    public List<Queen> queenMaxConflicts(Queen previous_queen ){
        List<Queen> all_queens = board.getAll_queens();
        int max_conflicts = board.getMaxConflicts();
        List<Queen> maxQueens = new ArrayList<>();
        for(var queen : all_queens) {
            if(previous_queen != queen && queen.getConflicts() == max_conflicts){
                maxQueens.add(queen);
            }
        }
        return maxQueens;
    }

    public QueensBoard bestOptionForBoard(List<Queen> maxQueens){
        QueensBoard bestBoard = new QueensBoard(board.getSize());
        int minConflicts = Integer.MAX_VALUE;
        for(var queen : maxQueens){
            QueensBoard scenario_board = createScenarioBoardFor(queen);
            int sum = scenario_board.sumTheirConflicts();
            if(minConflicts > sum){
                minConflicts = sum;
                bestBoard = scenario_board;
            }
        }
        return bestBoard;
    }
    public QueensBoard createScenarioBoardFor(Queen queen){
        QueensBoard scenario_board = new QueensBoard(board);
        scenario_board.removeQueen(queen);
        List<Integer> goodRows =  positionsWithMinConflictsForChosenQueen(scenario_board,queen);
        int row = randomChooseRowWithMinConflicts(goodRows,queen);
        queen.setPosition(row, queen.getColumn());
        scenario_board.placeQueen(queen);
        return scenario_board;
    }


    public int randomChooseRowWithMinConflicts(List<Integer> rows_with_minimal_conflicts,Queen queen) {
        int luckyNumber = Common.getRandomNumberFromZeroTo(rows_with_minimal_conflicts.size());
        return rows_with_minimal_conflicts.get(luckyNumber);
    }
    public List<Integer> positionsWithMinConflictsForChosenQueen(QueensBoard scenario_board,Queen queen){
        HashMap<Integer,Integer> rows_conflicts = scenario_board.getConflictsOnEveryPositionFor(queen);
        List<Integer> rowWithMinConflicts= new ArrayList<>();
        int min_conflicts = scenario_board.getSize();
        for(var row : rows_conflicts.keySet()){
            if(min_conflicts > rows_conflicts.get(row)){
                min_conflicts = rows_conflicts.get(row);
            }
        }
        for(var row : rows_conflicts.keySet()){
            if(rows_conflicts.get(row) == min_conflicts){
                rowWithMinConflicts.add(row);
            }
        }
        return rowWithMinConflicts;

    }

}
