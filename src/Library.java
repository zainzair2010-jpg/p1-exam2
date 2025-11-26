public class Library {
    private String name;
    private LinkedList<Book> books;
    private Stack<LoanOperation> loanHistory;
    private Queue<Book> bookQueue;
    
    public Library(String name) {
        this.name = name;
        this.books = new LinkedList<>();
        this.loanHistory = new Stack<>();
        this.bookQueue = new Queue<>();
    }
    
    public String getName() {
        return name;
    }
    
    public LinkedList<Book> getBooks() {
        return books;
    }
    
    public Stack<LoanOperation> getLoanHistory() {
        return loanHistory;
    }
    
    public Queue<Book> getBookQueue() {
        return bookQueue;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean addBook(Book book) {
        if (book != null) {
            books.add(book);
            return true;
        }
        return false;
    }
    
    public boolean removeBook(Book book) {
        if (book != null) {
            return books.remove(book);
        }
        return false;
    }
    
    public Book findBookByIsbn(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
    
    public LinkedList<Book> findBooksByTitle(String title) {
        LinkedList<Book> foundBooks = new LinkedList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
    
    public LinkedList<Book> findBooksByAuthor(String authorName) {
        LinkedList<Book> foundBooks = new LinkedList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getAuthor().toLowerCase().contains(authorName.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
    
    public LinkedList<Book> getAvailableBooks() {
        LinkedList<Book> availableBooks = new LinkedList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    
    public LinkedList<Book> getLoanedBooks() {
        LinkedList<Book> loanedBooks = new LinkedList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (!book.isAvailable()) {
                loanedBooks.add(book);
            }
        }
        return loanedBooks;
    }
    
    public boolean lendBook(Book book, String user) {
        if (book != null && book.lend()) {
            loanHistory.push(new LoanOperation(book, user, "PRESTAMO"));
            return true;
        }
        return false;
    }
    
    public boolean returnBook(Book book, String user) {
        if (book != null) {
            book.returnBook();
            loanHistory.push(new LoanOperation(book, user, "DEVOLUCION"));
            return true;
        }
        return false;
    }
    
    public void addBookToQueue(Book book) {
        bookQueue.enqueue(book);
        System.out.println(" Libro agregado a cola de procesamiento: " + book.getTitle());
    }
    
    public Book processNextBookInQueue() {
        if (bookQueue.isEmpty()) {
            System.out.println(" No hay libros en espera para procesar");
            return null;
        }
        
        Book book = bookQueue.dequeue();
        System.out.println(" Procesando libro: " + book.getTitle());
        System.out.println("   Registrado en sistema");
        System.out.println("   Etiquetado con código");
        System.out.println("   Asignada ubicación en estantería");
        System.out.println("   Listo para préstamo");
        
        addBook(book);
        return book;
    }
    
    public boolean undoLastLoan() {
        if (loanHistory.isEmpty()) {
            System.out.println(" No hay operaciones para deshacer");
            return false;
        }
        
        LoanOperation lastOperation = loanHistory.pop();
        if ("PRESTAMO".equals(lastOperation.getOperationType())) {
            lastOperation.getBook().returnBook();
            System.out.println(" Deshecho préstamo: " + lastOperation.getBook().getTitle());
            return true;
        } else if ("DEVOLUCION".equals(lastOperation.getOperationType())) {
            lastOperation.getBook().lend();
            System.out.println(" Deshecha devolución: " + lastOperation.getBook().getTitle());
            return true;
        }
        return false;
    }
    
    public String getStatistics() {
        int totalBooks = books.size();
        int availableBooks = getAvailableBooks().size();
        int loanedBooks = getLoanedBooks().size();
        int booksInQueue = bookQueue.size();
        int operationsInHistory = loanHistory.size();
        
        return String.format(
            "Estadísticas de la Biblioteca:\n" +
            "- Total de Libros: %d\n" +
            "- Libros Disponibles: %d\n" +
            "- Libros Prestados: %d\n" +
            "- Libros en Procesamiento: %d\n" +
            "- Operaciones en Historial: %d",
            totalBooks, availableBooks, loanedBooks, booksInQueue, operationsInHistory
        );
    }
    
    @Override
    public String toString() {
        return String.format("Biblioteca: %s - Libros: %d - En cola: %d - Operaciones: %d", 
            name, books.size(), bookQueue.size(), loanHistory.size());
    }
}