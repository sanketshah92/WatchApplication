package com.sanket.domain

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

const val ACTION_STOP_FOREGROUND = "com.sanket.watchapplication.stop"

@SuppressLint("NewApi")
class MeasureHeartRateService : Service(), SensorEventListener {
    private var iconNotification: Bitmap? = null
    private var notification: Notification? = null
    private var mNotificationManager: NotificationManager? = null
    private val mNotificationId = 123
    private lateinit var sensorManager: SensorManager
    private lateinit var heartRateSensonr: Sensor
    private val repository: HeartRateRepository by inject()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action != null && intent.action.equals(
                ACTION_STOP_FOREGROUND, ignoreCase = true
            )
        ) {
            stopForeground(true)
            stopSelf()
        }
        Log.e("SERVICE::",":::STARTED")
        generateForegroundNotification()
        startListeningHeartRate()
        return START_STICKY
    }

    @SuppressLint("NewApi")
    private fun startListeningHeartRate() {
        sensorManager = (getSystemService(SENSOR_SERVICE) as SensorManager)
        heartRateSensonr = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)
        sensorManager.registerListener(this, heartRateSensonr, SensorManager.SENSOR_DELAY_FASTEST)
    }

    private fun generateForegroundNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val pendingIntent =
                PendingIntent.getActivity(this, 0, Intent(), 0)
            iconNotification = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            if (mNotificationManager == null) {
                mNotificationManager =
                    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                assert(mNotificationManager != null)
                mNotificationManager?.createNotificationChannelGroup(
                    NotificationChannelGroup("heart_rate_measure", "HeartRateMeasure")
                )
                val notificationChannel =
                    NotificationChannel(
                        "service_channel", "Service Notifications",
                        NotificationManager.IMPORTANCE_MIN
                    )
                notificationChannel.enableLights(false)
                notificationChannel.lockscreenVisibility = Notification.VISIBILITY_SECRET
                mNotificationManager?.createNotificationChannel(notificationChannel)
            }
            val builder = NotificationCompat.Builder(this, "service_channel")

            builder.setContentTitle(
                StringBuilder(resources.getString(R.string.app_name)).append(" service is running")
                    .toString()
            )
                .setTicker(
                    StringBuilder(resources.getString(R.string.app_name)).append("service is running")
                        .toString()
                )
                .setContentText("Measuring heart rate")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setWhen(0)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
            if (iconNotification != null) {
                builder.setLargeIcon(Bitmap.createScaledBitmap(iconNotification!!, 128, 128, false))
            }
            builder.color = resources.getColor(R.color.menu_item_text_color_selected, null)
            notification = builder.build()
            startForeground(mNotificationId, notification)
        }

    }

    @SuppressLint("NewApi")
    override fun onSensorChanged(p0: SensorEvent?) {
        p0?.apply {
            if (sensor.type == Sensor.TYPE_HEART_RATE && values.isNotEmpty()) {
                val heartRate = values[0]
                Log.e("Senesor Event:", "$heartRate")
                addHREntryToDB(heartRate.toInt())
            }
        }
    }

    private fun addHREntryToDB(heartRate: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addNewHeartRateRecord(heartRate)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}