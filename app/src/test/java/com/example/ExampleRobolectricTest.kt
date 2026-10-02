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
    assertEquals(9, com.example.ui.components.CatholicEssentialsRepository.items.size)
    assertEquals(24, com.example.data.repository.ContentRepository.dilemmas.size)
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

  @Test
  fun `verify cinematic launch stage, login options, persistent session, and registered users count`() {
    val app = ApplicationProvider.getApplicationContext<android.app.Application>()
    // Clear preferences before test
    app.getSharedPreferences("kairos_user_prefs", Context.MODE_PRIVATE).edit().clear().commit()

    val viewModel = com.example.ui.viewmodel.MainViewModel(app)

    // 1. First launch (unregistered): App starts in 10s cinematic stage with 0 registered users
    assertEquals(com.example.data.model.AppLaunchStage.CINEMATIC, viewModel.appLaunchStage.value)
    assertEquals(0, viewModel.registeredUsersCount.value)

    // 2. Completing cinematic moves to Login stage
    viewModel.completeCinematic()
    assertEquals(com.example.data.model.AppLaunchStage.LOGIN, viewModel.appLaunchStage.value)

    // 3. Selecting Full Name, Joven/Señorita, Mayor/Menor, Profile Photo and submitting login
    viewModel.updateLoginFullName("Carlos Eduardo Ramírez")
    viewModel.selectLoginGender(com.example.data.model.GenderIdentity.JOVEN)
    viewModel.selectLoginAgeCategory(com.example.data.model.AgeCategory.MAYOR)
    viewModel.updateLoginPhotoUri("/data/user/0/com.example/files/profile_photos/carlos_gallery.jpg")

    val loginSuccess = viewModel.submitLogin()
    org.junit.Assert.assertTrue(loginSuccess)
    assertEquals(com.example.data.model.AppLaunchStage.MAIN_APP, viewModel.appLaunchStage.value)
    assertEquals("Carlos Eduardo Ramírez", viewModel.userProfile.value?.fullName)
    assertEquals(com.example.data.model.GenderIdentity.JOVEN, viewModel.userProfile.value?.gender)
    assertEquals(com.example.data.model.AgeCategory.MAYOR, viewModel.userProfile.value?.ageCategory)
    assertEquals("/data/user/0/com.example/files/profile_photos/carlos_gallery.jpg", viewModel.userProfile.value?.photoUri)
    org.junit.Assert.assertTrue(viewModel.userProfile.value?.hasPhoto == true)
    assertEquals(1, viewModel.registeredUsersCount.value)

    // Updating profile photo directly from Profile Dialog (e.g., taking a new camera photo)
    viewModel.updateCurrentUserProfilePhoto("/data/user/0/com.example/files/profile_photos/carlos_camera.jpg")
    assertEquals("/data/user/0/com.example/files/profile_photos/carlos_camera.jpg", viewModel.userProfile.value?.photoUri)

    // 4. Simulate closing the app and reopening it (new ViewModel instance):
    // Should NOT ask for registration again (starts directly in MAIN_APP) and keeps count = 1 and saved photo
    val reopenedViewModel = com.example.ui.viewmodel.MainViewModel(app)
    assertEquals(com.example.data.model.AppLaunchStage.MAIN_APP, reopenedViewModel.appLaunchStage.value)
    assertEquals("Carlos Eduardo Ramírez", reopenedViewModel.userProfile.value?.fullName)
    assertEquals("/data/user/0/com.example/files/profile_photos/carlos_camera.jpg", reopenedViewModel.userProfile.value?.photoUri)
    assertEquals(1, reopenedViewModel.registeredUsersCount.value)

    // 5. Registering a second person increments total registered users count to 2
    reopenedViewModel.startNewPersonRegistration()
    assertEquals(com.example.data.model.AppLaunchStage.LOGIN, reopenedViewModel.appLaunchStage.value)
    org.junit.Assert.assertNull(reopenedViewModel.loginPhotoUri.value)
    reopenedViewModel.updateLoginFullName("Lucía Fernanda Torres")
    reopenedViewModel.selectLoginGender(com.example.data.model.GenderIdentity.SENORITA)
    reopenedViewModel.selectLoginAgeCategory(com.example.data.model.AgeCategory.MENOR)
    reopenedViewModel.updateLoginPhotoUri("/data/user/0/com.example/files/profile_photos/lucia_gallery.jpg")
    org.junit.Assert.assertTrue(reopenedViewModel.submitLogin())
    assertEquals(2, reopenedViewModel.registeredUsersCount.value)
    assertEquals(2, reopenedViewModel.registeredUsersList.value.size)
    assertEquals("/data/user/0/com.example/files/profile_photos/lucia_gallery.jpg", reopenedViewModel.userProfile.value?.photoUri)

    // 6. Deleting the current account ("Lucía Fernanda Torres") decrements count to 1 and switches back to "Carlos Eduardo Ramírez" with his photo
    reopenedViewModel.deleteCurrentAccount()
    assertEquals(1, reopenedViewModel.registeredUsersCount.value)
    assertEquals(1, reopenedViewModel.registeredUsersList.value.size)
    assertEquals("Carlos Eduardo Ramírez", reopenedViewModel.userProfile.value?.fullName)
    assertEquals("/data/user/0/com.example/files/profile_photos/carlos_camera.jpg", reopenedViewModel.userProfile.value?.photoUri)
    assertEquals(com.example.data.model.AppLaunchStage.MAIN_APP, reopenedViewModel.appLaunchStage.value)

    // 7. Deleting the last remaining account resets registered count to 0 and returns to LOGIN screen
    reopenedViewModel.deleteCurrentAccount()
    assertEquals(0, reopenedViewModel.registeredUsersCount.value)
    org.junit.Assert.assertNull(reopenedViewModel.userProfile.value)
    assertEquals(com.example.data.model.AppLaunchStage.LOGIN, reopenedViewModel.appLaunchStage.value)

    // 8. Verify Dark Mode toggle
    val initialDark = reopenedViewModel.isDarkMode.value
    reopenedViewModel.toggleDarkMode()
    assertEquals(!initialDark, reopenedViewModel.isDarkMode.value)
  }

  @Test
  fun `verify 5 questions per day limit, anti-copy lock preventing return to home, and rank progression`() {
    val app = ApplicationProvider.getApplicationContext<android.app.Application>()
    app.getSharedPreferences("kairos_user_prefs", Context.MODE_PRIVATE).edit().clear().commit()

    val viewModel = com.example.ui.viewmodel.MainViewModel(app)
    assertEquals(0, viewModel.completedChallengesCount.value)
    assertEquals(0, viewModel.dailyQuestionsCompletedToday.value)
    assertEquals(com.example.data.model.FaithRankTier.BRONCE, viewModel.currentFaithRank.value)
    assertEquals(com.example.ui.viewmodel.Screen.Home, viewModel.currentScreen.value)

    // 1. User enters the Challenges section -> NOT locked yet, can freely go back or move to another page
    viewModel.navigateTo(com.example.ui.viewmodel.Screen.Challenges)
    assertEquals(com.example.ui.viewmodel.Screen.Challenges, viewModel.currentScreen.value)
    org.junit.Assert.assertFalse(viewModel.isChallengeAntiCopyLocked.value)

    viewModel.navigateTo(com.example.ui.viewmodel.Screen.Home)
    assertEquals(com.example.ui.viewmodel.Screen.Home, viewModel.currentScreen.value)
    viewModel.navigateTo(com.example.ui.viewmodel.Screen.Challenges)
    assertEquals(com.example.ui.viewmodel.Screen.Challenges, viewModel.currentScreen.value)

    // 2. When the user enters a specific question, navigation IS locked until the question is solved
    viewModel.startChallengeQuestion(1)
    org.junit.Assert.assertTrue(viewModel.isChallengeAntiCopyLocked.value)

    viewModel.navigateTo(com.example.ui.viewmodel.Screen.Home)
    assertEquals(com.example.ui.viewmodel.Screen.Challenges, viewModel.currentScreen.value)
    org.junit.Assert.assertTrue(viewModel.antiCopyWarningShown.value)

    viewModel.navigateTo(com.example.ui.viewmodel.Screen.Bible)
    assertEquals(com.example.ui.viewmodel.Screen.Challenges, viewModel.currentScreen.value)

    // 3. Complete first 4 questions of the day -> Each question locks when started and unlocks when solved
    for (nodeId in 1..4) {
      viewModel.startChallengeQuestion(nodeId)
      org.junit.Assert.assertTrue(viewModel.isChallengeAntiCopyLocked.value)
      org.junit.Assert.assertTrue(viewModel.completeChallengeNode(nodeId, 25))
      org.junit.Assert.assertFalse(viewModel.isChallengeAntiCopyLocked.value)
    }
    assertEquals(4, viewModel.completedChallengesCount.value)
    assertEquals(4, viewModel.dailyQuestionsCompletedToday.value)
    assertEquals(com.example.data.model.FaithRankTier.BRONCE, viewModel.currentFaithRank.value)

    // 4. Start 5th question -> locked while inside question, then solving it ranks up to PLATA and unlocks
    viewModel.startChallengeQuestion(5)
    org.junit.Assert.assertTrue(viewModel.isChallengeAntiCopyLocked.value)
    org.junit.Assert.assertTrue(viewModel.completeChallengeNode(5, 40))
    assertEquals(5, viewModel.completedChallengesCount.value)
    assertEquals(5, viewModel.dailyQuestionsCompletedToday.value)
    assertEquals(com.example.data.model.FaithRankTier.PLATA, viewModel.currentFaithRank.value)
    assertEquals(com.example.data.model.FaithRankTier.PLATA, viewModel.rankUpCelebrationTier.value)
    org.junit.Assert.assertFalse(viewModel.isChallengeAntiCopyLocked.value)

    // 5. Attempting a 6th question on the same day is blocked by the 5-questions-per-day limit
    org.junit.Assert.assertFalse(viewModel.completeChallengeNode(6, 40))
    assertEquals(5, viewModel.completedChallengesCount.value)

    // 6. Now that all 5 questions of the day are completed, the user CAN return to the Home menu
    viewModel.navigateTo(com.example.ui.viewmodel.Screen.Home)
    assertEquals(com.example.ui.viewmodel.Screen.Home, viewModel.currentScreen.value)

    viewModel.dismissRankUpCelebration()
    org.junit.Assert.assertNull(viewModel.rankUpCelebrationTier.value)

    // Wrong attempt resets streak and automatically rotates to a new question successively
    val initialQ1 = viewModel.getActiveQuestionForNode(1)
    val rotatedQ1First = viewModel.recordWrongChallengeAttempt(1)
    assertEquals(0, viewModel.challengeStreak.value)
    assertEquals(1, viewModel.wrongAttemptsByNode.value[1])
    org.junit.Assert.assertNotEquals(initialQ1.promptOrQuestion, rotatedQ1First.promptOrQuestion)

    val rotatedQ1Second = viewModel.recordWrongChallengeAttempt(1)
    assertEquals(2, viewModel.wrongAttemptsByNode.value[1])
    org.junit.Assert.assertNotEquals(rotatedQ1First.promptOrQuestion, rotatedQ1Second.promptOrQuestion)
    assertEquals(rotatedQ1Second.promptOrQuestion, viewModel.getActiveQuestionForNode(1).promptOrQuestion)

    // 7. Complete remaining stages across subsequent days (5 questions per day) up to LEYENDA_KAIROS (55)
    for (day in 2..11) {
      viewModel.advanceToNextChallengeDay("2026-10-day-$day")
      assertEquals(0, viewModel.dailyQuestionsCompletedToday.value)
      val startNode = (day - 1) * 5 + 1
      val endNode = day * 5
      for (nodeId in startNode..endNode) {
        org.junit.Assert.assertTrue(viewModel.completeChallengeNode(nodeId, 50))
      }
      assertEquals(5, viewModel.dailyQuestionsCompletedToday.value)
    }
    assertEquals(55, viewModel.completedChallengesCount.value)
    assertEquals(com.example.data.model.FaithRankTier.LEYENDA_KAIROS, viewModel.currentFaithRank.value)
  }

  @Test
  fun testCreatorMessagesAndPassword6108() {
    val app = androidx.test.core.app.ApplicationProvider.getApplicationContext<android.app.Application>()
    app.getSharedPreferences("kairos_user_prefs", android.content.Context.MODE_PRIVATE).edit().clear().commit()

    val viewModel = com.example.ui.viewmodel.MainViewModel(app)
    viewModel.updateLoginFullName("Andrés Felipe Gómez")
    viewModel.selectLoginGender(com.example.data.model.GenderIdentity.JOVEN)
    viewModel.selectLoginAgeCategory(com.example.data.model.AgeCategory.MAYOR)
    org.junit.Assert.assertTrue(viewModel.submitLogin())
    org.junit.Assert.assertFalse(viewModel.userProfile.value?.isCreatorAccount == true)

    // Send a message to the creator
    org.junit.Assert.assertTrue(viewModel.sendCreatorMessage("Muchas gracias por crear Kairós, me ayudó mucho hoy."))
    assertEquals(1, viewModel.creatorMessages.value.size)
    assertEquals("Andrés Felipe Gómez", viewModel.creatorMessages.value.first().senderName)
    assertEquals("Muchas gracias por crear Kairós, me ayudó mucho hoy.", viewModel.creatorMessages.value.first().messageText)

    // Regular user cannot open creator inbox dialog
    viewModel.openCreatorInboxDialog()
    org.junit.Assert.assertFalse(viewModel.showCreatorInboxDialog.value)

    // Another user trying to enter with the SAME name as an existing user ("Andrés Felipe Gómez") is blocked with notification
    viewModel.startNewPersonRegistration()
    viewModel.updateLoginFullName("andres felipe gomez")
    viewModel.selectLoginGender(com.example.data.model.GenderIdentity.JOVEN)
    viewModel.selectLoginAgeCategory(com.example.data.model.AgeCategory.MAYOR)
    org.junit.Assert.assertFalse(viewModel.submitLogin())
    org.junit.Assert.assertTrue(
      viewModel.loginDuplicateUserNotification.value?.contains("ya está registrado") == true
    )

    // Another user trying to enter with the creator's name ("Kevin Abraham") is blocked with notification
    viewModel.updateLoginFullName("Kevin Abraham")
    viewModel.selectLoginGender(com.example.data.model.GenderIdentity.JOVEN)
    viewModel.selectLoginAgeCategory(com.example.data.model.AgeCategory.MAYOR)
    org.junit.Assert.assertFalse(viewModel.submitLogin())
    org.junit.Assert.assertTrue(
      viewModel.loginDuplicateUserNotification.value?.contains("ya está registrado") == true
    )

    // Real creator authorizes with 6108
    org.junit.Assert.assertTrue(viewModel.submitLogin(creatorAuthPassword = "6108"))
    org.junit.Assert.assertTrue(viewModel.userProfile.value?.isCreatorAccount == true)

    // Creator can open the creator inbox dialog: starts locked
    viewModel.openCreatorInboxDialog()
    org.junit.Assert.assertTrue(viewModel.showCreatorInboxDialog.value)
    org.junit.Assert.assertFalse(viewModel.isCreatorInboxUnlocked.value)

    // Wrong password does not unlock
    org.junit.Assert.assertFalse(viewModel.unlockCreatorInbox("1234"))
    org.junit.Assert.assertFalse(viewModel.isCreatorInboxUnlocked.value)

    // Correct password 6108 unlocks the inbox
    org.junit.Assert.assertTrue(viewModel.unlockCreatorInbox("6108"))
    org.junit.Assert.assertTrue(viewModel.isCreatorInboxUnlocked.value)

    // Creator replies to the user's message
    val messageId = viewModel.creatorMessages.value.first().id
    org.junit.Assert.assertTrue(
      viewModel.replyToCreatorMessage(messageId, "¡Gracias Andrés! Dios te bendiga siempre.")
    )
    assertEquals(
      "¡Gracias Andrés! Dios te bendiga siempre.",
      viewModel.creatorMessages.value.first().creatorReplyText
    )
    org.junit.Assert.assertTrue(viewModel.creatorMessages.value.first().hasCreatorReply)
    viewModel.dismissCreatorInboxDialog()

    // Before the user sees the reply, replyFirstSeenAtMillis is null and it does not expire
    val seenTime = System.currentTimeMillis()
    org.junit.Assert.assertNull(viewModel.creatorMessages.value.first().replyFirstSeenAtMillis)
    viewModel.purgeExpiredCreatorMessages(seenTime + 48L * 3600_000L)
    assertEquals(1, viewModel.creatorMessages.value.size)

    // User opens their messages inbox directly WITHOUT password and sees the creator's reply
    val andresProfile = viewModel.registeredUsersList.value.first { it.fullName == "Andrés Felipe Gómez" }
    viewModel.switchActiveAccount(andresProfile)
    viewModel.openUserMessagesDialog(nowMillis = seenTime)
    org.junit.Assert.assertTrue(viewModel.showUserMessagesDialog.value)
    assertEquals(seenTime, viewModel.creatorMessages.value.first().replyFirstSeenAtMillis)

    // 12 hours after viewing: message is still present (12 hours remaining)
    val twelveHoursLater = seenTime + 12L * 3600_000L
    viewModel.purgeExpiredCreatorMessages(twelveHoursLater)
    assertEquals(1, viewModel.creatorMessages.value.size)
    assertEquals(12, viewModel.creatorMessages.value.first().remainingHoursUntilDeletion(twelveHoursLater))

    // Verify messages and creator replies persist across app restarts before 24h
    val reopenedViewModel = com.example.ui.viewmodel.MainViewModel(app)
    assertEquals(1, reopenedViewModel.creatorMessages.value.size)
    assertEquals("Andrés Felipe Gómez", reopenedViewModel.creatorMessages.value.first().senderName)
    assertEquals(
      "¡Gracias Andrés! Dios te bendiga siempre.",
      reopenedViewModel.creatorMessages.value.first().creatorReplyText
    )

    // 24 hours after the user saw the creator's reply: message is automatically deleted
    val twentyFourHoursLater = seenTime + com.example.data.model.CreatorMessage.REPLY_EXPIRATION_DURATION_MS
    reopenedViewModel.purgeExpiredCreatorMessages(twentyFourHoursLater)
    assertEquals(0, reopenedViewModel.creatorMessages.value.size)
  }
}






