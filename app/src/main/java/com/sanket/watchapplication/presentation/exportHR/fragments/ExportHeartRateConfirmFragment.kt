package com.sanket.watchapplication.presentation.exportHR.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sanket.watchapplication.R
import com.sanket.watchapplication.data.models.HeartRateData
import com.sanket.watchapplication.databinding.FragmentExportHeartRateConfirmBinding
import com.sanket.watchapplication.presentation.exportHR.viewmodel.ExportHeartRateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExportHeartRateConfirmFragment : Fragment() {
    private lateinit var binding: FragmentExportHeartRateConfirmBinding
    private val viewModel: ExportHeartRateViewModel by viewModel()
    private val permissionObserver = MutableLiveData<Boolean>(false)
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.forEach { actionMap ->
                when (actionMap.key) {
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                        permissionObserver.value = true
                    }
                    else -> {
                        permissionObserver.value = false
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.storage_permission_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

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
                requestStoragePermissionAndNavigate(it, requestPermission)
            })
        }


    }

    private fun requestStoragePermissionAndNavigate(
        data: List<HeartRateData>,
        requestPermission: ActivityResultLauncher<Array<String>>
    ) {
        requestPermission.launch(
            arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )
        navigateToHeartRateExportProgress(data)
    }

    private fun navigateToHeartRateExportProgress(data: List<HeartRateData>) {
        permissionObserver.observe(viewLifecycleOwner, Observer {
            if (it) {
                val action =
                    ExportHeartRateConfirmFragmentDirections.actionExportHeartRateConfirmFragmentToExportHearRateProgressFragment(
                        data.toTypedArray()
                    )
                findNavController().navigate(action)
            }
        })

    }
}