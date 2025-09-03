package com.trainibit.labs.spring_boot_jpa.controller;


import com.trainibit.labs.spring_boot_jpa.model.Book;
import com.trainibit.labs.spring_boot_jpa.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    // Create a new book

    @PostMapping("/addBook")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        logger.info("Crear nuevo libro: {}", book);
        Book savedBook = bookService.saveBook(book);
        logger.info("Libro guardado con el id: {}", savedBook.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    // Get all books
    @GetMapping("/getBooks")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        logger.info("Obtener lista de libros");
        return bookService.getAllBooks();
    }

    // Get a book by ID
    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        logger.info("Obtener el libro id {}", id);

        Optional<Book> book = bookService.getBookById(id);

        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a book
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        try {
            Optional<Book> book = bookService.getBookById(id);

            if (book.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Book libroExistente = book.get();
            libroExistente.setTitle(bookDetails.getTitle());
            libroExistente.setAuthor(bookDetails.getAuthor());
            libroExistente.setPrice(bookDetails.getPrice());

            Book libroActualizado = bookService.saveBook(libroExistente);
            return ResponseEntity.ok(libroActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a book
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.getBookById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}