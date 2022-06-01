package com.sanket.watchapplication.presentation


import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.sanket.watchapplication.data.services.MeasureHeartRateService
import com.sanket.watchapplication.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startHeartRateMeasureService()
    }

    private fun startHeartRateMeasureService() {
//        val intentStop = Intent(this, MeasureHeartRateService::class.java)
//        intentStop.action = ACTION_STOP_FOREGROUND
//        startService(intentStop)
        //startService(Intent(this, MeasureHeartRateService::class.java))
        startForegroundService(Intent(this, MeasureHeartRateService::class.java))
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        try {
            val manager =
                getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (service in manager.getRunningServices(
                Int.MAX_VALUE
            )) {
                if (serviceClass.name == service.service.className) {
                    return true
                }
            }
        } catch (e: Exception) {
            return false
        }
        return false
    }
}