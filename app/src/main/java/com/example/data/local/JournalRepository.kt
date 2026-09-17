package com.example.data.local

import kotlinx.coroutines.flow.Flow

class JournalRepository(private val dao: JournalDao) {
    val allEntries: Flow<List<JournalEntry>> = dao.getAllEntries()

    fun getEntriesByCategory(category: String): Flow<List<JournalEntry>> {
        return dao.getEntriesByCategory(category)
    }

    suspend fun insertEntry(entry: JournalEntry): Long {
        return dao.insertEntry(entry)
    }

    suspend fun updateEntry(entry: JournalEntry) {
        dao.updateEntry(entry)
    }

    suspend fun deleteEntry(entry: JournalEntry) {
        dao.deleteEntry(entry)
    }

    suspend fun deleteEntryById(id: Long) {
        dao.deleteEntryById(id)
    }
}
