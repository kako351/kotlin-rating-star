package com.kako351.kotlinratingstar.kotlinratingstar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children

class KotlinRatingStar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var maxStar: Int
    private var minStar: Int
    private var rating: Int
    private var ratingColor: Int
    private var readOnly: Boolean

    private val offColor = Color.LTGRAY

    private var changeLister: ((Int) -> Unit)? = null

    private var layout = this

    init {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.KotlinRatingStar, 0, 0)
        maxStar = a.getInt(R.styleable.KotlinRatingStar_maxStar, 2)
        minStar = a.getInt(R.styleable.KotlinRatingStar_minStar, 1)
        rating = a.getInt(R.styleable.KotlinRatingStar_rating, 2)
        readOnly = a.getBoolean(R.styleable.KotlinRatingStar_readOnly, true)
        ratingColor = a.getColor(R.styleable.KotlinRatingStar_ratingColor, Color.DKGRAY)
        init()
        a.recycle()
    }

    private fun init() {
        for (i in this.minStar..this.maxStar) {
            layout.addView(createStar(
                i,
                i <= rating
            ))
        }
    }

    private fun createStar(position: Int, isRate: Boolean) = ImageView(context).apply {
        setImageDrawable(ResourcesCompat.getDrawable(context.resources, R.drawable.ic_star_24dp, null))
        val l = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        l.weight = 1f
        layoutParams = l

        updateColorFilter(this, isRate)
        if(readOnly) setOnClickListener { updateRating(position) }
    }

    private fun updateRating(rating: Int) {
        layout.children.forEachIndexed { index, view ->
            if( view is ImageView) { updateColorFilter(view, index < rating) }
        }
        this.rating = rating
        changeLister?.invoke(rating)
    }

    private fun updateColorFilter(imageView: ImageView, isRating: Boolean) {
        val colorFilter = when(isRating) {
            true -> ratingColor
            false -> offColor
        }
        imageView.setColorFilter(colorFilter)
    }

    fun setRatingChangeListener(listener: (rating: Int) -> Unit) {
        changeLister = listener
    }
}
