package com.sanket.watchapplication.presentation.exportHR.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sanket.watchapplication.databinding.FragmentExportHeartRateConfirmBinding
import com.sanket.watchapplication.presentation.exportHR.viewmodel.ExportHeartRateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExportHeartRateConfirmFragment : Fragment() {
    private lateinit var binding: FragmentExportHeartRateConfirmBinding
     private val viewModel: ExportHeartRateViewModel by viewModel()
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
            viewModel.fetchUserHeartRateData().observe(viewLifecycleOwner, Observer {
                Log.e("Fetched Data::","$it")
                val action = ExportHeartRateConfirmFragmentDirections.actionExportHeartRateConfirmFragmentToExportHearRateProgressFragment(it.toTypedArray())
                findNavController().navigate(action)
            })
        }
    }
}