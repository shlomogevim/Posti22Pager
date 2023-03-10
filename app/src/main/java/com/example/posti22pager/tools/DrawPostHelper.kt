package com.example.posti22pager.tools

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.example.posti22pager.model.Post


class DrawPostHelper: AppCompatActivity() {
    val helper= Helper()
    lateinit var layout: ConstraintLayout
    //    lateinit var currentPost:Post
    var position1=""
    var margin1=0
    var dis1=0

    fun drawPost( constraintLayout: ConstraintLayout, post: Post) {
       // var post=updateTextLocation(currentPost)

        layout=constraintLayout
       setCurrentParameters(post)
      loadImage(constraintLayout, post)
      createText(constraintLayout,post)
//        logi("34      currentPost.postNum=${currentPost.postNum}")

//       ***********
//      drawHelperPostNum(post,constraintLayout)
//      ***********
    }

  /*  private fun updateTextLocation(post: Post): Post {
       val post1=updateTextSize(post)
       when (post1.postNum){
            4999000,4999034,4999035,4999053->{post1. textLocation = arrayListOf(10,-1,0,0,0,0, 0, 0) }                         //up
            4999009,4999013, 4999014,4999015,4999016,4999018,4999019,4999020,4999025,4999028,4999029,4999030,4999031,4999032,4999033,4999036,4999037,4999038->{ post1. textLocation = arrayListOf(10,0,0,-1,0,0, 0, 0) }                        //down
            4999048,4999051,4999052,4999054,4999058,4999059,4999071->{ post1. textLocation = arrayListOf(10,0,0,-1,0,0, 0, 0) }                        //down
            4999004,4999012->{ post1. textLocation = arrayListOf(10,5,0,-1,0,0, 0, 0) }
                }
        return post1
    }*/
  /*  private fun updateTextSize(post: Post): Post {
//        logi((" 57  ${post.postNum}"))
      val post1=updateBackGroundColor(post)
        when (post1.postNum){
                4999029,4999058,4999059->{post1.postTextSize =arrayListOf(0, 14) }
            4999002,4999007->{post1.postTextSize =arrayListOf(0, 15) }
            4999003,4999005,4999006,4999008,4999009,4999012,4999015,4999016,4999017,4999021,4999027,4999031,4999032,4999039,4999040->{post1.postTextSize =arrayListOf(0, 16) }
        }
        return post1
    }*/
   /* private fun updateBackGroundColor(post: Post): Post {
       val post1=updatePostTransparency(post)
        when (post1.postNum){
            4999014,4999025,4999039-> post1.postBackground =  "#000000"
        }
        return post1
    }*/
 /*   private fun updatePostTransparency(post: Post): Post {
       val post1 = post.copy()
        when (post1.postNum){
            4999025,4999014,4999039,4999052,4999071-> post1.postTransparency=10
            4999026-> post1.postTransparency=3
        }
        return post1
    }*/

