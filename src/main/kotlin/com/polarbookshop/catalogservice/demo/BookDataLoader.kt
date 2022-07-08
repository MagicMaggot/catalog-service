package com.polarbookshop.catalogservice.demo

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
//@Profile("testdata")
@ConditionalOnProperty(name = ["polar.testdata"])
class BookDataLoader(private val bookRepository: BookRepository) {
    @EventListener(ApplicationReadyEvent::class)
    fun loadBookTestData() {
        println("Loading data...")
        bookRepository.deleteAll()
        val book1 = Book(
            "1234567891", "Northern Lights",
            "Lyra Silverstar", 9.90
        )
        val book2 = Book(
            "1234567892", "Polar Journey",
            "Iorek Polarson", 12.90
        )
        bookRepository.saveAll(listOf(book1, book2))
    }
}