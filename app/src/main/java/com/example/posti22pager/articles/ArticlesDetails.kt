package com.example.posti22pager.articles

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posti22pager.R
import com.example.posti22pager.databinding.ActivityArticlesDetailsBinding
import com.example.posti22pager.model.Article
import com.example.posti22pager.tools.ARTICLES_DETALES_INDEX
import com.example.posti22pager.tools.SHARPREF_ALMA
import com.example.posti22pager.tools.SHARPREF_ARTICLRS_ARRAY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ArticlesDetails : AppCompatActivity() {
    private lateinit var binding: ActivityArticlesDetailsBinding
    var articles = ArrayList<Article>()
    private var articlesIndex = 0
    private var backGroundColor = ""
    private var textColor = ""
    private var texti = ""
    lateinit var gson: Gson
    lateinit var pref: SharedPreferences
    lateinit var currentArticle: Article
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityArticlesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = getSharedPreferences(SHARPREF_ALMA, MODE_PRIVATE)
        gson = Gson()
        articles = loadArticles()

        articlesIndex = intent.getIntExtra(ARTICLES_DETALES_INDEX, 0)
        currentArticle = findArticle(articlesIndex)
        draw_current_article()

    }

    private fun draw_current_article() {
        backGroundColor = currentArticle.articleBackground
        textColor = currentArticle.articleTextColor
        texti = currentArticle.aricleText

      


        binding.mainBackground.setBackgroundColor(Color.parseColor(backGroundColor))
        binding.tvArticle.setTextColor(Color.parseColor(textColor))
        binding.tvTitle.setTextColor(Color.parseColor(textColor))
        binding.tvTitle.text = setTextTitle()
        binding.tvArticle.text = texti
    }

    private fun setTextTitle(): String =
        "---------------------------------\n" +
                "${currentArticle.aricleTitle}\n" +
                "--------------------------------- "

    fun loadArticles(): ArrayList<Article> {
        articles.clear()
        val json: String? = pref.getString(SHARPREF_ARTICLRS_ARRAY, null)
        val type: Type = object : TypeToken<ArrayList<Article>>() {}.type
        // val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
        val arr: ArrayList<Article> = gson.fromJson(json, type)
        return arr
    }

    private fun findArticle(key: Int): Article {
        for (art in articles) {
            if (art.aricleNum == key) {
                return art
            }
        }
        return Article()
    }
}