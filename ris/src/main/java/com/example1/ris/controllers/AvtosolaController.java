package com.example1.ris.controllers;

import com.example1.ris.dao.AvtosolaRepository;
import com.example1.ris.exceprion.ResourceNotFoundException;
import com.example1.ris.models.Avtosola;
import com.example1.ris.models.Kandidat;
import com.example1.ris.models.Kraj;
import com.example1.ris.models.Nakup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/avtosole")
public class AvtosolaController {

    @Autowired
    private AvtosolaRepository avtosolaDao;

    @GetMapping
    public Iterable<Avtosola> vrniAvtosole(){
        return avtosolaDao.findAll();
    }

    @GetMapping("/{avtosola_id}")
    public Optional<Avtosola> vrniAvtosoloPoId(@PathVariable(name = "avtosola_id") Long avtosola_id){
        return avtosolaDao.findById(avtosola_id);
    }

    // Pridobi Avtošole kjer je ime podobno kot (imeAvtosole) in ime kraja avtošole je (imeKraj)
    @GetMapping("/imeAvtosole/{imeAvtosole}/{imeKraja}")
    public Iterable<Avtosola> vrniAvtosoloPoImenuInKraju(@PathVariable(name = "imeAvtosole") String imeAvtosole, @PathVariable(name = "imeKraja") String imeKraja){
        return avtosolaDao.vrniAvtosolePoImenuInKraju(imeAvtosole, imeKraja);
    }

    @PostMapping
    public Avtosola dodajAvtosolo(@RequestBody Avtosola avtosola){
        return avtosolaDao.save(avtosola);
    }

    // Dodaj Kraj Avtosoli
    @PostMapping("/dodajKraj/{avtosola_id}")
    public Avtosola dodajKraj(@PathVariable(name = "avtosola_id") Long avtosola_id, @RequestBody Kraj kraj){
        Avtosola posodobljenaAvtosola = avtosolaDao.findById(avtosola_id).orElseThrow(() -> new ResourceNotFoundException("Avtosola ne obstaja z id: " + avtosola_id));

        posodobljenaAvtosola.setKraj(kraj);

        return avtosolaDao.save(posodobljenaAvtosola);
    }

    // Spremeni Avtosola lastnosti
    @PutMapping("/{avtosola_id}")
    public Avtosola spremeniAvtosolo(@PathVariable(name = "avtosola_id") Long avtosola_id, @RequestBody Avtosola avtosola){
        Avtosola posodobljenaAvtosola = avtosolaDao.findById(avtosola_id).orElseThrow(() -> new ResourceNotFoundException("Avtosola ne obstaja z id: " + avtosola_id));

        posodobljenaAvtosola.setIme(avtosola.getIme());
        posodobljenaAvtosola.setInstruktorji(avtosola.getInstruktorji());
        posodobljenaAvtosola.setIzdelki(avtosola.getIzdelki());
        posodobljenaAvtosola.setKraj(avtosola.getKraj());
        posodobljenaAvtosola.setNaslov(avtosola.getNaslov());
        posodobljenaAvtosola.setTelefonska_st(avtosola.getTelefonska_st());
        posodobljenaAvtosola.setKandidati(avtosola.getKandidati());

        return avtosolaDao.save(posodobljenaAvtosola);
    }
}
