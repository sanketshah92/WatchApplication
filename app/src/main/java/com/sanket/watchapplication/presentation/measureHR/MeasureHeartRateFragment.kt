package com.sanket.watchapplication.presentation.measureHR

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sanket.watchapplication.R
import kotlinx.android.synthetic.main.fragment_measure_hr.*

class MeasureHeartRateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_measure_hr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInitialView()
    }

    private fun setupInitialView() {
        tv_measurement.visibility = View.VISIBLE
        btnStart.visibility = View.VISIBLE
        txtHeartRate.text = "---"
        iv_arrow.visibility = View.VISIBLE
        stopListeningHeartBeatView()
        addStartStopListeners()
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