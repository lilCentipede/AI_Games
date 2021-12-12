package tests;

import nqueens.QueensBoard;
import nqueens.QueensSimulation;
import org.junit.jupiter.api.Assertions;

class QueensSimulationTest {

    @org.junit.jupiter.api.Test
    void simulate6() {
            Assertions.assertTrue(QueensSimulation.simulate(6));
    }
    @org.junit.jupiter.api.Test
    void simulate100() {
            Assertions.assertTrue(QueensSimulation.simulate(100));
    }
    @org.junit.jupiter.api.Test
    void simulate500() {
            Assertions.assertTrue(QueensSimulation.simulate(500));

    }
    @org.junit.jupiter.api.Test
    void simulate1000() {
        Assertions.assertTrue(QueensSimulation.simulate(1000));

    }


    @org.junit.jupiter.api.Test
    void AreBoardAndItsCopyEqual() {
        QueensBoard board1 = new QueensBoard(4);
        QueensBoard board2 = new QueensBoard(board1);
        Assertions.assertEquals(board1,board2);
    }
}