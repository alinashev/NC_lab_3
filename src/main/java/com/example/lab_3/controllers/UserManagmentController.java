package com.example.lab_3.controllers;

import com.example.lab_3.models.Database;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class UserManagmentController {

    @GetMapping("/auth")
    private void auth(@RequestParam String username,@RequestParam String password){
        System.out.println(username + " " + password);
        try {
            if(Database.database().selectAllUsers(username,password)){
                System.out.println("Success");
            }else{
                System.out.println("FAIL");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ;
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
