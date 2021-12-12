package nboard;

import Commons.Board;
import Commons.Position;
import Commons.Strategy;

import java.util.*;

public class NBoard implements Board {
    private final int size;
    private int sumDistances;
    private Integer[][] board;
    private Position positionOfZero;
    public HashMap<Integer,Position> goal_board = new HashMap<>();
    private NStrategy.Direction board_achieved_from_direction;


    public NBoard(int size){
        this.size = size;
        this.positionOfZero = new Position(size,-1,-1);
        board = new Integer[size][size];
        goal_board = new HashMap<>();
        setupBoard();
        setGoalBoard();
        this.sumDistances  = calculateSumDistances();
        board_achieved_from_direction = NStrategy.Direction.FIRST;
    }
    public NBoard(NBoard other_board){
        this.size = other_board.getSize();
        this.sumDistances = other_board.sumDistances;
        this.positionOfZero = new Position(other_board.positionOfZero);
        this.board = new Integer[size][size];
        setUpBoardForCopyConstructor(other_board);
        this.goal_board = other_board.goal_board;
        this.board_achieved_from_direction = other_board.board_achieved_from_direction;
    }

    private void setUpBoardForCopyConstructor(NBoard other_board){
        for(int i = 0;i < size ; i++)
            for(int j = 0 ; j < size ; j++){
                this.board[i][j] = other_board.getBoard()[i][j];
        }
    }

    private void setGoalBoard(){
        int total_numbers = (int)Math.pow(size,2);
        int row = -1;
        int column;
        for(int i = 0; i < total_numbers;i++){
            column = i % size;
            if(column == 0){
                row++;
            }
            goal_board.put(i,new Position(size,row,column));
        }
    }

    private int manhattanDistance(Position p1, Position p2){
        return Math.abs(p1.getRow() - p2.getRow()) + Math.abs(p1.getColumn() - p2.getColumn());
    }

    public int calculateSumDistances(){
        int sumDistances = 0;
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
               int number = board[i][j];
               int distance = calculateDistanceFor(number,new Position(size,i,j));
               sumDistances += distance;
            }
        }
        return sumDistances;
    }
    public int calculateDistanceFor(int number, Position pos){
        return manhattanDistance(pos,goal_board.get(number));
    }

    private Stack<Integer> createNumbers(){
        int total_numbers = (int)Math.pow(size,2);
        Stack<Integer> all_numbers = new Stack<>();
        for(int i =0 ;i < total_numbers ;i++){
            all_numbers.push(i);
        }
        return all_numbers;
    }

    public void swapPositionWithZero(Position position){
        int rowZero = positionOfZero.getRow();
        int columnZero = positionOfZero.getColumn();
        int rowOther = position.getRow();
        int columnOther= position.getColumn();
        int number_of_posOfZero = 0;
        board[rowZero][columnZero] = board[rowOther][columnOther];
        board[rowOther][columnOther] = number_of_posOfZero;
        this.sumDistances = this.calculateSumDistances();
        this.positionOfZero.setPosition(rowOther,columnOther);
    }

    public void setupBoardWithTestBoard(Integer[][] other_board){
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                board[i][j] = other_board[i][j];
                if(board[i][j] == 0){
                    positionOfZero.setPosition(i,j);
                }
            }
        }
    }
    @Override
    public void setupBoard(){
        Stack<Integer> all_numbers = createNumbers();
        Collections.shuffle(all_numbers);
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                board[i][j] = all_numbers.pop();
                if(board[i][j] == 0){
                    positionOfZero.setPosition(i,j);
                }
            }
        }
    }

    @Override
    public void visualize(){
        for(var column : board){
            for(var pos : column){
                System.out.printf("%d ",pos);
            }
            System.out.println();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NBoard nBoard = (NBoard) o;
        return size == nBoard.size && sumDistances == nBoard.sumDistances && Arrays.deepEquals(board, nBoard.board) && Objects.equals(positionOfZero, nBoard.positionOfZero) && Objects.equals(goal_board, nBoard.goal_board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, sumDistances, positionOfZero, goal_board);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }

    @Override
    public int getSize(){
        return size;
    }

    public int getSumDistances() {
        return sumDistances;
    }

    public Integer[][] getBoard() {
        return board;
    }

    public HashMap<Integer, Position> getGoal_board() {
        return goal_board;
    }

    public Position getPositionOfZero() {
        return positionOfZero;
    }

    public void setPositionOfZero(Position positionOfZero) {
        this.positionOfZero = positionOfZero;
    }

    public NStrategy.Direction getBoardAchievedFromDirection() {
        return board_achieved_from_direction;
    }

    public void setBoard_achieved_from_direction(NStrategy.Direction board_achieved_from_direction) {
        this.board_achieved_from_direction = board_achieved_from_direction;
    }
}