    private fun drawHelperPostNum(currentPost: Post, constraintLayout: ConstraintLayout) {
        Helper1().createCenteredTextView2(currentPost.postNum.toString(),constraintLayout)
    }
     private fun setCurrentParameters(post:Post) {
        //    textLocation = arrayListOf(10,-1, 33,10,0,0, 0, 0)
      //  var post1=update_post(currentPost)
        val dataAr =post.textLocation
        dis1 = dataAr[2]
        if (dataAr[1] == -1) {
            position1 =TOP
            margin1= dataAr[3]
        }
        if (dataAr[3] == -1) {
            position1 = BOTTOM
            margin1= dataAr[1]
        }
//        logi("56 dis1=$dis1        position1=$position1     margin1=$margin1")
    }
    private fun createText(constraintLayout: ConstraintLayout, post:Post) {

//        logi("61   post1.postNum=${post1.postNum}    ")
//        logi("62   post1.textlocation=${post1.textLocation}    ")
        //    textLocation = arrayListOf(10,-1, 33,10,0,0, 0, 0)
        val mainLayout = createLinearLayout(layout.context,post)
        mainLayout.id = View.generateViewId()
        constraintLayout.addView(mainLayout)
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.connect(mainLayout.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(mainLayout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        if (position1 == TOP) {
            constraintSet.connect(mainLayout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, margin1.toPx())
        } else {
            constraintSet.connect(mainLayout.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM,margin1.toPx())
        }
        constraintSet.applyTo(constraintLayout)
        //setContentView(constraintLayout)
    }

    fun createLinearLayout(context: Context,post:Post): LinearLayout {
        val ll1 = LinearLayout(context)
        ll1.orientation = LinearLayout.VERTICAL
        ll1.gravity= Gravity.CENTER_HORIZONTAL
        val textViews = createTextViews(context,post)
        for (i in textViews) {
            ll1.addView(i)
        }
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        when (position1) {
            TOP -> layoutParams.topMargin = margin1.toPx()

            BOTTOM -> layoutParams.bottomMargin =margin1.toPx()
        }
        ll1.layoutParams = layoutParams
        return ll1
    }

   private fun createTextViews(context: Context, post:Post): Array<TextView?> {
       val textViews = arrayOfNulls<TextView>(post.postText.size)
       for (index in 0 until post.postText.size) {
           textViews[index] = createTextView(context, post, index)
       }
       return textViews
   }

    private fun createTextView(context: Context, post:Post, index:Int): TextView {
        //var post=update_post(post1)
        val textView = TextView(context)
        textView.id = View.generateViewId()
       textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.text = post.postText[index]
        textView.textSize = post.postTextSize[1].toFloat()
        textView.setTextColor(Color.parseColor(updateColor(post.postTextColor[1])))
        val shape = GradientDrawable()
        shape.cornerRadius = post.postRadiuas.toPx().toFloat()
        shape.setColor(Color.parseColor(updateColor(post.postBackground)))

        post.postBackground = post.postBackground.replace("#", "")
        post.postBackground = post.postBackground.replace("$", "")
        val tra = helper.getTransfo(post.postTransparency)
        shape.setColor(Color.parseColor("#$tra${post.postBackground}"))
        textView.background = shape
        val pad =post.postPadding
        textView.setPadding(pad[0].toPx(),pad[1].toPx(), pad[2].toPx(), pad[3].toPx())
        val typeface = helper.getFamilyFont(post.postFontFamily)
        textView.typeface = ResourcesCompat.getFont(layout.context, typeface)
        textView.setLineSpacing(0f, post.lineSpacing)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0,dis1.toPx(), 0, 0)
      layoutParams.gravity = Gravity.CENTER
        textView.layoutParams = layoutParams
        return textView

    }

    private fun loadImage(layout: ConstraintLayout, post: Post) {
        val imageView = ImageView(layout.context)
        imageView.id = View.generateViewId()
        val params = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
//        params.dimensionRatio = "H,1:1"
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        imageView.layoutParams = params

        /*  if (dxMovment==0 && dyMovment==0){
              imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }else{
            imageView.scaleType = ImageView.ScaleType.MATRIX
            val matrix = Matrix()
            post.textLocation[4]=dxMovment
            post.textLocation[5]=dyMovment
            matrix.postTranslate(dxMovment.toPxf(),dyMovment.toPxf(),)
            logi( "DrawPostHelper 160  dxMovment=$dxMovment  post.textLocation=${post.textLocation}")

//       matrix.postScale(0.9f, 1.0f)
            imageView.imageMatrix = matrix
        }*/

        val dxMovment=post.textLocation[4]
        val dyMovment=post.textLocation[5]
        if (dxMovment==0 && dyMovment==0){
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }else {
            imageView.scaleType = ImageView.ScaleType.MATRIX
            val matrix = Matrix()
            post.textLocation[4]=dxMovment
            post.textLocation[5]=dyMovment
            matrix.postTranslate(dxMovment.toPxf(),dyMovment.toPxf(),)
//            logi( "DrawPostHelper 160  dxMovment=$dxMovment  post.textLocation=${post.textLocation}")
            imageView.imageMatrix = matrix
        }

        layout.addView(imageView)
        Glide.with(layout.context)
            .load(post.imageUri)
            .into(imageView)
    }
    fun Int.toPxf(): Float = (this * Resources.getSystem().displayMetrics.density)
    fun updateColor(str: String): String {
        return "#" + str.replace("[^A-Za-z0-9]".toRegex(), "")
    }

    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()



    fun logi(message: String) {
        Log.i("gg", message)
    }




}
