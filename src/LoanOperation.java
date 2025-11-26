public class LoanOperation {
    private Book book;
    private String user;
    private String operationType;
    private String timestamp;
    
    public LoanOperation(Book book, String user, String operationType) {
        this.book = book;
        this.user = user;
        this.operationType = operationType;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }
    
    public Book getBook() { 
        return book; 
    }
    
    public String getUser() { 
        return user; 
    }
    
    public String getOperationType() { 
        return operationType; 
    }
    
    public String getTimestamp() { 
        return timestamp; 
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s por %s (%s)", 
            timestamp, operationType, book.getTitle(), user);
    }
}