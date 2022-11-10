import Models.Folder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        JsonRepository repository = new JsonRepository(Files.readAllBytes(Paths.get("web/WEB-INF/data.json")));
        for (Map.Entry<String, Folder> entry : repository.getMap().entrySet()){
            System.out.println(entry.getKey());
            Folder folder = entry.getValue();
            System.out.println(folder);
        }
    }
}
