/**
 * Author: Nicholas Power SD12
 * Date: May 20, 2025
 * Program description: This Java program simulates a movie theater seat reservation system 
 * with both a command-line interface (CLI) and a graphical user interface (GUI) built using Swing.
 */


package com.movietheater;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Theater theater = new Theater();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Movie Theater Reservation System!");

        while (running) {
            System.out.println("\n1. Display Seats");
            System.out.println("2. Reserve Seat");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    theater.displaySeats();
                    break;
                case "2":
                    System.out.print("Enter seat ID to reserve (e.g., A1): ");
                    theater.reserveSeat(scanner.nextLine().toUpperCase());
                    break;
                case "3":
                    System.out.print("Enter seat ID to cancel (e.g., A1): ");
                    theater.cancelSeat(scanner.nextLine().toUpperCase());
                    break;
                case "4":
                    theater.saveSeatingChart();
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
