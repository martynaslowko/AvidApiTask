package Models;

public class MediaItem {
    private String essenceType;
    private String track;
    private int start;
    private int length;
    private String mobId;
    private boolean online;
    private String type;

    public String getEssenceType() {
        return essenceType;
    }

    public String getTrack() {
        return track;
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }

    public String getMobId() {
        return mobId;
    }

    public boolean isOnline() {
        return online;
    }

    public String getType() {
        return type;
    }

    public void setEssenceType(String essenceType) {
        this.essenceType = essenceType;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setMobId(String mobId) {
        this.mobId = mobId;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setType(String type) {
        this.type = type;
    }
}
