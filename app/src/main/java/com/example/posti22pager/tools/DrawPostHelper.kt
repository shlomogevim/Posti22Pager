package com.example.posti22pager.tools

import android.content.Context
import android.content.SharedPreferences
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
import com.example.posti22pager.R
import com.example.posti22pager.model.Post
import com.flaviofaria.kenburnsview.KenBurnsView


class DrawPostHelper(val context: Context): AppCompatActivity() {
    val helper= Helper()
    val pref =context.getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
    var movingBackgroundMode = pref.getString(SHARPREF_MOVING_BACKGROUND, FALSE)

    //    lateinit var currentPost:Post
    var position1=""
    var margin1=0
    var dis1=0

    fun drawPost( constraintLayout: ConstraintLayout, post: Post) {
        setCurrentParameters(post)
      loadImageWithMove(constraintLayout, post)
      createText(constraintLayout,post)
//        logi("34      currentPost.postNum=${currentPost.postNum}")
//       ***********
//      drawHelperPostNum(post,constraintLayout)
//      ***********
    }

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
        val mainLayout = createLinearLayout(context ,post)
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
        textView.typeface = ResourcesCompat.getFont(context, typeface)
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
    private fun loadImageWithMove(layout: ConstraintLayout, post: Post) {
        val imageView=layout.findViewById<ImageView>(R.id.pagerImage)
        val kenBurnsView = layout.findViewById<KenBurnsView>(R.id.tour_image)
//        logi("Draw POst Helper 183           movingBackgroundMode=$movingBackgroundMode")
        if (movingBackgroundMode== TRUE){
            moveToTheSide(post, imageView  )
            Glide.with(layout.context)
                .load(post.imageUri)
                .into(kenBurnsView)
            kenBurnsView.resume()
        }else{
            Glide.with(layout.context)
                .load(post.imageUri)
                .into(imageView)
            kenBurnsView.pause()
        }
    }
    private fun moveToTheSide(post:Post,imageView:ImageView) {
        val dxMovment = post.textLocation[4]
        val dyMovment = post.textLocation[5]
        if (dxMovment == 0 && dyMovment == 0) {
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            imageView.scaleType = ImageView.ScaleType.MATRIX
            val matrix = Matrix()
            post.textLocation[4] = dxMovment
            post.textLocation[5] = dyMovment
            matrix.postTranslate(dxMovment.toPxf(), dyMovment.toPxf())
//            logi( "DrawPostHelper 160  dxMovment=$dxMovment  post.textLocation=${post.textLocation}")
            imageView.imageMatrix = matrix
        }
    }

 /*   private fun loadImage(layout: ConstraintLayout, post: Post) {
//        logi("drawPostHelper 183     post.postNum=${post.postNum}")
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

        val imageView1=layout.findViewById<ImageView>(R.id.pagerImage)
        val kenBurnsView = layout.findViewById<KenBurnsView>(R.id.tour_image)
        var movingBackgroundMode = pref.getString(SHARPREF_MOVING_BACKGROUND, FALSE)
//        movingBackgroundMode= TRUE
        if (movingBackgroundMode== TRUE){
            //moveToTheSide(post, imageView  )
            Glide.with(layout.context)
                .load(post.imageUri)
                .into(kenBurnsView)
            kenBurnsView.resume()
        }else{
            Glide.with(layout.context)
                .load(post.imageUri)
                .into(imageView)
            kenBurnsView.pause()
        }



        layout.addView(imageView)
        Glide.with(layout.context)
            .load(post.imageUri)
            .into(imageView)
    }*/
    /*private fun loadImageWithMove(layout: ConstraintLayout, post: Post) {
        val imageView=layout.findViewById<ImageView>(R.id.pagerImage)
        val kenBurnsView = layout.findViewById<KenBurnsView>(R.id.tour_image)
//        logi("Draw POst Helper 183           movingBackgroundMode=$movingBackgroundMode")
        if (movingBackgroundMode== TRUE){
            moveToTheSide(post, imageView  )
            Glide.with(layout.context)
                .load(post.imageUri)
                .into(kenBurnsView)
            kenBurnsView.resume()
        }else{
            Glide.with(layout.context)
                .load(post.imageUri)
                .into(imageView)
            kenBurnsView.pause()
        }
    }*/
    fun Int.toPxf(): Float = (this * Resources.getSystem().displayMetrics.density)
    fun updateColor(str: String): String {
        return "#" + str.replace("[^A-Za-z0-9]".toRegex(), "")
    }

    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()



    fun logi(message: String) {
        Log.i("gg", message)
    }




}
