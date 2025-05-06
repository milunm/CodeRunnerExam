import java.util.Collections;
import java.util.LinkedList;
import java.util.HashSet;

public class LinkedListAlternatives {

    // Q1 - LinkedList-based matrix validation not applicable directly.

    // Q2 - generateInitialTour using LinkedList
    public static LinkedList<Integer> generateInitialTour_LL(int cities) {
        if (cities <= 0) return null;
        if (cities > 1000000) return null;
        LinkedList<Integer> tour = new LinkedList<>();
        for (int i = 0; i < cities; i++) tour.add(i);
        Collections.shuffle(tour);
        return tour;
    }

    // Q3 - isValidTour using LinkedList
    public static int isValidTour_LL(LinkedList<Integer> tour, double[][] matrix) {
        if (tour == null) return 0;
        if (matrix == null) return 0;
        if (tour.size() != matrix.length) return 0;

        HashSet<Integer> unique = new HashSet<>(tour);
        if (unique.size() != matrix.length) return 0;

        return 1;
    }

    // Q4 - calculateFitness using LinkedList
    public static double calculateFitness_LL(LinkedList<Integer> tour, double[][] matrix) {
        if (tour == null) return Double.MAX_VALUE;
        if (matrix == null) return Double.MAX_VALUE;
        if (tour.size() != matrix.length) return Double.MAX_VALUE;

        double dist = 0;
        int size = tour.size();
        if (size < 2) return 0;
        for (int i = 0; i < size - 1; i++) {
            dist += matrix[tour.get(i)][tour.get(i + 1)];
        }
        dist += matrix[tour.get(size - 1)][tour.get(0)];
        return dist;
    }

    // Q5 - smallChange using LinkedList
    public static LinkedList<Integer> smallChange_LL(LinkedList<Integer> tour) {
        if (tour == null) return null;
        if (tour.size() < 2) return tour;

        LinkedList<Integer> newTour = new LinkedList<>(tour);
        int a = (int)(Math.random() * newTour.size());
        int b = (int)(Math.random() * newTour.size());
        while (a == b) b = (int)(Math.random() * newTour.size());

        Collections.swap(newTour, a, b);
        return newTour;
    }

    // Q6 - Simulated Annealing using LinkedList
    public static LinkedList<Integer> solveTSP_SA_LL(int numCities, double[][] matrix, int iterations){
        if (numCities <= 1) return null;
        if (iterations < 1) return null;
        if (matrix == null || isValidMatrix(matrix) == 0) return null;

        LinkedList<Integer> currentTour = generateInitialTour_LL(numCities);
        double currentFitness = calculateFitness_LL(currentTour, matrix);

        double initialTemp = 1000;
        double rateOfCooling = 0.98;
        for (int i = 0; i < iterations; i++){
            LinkedList<Integer> newTour = smallChange_LL(currentTour);
            double newFitness = calculateFitness_LL(newTour, matrix);
            double fitnessChange = newFitness - currentFitness;

            double progress = (double) i / iterations;
            boolean coolingComplete = progress > 0.75;
            double temperature = initialTemp * Math.pow(rateOfCooling, i);

            if (fitnessChange < 0 || !coolingComplete && Math.exp(-fitnessChange / temperature) > Math.random()){
                currentTour = newTour;
                currentFitness = newFitness;
            }
        }

        return currentTour;
    }

    // Utility matrix check
    public static int isValidMatrix(double[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != matrix.length) return 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] < 0 || (i == j && matrix[i][j] != 0) || matrix[i][j] != matrix[j][i]) return 0;
            }
        }
        return 1;
    }
}
