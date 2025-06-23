/**
 * Author: Nicholas Power SD12
 * Date: June 22, 2025
 * Program description: This project implements an application that supports undo and redo 
 * functionality using a custom linked list. It is built with generics to handle any data type, 
 * demonstrated with String states.
 * To Run Program:
 *          mvn clean compile
 *          mvn exec:java -Dexec.mainClass="com.undo.Main"
 */


package com.undo;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CLI interface for testing undo/redo functionality with string states.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StateManager<String> stateManager = new StateManager<>();
        
        boolean running = true;

        System.out.println("=== Undo/Redo CLI Application ===");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1 - Add new state");
            System.out.println("2 - Undo");
            System.out.println("3 - Redo");
            System.out.println("4 - Show current state");
            System.out.println("5 - Show full state history");
            System.out.println("6 - Clear all state history");
            System.out.println("7 - Exit");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter new state: ");
                    String newState = scanner.nextLine();
                    stateManager.addState(newState);
                    System.out.println("State added: " + newState);
                    break;

                case "2":
                    String undone = stateManager.undo();
                    if (undone != null) {
                        System.out.println("Undo successful. Current state: " + undone);
                    } else {
                        System.out.println("Cannot undo. Already at the earliest state.");
                    }
                    break;

                case "3":
                    String redone = stateManager.redo();
                    if (redone != null) {
                        System.out.println("Redo successful. Current state: " + redone);
                    } else {
                        System.out.println("Cannot redo. Already at the latest state.");
                    }
                    break;

                case "4":
                    String current = stateManager.getCurrentState();
                    if (current != null) {
                        System.out.println("Current state: " + current);
                    } else {
                        System.out.println("No states available.");
                    }
                    break;

                case "5":
                    stateManager.printHistory();
                    break;

                case "6":
                    stateManager.clearHistory();
                    System.out.println("State history cleared.");
                    break;

                case "7":
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
