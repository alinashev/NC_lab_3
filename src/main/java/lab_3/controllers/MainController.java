package lab_3.controllers;

import lab_3.models.TokenManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class MainController {

    @RequestMapping("/")
    public String authPage(HttpSession httpSession, HttpServletResponse response) throws IOException {
        if(httpSession.getAttribute("auth-token") != null){
            response.sendRedirect("/homePage");
        }
        return "auth";
    }
    @RequestMapping("/reg")
    public String regPage(HttpSession httpSession, HttpServletResponse response) throws IOException {
        if(httpSession.getAttribute("auth-token") != null){
            response.sendRedirect("/homePage");
        }
        return "reg";
    }


    @RequestMapping("/homePage")
    private String homePage(HttpSession httpSession, Model model, HttpServletResponse response) throws IOException {
        if(httpSession.getAttribute("auth-token") != null) {
            model.addAttribute("userName",
                    TokenManager.decodeJWT(httpSession.getAttribute("auth-token").toString())
                            .getIssuer());
            return "homePage";
        }
        response.sendRedirect("/");
        return "auth";
    }

    @RequestMapping("/logout")
    private void logout(HttpSession httpSession, HttpServletResponse response) throws IOException {
        httpSession.invalidate();
        response.sendRedirect("/");
    }
}