//interfaz que nos ayuda a definir un repositorio de JPA para libros

package com.trainibit.labs.spring_boot_jpa.repository;

import com.trainibit.labs.spring_boot_jpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //marca esto como un componente de acceso a datos.
public interface BookRepository extends JpaRepository<Book, Long> {
    // JpaRepository provides basic CRUD operations out of the box
    //proporciona métodos CRUD para Book.
}