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
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FolderDetailsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, Folder> repository = JsonRepository.getRepositoryInstance(getServletContext()).getMap();
        String URL = request.getRequestURL().toString();
        String file = URL.substring(URL.indexOf("folders/")+"folders/".length());
        String decodedPath = URLDecoder.decode(file, StandardCharsets.ISO_8859_1);
        Folder folder;
        try {
            folder = new Folder(repository.get(decodedPath));
        } catch (NullPointerException ex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

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
                if (skip < 0) throw new IOException("Skip value under 0");
            }
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        ArrayList<Asset> assets = getFilteredResults(folder.getAssets(), skip, limit, request, response);

        folder.setAssets(assets);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(objectMapper.writeValueAsString(folder));
        out.flush();
    }

    private ArrayList<Asset> getFilteredResults(ArrayList<Asset> assets, Integer skip, Integer limit, HttpServletRequest request,
                                                HttpServletResponse response) throws IOException {
        ArrayList<String> availableTypes = new ArrayList<>
                (Arrays.asList("filemob", "masterclip", "subclip", "sequence", "group"));
        if (request.getParameterMap().containsKey("type")) {
            if (!availableTypes.contains(request.getParameter("type")))
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            assets.removeIf(asset -> !Objects.equals(asset.getBase().getType(), request.getParameter("type")));
        }
        if (!(skip == null && limit == null)) {
            int counter = 0;
            ArrayList<Asset> assetsCopy = new ArrayList<>();
            for (Asset asset : assets) {
                if (skip != null && skip != counter) {
                    counter++;
                    continue;
                }
                if (limit != null && limit == assetsCopy.size())
                    break;
                assetsCopy.add(asset);
            }
            assets = assetsCopy;
        }
        return assets;
    }
}
