import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        //System.out.println(scanner);
        while(scanner.hasNextInt()) {
            QueensBoard board = new QueensBoard(scanner.nextInt());
            board.visualize();
            QueenStrategy strategy = new QueenStrategy(board);
            strategy.solve();
        }
        scanner.close();
    }
}
