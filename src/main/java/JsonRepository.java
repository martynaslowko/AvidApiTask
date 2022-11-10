import Models.Folder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.io.IOException;
import java.util.LinkedHashMap;

public class JsonRepository {
    private LinkedHashMap<String, Folder> map;

    public JsonRepository(byte[] bytes) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        setMap(objectMapper.readValue(bytes, new TypeReference<LinkedHashMap<String, Folder>>(){}));
    }

    public LinkedHashMap<String, Folder> getMap() {
        return map;
    }

    public void setMap(LinkedHashMap<String, Folder> map) {
        this.map = map;
    }
}
