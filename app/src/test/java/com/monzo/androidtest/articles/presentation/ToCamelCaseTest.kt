package com.monzo.androidtest.articles.presentation

import junit.framework.TestCase.assertEquals
import org.junit.Test


class ToCamelCaseTest {

    @Test
    fun `GIVEN value camelCase WHEN camelCase THEN correct value`() {
        val message = "fortuneFavorsTheBold"
        val expected = "fortuneFavorsTheBold"

        val result = message.toCamelCase()

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN value all upper case WHEN camelCase THEN correct value`() {
        val message = "fortune FAVORS TheBOLD"
        val expected = "fortuneFAVORSTheBOLD"

        val result = message.toCamelCase()

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN value with spaces WHEN camelCase THEN correct value`() {
        val message = "Fortune favors The bold"
        val expected = "fortuneFavorsTheBold"

        val result = message.toCamelCase()

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN value with underline WHEN camelCase THEN correct value`() {
        val message = "Fortune_favors_The_bold"
        val expected = "fortuneFavorsTheBold"

        val result = message.toCamelCase()

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN value with dash WHEN camelCase THEN correct value`() {
        val message = "Fortune-favors-The-bold"
        val expected = "fortuneFavorsTheBold"

        val result = message.toCamelCase()

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN value with mix WHEN camelCase THEN correct value`() {
        val message = "Fortune_favors The-boldExtra"
        val expected = "fortuneFavorsTheBoldExtra"

        val result = message.toCamelCase()

        assertEquals(expected, result)
    }
}