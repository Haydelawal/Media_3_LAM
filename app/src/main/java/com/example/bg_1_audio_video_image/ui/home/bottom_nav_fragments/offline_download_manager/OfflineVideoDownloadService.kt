/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.bg_1_audio_video_image.ui.home.bottom_nav_fragments.offline_download_manager

import android.app.Notification
import android.content.Context
import com.example.bg_1_audio_video_image.ui.home.bottom_nav_fragments.offline_download_manager.DemoUtil.getDownloadManager
import com.example.bg_1_audio_video_image.ui.home.bottom_nav_fragments.offline_download_manager.DemoUtil.getDownloadNotificationHelper
import androidx.media3.exoplayer.offline.DownloadService
import androidx.media3.exoplayer.offline.DownloadNotificationHelper
import androidx.media3.exoplayer.scheduler.PlatformScheduler
import androidx.media3.exoplayer.offline.Download
import androidx.media3.common.util.NotificationUtil
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.offline.DownloadManager
import androidx.media3.exoplayer.scheduler.Scheduler
import com.example.bg_1_audio_video_image.R
import com.example.bg_1_audio_video_image.ui.home.bottom_nav_fragments.offline_download_manager.DemoUtil.DOWNLOAD_NOTIFICATION_CHANNEL_ID
import java.lang.Exception

/**
 * A service for downloading media.
 */
@androidx.annotation.OptIn(UnstableApi::class)

class OfflineVideoDownloadService : DownloadService(
    FOREGROUND_NOTIFICATION_ID,
    DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL,
    DOWNLOAD_NOTIFICATION_CHANNEL_ID,
    R.string.exo_download_notification_channel_name,  /* channelDescriptionResourceId= */
    0
) {
    override fun getDownloadManager(): DownloadManager {
        // This will only happen once, because getDownloadManager is guaranteed to be called only once
        // in the life cycle of the process.
        val downloadManager = getDownloadManager( /* context= */this)
        val downloadNotificationHelper = getDownloadNotificationHelper( /* context= */this)
        downloadManager!!.addListener(
            TerminalStateNotificationHelper(
                this, downloadNotificationHelper, FOREGROUND_NOTIFICATION_ID + 1
            )
        )
        return downloadManager
    }

    override fun getScheduler(): Scheduler? {
        return if (Util.SDK_INT >= 21) PlatformScheduler(this, JOB_ID) else null
    }

    override fun getForegroundNotification(
        downloads: List<Download>,
        notMetRequirements: Int
    ): Notification {

        return getDownloadNotificationHelper(this)
            ?.buildProgressNotification(
                this,
                R.mipmap.ic_launcher,  /* contentIntent= */
                null,  /* message= */
                "Downloading ",
                downloads,
                notMetRequirements
            )!!
    }

    /**
     * Creates and displays notifications for downloads when they complete or fail.
     *
     *
     * This helper will outlive the lifespan of a single instance of [OfflineVideoDownloadService].
     * It is static to avoid leaking the first [OfflineVideoDownloadService] instance.
     */
    private class TerminalStateNotificationHelper(
        context: Context, notificationHelper: DownloadNotificationHelper?, firstNotificationId: Int
    ) : DownloadManager.Listener {
        private val context: Context
        private val notificationHelper: DownloadNotificationHelper?
        private var nextNotificationId: Int
        override fun onDownloadChanged(
            downloadManager: DownloadManager,
            download: Download,
            finalException: Exception?
        ) {
//            super@Listener.onDownloadChanged(downloadManager, download, finalException)
            val notification: Notification
            notification = if (download.state == Download.STATE_COMPLETED) {

//                val intent = Intent(context, OfflinePlayerActivity::class.java)

                notificationHelper!!.buildDownloadCompletedNotification(
                    context,
                    R.drawable.exo_ic_check,  /* contentIntent= */
                    null,
                    Util.fromUtf8Bytes(download.request.data)
                )
            }


            else if (download.state == Download.STATE_FAILED) {
                notificationHelper!!.buildDownloadFailedNotification(
                    context,
                    R.drawable.exo_icon_stop,  /* contentIntent= */
                    null,
                    Util.fromUtf8Bytes(download.request.data)
                )
            } else {
                return
            }
            NotificationUtil.setNotification(context, nextNotificationId++, notification)
        }

        init {
            this.context = context.applicationContext
            this.notificationHelper = notificationHelper
            nextNotificationId = firstNotificationId
        }
    }

    companion object {
        private const val JOB_ID = 1
        private const val FOREGROUND_NOTIFICATION_ID = 1
    }
}