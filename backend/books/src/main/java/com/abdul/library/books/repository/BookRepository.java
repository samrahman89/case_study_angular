package com.abdul.library.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdul.library.books.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

}
