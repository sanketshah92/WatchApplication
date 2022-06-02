package com.sanket.watchapplication.presentation.measureHR

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sanket.watchapplication.R
import com.sanket.watchapplication.presentation.measureHR.viewmodel.MeasureHeartRateViewModel
import kotlinx.android.synthetic.main.fragment_measure_hr.*
import org.koin.android.ext.android.inject


class MeasureHeartRateFragment : Fragment() {
    private lateinit var graphUtils: HeartRateGraphUtils
    private val measureHeartRateViewModel: MeasureHeartRateViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_measure_hr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        graphUtils = HeartRateGraphUtils(animHeartBeat)
        setupInitialView()
        measureHeartRateViewModel.listenHeartRate().observe(viewLifecycleOwner, Observer {
            it.observe(viewLifecycleOwner, Observer { data ->
                txtHeartRate.text = data.heartRate.toString()
            })
        })
    }

    private fun setupInitialView() {
        tv_measurement.visibility = View.VISIBLE
        btnStart.visibility = View.VISIBLE
        txtHeartRate.text = "---"
        iv_arrow.visibility = View.VISIBLE
        stopListeningHeartBeatView()
        addStartStopListeners()
        graphUtils.initializeGraphProperties()
        createHeartDummyRecords()
    }

    private fun createHeartDummyRecords() {
        graphUtils.addNewHeartEntry(7.0)
        graphUtils.addNewHeartEntry(100.0)
        graphUtils.addNewHeartEntry(108.0)
        graphUtils.addNewHeartEntry(75.0)
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
        txtHeartRate.text = "72"
        hideInitialView()
    }

    private fun stopListeningHeartBeatView() {
        animHeartBeat.visibility = View.GONE
        btnStop.visibility = View.GONE
        iv_heart.pauseAnimation()
    }

}