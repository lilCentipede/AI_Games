import java.util.List;
import java.util.Objects;

public class Queen {
    private Position position;
    private int conflicts;
    private static class Position{
        int x;
        int y;
        final int size_of_board;
        Position(int size_of_board){
            this.size_of_board = size_of_board;
            this.x =0;
            this.y =0;
        }
        Position setPosition(int x,int y){
            this.x = x;
            this.y = y;
            return this;
        }
        int getX(){return x;}
        int getY(){return y;}
    }
    Queen(int x, int y, int size){
        this.position = new Position(size).setPosition(x,y);
        this.conflicts = 0;
    }
    Position getPosition(){return position;}
    void setPosition(int x,int y){
        position.setPosition(x,y);
    }

    int getX(){return position.x;}
    int getY(){return position.y;}

    int getConflicts(){return conflicts;}
    void setConflicts(List<Queen> all_queens){
        this.conflicts = calculateConflicts(all_queens);
    }
    void setConflicts(int conflicts){
        this.conflicts = conflicts;
    }
    int calculateConflicts(List<Queen> all_queens){
        int conflicts = 0;
        for(var queen : all_queens){
            if(this != queen && hasConflictWith(queen)){
                conflicts++;
            }
        }
        assert(conflicts < all_queens.size());
        return conflicts;
    }

    boolean hasConflictWith(Queen other_queen){
        return (this.position.x == other_queen.position.x ||
                this.position.y == other_queen.position.y ||
                Math.abs(this.position.x - other_queen.position.x) == Math.abs(this.position.y - other_queen.position.y));
    }
    boolean onHerPositionIs(Queen other_queen){
        return (this != other_queen &&
                this.position.x == other_queen.position.x &&
                this.position.y == other_queen.position.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queen queen = (Queen) o;
        return conflicts == queen.conflicts && Objects.equals(position, queen.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, conflicts);
    }

}
