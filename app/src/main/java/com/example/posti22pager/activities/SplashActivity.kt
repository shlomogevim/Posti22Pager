package com.example.posti22pager.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import com.example.posti22pager.databinding.ActivitySplashBinding
import com.example.posti22pager.model.Article
import com.example.posti22pager.model.Post
import com.example.posti22pager.tools.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    lateinit var pref: SharedPreferences
    val helper = Helper()
    var delayInMicroSecond = 0
    var pressHelpBtn = false
    lateinit var timer: CountDownTimer
    var articles = ArrayList<Article>()
    val util = UtilityPost()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        helpBtnOperate()
        getHeadLines()
        downloadAllPost()
        downloadAllArticles()
//          pauseIt()
    }

    fun downloadAllPost(): ArrayList<Post> {
        var posts = ArrayList<Post>()
        val ranges = helper.getRanges()
//        posts.addAll(downloadPostsForRanges(ranges))
        posts.addAll(downloadPosts())
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
//                        logi("57  posts.size=${posts.size}")
                        val posts1 = createSuffelPosts(posts)
                        savePosts(posts1)
                        pref.edit().putInt(SHARPREF_TOTAL_POSTS_SIZE, posts.size).apply()
                    }
                }
        }
        return posts
    }

    private fun downloadPosts(): ArrayList<Post> {
        val posts = ArrayList<Post>()
        FirebaseFirestore.getInstance().collection(POST_REF)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    for (doc in value.documents) {
                        val post = Helper().retrivePostFromFirestore(doc)
                        posts.add(post)
                    }
//                        logi("57  posts.size=${posts.size}")
                    val posts1 = createSuffelPosts(posts)
                    savePosts(posts1)
                    pref.edit().putInt(SHARPREF_TOTAL_POSTS_SIZE, posts.size).apply()
                }
            }
        return posts
    }

    private fun savePosts(posts1: ArrayList<Post>) {
        pref.edit().remove(SHARPREF_POSTS_ARRAY).apply()
        val editor = pref.edit()
        val gson = Gson()
        val json: String = gson.toJson(posts1)
        editor.putString(SHARPREF_POSTS_ARRAY, json)
        editor.apply()
    }

    private fun createSuffelPosts(posts: ArrayList<Post>): ArrayList<Post> {
        val posts1 = posts.toMutableList()
        posts1.shuffle(Random(System.currentTimeMillis()))
        return ArrayList(posts1)
    }

    private fun initData() {
        //gradeArray = arrayListOf()
        pref = getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
        pref.edit().remove(SHARPREF_ALMA).apply()
        pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, 0).apply()
        pref.edit().putString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_TIME_PUBLISH).apply()
//        pref.edit().putString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_RECOMMENDED).apply()
        pref.edit().putString(SHARPREF_MOVING_BACKGROUND, FALSE).apply()
//        delayInMicroSecond = pref.getInt(SHARPREF_SPLASH_SCREEN_DELAY, 10) * 1000
        delayInMicroSecond = pref.getInt(SHARPREF_SPLASH_SCREEN_DELAY, 3) * 1000
    }

    private fun helpBtnOperate() {
        binding.btnHelp.setOnClickListener {
            pressHelpBtn = true
//            startActivity(Intent(this, HelpActivity::class.java))
            pauseIt()
        }
    }

    private fun getHeadLines() {
        setFirstHello()
        timerWorks()
    }

    private fun setFirstHello() {
        val name: String = getUserName()
        val helloSt = "מה קורה " + "\n" +
                "" + "\n" +
                name + "\n" +
                "" + "\n" +
                "מה הענינים " + "\n" +
                "" + "\n" +
                "איך ככה " + "\n" +
                ""
        binding.tvText1.text = helloSt
    }

    private fun getUserName(): String {
//        if (currentUser != null) {
//            return "${currentUser!!.userName} ${currentUser!!.lastName} "
//        } else {
//            return "אורח"
//        }
        return "אורח"
    }
    fun downloadAllArticles(): ArrayList<Article> {
        articles.clear()
        FirebaseFirestore.getInstance().collection(ARTICAL_REF)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    for (doc in value.documents) {
                        val article = util.retriveArticleFromFirestore(doc)
                        articles.add(article)
                    }
                    saveArticles()
                    // showArticuls()
                }
            }
        return articles
    }
    private fun saveArticles() {
//        showArticls()
        pref.edit().remove(SHARPREF_ARTICLRS_ARRAY).apply()
        val editor = pref.edit()
        val gson = Gson()
        val json: String = gson.toJson(articles)
        editor.putString(SHARPREF_ARTICLRS_ARRAY, json)
        editor.apply()
    }
    private fun showArticls() {
//        logi("SplashActivity 130    articles.size=${articles.size}")
        for (index in 0 until articles.size) {
//            logi("SplashActivity 132     index=$index  articles[index].aricleNum=${articles[index].aricleNum}")
        }
    }
    private fun timerWorks() {
//          lottie.animate().translationY(1400f).setDuration(1000).setStartDelay(4000)
        // starteAnimateLottie()
        val animate1 = AlphaAnimation(0f, 1.0f)
        animate1.duration = 5000
        binding.lottie.startAnimation(animate1)
        val st1 = "אל תתיאש עוד: " + "\n" + "\n"
        val st2 = "   שניות   " + "\n" + "\n" +
                "  תתחיל האפליקציה לעבוד "
        binding.lottie.playAnimation()
        timer = object : CountDownTimer(delayInMicroSecond.toLong(), 1000) {
            override fun onTick(remaning: Long) {
                val totalMessage = st1 + (remaning / 1000).toString() + st2
                binding.tvText2.text = totalMessage
            }

            override fun onFinish() {
                timer?.let {
                    binding.lottie.cancelAnimation()
                    binding.tvText2.visibility = View.GONE
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        timer.start()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    private fun pauseIt() {
        Handler().postDelayed(
            {
                if (!pressHelpBtn) {
                    startActivity(Intent(this, MainActivity::class.java))
//                    startActivity(Intent(this, ArticlesActivity::class.java))
//                   startActivity(Intent(this, HelpActivity::class.java))
                }
//           }, delayInMicroSecond.toLong()
            }, 0
        )
    }

    fun logi(message: String) {
        Log.i("gg", message)
    }


}