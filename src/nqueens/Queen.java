package nqueens;

import Commons.Position;

import java.util.List;
import java.util.Objects;

public class Queen {
    private Position position;
    private int conflicts;

    Queen(int row, int column, int size){
        this.position = new Position(size).setPosition(row,column);
        this.conflicts = 0;
    }
    Queen(int size){
        this.position = new Position(size).setPosition(-1,-1);
        this.conflicts = 0;
    }
    Queen(Queen other_queen){
        this.position = new Position(other_queen.position);
        this.conflicts = other_queen.getConflicts();
    }
    Position getPosition(){return position;}
    void setPosition(int row,int column){
        position.setPosition(row,column);
    }

    int getRow(){return position.getRow();}
    int getColumn(){return position.getColumn();}

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
        return conflicts;
    }

    boolean hasConflictWith(Queen other_queen){
        return (this.position.getRow() == other_queen.position.getRow() ||
                this.position.getColumn() == other_queen.position.getColumn() ||
                Math.abs(this.position.getRow() - other_queen.position.getRow()) == Math.abs(this.position.getColumn() - other_queen.position.getColumn()));
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
