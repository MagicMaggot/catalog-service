package com.polarbookshop.catalogservice.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import javax.validation.Validation
import javax.validation.Validator

class BookValidationTests {

    companion object {
        private lateinit var validator: Validator

        @BeforeAll
        @JvmStatic
        fun setUp() {
            val factory = Validation.buildDefaultValidatorFactory()
            validator = factory.validator
        }
    }

    @Test
    fun whenAllFieldsCorrectThenValidationSucceeds() {
        val book = Book("1234567890", "Title", "Author", 9.90)
        val violations = validator.validate(book)
        Assertions.assertThat(violations).isEmpty()
    }

    @Test
    fun whenIsbnDefinedButIncorrectThenValidationFails() {
        val book = Book("a234567890", "Title", "Author", 9.90)
        val violations = validator.validate(book)
        Assertions.assertThat(violations).hasSize(1)
        Assertions.assertThat(violations.first().message).isEqualTo("The ISBN format must be valid.")
    }
}