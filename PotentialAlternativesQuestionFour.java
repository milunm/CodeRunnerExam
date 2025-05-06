import java.util.ArrayList;

public class PotentialAlternativesQuestionFour {

    // Variation 1: Accepts int[][] instead of double[][]
    public static double calculateFitnessIntMatrix(ArrayList<Integer> tour, int[][] matrix) {
        if (tour == null) return Double.MAX_VALUE;
        if (matrix == null) return Double.MAX_VALUE;
        if (tour.size() != matrix.length) return Double.MAX_VALUE;
        int tourSize = tour.size();
        if (tourSize < 2) return 0;
        double tourDistance = 0;
        for (int i = 0; i < tourSize - 1; i++) {
            tourDistance += matrix[tour.get(i)][tour.get(i + 1)];
        }
        tourDistance += matrix[tour.get(tourSize - 1)][tour.get(0)];
        return tourDistance;
    }

    // Variation 2: Returns int by rounding the fitness
    public static int calculateFitnessAsInt(ArrayList<Integer> tour, double[][] matrix) {
        if (tour == null) return Integer.MAX_VALUE;
        if (matrix == null) return Integer.MAX_VALUE;
        if (tour.size() != matrix.length) return Integer.MAX_VALUE;
        int tourSize = tour.size();
        if (tourSize < 2) return 0;
        double tourDistance = 0;
        for (int i = 0; i < tourSize - 1; i++) {
            tourDistance += matrix[tour.get(i)][tour.get(i + 1)];
        }
        tourDistance += matrix[tour.get(tourSize - 1)][tour.get(0)];
        return (int) Math.round(tourDistance);
    }

    // Variation 3: Accepts ArrayList<ArrayList<Double>> matrix
    public static double calculateFitnessArrayListMatrix(ArrayList<Integer> tour, ArrayList<ArrayList<Double>> matrix) {
        if (tour == null) return Double.MAX_VALUE;
        if (matrix == null) return Double.MAX_VALUE;
        if (tour.size() != matrix.size()) return Double.MAX_VALUE;
        int tourSize = tour.size();
        if (tourSize < 2) return 0;
        double tourDistance = 0;
        for (int i = 0; i < tourSize - 1; i++) {
            tourDistance += matrix.get(tour.get(i)).get(tour.get(i + 1));
        }
        tourDistance += matrix.get(tour.get(tourSize - 1)).get(tour.get(0));
        return tourDistance;
    }
}