public class Queue<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;
    
    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }
    
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue vacío - No se puede hacer dequeue");
        }
        
        T data = front.getData();
        front = front.getNext();
        
        if (front == null) {
            rear = null;
        }
        
        size--;
        return data;
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue vacío - No se puede hacer peek");
        }
        return front.getData();
    }
    
    public boolean isEmpty() {
        return front == null;
    }
    
    public int size() {
        return size;
    }
}