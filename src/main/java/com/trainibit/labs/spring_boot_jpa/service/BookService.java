//interfaz que nos ayuda a definir metodos para las operaciones CRUD usando el repositorio de JPA

package com.trainibit.labs.spring_boot_jpa.service;

import com.trainibit.labs.spring_boot_jpa.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book saveBook(Book book);
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book updateBook(Long id, Book bookDetails);
    void deleteBook(Long id);
}