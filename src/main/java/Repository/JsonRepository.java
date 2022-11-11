package Repository;

import Models.Folder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;

public class JsonRepository {
    private LinkedHashMap<String, Folder> map;

    private static JsonRepository repository = null;

    private JsonRepository(ServletContext ctx) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        setMap(objectMapper.readValue(Files.readAllBytes(new File(ctx
                .getResource("WEB-INF/data.json")
                .getFile())
                .toPath()),
                new TypeReference<LinkedHashMap<String, Folder>>(){}));
    }

    public static JsonRepository getRepositoryInstance(ServletContext ctx) throws IOException {
        if (repository == null) {
            repository = new JsonRepository(ctx);
        }
        return repository;
    }

    public LinkedHashMap<String, Folder> getMap() {
        return map;
    }

    private void setMap(LinkedHashMap<String, Folder> map) {
        this.map = map;
    }
}
