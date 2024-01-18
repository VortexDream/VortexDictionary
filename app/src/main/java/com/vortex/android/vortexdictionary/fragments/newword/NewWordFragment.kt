package com.vortex.android.vortexdictionary.fragments.newword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vortex.android.vortexdictionary.R
import com.vortex.android.vortexdictionary.databinding.FragmentNewWordBinding
import com.vortex.android.vortexdictionary.model.Word
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class NewWordFragment : Fragment() {

    private var _binding: FragmentNewWordBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val newWordViewModel: NewWordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewWordBinding.inflate(layoutInflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            fab.setOnClickListener {
                val englishWord = englishWordTextField.editText!!.text.toString()
                val russianWord = russianWordTextField.editText!!.text.toString()
                if (englishWord.isNotBlank() && russianWord.isNotBlank()) {
                    englishWordTextField.isErrorEnabled = false
                    russianWordTextField.isErrorEnabled = false
                    newWordViewModel.addWord(
                        Word(
                            englishText = englishWord,
                            russianText = russianWord,
                            date = Date()
                        )
                    )
                    findNavController().popBackStack()
                } else if (englishWord.isBlank() && russianWord.isBlank()) {
                    englishWordTextField.error = getString(R.string.no_word_english_error)
                    russianWordTextField.error = getString(R.string.no_word_russian_error)
                } else if (englishWord.isBlank()) {
                    englishWordTextField.error = getString(R.string.no_word_english_error)
                    russianWordTextField.isErrorEnabled = false
                } else {
                    englishWordTextField.isErrorEnabled = false
                    russianWordTextField.error = getString(R.string.no_word_russian_error)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}