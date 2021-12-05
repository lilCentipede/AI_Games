import nqueens.QueensSimulation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        //System.out.println(scanner);
        while(scanner.hasNextInt()) {
            QueensSimulation.simulate(scanner.nextInt());
        }
        scanner.close();
    }
}
