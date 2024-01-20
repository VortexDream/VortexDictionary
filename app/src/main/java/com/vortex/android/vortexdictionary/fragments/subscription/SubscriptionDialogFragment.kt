package com.vortex.android.vortexdictionary.fragments.subscription

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vortex.android.vortexdictionary.broadcastreciever.TimerManager
import com.vortex.android.vortexdictionary.databinding.FragmentSubscriptionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubscriptionDialogFragment: DialogFragment() {

    private var _binding: FragmentSubscriptionBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private var _timerManager: TimerManager? = null
    private val timerManager
        get() = checkNotNull(_timerManager)

    private val subscriptionViewModel: SubscriptionViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        _binding = FragmentSubscriptionBinding.inflate(layoutInflater, container,false)
        _timerManager = TimerManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _timerManager = null
    }

    private fun configureUi() {
        binding.apply {
            acceptButton.setOnClickListener {
                requestPermissionAndBuySubscription()
            }
            declineButton.setOnClickListener {
                findNavController().popBackStack()
            }
            price.text = subscriptionViewModel.getSubscriptionPrice()
        }
    }

    private fun requestPermissionAndBuySubscription() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (!alarmManager.canScheduleExactAlarms()) {
                startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
                Log.d("TEST", "REQ PERM")
            } else {
                subscriptionViewModel.buySubscription()
                timerManager.startTimer()
                findNavController().popBackStack()
                Log.d("TEST", "SUBS BOUGHT")
            }
        } else {
            subscriptionViewModel.buySubscription()
            timerManager.startTimer()
            findNavController().popBackStack()
            Log.d("TEST", "SUBS BOUGHT")
        }
    }
}
