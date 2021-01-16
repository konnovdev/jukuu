package net.chineseguide.jukuu.data.repository

import com.nhaarman.mockitokotlin2.*
import net.chineseguide.jukuu.data.converter.JukuuHtmlConverter
import net.chineseguide.jukuu.data.converter.UrlConverter
import net.chineseguide.jukuu.data.datasource.SentenceRemoteDataSource
import net.chineseguide.jukuu.domain.entity.SentenceCollection
import org.jsoup.nodes.Document
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SentenceRepositoryImplTest {

    private companion object {

        const val JUKUU_SENTENCES_PER_PAGE = 10
        val DOWNLOAD_NOT_NEEDED = null
    }

    private val sentenceRemoteDataSource: SentenceRemoteDataSource = mock()
    private val urlConverter: UrlConverter = mock()
    private val jukuuHtmlConverter: JukuuHtmlConverter = mock()

    private val sentenceRepository = SentenceRepositoryImpl(
        sentenceRemoteDataSource,
        urlConverter,
        jukuuHtmlConverter
    )

    @Test
    fun `WHEN get EXPECT return first page`() {
        val document: Document = mock()
        val query = "pet store"
        val encodedQuery = "pet+store"
        val expectedSentenceCollection = SentenceCollection(
            "id",
            "pet store",
            mutableListOf()
        )
        whenever(urlConverter.convert(query)).thenReturn(encodedQuery)
        whenever(sentenceRemoteDataSource.getFirstPage(encodedQuery)).thenReturn(document)
        whenever(jukuuHtmlConverter.convert(document)).thenReturn(expectedSentenceCollection)

        val result = sentenceRepository.get(query)

        verify(urlConverter).convert(query)
        verify(sentenceRemoteDataSource).getFirstPage(encodedQuery)
        verify(jukuuHtmlConverter).convert(document)
        assertEquals(expectedSentenceCollection, result)
    }

    @Test
    fun `WHEN get next EXPECT return custom page`() {
        val document: Document = mock()
        val query = "pet store"
        val encodedQuery = "pet+store"
        val expectedSentenceCollection = SentenceCollection(
            "id",
            "pet store",
            mutableListOf()
        )
        val page = 3
        val sentenceIndex = 38
        whenever(urlConverter.convert(query)).thenReturn(encodedQuery)
        whenever(sentenceRemoteDataSource.getCustomPage(encodedQuery, page)).thenReturn(document)
        whenever(jukuuHtmlConverter.convert(document)).thenReturn(expectedSentenceCollection)

        val result = sentenceRepository.getNext(query, sentenceIndex)

        verify(urlConverter).convert(query)
        verify(sentenceRemoteDataSource).getCustomPage(encodedQuery, page)
        verify(jukuuHtmlConverter).convert(document)
        assertEquals(expectedSentenceCollection, result)
    }

    @Test
    fun `WHEN get next with sentence index smaller than jukuu sentences per page EXPECT return download not needed`() {
        val query = "pet store"
        val sentenceIndex = JUKUU_SENTENCES_PER_PAGE - 6

        val result = sentenceRepository.getNext(query, sentenceIndex)

        verify(urlConverter, never()).convert(query)
        verify(sentenceRemoteDataSource, never()).getCustomPage(any(), any())
        verify(jukuuHtmlConverter, never()).convert(any())
        assertEquals(DOWNLOAD_NOT_NEEDED, result)
    }

    @Test
    fun testPagination() {
        val document1: Document = mock()
        val document2: Document = mock()
        val query = "pet store"
        val encodedQuery = "pet+store"
        val expectedSentenceCollection1 = SentenceCollection(
            "id",
            "pet store",
            mutableListOf()
        )
        val expectedSentenceCollection2 = SentenceCollection(
            "id2",
            "pet store",
            mutableListOf()
        )
        var page = 1
        var sentenceIndex = 10
        whenever(urlConverter.convert(query)).thenReturn(encodedQuery)
        whenever(sentenceRemoteDataSource.getCustomPage(encodedQuery, page)).thenReturn(document1)
        whenever(jukuuHtmlConverter.convert(document1)).thenReturn(expectedSentenceCollection1)

        val result1 = sentenceRepository.getNext(query, sentenceIndex)

        sentenceIndex = 15
        sentenceRepository.getNext(query, sentenceIndex)

        page = 2
        sentenceIndex = 20
        whenever(sentenceRemoteDataSource.getCustomPage(encodedQuery, page)).thenReturn(document2)
        whenever(jukuuHtmlConverter.convert(document2)).thenReturn(expectedSentenceCollection2)

        val result2 = sentenceRepository.getNext(query, sentenceIndex)

        verify(urlConverter, times(2)).convert(query)
        verify(sentenceRemoteDataSource).getCustomPage(encodedQuery, 1)
        verify(sentenceRemoteDataSource).getCustomPage(encodedQuery, 2)
        verify(jukuuHtmlConverter).convert(document1)
        verify(jukuuHtmlConverter).convert(document2)
        assertEquals(expectedSentenceCollection1, result1)
        assertEquals(expectedSentenceCollection2, result2)
        verifyNoMoreInteractions(urlConverter, sentenceRemoteDataSource, jukuuHtmlConverter)
    }
}