package net.chineseguide.jukuu.data.converter

import org.junit.Assert.assertEquals
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.Test

class JukuuHtmlConverterTest {

    private val jukuuHtmlConverter = JukuuHtmlConverter()

    @Test
    fun convertDocumentToSentences() {
        val englishSentence1 = "When to test: Summative and formative evaluations"
        val englishSentence2 = "Finally, personas and scenarios are used to test the validity of design ideas and assumptions throughout the process."
        val chineseSentence1 = "测试的时机为总结式和进展式评估"
        val chineseSentence2 = "最后，人物角色和场景剧本被用来在整个过程中测试设计思想和设计猜想的有效性。"

        val result = jukuuHtmlConverter.convert(getMockedDocument())

        assertEquals(englishSentence1, result.sentences[0].originalSentence)
        assertEquals(englishSentence2, result.sentences[1].originalSentence)
        assertEquals(chineseSentence1, result.sentences[0].translatedSentence)
        assertEquals(chineseSentence2, result.sentences[1].translatedSentence)
    }

    private fun getMockedDocument(): Document {
        val titleElementInnerHtml = "'test'的翻译、'test'的解释、双语例句、在线造句 - 句酷中英双语例句"
        val titleElement: Element = mock {
            on { html() } doReturn titleElementInnerHtml
        }
        val headElement: Element = mock {
            on { getElementsByTag("title") } doReturn Elements(titleElement)
        }
        val englishElement1: Element = mock {
            on { html() } doReturn "<td valign=\"top\">1. </td>\n<td> When to <b>test</b>: Summative and formative evaluations </td>"
        }
        val englishElement2: Element = mock {
            on { html() } doReturn "<td valign=\"top\">2. </td>\n<td> Finally, personas and scenarios are used to <b>test</b> the validity of design ideas and assumptions throughout the process. </td>"
        }
        val chineseElement1: Element = mock {
            on { html() } doReturn "<td></td>\n<td> 测试的时机为总结式和进展式评估 </td>"
        }
        val chineseElement2: Element = mock {
            on { html() } doReturn "<td></td>\n<td> 最后，人物角色和场景剧本被用来在整个过程中测试设计思想和设计猜想的有效性。 </td>"
        }
        val bodyElement: Element = mock {
            on { getElementsByClass("e") } doReturn Elements(englishElement1, englishElement2)
            on { getElementsByClass("c") } doReturn Elements(chineseElement1, chineseElement2)
        }

        val document: Document = mock {
            on { head() } doReturn headElement
            on { body() } doReturn bodyElement
        }
        return document
    }
}