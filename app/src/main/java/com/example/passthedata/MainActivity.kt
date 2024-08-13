package com.example.passthedata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var usernameEdt: EditText
    lateinit var msgEdt: EditText
    lateinit var passDataBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the EditTexts and Button
        usernameEdt = findViewById(R.id.idEdtUsername)
        msgEdt = findViewById(R.id.idEdtMsg)
        passDataBtn = findViewById(R.id.idBtnPassData)

        // Set a click listener for the button
        passDataBtn.setOnClickListener {
            // Get data from the EditTexts
            val username = usernameEdt.text.toString()
            val msg = msgEdt.text.toString()

            // Create an Intent to open MainActivity2
            val i = Intent(this@MainActivity, MainActivity2::class.java)

            // Pass the username and message to the new activity
            i.putExtra("username", username)
            i.putExtra("message", msg)

            // Start the new activity
            startActivity(i)
        }
    }
}
