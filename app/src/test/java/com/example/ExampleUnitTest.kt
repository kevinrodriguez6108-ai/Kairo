package com.example

import com.example.data.model.Testament
import com.example.data.repository.BibleChapterRepository
import com.example.data.repository.BibleRepository
import com.example.data.repository.DailyQuoteRepository
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun testDailyQuoteRepositoryPredefinedVerses() {
    val quotes = DailyQuoteRepository.quotes
    assertTrue("Predefined quotes list should have at least 20 verses", quotes.size >= 20)

    for (quote in quotes) {
      assertTrue("Quote ID should not be blank", quote.id.isNotBlank())
      assertTrue("Quote text should not be blank", quote.text.isNotBlank())
      assertTrue("Quote reference should not be blank", quote.reference.isNotBlank())
      assertTrue("Quote book should not be blank", quote.book.isNotBlank())
      assertTrue("Quote category should not be blank", quote.category.isNotBlank())
      assertTrue("Quote reflection should not be blank", quote.reflection.isNotBlank())
      assertTrue("Quote short prayer should not be blank", quote.shortPrayer.isNotBlank())
    }

    val todayQuote = DailyQuoteRepository.getTodayQuote()
    assertNotNull(todayQuote)
    assertTrue(todayQuote.text.isNotBlank())

    val nextQuote = DailyQuoteRepository.getNextQuote(todayQuote.id)
    assertNotNull(nextQuote)
    assertNotEquals(todayQuote.id, nextQuote.id)

    val indexedQuote = DailyQuoteRepository.getQuoteByIndex(0)
    assertEquals(quotes[0].id, indexedQuote.id)
  }

  @Test
  fun testCuratedBibleChapter() {
    val filipenses = BibleRepository.catholicBooks.find { it.name == "Filipenses" }
    assertNotNull(filipenses)

    val chapter4 = BibleChapterRepository.getChapter(filipenses!!, 4)
    assertEquals(4, chapter4.chapterNumber)
    assertEquals("Filipenses", chapter4.bookName)
    assertTrue(chapter4.verses.isNotEmpty())
    assertTrue(chapter4.explanation.youthContext.isNotBlank())
    assertTrue(chapter4.explanation.keyTeachings.isNotEmpty())
    assertTrue(chapter4.explanation.youthPrayer.isNotBlank())
  }

  @Test
  fun testDeuterocanonicalBookChapters() {
    val tobias = BibleRepository.catholicBooks.find { it.name == "Tobías" }
    assertNotNull(tobias)
    assertTrue(tobias!!.isDeuterocanonical)
    assertEquals(14, tobias.chaptersCount)

    // Verify chapter 4 (Father Tobit's counsel)
    val chapter4 = BibleChapterRepository.getChapter(tobias, 4)
    assertEquals(4, chapter4.chapterNumber)
    assertTrue(chapter4.verses.any { it.text.contains("limosna", ignoreCase = true) })
    assertTrue(chapter4.explanation.reflectionQuestion.isNotBlank())
  }

  @Test
  fun testAllBooksChaptersSafeAccess() {
    // Verify that every single Catholic book can load chapter 1 safely without exceptions
    assertEquals(73, BibleRepository.catholicBooks.size)
    for (book in BibleRepository.catholicBooks) {
      val ch1 = BibleChapterRepository.getChapter(book, 1)
      assertEquals(1, ch1.chapterNumber)
      assertTrue(ch1.verses.isNotEmpty())
      assertTrue(ch1.explanation.mainIdea.isNotBlank())

      val chLast = BibleChapterRepository.getChapter(book, book.chaptersCount)
      assertEquals(book.chaptersCount, chLast.chapterNumber)
    }
  }
}
