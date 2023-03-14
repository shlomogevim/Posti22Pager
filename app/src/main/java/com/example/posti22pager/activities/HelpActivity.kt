package com.example.posti22pager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posti22pager.R
import com.example.posti22pager.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}