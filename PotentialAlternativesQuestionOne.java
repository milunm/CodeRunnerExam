import java.util.ArrayList;

public class PotentialAlternativesQuestionOne {
    // Q1 - Variation 1: Same logic but for int[][] matrix
public static int isValidMatrixINT(int[][] matrix) {
    if (matrix == null || matrix.length == 0) return 0; //return 0 if matrix is null or empty 
    int cities = matrix.length; //store number of cities

    for (int i = 0; i < cities; i++) {
        if (matrix[i].length != cities) return 0; //if column length doesnt match row length, return 0

        for (int j = 0; j < cities; j++) {
            int temp = matrix[i][j]; //temporary store current distance being checked

            if (temp < 0) return 0; //ensure there are no negative distances

            if (i == j && temp != 0) return 0; //ensure diagonal (return distance) is equal to 0

            if (temp != matrix[j][i]) return 0; //ensure matrix is symmetric
        }
    }
    return 1; //if all checks pass, matrix is valid
}

// Q1 - Variation 2: Same logic but returns boolean instead of int
public static boolean isValidMatrixBoolean(double[][] matrix) {
    if (matrix == null || matrix.length == 0) return false; //return false if matrix is null or empty 
    int cities = matrix.length; //store number of cities

    for (int i = 0; i < cities; i++) {
        if (matrix[i].length != cities) return false; //if column length doesnt match row length, return false

        for (int j = 0; j < cities; j++) {
            double temp = matrix[i][j]; //temporary store current distance being checked

            if (temp < 0) return false; //ensure there are no negative distances

            if (i == j && temp != 0) return false; //ensure diagonal (return distance) is equal to 0

            if (temp != matrix[j][i]) return false; //ensure matrix is symmetric
        }
    }
    return true; //if all checks pass, matrix is valid
}

// Q1 - Variation 3: Using ArrayList<ArrayList<Double>> as input
public static int isValidMatrixArrayList(ArrayList<ArrayList<Double>> matrix) {
    if (matrix == null || matrix.size() == 0) return 0; //return 0 if matrix is null or empty 
    int cities = matrix.size(); //store number of cities

    for (int i = 0; i < cities; i++) {
        if (matrix.get(i).size() != cities) return 0; //if column length doesnt match row length, return 0

        for (int j = 0; j < cities; j++) {
            double temp = matrix.get(i).get(j); //temporary store current distance being checked

            if (temp < 0) return 0; //ensure there are no negative distances

            if (i == j && temp != 0) return 0; //ensure diagonal (return distance) is equal to 0

            if (temp != (matrix.get(j).get(i))) return 0; //ensure matrix is symmetric
        }
    }
    return 1; //if all checks pass, matrix is valid
}


    
}
