package lab_3.controllers;

import lab_3.models.Database;
import lab_3.models.TokenManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TicketController extends GeneralController {

    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @RequestMapping(method = RequestMethod.POST, value = "/createNewTicket")
    public void createTicket(HttpSession httpSession, @RequestParam String desc, @RequestParam String bugStatus,
                             @RequestParam String severity, @RequestParam String priority,
                             @RequestParam String name, @RequestParam String expected, @RequestParam String actual,
                             HttpServletResponse response) throws IOException {
        try {
            //TokenManager.decodeJWT(httpSession.getAttribute("auth-token").toString())
            //                    .getIssuer()
            Database.database().createNewTicket(bugStatus, severity, priority,
                    name, desc, formatter.format(new Date()), expected, actual);
            response.sendRedirect("/homePage");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/ticket/{id}")
    public String get(Model model, HttpSession httpSession, @PathVariable String id, HttpServletResponse response) throws IOException, SQLException {
        if(httpSession.getAttribute("auth-token") != null) {
            model.addAttribute("userName",
                    TokenManager.decodeJWT(httpSession.getAttribute("auth-token").toString())
                            .getIssuer());
                 model.addAttribute("ticket",Database.database().getCurrentTicket(id));
                 return "ticket";
        }
        response.sendRedirect("/");
        return "auth";
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/deleteTicket/{id}")
    public void delete(HttpSession httpSession, @PathVariable String id, HttpServletResponse response) throws SQLException, IOException {
        if(httpSession.getAttribute("auth-token") != null) {
            Database.database().deleteTicket(id);
            response.sendRedirect("/homePage");
            response.getWriter().println("<script>alert('Ticket was removed');</script>");
        }else{
            response.sendRedirect("/");
        }

    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/searchTicket")
    public void search(@RequestParam String value) throws SQLException, IOException {
        Database.database().searchTickets(value);
    }
}
