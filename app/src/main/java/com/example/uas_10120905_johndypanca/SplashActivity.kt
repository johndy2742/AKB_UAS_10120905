package com.example.uas_10120905_johndypanca

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.uas_10120905_johndypanca.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 3000 // 3 seconds
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the MediaPlayer with the sound file
        mediaPlayer = MediaPlayer()

        // Set the audio attributes to handle different Android versions
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()
        mediaPlayer.setAudioAttributes(audioAttributes)

        // Load the sound file from the raw resource folder
        val soundUri = Uri.parse("android.resource://${packageName}/${R.raw.loading}")
        mediaPlayer.setDataSource(this, soundUri)

        // Set a listener to handle errors and start the sound when it's prepared
        mediaPlayer.setOnPreparedListener {
            it.start()

            // Start the MainActivity after the specified delay
            Handler().postDelayed({
                val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }, SPLASH_DELAY)
        }

        // Set a listener to handle errors during loading
        mediaPlayer.setOnErrorListener { _, what, extra ->
            // Handle errors here (if needed)
            // Return false if you want to propagate the error handling to the next error listener
            false
        }

        // Prepare the MediaPlayer
        mediaPlayer.prepareAsync()
    }

    override fun onPause() {
        super.onPause()
        // Release the MediaPlayer when the activity is paused or destroyed
        mediaPlayer.release()
    }
}
