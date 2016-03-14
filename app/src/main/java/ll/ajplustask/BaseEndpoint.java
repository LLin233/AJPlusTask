package ll.ajplustask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Le on 2016/3/14.
 */
public abstract class BaseEndpoint {
    protected String TAG = this.getClass().getSimpleName();
    protected static final String BASE_URL = "http://api.brightcove.com/services/";
    protected static final String GIVENTOKEN = "LD4NFI0aYDQPuMqQcOSikE1iC8cqGaJFyNocNsS708N693c7oNn9lw..";
    protected static OkHttpClient httpClient = new OkHttpClient();
    protected static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    protected static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    protected final String accessToken;

    protected BaseEndpoint(final String accessToken) {
        this.accessToken = accessToken;
    }

}