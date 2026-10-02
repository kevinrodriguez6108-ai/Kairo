package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.JournalCategory
import com.example.data.local.JournalEntry
import com.example.data.local.JournalRepository
import com.example.data.model.AgeCategory
import com.example.data.model.AppLaunchStage
import com.example.data.model.ChallengeNode
import com.example.data.model.CreatorMessage
import com.example.data.model.DailyQuote
import com.example.data.model.DailySpark
import com.example.data.model.Dilemma
import com.example.data.model.FaithRankTier
import com.example.data.model.GenderIdentity
import com.example.data.model.PrayerItem
import com.example.data.model.SacramentType
import com.example.data.model.UserProfile
import com.example.data.repository.ChallengeRepository
import com.example.data.repository.ContentRepository
import com.example.data.repository.DailyQuoteRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen(val title: String) {
    object Home : Screen("Hoy")
    object Challenges : Screen("Retos")
    object Sacraments : Screen("Sacramentos")
    object Dilemmas : Screen("Dilemas")
    object Bible : Screen("Biblia")
    object Journal : Screen("Mi Diario")
    object Prayers : Screen("Oraciones")
}

data class CalmTimerState(
    val isRunning: Boolean = false,
    val secondsLeft: Int = 120, // 2 minutes
    val totalSeconds: Int = 120,
    val isCompleted: Boolean = false,
    val currentPrompt: String = "Inhala paz... Exhala prisa..."
)

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: JournalRepository
    private val prefs = application.getSharedPreferences("kairos_user_prefs", Context.MODE_PRIVATE)

    // Cargar perfil guardado previamente (si ya se registró antes de cerrar la app)
    private val savedName = (prefs.getString("user_full_name", "") ?: "").trim()
    private val savedGender = GenderIdentity.fromStoredString(prefs.getString("user_gender", null))
    private val savedAgeCategory = AgeCategory.fromStoredString(prefs.getString("user_age_category", null))
    private val savedPhotoUri = prefs.getString("user_photo_uri", null)?.trim()?.takeIf { it.isNotBlank() }

    private val initialProfileFromPrefs: UserProfile? =
        if (savedName.isNotBlank()) {
            UserProfile(
                fullName = savedName,
                gender = savedGender ?: GenderIdentity.JOVEN,
                ageCategory = savedAgeCategory ?: AgeCategory.MAYOR,
                photoUri = savedPhotoUri
            )
        } else {
            null
        }

    // Historial y contador total de personas registradas en la app
    private val _registeredUsersList = MutableStateFlow(loadRegisteredUsersFromPrefs(initialProfileFromPrefs))
    val registeredUsersList: StateFlow<List<UserProfile>> = _registeredUsersList.asStateFlow()

    private val initialProfile: UserProfile? =
        initialProfileFromPrefs ?: _registeredUsersList.value.firstOrNull()

    // Si ya está registrado, al volver a abrir la app entra directo a MAIN_APP sin pedir registro de nuevo
    private val _appLaunchStage = MutableStateFlow(
        if (initialProfile != null) AppLaunchStage.MAIN_APP else AppLaunchStage.CINEMATIC
    )
    val appLaunchStage: StateFlow<AppLaunchStage> = _appLaunchStage.asStateFlow()

    // Datos del formulario de Inicio de Sesión / Registro
    private val _loginFullName = MutableStateFlow(initialProfile?.fullName ?: savedName)
    val loginFullName: StateFlow<String> = _loginFullName.asStateFlow()

    private val _loginGender = MutableStateFlow(initialProfile?.gender ?: savedGender)
    val loginGender: StateFlow<GenderIdentity?> = _loginGender.asStateFlow()

    private val _loginAgeCategory = MutableStateFlow(initialProfile?.ageCategory ?: savedAgeCategory)
    val loginAgeCategory: StateFlow<AgeCategory?> = _loginAgeCategory.asStateFlow()

    private val _loginPhotoUri = MutableStateFlow<String?>(initialProfile?.photoUri ?: savedPhotoUri)
    val loginPhotoUri: StateFlow<String?> = _loginPhotoUri.asStateFlow()

    private val _userProfile = MutableStateFlow(initialProfile)
    val userProfile: StateFlow<UserProfile?> = _userProfile.asStateFlow()

    private val _registeredUsersCount = MutableStateFlow(
        prefs.getInt("registered_users_count", _registeredUsersList.value.size)
            .coerceAtLeast(_registeredUsersList.value.size)
    )
    val registeredUsersCount: StateFlow<Int> = _registeredUsersCount.asStateFlow()

    private val _showRegisteredUsersDialog = MutableStateFlow(false)
    val showRegisteredUsersDialog: StateFlow<Boolean> = _showRegisteredUsersDialog.asStateFlow()

    private val _isEditingExistingProfile = MutableStateFlow(false)
    val isEditingExistingProfile: StateFlow<Boolean> = _isEditingExistingProfile.asStateFlow()

    private val _loginDuplicateUserNotification = MutableStateFlow<String?>(null)
    val loginDuplicateUserNotification: StateFlow<String?> = _loginDuplicateUserNotification.asStateFlow()

    // Buzón de mensajes enviados al creador (protegido con contraseña exclusiva 6108)
    private val _creatorMessages = MutableStateFlow(loadCreatorMessagesFromPrefs())
    val creatorMessages: StateFlow<List<CreatorMessage>> = _creatorMessages.asStateFlow()

    private val _showCreatorInboxDialog = MutableStateFlow(false)
    val showCreatorInboxDialog: StateFlow<Boolean> = _showCreatorInboxDialog.asStateFlow()

    private val _isCreatorInboxUnlocked = MutableStateFlow(false)
    val isCreatorInboxUnlocked: StateFlow<Boolean> = _isCreatorInboxUnlocked.asStateFlow()

    // Buzón de respuestas para el usuario (sin contraseña)
    private val _showUserMessagesDialog = MutableStateFlow(false)
    val showUserMessagesDialog: StateFlow<Boolean> = _showUserMessagesDialog.asStateFlow()

    // Tema claro / Modo nocturno (oscuro) para lectura cómoda antes de dormir
    private val _isDarkMode = MutableStateFlow(prefs.getBoolean("is_dark_mode", false))
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    // Sistema de Retos tipo Duolingo + Rangos tipo Free Fire (sube cada 5 retos / 5 preguntas por día)
    private var currentChallengeDateKey: String = java.time.LocalDate.now().toString()
    private val savedChallengeDateKey: String = prefs.getString("last_challenge_date_key", "") ?: ""

    private val _completedChallengesCount = MutableStateFlow(
        prefs.getInt("completed_challenges_count", 0).coerceAtLeast(0)
    )
    val completedChallengesCount: StateFlow<Int> = _completedChallengesCount.asStateFlow()

    private val _dailyQuestionsCompletedToday = MutableStateFlow(
        if (savedChallengeDateKey == currentChallengeDateKey) {
            prefs.getInt("daily_questions_completed_today", 0)
                .coerceIn(0, FaithRankTier.MAX_DAILY_QUESTIONS)
        } else {
            0
        }
    )
    val dailyQuestionsCompletedToday: StateFlow<Int> = _dailyQuestionsCompletedToday.asStateFlow()

    // Bloqueo anti-copia: una vez que entra al apartado de Retos, no puede regresar al menú de inicio
    // ni abrir otras secciones hasta completar sus 5 preguntas del día.
    private val _isChallengeAntiCopyLocked = MutableStateFlow(false)
    val isChallengeAntiCopyLocked: StateFlow<Boolean> = _isChallengeAntiCopyLocked.asStateFlow()

    private val _antiCopyWarningShown = MutableStateFlow(false)
    val antiCopyWarningShown: StateFlow<Boolean> = _antiCopyWarningShown.asStateFlow()

    private val _currentFaithRank = MutableStateFlow(
        FaithRankTier.fromCompletedChallenges(_completedChallengesCount.value)
    )
    val currentFaithRank: StateFlow<FaithRankTier> = _currentFaithRank.asStateFlow()

    private val _challengeXp = MutableStateFlow(prefs.getInt("challenge_xp", 0).coerceAtLeast(0))
    val challengeXp: StateFlow<Int> = _challengeXp.asStateFlow()

    private val _challengeStreak = MutableStateFlow(prefs.getInt("challenge_streak", 0).coerceAtLeast(0))
    val challengeStreak: StateFlow<Int> = _challengeStreak.asStateFlow()

    private val _rankUpCelebrationTier = MutableStateFlow<FaithRankTier?>(null)
    val rankUpCelebrationTier: StateFlow<FaithRankTier?> = _rankUpCelebrationTier.asStateFlow()

    // Rotación automática de preguntas cuando el usuario se equivoca en una de las 5 del día
    private val seenPromptsByNode = mutableMapOf<Int, MutableSet<String>>()
    private val _activeQuestionByNode = MutableStateFlow<Map<Int, ChallengeNode>>(emptyMap())
    val activeQuestionByNode: StateFlow<Map<Int, ChallengeNode>> = _activeQuestionByNode.asStateFlow()

    private val _wrongAttemptsByNode = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val wrongAttemptsByNode: StateFlow<Map<Int, Int>> = _wrongAttemptsByNode.asStateFlow()

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Home)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _selectedSacrament = MutableStateFlow(SacramentType.BAUTISMO)
    val selectedSacrament: StateFlow<SacramentType> = _selectedSacrament.asStateFlow()

    // Cita del Día inspiradora (se muestra al abrir la aplicación)
    private val _dailyQuote = MutableStateFlow(DailyQuoteRepository.getTodayQuote())
    val dailyQuote: StateFlow<DailyQuote> = _dailyQuote.asStateFlow()

    private val _showDailyQuoteDialog = MutableStateFlow(true)
    val showDailyQuoteDialog: StateFlow<Boolean> = _showDailyQuoteDialog.asStateFlow()

    private val _dailyQuoteSaved = MutableStateFlow(false)
    val dailyQuoteSaved: StateFlow<Boolean> = _dailyQuoteSaved.asStateFlow()

    private val _selectedCategoryFilter = MutableStateFlow<String?>(null)
    val selectedCategoryFilter: StateFlow<String?> = _selectedCategoryFilter.asStateFlow()

    private val _dailySparkIndex = MutableStateFlow(0)
    val dailySparkIndex: StateFlow<Int> = _dailySparkIndex.asStateFlow()

    private val _isDailyMissionCompleted = MutableStateFlow(false)
    val isDailyMissionCompleted: StateFlow<Boolean> = _isDailyMissionCompleted.asStateFlow()

    private val _calmTimerState = MutableStateFlow(CalmTimerState())
    val calmTimerState: StateFlow<CalmTimerState> = _calmTimerState.asStateFlow()

    // Búsqueda y exploración de la Biblia Católica
    private val _bibleSearchQuery = MutableStateFlow("")
    val bibleSearchQuery: StateFlow<String> = _bibleSearchQuery.asStateFlow()

    private val _bibleFilter = MutableStateFlow("Todos")
    val bibleFilter: StateFlow<String> = _bibleFilter.asStateFlow()

    private val _bibleTab = MutableStateFlow(0) // 0: Pasajes y Búsqueda, 1: 73 Libros, 2: Guía Católica
    val bibleTab: StateFlow<Int> = _bibleTab.asStateFlow()

    // Lector Completo de Capítulos Bíblicos para Jóvenes
    private val _selectedBibleBook = MutableStateFlow<com.example.data.model.BibleBookInfo?>(null)
    val selectedBibleBook: StateFlow<com.example.data.model.BibleBookInfo?> = _selectedBibleBook.asStateFlow()

    private val _selectedChapterNumber = MutableStateFlow(1)
    val selectedChapterNumber: StateFlow<Int> = _selectedChapterNumber.asStateFlow()

    private val _bibleTextScale = MutableStateFlow(1.0f) // 0.9f, 1.0f, 1.15f, 1.3f
    val bibleTextScale: StateFlow<Float> = _bibleTextScale.asStateFlow()

    private val _chapterReaderSection = MutableStateFlow(0) // 0: Lectura & Explicación completa, 1: Solo Versículos, 2: Para Entenderlo Hoy
    val chapterReaderSection: StateFlow<Int> = _chapterReaderSection.asStateFlow()

    private var timerJob: Job? = null

    init {
        val db = AppDatabase.getDatabase(application)
        repository = JournalRepository(db.journalDao())
    }

    private fun loadRegisteredUsersFromPrefs(fallbackProfile: UserProfile?): List<UserProfile> {
        val serialized = prefs.getString("registered_users_records", "") ?: ""
        val parsed = serialized.split("\n")
            .mapNotNull { line ->
                val parts = line.split("|")
                if (parts.size >= 3) {
                    val name = parts[0].trim()
                    val g = GenderIdentity.fromStoredString(parts[1].trim())
                    val a = AgeCategory.fromStoredString(parts[2].trim())
                    val photo = parts.getOrNull(3)?.trim()?.takeIf { it.isNotBlank() }
                    if (name.isNotBlank() && g != null && a != null) {
                        UserProfile(fullName = name, gender = g, ageCategory = a, photoUri = photo)
                    } else null
                } else null
            }
        return if (parsed.isNotEmpty()) {
            parsed
        } else if (fallbackProfile != null) {
            listOf(fallbackProfile)
        } else {
            emptyList()
        }
    }

    private fun serializeRegisteredUsers(users: List<UserProfile>): String {
        return users.joinToString("\n") {
            val cleanName = it.fullName.replace("|", " ")
            val cleanPhoto = (it.photoUri ?: "").replace("|", "")
            "$cleanName|${it.gender.name}|${it.ageCategory.name}|$cleanPhoto"
        }
    }

    private fun loadCreatorMessagesFromPrefs(nowMillis: Long = System.currentTimeMillis()): List<CreatorMessage> {
        val serialized = prefs.getString("creator_messages_records", "") ?: ""
        if (serialized.isBlank()) return emptyList()
        val parsed = serialized.split("\n")
            .mapNotNull { line ->
                val parts = line.split("||")
                if (parts.size >= 5) {
                    val id = parts[0].trim().toLongOrNull() ?: System.currentTimeMillis()
                    val senderName = parts[1].trim()
                    val senderBadge = parts[2].trim()
                    val formattedDate = parts[3].trim()
                    val messageText = parts[4].replace("\\n", "\n").trim()
                    val replyDate = parts.getOrNull(5)?.trim()?.takeIf { it.isNotBlank() }
                    val replyText = parts.getOrNull(6)?.replace("\\n", "\n")?.trim()?.takeIf { it.isNotBlank() }
                    val replySeenAt = parts.getOrNull(7)?.trim()?.toLongOrNull()
                    if (messageText.isNotBlank()) {
                        CreatorMessage(
                            id = id,
                            senderName = senderName.ifBlank { "Usuario de Kairós" },
                            senderBadge = senderBadge.ifBlank { "Comunidad Kairós" },
                            messageText = messageText,
                            formattedDate = formattedDate,
                            creatorReplyText = replyText,
                            creatorReplyDate = replyDate,
                            replyFirstSeenAtMillis = replySeenAt
                        )
                    } else null
                } else null
            }
        val activeMessages = parsed.filterNot { it.isExpiredAfterViewing(nowMillis) }
        if (activeMessages.size != parsed.size) {
            prefs.edit()
                .putString("creator_messages_records", serializeCreatorMessages(activeMessages))
                .commit()
        }
        return activeMessages
    }

    private fun serializeCreatorMessages(messages: List<CreatorMessage>): String {
        return messages.joinToString("\n") { msg ->
            val safeName = msg.senderName.replace("||", " ").replace("\n", " ").trim()
            val safeBadge = msg.senderBadge.replace("||", " ").replace("\n", " ").trim()
            val safeDate = msg.formattedDate.replace("||", " ").replace("\n", " ").trim()
            val safeText = msg.messageText.replace("||", " ").replace("\n", "\\n").trim()
            val safeReplyDate = (msg.creatorReplyDate ?: "").replace("||", " ").replace("\n", " ").trim()
            val safeReplyText = (msg.creatorReplyText ?: "").replace("||", " ").replace("\n", "\\n").trim()
            val safeReplySeenAt = msg.replyFirstSeenAtMillis?.toString() ?: ""
            "${msg.id}||$safeName||$safeBadge||$safeDate||$safeText||$safeReplyDate||$safeReplyText||$safeReplySeenAt"
        }
    }

    val journalEntries: StateFlow<List<JournalEntry>> = combine(
        repository.allEntries,
        _selectedCategoryFilter
    ) { entries, filter ->
        if (filter == null) {
            entries
        } else {
            entries.filter { it.category == filter }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun completeCinematic() {
        // Si ya está registrado, pasa directo a la app principal sin volver a pedir registro
        if (_userProfile.value != null) {
            _appLaunchStage.value = AppLaunchStage.MAIN_APP
        } else {
            _appLaunchStage.value = AppLaunchStage.LOGIN
        }
    }

    fun replayCinematic() {
        _appLaunchStage.value = AppLaunchStage.CINEMATIC
    }

    fun updateLoginFullName(name: String) {
        _loginFullName.value = name
        if (_loginDuplicateUserNotification.value != null) {
            _loginDuplicateUserNotification.value = null
        }
        persistPendingFirstRegistrationIfComplete()
    }

    fun dismissLoginDuplicateNotification() {
        _loginDuplicateUserNotification.value = null
    }

    fun selectLoginGender(gender: GenderIdentity) {
        _loginGender.value = gender
        persistPendingFirstRegistrationIfComplete()
    }

    fun selectLoginAgeCategory(ageCategory: AgeCategory) {
        _loginAgeCategory.value = ageCategory
        persistPendingFirstRegistrationIfComplete()
    }

    fun updateLoginPhotoUri(photoUri: String?) {
        _loginPhotoUri.value = photoUri?.trim()?.takeIf { it.isNotBlank() }
        persistPendingFirstRegistrationIfComplete()
    }

    private fun persistPendingFirstRegistrationIfComplete() {
        // Si es el primer registro y el usuario ya llenó los 3 datos (y no es nombre duplicado ni del creador),
        // guardamos de forma inmediata en disco.
        if (_userProfile.value != null) return
        val trimmedName = _loginFullName.value.trim()
        val gender = _loginGender.value ?: return
        val ageCategory = _loginAgeCategory.value ?: return
        if (trimmedName.isBlank()) return
        if (UserProfile.isCreatorName(trimmedName)) return
        if (_registeredUsersList.value.any { UserProfile.isSameUsername(it.fullName, trimmedName) }) return

        val draftProfile = UserProfile(
            fullName = trimmedName,
            gender = gender,
            ageCategory = ageCategory,
            photoUri = _loginPhotoUri.value
        )
        prefs.edit()
            .putBoolean("is_user_registered", true)
            .putString("user_full_name", trimmedName)
            .putString("user_gender", gender.name)
            .putString("user_age_category", ageCategory.name)
            .putString("user_photo_uri", draftProfile.photoUri ?: "")
            .putInt("registered_users_count", 1)
            .putString("registered_users_records", serializeRegisteredUsers(listOf(draftProfile)))
            .commit()
    }

    fun updateCurrentUserProfilePhoto(photoUri: String?) {
        val normalizedPhoto = photoUri?.trim()?.takeIf { it.isNotBlank() }
        _loginPhotoUri.value = normalizedPhoto
        val current = _userProfile.value ?: return
        val updatedProfile = current.copy(photoUri = normalizedPhoto)
        _userProfile.value = updatedProfile

        val currentList = _registeredUsersList.value.toMutableList()
        val index = currentList.indexOfFirst {
            it.fullName.equals(current.fullName, ignoreCase = true)
        }
        if (index >= 0) {
            currentList[index] = updatedProfile
        } else {
            currentList.add(updatedProfile)
        }
        _registeredUsersList.value = currentList

        prefs.edit()
            .putString("user_photo_uri", normalizedPhoto ?: "")
            .putString("registered_users_records", serializeRegisteredUsers(currentList))
            .commit()
    }

    fun submitLogin(creatorAuthPassword: String? = null): Boolean {
        val trimmedName = _loginFullName.value.trim()
        val gender = _loginGender.value
        val ageCategory = _loginAgeCategory.value
        if (trimmedName.isBlank() || gender == null || ageCategory == null) {
            return false
        }
        val currentList = _registeredUsersList.value.toMutableList()
        val existingIndex = currentList.indexOfFirst {
            UserProfile.isSameUsername(it.fullName, trimmedName)
        }
        val isEditingOwnCurrentName = _isEditingExistingProfile.value &&
            _userProfile.value != null &&
            UserProfile.isSameUsername(_userProfile.value?.fullName, trimmedName)

        // 1) Bloquear si el nombre ya pertenece a otro usuario registrado
        if (existingIndex >= 0 && !isEditingOwnCurrentName) {
            _loginDuplicateUserNotification.value =
                "Ese usuario ya está registrado. No se puede ingresar con el mismo nombre de otro usuario."
            return false
        }

        // 2) Bloquear si un usuario intenta entrar con el mismo nombre que el creador (Kevin Abraham / Kevin Abraham Rodríguez)
        if (UserProfile.isCreatorName(trimmedName) && !isEditingOwnCurrentName) {
            val isAuthorizedCreator = creatorAuthPassword?.trim() == CREATOR_INBOX_PASSWORD
            if (!isAuthorizedCreator) {
                _loginDuplicateUserNotification.value =
                    "Ese usuario ya está registrado (Cuenta del Creador). No se puede ingresar con este nombre."
                return false
            }
        }

        _loginDuplicateUserNotification.value = null

        val editingActiveIndex = if (_isEditingExistingProfile.value && _userProfile.value != null) {
            currentList.indexOfFirst {
                UserProfile.isSameUsername(it.fullName, _userProfile.value?.fullName)
            }
        } else {
            existingIndex
        }

        val resolvedPhotoUri = _loginPhotoUri.value
            ?: if (editingActiveIndex >= 0) currentList[editingActiveIndex].photoUri else null

        val profile = UserProfile(
            fullName = trimmedName,
            gender = gender,
            ageCategory = ageCategory,
            photoUri = resolvedPhotoUri
        )
        _userProfile.value = profile
        _loginPhotoUri.value = resolvedPhotoUri
        _isEditingExistingProfile.value = false

        val newCount = if (editingActiveIndex >= 0) {
            currentList[editingActiveIndex] = profile
            _registeredUsersCount.value.coerceAtLeast(currentList.size)
        } else {
            currentList.add(profile)
            (_registeredUsersCount.value + 1).coerceAtLeast(currentList.size)
        }

        _registeredUsersList.value = currentList
        _registeredUsersCount.value = newCount

        prefs.edit()
            .putBoolean("is_user_registered", true)
            .putString("user_full_name", trimmedName)
            .putString("user_gender", gender.name)
            .putString("user_age_category", ageCategory.name)
            .putString("user_photo_uri", resolvedPhotoUri ?: "")
            .putInt("registered_users_count", newCount)
            .putString("registered_users_records", serializeRegisteredUsers(currentList))
            .commit()

        _appLaunchStage.value = AppLaunchStage.MAIN_APP
        return true
    }

    fun startNewPersonRegistration() {
        _showRegisteredUsersDialog.value = false
        _isEditingExistingProfile.value = false
        _loginDuplicateUserNotification.value = null
        _loginFullName.value = ""
        _loginGender.value = null
        _loginAgeCategory.value = null
        _loginPhotoUri.value = null
        _appLaunchStage.value = AppLaunchStage.LOGIN
    }

    fun cancelRegistrationAndReturnToApp() {
        if (_userProfile.value != null) {
            _isEditingExistingProfile.value = false
            _loginDuplicateUserNotification.value = null
            _loginFullName.value = _userProfile.value!!.fullName
            _loginGender.value = _userProfile.value!!.gender
            _loginAgeCategory.value = _userProfile.value!!.ageCategory
            _loginPhotoUri.value = _userProfile.value!!.photoUri
            _appLaunchStage.value = AppLaunchStage.MAIN_APP
        }
    }

    fun openLoginProfile() {
        if (_currentScreen.value is Screen.Challenges && _isChallengeAntiCopyLocked.value) {
            _antiCopyWarningShown.value = true
            return
        }
        _isEditingExistingProfile.value = true
        _loginDuplicateUserNotification.value = null
        _userProfile.value?.let { current ->
            _loginFullName.value = current.fullName
            _loginGender.value = current.gender
            _loginAgeCategory.value = current.ageCategory
            _loginPhotoUri.value = current.photoUri
        }
        _appLaunchStage.value = AppLaunchStage.LOGIN
    }

    fun openRegisteredUsersDialog() {
        if (_currentScreen.value is Screen.Challenges && _isChallengeAntiCopyLocked.value) {
            _antiCopyWarningShown.value = true
            return
        }
        _showRegisteredUsersDialog.value = true
    }

    fun dismissRegisteredUsersDialog() {
        _showRegisteredUsersDialog.value = false
    }

    fun switchActiveAccount(profile: UserProfile) {
        _userProfile.value = profile
        _loginFullName.value = profile.fullName
        _loginGender.value = profile.gender
        _loginAgeCategory.value = profile.ageCategory
        _loginPhotoUri.value = profile.photoUri
        prefs.edit()
            .putBoolean("is_user_registered", true)
            .putString("user_full_name", profile.fullName)
            .putString("user_gender", profile.gender.name)
            .putString("user_age_category", profile.ageCategory.name)
            .putString("user_photo_uri", profile.photoUri ?: "")
            .commit()
    }

    fun deleteRegisteredAccount(accountToDelete: UserProfile) {
        val currentList = _registeredUsersList.value.toMutableList()
        val removed = currentList.removeAll {
            it.fullName.equals(accountToDelete.fullName, ignoreCase = true)
        }
        if (!removed && _userProfile.value?.fullName.equals(accountToDelete.fullName, ignoreCase = true)) {
            currentList.clear()
        }

        val newCount = currentList.size
        _registeredUsersList.value = currentList
        _registeredUsersCount.value = newCount

        val wasActiveAccount = _userProfile.value?.fullName.equals(accountToDelete.fullName, ignoreCase = true)

        if (currentList.isEmpty()) {
            _userProfile.value = null
            _loginFullName.value = ""
            _loginGender.value = null
            _loginAgeCategory.value = null
            _loginPhotoUri.value = null
            _showRegisteredUsersDialog.value = false
            prefs.edit()
                .putBoolean("is_user_registered", false)
                .remove("user_full_name")
                .remove("user_gender")
                .remove("user_age_category")
                .remove("user_photo_uri")
                .putInt("registered_users_count", 0)
                .putString("registered_users_records", "")
                .commit()
            _appLaunchStage.value = AppLaunchStage.LOGIN
        } else {
            val nextActive = if (wasActiveAccount) currentList.first() else (_userProfile.value ?: currentList.first())
            _userProfile.value = nextActive
            _loginFullName.value = nextActive.fullName
            _loginGender.value = nextActive.gender
            _loginAgeCategory.value = nextActive.ageCategory
            _loginPhotoUri.value = nextActive.photoUri
            prefs.edit()
                .putBoolean("is_user_registered", true)
                .putString("user_full_name", nextActive.fullName)
                .putString("user_gender", nextActive.gender.name)
                .putString("user_age_category", nextActive.ageCategory.name)
                .putString("user_photo_uri", nextActive.photoUri ?: "")
                .putInt("registered_users_count", newCount)
                .putString("registered_users_records", serializeRegisteredUsers(currentList))
                .commit()
        }
    }

    fun deleteCurrentAccount() {
        val active = _userProfile.value ?: _registeredUsersList.value.firstOrNull() ?: return
        deleteRegisteredAccount(active)
    }

    fun toggleDarkMode() {
        val newMode = !_isDarkMode.value
        _isDarkMode.value = newMode
        prefs.edit().putBoolean("is_dark_mode", newMode).apply()
    }

    fun setDarkMode(enabled: Boolean) {
        _isDarkMode.value = enabled
        prefs.edit().putBoolean("is_dark_mode", enabled).apply()
    }

    fun completeChallengeNode(nodeId: Int, xpReward: Int): Boolean {
        val previousCount = _completedChallengesCount.value
        val previousRank = _currentFaithRank.value
        val isNewChallenge = nodeId > previousCount

        if (isNewChallenge && _dailyQuestionsCompletedToday.value >= FaithRankTier.MAX_DAILY_QUESTIONS) {
            return false
        }

        val newCount = if (nodeId == previousCount + 1) {
            previousCount + 1
        } else {
            previousCount.coerceAtLeast(nodeId)
        }

        val newDailyCount = if (isNewChallenge) {
            (_dailyQuestionsCompletedToday.value + 1).coerceAtMost(FaithRankTier.MAX_DAILY_QUESTIONS)
        } else {
            _dailyQuestionsCompletedToday.value
        }

        val newRank = FaithRankTier.fromCompletedChallenges(newCount)
        val newXp = _challengeXp.value + xpReward.coerceAtLeast(5)
        val newStreak = _challengeStreak.value + 1

        _completedChallengesCount.value = newCount
        _dailyQuestionsCompletedToday.value = newDailyCount
        _currentFaithRank.value = newRank
        _challengeXp.value = newXp
        _challengeStreak.value = newStreak

        // Al terminar de resolver la pregunta actual, se desbloquea para que pueda volver atrás o ir a otra página
        _isChallengeAntiCopyLocked.value = false
        _antiCopyWarningShown.value = false

        if (newRank.ordinal > previousRank.ordinal) {
            _rankUpCelebrationTier.value = newRank
        }

        prefs.edit()
            .putInt("completed_challenges_count", newCount)
            .putInt("daily_questions_completed_today", newDailyCount)
            .putString("last_challenge_date_key", currentChallengeDateKey)
            .putInt("challenge_xp", newXp)
            .putInt("challenge_streak", newStreak)
            .apply()
        return true
    }

    fun startChallengeQuestion(nodeId: Int): ChallengeNode {
        // Cuando entra a una pregunta sí se bloquea: tiene que resolverla sí o sí antes de salir o ir atrás
        _isChallengeAntiCopyLocked.value = true
        _antiCopyWarningShown.value = false
        return getActiveQuestionForNode(nodeId)
    }

    fun advanceToNextChallengeDay(newDateKey: String = "day_${System.nanoTime()}") {
        currentChallengeDateKey = newDateKey
        _dailyQuestionsCompletedToday.value = 0
        _isChallengeAntiCopyLocked.value = false
        _antiCopyWarningShown.value = false
        prefs.edit()
            .putString("last_challenge_date_key", currentChallengeDateKey)
            .putInt("daily_questions_completed_today", 0)
            .apply()
    }

    fun triggerAntiCopyWarning() {
        if (_isChallengeAntiCopyLocked.value) {
            _antiCopyWarningShown.value = true
        }
    }

    fun dismissAntiCopyWarning() {
        _antiCopyWarningShown.value = false
    }

    fun getActiveQuestionForNode(nodeId: Int): ChallengeNode {
        return _activeQuestionByNode.value[nodeId] ?: ChallengeRepository.getChallengeById(nodeId)
    }

    fun recordWrongChallengeAttempt(
        nodeId: Int = (_completedChallengesCount.value + 1).coerceAtMost(FaithRankTier.TOTAL_CHALLENGES)
    ): ChallengeNode {
        _challengeStreak.value = 0
        prefs.edit().putInt("challenge_streak", 0).apply()

        val currentQuestion = getActiveQuestionForNode(nodeId)
        val seenSet = seenPromptsByNode.getOrPut(nodeId) {
            mutableSetOf(ChallengeRepository.getChallengeById(nodeId).promptOrQuestion)
        }
        seenSet.add(currentQuestion.promptOrQuestion)

        val newWrongCount = (_wrongAttemptsByNode.value[nodeId] ?: 0) + 1
        val replacementQuestion = ChallengeRepository.getReplacementQuestion(
            originalChallengeId = nodeId,
            failedAttemptCount = newWrongCount,
            alreadySeenPrompts = seenSet
        )
        seenSet.add(replacementQuestion.promptOrQuestion)

        _wrongAttemptsByNode.value = _wrongAttemptsByNode.value + (nodeId to newWrongCount)
        _activeQuestionByNode.value = _activeQuestionByNode.value + (nodeId to replacementQuestion)

        return replacementQuestion
    }

    fun dismissRankUpCelebration() {
        _rankUpCelebrationTier.value = null
    }

    fun navigateTo(screen: Screen) {
        if (_currentScreen.value is Screen.Challenges && _isChallengeAntiCopyLocked.value && screen !is Screen.Challenges) {
            // Solo bloquea si ya entró a una pregunta y aún no la ha resuelto
            _antiCopyWarningShown.value = true
            return
        }

        _currentScreen.value = screen
        if (screen is Screen.Challenges) {
            // Al entrar a la pantalla de Retos NO se bloquea ir atrás ni moverse a otra página
            _isChallengeAntiCopyLocked.value = false
            _antiCopyWarningShown.value = false
            _showDailyQuoteDialog.value = false
        }
    }

    fun selectSacrament(sacrament: SacramentType) {
        if (_currentScreen.value is Screen.Challenges && _isChallengeAntiCopyLocked.value) {
            _antiCopyWarningShown.value = true
            return
        }
        _selectedSacrament.value = sacrament
        _currentScreen.value = Screen.Sacraments
    }

    fun setCategoryFilter(category: String?) {
        _selectedCategoryFilter.value = category
    }

    fun dismissDailyQuoteDialog() {
        _showDailyQuoteDialog.value = false
    }

    fun showDailyQuoteDialog() {
        if (_currentScreen.value is Screen.Challenges && _isChallengeAntiCopyLocked.value) {
            _antiCopyWarningShown.value = true
            return
        }
        _showDailyQuoteDialog.value = true
    }

    fun nextDailyQuote() {
        _dailyQuote.value = DailyQuoteRepository.getNextQuote(_dailyQuote.value.id)
        _dailyQuoteSaved.value = false
    }

    fun saveDailyQuoteToJournal(quote: DailyQuote = _dailyQuote.value) {
        val entryTitle = "Cita del Día: ${quote.reference}"
        val entryContent = "«${quote.text}»\n\nTema: ${quote.category}\n\nReflexión para mi jornada:\n${quote.reflection}\n\nOración:\n${quote.shortPrayer}"
        addJournalEntry(
            title = entryTitle,
            content = entryContent,
            category = JournalCategory.SPIRITUAL_READING.name,
            sacramentRelated = null
        )
        _dailyQuoteSaved.value = true
    }

    fun nextDailySpark() {
        val next = (_dailySparkIndex.value + 1) % ContentRepository.dailySparks.size
        _dailySparkIndex.value = next
    }

    fun toggleDailyMission() {
        _isDailyMissionCompleted.value = !_isDailyMissionCompleted.value
    }

    fun addJournalEntry(
        title: String,
        content: String,
        category: String = JournalCategory.GRATITUDE.name,
        sacramentRelated: String? = null
    ) {
        if (title.isBlank() && content.isBlank()) return
        viewModelScope.launch {
            repository.insertEntry(
                JournalEntry(
                    title = title.ifBlank { "Reflexión del día" },
                    content = content,
                    category = category,
                    sacramentRelated = sacramentRelated,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

    fun deleteJournalEntry(entry: JournalEntry) {
        viewModelScope.launch {
            repository.deleteEntry(entry)
        }
    }

    fun startOrPauseCalmTimer() {
        if (_calmTimerState.value.isRunning) {
            timerJob?.cancel()
            _calmTimerState.value = _calmTimerState.value.copy(isRunning = false)
        } else {
            if (_calmTimerState.value.isCompleted || _calmTimerState.value.secondsLeft <= 0) {
                _calmTimerState.value = CalmTimerState(isRunning = true, secondsLeft = 120)
            } else {
                _calmTimerState.value = _calmTimerState.value.copy(isRunning = true)
            }

            timerJob = viewModelScope.launch {
                val prompts = listOf(
                    "Inhala paz... Exhala prisa...",
                    "Dios está aquí contigo ahora mismo...",
                    "Suelta los pendientes del día...",
                    "Eres amado sin condiciones...",
                    "Descansa en Su presencia...",
                    "Tu corazón está a salvo en Sus manos..."
                )
                while (_calmTimerState.value.secondsLeft > 0) {
                    delay(1000)
                    val newSeconds = _calmTimerState.value.secondsLeft - 1
                    val promptIndex = ((120 - newSeconds) / 20) % prompts.size
                    _calmTimerState.value = _calmTimerState.value.copy(
                        secondsLeft = newSeconds,
                        currentPrompt = prompts[promptIndex],
                        isCompleted = newSeconds == 0,
                        isRunning = newSeconds > 0
                    )
                }
            }
        }
    }

    fun resetCalmTimer() {
        timerJob?.cancel()
        _calmTimerState.value = CalmTimerState()
    }

    fun setBibleSearchQuery(query: String) {
        _bibleSearchQuery.value = query
    }

    fun setBibleFilter(filter: String) {
        _bibleFilter.value = filter
    }

    fun setBibleTab(tabIndex: Int) {
        _bibleTab.value = tabIndex
    }

    fun savePassageToJournal(passage: com.example.data.model.BiblePassage) {
        val entryTitle = "Reflexión: ${passage.reference}"
        val entryContent = "«${passage.text}»\n\nPregunta para mí: ${passage.reflectionPrompt}\n\nMi oración o pensamiento:\n"
        addJournalEntry(
            title = entryTitle,
            content = entryContent,
            category = com.example.data.local.JournalCategory.GRATITUDE.name,
            sacramentRelated = if (passage.tags.contains("sacramentos")) passage.book else null
        )
    }

    fun openBibleBook(book: com.example.data.model.BibleBookInfo, chapter: Int = 1) {
        _selectedBibleBook.value = book
        _selectedChapterNumber.value = chapter.coerceIn(1, book.chaptersCount.coerceAtLeast(1))
        _chapterReaderSection.value = 0
        navigateTo(Screen.Bible)
    }

    fun closeBibleBook() {
        _selectedBibleBook.value = null
    }

    fun selectBibleChapter(chapter: Int) {
        val currentBook = _selectedBibleBook.value ?: return
        _selectedChapterNumber.value = chapter.coerceIn(1, currentBook.chaptersCount.coerceAtLeast(1))
    }

    fun nextBibleChapter() {
        val currentBook = _selectedBibleBook.value ?: return
        if (_selectedChapterNumber.value < currentBook.chaptersCount) {
            _selectedChapterNumber.value += 1
        }
    }

    fun previousBibleChapter() {
        if (_selectedChapterNumber.value > 1) {
            _selectedChapterNumber.value -= 1
        }
    }

    fun setBibleTextScale(scale: Float) {
        _bibleTextScale.value = scale
    }

    fun setChapterReaderSection(section: Int) {
        _chapterReaderSection.value = section
    }

    fun saveChapterToJournal(chapter: com.example.data.model.BibleChapter) {
        val entryTitle = "Lectura de ${chapter.bookName} - Cap. ${chapter.chapterNumber}"
        val highlighted = chapter.verses.filter { it.isHighlight }.joinToString("\n") { "v${it.number}: «${it.text}»" }
        val entryContent = "${chapter.title}\n\n$highlighted\n\nClave para mí:\n${chapter.explanation.mainIdea}\n\nPregunta para mi vida:\n${chapter.explanation.reflectionQuestion}\n\nMi oración:\n${chapter.explanation.youthPrayer}"
        
        addJournalEntry(
            title = entryTitle,
            content = entryContent,
            category = com.example.data.local.JournalCategory.SPIRITUAL_READING.name,
            sacramentRelated = null
        )
    }

    fun sendCreatorMessage(messageText: String): Boolean {
        val cleanText = messageText.trim()
        if (cleanText.isBlank()) return false

        val currentProfile = _userProfile.value
        val senderName = currentProfile?.fullName?.takeIf { it.isNotBlank() }
            ?: _loginFullName.value.trim().takeIf { it.isNotBlank() }
            ?: "Usuario de Kairós"
        val senderBadge = currentProfile?.badgeSummary ?: "Comunidad Kairós"

        val formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        val nowFormatted = java.time.LocalDateTime.now().format(formatter)

        val newMessage = CreatorMessage(
            id = System.currentTimeMillis() + _creatorMessages.value.size,
            senderName = senderName,
            senderBadge = senderBadge,
            messageText = cleanText,
            formattedDate = nowFormatted
        )

        val updatedList = listOf(newMessage) + _creatorMessages.value
        _creatorMessages.value = updatedList
        prefs.edit()
            .putString("creator_messages_records", serializeCreatorMessages(updatedList))
            .commit()
        return true
    }

    fun openCreatorInboxDialog() {
        if (_currentScreen.value is Screen.Challenges && _isChallengeAntiCopyLocked.value) {
            _antiCopyWarningShown.value = true
            return
        }
        // Solo el creador con el nombre de usuario Kevin Abraham puede ver/abrir el buzón de mensajes
        if (_userProfile.value?.isCreatorAccount != true) {
            return
        }
        purgeExpiredCreatorMessages()
        _showRegisteredUsersDialog.value = false
        _isCreatorInboxUnlocked.value = false
        _showCreatorInboxDialog.value = true
    }

    fun dismissCreatorInboxDialog() {
        _showCreatorInboxDialog.value = false
        _isCreatorInboxUnlocked.value = false
    }

    fun unlockCreatorInbox(password: String): Boolean {
        val isCorrect = password.trim() == CREATOR_INBOX_PASSWORD
        _isCreatorInboxUnlocked.value = isCorrect
        return isCorrect
    }

    fun deleteCreatorMessage(messageId: Long) {
        if (!_isCreatorInboxUnlocked.value) return
        val updatedList = _creatorMessages.value.filterNot { it.id == messageId }
        _creatorMessages.value = updatedList
        prefs.edit()
            .putString("creator_messages_records", serializeCreatorMessages(updatedList))
            .commit()
    }

    fun replyToCreatorMessage(messageId: Long, replyText: String): Boolean {
        if (!_isCreatorInboxUnlocked.value) return false
        val cleanReply = replyText.trim()
        if (cleanReply.isBlank()) return false

        val formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        val nowFormatted = java.time.LocalDateTime.now().format(formatter)

        var found = false
        val updatedList = _creatorMessages.value.map { msg ->
            if (msg.id == messageId) {
                found = true
                msg.copy(
                    creatorReplyText = cleanReply,
                    creatorReplyDate = nowFormatted,
                    replyFirstSeenAtMillis = null
                )
            } else {
                msg
            }
        }
        if (!found) return false

        _creatorMessages.value = updatedList
        prefs.edit()
            .putString("creator_messages_records", serializeCreatorMessages(updatedList))
            .commit()
        return true
    }

    fun purgeExpiredCreatorMessages(nowMillis: Long = System.currentTimeMillis()) {
        val current = _creatorMessages.value
        val active = current.filterNot { it.isExpiredAfterViewing(nowMillis) }
        if (active.size != current.size) {
            _creatorMessages.value = active
            prefs.edit()
                .putString("creator_messages_records", serializeCreatorMessages(active))
                .commit()
        }
    }

    fun markCreatorRepliesAsSeenForCurrentUser(nowMillis: Long = System.currentTimeMillis()) {
        purgeExpiredCreatorMessages(nowMillis)
        val activeUserName = _userProfile.value?.fullName?.takeIf { it.isNotBlank() }
            ?: _loginFullName.value.trim().takeIf { it.isNotBlank() }
            ?: "Usuario de Kairós"

        var changed = false
        val updatedList = _creatorMessages.value.map { msg ->
            if (
                msg.senderName.equals(activeUserName, ignoreCase = true) &&
                msg.hasCreatorReply &&
                msg.replyFirstSeenAtMillis == null
            ) {
                changed = true
                msg.copy(replyFirstSeenAtMillis = nowMillis)
            } else {
                msg
            }
        }
        if (changed) {
            _creatorMessages.value = updatedList
            prefs.edit()
                .putString("creator_messages_records", serializeCreatorMessages(updatedList))
                .commit()
        }
    }

    fun openUserMessagesDialog(nowMillis: Long = System.currentTimeMillis()) {
        if (_currentScreen.value is Screen.Challenges && _isChallengeAntiCopyLocked.value) {
            _antiCopyWarningShown.value = true
            return
        }
        markCreatorRepliesAsSeenForCurrentUser(nowMillis)
        _showRegisteredUsersDialog.value = false
        _showUserMessagesDialog.value = true
    }

    fun dismissUserMessagesDialog() {
        _showUserMessagesDialog.value = false
    }

    companion object {
        const val CREATOR_INBOX_PASSWORD = "6108"
    }
}
