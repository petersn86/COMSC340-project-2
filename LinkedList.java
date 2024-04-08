public class LinkedList {
    public Node head;

    public LinkedList() {
        this.head = null;
    }

    public void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    public int getElementAt(int index) {
        if (head == null || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node current = head;
        int currentIndex = 0;

        while (current != null) {
            if (currentIndex == index) {
                return current.data;
            }
            current = current.next;
            currentIndex++;
        }

        throw new IndexOutOfBoundsException("Index out of bounds");
    }

    public Node getNext(Node currentNode) {
        if (currentNode == null || currentNode.next == null) {
            return null;
        } else {
            return currentNode.next;
        }
    }

}
