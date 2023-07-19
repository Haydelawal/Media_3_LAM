package com.example.bg_1_audio_video_image.ui.home.bottom_nav_fragments.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.navArgs
import com.example.bg_1_audio_video_image.R
import com.example.bg_1_audio_video_image.databinding.ActivityOfflinePlayerBinding
import com.example.bg_1_audio_video_image.databinding.ActivityPlayAudioBinding
import com.example.bg_1_audio_video_image.ui.home.bottom_nav_fragments.offline_download_manager.DemoUtil
import com.example.bg_1_audio_video_image.ui.home.bottom_nav_fragments.offline_download_manager.DownloadTracker

class OfflinePlayerActivity : AppCompatActivity(), Player.Listener {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityOfflinePlayerBinding.inflate(layoutInflater)
    }

    private var player: Player? = null

    private var playWhenReady = true
    private var mediaItemIndex = 0
    private var playbackPosition = 0L

    private var downloadTracker: DownloadTracker? = null
    private val playbackStateListener: Player.Listener = playbackStateListener()


    private val args: PlayAudioActivityArgs by navArgs()


    companion object {

        fun getIntent(context: Context): Intent {
            return Intent(context, OfflinePlayerActivity::class.java)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        downloadTracker = DemoUtil.getDownloadTracker(this)
//        binding.download.visibility = View.GONE
//        binding.playOffline.visibility = View.GONE
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

                val mediaItem = MediaItem.fromUri(args.audioData.media_url)

                exoPlayer.setMediaItems(listOf(mediaItem), mediaItemIndex, playbackPosition)
                exoPlayer.addListener(playbackStateListener) // Add this line


                exoPlayer.playWhenReady = playWhenReady

                exoPlayer.prepare()
            }

    }

    private fun releasePlayer() {
        player?.let { player ->
            playbackPosition = player.currentPosition
            mediaItemIndex = player.currentMediaItemIndex
            playWhenReady = player.playWhenReady

            player.removeListener(playbackStateListener) // Add this line


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
                    val builder = AlertDialog.Builder(this@OfflinePlayerActivity)
                    builder.setTitle("Audio Player")
                    builder.setMessage("There Was An Error While Loading Audio. Do You Want To Retry?")
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

                    val builder = AlertDialog.Builder(this@OfflinePlayerActivity)
                    builder.setTitle("Audio Player")
                    builder.setMessage("The Audio File Has Ended")
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