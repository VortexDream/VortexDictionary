package com.vortex.android.vortexdictionary.fragments.subscription

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vortex.android.vortexdictionary.databinding.FragmentSubscriptionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubscriptionDialogFragment: DialogFragment() {

    private var _binding: FragmentSubscriptionBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val subscriptionViewModel: SubscriptionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        _binding = FragmentSubscriptionBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configureUi() {
        binding.apply {
            acceptButton.setOnClickListener {
                subscriptionViewModel.buySubscription()
            }
            declineButton.setOnClickListener {
                findNavController().popBackStack()
            }
            price.text = subscriptionViewModel.getSubscriptionPrice()
        }
    }
}
