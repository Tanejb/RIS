package com.example1.ris.controllers;

import com.example1.ris.dao.InstruktorRepository;
import com.example1.ris.dao.TerminRepository;
import com.example1.ris.exceprion.ResourceNotFoundException;
import com.example1.ris.models.Instruktor;
import com.example1.ris.models.Termin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/termini")
public class TerminController {

    @Autowired
    private TerminRepository terminDao;

    @GetMapping
    public Iterable<Termin> vrniTermine(){
        return terminDao.findAll();
    }

    @GetMapping("/{termin_id}")
    public Optional<Termin> vrniTerminPoId(@PathVariable(name = "termin_id")Long termin_id){
        return terminDao.findById(termin_id);
    }

    // Pridobe polne termine
    @GetMapping("/polni")
    public Iterable<Termin> vrniPolneTermine(){
        return terminDao.vrniTermineKiSoPolni();
    }

    // Pridobi termine brez kandidta
    @GetMapping("/brezKandidata")
    public Iterable<Termin> vrniTermineBrezKandidata(){
        return terminDao.vrniPrazneTermine();
    }

    @GetMapping("/poId/{id_instruktor}/{id_kandidat}")
    public Iterable<Termin> vrniTerminePoIdInstruktorjaInKandidata(@PathVariable(name = "id_instruktor")Long id_instruktor, @PathVariable(name = "id_kandidat")Long id_kandidat){
        return terminDao.vrniTerminePoIdINstruktorInKandidat(id_instruktor, id_kandidat);
    }

    @PostMapping
    public Termin dodajTermin(@RequestBody Termin termin){
        return terminDao.save(termin);
    }

    @PutMapping("/{termin_id}")
    public Termin posodobiTermin(@PathVariable(name = "termin_id")Long termin_id, @RequestBody Termin termin){
        Termin posodobljenTermin = terminDao.findById(termin_id).orElseThrow(() -> new ResourceNotFoundException("Termin ne obstaja z id: " + termin_id));

        posodobljenTermin.setDatum(termin.getDatum());
        posodobljenTermin.setTrajanje(termin.getTrajanje());
        posodobljenTermin.setUra_pricetka(termin.getUra_pricetka());

        return terminDao.save(posodobljenTermin);
    }

    @DeleteMapping("/{termin_id}")
    public void izbrisiTermin(@PathVariable(name = "termin_id")Long termin_id){
        terminDao.deleteById(termin_id);
    }
}
