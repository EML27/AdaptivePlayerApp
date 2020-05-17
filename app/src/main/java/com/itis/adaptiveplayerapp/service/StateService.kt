package com.itis.adaptiveplayerapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.itis.adaptiveplayerapp.R
import com.itis.adaptiveplayerapp.di.component.DaggerServiceInteractorComponent
import com.itis.adaptiveplayerapp.service.interactor.SpotifyServiceInteractor
import javax.inject.Inject

class StateService : Service() {


    private var state = "Calm"
    val CHANNEL_ID = "27"
    override fun onBind(intent: Intent): IBinder = mBinder

    inner class MyBinder : Binder() {
        fun getService() = this@StateService
    }

    var mBinder = MyBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent.let {
            when (it?.action) {
                "start" -> {setNotification()}

            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun setNotification() {
        // Интенты для будущих команд
        val getIntent =
            { action: String -> Intent(this, StateService::class.java).setAction(action) }
        val getPendingIntent =
            { action: String -> PendingIntent.getService(this, 0, getIntent(action), 0) }

        val currentStateTitle: String

        //Логика установки текущего состояния
        currentStateTitle = state
        // оаоаоаоаоаоаоа
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification_channel_name)
            val descriptionText = getString(R.string.notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                notificationManager.getNotificationChannel(CHANNEL_ID) ?: NotificationChannel(
                    CHANNEL_ID,
                    name,
                    importance
                ).apply {
                    description = descriptionText
                }
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(currentStateTitle)
            .setSmallIcon(R.drawable.ic_notification_small)
            .build()

        notificationManager.notify(1, notification)
    }

}
