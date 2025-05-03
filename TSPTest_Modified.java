
import java.util.*;
import java.io.*;

public class TSPTest_Modified {
    public static void main(String[] args) {
        int numCities = 5;
        int runsPerMethod = 10;
        ArrayList<double[][]> matrices = getPredefinedMatrices();

        try {
            PrintWriter writer = new PrintWriter(new File("tsp_results.csv"));
            writer.println("Matrix,Iterations,Method,BestDistance,AverageDistance,BestTour");

            for (int m = 0; m < matrices.size(); m++) {
                double[][] matrix = matrices.get(m);

                for (int iter = 100; iter <= 1000; iter += 100) {
                    runMethod("HC", writer, m + 1, iter, numCities, matrix);
                    runMethod("SA", writer, m + 1, iter, numCities, matrix);
                    runMethod("Hybrid", writer, m + 1, iter, numCities, matrix);
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runMethod(String method, PrintWriter writer, int matrixNum, int iter, int numCities, double[][] matrix) {
        double total = 0;
        double best = Double.MAX_VALUE;
        ArrayList<Integer> bestTour = null;

        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> tour;
            if (method.equals("HC")) {
                tour = TSPHeuristicSolver.solveTSP_HC(numCities, matrix, iter);
            } else if (method.equals("SA")) {
                tour = TSPHeuristicSolver.solveTSP_SA(numCities, matrix, iter);
            } else {
                tour = TSPHeuristicSolver.solveTSP_hybrid(numCities, matrix, iter);
            }
            double fitness = TSPHeuristicSolver.calculateFitness(tour, matrix);
            total += fitness;
            if (fitness < best) {
                best = fitness;
                bestTour = tour;
            }
        }

        double average = total / 10.0;
        writer.printf("Matrix %d,%d,%s,%.1f,%.2f,%s\n", matrixNum, iter, method, best, average, bestTour.toString());
    }

    public static ArrayList<double[][]> getPredefinedMatrices() {
        ArrayList<double[][]> matrices = new ArrayList<>();

        matrices.add(new double[][] { {0, 2, 9, 10, 7}, {2, 0, 6, 4, 3}, {9, 6, 0, 8, 5}, {10, 4, 8, 0, 6}, {7, 3, 5, 6, 0} });
        matrices.add(new double[][] { {0, 3, 8, 4, 6}, {3, 0, 7, 5, 2}, {8, 7, 0, 6, 3}, {4, 5, 6, 0, 5}, {6, 2, 3, 5, 0} });
        matrices.add(new double[][] { {0, 1, 2, 3, 4}, {1, 0, 5, 6, 7}, {2, 5, 0, 8, 9}, {3, 6, 8, 0, 10}, {4, 7, 9, 10, 0} });
        matrices.add(new double[][] { {0, 9, 4, 8, 7}, {9, 0, 2, 3, 5}, {4, 2, 0, 6, 1}, {8, 3, 6, 0, 2}, {7, 5, 1, 2, 0} });
        matrices.add(new double[][] { {0, 6, 7, 3, 4}, {6, 0, 8, 5, 7}, {7, 8, 0, 9, 6}, {3, 5, 9, 0, 2}, {4, 7, 6, 2, 0} });
        matrices.add(new double[][] { {0, 4, 1, 9, 3}, {4, 0, 5, 2, 6}, {1, 5, 0, 7, 8}, {9, 2, 7, 0, 4}, {3, 6, 8, 4, 0} });
        matrices.add(new double[][] { {0, 2, 3, 4, 5}, {2, 0, 6, 7, 8}, {3, 6, 0, 9, 1}, {4, 7, 9, 0, 2}, {5, 8, 1, 2, 0} });
        matrices.add(new double[][] { {0, 5, 3, 9, 1}, {5, 0, 2, 4, 6}, {3, 2, 0, 7, 8}, {9, 4, 7, 0, 3}, {1, 6, 8, 3, 0} });
        matrices.add(new double[][] { {0, 2, 3, 6, 7}, {2, 0, 1, 5, 4}, {3, 1, 0, 8, 9}, {6, 5, 8, 0, 2}, {7, 4, 9, 2, 0} });
        matrices.add(new double[][] { {0, 4, 6, 1, 8}, {4, 0, 3, 7, 2}, {6, 3, 0, 5, 9}, {1, 7, 5, 0, 3}, {8, 2, 9, 3, 0} });

        return matrices;
    }
}
