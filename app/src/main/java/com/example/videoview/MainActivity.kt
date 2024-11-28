package com.example.videoview

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.videoview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val links = mutableListOf(
            "https://videocdn.cdnpk.net/joy/content/video/free/2014-06/large_preview/Blue_Sky_and_Clouds_Timelapse_0892__Videvo.mp4?token=exp=1732805802~hmac=bb57344d8bf446f32f5c5dc59d430bba29888a893096768be9e9222cfe320a43",
            "android.resource://$packageName/${R.raw.priroda}"
        )
        var index = 0
        val mediaController = MediaController(this)
        mediaController.setAnchorView(mediaController)

        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(Uri.parse(links[index]))
        binding.videoView.requestFocus()
        binding.videoView.start()

        binding.nextFAB.setOnClickListener {
            if (index == links.size - 1) {
                index = 0
            } else {
                index += 1
            }
            binding.videoView.setVideoURI(Uri.parse(links[index]))
            binding.videoView.start()
        }

        binding.previousFAB.setOnClickListener {
            if (index == 0) {
                index = links.size - 1
            } else {
                index -= 1
            }
            binding.videoView.setVideoURI(Uri.parse(links[index]))
            binding.videoView.start()
        }
    }
}
