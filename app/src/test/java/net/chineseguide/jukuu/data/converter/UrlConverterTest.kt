package net.chineseguide.jukuu.data.converter

import org.junit.Assert.assertEquals
import org.junit.Test

class UrlConverterTest {

    private val urlConverter = UrlConverter()

    @Test
    fun testConvertQueryToUrlFormat() {
        val unformattedQuery = "shop, 。rob it и got 一些 $, ，THEN wen聽 bar."
        val formattedQuery = "shop%2C+%E3%80%82rob+it+%D0%B8+got+%E4%B8%80%E4%BA%9B+%24%2C+%EF%BC%8CTHEN+wen%E8%81%BD+bar."

        val result = urlConverter.convert(unformattedQuery)

        assertEquals(formattedQuery, result)
    }
}