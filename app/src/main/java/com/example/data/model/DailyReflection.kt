package com.example.data.model

data class DailySpark(
    val title: String,
    val biblicalVerse: String,
    val reflection: String,
    val authorOrSaint: String,
    val microMission: String
)

data class PrayerItem(
    val id: String,
    val title: String,
    val situation: String,
    val content: String,
    val category: String
)
