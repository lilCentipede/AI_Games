package nqueens;

import Commons.Common;
import Commons.Strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueenStrategy implements Strategy {
    private QueensBoard board;
    QueenStrategy(QueensBoard board){
        this.board = board;
    }

    @Override
    public boolean solve(){
        int i = 0;
        double k = 2;
        Queen previous_queen = new Queen(board.getSize());
        while(!board.isSolved() ){
            List<Queen> maxQueens = queenMaxConflicts(previous_queen);
            board = bestOptionForBoard(maxQueens);
            //board.viewQueensPositions();
            ++i;
           // System.out.println("----------------------------------------");
           // board.visualize();
           // System.out.println("----------------------------------------");
            if( i > k*board.getSize()){
                return false;
            }
        }
        System.out.println("On " + i + "th try");
        //board.visualize();
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
        List<QueensBoard> allBoards = new ArrayList<>();
        List<QueensBoard> bestBoards = new ArrayList<>();
        int minConflicts = Integer.MAX_VALUE;
        for(var queen : maxQueens){
            QueensBoard scenario_board = createScenarioBoardFor(queen);
             int sum = scenario_board.sumTheirConflicts();
             allBoards.add(scenario_board);
            if(minConflicts > sum){
                minConflicts = sum;
            }
        }
        for(var board : allBoards){
            int sum = board.getSumConflicts();
            if(minConflicts == sum){
                bestBoards.add(board);
            }
        }
        int luckyNumber = Common.getRandomNumberFromZeroTo(bestBoards.size());
        return bestBoards.get(luckyNumber);
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
        int min_conflicts = Integer.MAX_VALUE;
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
