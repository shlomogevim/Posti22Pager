package com.example.posti22pager.tools

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class Helper1 : AppCompatActivity() {

    fun createCenteredTextView(message: String, context: Context): TextView {
        val textView = TextView(context)
        textView.id= View.generateViewId()
        textView.textSize=50f
        textView.text = message
        textView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        return textView
    }

    fun createCenteredTextView2(message: String, layout: ConstraintLayout) {
        val context = layout.context
        val textView = createTextView(message, context)
        textView.id= View.generateViewId()
        textView.textSize=20f
        textView.setPadding(100,0,100,0)
        textView.setBackgroundColor(Color.WHITE)
        addTextViewToLayout(textView, layout)
        centerTextView(textView, layout)
    }

    fun createTextView(message: String, context: Context): TextView {
        val textView = TextView(context)
        textView.text = message
        textView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        return textView
    }

    fun addTextViewToLayout(textView: TextView, layout: ConstraintLayout) {
        layout.addView(textView)
    }

    fun centerTextView(textView: TextView, layout: ConstraintLayout) {
        val constraints = ConstraintSet()
        constraints.clone(layout)
        constraints.connect(textView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        constraints.connect(textView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
        constraints.connect(textView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
        constraints.connect(textView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
        constraints.centerHorizontally(textView.id, ConstraintSet.PARENT_ID)
        constraints.centerVertically(textView.id, ConstraintSet.PARENT_ID)
        constraints.applyTo(layout)
    }
    fun logi(message: String) {
        Log.i("gg", message)
    }
}