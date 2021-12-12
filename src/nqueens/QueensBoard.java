package nqueens;

import Commons.Board;
import Commons.Common;

import java.util.*;

//takes care of setting up the board
public class QueensBoard implements Board {
    private final int size;
    private final List<Queen> all_queens;
    private int sumConflicts;
    private final Character[][] board;
    public QueensBoard(int size){
        this.size = size;
        all_queens = new ArrayList<>(size);
        sumConflicts = 0;
        board = new Character[size][size];
        setupBoard();
        placeAllQueensForFirstTime();
        setTheirConflicts();
    }
    public QueensBoard(QueensBoard other_board){
        this.size = other_board.size;
        this.all_queens = new ArrayList<>(size);
        this.sumConflicts = other_board.sumConflicts;
        this.board = new Character[size][size];
        setupBoard();
        placeQueensForCopyConstructor(other_board);
        setTheirConflicts();

    }
    public void placeQueensForCopyConstructor(QueensBoard other_board){
        for(int i = 0;i < other_board.size ; i++){
            Queen queen = new Queen(other_board.all_queens.get(i));
            placeQueen(queen);
        }
    }
    List<Queen> getAll_queens(){
        return all_queens;
    }
    public int getSize(){return size;}
    int getSumConflicts(){return sumConflicts;}

    public boolean placeQueen(Queen new_queen){
        all_queens.add(new_queen);
        int row = new_queen.getRow();
        int column = new_queen.getColumn();
        board[row][column] = '@';
        return true;
    }
    public void removeQueen(Queen old_queen){
        all_queens.remove(old_queen);
        int row = old_queen.getRow();
        int column = old_queen.getColumn();
        board[row][column] = '*';
    }

    public void placeAllQueensForFirstTime(){
        int number_of_queens = 0;
        while(number_of_queens < size) {
            int row = Common.getRandomNumberFromZeroTo(size);
            int col = number_of_queens;
            if(placeQueen(new Queen(row, col, size))) {
                number_of_queens++;
            }
        }
    }
    public void setupBoard(){
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                board[i][j] = '*';
            }
        }
    }
    public void setTheirConflicts(){
        for(Queen queen : all_queens){
            queen.setConflicts(all_queens);
        }
    }
    public int sumTheirConflicts(){
        setTheirConflicts();
        sumConflicts = 0;
        for(Queen queen : all_queens){
            sumConflicts += queen.getConflicts();
        }
        return sumConflicts;
    }
    public void viewQueensPositions(){
        for(Queen queen : all_queens){
            System.out.print("[" + queen.getRow() + "," + queen.getColumn() + "]  ");
        }
        System.out.println();
    }

    public void visualize(){
        for(var column : board){
            for(var pos : column){
                System.out.printf("%c ",pos);
            }
            System.out.println();
        }
    }
    public boolean isSolved(){
        for(Queen queen : all_queens){
            int conflicts = queen.getConflicts();
            if(conflicts != 0){
                return false;
            }
        }
        return true;
    }
    public HashMap<Integer,Integer> getConflictsOnEveryPositionFor(Queen queen){
        HashMap<Integer,Integer> row_conflicts = new HashMap<>();
        int current_row = queen.getRow();
        for(int row = 0; row < size; row++){
            if(row != current_row){
                queen.setPosition(row,queen.getColumn());
                int conflictsOnThisPosition = queen.calculateConflicts(all_queens);
                row_conflicts.put(row,conflictsOnThisPosition);
            }
        }
        return row_conflicts;
    }
    public int getMaxConflicts(){
        int minconflicts = -1;
        for(Queen queen : all_queens){
            if(minconflicts < queen.getConflicts()){
                minconflicts = queen.getConflicts();
            }
        }
        return minconflicts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueensBoard that = (QueensBoard) o;
        return size == that.size && Objects.equals(all_queens, that.all_queens) && Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, all_queens);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }
}
