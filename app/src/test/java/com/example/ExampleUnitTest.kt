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

  @Test
  fun testUserProfileAndLoginOptions() {
    val jovenMayor = com.example.data.model.UserProfile(
      fullName = "Juan Pablo Morales",
      gender = com.example.data.model.GenderIdentity.JOVEN,
      ageCategory = com.example.data.model.AgeCategory.MAYOR
    )
    assertEquals("Juan", jovenMayor.firstName)
    assertEquals("¡Bienvenido, Juan!", jovenMayor.personalizedGreeting)
    assertEquals("Varon • Mayor", jovenMayor.badgeSummary)

    val senoritaMenor = com.example.data.model.UserProfile(
      fullName = "María José López",
      gender = com.example.data.model.GenderIdentity.SENORITA,
      ageCategory = com.example.data.model.AgeCategory.MENOR
    )
    assertEquals("María", senoritaMenor.firstName)
    assertEquals("¡Bienvenida, María!", senoritaMenor.personalizedGreeting)
    assertEquals("Mujer • Menor", senoritaMenor.badgeSummary)
  }

  @Test
  fun testChallengesAndRankSystemEveryFiveChallenges() {
    val challenges = com.example.data.repository.ChallengeRepository.challenges
    assertEquals("There should be 55 progressive challenges across 11 rank-up stages", 55, challenges.size)
    assertEquals("There should be 12 rank tiers", 12, com.example.data.model.FaithRankTier.entries.size)
    assertEquals("There should be 10 special emblems", 10, com.example.data.repository.ChallengeRepository.specialEmblems.size)

    // Verify every 5th challenge is a BOSS_RANK_UP challenge and options are valid
    challenges.forEachIndexed { index, node ->
      assertEquals(index + 1, node.id)
      assertTrue(node.options.size == 4)
      assertTrue(node.correctOptionIndex in 0..3)
      if (node.id % 5 == 0) {
        assertEquals(com.example.data.model.ChallengeNodeType.BOSS_RANK_UP, node.type)
      }
    }

    // Verify all 3 categories requested by user are well represented:
    // 1) Designed around what is in the app (CONTENIDO_APP)
    // 2) History of Jesus up to the day He was crucified (HISTORIA_DE_JESUS)
    // 3) Fun & reasonable youth questions (JOVENES_DIVERTIDA)
    assertTrue(challenges.count { it.category == com.example.data.model.ChallengeCategory.CONTENIDO_APP } >= 15)
    assertTrue(challenges.count { it.category == com.example.data.model.ChallengeCategory.HISTORIA_DE_JESUS } >= 20)
    assertTrue(challenges.count { it.category == com.example.data.model.ChallengeCategory.JOVENES_DIVERTIDA } >= 10)

    // Verify rank progression every 5 challenges across all 12 ranks (Bronce -> Leyenda Kairós)
    assertEquals(com.example.data.model.FaithRankTier.BRONCE, com.example.data.model.FaithRankTier.fromCompletedChallenges(0))
    assertEquals(com.example.data.model.FaithRankTier.BRONCE, com.example.data.model.FaithRankTier.fromCompletedChallenges(4))
    assertEquals(com.example.data.model.FaithRankTier.PLATA, com.example.data.model.FaithRankTier.fromCompletedChallenges(5))
    assertEquals(com.example.data.model.FaithRankTier.ORO, com.example.data.model.FaithRankTier.fromCompletedChallenges(10))
    assertEquals(com.example.data.model.FaithRankTier.PLATINO, com.example.data.model.FaithRankTier.fromCompletedChallenges(15))
    assertEquals(com.example.data.model.FaithRankTier.DIAMANTE, com.example.data.model.FaithRankTier.fromCompletedChallenges(20))
    assertEquals(com.example.data.model.FaithRankTier.ESMERALDA, com.example.data.model.FaithRankTier.fromCompletedChallenges(25))
    assertEquals(com.example.data.model.FaithRankTier.ZAFIRO, com.example.data.model.FaithRankTier.fromCompletedChallenges(30))
    assertEquals(com.example.data.model.FaithRankTier.RUBI, com.example.data.model.FaithRankTier.fromCompletedChallenges(35))
    assertEquals(com.example.data.model.FaithRankTier.HEROICO, com.example.data.model.FaithRankTier.fromCompletedChallenges(40))
    assertEquals(com.example.data.model.FaithRankTier.MAESTRO, com.example.data.model.FaithRankTier.fromCompletedChallenges(45))
    assertEquals(com.example.data.model.FaithRankTier.GRAN_MAESTRO, com.example.data.model.FaithRankTier.fromCompletedChallenges(50))
    assertEquals(com.example.data.model.FaithRankTier.LEYENDA_KAIROS, com.example.data.model.FaithRankTier.fromCompletedChallenges(55))

    // Verify special emblems unlock progression
    assertEquals(0, com.example.data.repository.ChallengeRepository.getUnlockedEmblems(0).size)
    assertEquals(5, com.example.data.repository.ChallengeRepository.getUnlockedEmblems(25).size)
    assertEquals(10, com.example.data.repository.ChallengeRepository.getUnlockedEmblems(55).size)

    // Verify automatic replacement question rotation when a user gets one of the 5 questions wrong
    val initialNode1 = com.example.data.repository.ChallengeRepository.getChallengeById(1)
    val seenPrompts = mutableSetOf(initialNode1.promptOrQuestion)
    for (attempt in 1..6) {
      val replacement = com.example.data.repository.ChallengeRepository.getReplacementQuestion(
        originalChallengeId = 1,
        failedAttemptCount = attempt,
        alreadySeenPrompts = seenPrompts
      )
      assertEquals(1, replacement.id)
      assertTrue("Replacement question on wrong attempt #$attempt should be different from previously seen questions", !seenPrompts.contains(replacement.promptOrQuestion))
      seenPrompts.add(replacement.promptOrQuestion)
    }
  }
}


