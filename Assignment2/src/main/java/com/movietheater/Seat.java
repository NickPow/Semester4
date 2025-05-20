package com.movietheater;

public class Seat {
    private final String seatId;
    private boolean reserved;

    public Seat(String seatId) {
        this.seatId = seatId;
        this.reserved = false;
    }

    public String getSeatId() {
        return seatId;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void reserve() {
        reserved = true;
    }

    public void cancel() {
        reserved = false;
    }

    @Override
    public String toString() {
        return reserved ? "[X]" : "[" + seatId + "]";
    }
}
