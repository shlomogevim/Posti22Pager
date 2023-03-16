package com.example.posti22pager.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.posti22pager.articles.ArticlesActivity
import com.example.posti22pager.databinding.ActivitySetupBinding
import com.example.posti22pager.model.Post
import com.example.posti22pager.tools.*

class SetupActivity : AppCompatActivity(){
    private lateinit var binding: ActivitySetupBinding
    private lateinit var pref:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref=getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)

        setupButtons()
    }

    private fun setupButtons() {
        binding.btnArticles.setOnClickListener {
                startActivity(Intent(this, ArticlesActivity::class.java))
            finish()
        }
        binding.btnTimeOrder.setOnClickListener {
            pref.edit().putString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_TIME_PUBLISH).apply()
            pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, 0).apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.btnSuffelOrder.setOnClickListener {
            pref.edit().putString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_SUFFEL).apply()
            pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, 0).apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    fun logi(message: String) {
        Log.i("gg", message)
    }
}