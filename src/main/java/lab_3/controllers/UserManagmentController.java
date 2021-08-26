package lab_3.controllers;

import lab_3.models.Database;
import lab_3.models.TokenManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@RestController
public class UserManagmentController {

    @GetMapping("/auth")
    private void auth(@RequestParam String username, @RequestParam String password, HttpServletResponse response, HttpSession httpSession){
        System.out.println(username + " " + password);
        try {
            if(Database.database().selectAllUsers(username,password)){
                System.out.println("Success");
                httpSession.setAttribute("auth-token", TokenManager.createJWT(username));
                response.sendRedirect("/homePage");
            }else{
                System.out.println("FAIL");
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register_user")
    private void register(@RequestParam String username, @RequestParam String email, @RequestParam String password){
        try {
            Database.database().registration(username, email,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
