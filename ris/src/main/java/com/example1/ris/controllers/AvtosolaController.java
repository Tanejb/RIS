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

@CrossOrigin
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

    // Pridobi Avtosole, ki imajo Instruktorje
    @GetMapping("/imaInstroktorja")
    public Iterable<Avtosola> vrniAvtosoleZVecInstruktorji(){
        return avtosolaDao.vrniAvtosoleZVecInstruktorji();
    }

    @PostMapping
    public Long dodajAvtosolo(@RequestBody Avtosola avtosola){
        avtosolaDao.save(avtosola);

        return avtosola.getId();
    }

    // Dodaj Kraj Avtosoli
    @PostMapping("/dodajKraj/{avtosola_id}")
    public Avtosola dodajKraj(@PathVariable(name = "avtosola_id") Long avtosola_id, @RequestBody Kraj kraj){
        Avtosola posodobljenaAvtosola = avtosolaDao.findById(avtosola_id).orElseThrow(() -> new ResourceNotFoundException("Avtosola ne obstaja z id: " + avtosola_id));

        posodobljenaAvtosola.setKraj(null);
        posodobljenaAvtosola.setKraj(kraj);

        return avtosolaDao.save(posodobljenaAvtosola);
    }

    // Spremeni Avtosola lastnosti - Upravljanje profila
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
        posodobljenaAvtosola.setE_naslov(avtosola.getE_naslov());
        posodobljenaAvtosola.setGeslo(avtosola.getGeslo());

        return avtosolaDao.save(posodobljenaAvtosola);
    }

    // Izbrisi instruktorja
    @DeleteMapping("/izbrisiAvtosolo/{avtosola_id}")
    public String izbrisiAvtosolo(@PathVariable(name = "avtosola_id")Long avtosola_id){
        avtosolaDao.deleteById(avtosola_id);
        return "Succesfully deleted user with id: " + avtosola_id;
    }
}
