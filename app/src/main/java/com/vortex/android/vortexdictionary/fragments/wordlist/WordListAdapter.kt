package com.vortex.android.vortexdictionary.fragments.wordlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vortex.android.vortexdictionary.databinding.ListItemWordBinding
import com.vortex.android.vortexdictionary.model.Word

class WordHolder(
    private val binding: ListItemWordBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(word: Word) {
        binding.apply {
            englishWordTextView.text = word.englishText
            russianWordTextView.text = word.russianText
        }
    }
}

class WordListAdapter(
    private val words: List<Word>,
    private val context: Context,
) : RecyclerView.Adapter<WordHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemWordBinding.inflate(inflater, parent, false)
        return WordHolder(binding, context)
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val word = words[position]
        holder.bind(word)
    }
}