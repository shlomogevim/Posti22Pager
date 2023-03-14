package com.example.posti22pager.articles

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.example.posti22pager.R
import com.example.posti22pager.activities.BaseActivity
import com.example.posti22pager.databinding.ActivityArticlesBinding
import com.example.posti22pager.model.Article
import com.example.posti22pager.tools.ARTICLES_DETALES_INDEX
import com.example.posti22pager.tools.Helper
import com.example.posti22pager.tools.SHARPREF_ALMA
import com.example.posti22pager.tools.SHARPREF_ARTICLRS_ARRAY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ArticlesActivity : AppCompatActivity(){
    private lateinit var binding: ActivityArticlesBinding
    lateinit var gson: Gson
    var articles = ArrayList<Article>()
    lateinit var pref: SharedPreferences
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences(SHARPREF_ALMA, MODE_PRIVATE)
        gson = Gson()
        articles = loadArticles()
        logi("ArticlesActivity  40  articles.size=${articles.size}")
        drawLayout()
    }

    private fun drawLayout() {
        for (index in 0 until articles.size){
            if (index==0){
                creatButton("התורה",0)
            }
            val currentA=articles[index]
            if (currentA.aricleNum>20){
                creatButton(currentA.aricleTitle,currentA.aricleNum)
            }
        }
    }

    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun Int.toSp():Float=(this.toFloat()/resources.displayMetrics.scaledDensity)

    private fun creatButton(title:String,id:Int) {
        button = Button(this)
        button.id = id
        setItOnclick(button)
        button.text=title
        button.setBackgroundResource(R.drawable.button_background1)
        val fontAddress = Helper(). getFamilyFont(103)
        button.typeface = ResourcesCompat.getFont(this, fontAddress)
        button.textSize=16f
        button.setTextColor(Color.parseColor("#ffffff"))
        // button.setBackgroundColor(Color.parseColor("#FF018786"))
        val  radius=16
        val shape = GradientDrawable()
        shape.cornerRadius = radius.toPx().toFloat()
        shape.setColor(Color.parseColor("#FF018786"))
        button.background = shape
        val layoutParam: LinearLayout.LayoutParams=  LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParam.marginStart=16.toPx()
        layoutParam.marginEnd=16.toPx()
        layoutParam.topMargin=16.toPx()
        button.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        // correct padding in the firestore if needed
        binding.articlesLayout.addView(button,layoutParam)
    }

    private fun setItOnclick(button: Button) {
        button.setOnClickListener {
            when (it.id){
                0 -> {
                    val intent = Intent(this, AtoraActivity::class.java)
                    startActivity(intent)
                }
                else  ->{
                    val intent = Intent(this, ArticlesDetails::class.java)
                    intent.putExtra(ARTICLES_DETALES_INDEX, it.id)
                    startActivity(intent)
                }
            }

        }
    }

    private fun loadArticles(): ArrayList<Article> {
        articles.clear()
//        val json: String? = pref.getString(SHARPREF_ARTICLRS_ARRAY, null)
        val json: String? = pref.getString(SHARPREF_ARTICLRS_ARRAY, null)
        logi("124 json=$json")
        if (json!=null){
            val type: Type = object : TypeToken<ArrayList<Article>>() {}.type
            val arr: ArrayList<Article> = gson.fromJson(json, type)
            return arr
            }else{
            return ArrayList()
        }
    }

    fun logi(message: String) {
        Log.i("gg", message)
    }

}