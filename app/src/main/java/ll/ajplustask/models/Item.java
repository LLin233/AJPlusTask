package ll.ajplustask.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Le on 2016/3/14.
 */
public class Item implements Parcelable {
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
                ", shortDescription='" + shortDescription + '\'' +
                ", videoStillURL='" + videoStillURL + '\'' +
                ", HLSURL='" + HLSURL + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.shortDescription);
        dest.writeString(this.videoStillURL);
        dest.writeInt(this.length);
        dest.writeString(this.HLSURL);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.shortDescription = in.readString();
        this.videoStillURL = in.readString();
        this.length = in.readInt();
        this.HLSURL = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}