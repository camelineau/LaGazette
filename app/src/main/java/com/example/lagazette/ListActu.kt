package com.example.lagazette

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.app.ProgressDialog
import android.os.Bundle
import com.example.lagazette.Models.RequestManager
import com.example.lagazette.Models.NewsApiResponse
import com.example.lagazette.Models.NewsHeadlines
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.SearchView
import com.example.lagazette.DetailArticle
import com.google.android.material.bottomnavigation.BottomNavigationView

class ListActu : AppCompatActivity(), SelectListener, View.OnClickListener {
    lateinit var recyclerView: RecyclerView
    var adapter: CustomAdapter? = null
    var dialog: ProgressDialog? = null
    lateinit var b1: Button
    lateinit var b2: Button
    lateinit var b3: Button
    lateinit var b4: Button
    lateinit var b5: Button
    lateinit var b6: Button
    lateinit var b7: Button
    lateinit var searchView: SearchView
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_actu)
        searchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                dialog!!.setTitle("Recherche de nouveaux article de $query")
                dialog!!.show()
                val manager = RequestManager(this@ListActu)
                manager.getNewsHeadlines(listener, "general", query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        dialog = ProgressDialog(this)
        dialog!!.setTitle("Recherche de nouveaux articles..")
        dialog!!.show()
        b1 = findViewById(R.id.btn_1)
        b1.setOnClickListener(this)
        b2 = findViewById(R.id.btn_2)
        b2.setOnClickListener(this)
        b3 = findViewById(R.id.btn_3)
        b3.setOnClickListener(this)
        b4 = findViewById(R.id.btn_4)
        b4.setOnClickListener(this)
        b5 = findViewById(R.id.btn_5)
        b5.setOnClickListener(this)
        b6 = findViewById(R.id.btn_6)
        b6.setOnClickListener(this)
        b7 = findViewById(R.id.btn_7)
        b7.setOnClickListener(this)
        val manager = RequestManager(this)
        manager.getNewsHeadlines(listener, "general", null)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setSelectedItemId(R.id.ic_accueil)
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_accueil -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0,0);
                    return@OnNavigationItemSelectedListener true
                };

                R.id.ic_list_article -> {
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

    private val listener: OnFetchDataListener<NewsApiResponse?> =
        object : OnFetchDataListener<NewsApiResponse?> {
            override fun onFetchData(list: List<NewsHeadlines?>?, message: String?) {
                if (list != null) {
                    if (list.isEmpty()) {
                        Toast.makeText(this@ListActu, "Pas d'articles trouvés", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        showNews(list as List<NewsHeadlines>)
                        dialog!!.dismiss()
                    }
                }
            }

            override fun onError(message: String?) {
                Toast.makeText(this@ListActu, "Une erreur est survenue !", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    private fun showNews(list: List<NewsHeadlines>) {
        recyclerView = findViewById(R.id.recycler_main)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(GridLayoutManager(this, 1))
        adapter = CustomAdapter(this, list, this)
        recyclerView.setAdapter(adapter)
    }

    override fun OnNewsClicked(headlines: NewsHeadlines?) {
        startActivity(
            Intent(this@ListActu, DetailArticle::class.java)
                .putExtra("data", headlines)
        )
    }

    override fun onClick(v: View) {
        val button = v as Button
        val category = button.text.toString()
        dialog!!.setTitle("Recherche de nouveaux articles de $category")
        dialog!!.show()
        val manager = RequestManager(this)
        manager.getNewsHeadlines(listener, category, null)
    }
}