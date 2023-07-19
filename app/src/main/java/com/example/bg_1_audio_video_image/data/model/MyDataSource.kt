package com.example.bg_1_audio_video_image.data.model

object MyDataSource {

    val VIDEO_DUMMY_DATA = listOf<MyVideoData>(
        MyVideoData(
            id = 1,
            title = "Video 1",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video_thumbs%2Faaa.jpg?alt=media&token=1a2b33bc-b2da-4477-ae7e-a60feb9f25bf",
            media_url = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video%2FVIDEO%20ABC.mp4?alt=media&token=a472e414-523b-49a7-b274-59039ed90059",
            publisher = "Babban Gona"
        ),
        MyVideoData(
            id = 2,
            title = "Video 2",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video_thumbs%2Fbbb.jpg?alt=media&token=276ccb1a-943a-4e69-b003-eb7ea6d6bb2a",
            media_url = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video%2FVIDEO%20DEF.mp4?alt=media&token=bcaf454a-0ef1-4cb7-a3d8-8eca9430611a",
            publisher = "Bobba Fett"
        ),
        MyVideoData(
            id = 3,
            title = "Video 3",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video_thumbs%2Fccc.jpg?alt=media&token=0b56920e-168a-4160-a8ac-a80ca83fb6c6",
            media_url = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video%2FVIDEO%20GHI.mp4?alt=media&token=3a0598a0-823f-4999-b3aa-50707c8336c9",
            publisher = "Niklaus"
        ),
        MyVideoData(
            id = 4,
            title = "Video 4",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video_thumbs%2Fddd.jpg?alt=media&token=2f9a114d-478a-4a35-ac13-2446956798b3",
            media_url = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video%2FVIDEO%20JKL.mp4?alt=media&token=2c557c8b-ab08-4eaf-a7ea-c312cc59fbf1",
            publisher = "Leo Messi"
        ),
        MyVideoData(
            id = 5,
            title = "Video 5",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video_thumbs%2Feee.jpg?alt=media&token=74cd3b16-3355-4807-8a9f-88d9c704ae1a",
            media_url = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video%2FVIDEO%20MNO.mp4?alt=media&token=b030b24b-d318-4358-bb82-e64208f1cafe",
            publisher = "Davido"
        ),
        MyVideoData(
            id = 6,
            title = "Video 6",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video_thumbs%2Ffff.jpg?alt=media&token=cbd02078-5481-4411-909e-321102b11865",
            media_url = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/video%2FVIDEO%20PQR.mp4?alt=media&token=c6598a74-9728-4553-b7e4-f45c60dae5b2",
            publisher = "Master Chief"
        )

    )

    val AUDIO_DUMMY_DATA = listOf<MyAudioData>(
        MyAudioData(
            id = 1,
            title = "Nonstop",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio_thumbs%2Fdrake.png?alt=media&token=7aa95af2-8397-4696-8ef2-f538735efcc3",
            media_url = "https://firebasesorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio%2FDrake%20-%20Nonstop.mp3?alt=media&token=89986392-0efd-4db1-a324-b7a164aeb509",
            artiste = "Drake"
        ),
        MyAudioData(
            id = 2,
            title = "Space Cadet",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio_thumbs%2Fmetro.jpg?alt=media&token=70e8f60d-c875-4f6c-ba6c-abbe19fec86a",
            media_url = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio%2F05.%20Space%20Cadet%20(feat.%20Gunna).mp3?alt=media&token=15c6fd1a-f4b7-4bfe-ba98-acec4b674383",
            artiste = "Metro Boomin Gunna"
        ),
        MyAudioData(
            id = 3,
            title = "Dreaming",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio_thumbs%2Fpopsmoke.jpg?alt=media&token=439094f8-0307-4e97-9b92-97e9c78cc777",
            media_url = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio%2FPop%20Smoke%20-%20Dreaming%20(Meet%20The%20Woo%202)%20(320%20kbps).mp3?alt=media&token=4eb34504-c663-4aa7-8ddc-1485556358aa",
            artiste = "Pop Smoke"
        ),
        MyAudioData(
            id = 4,
            title = "What's My Name",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio_thumbs%2Frihanna.jpg?alt=media&token=e502bc14-8d69-4871-80dd-e63390f05c6c",
            media_url = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio%2Frihanna_what_s_my_name.mp3?alt=media&token=42f456bc-a99b-4ddb-af74-552fce3b2b29",
            artiste = "Rihanna"
        ),
        MyAudioData(
            id = 5,
            title = "Main Title",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio_thumbs%2Fqueen.jpg?alt=media&token=115403b9-4629-4e52-9146-6701817e02e0",
            media_url = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio%2FMain%20Title%20from%20the%20Netflix%20Series%20Queen%20Charlotte.mp3?alt=media&token=b8a49688-435d-466c-8ab3-fbc616a0215d",
            artiste = "Kris Bowers"
        ),
        MyAudioData(
            id = 6,
            title = "Wanna Be Starting Something",
            thumbnail = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio_thumbs%2Fmj.png?alt=media&token=51be10f9-cd8e-4d8b-b171-2a391d8ab952",
            media_url = "https://firebasestorage.googleapis.com/v0/b/bg-audio-video-image.appspot.com/o/audio%2FWanna%20Be%20Startin%20Somethin.mp3?alt=media&token=b11aef66-6426-4105-976d-c42c13050da0",
            artiste = "Micheal Jackson"
        )

    )
}