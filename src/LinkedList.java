public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
   
    public boolean add(T data) {
        Node<T> newNode = new Node<>(data);
        
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        
        size++;
        return true;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        
        return current.getData();
    }

    public boolean remove(T data) {
        if (head == null) return false;
        
        Node<T> current = head;
        
        while (current != null && !current.getData().equals(data)) {
            current = current.getNext();
        }
        
        if (current == null) return false; 
        
        if (current.getPrev() != null) {
            current.getPrev().setNext(current.getNext());
        } else {
            head = current.getNext(); 
        }
        
        if (current.getNext() != null) {
            current.getNext().setPrev(current.getPrev());
        } else {
            tail = current.getPrev(); 
        }
        
        size--;
        return true;
    }

    public boolean contains(T data) {
        Node<T> current = head;
        
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public int size() {
        return size;
    }
    
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = head;
        
        for (int i = 0; i < size; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        
        return array;
    }
}