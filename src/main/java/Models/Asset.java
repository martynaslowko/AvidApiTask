package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Asset {
    private ArrayList<Attribute> attributes;
    private Base base;
    private String mobId;
    private Common common;
    @JsonProperty("media-items")
    private ArrayList<MediaItem> mediaItems;

    public String getMobId() {
        return mobId;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public Base getBase() {
        return base;
    }

    public Common getCommon() {
        return common;
    }

    public ArrayList<MediaItem> getMediaItems() {
        return mediaItems;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public void setMobId(String mobId) {
        this.mobId = mobId;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public void setMediaItems(ArrayList<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }
}
