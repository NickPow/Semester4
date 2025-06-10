/**
 * Author: Nicholas Power SD12
 * Date: June 10, 2025
 * Program description: Implement a delete method in the existing 
 * SingleLinkedList class found in the file named SingleLinkedListTest
 */


package Assignment3;

public class SingleLinkedListTest {
    public Node head;
    public Node tail;
    public int size;

    // Create the initial linked list with a single node
    public Node createSingleLinkedlist(int nodeValue){
        Node node = new Node();
        node.value = nodeValue;
        node.next = null;
        head = node;
        tail = node;
        size = 1;
        return head;
    }

    // Insert a new node at the given location
    public void insertLinkedList(int nodeValue, int location) {
        Node node = new Node();
        node.value = nodeValue;

        if (head == null){
            createSingleLinkedlist(nodeValue);
            return;
        } else if (location == 0) {
            node.next = head;
            head = node;
        } else if (location >= size){
            node.next = null;
            tail.next = node;
            tail = node;
        } else {
            Node tempNode = head;
            int index = 0;
            while (index < location - 1){
                tempNode = tempNode.next;
                index++;
            }
            Node nextNode = tempNode.next;
            tempNode.next = node;
            node.next = nextNode;
        }
        size++;
    }

    // Traverse and print all elements of the linked list
    public void traverseLinkedList(){
        if (head == null) {
            System.out.println("SLL does not exist");
        } else {
            Node tempNode = head;
            for (int i = 0; i < size; i++) {
                System.out.print(tempNode.value);
                if (i != size -1) {
                    System.out.print(" -> ");
                }
                tempNode = tempNode.next;
            }
            System.out.println();
        }
        System.out.println();
    }

    // Search for a node by its value
    public boolean searchNode(int nodeValue){
        if (head != null) {
            Node tempNode = head;
            for (int i = 0; i < size; i++){
                if (tempNode.value == nodeValue) {
                    System.out.println("Found the node at location: " + i);
                    return true;
                }
                tempNode = tempNode.next;
            }
        }
        System.out.println("Node not found");
        return false;
    }

    // Delete a node at the given position
    public void delete(int position) {
        // Handle invalid position
        if (position < 0 || position >= size) {
            System.out.println("Error: Invalid position. No node deleted.");
            return;
        }

        // Deleting the first node
        if (position == 0) {
            System.out.println("Deleting node at head: " + head.value);
            head = head.next;

            // If list becomes empty
            if (head == null) {
                tail = null;
            }

        // Deleting the last node
        } else if (position == size - 1) {
            Node tempNode = head;
            for (int i = 0; i < size - 2; i++) {
                tempNode = tempNode.next;
            }
            System.out.println("Deleting node at tail: " + tail.value);
            tempNode.next = null;
            tail = tempNode;

        // Deleting a node from the middle
        } else {
            Node tempNode = head;
            for (int i = 0; i < position - 1; i++) {
                tempNode = tempNode.next;
            }
            System.out.println("Deleting node at position " + position + ": " + tempNode.next.value);
            tempNode.next = tempNode.next.next;
        }

        size--;
    }

    // Main method for testing
    public static void main(String[] args) {
        SingleLinkedListTest list = new SingleLinkedListTest();

        // Step 1: Create list with first node
        list.createSingleLinkedlist(10);

        // Step 2: Insert additional values
        list.insertLinkedList(20, 1);
        list.insertLinkedList(30, 2);
        list.insertLinkedList(40, 3);

        System.out.println("Initial List:");
        list.traverseLinkedList(); 

        // Step 3: Delete head
        list.delete(0);
        list.traverseLinkedList(); 

        // Step 4: Delete middle
        list.delete(1);
        list.traverseLinkedList(); 

        // Step 5: Delete tail
        list.delete(1);
        list.traverseLinkedList(); 

        // Step 6: Invalid deletion
        list.delete(5); 
    }
}


