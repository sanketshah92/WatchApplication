package com.sanket.watchapplication.presentation.exportHR.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sanket.watchapplication.R
import com.sanket.watchapplication.databinding.FragmentExportHeartRateProgressBinding
import com.sanket.watchapplication.presentation.exportHR.viewmodel.HeartRateToCSVViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExportHearRateProgressFragment : Fragment() {
    private lateinit var binding: FragmentExportHeartRateProgressBinding
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val viewModel: HeartRateToCSVViewModel by viewModel()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            val data = ExportHearRateProgressFragmentArgs.fromBundle(bundle).heartRateData
            viewModel.prepareCSV(data.asList())
            viewModel.exportState.observe(viewLifecycleOwner, Observer {
                when (it) {
                    "Loading" -> {

                    }
                    "SUCCESS" -> {
                        findNavController().navigate(R.id.action_exportHearRateProgressFragment_to_exportHeartRateResultFragment)
                    }
                    "ERROR" -> {
                        findNavController().popBackStack()
                    }
                    else -> {

                    }
                }
            })
        }

    }
}