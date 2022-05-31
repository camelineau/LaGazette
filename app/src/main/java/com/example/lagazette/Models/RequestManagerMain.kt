package com.example.lagazette.Models

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.lagazette.OnFetchDataListener
import com.example.lagazette.R
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Exception

class RequestManagerMain(var context: Context) {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNewsHeadlines(listener: OnFetchDataListener<*>, country: String?, sortBy: String?, query: String?, PageSize: Int?) {
        val callNewsApi = retrofit.create(CallNewsApi::class.java)
        val call =
            callNewsApi.callHeadlines(country, sortBy, query, PageSize, context.getString(R.string.api_key))
        //val callMain = callNewsApi.callMainHeadlines("fr", "popularity", "purée", 4, context.getString(R.string.api_key))
        try {
            call.enqueue(object : Callback<NewsApiResponse> {
                override fun onResponse(
                    call: Call<NewsApiResponse>,
                    response: Response<NewsApiResponse>
                ) {
                    /*if (response.isSuccessful) {
                        Toast.makeText(context, "Error !!", Toast.LENGTH_SHORT).show()
                    }*/
                    listener.onFetchData(response.body()!!.articles, response.message())
                }

                override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                    listener.onError("Request Failed!")
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface CallNewsApi {
        @GET("top-headlines")
        fun callHeadlines(
            @Query("country") country: String?,
            @Query("sortBy") sortBy: String?,
            @Query("q") query: String?,
            @Query("PageSize") PageSize: Int?,
            @Query("apiKey") api_key: String?
        ): Call<NewsApiResponse>

    }
}