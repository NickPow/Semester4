package com.undo;

/**
 * Represents a node in a doubly linked list for storing a state.
 *
 * @param <T> the type of data stored in the node
 */
public class StateNode<T> {
    T data;
    StateNode<T> prev;
    StateNode<T> next;

    /**
     * Constructs a new StateNode with the given data.
     *
     * @param data the state data to store
     */
    public StateNode(T data) {
        this.data = data;
    }
}
