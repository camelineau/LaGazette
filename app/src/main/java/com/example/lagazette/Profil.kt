package com.example.lagazette

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profil : AppCompatActivity() {
    lateinit var bottomNavigationView : BottomNavigationView
    lateinit var myButton: Button
    lateinit var myTextView: TextView
    lateinit var myEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        myButton = findViewById(R.id.bt_button)
        myTextView = findViewById(R.id.tv_text)
        myEditText = findViewById(R.id.et_text)

        loadData()

        myButton.setOnClickListener{
            saveData()
        }


        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.ic_accueil
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_accueil -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0,0);
                    return@OnNavigationItemSelectedListener true
                };

                R.id.ic_list_article -> {
                    startActivity(Intent(applicationContext, ListActu::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.ic_profil -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }


    private fun saveData(){
        val insertedText=myEditText.text.toString()
        myTextView.text = insertedText

        val sharedPreferences=getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.apply{
            putString("STRING_KEY", insertedText)
        }.apply()
        Toast.makeText(this,"Sauvegarde réussie",Toast.LENGTH_SHORT).show()
    }

    private fun loadData(){
        val sharedPreferences=getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString= sharedPreferences.getString("STRING_KEY",null)

        myTextView.text=savedString
    }
}