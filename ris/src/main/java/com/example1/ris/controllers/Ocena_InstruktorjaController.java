package com.example1.ris.controllers;

import com.example1.ris.dao.Ocena_InstruktorjaRepository;
import com.example1.ris.models.Ocena_instruktorja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/oceneInstruktorjev")
public class Ocena_InstruktorjaController {
    @Autowired
    private Ocena_InstruktorjaRepository ocenaInstruktorjaDao;

    @GetMapping
    private Iterable<Ocena_instruktorja> pridobiOcene(){
        return ocenaInstruktorjaDao.findAll();
    }
    @PostMapping
    private Ocena_instruktorja dodajOceno(@RequestBody Ocena_instruktorja ocenaInstruktorja){
        return ocenaInstruktorjaDao.save(ocenaInstruktorja);
    }
}
