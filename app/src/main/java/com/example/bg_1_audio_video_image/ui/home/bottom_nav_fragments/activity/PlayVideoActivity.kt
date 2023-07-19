package com.example.bg_1_audio_video_image.ui.home.bottom_nav_fragments.activity

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.navArgs
import com.example.bg_1_audio_video_image.R
import com.example.bg_1_audio_video_image.databinding.ActivityPlayAudioBinding
import com.example.bg_1_audio_video_image.databinding.ActivityPlayVideoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlayVideoActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlayVideoBinding.inflate(layoutInflater)
    }

    private val playbackStateListener: Player.Listener = playbackStateListener()

    private var player: Player? = null

    private var playWhenReady = true
    private var mediaItemIndex = 0
    private var playbackPosition = 0L

    private val args: PlayVideoActivityArgs by navArgs()

    //activity_main ==>> Activity_Main_Binding
//    private lateinit var binding: ActivityPlayVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityPlayVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
/*
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
*/

    }


    public override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT > 23) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (Build.VERSION.SDK_INT <= 23 || player == null) {
            initializePlayer()
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun initializePlayer() {
        // ExoPlayer implements the Player interface
        player = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                binding.videoView.player = exoPlayer

                val mediaItem = MediaItem.fromUri(args.videoData.media_url)

                exoPlayer.setMediaItems(listOf(mediaItem), mediaItemIndex, playbackPosition)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.addListener(playbackStateListener) // Add this line

                exoPlayer.prepare()
            }
    }

    private fun releasePlayer() {
        player?.let { player ->
            playbackPosition = player.currentPosition
            mediaItemIndex = player.currentMediaItemIndex
            playWhenReady = player.playWhenReady
            player.removeListener(playbackStateListener)

            player.release()
        }
        player = null
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.videoView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun playbackStateListener() = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
//            val stateString: String =
            when (playbackState) {
                ExoPlayer.STATE_IDLE -> {
                    val builder = AlertDialog.Builder(this@PlayVideoActivity)
                    builder.setTitle("Video Player")
                    builder.setMessage("There Was An Error While Loading Video. Do You Want To Retry?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                    builder.setPositiveButton("YES"){ _,_ ->
                        player?.prepare()
                        player?.playWhenReady
                    }
                    builder.setNegativeButton("NO"){ _,_ ->}
                    builder.setCancelable(false)
//                    builder.setOnDismissListener()

                    builder.show()
                    Log.d("MYHTTP", "idle...")

                }
                ExoPlayer.STATE_BUFFERING -> {
                    Log.d("MYHTTP", "buffering...")

                }
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY-"
                ExoPlayer.STATE_ENDED -> {

                    val builder = AlertDialog.Builder(this@PlayVideoActivity)
                    builder.setTitle("Video Player")
                    builder.setMessage("The Video File Has Ended")
                    builder.setCancelable(false)

//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                    builder.setPositiveButton("OKAY"){ _,_ ->}
                    builder.show()
                }
                else -> {}
            }
        }
    }

}