package com.kesegotumisang.apptest

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import com.kesegotumisang.apptest.NewsApp

object NewsApp : Application() {
    private const val NEWS_LIST = "news_list"
    fun setNewsList(activity: Activity, newsListGson: String?) {
        val sharedPref = activity.getPreferences(MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(NEWS_LIST, newsListGson)
        editor.apply()
    }

    fun getNewsList(activity: Activity): String? {
        val sharedPref = activity.getPreferences(MODE_PRIVATE)
        return sharedPref.getString(NEWS_LIST, "NoData")
    }
}