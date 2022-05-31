package com.example.lagazette

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profil : AppCompatActivity() {
    lateinit var bottomNavigationView : BottomNavigationView
    lateinit var myButton: Button
    lateinit var myTextView: TextView
    lateinit var myEditText: EditText
    lateinit var spinner : Spinner
    lateinit var txt_lang : TextView
    val lang = arrayOf("ar", "de", "en", "es", "fr", "he", "it", "nl", "no", "pt", "ru", "sv", "ud", "zh")
    lateinit var chosenLang: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        myButton = findViewById(R.id.bt_button)
        myTextView = findViewById(R.id.tv_text)
        myEditText = findViewById(R.id.et_text)
        txt_lang = findViewById(R.id.txt_lang_choice)

        loadData()

        myButton.setOnClickListener{
            saveData()
        }

        spinner = findViewById(R.id.spin_lang)
        val arrayAdapter = ArrayAdapter(this@Profil, android.R.layout.simple_spinner_dropdown_item, lang)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chosenLang=lang[position]
                Toast.makeText(this@Profil, chosenLang, Toast.LENGTH_LONG)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

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

        val insertedLang=chosenLang
        txt_lang.text=insertedLang

        val sharedPreferences=getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.apply{
            putString("STRING_KEY", insertedText)
        }.apply()
        editor.apply{
            putString("LANG_KEY", insertedLang)
        }.apply()

        Toast.makeText(this,"Sauvegarde réussie",Toast.LENGTH_SHORT).show()
    }

    private fun loadData(){
        val sharedPreferences=getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val savedString= sharedPreferences.getString("STRING_KEY",null)
        myTextView.text=savedString

        val savedLang= sharedPreferences.getString("LANG_KEY",null)
        txt_lang.text=savedLang
    }

}