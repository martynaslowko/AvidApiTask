import Models.Folder;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        JsonRepository repository = new JsonRepository();
        for (Map.Entry<String, Folder> entry : repository.getMap().entrySet()){
            System.out.println(entry.getKey());
        }
    }
}
