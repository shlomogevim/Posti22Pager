package com.example.posti22pager.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.posti22pager.R
import com.example.posti22pager.activities.PostDetailesActivity
import com.example.posti22pager.model.Post
import com.example.posti22pager.tools.DrawPostHelper
import com.example.posti22pager.tools.SHARPREF_ALMA
import com.example.posti22pager.tools.SHARPREF_CURRENT_POST
import com.google.gson.Gson

class PostViewPagerAdapter(private val posts: ArrayList<Post>) : RecyclerView.Adapter<PostViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view,parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(itemView: View,context: Context) : RecyclerView.ViewHolder(itemView) {
        val layout = itemView?.findViewById<ConstraintLayout>(R.id.itemLayout)!!

        init {
            itemView.setOnClickListener {
                val currentPost=posts[adapterPosition]
                val pref = context.getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
                val editor = pref.edit()
                val gson = Gson()
                val json: String = gson.toJson(currentPost)
                editor.putString(SHARPREF_CURRENT_POST, json)
                editor.apply()
                context.startActivity(Intent(context, PostDetailesActivity::class.java))
            }
        }

        fun bindItems(post: Post) {
            DrawPostHelper(layout.context).drawPost(layout, post)
        }
    }
}





/*class PostViewPagerAdapter(val context: Context,private val posts: ArrayList<Post>) : RecyclerView.Adapter<PostViewPagerAdapter.ViewHolder>() {
    val pref = context.getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout = itemView?.findViewById<ConstraintLayout>(R.id.itemLayout)!!
        val image = itemView?.findViewById<ImageView>(R.id.pagerImage)

        fun bindItems(post: Post) {
            DrawPostHelper().drawPost(layout, post)
            image.setOnClickListener {
                val editor = pref.edit()
                val gson = Gson()
                val json: String = gson.toJson(post)
                editor.putString(SHARPREF_CURRENT_POST, json)
                editor.apply()
                context.startActivity(Intent(context, PostDetailesActivity::class.java))
            }
        }
    }
}*/