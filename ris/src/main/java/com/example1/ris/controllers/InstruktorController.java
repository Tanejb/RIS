package com.example1.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instruktorji")
public class InstruktorController {

    @Autowired
    private InstruktorController instruktorDao;
}
