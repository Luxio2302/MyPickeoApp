package com.example.mypickeoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.mypickeoapp.databinding.ActivitySplashScreenBinding


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sideAnimation = AnimationUtils.loadAnimation(this,R.anim.slide)

        binding.ivLogo.startAnimation(sideAnimation)
        binding.progressBar.startAnimation(sideAnimation)

        Handler().postDelayed({
            startActivity(Intent(this,UserSession::class.java))
            finish()
        },5000)

    }


}