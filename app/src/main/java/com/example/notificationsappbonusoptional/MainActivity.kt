package com.example.notificationsappbonusoptional

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.notificationsappbonusoptional.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val channel_Id = "channel_id_654"
    val notificationId = 123
    var next: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotification.setOnClickListener {

            counter()

        }
    }

    @SuppressLint("ObsoleteSdkInt")

    fun createNotification() {
        var text="Ready"
        var builder: Notification.Builder
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, MainActivity2::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel =
                NotificationChannel(channel_Id, text, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channel_Id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Egg counting")
                .setContentText(text)
                .setContentIntent(pendingIntent)

        } else {
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Egg counting")
                .setContentText((text))
                .setContentIntent(pendingIntent)


        }

        notificationManager.notify(notificationId, builder.build())
    }

    fun counter() {


        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = "Time: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                next=true
                print(next)
                createNotification()

            }
        }.start()
    }


}