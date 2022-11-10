import Models.Folder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonRepository {
    private Map<String, Folder> map;

    public JsonRepository(byte[] bytes) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        setMap(objectMapper.readValue(bytes, Map.class));
    }

    public Map<String, Folder> getMap() {
        return map;
    }

    public void setMap(Map<String, Folder> map) {
        this.map = map;
    }
}
