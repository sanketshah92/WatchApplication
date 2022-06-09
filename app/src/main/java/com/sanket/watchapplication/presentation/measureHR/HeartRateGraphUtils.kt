package com.sanket.watchapplication.presentation.measureHR

import android.graphics.Color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.sanket.watchapplication.R

class HeartRateGraphUtils(private val view:LineChart) {

    fun initializeGraphProperties() {
        view.description.isEnabled = false
        view.axisRight.isEnabled = false
        view.legend.isEnabled = false
        view.setDrawGridBackground(false)
        view.setPinchZoom(false)
        view.setScaleEnabled(false)
        view.isDoubleTapToZoomEnabled = false
        view.isScaleYEnabled = false
        view.isDragXEnabled = false
        view.isDragYEnabled = false
        val xAxis: XAxis = view.xAxis
        xAxis.isEnabled = false
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        val yAxisHeart: YAxis = view.axisLeft
        yAxisHeart.isEnabled = false
        yAxisHeart.axisMaximum = 600f
        yAxisHeart.axisMinimum = -600f
        yAxisHeart.setDrawAxisLine(false)
        yAxisHeart.setDrawZeroLine(false)

        //add empty data
        view.data = LineData()
        view.setViewPortOffsets(0f, 0f, 0f, 0f)
    }

    private fun createHeartDataSet(color: Int): LineDataSet {
        val set = LineDataSet(null, "Live Heart")
        set.axisDependency = YAxis.AxisDependency.LEFT
        set.color = color
        set.lineWidth = 1f
        set.setDrawCircles(false)
        set.isHighlightEnabled = false
        set.setDrawValues(false)
        set.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        set.cubicIntensity = 0.2f
        return set
    }

    fun addNewHeartEntry(heartRate: Double) {
        val data: LineData = view.data
        if (data != null) {
            var set = data.getDataSetByIndex(0)
            if (set == null) {
                set = createHeartDataSet(Color.parseColor("#DB00FF"))
                data.addDataSet(set)
            }
            data.addEntry(
                Entry(
                    set.entryCount.toFloat(),
                    heartRate.toFloat()
                ), 0
            )
            data.notifyDataChanged()
            view.notifyDataSetChanged()
            view.setVisibleXRangeMaximum(625f)

            // move to the latest entry
            view.moveViewToX(set.entryCount.toFloat())
            view.data = data
            view.invalidate()
        }

    }
}