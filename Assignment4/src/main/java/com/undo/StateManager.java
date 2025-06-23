package com.undo;

/**
 * Manages a sequence of state changes with undo/redo functionality.
 *
 * @param <T> the type of the state data
 */
public class StateManager<T> {
    private StateNode<T> current;

    /**
     * Adds a new state and clears any redo history.
     *
     * @param data the new state to add
     */
    public void addState(T data) {
        StateNode<T> newNode = new StateNode<>(data);

        if (current != null) {
            current.next = null; // Clear redo history
            current.next = newNode;
            newNode.prev = current;
        }

        current = newNode;
    }

    /**
     * Moves one step backward in the state history.
     *
     * @return the previous state, or null if not available
     */
    public T undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            return current.data;
        }
        return null;
    }

    /**
     * Moves one step forward in the state history.
     *
     * @return the next state, or null if not available
     */
    public T redo() {
        if (current != null && current.next != null) {
            current = current.next;
            return current.data;
        }
        return null;
    }

    /**
     * Returns the current state.
     *
     * @return the current state, or null if none exists
     */
    public T getCurrentState() {
        return current != null ? current.data : null;
    }

    /**
     * Prints the entire state history from first to current.
     */
    public void printHistory() {
        StateNode<T> node = current;

        // Navigate to first node
        while (node != null && node.prev != null) {
            node = node.prev;
        }

        System.out.print("State History: ");
        while (node != null) {
            System.out.print(node.data);
            if (node == current) {
                System.out.print(" [Current]");
            }
            System.out.print(" -> ");
            node = node.next;
        }
        System.out.println("null");
    }

    /**
     * Clears all state history.
     */
    public void clearHistory() {
        current = null;
    }
}
