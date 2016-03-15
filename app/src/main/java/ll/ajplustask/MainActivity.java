package ll.ajplustask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ll.ajplustask.adapters.ListAdapter;
import ll.ajplustask.endpoints.AJVideoEndpoint;
import ll.ajplustask.models.LoadVideoListEvent;

public class MainActivity extends AppCompatActivity {
    public static String PARRM_KEY = "Parcelable.ITEM";
    private AJVideoEndpoint mAJVideoEndpoint;
    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);


        mAJVideoEndpoint = new AJVideoEndpoint();
        setUpToolBar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new ListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent playVideo = new Intent(MainActivity.this, PlayerActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(PARRM_KEY, mAdapter.getItems().get(position));
                playVideo.putExtras(mBundle);
                ActivityCompat.startActivity(MainActivity.this, playVideo, null);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAJVideoEndpoint.requestLastVideos();
    }

    private void setUpToolBar() {
        mToolbar.setTitle("AJPLUSTask");
        setSupportActionBar(mToolbar);
    }

    @Subscribe
    public void onEvent(LoadVideoListEvent event) {
        mAdapter.setDataSets(event.getVideoLists());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

}
