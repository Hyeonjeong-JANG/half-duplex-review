package com.example.yourapp.controller;

import com.example.yourapp.RequestMapping;

public class BoardController {
    @RequestMapping("/save")
    public String save() {
        return "board write";
    }

    @RequestMapping("/findAll")
    public String findAll() {
        return "board list";
    }

    @RequestMapping("/findById")
    public String findById() {
        return "board one";
    }
}
