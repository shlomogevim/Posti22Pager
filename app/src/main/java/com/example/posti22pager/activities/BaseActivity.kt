package com.example.posti22pager.activities

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.posti22pager.model.Post
import com.example.posti22pager.tools.SHARPREF_ALMA
import com.example.posti22pager.tools.SHARPREF_POSTS_ARRAY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

open class BaseActivity:AppCompatActivity() {

        val pref=getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
    var posts = ArrayList<Post>()

    fun loadPosts():ArrayList<Post>{
        posts.clear()
        val gson= Gson()
        val json: String? =pref.getString(SHARPREF_POSTS_ARRAY,null)
        val type: Type =object : TypeToken<ArrayList<Post>>() {}.type
        // val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
        val arr:ArrayList<Post> =gson.fromJson(json,type)
        return arr
    }
    fun logi(message: String) {
        Log.i("gg", message)
    }
}