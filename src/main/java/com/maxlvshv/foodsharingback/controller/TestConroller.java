package com.maxlvshv.foodsharingback.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestConroller {
    @GetMapping
    public String sayHi(){
        return "Hey";
    }

    @GetMapping("/sec")
    @PreAuthorize("hasRole('ADMIN')")
    public String sayHiSecr(){
        return "it's secret's Hey for admin";
    }
}
