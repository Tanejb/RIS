package com.example1.ris.controllers;

import com.example1.ris.dao.KandidatRepository;
import com.example1.ris.exceprion.ResourceNotFoundException;
import com.example1.ris.models.Kandidat;
import com.example1.ris.models.Kraj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/kandidati")
public class KandidatController {

    @Autowired
    private KandidatRepository kandidatDao;

    @GetMapping
    public Iterable<Kandidat> vrniKandidate(){
        return kandidatDao.findAll();
    }

    @GetMapping("/{kandidat_id}")
    public Optional <Kandidat> najdiKandidataPoId(@PathVariable(name = "kandidat_id") Long kandidat_id){
        return kandidatDao.findById(kandidat_id);
    }

    @GetMapping("/imeKandidata/{imeKandidata}/{priimekKandidata}/{imeKraja}")
    public Iterable<Kandidat> vrniKandiataPoImenuInPrimkuInKraju(@PathVariable(name = "imeKandidata") String imeKandidata, @PathVariable(name = "priimekKandidata") String priimekKandidata, @PathVariable(name = "imeKraja") String imeKraja){
        return kandidatDao.vrniKandidataPoImenuInPriimkuInKraju(imeKandidata, priimekKandidata, imeKraja);
    }

    @PostMapping
    public Kandidat dodajKandidata(@RequestBody Kandidat kandidat){
        return kandidatDao.save(kandidat);
    }

    @PutMapping("/dodajKraj/{kandidat_id}")
    public Kandidat dodajKraj(@PathVariable(name = "kandidat_id") Long kandidat_id, @RequestBody Kraj kraj){
        Kandidat posodobljenKandidat = kandidatDao.findById(kandidat_id).orElseThrow(() -> new ResourceNotFoundException("Kandidat ne obstaja z id: " + kandidat_id));

        posodobljenKandidat.setKraj(kraj);

        return kandidatDao.save(posodobljenKandidat);
    }
}
