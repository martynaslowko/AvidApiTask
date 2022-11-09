import Models.Folder;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class FolderServlet extends HttpServlet{
    private Map<String, Folder> repository;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (repository == null){
            byte[] data = getServletConfig().getServletContext().getResource("WEB-INF/data.json").getFile().getBytes();
            repository = new JsonRepository(data).getMap();
        }
        response.getOutputStream().println(repository.entrySet().toString());
    }
}
