package com.sanket.watchapplication.presentation.exportHR.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sanket.watchapplication.R
import com.sanket.watchapplication.databinding.FragmentExportHeartRateProgressBinding

class ExportHearRateProgressFragment : Fragment() {
    private lateinit var binding: FragmentExportHeartRateProgressBinding
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val navigationRunnable = {
        findNavController().navigate(R.id.action_exportHearRateProgressFragment_to_exportHeartRateResultFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExportHeartRateProgressBinding.inflate(inflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(navigationRunnable, 2500)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacksAndMessages(null)
    }
}