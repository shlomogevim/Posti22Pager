package com.example.posti22pager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.posti22pager.articles.ArticlesActivity
import com.example.posti22pager.databinding.ActivitySetupBinding
import com.example.posti22pager.model.Post

class SetupActivity : AppCompatActivity(){
    private lateinit var binding: ActivitySetupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()
    }

    private fun setupButtons() {
        binding.btnArticles.setOnClickListener {
                startActivity(Intent(this, ArticlesActivity::class.java))
            finish()
        }
    }
    fun logi(message: String) {
        Log.i("gg", message)
    }
}