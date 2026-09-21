package com.mixcassettetv.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mixcassettetv.R
import com.mixcassettetv.service.AudioService

class MainActivity : AppCompatActivity() {

    private lateinit var btnPlayPause: Button
    private lateinit var btnPrev: Button
    private lateinit var btnNext: Button
    private lateinit var tvLyrics: TextView
    private lateinit var ivLeftSpool: ImageView
    private lateinit var ivRightSpool: ImageView
    private lateinit var videoContainerView: View

    private var isPlaying = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupListeners()
        startAudioService()
        
        loadCurrentTrackWithRetry(3)
    }

    private fun initViews() {
        btnPlayPause = findViewById(R.id.btnPlayPause)
        btnPrev = findViewById(R.id.btnPrev)
        btnNext = findViewById(R.id.btnNext)
        tvLyrics = findViewById(R.id.tvLyrics)
        ivLeftSpool = findViewById(R.id.ivLeftSpool)
        ivRightSpool = findViewById(R.id.ivRightSpool)
        videoContainerView = findViewById(R.id.videoContainerView)
    }

    private fun setupListeners() {
        btnPlayPause.setOnClickListener {
            isPlaying = !isPlaying
            if (isPlaying) {
                btnPlayPause.text = "PAUSE"
                startAudioService()
            } else {
                btnPlayPause.text = "PLAY"
            }
        }

        btnPrev.setOnClickListener {
            loadCurrentTrackWithRetry(3)
        }

        btnNext.setOnClickListener {
            loadCurrentTrackWithRetry(3)
        }
    }

    private fun startAudioService() {
        val intent = Intent(this, AudioService::class.java)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }

    private fun loadCurrentTrackWithRetry(retriesLeft: Int) {
        tvLyrics.text = "Cargando letra y sincronización..."
        
        val isLoadedSuccessfully = retriesLeft < 3 

        if (isLoadedSuccessfully || retriesLeft <= 0) {
            tvLyrics.text = "♪ [MODO KARAOKE] ♪\n\n'Reproduciendo pista actual'\n• Línea 1 de la canción...\n• Línea 2 de la canción..."
        } else {
            handler.postDelayed({
                loadCurrentTrackWithRetry(retriesLeft - 1)
            }, 1000)
        }
    }

    private fun onVideoClosed() {
        videoContainerView.visibility = View.GONE
        tvLyrics.visibility = View.VISIBLE
        
        if (!isPlaying) {
            isPlaying = true
            btnPlayPause.text = "PAUSE"
        }
    }
}
