package com.movietheater;

import com.movietheater.utils.InputValidator;

import java.io.*;
import java.util.Scanner;

public class Theater {
    private final int rows = 6;
    private final int cols = 8;
    private final Seat[][] seats = new Seat[rows][cols];
    private final String saveFile = "seating.txt";

    public Theater() {
        initializeSeats();
        loadSeatingChart();
    }

    public Seat getSeatByCoordinates(int row, int col) {
        if (row >= 0 && row < 6 && col >= 0 && col < 8) {
            return seats[row][col];
        }
        return null;
    }
    

    private void initializeSeats() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char rowChar = (char) ('A' + i);
                seats[i][j] = new Seat("" + rowChar + (j + 1));
            }
        }
    }

    public void displaySeats() {
        System.out.println("\nCurrent Seating Chart:");
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }
    }

    public boolean reserveSeat(String seatId) {
        Seat seat = getSeat(seatId);
        if (seat == null) {
            System.out.println("Invalid seat.");
            return false;
        }
        if (seat.isReserved()) {
            System.out.println("Seat already reserved. Suggesting next available seat...");
            suggestNextAvailable();
            return false;
        }
        seat.reserve();
        System.out.println("Seat " + seatId + " reserved.");
        return true;
    }

    public boolean cancelSeat(String seatId) {
        Seat seat = getSeat(seatId);
        if (seat == null) {
            System.out.println("Invalid seat.");
            return false;
        }
        if (!seat.isReserved()) {
            System.out.println("Seat is not currently reserved.");
            return false;
        }
        seat.cancel();
        System.out.println("Reservation for " + seatId + " canceled.");
        return true;
    }

    private Seat getSeat(String seatId) {
        if (!InputValidator.isValidSeatId(seatId, rows, cols)) return null;
        int row = seatId.toUpperCase().charAt(0) - 'A';
        int col = Integer.parseInt(seatId.substring(1)) - 1;
        return seats[row][col];
    }

    private void suggestNextAvailable() {
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                if (!seat.isReserved()) {
                    System.out.println("Try seat " + seat.getSeatId());
                    return;
                }
            }
        }
        System.out.println("No available seats.");
    }

    public void saveSeatingChart() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {
            for (Seat[] row : seats) {
                for (Seat seat : row) {
                    writer.write(seat.getSeatId() + ":" + (seat.isReserved() ? "1" : "0"));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving seating chart.");
        }
    }

    public void loadSeatingChart() {
        File file = new File(saveFile);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(":");
                String id = parts[0];
                boolean reserved = parts[1].equals("1");
                Seat seat = getSeat(id);
                if (seat != null && reserved) seat.reserve();
            }
        } catch (IOException e) {
            System.out.println("Error loading seating chart.");
        }
    }
}
