package com.vortex.android.vortexdictionary.fragments.wordcard

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
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
class WordCardFragment: Fragment() {

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

        hideTranslation()
        if (wordCardViewModel.isTranslationVisible) {
            showTranslation()
        }
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
            randomWordButton.setOnClickListener {
                hideTranslation()
                wordCardViewModel.isTranslationVisible = false
                wordCardViewModel.getRandomWord()
            }
            fab.setOnClickListener {
                if (wordCardViewModel.wordCounter < 5) {
                    findNavController().navigate(WordCardFragmentDirections.newWord())
                } else {
                    findNavController().navigate(WordCardFragmentDirections.popupSubscription())
                }
            }
            translateButton.setOnClickListener {
                if (!wordCardViewModel.isTranslationVisible && wordCardViewModel.translationCounter < 5) {
                    showTranslation()
                    wordCardViewModel.translationCounter++
                    wordCardViewModel.setTranslationCounter()
                    wordCardViewModel.isTranslationVisible = true
                } else if (wordCardViewModel.translationCounter >= 5) {
                    findNavController().navigate(WordCardFragmentDirections.popupSubscription())
                }
            }
        }
    }

    private fun hideTranslation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val effect = RenderEffect.createBlurEffect(
                128.0f,
                16.0f,
                Shader.TileMode.REPEAT
            )
            binding.russianWordTextView.setRenderEffect(effect)
        } else {
            binding.russianWordTextView.isInvisible = true
        }

    }

    private fun showTranslation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            binding.russianWordTextView.setRenderEffect(null)
        } else {
            binding.russianWordTextView.isVisible = true
        }
    }
}