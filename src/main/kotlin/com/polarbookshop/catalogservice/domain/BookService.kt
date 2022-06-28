package com.polarbookshop.catalogservice.domain

import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {
    fun viewBookList(): Iterable<Book> {
        return bookRepository.findAll()
    }

    fun viewBookDetails(isbn: String): Book {
        return bookRepository.findByIsbn(isbn) ?: throw BookNotFoundException(isbn)
    }

    fun addBookToCatalog(book: Book): Book {
        if (bookRepository.existsByIsbn(book.isbn)) {
            throw BookAlreadyExistsException(book.isbn)
        }
        return bookRepository.save(book)
    }

    fun removeBookFromCatalog(isbn: String) {
        bookRepository.deleteByIsbn(isbn)
    }

    fun editBookDetails(isbn: String, book: Book): Book {
        return bookRepository.findByIsbn(isbn)
            ?.let {
//                val bookToUpdate = book.copy(isbn = it.isbn)
                val bookToUpdate = Book(it.isbn, book.title, book.author, book.price)
                bookRepository.save(bookToUpdate)
            } ?: addBookToCatalog(book)
    }
}