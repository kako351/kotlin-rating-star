package com.kako351.kotlinratingstar.kotlinratingstar

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat

class KotlinRatingStar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var maxStar: Int
    private var minStar: Int

    private var layout = this

    init {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.KotlinRatingStar, 0, 0)
        maxStar = a.getInt(R.styleable.KotlinRatingStar_maxStar, 2)
        minStar = a.getInt(R.styleable.KotlinRatingStar_minStar, 1)
        init()
        a.recycle()
    }

    private fun init() {
        for (i in this.minStar..this.maxStar) {
            layout.addView(createStar())
        }
    }

    private fun createStar() = ImageView(context).apply {
        setImageDrawable(ResourcesCompat.getDrawable(context.resources, R.drawable.ic_star_24dp, null))
        val l = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        l.weight = 1f
        layoutParams = l
    }
}
