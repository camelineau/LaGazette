package com.example.lagazette

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.*
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        println(today)

        var url = "https://newsapi.org/v2/everything?" +
                "from=today&" +
                "country=fr" +
                "sortBy=popularity&" +
                "apiKey=API_KEY";


        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setSelectedItemId(R.id.ic_accueil)
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_accueil -> {
                    return@OnNavigationItemSelectedListener true
                };

                R.id.ic_list_article -> {
                    startActivity(Intent(applicationContext, ListActu::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.ic_profil -> {
                    startActivity(Intent(applicationContext, Profil::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}