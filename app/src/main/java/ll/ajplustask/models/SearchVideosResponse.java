package ll.ajplustask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Le on 2016/3/14.
 */
public class SearchVideosResponse {
    @SerializedName("items")
    @Expose
    public List<Item> items;
    @SerializedName("page_number")
    @Expose
    public int pageNumber;
    @SerializedName("page_size")
    @Expose
    public int pageSize;
    @SerializedName("total_count")
    @Expose
    public int totalCount;

    public List<Item> getItems() {
        return items;
    }
}
