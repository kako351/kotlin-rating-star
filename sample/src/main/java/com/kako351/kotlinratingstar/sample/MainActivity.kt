package com.kako351.kotlinratingstar.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kako351.kotlinratingstar.kotlinratingstar.KotlinRatingStar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ratingStar = this.findViewById<KotlinRatingStar>(R.id.ratingStar)
        ratingStar.setRatingChangeListener {
            Log.d("KotlinRatingStar", "${it}")
        }
    }
}
