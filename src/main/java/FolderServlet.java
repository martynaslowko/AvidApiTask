import Models.Folder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class FolderServlet extends HttpServlet{
    private Map<String, Folder> repository;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (repository == null){
            byte[] data = Files.readAllBytes(Path.of(getServletConfig().getServletContext().getResource("WEB-INF/data.json").getFile()));
            repository = new JsonRepository(data).getMap();
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(repository.entrySet());
    }
}
