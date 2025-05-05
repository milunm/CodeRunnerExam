import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FINAL_VERSION {
    // Q1 
    public static int isValidMatrix(double[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0; //return 0 if matrix is null or empty
        int cities = matrix.length; //store number of cities

        for (int i = 0; i < cities; i++) {
            if (matrix[i].length != cities) return 0; //if column length doesnt match row length, return - (checks if matrix is square)

            for (int j = 0; j < cities; j++) { 
                double temp = matrix[i][j]; //temporary store current distance being checked

                if (temp < 0) return 0; //ensure there are no negative distances

                if (i == j && temp != 0) return 0; //ensure diagonal (return distance) is equal to 0

                if (temp != matrix[j][i]) return 0; //ensure matrix is symmetric
            }
        }
        return 1; //if all checks pass, matrix is valid
    }
     
    // Q2
    public static ArrayList<Integer> generateInitialTour(int cities) { //return a random tour as an ArrayList of type Integer, when taking in the total number of cities
        ArrayList<Integer> randomTour = new ArrayList<>(); //empty list to store the tour
        for (int cityIndex = 0; cityIndex < cities; cityIndex++) randomTour.add(cityIndex); //for loop, add each city index to the list
        Collections.shuffle(randomTour); //randomize the order of the tour
        return randomTour; //return shuffled list which can then be used to start the search
    }

    // Q3
    public static int isValidTour(ArrayList<Integer> tour, double[][] matrix) {
        if (tour == null || matrix == null || tour.size() != matrix.length) return 0; //check for null values and if the tour length matches the number of cities

        Set<Integer> checkDuplicate = new HashSet<>(tour); //detect any duplicate cities (this set variable only stores unique cities)
        if (checkDuplicate.size() != matrix.length) return 0; //if cities are repeated or missing, tour is invalid

        return 1; //if all checks passed, tour is valid 
    }

    // Q4
    public static double calculateFitness(ArrayList<Integer> tour, double[][] matrix) {
        if (tour == null || matrix == null || tour.size() != matrix.length){ 
            return Double.MAX_VALUE; //return a large number if invalid, indicates bad fitness
         } //validation for invalid tour or matrix inputs

        double tourDistance = 0; //variable to store total tour distance
        int tourSize = tour.size(); //get total size of tour
        for (int i = 0; i < tourSize - 1; i++) { //loop through each city within the tour
            tourDistance += matrix[tour.get(i)][tour.get(i + 1)]; //add distance from current city to next city in the tour
        }
        tourDistance += matrix[tour.get(tourSize - 1)][tour.get(0)]; // return to start, add distance from last city back to first one (completing circular tour)
        return tourDistance; //return the total distance covered
    }

    // Q5
    public static ArrayList<Integer> smallChange(ArrayList<Integer> tour) {
        ArrayList<Integer> newTour = new ArrayList<>(tour); //create copy so the original input isnt modified
        int firstIndex = (int)(Math.random() * newTour.size());
        int secondIndex = (int)(Math.random() * newTour.size()); // pick 2 random index within the tour (type INT insures its a whole number)
        while (firstIndex == secondIndex){
            firstIndex = (int)(Math.random() * newTour.size());
            secondIndex = (int)(Math.random() * newTour.size()); //while the indexes are equal, continue picking new indexes randomly
        } 
        Collections.swap(newTour, firstIndex, secondIndex); //swap the cities at index i and j
        return newTour; //return the new tour (with a small change to original input)
    }

    //Q6: SIMULATED ANNEALING
    public static ArrayList<Integer> solveTSP_SA(int numCities, double[][] matrix, int iterations){
        if (numCities <= 1 || isValidMatrix(matrix) == 0 || iterations < 1) return null; //validation before proceeding 
        //simulated annealing approach
        ArrayList<Integer> currentTour = generateInitialTour(numCities); //generate a random valid tour to start
        double currentFitness = calculateFitness(currentTour, matrix); //calculate fitness (total distance) of initial tour

        //setup variables for SA approach
        double initialTemp = 1000; //starting temp (start of hot, decrease over iterations)
        double rateOfCooling = 0.98; //cooling rate (enables cooling over each iteration)
        for (int i = 0; i < iterations; i++){
            ArrayList<Integer> newTour = smallChange(currentTour); //generate a new tour with slight mutation (2 swapped cities)
            double newFitness = calculateFitness(newTour, matrix); //calculate fitness of new tour
            double fitnessChange = newFitness - currentFitness; //calculate fitness difference between new and current tour

            double progress = (double) i / iterations; //track percentage of iterations complete
            double coolingCompleteThreshold = 0.75; //threshold to change when cooling can be considered complete
            boolean coolingComplete = progress > coolingCompleteThreshold; //after 75% of iterations, cooling is complete and the algorithm should stop accepting worse solutions 

            //dynamically lower temp using exponential cooling
            double temperature = initialTemp * Math.pow(rateOfCooling, i);
            

            if (fitnessChange < 0 || !coolingComplete && Math.exp(-fitnessChange / temperature) > Math.random()){ //sometimes accept worse solutions based on probability generated by temperature + the fitness difference 
                //(higher probability if tour is only slightly worse, if a lot worse then low probability of accepting.) 

                currentTour = newTour;
                currentFitness = newFitness;
            }
           
        }

        return currentTour;
    }

    
}
