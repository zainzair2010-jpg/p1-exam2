public class Stack<T> {
    private Node<T> top;
    private int size;
    
    public Stack() {
        this.top = null;
        this.size = 0;
    }
    
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(top);
        top = newNode;
        size++;
    }
    
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack vacío - No se puede hacer pop");
        }
        T data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack vacío - No se puede hacer peek");
        }
        return top.getData();
    }
    
    public boolean isEmpty() {
        return top == null;
    }
    
    public int size() {
        return size;
    }

    public boolean undoLastLoan() {
        throw new UnsupportedOperationException("Unimplemented method 'undoLastLoan'");
    }
}