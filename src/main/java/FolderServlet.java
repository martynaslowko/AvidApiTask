import Models.Folder;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FolderServlet extends HttpServlet{
    private Map<String, Folder> repository;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (repository == null){
            byte[] data = Files.readAllBytes(new File(getServletContext()
                    .getResource("WEB-INF/data.json")
                    .getFile())
                    .toPath());
            repository = new JsonRepository(data).getMap();
        }
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        Map<String, ArrayList<Map<String, Object>>> results = new LinkedHashMap<>();
        ArrayList<Map<String, Object>> folders = new ArrayList<>();
        for (Map.Entry<String, Folder> entry : repository.entrySet()){
            Map<String, Object> details = new HashMap<>();
            details.put("path", entry.getKey());
            details.put("id", repository.get(entry.getKey()).getId());
            folders.add(details);
        }
        results.put("results", folders);

        ObjectMapper objectMapper = new ObjectMapper();
        out.print(objectMapper.writeValueAsString(results));
        out.flush();
    }
}
