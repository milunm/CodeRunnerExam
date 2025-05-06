import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class PotentialAlternativesQuestion6 {

    // Alternative 1: Use int[][] instead of double[][]
    public static ArrayList<Integer> solveTSP_SA_INT(int numCities, int[][] matrix, int iterations){
        if (numCities <= 1) return null;
        if (iterations < 1) return null;
        if (matrix == null || matrix.length != numCities) return null;

        ArrayList<Integer> currentTour = generateInitialTour(numCities);
        double currentFitness = calculateFitness(currentTour, convertToDouble(matrix));

        double initialTemp = 1000;
        double rateOfCooling = 0.98;
        for (int i = 0; i < iterations; i++){
            ArrayList<Integer> newTour = smallChange(currentTour);
            double newFitness = calculateFitness(newTour, convertToDouble(matrix));
            double fitnessChange = newFitness - currentFitness;

            double progress = (double) i / iterations;
            double coolingCompleteThreshold = 0.75;
            boolean coolingComplete = progress > coolingCompleteThreshold;

            double temperature = initialTemp * Math.pow(rateOfCooling, i);

            if (fitnessChange < 0 || !coolingComplete && Math.exp(-fitnessChange / temperature) > Math.random()){
                currentTour = newTour;
                currentFitness = newFitness;
            }
        }

        return currentTour;
    }

    // Alternative 2: Return tour as String
    public static String solveTSP_SA_String(int numCities, double[][] matrix, int iterations){
        ArrayList<Integer> tour = solveTSP_SA(numCities, matrix, iterations);
        if (tour == null) return null;
        return tour.toString().replaceAll("[\\[\\] ]", ""); // e.g., "0,1,2,3"
    }

    // Alternative 3: Accept matrix as ArrayList of ArrayLists
    public static ArrayList<Integer> solveTSP_SA_List(int numCities, ArrayList<ArrayList<Double>> matrix, int iterations){
        if (numCities <= 1) return null;
        if (iterations < 1) return null;
        if (matrix == null || matrix.size() != numCities) return null;

        double[][] converted = new double[numCities][numCities];
        for (int i = 0; i < numCities; i++){
            for (int j = 0; j < numCities; j++){
                converted[i][j] = matrix.get(i).get(j);
            }
        }
        return solveTSP_SA(numCities, converted, iterations);
    }

    // Alternative 4: Return type as LinkedList
    public static LinkedList<Integer> solveTSP_SA_LinkedList(int numCities, double[][] matrix, int iterations) {
        ArrayList<Integer> result = solveTSP_SA(numCities, matrix, iterations);
        return result == null ? null : new LinkedList<>(result);
    }

    // Helper: Convert int[][] to double[][]
    public static double[][] convertToDouble(int[][] matrix) {
        int n = matrix.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = matrix[i][j];
        return result;
    }

    // You can copy your actual SA implementation here for this file to work standalone
    public static ArrayList<Integer> solveTSP_SA(int numCities, double[][] matrix, int iterations) {
        if (numCities <= 1) return null;
        if (iterations < 1) return null;
        if (matrix == null || isValidMatrix(matrix) == 0) return null;

        ArrayList<Integer> currentTour = generateInitialTour(numCities);
        double currentFitness = calculateFitness(currentTour, matrix);

        double initialTemp = 1000;
        double rateOfCooling = 0.98;

        for (int i = 0; i < iterations; i++){
            ArrayList<Integer> newTour = smallChange(currentTour);
            double newFitness = calculateFitness(newTour, matrix);
            double fitnessChange = newFitness - currentFitness;

            double progress = (double) i / iterations;
            double coolingCompleteThreshold = 0.75;
            boolean coolingComplete = progress > coolingCompleteThreshold;

            double temperature = initialTemp * Math.pow(rateOfCooling, i);

            if (fitnessChange < 0 || !coolingComplete && Math.exp(-fitnessChange / temperature) > Math.random()){
                currentTour = newTour;
                currentFitness = newFitness;
            }
        }

        return currentTour;
    }

    // Dummy helpers to avoid compiler errors
    public static ArrayList<Integer> generateInitialTour(int cities) {
        ArrayList<Integer> tour = new ArrayList<>();
        for (int i = 0; i < cities; i++) tour.add(i);
        Collections.shuffle(tour);
        return tour;
    }

    public static ArrayList<Integer> smallChange(ArrayList<Integer> tour) {
        int a = (int)(Math.random() * tour.size());
        int b = (int)(Math.random() * tour.size());
        while (a == b) b = (int)(Math.random() * tour.size());
        ArrayList<Integer> newTour = new ArrayList<>(tour);
        Collections.swap(newTour, a, b);
        return newTour;
    }

    public static double calculateFitness(ArrayList<Integer> tour, double[][] matrix) {
        double dist = 0;
        for (int i = 0; i < tour.size() - 1; i++) dist += matrix[tour.get(i)][tour.get(i+1)];
        dist += matrix[tour.get(tour.size() - 1)][tour.get(0)];
        return dist;
    }

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
    

