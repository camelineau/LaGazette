package com.example.lagazette

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.*
import java.util.*


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnActuClick = findViewById<Button>(R.id.btnActu)
        btnActuClick.setOnClickListener{
            val intent = Intent(this, ListeActu::class.java)
            startActivity(intent)
        }
        val btnDetailClick = findViewById<Button>(R.id.btnArticle)
        btnDetailClick.setOnClickListener{
            val intent = Intent(this, DetailArticle::class.java)
            startActivity(intent)
        }
        val btnProfilClick = findViewById<Button>(R.id.btnProfil)
        btnProfilClick.setOnClickListener{
            val intent = Intent(this, Profil::class.java)
            startActivity(intent)

        }
        /*LocalDate today = LocalDate.now()

        curl https://newsapi.org/v2/everything -G \
        -d from=today \
        -d country=fr \
        -d sortBy=popularity \
        -d apiKey=API_KEY*/
    }
}