class Node<T> {
    T state;
    Node<T> prev;
    Node<T> next;

    Node(T state) {
        this.state = state;
    }
}

public class UndoRedoManager<T> {
    private Node<T> current;

    public void addState(T newState) {
        Node<T> newNode = new Node<>(newState);
        if (current != null) {
            // Remove forward history
            current.next = null;
            newNode.prev = current;
            current.next = newNode;
        }
        current = newNode;
    }

    public boolean undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            return true;
        }
        System.out.println("No more undo steps.");
        return false;
    }

    public boolean redo() {
        if (current != null && current.next != null) {
            current = current.next;
            return true;
        }
        System.out.println("No more redo steps.");
        return false;
    }

    public T getCurrentState() {
        return current != null ? current.state : null;
    }

    // This is the optional stuff
    public void printHistory() {
        Node<T> temp = current;
        // Move to head
        while (temp != null && temp.prev != null) {
            temp = temp.prev;
        }
        System.out.print("History: ");
        while (temp != null) {
            System.out.print(temp.state + (temp == current ? "(current)" : "") + " <> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

}