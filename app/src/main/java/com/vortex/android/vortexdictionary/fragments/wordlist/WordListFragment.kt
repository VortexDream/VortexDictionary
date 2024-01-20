package com.vortex.android.vortexdictionary.fragments.wordlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vortex.android.vortexdictionary.R
import com.vortex.android.vortexdictionary.databinding.FragmentWordListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordListFragment : Fragment(), MenuProvider, SearchView.OnQueryTextListener {

    private var _binding: FragmentWordListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val wordListViewModel: WordListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWordListBinding.inflate(layoutInflater, container,false)
        activity?.addMenuProvider(this, viewLifecycleOwner)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordListViewModel.getWordList()
        observeWordList()
        configureUi()
        configureSorting()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.word_list_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            wordListViewModel.searchDatabase("%$it%")
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        query?.let {
            wordListViewModel.searchDatabase("%$it%")
            binding.radioDate.isChecked = true
            binding.radioAscended.isChecked = true
        }
        return true
    }

    private fun configureUi() {
        binding.wordsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun configureSorting() {
        binding.apply { 
            radioDate.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (binding.radioAscended.isChecked) {
                        wordListViewModel.getItemsSortedByDateAscended()
                    }
                    if (binding.radioDescended.isChecked) {
                        wordListViewModel.getItemsSortedByDateDescended()
                    }
                }
            }
            radioLatin.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (binding.radioAscended.isChecked) {
                        wordListViewModel.getItemsSortedByLatinAscended()
                    }
                    if (binding.radioDescended.isChecked) {
                        wordListViewModel.getItemsSortedByLatinDescended()
                    }
                }
            }
            radioCyrillic.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (binding.radioAscended.isChecked) {
                        wordListViewModel.getItemsSortedByCyrillicAscended()
                    }
                    if (binding.radioDescended.isChecked) {
                        wordListViewModel.getItemsSortedByCyrillicDescended()
                    }
                }
            }
            radioAscended.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (binding.radioDate.isChecked) {
                        wordListViewModel.getItemsSortedByDateAscended()
                    }
                    if (binding.radioLatin.isChecked) {
                        wordListViewModel.getItemsSortedByLatinAscended()
                    }
                    if (binding.radioCyrillic.isChecked) {
                        wordListViewModel.getItemsSortedByCyrillicAscended()
                    }
                }
            }
            radioDescended.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (binding.radioDate.isChecked) {
                        wordListViewModel.getItemsSortedByDateDescended()
                    }
                    if (binding.radioLatin.isChecked) {
                        wordListViewModel.getItemsSortedByLatinDescended()
                    }
                    if (binding.radioCyrillic.isChecked) {
                        wordListViewModel.getItemsSortedByCyrillicDescended()
                    }
                }
            }
        }
    }

    private fun observeWordList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                wordListViewModel.wordList.collect { wordList ->
                    binding.wordsRecyclerView.adapter = WordListAdapter(wordList, requireContext())
                }
            }
        }
    }
}