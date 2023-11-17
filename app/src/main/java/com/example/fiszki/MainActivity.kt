package com.example.fiszki

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fiszki.ui.theme.Flashcard
import com.example.fiszki.ui.theme.FlashcardAdapter

class MainActivity : ComponentActivity() {
    private lateinit var flashcardAdapter: FlashcardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flashcardAdapter = FlashcardAdapter(mutableListOf())

        val rvFlashcardList: RecyclerView = findViewById(R.id.rvFlashcardsList)
        rvFlashcardList.adapter = flashcardAdapter
        rvFlashcardList.layoutManager = LinearLayoutManager(this)

        val bAddNewFlashcard: Button = findViewById(R.id.bAddNewFlashcard)
        val etFlashcardTitle: EditText = findViewById(R.id.etFlashcardTitle)
        bAddNewFlashcard.setOnClickListener {
            val flashcardTitle = etFlashcardTitle.text.toString()
            if (flashcardTitle.isNotEmpty()) {
                val flashcard = Flashcard(flashcardTitle, "")
                flashcardAdapter.addNewFlashCard(flashcard)
                etFlashcardTitle.text.clear()
            }
        }

        val bDeleteFlashcards: Button = findViewById(R.id.bDeleteFlashcards)
        bDeleteFlashcards.setOnClickListener {
            flashcardAdapter.deleteFlashcards()
        }

        val bTranslateFlashcards: Button = findViewById(R.id.bTranslateFlashcards)
        val rvFlashcardsList: RecyclerView = findViewById(R.id.rvFlashcardsList)
        var languageIndicator = 0
        bTranslateFlashcards.setOnClickListener {
            for (position in 0 until flashcardAdapter.itemCount) {
                val holder = rvFlashcardsList.findViewHolderForAdapterPosition(position)
                        as FlashcardAdapter.FlashcardsHolder
                if (languageIndicator == 0) {
                    flashcardAdapter.translateFlashcards(holder, position, "en")
                } else {
                    flashcardAdapter.translateFlashcards(holder, position, "pl")
                }
            }
            languageIndicator = if (languageIndicator == 1) {
                0
            } else {
                1
            }
        }

    }
}