import Models.Folder;
import Models.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

public class FolderServlet extends HttpServlet{
    private LinkedHashMap<String, Folder> repository;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (repository == null){
            byte[] data = Files.readAllBytes(new File(getServletContext()
                    .getResource("WEB-INF/data.json")
                    .getFile())
                    .toPath());
            repository = new JsonRepository(data).getMap();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Result> results = null;
        for (int i = 0; i < repository.size(); i++){
            String path = repository.keySet().toArray()[i].toString();
            Folder folder = repository.get(path);
            Result result = new Result(folder.getId(), path);
            results.put("result", result);
        }
        out.print(objectMapper.writeValueAsString(results));
        out.flush();
    }
}
