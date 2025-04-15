import java.util.*;

public class TSPHeuristicSolver {

    // Q1: Check if the matrix is valid
    public static int isValidMatrix(double[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            if (matrix[i].length != n) return 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < 0) return 0;
                if (i == j && matrix[i][j] != 0) return 0;
                if (matrix[i][j] != matrix[j][i]) return 0;
            }
        }
        return 1;
    }

    // Q2: Generate initial random tour
    public static ArrayList<Integer> generateInitialTour(int n) {
        ArrayList<Integer> tour = new ArrayList<>();
        for (int i = 0; i < n; i++) tour.add(i);
        Collections.shuffle(tour);
        return tour;
    }

    // Q3: Check if tour is valid and complete
    public static int isValidTour(ArrayList<Integer> tour, double[][] matrix) {
        if (tour == null || matrix == null || tour.size() != matrix.length) return 0;

        Set<Integer> seen = new HashSet<>(tour);
        if (seen.size() != matrix.length) return 0;

        return 1;
    }

    // Q4: Calculate fitness (total distance)
    public static double calculateFitness(ArrayList<Integer> tour, double[][] matrix) {
        if (tour == null || matrix == null || tour.size() != matrix.length) return Double.MAX_VALUE;

        double total = 0;
        int n = tour.size();
        for (int i = 0; i < n - 1; i++) {
            total += matrix[tour.get(i)][tour.get(i + 1)];
        }
        total += matrix[tour.get(n - 1)][tour.get(0)]; // return to start
        return total;
    }

    // Q5: Make small change (swap 2 cities)
    public static ArrayList<Integer> smallChange(ArrayList<Integer> tour) {
        ArrayList<Integer> newTour = new ArrayList<>(tour);
        int i = (int)(Math.random() * newTour.size());
        int j = (int)(Math.random() * newTour.size());
        while (i == j) j = (int)(Math.random() * newTour.size());
        Collections.swap(newTour, i, j);
        return newTour;
    }

    // Q6: Heuristic algorithm (Hill Climbing)
    public static ArrayList<Integer> solveTSP(int numCities, double[][] matrix, int iterations) {
        if (numCities <= 1 || isValidMatrix(matrix) == 0 || iterations < 1) return null;

        ArrayList<Integer> currentTour = generateInitialTour(numCities);
        double currentFitness = calculateFitness(currentTour, matrix);

        for (int i = 0; i < iterations; i++) {
            ArrayList<Integer> newTour = smallChange(currentTour);
            double newFitness = calculateFitness(newTour, matrix);

            if (newFitness < currentFitness) {
                currentTour = newTour;
                currentFitness = newFitness;
            }
        }
        return currentTour;
    }
}
