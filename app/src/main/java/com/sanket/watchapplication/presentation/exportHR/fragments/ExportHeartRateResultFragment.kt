package com.sanket.watchapplication.presentation.exportHR.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sanket.watchapplication.databinding.FragmentExportHeartRateSuccessBinding

class ExportHeartRateResultFragment : Fragment() {
    private lateinit var binding: FragmentExportHeartRateSuccessBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExportHeartRateSuccessBinding.inflate(inflater)
        return binding.root
    }
}