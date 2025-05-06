import java.util.*;

public class PotentialAlternativesQuestionThree {

    // Q3 - Variation 1: Return boolean instead of int
    public static boolean isValidTourBoolean(ArrayList<Integer> tour, double[][] matrix) {
        if (tour == null) return false;
        if (matrix == null) return false;
        if (tour.size() != matrix.length) return false;

        Set<Integer> checkDuplicate = new HashSet<>(tour);
        if (checkDuplicate.size() != matrix.length) return false;

        return true;
    }

    // Q3 - Variation 2: Accepts int[][] instead of double[][]
    public static int isValidTourIntMatrix(ArrayList<Integer> tour, int[][] matrix) {
        if (tour == null) return 0;
        if (matrix == null) return 0;
        if (tour.size() != matrix.length) return 0;

        Set<Integer> checkDuplicate = new HashSet<>(tour);
        if (checkDuplicate.size() != matrix.length) return 0;

        return 1;
    }

    // Q3 - Variation 3: Accepts matrix as ArrayList<ArrayList<Double>>
    public static int isValidTourArrayList(ArrayList<Integer> tour, ArrayList<ArrayList<Double>> matrix) {
        if (tour == null) return 0;
        if (matrix == null) return 0;
        if (tour.size() != matrix.size()) return 0;

        Set<Integer> checkDuplicate = new HashSet<>(tour);
        if (checkDuplicate.size() != matrix.size()) return 0;

        return 1;
    }
} 
