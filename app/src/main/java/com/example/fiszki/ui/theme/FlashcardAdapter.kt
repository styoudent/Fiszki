package com.example.fiszki.ui.theme

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fiszki.R

class FlashcardAdapter(
    private val flashcards: MutableList<Flashcard>
) : RecyclerView.Adapter<FlashcardAdapter.FlashcardsHolder>() {
    class FlashcardsHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardsHolder {
        return FlashcardsHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_flashcard,
                parent,
                false
            )
        )
    }

    fun addNewFlashCard(flashcard: Flashcard) {
        flashcards.add(flashcard)
        notifyItemInserted(flashcards.size - 1)
    }

    fun deleteFlashcards() {
        flashcards.removeAll { flashcard: Flashcard ->
            flashcard.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvFlashcardTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvFlashcardTitle.paintFlags = tvFlashcardTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvFlashcardTitle.paintFlags =
                tvFlashcardTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: FlashcardsHolder, position: Int) {
        val currFlashcard = flashcards[position]
        holder.itemView.apply {
            val tvFlashcardTitle: TextView = findViewById(R.id.tvFlashcardTitle)
            val cbToTranslate: CheckBox = findViewById(R.id.cbToTranslate)
            tvFlashcardTitle.text = currFlashcard.title
            cbToTranslate.isChecked = currFlashcard.isChecked
            toggleStrikeThrough(tvFlashcardTitle, currFlashcard.isChecked)
            cbToTranslate.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvFlashcardTitle, isChecked)
                currFlashcard.isChecked = !currFlashcard.isChecked
            }
        }
    }


    override fun getItemCount(): Int {
        return flashcards.size
    }
}