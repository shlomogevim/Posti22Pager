package com.example.posti22pager.articles

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posti22pager.R
import com.example.posti22pager.databinding.ActivityArticlesDetailsBinding
import com.example.posti22pager.databinding.ActivityAtoraDetailesBinding
import com.example.posti22pager.model.Article
import com.example.posti22pager.tools.ARTICLES_DETALES_INDEX
import com.example.posti22pager.tools.SHARPREF_ALMA
import com.example.posti22pager.tools.SHARPREF_ARTICLRS_ARRAY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class AtoraDetailesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAtoraDetailesBinding
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
        binding= ActivityAtoraDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = getSharedPreferences(SHARPREF_ALMA, MODE_PRIVATE)
        gson = Gson()
        loadArticles()
        articlesIndex = intent.getIntExtra(ARTICLES_DETALES_INDEX, 0)
        currentArticle = findArticle(articlesIndex)
        draw_current_articles()
    }

    private fun draw_current_articles() {
        backGroundColor = currentArticle.articleBackground
        textColor = currentArticle.articleTextColor
        texti = currentArticle.aricleText


        binding.mainAtoraBackground.setBackgroundColor(Color.parseColor(backGroundColor))
        binding.tvAtoraArticle.setTextColor(Color.parseColor(textColor))
        binding.titleAtoraArticle.setTextColor(Color.parseColor(textColor))
        binding.titleAtoraArticle.text=setTextTitle()
        binding.tvAtoraArticle.text = texti
    }

    private fun setTextTitle(): String =
        "---------------------------------\n" +
                "${currentArticle.aricleTitle}\n" +
                "--------------------------------- "


    private fun loadArticles() {
        articles.clear()
        val gson = Gson()
        val pref = getSharedPreferences(SHARPREF_ALMA, MODE_PRIVATE)
        val json: String? = pref.getString(SHARPREF_ARTICLRS_ARRAY, null)
        val type: Type = object : TypeToken<ArrayList<Article>>() {}.type
        // val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
        articles = gson.fromJson(json, type)
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