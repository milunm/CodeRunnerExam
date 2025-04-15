import java.util.*;

public class TSPTest {
    public static void main(String[] args) {
        int n = 5;
        double[][] matrix = {
            {0, 2, 9, 10, 7},
            {2, 0, 6, 4, 3},
            {9, 6, 0, 8, 5},
            {10, 4, 8, 0, 6},
            {7, 3, 5, 6, 0}
        };

        ArrayList<Integer> result = TSPHeuristicSolver.solveTSP(n, matrix, 1000);

        System.out.println("Best Tour Found: " + result);
        System.out.println("Tour Distance: " + TSPHeuristicSolver.calculateFitness(result, matrix));
    }
}
