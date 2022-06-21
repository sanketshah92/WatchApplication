package com.sanket.watchapplication.presentation.measureHR

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sanket.data.services.MeasureHeartRateService
import com.sanket.watchapplication.R
import com.sanket.watchapplication.presentation.measureHR.viewmodel.MeasureHeartRateViewModel
import kotlinx.android.synthetic.main.fragment_measure_hr.*
import org.koin.android.ext.android.inject


class MeasureHeartRateFragment : Fragment() {
    private lateinit var graphUtils: HeartRateGraphUtils
    private val measureHeartRateViewModel: MeasureHeartRateViewModel by inject()
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                startHeartRateMeasureService()
                startObservingHeartRateData()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.sensor_permission_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_measure_hr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestBodySensorPermission(requestPermission)
        graphUtils = HeartRateGraphUtils(animHeartBeat)
        setupInitialView()

    }

    private fun startObservingHeartRateData() {
        measureHeartRateViewModel.listenHeartRate().observe(viewLifecycleOwner, Observer {
            it.observe(viewLifecycleOwner, Observer { data ->
                data?.let {
                    txtHeartRate.text = it.heartRate.toString()
                    graphUtils.addNewHeartEntry(it.heartRate.toDouble())
                }

            })
        })
    }

    private fun startHeartRateMeasureService() {
       // val service = MeasureHeartRateService(requireActivity())
        requireActivity().startForegroundService(
            Intent(
                requireContext(),
                MeasureHeartRateService::class.java
            )
        )
    }

    private fun requestBodySensorPermission(
        requestPermission: ActivityResultLauncher<String>
    ) {
        requestPermission.launch(android.Manifest.permission.BODY_SENSORS)
    }

    private fun setupInitialView() {
        tv_measurement.visibility = View.VISIBLE
        btnStart.visibility = View.VISIBLE
        txtHeartRate.text = "---"
        iv_arrow.visibility = View.VISIBLE
        stopListeningHeartBeatView()
        addStartStopListeners()
        graphUtils.initializeGraphProperties()
    }

    private fun addStartStopListeners() {
        btnStart.setOnClickListener {
            startListeningHeartBeatView()
        }
        btnStop.setOnClickListener {
            setupInitialView()
        }
    }

    private fun hideInitialView() {
        btnStart.visibility = View.GONE
        tv_measurement.visibility = View.GONE
        iv_arrow.visibility = View.INVISIBLE
    }

    private fun startListeningHeartBeatView() {
        animHeartBeat.visibility = View.VISIBLE
        btnStop.visibility = View.VISIBLE
        iv_heart.playAnimation()
        txtHeartRate.text = "---"
        hideInitialView()
    }

    private fun stopListeningHeartBeatView() {
        animHeartBeat.visibility = View.GONE
        btnStop.visibility = View.GONE
        iv_heart.pauseAnimation()
    }

}