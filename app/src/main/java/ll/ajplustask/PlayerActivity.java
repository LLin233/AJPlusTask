package ll.ajplustask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.brightcove.player.analytics.Analytics;
import com.brightcove.player.model.Video;
import com.brightcove.player.view.BrightcoveVideoView;

import ll.ajplustask.endpoints.AJVideoEndpoint;
import ll.ajplustask.models.Item;

public class PlayerActivity extends AppCompatActivity {

    private BrightcoveVideoView brightcoveVideoView;
    private Toolbar mToolbar;
    private Item mSelectedVideo;
    private TextView mTitleTextView;
    private TextView mShortDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mSelectedVideo = getIntent().getParcelableExtra(MainActivity.PARRM_KEY);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        brightcoveVideoView = (BrightcoveVideoView) findViewById(R.id.brightcove_video_view);
        mTitleTextView = (TextView) findViewById(R.id.title);
        mShortDescriptionTextView = (TextView) findViewById(R.id.shortDescription);

        setUpToolBar();
        setUpInfoDisplay();

        Analytics analytics = brightcoveVideoView.getAnalytics();
        analytics.setAccount(AJVideoEndpoint.AJPLUSID);
        brightcoveVideoView.add(Video.createVideo(mSelectedVideo.HLSURL));
        brightcoveVideoView.start();

    }

    private void setUpInfoDisplay() {
        mShortDescriptionTextView.setText(mSelectedVideo.shortDescription);
        mTitleTextView.setText(mSelectedVideo.name);
    }

    private void setUpToolBar() {
        mToolbar.setTitle("Playing");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
