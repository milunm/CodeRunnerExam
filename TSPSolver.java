import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TSPSolver {
    // Q1 
    public static int isValidMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0; // return 0 if matrix is null or empty
        int cities = matrix.length; // store number of cities

        for (int i = 0; i < cities; i++) {
            if (matrix[i].length != cities) return 0; // checks if matrix is square

            for (int j = 0; j < cities; j++) {
                int temp = matrix[i][j]; // temporary store current distance being checked

                if (temp < 0) return 0; // ensure there are no negative distances
                if (i == j && temp != 0) return 0; // ensure diagonal distance is 0
                if (temp != matrix[j][i]) return 0; // ensure matrix is symmetric
            }
        }
        return 1; // if all checks pass, matrix is valid
    }

    // Q2
    public static List<Integer> generateInitialTour(int cities) { // return a random tour as a List of Integer
        List<Integer> randomTour = new ArrayList<>(); // empty list to store the tour
        for (int cityIndex = 0; cityIndex < cities; cityIndex++) {
            randomTour.add(cityIndex);
        }
        Collections.shuffle(randomTour); // randomize the order
        return randomTour;
    }

    // Q3
    public static int isValidTour(List<Integer> tour, int[][] matrix) {
        if (tour == null) return 0; // null check
        if (matrix == null) return 0;
        if (tour.size() != matrix.length) return 0; // size match

        Set<Integer> checkDuplicate = new HashSet<>(tour);
        if (checkDuplicate.size() != matrix.length) return 0; // duplicate check

        return 1; // valid
    }

    // Q4
    public static int calculateFitness(List<Integer> tour, int[][] matrix) {
        if (tour == null) return Integer.MAX_VALUE;
        if (matrix == null) return Integer.MAX_VALUE;
        if (tour.size() != matrix.length) return Integer.MAX_VALUE;

        int tourDistance = 0;
        int tourSize = tour.size();
        if (tourSize < 2) return 0; // no distance

        for (int i = 0; i < tourSize - 1; i++) {
            tourDistance += matrix[tour.get(i)][tour.get(i + 1)];
        }
        tourDistance += matrix[tour.get(tourSize - 1)][tour.get(0)]; // return to start
        return tourDistance;
    }

    // Q5
    public static List<Integer> smallChange(List<Integer> tour) {
        if (tour == null) return null;
        if (tour.size() < 2) return tour;

        List<Integer> newTour = new ArrayList<>(tour); // copy
        int firstIndex = (int) (Math.random() * newTour.size());
        int secondIndex = (int) (Math.random() * newTour.size());
        while (firstIndex == secondIndex) {
            firstIndex = (int) (Math.random() * newTour.size());
            secondIndex = (int) (Math.random() * newTour.size());
        }
        Collections.swap(newTour, firstIndex, secondIndex);
        return newTour;
    }

    // Q6: SIMULATED ANNEALING
    public static List<Integer> solveTSP_SA(int numCities, int[][] matrix, int iterations) {
        if (numCities <= 1) return null;
        if (iterations < 1) return null;
        if (isValidMatrix(matrix) == 0) return null;

        // simulated annealing
        List<Integer> currentTour = generateInitialTour(numCities);
        int currentFitness = calculateFitness(currentTour, matrix);

        double initialTemp = 1000; // starting temperature
        double rateOfCooling = 0.98; // cooling rate
        for (int i = 0; i < iterations; i++) {
            List<Integer> newTour = smallChange(currentTour);
            int newFitness = calculateFitness(newTour, matrix);
            int fitnessChange = newFitness - currentFitness;

            double progress = (double) i / iterations; //track percentage of iterations complete
            double coolingCompleteThreshold = 0.75; //threshold to change when cooling can be considered complete
            boolean coolingComplete = progress > coolingCompleteThreshold; //after 75% of iterations, cooling is complete and the algorithm should stop accepting worse solutions 

            double temperature = initialTemp * Math.pow(rateOfCooling, i);
            if (fitnessChange < 0 || !coolingComplete & Math.exp(-fitnessChange / temperature) > Math.random()) {
                currentTour = newTour;
                currentFitness = newFitness;
            }
        }
        return currentTour;
    }
}
