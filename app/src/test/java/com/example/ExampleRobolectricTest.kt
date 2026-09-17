package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Kairós", appName)
  }

  @Test
  fun `verify catholic bible has 73 books and 7 deuterocanonical books`() {
    val books = com.example.data.repository.BibleRepository.catholicBooks
    assertEquals(73, books.size)

    val deuterocanonicalBooks = books.filter { it.isDeuterocanonical }
    assertEquals(7, deuterocanonicalBooks.size)

    val deutNames = deuterocanonicalBooks.map { it.name }
    org.junit.Assert.assertTrue(deutNames.contains("Tobías"))
    org.junit.Assert.assertTrue(deutNames.contains("Judit"))
    org.junit.Assert.assertTrue(deutNames.contains("1 Macabeos"))
    org.junit.Assert.assertTrue(deutNames.contains("2 Macabeos"))
    org.junit.Assert.assertTrue(deutNames.contains("Sabiduría"))
    org.junit.Assert.assertTrue(deutNames.contains("Eclesiástico (Sirácida)"))
    org.junit.Assert.assertTrue(deutNames.contains("Baruc"))
  }

  @Test
  fun `verify bible passage search returns relevant youth passages`() {
    val peacePassages = com.example.data.repository.BibleRepository.searchPassages("paz")
    org.junit.Assert.assertTrue(peacePassages.isNotEmpty())

    val friendshipPassages = com.example.data.repository.BibleRepository.searchPassages("amigo")
    org.junit.Assert.assertTrue(friendshipPassages.isNotEmpty())

    val deutPassages = com.example.data.repository.BibleRepository.searchPassages("", "Deuterocanónicos")
    org.junit.Assert.assertTrue(deutPassages.isNotEmpty())
    org.junit.Assert.assertTrue(deutPassages.all { it.isDeuterocanonical })
  }
}
