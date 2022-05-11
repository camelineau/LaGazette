package com.example.lagazette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
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
    }
}