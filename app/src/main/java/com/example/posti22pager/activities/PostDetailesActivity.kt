package com.example.posti22pager.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.posti22pager.databinding.ActivityPostDetailesBinding
import com.example.posti22pager.model.Post
import com.example.posti22pager.tools.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer2
import java.lang.reflect.Type
import kotlin.random.Random

class PostDetailesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailesBinding
    lateinit var pref: SharedPreferences
    lateinit var currentPost: Post
    var movingMode=""
    /*
      var util = UtilityPost()
    var currentUser: User? = null
    var textViewArray = ArrayList<TextView>()
    lateinit var commentsRV: RecyclerView
    lateinit var commentAdapter: CommentAdapter
    var comments = ArrayList<Comment>()
    var message = ""
    lateinit var newUtil1: NewUtilities
  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
        currentPost = loadCurrentPost()
        pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, currentPost.postNum).apply()
         movingMode = pref.getString(SHARPREF_MOVING_BACKGROUND, FALSE).toString()
       setMovingModeTextBtn()
        drawHeadline()
        btnSetting()

    }

    private fun setMovingModeTextBtn() {
        if (movingMode== FALSE){
            binding.movingModeBtn.text="סטטי"
        }else{
            binding.movingModeBtn.text="דינמי"
        }
    }

    private fun btnSetting() {
        val btn=binding.movingModeBtn
        btn.setOnClickListener {
            if (movingMode== FALSE){
                movingMode= TRUE
                pref.edit().putString(SHARPREF_MOVING_BACKGROUND, TRUE).apply()
            } else{
                movingMode= FALSE
                pref.edit().putString(SHARPREF_MOVING_BACKGROUND, FALSE).apply()
            }
            setMovingModeTextBtn()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.helpBtn.setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
            finish()
        }
        binding.definitionBtn.setOnClickListener {
            startActivity(Intent(this, SetupActivity::class.java))
            finish()

        }
    }

    fun loadCurrentPost(): Post {
        val gson = Gson()
        val json: String? = pref.getString(SHARPREF_CURRENT_POST, null)
        val type: Type = object : TypeToken<Post>() {}.type
        val post: Post = gson.fromJson(json, type)
        return post
    }

    private fun drawHeadline() {
//        val st = "  פוסט מספר: " + currentPost.postNum.toString()
//        val st = " מספר: " + currentPost.postNum.toString()
        val st =  currentPost.postNum.toString()
        binding.postNumber.text = st
        // logi("PostDetailsActivity  233  post=$currentPost    \n post.postText.size= ${currentPost.postText.size}")
        drawPostText()
    }

    private fun drawPostText() {
        val combineText = currentPost.postText.joinToString(separator = "\n")
        binding.textPost.text = combineText
        binding.textPost.setHorizontallyScrolling(false)

    }
    fun logi(message: String) {
        Log.i("gg", message)
    }

}





    /*   val numlinesToShow = Math.min(currentPost.postText.size, 5)
//        val myLinearLayout = LinearLayout(this)
    val myLinearLayout = binding.textPostLayout

    val parentLayout=myLinearLayout.parent as ViewGroup
    parentLayout.removeView(myLinearLayout)
    val scrollView = ScrollView(this)
    scrollView.addView(myLinearLayout)

    myLinearLayout.orientation = LinearLayout.VERTICAL

    for (i in 0 until numlinesToShow) {
        val textView = TextView(this)
        textView.textSize = 12f
        textView.setTextColor(Color.WHITE)
        textView.setBackgroundColor(Color.GREEN)
        textView.text = currentPost.postText[i]
        myLinearLayout.addView(textView)
    }

    setContentView(scrollView)*/


/*
        val textPostLayout=binding.textPostLayout
        val scrollView=binding.scrollView
        for (i in 0..currentPost.postText.size){
            val textView=TextView(this)
            textView.text=currentPost.postText[i]
            textPostLayout.addView(textView)
            textPostLayout.layoutParams.height=LinearLayout.LayoutParams.WRAP_CONTENT
          scrollView.layoutParams.height=0*/

    /*val scrollView = binding.scrollView
    scrollView.isScrollContainer = false // disable scrolling

    val textPostLayout = binding.textPostLayout
    textPostLayout.orientation = LinearLayout.VERTICAL

    val postText = currentPost.postText

    for (i in 0 until postText.size) {
        if (i == 5) break // maximum of 5 lines
        val textView = TextView(this)
        textView.text = postText[i]
        textPostLayout.addView(textView)
    }

    val layoutParams = FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    )
    textPostLayout.layoutParams = layoutParams*/

    /* val scrollView = binding.scrollView

    scrollView.isVerticalScrollBarEnabled = true // enable vertical scrollbar
    scrollView.isHorizontalScrollBarEnabled = false // disable horizontal scrollbar

    val textPostLayout = binding.textPostLayout
    textPostLayout.orientation = LinearLayout.VERTICAL

    val postText = currentPost.postText

    for (i in 0 until postText.size) {
        if (i == 5) break // maximum of 5 lines
        val textView = TextView(this)
        textView.text = postText[i]
        textPostLayout.addView(textView)
    }

    val layoutParams = FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    )
    textPostLayout.layoutParams = layoutParams

    scrollView.post {
        // set scrollview height dynamically based on content
        val height = textPostLayout.measuredHeight
        val params = scrollView.layoutParams
        params.height = height.coerceAtMost(scrollView.height)
        scrollView.layoutParams = params
    }
}*/
    /*  val scrollView = binding.scrollView
      scrollView.isFillViewport = true
      scrollView.isVerticalScrollBarEnabled = true // enable vertical scrollbar
      scrollView.isHorizontalScrollBarEnabled = false // disable horizontal scrollbar

      val textPostLayout = binding.textPostLayout
      textPostLayout.orientation = LinearLayout.VERTICAL

      val postText = currentPost.postText

      for (i in 0 until postText.size) {
          if (i == 5) break // maximum of 5 lines
          val textView = TextView(this)
          textView.text = postText[i]
          textPostLayout.addView(textView)
      }

      val layoutParams = textPostLayout.layoutParams as LinearLayout.LayoutParams
      layoutParams.height =
          resources.getDimension(R.dimen.max_text_post_height).toInt() // set maximum height
      textPostLayout.layoutParams = layoutParams*/





