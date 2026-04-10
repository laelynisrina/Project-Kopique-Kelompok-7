package com.example.kopiqueapps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtWelcome = findViewById<TextView>(R.id.txtWelcome)

        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val btnMaps = findViewById<Button>(R.id.btnMaps)
        val btnShare = findViewById<Button>(R.id.btnShare)

        btnOrder.setOnClickListener {

            val uri = Uri.parse("https://wa.me/6281234567890")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        btnMaps.setOnClickListener {

            val uri = Uri.parse("geo:0,0?q=Kopique Coffee Shop")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        btnShare.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,
                "Ayo pesan kopi di Kopique Coffee Shop ☕")

            startActivity(Intent.createChooser(intent,"Share via"))
        }
    }
}