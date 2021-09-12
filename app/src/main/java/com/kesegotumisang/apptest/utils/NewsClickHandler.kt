package com.kesegotumisang.apptest.utils

import android.content.Context
import android.content.Intent
import android.view.View
import com.kesegotumisang.apptest.DetailActivity
import com.kesegotumisang.apptest.models.Article


class NewsClickHandler(context: Context) {
    private val context: Context = context
    fun onSaveClick(view: View, article: Article?) {
        val nextInt = Intent(view.context, DetailActivity::class.java)
        nextInt.putExtra("SELECTED_NEWS", article)
        context.startActivity(nextInt)
    }

}