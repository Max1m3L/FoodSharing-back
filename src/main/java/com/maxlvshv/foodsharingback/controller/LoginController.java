package com.maxlvshv.foodsharingback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secret")
public class LoginController {
    @GetMapping
    public String sayHi(){
        return "Hey";
    }
}
