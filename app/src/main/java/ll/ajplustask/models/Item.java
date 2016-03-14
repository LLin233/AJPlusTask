package ll.ajplustask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Le on 2016/3/14.
 */
public class Item {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("shortDescription")
    @Expose
    public String shortDescription;
    @SerializedName("videoStillURL")
    @Expose
    public String videoStillURL;
    @SerializedName("length")
    @Expose
    public int length;
    @SerializedName("HLSURL")
    @Expose
    public String HLSURL;

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", videoStillURL='" + videoStillURL + '\'' +
                ", HLSURL='" + HLSURL + '\'' +
                '}';
    }
}