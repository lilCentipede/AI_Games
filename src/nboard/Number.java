package nboard;

import Commons.Board;
import Commons.Position;

public class Number {
    private Position position;
    private int symbol;

    Number(Position position,int symb){
        this.position = position;
        assert(symb >= 0 && symb <= Math.pow(position.getSize_of_board(),2) - 1);
        this.symbol = symb;
    }
    Number(int symb){
        this.symbol = symb;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }
}
