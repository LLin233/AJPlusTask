# Summay

## List view
It will show last videos on a list with thumbnails of the video. When you tap one item it will go to the video screen.

* List view is implemented using RecyclerView. Content is set only thumbnails and name of video.  Thumbnails are to small to fit my design, I changed it to videoStillURL instead.
* Image loading is implemented using glide with full disk cache configuration.

## Video screen

It will show a Brightcove player, not on full screen, to play the video by id (you can use the method findVideoWithVideoID or similar). The controls should include a tap to full screen mode. Additionally on top you should have a Back button to go to the first List view screen.
* Video can be created from HSLurl which included in the response when we requested our list. Video model is parcelable so that it could send to player with intent. This would reduce unnecessary HTTP request from device.
* User could navigate back from tapping toolbar.
* Used BrightcoveVideoView as video play.

## Network

API request is implemented with retrofit 2.0 and okhttp as HttpClient.

## Dependencies

```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.1.1'

    compile 'com.android.support:recyclerview-v7:23.1.1'
    compile 'com.android.support:cardview-v7:23.1.1'

    compile 'com.squareup.okhttp:logging-interceptor:2.6.0'
    compile 'com.squareup.okhttp:okhttp:2.6.0'

    compile 'com.squareup.retrofit:converter-gson:2.0.0-beta2'
    compile 'com.squareup.retrofit:retrofit:2.0.0-beta2'

    compile "com.brightcove.player:exoplayer:4.+"

    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'org.greenrobot:eventbus:3.0.0'
}

```

## Demo Video
**[Demo](https://youtu.be/bJ-qqnF_u84)**

## Something detail be done if needed
* network error handling : checking network status before sending request, this requires extra permission. 
* better look.

## Candidate: Leonard Lin (linle.job@gmail.com)
