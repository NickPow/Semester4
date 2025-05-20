package com.movietheater.utils;

public class InputValidator {

    public static boolean isValidSeatId(String seatId, int rows, int cols) {
        if (seatId == null || seatId.length() < 2) return false;
        char rowChar = seatId.toUpperCase().charAt(0);
        String colStr = seatId.substring(1);
        try {
            int row = rowChar - 'A';
            int col = Integer.parseInt(colStr) - 1;
            return row >= 0 && row < rows && col >= 0 && col < cols;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
