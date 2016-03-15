package ll.ajplustask.endpoints;

import android.util.Log;

import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import org.greenrobot.eventbus.EventBus;

import ll.ajplustask.models.LoadVideoListEvent;
import ll.ajplustask.models.SearchVideosResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Le on 2016/3/14.
 */

/***
 * Endpoint for making API request and process response
 */
public class AJVideoEndpoint extends BaseEndpoint {
    public static final String AJPLUSID = "2848955552001";

    public interface VideoService {
        /**
         * http://api.brightcove.com/services/library?command=search_videos&page_size=25
         * &video_fields=id,name,shortDescription,creationDate,publishedDate,linkURL,linkText,tags,videoStillURL,thumbnailURL,length,renditions,iOSRenditions,HDSRenditions //given
         * &video_fields=id,name,shortDescription,thumbnailURL,length,HLSURL  //current using
         * &media_delivery=default
         * &sort_by=PUBLISH_DATE:DESC
         * &page_number=0
         * &get_item_count=true
         * &token=LD4NFI0aYDQPuMqQcOSikE1iC8cqGaJFyNocNsS708N693c7oNn9lw..
         */
        @GET("library")
        Call<SearchVideosResponse> getLastVideos(@Query("command") String command,
                                                 @Query("page_size") Integer pageSize,
                                                 @Query("video_fields") String fields,
                                                 @Query("media_delivery") String mediaDeliveryOption,
                                                 @Query("sort_by") String condition,
                                                 @Query("page_number") Integer pageNumber,
                                                 @Query("get_item_count") Boolean isGetItemCount,
                                                 @Query("token") String token);
    }

    public AJVideoEndpoint() {
        this(GIVENTOKEN);
    }

    public AJVideoEndpoint(String accessToken) {
        super(accessToken);
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        httpClient.interceptors().add(logging);
        Retrofit retrofit = builder.client(httpClient).build();
        Log.d("User", retrofit.toString());
        mVideoService = retrofit.create(VideoService.class);
    }

    private final VideoService mVideoService;

    public void requestLastVideos() {
        Call<SearchVideosResponse> calls = mVideoService.getLastVideos("search_videos",
                25,
                "id,name,shortDescription,videoStillURL,length,HLSURL",
                "default",
                "PUBLISH_DATE:DESC",
                0,
                true,
                GIVENTOKEN);
        calls.enqueue(new Callback<SearchVideosResponse>() {
            @Override
            public void onResponse(Response<SearchVideosResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    EventBus.getDefault().post(new LoadVideoListEvent(response.body().getItems())); //once list created from json, send event to update listview
                }
            }
            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
