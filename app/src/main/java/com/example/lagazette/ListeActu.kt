package com.example.lagazette

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import java.util.ArrayList

class ListeActu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_actu)

        val image_details = listData
        val listView = findViewById<View>(R.id.listActu) as ListView
        listView.adapter = CustomListAdapter(this, image_details)

        // Test pour avoir l'erreur lors du lancement
        /*listView.onItemClickListener = OnItemClickListener { a, v, position, id ->
            val o = listView.getItemAtPosition(position)
            val country = o as Country
            Toast.makeText(this@ListeActu, "Selected : $country", Toast.LENGTH_LONG).show()
        }*/
    }

    private val listData: List<Country>
        private get() {
            val list: MutableList<Country> = ArrayList()
            val vietnam = Country("Vietnam", "vn", 98000000)
            val usa = Country("United States", "us", 320000000)
            val russia = Country("Russia", "ru", 142000000)
            list.add(vietnam)
            list.add(usa)
            list.add(russia)
            return list
        }
}