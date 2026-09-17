package com.example.data.model

data class BiblePassage(
    val id: String,
    val reference: String, // e.g. "Eclesiástico 6, 14-16"
    val book: String, // e.g. "Eclesiástico (Sirácida)"
    val testament: Testament,
    val isDeuterocanonical: Boolean = false,
    val text: String,
    val theme: String, // e.g. "Amistad Verdadera"
    val youthContext: String, // Relatable youth explanation
    val reflectionPrompt: String,
    val tags: List<String>
)

enum class Testament {
    ANTIGUO,
    NUEVO
}

data class BibleBookInfo(
    val name: String,
    val abbreviation: String,
    val testament: Testament,
    val category: String, // Pentateuco, Históricos, Sapienciales, Proféticos, Evangelios, Cartas, etc.
    val isDeuterocanonical: Boolean = false,
    val chaptersCount: Int,
    val description: String,
    val keyVerse: String
)

data class BibleVerse(
    val number: Int,
    val text: String,
    val isHighlight: Boolean = false
)

data class YouthChapterExplanation(
    val mainIdea: String,
    val youthContext: String,
    val keyTeachings: List<String>,
    val reflectionQuestion: String,
    val youthPrayer: String
)

data class BibleChapter(
    val bookName: String,
    val chapterNumber: Int,
    val title: String,
    val verses: List<BibleVerse>,
    val explanation: YouthChapterExplanation
)
