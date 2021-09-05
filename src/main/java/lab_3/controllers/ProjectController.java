package lab_3.controllers;

import lab_3.models.Database;
import lab_3.models.TokenManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@RestController
public class ProjectController extends GeneralController {

    @RequestMapping(method = RequestMethod.POST, value = "/createNewProject")
    public void createProject(HttpSession httpSession, @RequestParam String projectName, HttpServletResponse response) throws IOException {
        try {
            Database.database().createNewProject(projectName);
            response.sendRedirect("/homePage");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/project/{id}")
    public String get(Model model, HttpSession httpSession, @PathVariable String id, HttpServletResponse response) throws IOException, SQLException {
        if(httpSession.getAttribute("auth-token") != null) {
            model.addAttribute("userName",
                    TokenManager.decodeJWT(httpSession.getAttribute("auth-token").toString())
                            .getIssuer());
            model.addAttribute("ticket",Database.database().getCurrentProject(id));
            return "project";
        }
        response.sendRedirect("/");
        return "auth";
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/deleteProject/{id}")
    public void delete(HttpSession httpSession, @PathVariable String id, HttpServletResponse response) throws SQLException, IOException {
        if(httpSession.getAttribute("auth-token") != null) {
            Database.database().deleteProject(id);
            response.sendRedirect("/homePage");
            response.getWriter().println("<script>alert('Project was removed');</script>");
        }else{
            response.sendRedirect("/");
        }

    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/searchProject")
    public void search(@RequestParam String value) throws SQLException, IOException {
        Database.database().searchProject(value);
    }
}
