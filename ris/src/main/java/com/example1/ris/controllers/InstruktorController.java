package com.example1.ris.controllers;

import com.example1.ris.dao.AvtosolaRepository;
import com.example1.ris.dao.InstruktorRepository;
import com.example1.ris.dao.TerminRepository;
import com.example1.ris.exceprion.ResourceNotFoundException;
import com.example1.ris.models.Avtosola;
import com.example1.ris.models.Instruktor;
import com.example1.ris.models.Termin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/instruktorji")
public class InstruktorController {

    @Autowired
    private InstruktorRepository instruktorDao;

    @Autowired
    private AvtosolaRepository avtosolaDao;

    @Autowired
    private TerminRepository terminDao;

    @GetMapping
    public Iterable<Instruktor> vrniInstruktorje(){return instruktorDao.findAll();}

    @GetMapping("/{instruktor_id}")
    public Optional<Instruktor> vrniInstruktorjaPoId(@PathVariable(name = "instruktor_id")Long instruktor_id){
        return instruktorDao.findById(instruktor_id);
    }

    @PostMapping
    public Instruktor dodajInstruktorja(@RequestBody Instruktor instruktor){
        return instruktorDao.save(instruktor);
    }

    // Dodaj Avtošolo Instrukturju
    @PostMapping("/dodajAvtosolo/{instruktor_id}")
    public Instruktor dodajAvtosoloInstrukturju(@PathVariable(name = "instruktor_id")Long instruktor_id, @RequestBody Avtosola avtosola){
        Instruktor iskanInstruktor = instruktorDao.findById(instruktor_id).orElseThrow(() -> new ResourceNotFoundException("Instruktor ne obstaja z id: " + instruktor_id));
        iskanInstruktor.setAvtosola(avtosola);

        Avtosola dodanaAvtosola = avtosolaDao.findById(avtosola.getId()).orElseThrow(() -> new ResourceNotFoundException("Avtosola ne obstaja z id: " + avtosola.getId()));
        dodanaAvtosola.getInstruktorji().add(iskanInstruktor);
        avtosolaDao.save(dodanaAvtosola);

        return instruktorDao.save(iskanInstruktor);
    }

    // Dodaj Termin Instrukturju
    @PostMapping("/dodajTermin/{instruktor_id}")
    public Instruktor dodajTerminInstrukturju(@PathVariable(name = "instruktor_id")Long instruktor_id, @RequestBody Termin termin){
        Instruktor iskanInstruktor = instruktorDao.findById(instruktor_id).orElseThrow(()  -> new ResourceNotFoundException("Instruktor ne obstaja z id: " + instruktor_id));
        iskanInstruktor.getTermini().add(termin);

        Termin dodanTermin = terminDao.findById(termin.getId()).orElseThrow(()  -> new ResourceNotFoundException("Termin ne obstaja z id: " + termin.getId()));
        dodanTermin.setInstruktor(iskanInstruktor);
        terminDao.save(dodanTermin);

        return instruktorDao.save(iskanInstruktor);
    }

    // Spreminjanje Instruktorja
    @PutMapping("/spremeniInstruktorja/{instruktor_id}")
    public Instruktor spremeniInstruktorja(@PathVariable(name = "instruktor_id")Long instruktor_id, @RequestBody Instruktor instruktor){
        Instruktor posodobljenInstruktor = instruktorDao.findById(instruktor_id).orElseThrow(() -> new ResourceNotFoundException("Instruktor ne obstja z id: " + instruktor_id));

        posodobljenInstruktor.setIme(instruktor.getIme());
        posodobljenInstruktor.setPriimek(instruktor.getPriimek());
        posodobljenInstruktor.setTelefonska_st(instruktor.getTelefonska_st());
        posodobljenInstruktor.setE_naslov(instruktor.getE_naslov());
        posodobljenInstruktor.setCena_voznje(instruktor.getCena_voznje());

        return instruktorDao.save(posodobljenInstruktor);
    }

    // Izbrisi instruktorja
    @DeleteMapping("/izbrisiInstruktorja/{instruktor_id}")
    public void izbrisiInstruktorja(@PathVariable(name = "instruktor_id")Long instruktor_id){
        instruktorDao.deleteById(instruktor_id);
    }
}
