package net.chineseguide.jukuu.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import net.chineseguide.jukuu.data.api.SentenceApi
import org.jsoup.nodes.Document
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SentenceRemoteDataSourceImplTest {

    private val sentenceApi: SentenceApi = mock()
    private val sentenceRemoteDataSource = SentenceRemoteDataSourceImpl(sentenceApi)

    @Test
    fun apiGetFirstPage() {
        val query = "sandwich"
        val expectedDocument: Document = mock()
        whenever(sentenceApi.getFirstPage(query)).thenReturn(expectedDocument)

        val result = sentenceRemoteDataSource.getFirstPage(query)

        verify(sentenceApi).getFirstPage(query)
        assertEquals(expectedDocument, result)
    }

    @Test
    fun apiGetCustomPage() {
        val query = "burger"
        val page = 2
        val expectedDocument: Document = mock()
        whenever(sentenceApi.getCustomPage(query, page)).thenReturn(expectedDocument)

        val result = sentenceRemoteDataSource.getCustomPage(query, page)

        verify(sentenceApi).getCustomPage(query, page)
        assertEquals(expectedDocument, result)
    }
}