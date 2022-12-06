package com.example1.ris.controllers;

import com.example1.ris.dao.InstruktorRepository;
import com.example1.ris.models.Instruktor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/instruktorji")
public class InstruktorController {

    @Autowired
    private InstruktorRepository instruktorDao;

    @GetMapping
    public Iterable<Instruktor> vrniInstruktorje(){return instruktorDao.findAll();}

    @GetMapping("/{instruktor_id}")
    public Optional<Instruktor> vrniInstruktorjaPoId(@PathVariable(name = "instruktor_id")Long instruktor_id){
        return instruktorDao.findById(instruktor_id);
    }

}
