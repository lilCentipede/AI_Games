import java.util.ArrayList;
import java.util.List;

//takes care of setting up the board
public class QueensBoard {
    private final int size;
    private final List<Queen> all_queens;
    private final Character[][] board;
    public QueensBoard(int size){
        this.size = size;
        all_queens = new ArrayList<>(size);
        board = new Character[size][size];
        setupBoard();
        placeAllQueensForFirstTime();
        setTheirConflicts();
    }
    List<Queen> getAll_queens(){
        return all_queens;
    }
    int getSize(){return size;}

    public boolean placeQueen(Queen new_queen){
        all_queens.add(new_queen);
        int x = new_queen.getX();
        int y = new_queen.getY();
        board[x][y] = '@';
        return true;
    }
    public boolean removeQueen(Queen old_queen){
        all_queens.remove(old_queen);
        int x = old_queen.getX();
        int y = old_queen.getY();
        board[x][y] = '*';
        return true;
    }
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    private int getRandomNumberFromZeroTo(int max){
        return getRandomNumber(0,max);
    }
    public void placeAllQueensForFirstTime(){
        int number_of_queens = 0;
        while(number_of_queens < size) {
            int x = getRandomNumberFromZeroTo(size);
            int y = number_of_queens;
            if(placeQueen(new Queen(x, y, size))) {
                number_of_queens++;
            }
        }
    }
    private void setupBoard(){
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

}
