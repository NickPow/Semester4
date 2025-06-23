package com.undo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StateManagerTest {

    @Test
    void testUndoRedoFlow() {
        StateManager<String> manager = new StateManager<>();

        manager.addState("First");
        manager.addState("Second");
        manager.addState("Third");

        assertEquals("Third", manager.getCurrentState());
        assertEquals("Second", manager.undo());
        assertEquals("First", manager.undo());
        assertNull(manager.undo()); // No more undo

        assertEquals("Second", manager.redo());
        assertEquals("Third", manager.redo());
        assertNull(manager.redo()); // No more redo
    }

    @Test
    void testRedoClearedAfterNewState() {
        StateManager<String> manager = new StateManager<>();

        manager.addState("A");
        manager.addState("B");
        manager.addState("C");

        manager.undo(); // Back to B
        manager.undo(); // Back to A

        manager.addState("D"); // Redo history cleared

        assertEquals("D", manager.getCurrentState());
        assertNull(manager.redo());
    }

    @Test
    void testClearHistory() {
        StateManager<Integer> manager = new StateManager<>();

        manager.addState(1);
        manager.addState(2);
        manager.addState(3);

        assertEquals(3, manager.getCurrentState());

        manager.clearHistory();

        assertNull(manager.getCurrentState());
        assertNull(manager.undo());
        assertNull(manager.redo());
    }
}
