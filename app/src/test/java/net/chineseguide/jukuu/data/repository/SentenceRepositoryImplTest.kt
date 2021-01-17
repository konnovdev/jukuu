package net.chineseguide.jukuu.data.repository

import com.nhaarman.mockitokotlin2.*
import net.chineseguide.jukuu.data.converter.JukuuHtmlConverter
import net.chineseguide.jukuu.data.converter.UrlConverter
import net.chineseguide.jukuu.data.datasource.SentenceRemoteDataSource
import net.chineseguide.jukuu.domain.entity.Sentence
import org.jsoup.nodes.Document
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SentenceRepositoryImplTest {

    private companion object {

        const val JUKUU_SENTENCES_PER_PAGE = 10
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
        val expectedSentences = listOf<Sentence>()
        whenever(urlConverter.convert(query)).thenReturn(encodedQuery)
        whenever(sentenceRemoteDataSource.getFirstPage(encodedQuery)).thenReturn(document)
        whenever(jukuuHtmlConverter.convert(document)).thenReturn(expectedSentences)

        val result = sentenceRepository.get(query)

        verify(urlConverter).convert(query)
        verify(sentenceRemoteDataSource).getFirstPage(encodedQuery)
        verify(jukuuHtmlConverter).convert(document)
        assertEquals(expectedSentences, result)
    }

    @Test
    fun `WHEN get next EXPECT return custom page`() {
        val document: Document = mock()
        val query = "pet store"
        val encodedQuery = "pet+store"
        val expectedSentences = listOf<Sentence>()
        val page = 3
        val sentenceIndex = 38
        whenever(urlConverter.convert(query)).thenReturn(encodedQuery)
        whenever(sentenceRemoteDataSource.getCustomPage(encodedQuery, page)).thenReturn(document)
        whenever(jukuuHtmlConverter.convert(document)).thenReturn(expectedSentences)

        val result = sentenceRepository.getNext(query, sentenceIndex)

        verify(urlConverter).convert(query)
        verify(sentenceRemoteDataSource).getCustomPage(encodedQuery, page)
        verify(jukuuHtmlConverter).convert(document)
        assertEquals(expectedSentences, result)
    }

    @Test
    fun `WHEN get next with sentence index smaller than jukuu sentences per page EXPECT return empty list`() {
        val query = "pet store"
        val sentenceIndex = JUKUU_SENTENCES_PER_PAGE - 6

        val result = sentenceRepository.getNext(query, sentenceIndex)

        verify(urlConverter, never()).convert(query)
        verify(sentenceRemoteDataSource, never()).getCustomPage(any(), any())
        verify(jukuuHtmlConverter, never()).convert(any())
        assertEquals(listOf<Sentence>(), result)
    }

    @Test
    fun testPagination() {
        val document1: Document = mock()
        val document2: Document = mock()
        val query = "pet store"
        val encodedQuery = "pet+store"
        val expectedSentences1 = listOf<Sentence>()
        val expectedSentences2 = listOf<Sentence>()
        var page = 1
        var sentenceIndex = 10
        whenever(urlConverter.convert(query)).thenReturn(encodedQuery)
        whenever(sentenceRemoteDataSource.getCustomPage(encodedQuery, page)).thenReturn(document1)
        whenever(jukuuHtmlConverter.convert(document1)).thenReturn(expectedSentences1)

        val result1 = sentenceRepository.getNext(query, sentenceIndex)

        sentenceIndex = 15
        sentenceRepository.getNext(query, sentenceIndex)

        page = 2
        sentenceIndex = 20
        whenever(sentenceRemoteDataSource.getCustomPage(encodedQuery, page)).thenReturn(document2)
        whenever(jukuuHtmlConverter.convert(document2)).thenReturn(expectedSentences2)

        val result2 = sentenceRepository.getNext(query, sentenceIndex)

        verify(urlConverter, times(2)).convert(query)
        verify(sentenceRemoteDataSource).getCustomPage(encodedQuery, 1)
        verify(sentenceRemoteDataSource).getCustomPage(encodedQuery, 2)
        verify(jukuuHtmlConverter).convert(document1)
        verify(jukuuHtmlConverter).convert(document2)
        assertEquals(expectedSentences1, result1)
        assertEquals(expectedSentences2, result2)
        verifyNoMoreInteractions(urlConverter, sentenceRemoteDataSource, jukuuHtmlConverter)
    }
}