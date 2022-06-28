package com.polarbookshop.catalogservice.domain

class BookAlreadyExistsException(isbn: String) : RuntimeException("A book with ISBN $isbn already exists.")

class BookNotFoundException(isbn: String) : RuntimeException("The book with ISBN $isbn was not found.")