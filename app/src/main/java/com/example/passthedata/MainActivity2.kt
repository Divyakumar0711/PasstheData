package com.example.passthedata

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity2 : AppCompatActivity() {

    lateinit var msgTV: TextView

    private val CHANNEL_ID = "message_channel"
    private val NOTIFICATION_ID = 1

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        msgTV = findViewById(R.id.idTVMsg)

        // Get the username and message from the intent
        val username = intent.extras?.getString("username") ?: "No username"
        val message = intent.extras?.getString("message") ?: "No message"

        // Display the username and message in the TextView
        msgTV.text = "Username: $username\nMessage: $message"

        // Create notification channel if needed
        createNotificationChannel()

        // Load icons


        // Build the notification
        // Create custom notification layout
        val customView = RemoteViews(packageName, R.layout.fragment_notifications)

// Set the username and message text in the custom layout
        customView.setTextViewText(R.id.username_text, username)
        customView.setTextViewText(R.id.message_text, message)

// Set icons (already defined in the layout)
        customView.setImageViewResource(R.id.person_icon, R.drawable.baseline_person_24)
        customView.setImageViewResource(R.id.message_icon, R.drawable.baseline_message_24)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification) // Small icon in the status bar

            .setCustomContentView(customView) // Set the custom view
            .setStyle(NotificationCompat.DecoratedCustomViewStyle()) // Required for custom views
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

// Show the notification
        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

        private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Message Channel"
            val descriptionText = "Channel for message notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
