package com.vortex.android.vortexdictionary.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vortex.android.vortexdictionary.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment: Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val settingsViewModel: SettingsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(layoutInflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.translateButton.setOnClickListener {
            settingsViewModel.apply {
                setSubscriptionStatus(false)
                setTranslationCounter(0)
                setWordCounter(0)
                /*try { //Вариант с полной очисткой данных - перезапускает приложение
                    val runtime = Runtime.getRuntime()
                    runtime.exec("pm clear com.vortex.android.vortexdictionary")
                } catch (e: Exception) {
                    e.printStackTrace()
                }*/
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}