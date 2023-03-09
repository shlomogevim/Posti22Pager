package com.example.posti22pager.tools

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class PostPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val scaleFactor = 0.8f
        val alphaFactor = 0.5f
        if (position < -1) {
            page.alpha = 0f
        } else if (position <= 1) {
            page.scaleX = Math.max(scaleFactor, 1 - Math.abs(position))
            page.scaleY = Math.max(scaleFactor, 1 - Math.abs(position))
            page.alpha = Math.max(alphaFactor, 1 - Math.abs(position))
        } else {
            page.alpha = 0f
        }
    }
}
