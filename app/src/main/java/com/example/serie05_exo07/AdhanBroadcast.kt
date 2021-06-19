package com.example.serie05_exo07

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AdhanBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val builder : NotificationCompat.Builder = NotificationCompat.Builder(context,"notifyadhan")
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentTitle("Prayer Time")
            .setContentText("It's the prayers time")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notificationManager : NotificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManager.notify(200,builder.build())
    }

}