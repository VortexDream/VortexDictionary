package com.vortex.android.vortexdictionary.fragments.newword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vortex.android.vortexdictionary.databinding.FragmentNewWordBinding
import dagger.hilt.android.AndroidEntryPoint

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

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}