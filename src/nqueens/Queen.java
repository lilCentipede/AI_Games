package nqueens;

import java.util.List;
import java.util.Objects;

public class Queen {
    private Position position;
    private int conflicts;
    private static class Position{
        int row;
        int column;
        final int size_of_board;
        Position(int size_of_board){
            this.size_of_board = size_of_board;
            this.row = 0;
            this.column =0;
        }
        Position(Position other_position){
            this.size_of_board = other_position.size_of_board;
            this.column = other_position.column;
            this.row = other_position.row;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row && column == position.column && size_of_board == position.size_of_board;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column, size_of_board);
        }

        @Override
        public String toString() {
            return "Position{" +
                    "row=" + row +
                    ", column=" + column +
                    '}';
        }

        Position setPosition(int row, int column){
            this.row = row;
            this.column = column;
            return this;
        }
    }
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

    int getRow(){return position.row;}
    int getColumn(){return position.column;}

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
        return (this.position.row == other_queen.position.row ||
                this.position.column == other_queen.position.column ||
                Math.abs(this.position.row - other_queen.position.row) == Math.abs(this.position.column - other_queen.position.column));
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
