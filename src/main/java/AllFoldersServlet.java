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

public class AllFoldersServlet extends HttpServlet{
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

        Integer limit = null;
        Integer skip = null;

        try {
            if (request.getParameterMap().containsKey("limit")) {
                limit = Integer.parseInt(request.getParameter("limit"));
                if (limit < 0) throw new IOException("Limit value under 0");
            }
            if (request.getParameterMap().containsKey("skip")){
                skip = Integer.parseInt(request.getParameter("skip"));
                if (limit != null && skip >= limit) throw new IOException("Skip value over/equals limit");
                if (skip > repository.size()) throw new IOException("Skip value over repository size");
                if (skip < 0) throw new IOException("Skip value under 0");
            }
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getStatus();
            return;
        }

        Map<String, ArrayList<Map<String, Object>>> results = new LinkedHashMap<>();
        ArrayList<Map<String, Object>> folders = new ArrayList<>();
        int counter = 0;
        for (Map.Entry<String, Folder> entry : repository.entrySet()){
            if (request.getParameterMap().containsKey("search") &&
                    !entry.getKey().contains(request.getParameter("search")))
                continue;
            if (limit != null && limit == counter)
                break;
            counter++;
            if (skip != null && skip > counter)
                continue;
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
