import Models.Folder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JsonRepository {
    private Map<String, Folder> map;

    public JsonRepository(byte[] bytes) throws IOException {
        byte[] jsonData = bytes;
        ObjectMapper objectMapper = new ObjectMapper();
        setMap(objectMapper.readValue(jsonData, Map.class));
    }

    public Map<String, Folder> getMap() {
        return map;
    }

    public void setMap(Map<String, Folder> map) {
        this.map = map;
    }
}
