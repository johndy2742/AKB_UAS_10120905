package com.example.uas_10120905_johndypanca

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.uas_10120905_johndypanca.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 3000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DELAY)
    }
}
