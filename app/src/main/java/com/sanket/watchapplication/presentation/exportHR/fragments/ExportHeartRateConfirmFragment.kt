package com.sanket.watchapplication.presentation.exportHR.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sanket.watchapplication.R
import com.sanket.watchapplication.databinding.FragmentExportHeartRateConfirmBinding

class ExportHeartRateConfirmFragment:Fragment() {
    private lateinit var binding:FragmentExportHeartRateConfirmBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExportHeartRateConfirmBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConfirmExport.setOnClickListener {
            findNavController().navigate(R.id.action_exportHeartRateConfirmFragment_to_exportHearRateProgressFragment)
        }
    }
}