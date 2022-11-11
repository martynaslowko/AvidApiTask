package Servlets;

import Models.Asset;
import Models.Folder;
import Repository.JsonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class FolderDetailsServlet extends HttpServlet {
    private Map<String, Folder> repository;
    private final ArrayList<String> availableTypes =
            new ArrayList<>(Arrays.asList("filemob", "masterclip", "subclip", "sequence", "group"));

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
        String URL = request.getRequestURL().toString();
        String file = URL.substring(URL.indexOf("folders/")+"folders/".length());
        String decodedPath = URLDecoder.decode(file, StandardCharsets.ISO_8859_1);
        Folder folder = repository.get(decodedPath);
        if (folder == null) response.sendError(HttpServletResponse.SC_NOT_FOUND);
        else if (request.getParameterMap().containsKey("type")) {
            if (!availableTypes.contains(request.getParameter("type")))
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            ArrayList<Asset> assets = folder.getAssets();
            assets.removeIf(asset -> !Objects.equals(asset.getBase().getType(), request.getParameter("type")));
            folder.setAssets(assets);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        out.print(objectMapper.writeValueAsString(folder));
        out.flush();
    }
}
