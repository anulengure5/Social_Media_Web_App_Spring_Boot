package com.anulengure5.websocialapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/Home")
    public String homeControllerHandler()
    {
        return "this is home controller";
    }

}