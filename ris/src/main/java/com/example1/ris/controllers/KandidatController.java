package com.example1.ris.controllers;

import com.example1.ris.EmailSenderService;
import com.example1.ris.dao.KandidatRepository;
import com.example1.ris.dao.TerminRepository;
import com.example1.ris.exceprion.ResourceNotFoundException;
import com.example1.ris.models.Instruktor;
import com.example1.ris.models.Kandidat;
import com.example1.ris.models.Kraj;
import com.example1.ris.models.Termin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/kandidati")
public class KandidatController {

    @Autowired
    private KandidatRepository kandidatDao;

    @Autowired
    private TerminRepository terminDao;

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping
    public Iterable<Kandidat> vrniKandidate(){
        return kandidatDao.findAll();
    }

    @GetMapping("/{kandidat_id}")
    public Optional <Kandidat> najdiKandidataPoId(@PathVariable(name = "kandidat_id") Long kandidat_id){
        return kandidatDao.findById(kandidat_id);
    }

    // Pridobi Kandidate katerih ime je podobno (imeKandidata), priimek kandidata je podoben (priimekKandidata) in ime kraja kandidata je enako (imeKraja)
    @GetMapping("/imeKandidata/{imeKandidata}/{priimekKandidata}/{imeKraja}")
    public Iterable<Kandidat> vrniKandiataPoImenuInPrimkuInKraju(@PathVariable(name = "imeKandidata") String imeKandidata, @PathVariable(name = "priimekKandidata") String priimekKandidata, @PathVariable(name = "imeKraja") String imeKraja){
        return kandidatDao.vrniKandidataPoImenuInPriimkuInKraju(imeKandidata, priimekKandidata, imeKraja);
    }

    // Pridobi Kandidate katerih Kraj je enak (imeKraja)
    @GetMapping("/kandidatiIzKraja/{imeKraja}")
    public Iterable<Kandidat> vrniKandidataIzKraja(@PathVariable(name = "imeKraja")String imeKraja){
        return kandidatDao.vrniKandidatePoKraju(imeKraja);
    }

    @PostMapping
    public Kandidat dodajKandidata(@RequestBody Kandidat kandidat){
        String welcome = "Welcome, " + kandidat.getIme();
        String body = "Thank you for registering on MojInstruktor app!";
        String userEmail = kandidat.getE_naslov();
        String attachment = "/Users/mihaprah/Desktop/RIS_repo/driveSafePhoto.jpg";
        emailSenderService.sendEmail(userEmail, welcome, body, attachment);
        return kandidatDao.save(kandidat);
    }

    // Dodaj Kraj Kandidatu
    @PostMapping("/dodajKraj/{kandidat_id}")
    public Kandidat dodajKraj(@PathVariable(name = "kandidat_id") Long kandidat_id, @RequestBody Kraj kraj){
        Kandidat posodobljenKandidat = kandidatDao.findById(kandidat_id).orElseThrow(() -> new ResourceNotFoundException("Kandidat ne obstaja z id: " + kandidat_id));

        posodobljenKandidat.setKraj(null);
        posodobljenKandidat.setKraj(kraj);

        return kandidatDao.save(posodobljenKandidat);
    }

    // Dodaj Termin Kandidatu
    // Kandidata samo poiscec
    @PostMapping("/dodajTermin/{kandidat_id}")
    public Kandidat dodajTerminInstrukturju(@PathVariable(name = "kandidat_id")Long kandidat_id, @RequestBody Termin termin){
        Kandidat iskanKandidat = kandidatDao.findById(kandidat_id).orElseThrow(()  -> new ResourceNotFoundException("Kandidat ne obstaja z id: " + kandidat_id));

        Termin dodanTermin = terminDao.findById(termin.getId()).orElseThrow(()  -> new ResourceNotFoundException("Termin ne obstaja z id: " + termin.getId()));
        dodanTermin.setKandidat(iskanKandidat);
        terminDao.save(dodanTermin);

        return kandidatDao.save(iskanKandidat);
    }

    // Izbrisi Kandidata po Id
    @DeleteMapping("/izbrisiKandidata/{kandidat_id}")
    public void izbrisiKandidata(@PathVariable(name = "kandidat_id")Long kandidat_id){
        kandidatDao.deleteById(kandidat_id);
    }
}
