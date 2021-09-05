package lab_3.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public abstract class GeneralController {

    public abstract String get(Model model, HttpSession httpSession, @PathVariable String id, HttpServletResponse response) throws IOException, SQLException;

    public abstract void delete(HttpSession httpSession, @PathVariable String id, HttpServletResponse response) throws SQLException, IOException ;

    public abstract void search(@RequestParam String value)throws SQLException, IOException;




}
