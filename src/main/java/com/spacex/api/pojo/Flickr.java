
package com.spacex.api.pojo;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Flickr {

    @SerializedName("small")
    @Expose
    private List<Object> small = null;
    @SerializedName("original")
    @Expose
    private List<String> original = null;

    public List<Object> getSmall() {
        return small;
    }

    public void setSmall(List<Object> small) {
        this.small = small;
    }

    public List<String> getOriginal() {
        return original;
    }

    public void setOriginal(List<String> original) {
        this.original = original;
    }

}
