package com.example.fiszki.ui.theme

data class Flashcard (
    val title: String,
    var translation: String,
    var isChecked: Boolean = false
)