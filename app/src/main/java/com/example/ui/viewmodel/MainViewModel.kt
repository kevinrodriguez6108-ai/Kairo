package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.JournalCategory
import com.example.data.local.JournalEntry
import com.example.data.local.JournalRepository
import com.example.data.model.DailyQuote
import com.example.data.model.DailySpark
import com.example.data.model.Dilemma
import com.example.data.model.PrayerItem
import com.example.data.model.SacramentType
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

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun selectSacrament(sacrament: SacramentType) {
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
}
