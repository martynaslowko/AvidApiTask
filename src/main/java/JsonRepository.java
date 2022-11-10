import Models.Folder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonRepository {
    private LinkedHashMap<String, Folder> map;

    public JsonRepository(byte[] bytes) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        setMap(objectMapper.readValue(bytes, LinkedHashMap.class));
    }

    public LinkedHashMap<String, Folder> getMap() {
        return map;
    }

    public void setMap(LinkedHashMap<String, Folder> map) {
        this.map = map;
    }
}
