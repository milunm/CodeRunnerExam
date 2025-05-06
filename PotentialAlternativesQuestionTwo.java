import java.util.ArrayList;
import java.util.Collections;

public class PotentialAlternativesQuestionTwo {


    // Q2 - Variation 1: Return int[] instead of ArrayList<Integer>
public static int[] generateInitialTourArray(int cities) {
    if (cities <= 0) return null;
    if (cities > 1000) return null;
    int[] tour = new int[cities];
    for (int i = 0; i < cities; i++) tour[i] = i;
    // Fisher-Yates shuffle
    for (int i = cities - 1; i > 0; i--) {
        int j = (int)(Math.random() * (i + 1));
        int temp = tour[i];
        tour[i] = tour[j];
        tour[j] = temp;
    }
    return tour;
}

// Q2 - Variation 2: Takes matrix as input
public static ArrayList<Integer> generateInitialTourFromMatrix(double[][] matrix) {
    if (matrix == null) return null;
    if (matrix.length <= 0) return null;
    if (matrix.length > 1000) return null;
    int cities = matrix.length;
    ArrayList<Integer> randomTour = new ArrayList<>();
    for (int i = 0; i < cities; i++) randomTour.add(i);
    Collections.shuffle(randomTour);
    return randomTour;
}

// Q2 - Variation 3: Takes matrix as ArrayList
public static ArrayList<Integer> generateInitialTourFromArrayList(ArrayList<ArrayList<Double>> matrix) {
    if (matrix == null) return null;
    if (matrix.size() <= 0) return null;
    if (matrix.size() > 1000) return null;
    int cities = matrix.size();
    ArrayList<Integer> randomTour = new ArrayList<>();
    for (int i = 0; i < cities; i++) randomTour.add(i);
    Collections.shuffle(randomTour);
    return randomTour;
}



    


    



    
}