package com.example.sampleesp

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView


class SplashActivity : AppCompatActivity() {

    lateinit var image:ImageView
    lateinit var title:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        image = findViewById(R.id.imageSplash)
        title = findViewById(R.id.titleSplash)

        ObjectAnimator.ofFloat(image, "translationX", -100f).apply {
            duration = 2000
            start()
        }
        ObjectAnimator.ofFloat(title, "translationX", 80f).apply {
            duration = 2000
            start()
        }


        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
        },4000)
    }
}
