package nqueens;

public class QueensSimulation {
    public QueensBoard board;
    public QueenStrategy strategy;

    public static boolean simulate(int board_size) {
        boolean result = false;
        int j = 0;
        while (!result) {
           QueensBoard board = new QueensBoard(board_size);
            ++j;
           //board.visualize();
            QueenStrategy strategy = new QueenStrategy(board);
            result = strategy.solve();
        }
        System.out.println("Random Restarts: " + j);
        return result;
    }
}
