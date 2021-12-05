package tests;

import nqueens.QueensBoard;
import nqueens.QueensSimulation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.*;

class QueensSimulationTest {

    @org.junit.jupiter.api.Test
    void simulate100() {
        for(int i = 0; i < 100; i++) {
            Assertions.assertTrue(QueensSimulation.simulate(60));
        }
    }
    @org.junit.jupiter.api.Test
    void simulate1() {
            Assertions.assertTrue(QueensSimulation.simulate(6));
    }
    @org.junit.jupiter.api.Test
    void simulate() {
            Assertions.assertTrue(QueensSimulation.simulate(400));

    }

    @org.junit.jupiter.api.Test
    void AreBoardAndItsCopyEqual() {
        QueensBoard board1 = new QueensBoard(4);
        QueensBoard board2 = new QueensBoard(board1);
        Assertions.assertEquals(board1,board2);
    }
}