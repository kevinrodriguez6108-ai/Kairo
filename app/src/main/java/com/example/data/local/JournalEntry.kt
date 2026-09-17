package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class JournalCategory(val displayName: String) {
    GRATITUDE("Gratitud"),
    SACRAMENTS("Sacramentos"),
    DECISION("Decisión / Dilema"),
    PEACE("Paz y Ansiedad"),
    CHALLENGE("Reto del Día"),
    SPIRITUAL_READING("Lectura Bíblica")
}

@Entity(tableName = "journal_entries")
data class JournalEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val category: String = JournalCategory.GRATITUDE.name,
    val sacramentRelated: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = false
)
