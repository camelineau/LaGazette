package com.example.lagazette

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lagazette.Models.NewsApiResponse
import com.example.lagazette.Models.NewsHeadlines
import com.example.lagazette.Models.RequestManagerMain
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.*
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity(), SelectListener {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var recyclerView: RecyclerView
    var adapter: CustomAdapter? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //val today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        //println(today)

        /*var url = "https://newsapi.org/v2/everything?" +
                "from=today&" +
                "country=fr&" +
                "sortBy=popularity&" +
                "apiKey=API_KEY";

         */
        // https://newsapi.org/v2/everything?country=fr&sortBy=popularity&apiKey=dcbd5c2ade994e0989e5f655b22fdc04

        val manager = RequestManagerMain(this)
        manager.getNewsHeadlines(listener, "popularity", "terre")

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

    private val listener: OnFetchDataListener<NewsApiResponse?> =
        object : OnFetchDataListener<NewsApiResponse?> {
            override fun onFetchData(list: List<NewsHeadlines?>?, message: String?) {
                if (list != null) {
                    if (list.isEmpty()) {
                        Toast.makeText(this@MainActivity, "Pas d'articles trouvés", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        showNews(list as List<NewsHeadlines>)
                        //dialog!!.dismiss()
                    }
                }
            }

            override fun onError(message: String?) {
                Toast.makeText(this@MainActivity, "Une erreur est survenue !", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    private fun showNews(list: List<NewsHeadlines>) {
        recyclerView = findViewById(R.id.recycler_main)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(GridLayoutManager(this, 1))
        adapter = CustomAdapter(this, list, ListActu())
        recyclerView.setAdapter(adapter)
    }

     override fun OnNewsClicked(headlines: NewsHeadlines?) {
         val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(headlines!!.url))
         startActivity(browserIntent)
    }

}