/**
 * Author: Nicholas Power SD12
 * Date: May 12, 2025
 * Program description: This program calculates the average temperature over a specified number of days.
 */



import java.util.Scanner;

public class TemperatureCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Ask for number of days
        System.out.print("Enter the number of days: ");
        int numberOfDays = scanner.nextInt();

        // Step 2: Get temperature inputs
        double[] temperatures = new double[numberOfDays];
        for (int i = 0; i < numberOfDays; i++) {
            temperatures[i] = getValidTemperature(scanner, i + 1);
        }

        // Step 3: Calculate average
        double average = calculateAverage(temperatures);
        System.out.printf("Average temperature: %.2f\n", average);

        // Step 4: Count and print days above average
        int countAboveAverage = countAboveAverage(temperatures, average);
        printAboveAverageDays(temperatures, average);

        System.out.println("Number of days above average temperature: " + countAboveAverage);

        scanner.close();
    }

    // Gets temperature from the user
    public static double getValidTemperature(Scanner scanner, int day) {
        while (true) {
            System.out.print("Enter temperature for day " + day + ": ");
            if (scanner.hasNextDouble()) {
                double temp = scanner.nextDouble();
                if (temp >= -100 && temp <= 100) {
                    return temp;
                } else {
                    System.out.println("Please enter a temperature between -100 and 100.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next(); 
            }
        }
    }

    // Calculates the average temperature
    public static double calculateAverage(double[] temps) {
        double sum = 0;
        for (double temp : temps) {
            sum += temp;
        }
        return sum / temps.length;
    }

    // Counts how many temperatures are above the average
    public static int countAboveAverage(double[] temps, double average) {
        int count = 0;
        for (double temp : temps) {
            if (temp > average) {
                count++;
            }
        }
        return count;
    }

    // Prints each day that had a temperature above average
    public static void printAboveAverageDays(double[] temps, double average) {
        System.out.println("Days with above average temperature:");
        for (int i = 0; i < temps.length; i++) {
            if (temps[i] > average) {
                System.out.printf("Day %d: %.2f\n", i + 1, temps[i]);
            }
        }
    }
}
