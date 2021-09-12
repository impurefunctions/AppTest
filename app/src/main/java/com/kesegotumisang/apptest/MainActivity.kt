package com.kesegotumisang.apptest

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kesegotumisang.apptest.NewsApp.getNewsList
import com.kesegotumisang.apptest.NewsApp.setNewsList
import com.kesegotumisang.apptest.adapters.ArticleAdapter
import com.kesegotumisang.apptest.api.ApiClient.client
import com.kesegotumisang.apptest.api.ApiInterface
import com.kesegotumisang.apptest.models.ApiResponse
import com.kesegotumisang.apptest.models.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {
    private var noDataTv: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var listType: Type? = null
    private var gson: Gson? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inits()
        if (isOnline(this)) {
            callApi("all-sections", "7") // for section - all-sections, sports, international
        } else {
            Toast.makeText(
                this@MainActivity,
                "No network connection. Loaded Offline data",
                Toast.LENGTH_LONG
            ).show()
            savedData
        }
    }

    private fun isOnline(activity: MainActivity): Boolean {
        val cm =
            (activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnectedOrConnecting
    }

    private fun inits() {
        listType = object : TypeToken<List<Article?>?>() {}.type
        gson = Gson()
        progressBar = findViewById(R.id.progressBar)
        noDataTv = findViewById(R.id.textViewNoData)
        recyclerView = findViewById(R.id.recycler_article)
    }

    private fun callApi(section: String, period: String) {
        val apiService = client!!.create(ApiInterface::class.java)
        val call: Call<ApiResponse?>? = apiService.getNewsDetails(section, period, apiKey())
        call!!.enqueue(object : Callback<ApiResponse?> {
            override fun onResponse(call: Call<ApiResponse?>?, response: Response<ApiResponse?>) {
                val statusCode: Int = response.code()
                if (statusCode == 200) {
                    val articleList: List<Article> = response.body()!!.getResults()
                    saveData(articleList)
                } else {
                    showError("Server Problem. Try again!")
                }
            }

            override fun onFailure(call: Call<ApiResponse?>?, t: Throwable) {
                showError(t.message)
            }
        })
    }

    private external fun apiKey(): String

    private fun saveData(articleList: List<Article>) {
        //LIST DATA CONVERT TO GSON STRING
        val gsonStr: String = gson!!.toJson(articleList, listType)
        setNewsList(this, gsonStr)
        savedData
    }//CONVERT TO LIST

    //GET SAVED DATA
    private val savedData: Unit
        private get() {
            //GET SAVED DATA
            val gsonList = getNewsList(this)
            if (gsonList != "n/a") {
                //CONVERT TO LIST
                val newsList = gson!!.fromJson<List<Article>>(gsonList, listType)
                setupRecycler(newsList)
            } else {
                showError("No saved news to display...!")
            }
        }

    private fun setupRecycler(dataList: List<Article>?) {
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView!!.adapter = ArticleAdapter(this, dataList!!)
        assert(dataList != null)
        if (dataList!!.size > 0) {
            dataVisible()
        } else {
            showError("No News..!")
        }
    }

    private fun showError(message: String?) {
        noDataTv!!.text = message
        progressBar!!.visibility = View.GONE
        recyclerView!!.visibility = View.GONE
        noDataTv!!.visibility = View.VISIBLE
    }

    private fun dataVisible() {
        noDataTv!!.visibility = View.GONE
        progressBar!!.visibility = View.GONE
        recyclerView!!.visibility = View.VISIBLE
    }
}


/*  *//**
 * A native method that is implemented by the 'apptest' native library,
 * which is packaged with this application.
 *//*
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'apptest' library on application startup.
        init {
            System.loadLibrary("apptest")
        }
    }*/