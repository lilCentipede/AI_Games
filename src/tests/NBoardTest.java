package tests;

import Commons.Position;
import nboard.NBoard;
import nboard.NStrategy;
import nqueens.QueensSimulation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBoardTest {

    @org.junit.jupiter.api.Test
    void equalBoards(){
        NBoard board = new NBoard(3);
        NBoard board2 = new NBoard(board);
        Assertions.assertEquals(board,board2);
    }

    @org.junit.jupiter.api.Test
    void viewNBoard() {
       NBoard board = new NBoard(3);
       board.visualize();
       assertTrue(true);
    }

    @org.junit.jupiter.api.Test
    void viewSumDistancesAndPosOfZero() {
        NBoard board = new NBoard(3);
        board.visualize();
        int distance = board.calculateSumDistances();
        System.out.println(distance);
        System.out.println(board.getPositionOfZero());
        assertTrue(true);
    }

    void solve(NBoard board,Integer[][] settup){
        board.setupBoardWithTestBoard(settup);
        board.visualize();
        System.out.println("-----------------");
        NStrategy nstrategy = new NStrategy(board);
        assertTrue(nstrategy.solve());
    }

    @Test
    void solveA(){
        NBoard board = new NBoard(3);
        Integer[][] A_yes = { //503 moves
                {0, 3, 5},
                {4, 2, 8},
                {1, 7, 6}};
        solve(board,A_yes);
    }
    @Test
    void solveB(){
        NBoard board = new NBoard(3);
        Integer[][] B_yes = { //352 moves
                {8, 6, 7},
                {2, 5, 4},
                {3, 0, 1}};
        solve(board,B_yes);
    }
    @Test
    void solveC(){
        NBoard board = new NBoard(3);
        Integer[][] C_yes = { //393 moves
                {6, 4, 7},
                {8, 5, 0},
                {3, 2, 1}};
        solve(board,C_yes);
    }
    @Test
    void solveD(){
        NBoard board = new NBoard(3);
        Integer[][] D_yes = { //695 moves
                {2, 3, 6},
                {1, 5, 8},
                {4, 7, 0}};
        solve(board,D_yes);
    }
    @Test
    void solveE(){
        NBoard board = new NBoard(3);
        Integer[][] E_no = {
                {6, 5, 3},
                {2, 4, 8},
                {7, 0, 1}};
        solve(board,E_no);
    }

}