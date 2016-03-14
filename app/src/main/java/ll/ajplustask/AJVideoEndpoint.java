package ll.ajplustask;

import android.util.Log;

import com.google.gson.JsonElement;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Le on 2016/3/14.
 */
public class AJVideoEndpoint extends BaseEndpoint {
    public interface VideoService {
        /**
         * http://api.brightcove.com/services/library?command=search_videos&page_size=25
         * &video_fields=id,name,shortDescription,creationDate,publishedDate,linkURL,linkText,tags,videoStillURL,thumbnailURL,length,renditions,iOSRenditions,HDSRenditions
         * &media_delivery=default
         * &sort_by=PUBLISH_DATE:DESC
         * &page_number=0
         * &get_item_count=true
         * &token=LD4NFI0aYDQPuMqQcOSikE1iC8cqGaJFyNocNsS708N693c7oNn9lw..
         */
        @GET("library")
        Call<JsonElement> getLastVideos(@Query("command") String command,
                                        @Query("page_size") Integer pageSize,
                                        @Query("video_fields") String fields,
                                        @Query("media_delivery") String mediaDeliveryOption,
                                        @Query("sort_by") String condition,
                                        @Query("page_number") Integer pageNumber,
                                        @Query("get_item_count") Boolean isGetItemCount,
                                        @Query("token") String token);
    }

    protected AJVideoEndpoint() {
        this(GIVENTOKEN);
    }

    protected AJVideoEndpoint(String accessToken) {
        super(accessToken);
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        httpClient.interceptors().add(logging);
        Retrofit retrofit = builder.client(httpClient).build();
        Log.d("User", retrofit.toString());
        mVideoService = retrofit.create(VideoService.class);
    }

    private final VideoService mVideoService;

    public void requestLastVideos() {
        Call<JsonElement> calls = mVideoService.getLastVideos("search_videos",
                25,
                "id,name,shortDescription,creationDate,publishedDate,linkURL,linkText,tags,videoStillURL,thumbnailURL,length,renditions,iOSRenditions,HDSRenditions",
                "default",
                "PUBLISH_DATE:DESC",
                0,
                true,
                GIVENTOKEN);
        calls.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.v(TAG, response.body().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
