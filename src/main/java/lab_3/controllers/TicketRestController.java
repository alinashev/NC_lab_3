package lab_3.controllers;

import lab_3.models.Database;
import lab_3.models.pojo.Ticket;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class TicketRestController {

    @RequestMapping(method = RequestMethod.GET, value = "/searchTicketByName")
    public Ticket searchTicket(@RequestParam String value) throws SQLException, IOException {
        return Database.database().searchTicket(value);
    }
}
