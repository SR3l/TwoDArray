
/*
Chapter 7. PC #16. 2D Array Operations with Additional Requirements
Write a program that creates an ArrayList of ArrayList of Doubles. The program should ask the user to enter the filename from the keyboard and validate the file for existence. Once the file is verified, the program should load the two-dimensional ArrayList with test data from the file. Be careful, as some of the files will contain rows with different number of elements. The program should work regardless whether the input data is perfectly rectangular or ragged. The program should have the following methods:
• main. Main entry point for the program.
• getRowSubtotal. This method should accept a two-dimensional ArrayList as its first argument
and an integer as its second argument. The second argument should be the index of the row in the ArrayList.
 The method should return the subtotal of the values in the specified row.
• getColSubtotal. This method should accept a two-dimensional ArrayList as its first argument
 and an integer as its second argument. The second argument should be the index of the column in the
 ArrayList. The method should return the subtotal of the values in the specified column. I
 f any of the rows has a size that is less than the column number, then that row should be skipped when calculating the column subtotal.
• getTotal. This method should accept a two-dimensional array as its argument and return the total of all
 the values in the array.
Demonstrate each of the methods in this program.





 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);


        System.out.println("Please enter the file name or type QUIT to exit:");
        String fileName = scan.nextLine();

        if (fileName.equalsIgnoreCase("quit")) {
            return;
        }


        File file = new File(fileName);
        while (!file.exists()) {
            System.out.printf("File: %s does not exist.\n", fileName);
            System.out.println("Please enter the file name again or type QUIT to exit:");
            fileName = scan.nextLine();
            if (fileName.equalsIgnoreCase("quit")) {
                return;
            }
            file = new File(fileName);

        }
        ArrayList<ArrayList<Double>> array = load(file);

        for (int row = 0; row < array.size(); row++) {
            System.out.printf("Row %d Length: %d, Subtotal: %.3f\n", row, array.get(row).size(), getRowSubTotal(array, row));
        }
        for (int col = 0; col < longestRow(array); col++) {
            System.out.printf("Column %d Height: %d, Subtotal: %.3f\n", col, getColHeight(array, col), getColSubTotal(array, col));
        }

        System.out.printf("Array Elements: %d, Total: %.3f\n", getElementCount(array), getTotal(array));

    }

    private static int getElementCount(ArrayList<ArrayList<Double>> array) {
        int count = 0;
        for (ArrayList<Double> row : array) {
            count += row.size();
        }
        return count;

    }

    private static int getColHeight(ArrayList<ArrayList<Double>> array, int col) {
        int height = 0;
        for (ArrayList<Double> row : array) {
            if (row.size() > col) {
                height++;
            }
        }
        return height;
    }

    private static int longestRow(ArrayList<ArrayList<Double>> array) {
        int longestRowSize = 0;
        for (ArrayList<Double> row : array) {
            if (row.size() > longestRowSize) {
                longestRowSize = row.size();
            }
        }
        return longestRowSize;
    }

    private static ArrayList<ArrayList<Double>> load(File file) throws FileNotFoundException {
        Scanner inputFile = new Scanner(file);
        ArrayList<ArrayList<Double>> result = new ArrayList<>();

        while (inputFile.hasNext()) {
            String line = inputFile.nextLine();
            ArrayList<Double> row = new ArrayList<>();
            Scanner rowReader = new Scanner(line);
            while (rowReader.hasNext()) {
                row.add(rowReader.nextDouble());
            }
            rowReader.close();
            result.add(row);
        }
        inputFile.close();
        return result;
    }

    private static double getRowSubTotal(ArrayList<ArrayList<Double>> array, int row) {
        double total = 0.0;
        for (double element : array.get(row)) {
            total += element;
        }
        return total;
    }

    private static double getColSubTotal(ArrayList<ArrayList<Double>> array, int col) {
        double total = 0.0;
        for (ArrayList<Double> row : array) {
            if (row.size() > col) {
                total += row.get(col);
            }
        }
        return total;

    }

    private static double getTotal(ArrayList<ArrayList<Double>> array) {
        double total = 0.0;
        for (ArrayList<Double> row : array) {
            for (double element : row) {
                total += element;
            }
        }
        return total;
    }
}