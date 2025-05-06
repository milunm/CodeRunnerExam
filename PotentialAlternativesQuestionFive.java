
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PotentialAlternativesQuestionFive {

    // Q5 - Alternative 1: Use int[] instead of ArrayList<Integer>
    public static int[] smallChangeArray(int[] tour) {
        if (tour == null) return null;
        if (tour.length < 2) return tour;

        int[] newTour = tour.clone();
        Random rand = new Random();
        int i = rand.nextInt(newTour.length);
        int j = rand.nextInt(newTour.length);
        while (i == j) {
            i = rand.nextInt(newTour.length);
            j = rand.nextInt(newTour.length);
        }

        int temp = newTour[i];
        newTour[i] = newTour[j];
        newTour[j] = temp;

        return newTour;
    }

    // Q5 - Alternative 2: Return null for size < 2
    public static ArrayList<Integer> smallChangeNullIfSmall(ArrayList<Integer> tour) {
        if (tour == null) return null;
        if (tour.size() < 2) return null;

        ArrayList<Integer> newTour = new ArrayList<>(tour);
        int i = (int)(Math.random() * newTour.size());
        int j = (int)(Math.random() * newTour.size());
        while (i == j) {
            i = (int)(Math.random() * newTour.size());
            j = (int)(Math.random() * newTour.size());
        }

        Collections.swap(newTour, i, j);
        return newTour;
    }

    // Q5 - Alternative 3: In-place mutation with void return type
    public static void smallChangeInPlace(ArrayList<Integer> tour) {
        if (tour == null || tour.size() < 2) return;

        int i = (int)(Math.random() * tour.size());
        int j = (int)(Math.random() * tour.size());
        while (i == j) {
            i = (int)(Math.random() * tour.size());
            j = (int)(Math.random() * tour.size());
        }

        Collections.swap(tour, i, j);
    }
}
