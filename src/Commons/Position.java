package Commons;

import java.util.Objects;

public class Position{
    int row;
    int column;
    final int size_of_board;

    public int getSize_of_board() {
        return size_of_board;
    }

    public Position(int size_of_board){
        this.size_of_board = size_of_board;
        this.row = 0;
        this.column =0;
    }

    public Position(int size_of_board,int row, int column){
        this.size_of_board = size_of_board;
        this.row = row;
        this.column = column;
    }

    public Position(Position other_position){
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

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public Position setPosition(int row, int column){
        this.row = row;
        this.column = column;
        return this;
    }
}
