package com.kesegotumisang.apptest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kesegotumisang.apptest.databinding.ArticleDataBinding
import com.kesegotumisang.apptest.models.Article
import com.kesegotumisang.apptest.utils.NewsClickHandler


class ArticleAdapter(context: Context, dataList: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private val mContext: Context
    private val articleList: List<Article>
    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val dataBinding: ArticleDataBinding =
            ArticleDataBinding.inflate(layoutInflater!!, parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = articleList[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    inner class ViewHolder(dataBinding: ArticleDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        private val articleDataBinding: ArticleDataBinding = dataBinding
        fun bind(articles: Article?) {
            articleDataBinding.setArticle(articles)
        }

        init {
            articleDataBinding.setHandler(NewsClickHandler(mContext))
        }
    }

    init {
        mContext = context
        articleList = dataList
    }
}