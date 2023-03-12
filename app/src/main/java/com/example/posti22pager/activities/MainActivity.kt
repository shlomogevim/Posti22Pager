package com.example.posti22pager.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.posti22pager.adapters.PostViewPagerAdapter
import com.example.posti22pager.databinding.ActivityMainBinding
import com.example.posti22pager.model.Post
import com.example.posti22pager.tools.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer2
import java.lang.reflect.Type
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
 private lateinit var binding:ActivityMainBinding
    val helper=Helper()
    lateinit var pref: SharedPreferences
    var posts=ArrayList<Post>()
    lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        posts=ArrayList<Post>()
        viewPager=binding.viewpager
//        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//        supportActionBar?.hide()

        pref = getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)

    }
    override fun onResume() {
        super.onResume()
//       logi("MainActivity 63   onResume        ")
        posts.clear()
        posts=loadPosts()
        createViewPager10()
    }

    private fun createViewPager10() {

        val adapter =PostViewPagerAdapter(posts)
        viewPager.adapter = adapter



//        logi("posts1.size=${posts1.size}")
     //   viewPager.adapter = PostViewPagerAdapter(posts)
        viewPager.setOffscreenPageLimit(2)

        val cardFlipPageTransformer = CardFlipPageTransformer2()
        cardFlipPageTransformer.setScalable(false)
        viewPager.setPageTransformer(cardFlipPageTransformer)

        val  currentPostNum = pref.getInt(SHARPREF_CURRENT_POST_NUM, 0)
        if (currentPostNum!=0){
            val postIndex =posts.indexOfFirst { it.postNum== currentPostNum }
            viewPager.setCurrentItem(postIndex, true)
        }

        /*  val cardFlipPageTransformer = CardFlipPageTransformer2()
        cardFlipPageTransformer.setScalable(false)
        binding.viewpager.setPageTransformer(cardFlipPageTransformer)*/
    }


    private fun loadPosts(): ArrayList<Post> {
        val gson = Gson()
        val json: String? = pref.getString(SHARPREF_POSTS_ARRAY, null)
        if (json != null) {
            val type: Type = object : TypeToken<ArrayList<Post>>() {}.type
            val posts: ArrayList<Post> = gson.fromJson(json, type)
            return posts
        } else {
            // handle case where SHARPREF_POSTS_ARRAY is not set in pref
            return ArrayList<Post>()
        }
    }


    fun downloadAllPost(): ArrayList<Post> {
        var posts = ArrayList<Post>()
        val ranges = helper.getRanges()
        posts.addAll(downloadPostsForRanges(ranges))
        return posts
    }


    private fun downloadPostsForRanges(ranges: List<Pair<Int, Int>>): ArrayList<Post> {
        val posts = ArrayList<Post>()
        for (range in ranges) {
            FirebaseFirestore.getInstance().collection(POST_REF)
                .whereGreaterThanOrEqualTo(POST_NUM, range.first)
                .whereLessThanOrEqualTo(POST_NUM, range.second)
                .addSnapshotListener { value, error ->
                    if (value != null) {
                        for (doc in value.documents) {
                            val post = Helper().retrivePostFromFirestore(doc)
                            posts.add(post)
                        }

//                     createViewPager(posts)
//                       createViewPagerWithSuffel_1(posts)
//                       createViewPagerWithSuffel_2(posts)
                        createViewPagerWithSuffel_3(posts)
                    }
                }
        }
        return posts
    }

    private fun createViewPagerWithSuffel_3(posts: ArrayList<Post>) {
        val posts1 = posts.toMutableList()
        posts1.shuffle(Random(System.currentTimeMillis()))
        Handler().postDelayed({
            createViewPager(ArrayList(posts1))
        },1000)
    }

    private fun createViewPagerWithSuffel_2(posts: ArrayList<Post>) {
        task1(posts){result->
            createViewPager(ArrayList(result))
        }
    }

    private fun createViewPagerWithSuffel_1(posts: ArrayList<Post>) {
        val posts1 = posts.toMutableList()
        posts1.shuffle(Random(System.currentTimeMillis()))
        Thread.sleep(300)
        createViewPager(ArrayList(posts1))
    }

    fun task1(posts: ArrayList<Post>, callback: (result: ArrayList<Post>) -> Unit) {
        val posts1 = posts.toMutableList()
        posts1.shuffle(Random(System.currentTimeMillis()))
        callback(ArrayList(posts1))
    }
    private fun createViewPager(posts: ArrayList<Post>) {
        binding.viewpager.adapter = PostViewPagerAdapter(posts)

        //   viewPager.setPageTransformer(PostPageTransformer())

        val cardFlipPageTransformer = CardFlipPageTransformer2()
        cardFlipPageTransformer.setScalable(false)
        binding.viewpager.setPageTransformer(cardFlipPageTransformer)

    }

    fun logi(message: String) {
        Log.i("gg", message)
    }

}

