package com.example.lagazette

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.*
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnActuClick = findViewById<Button>(R.id.btnActu)
        btnActuClick.setOnClickListener{
            val intent = Intent(this, ListActu::class.java)
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
        val today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        println(today)

        var url = "https://newsapi.org/v2/everything?" +
                "from=today&" +
                "country=fr" +
                "sortBy=popularity&" +
                "apiKey=API_KEY";
    }
}