package nboard;

import Commons.Position;
import Commons.Strategy;

import java.util.ArrayList;
import java.util.List;




public class NStrategy implements Strategy {
    public enum Direction{
        UP,DOWN,LEFT,RIGHT,FIRST
    }
    private List<NBoard> boards;
    private int size;

    public NStrategy(NBoard board){
        this.boards = new ArrayList<>();
        boards.add(board);
        size = board.getSize();
     }
    @Override
    public boolean solve(){
        boolean result = false;
        int i = 0;
        int k = 1000*size;
        while(!result && i < k) {
            NBoard bestBoard = takeBoardWithBestSum();
            //System.out.println("--------------------");
            // bestBoard.visualize();
            int sum = bestBoard.getSumDistances();
            //System.out.println(sum);
            ++i;
            if (sum == 0) {
                bestBoard.visualize();
                System.out.printf("%d",i);
                return true;
            }
            else
                findNewMoves(bestBoard);
        }
        return false;
    }

    public void findNewMoves(NBoard board){
        Direction previousDirection = board.getBoardAchievedFromDirection();
        boards.remove(board);
        Position posOfZero = board.getPositionOfZero();
        if(moveUp(posOfZero) && previousDirection != Direction.DOWN){
            Position pos = new Position(posOfZero);
            pos.setRow(posOfZero.getRow() - 1);
            doTheSwapAndPushInList(pos,board,Direction.UP);
        }
        if(moveDown(posOfZero) && previousDirection != Direction.UP){
            Position pos = new Position(posOfZero);
            pos.setRow(posOfZero.getRow() + 1);
            doTheSwapAndPushInList(pos,board,Direction.DOWN);
        }
        if(moveLeft(posOfZero) && previousDirection != Direction.RIGHT){
            Position pos = new Position(posOfZero);
            pos.setColumn(posOfZero.getColumn() - 1);
            doTheSwapAndPushInList(pos,board,Direction.LEFT);
        }
        if(moveRight(posOfZero) && previousDirection != Direction.LEFT){
            Position pos = new Position(posOfZero);
            pos.setColumn(posOfZero.getColumn() + 1);
            doTheSwapAndPushInList(pos,board,Direction.RIGHT);
        }

    }

    public void doTheSwapAndPushInList(Position pos,NBoard board,Direction direction){
        NBoard other_board = new NBoard(board);
        other_board.swapPositionWithZero(pos);
        other_board.setBoard_achieved_from_direction(direction);
        boards.add(other_board);
    }

    public NBoard takeBoardWithBestSum(){
        int bestSum = Integer.MAX_VALUE;
        NBoard bestBoard = new NBoard(size);
        for(var board : boards){
            if(bestSum > board.getSumDistances()) {
                bestSum = board.getSumDistances();
                bestBoard = board;
            }
        }
        return bestBoard;
    }

    public boolean moveUp(Position posOfZero){
        return posOfZero.getRow() > 0;
    }
    public boolean moveDown(Position posOfZero){
        return posOfZero.getRow() < size - 1;
    }
    public boolean moveLeft(Position posOfZero){
        return posOfZero.getColumn() > 0;
    }
    public boolean moveRight(Position posOfZero){
        return posOfZero.getColumn() < size - 1;
    }

}
