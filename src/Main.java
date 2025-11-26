public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE BIBLIOTECA ===");
        System.out.println("Aplicación Java de consola iniciada.\n");
        
        // Crear una biblioteca
        Library library = new Library("Biblioteca Central");
        
        // Crear algunos libros
        Book book1 = new Book("El Quijote", "978-84-376-0494-7", "Miguel de Cervantes", 1605);
        Book book2 = new Book("Cien años de soledad", "978-84-376-0495-4", "Gabriel García Márquez", 1967);
        Book book3 = new Book("1984", "978-84-376-0496-1", "George Orwell", 1949);
        Book book4 = new Book("El amor en los tiempos del cólera", "978-84-376-0497-8", "Gabriel García Márquez", 1985);
        Book book5 = new Book("Rayuela", "978-84-376-0498-5", "Julio Cortázar", 1963);
        Book book6 = new Book("La ciudad y los perros", "978-84-376-0499-2", "Mario Vargas Llosa", 1962);
        
        // ===== TAREA N°3: PROCESAMIENTO DE LIBROS NUEVOS (COLA) =====
        System.out.println(" TAREA N°3: PROCESAMIENTO DE LIBROS NUEVOS");
        System.out.println("=============================================");
        
        // Agregar libros a la cola de procesamiento
        System.out.println("\n AGREGANDO LIBROS A COLA DE PROCESAMIENTO:");
        library.addBookToQueue(book1);
        library.addBookToQueue(book2);
        library.addBookToQueue(book3);
        library.addBookToQueue(book4);
        
        // Mostrar estado de la cola
        System.out.println("\n ESTADO COLA PROCESAMIENTO:");
        System.out.println("Libros en cola: " + library.getBookQueue().size());
        System.out.println("Siguiente libro a procesar: " + library.getBookQueue().peek().getTitle());
        
        // Procesar libros de la cola
        System.out.println("\n PROCESANDO LIBROS DE LA COLA:");
        library.processNextBookInQueue();
        library.processNextBookInQueue();
        library.processNextBookInQueue();
        
        // Agregar más libros a la cola
        library.addBookToQueue(book5);
        library.addBookToQueue(book6);
        
        // Procesar los restantes
        library.processNextBookInQueue();
        library.processNextBookInQueue();
        library.processNextBookInQueue(); 
        
        // ===== OPERACIONES BÁSICAS DE BIBLIOTECA =====
        System.out.println("\n OPERACIONES BÁSICAS DE BIBLIOTECA");
        System.out.println("=====================================");
        
        // Mostrar información de la biblioteca
        System.out.println("\n INFORMACIÓN DE LA BIBLIOTECA:");
        System.out.println(library);
        System.out.println("\n" + library.getStatistics());
        
        // Búsquedas
        System.out.println("\n BÚSQUEDA POR TÍTULO ('amor'):");
        LinkedList<Book> searchResults = library.findBooksByTitle("amor");
        for (int i = 0; i < searchResults.size(); i++) {
            System.out.println("  - " + searchResults.get(i));
        }
        
        System.out.println("\n BÚSQUEDA POR AUTOR ('García Márquez'):");
        LinkedList<Book> authorBooks = library.findBooksByAuthor("García Márquez");
        for (int i = 0; i < authorBooks.size(); i++) {
            System.out.println("  - " + authorBooks.get(i));
        }
        
        // ===== TAREA N°2: SISTEMA DE PRÉSTAMOS E HISTORIAL (PILA) =====
        System.out.println("\n TAREA N°2: SISTEMA DE PRÉSTAMOS E HISTORIAL");
        System.out.println("==============================================");
        
        // Realizar préstamos
        System.out.println("\n REALIZANDO PRÉSTAMOS:");
        Book foundBook = library.findBookByIsbn("978-84-376-0494-7");
        if (foundBook != null && library.lendBook(foundBook, "Juan Pérez")) {
            System.out.println(" Se prestó: " + foundBook.getTitle() + " a Juan Pérez");
        }
        
        foundBook = library.findBookByIsbn("978-84-376-0495-4");
        if (foundBook != null && library.lendBook(foundBook, "María García")) {
            System.out.println(" Se prestó: " + foundBook.getTitle() + " a María García");
        }
        
        foundBook = library.findBookByIsbn("978-84-376-0496-1");
        if (foundBook != null && library.lendBook(foundBook, "Carlos López")) {
            System.out.println(" Se prestó: " + foundBook.getTitle() + " a Carlos López");
        }
        
        // Mostrar estado después de préstamos
        System.out.println("\n ESTADO DESPUÉS DE PRÉSTAMOS:");
        System.out.println(library.getStatistics());
        
        // Mostrar historial de operaciones
        System.out.println("\n HISTORIAL DE OPERACIONES:");
        System.out.println("Operaciones registradas: " + library.getLoanHistory().size());
        try {
            System.out.println("Última operación: " + library.getLoanHistory().peek());
        } catch (Exception e) {
            System.out.println("No hay operaciones en el historial");
        }
        
        // Deshacer última operación
        System.out.println("\n DESHACIENDO ÚLTIMA OPERACIÓN:");
        if (library.getLoanHistory().undoLastLoan()) {
            System.out.println(" Operación deshecha exitosamente");
        }
        
        // Realizar una devolución
        System.out.println("\n REALIZANDO DEVOLUCIÓN:");
        foundBook = library.findBookByIsbn("978-84-376-0495-4");
        if (foundBook != null) {
            library.returnBook(foundBook, "María García");
            System.out.println(" Se devolvió: " + foundBook.getTitle());
        }
        
        // Mostrar estado final
        System.out.println("\n ESTADO FINAL:");
        System.out.println(library.getStatistics());
        
        System.out.println("\n LIBROS DISPONIBLES:");
        LinkedList<Book> available = library.getAvailableBooks();
        for (int i = 0; i < available.size(); i++) {
            System.out.println("  - " + available.get(i));
        }
        
        System.out.println("\n LIBROS PRESTADOS:");
        LinkedList<Book> loaned = library.getLoanedBooks();
        for (int i = 0; i < loaned.size(); i++) {
            System.out.println("  - " + loaned.get(i));
        }
        
        // ===== PRUEBAS ADICIONALES =====
        System.out.println("\n PRUEBAS ADICIONALES");
        System.out.println("======================");
        
        // Probar búsqueda por ISBN
        System.out.println("\n BÚSQUEDA POR ISBN ('978-84-376-0494-7'):");
        Book isbnBook = library.findBookByIsbn("978-84-376-0494-7");
        if (isbnBook != null) {
            System.out.println("  - Encontrado: " + isbnBook);
        } else {
            System.out.println("  - No encontrado");
        }
        
        // Probar eliminación de libro
        System.out.println("\n ELIMINANDO LIBRO:");
        if (library.removeBook(book3)) {
            System.out.println(" Libro eliminado: " + book3.getTitle());
        }
        
        // Estadísticas finales
        System.out.println("\n ESTADÍSTICAS FINALES COMPLETAS:");
        System.out.println(library.getStatistics());
        
        System.out.println("\n DEMOSTRACIÓN COMPLETADA - TODAS LAS TAREAS IMPLEMENTADAS");
        System.out.println(" Tarea N°1: LinkedList y Node implementadas");
        System.out.println(" Tarea N°2: Sistema de préstamos con historial (Pila)");
        System.out.println(" Tarea N°3: Procesamiento de libros nuevos (Cola)");
        System.out.println(" Tarea N°4: Pruebas del sistema completas");
    }
}