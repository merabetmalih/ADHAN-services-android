package com.example.serie05_exo07

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button : Button = findViewById(R.id.button)
        var mMediaPlayer: MediaPlayer
        mMediaPlayer = MediaPlayer.create(this, R.raw.c5c12e0cdba9)
        val thread = ExempleThread(this,mMediaPlayer)



        createNotificationChannel()

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 1)
            set(Calendar.MINUTE, 9)
        }

        var  timeAtButtonClick: Long = System.currentTimeMillis()

        button.setOnClickListener {
            thread.start()
            val intent  = Intent (this,AdhanBroadcast::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this,0,intent,0)

//            val alarmManager : AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
//
//            alarmManager.set(AlarmManager.RTC_WAKEUP,10000+timeAtButtonClick,pendingIntent)
//            Toast.makeText(this,"Adhan Alarm Is Activated",Toast.LENGTH_SHORT).show()




        }

    }



    public fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name : CharSequence = "notifyadhan"
            val description = "Adhan Reminder"
            val importance : Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("notifyadhan",name,importance)
            channel.description = description

            val notificationManager : NotificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)



        }
    }
    class ExempleThread (val context: Context,var mediaPlayer: MediaPlayer):Thread(){
        public fun startnotification() {
            val builder: NotificationCompat.Builder =
                NotificationCompat.Builder(this.context, "notifyadhan")
                    .setSmallIcon(android.R.drawable.ic_media_play)
                    .setContentTitle("Prayer Time")
                    .setContentText("It's the prayers time")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setDefaults(Notification.DEFAULT_SOUND)

            val notificationManager: NotificationManagerCompat =
                NotificationManagerCompat.from(this.context)
            notificationManager.notify(200, builder.build())
        }
        override fun run() {
            Thread.sleep(2000)
            startnotification()
            mediaPlayer!!.start()
        }
    }
}