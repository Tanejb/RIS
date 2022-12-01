package com.example1.ris.controllers;

import com.example1.ris.dao.InstruktorRepository;
import com.example1.ris.models.Instruktor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instruktorji")
public class InstruktorController {

    @Autowired
    private InstruktorRepository instruktorDao;
    
}
