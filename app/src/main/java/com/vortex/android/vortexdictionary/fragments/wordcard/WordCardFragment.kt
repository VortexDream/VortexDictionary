package com.vortex.android.vortexdictionary.fragments.wordcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.vortex.android.vortexdictionary.databinding.FragmentWordCardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordCardFragment : Fragment() {

    private var _binding: FragmentWordCardBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val wordCardViewModel: WordCardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWordCardBinding.inflate(layoutInflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeWord()
        configureUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeWord() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                wordCardViewModel.currentWord.collect { word ->
                    binding.englishWordTextView.text = word?.englishText
                    binding.russianWordTextView.text = word?.russianText
                }
            }
        }
    }

    private fun configureUi() {
        binding.apply {
            newWordButton.setOnClickListener {
                wordCardViewModel.getRandomWord()
            }
            fab.setOnClickListener {
                findNavController().navigate(WordCardFragmentDirections.newWord())
            }
        }
    }
}