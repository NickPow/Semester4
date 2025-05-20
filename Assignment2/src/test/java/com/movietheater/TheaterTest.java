package com.movietheater;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TheaterTest {

    @Test
    public void testSeatReservation() {
        Theater theater = new Theater();
        assertTrue(theater.reserveSeat("A1"));
        assertFalse(theater.reserveSeat("A1")); // already reserved
        assertTrue(theater.cancelSeat("A1"));
        assertFalse(theater.cancelSeat("A1")); // already canceled
    }
}
