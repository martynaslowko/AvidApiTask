package Models;

import java.util.ArrayList;

public class Folder {
    private String path;
    private int id;
    private ArrayList<Asset> assets;

    public int getId() {
        return id;
    }

    public ArrayList<Asset> getAssets() {
        return assets;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAssets(ArrayList<Asset> assets) {
        this.assets = assets;
    }
}
