package Models;

import java.util.ArrayList;

public class Folder {
    private int id;
    private ArrayList<Asset> assets;

    public Folder(){}

    public Folder(Folder folder) {
        id = folder.getId();
        assets = folder.getAssets();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Asset> getAssets() {
        return assets;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAssets(ArrayList<Asset> assets) {
        this.assets = assets;
    }
}
