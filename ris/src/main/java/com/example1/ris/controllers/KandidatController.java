package com.example1.ris.controllers;

import com.example1.ris.EmailSenderService;
import com.example1.ris.dao.AvtosolaRepository;
import com.example1.ris.dao.KandidatRepository;
import com.example1.ris.dao.TerminRepository;
import com.example1.ris.exceprion.ResourceNotFoundException;
import com.example1.ris.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/kandidati")
public class KandidatController {

    @Autowired
    private KandidatRepository kandidatDao;

    @Autowired
    private TerminRepository terminDao;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private AvtosolaRepository avtosolaDao;

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
    public Long dodajKandidata(@RequestBody Kandidat kandidat){
        String welcome = "Welcome, " + kandidat.getIme();
        String body = "Thank you for registering on MojInstruktor app!";
        String userEmail = kandidat.getE_naslov();
        String attachment = "/Users/mihaprah/Desktop/RIS_repo/driveSafePhoto.jpg";
        //emailSenderService.sendEmail(userEmail, welcome, body, attachment);
        kandidatDao.save(kandidat);
        return kandidat.getId();
    }

    // Dodaj Kraj Kandidatu
    @PostMapping("/dodajKraj/{kandidat_id}")
    public Kandidat dodajKraj(@PathVariable(name = "kandidat_id") Long kandidat_id, @RequestBody Kraj kraj){
        Kandidat posodobljenKandidat = kandidatDao.findById(kandidat_id).orElseThrow(() -> new ResourceNotFoundException("Kandidat ne obstaja z id: " + kandidat_id));

        posodobljenKandidat.setKraj(null);
        posodobljenKandidat.setKraj(kraj);

        return kandidatDao.save(posodobljenKandidat);
    }

    // Dodaj Termin Kandidatu - Prijava na termin
    // Kandidata samo poiscec
    @PostMapping("/dodajTermin/{kandidat_id}")
    public Kandidat dodajTerminInstrukturju(@PathVariable(name = "kandidat_id")Long kandidat_id, @RequestBody Termin termin){
        Kandidat iskanKandidat = kandidatDao.findById(kandidat_id).orElseThrow(()  -> new ResourceNotFoundException("Kandidat ne obstaja z id: " + kandidat_id));

        Termin dodanTermin = terminDao.findById(termin.getId()).orElseThrow(()  -> new ResourceNotFoundException("Termin ne obstaja z id: " + termin.getId()));
        dodanTermin.setKandidat(iskanKandidat);
        terminDao.save(dodanTermin);

        return kandidatDao.save(iskanKandidat);
    }

    // Odstrani Termin Kandidatu - Odjava z termina
    @PostMapping("/odstraniTermin/{kandidat_id}")
    public Kandidat odstraniTermin(@PathVariable(name = "kandidat_id")Long kandidat_id, @RequestBody Termin termin){
        Kandidat iskanKandidat = kandidatDao.findById(kandidat_id).orElseThrow(()  -> new ResourceNotFoundException("Kandidat ne obstaja z id: " + kandidat_id));

        Termin obstojecTermin = terminDao.findById(termin.getId()).orElseThrow(() -> new ResourceNotFoundException("Termin ne obstaja z id: " + termin.getId()));

        Collection<Termin> obstojeciTermini = iskanKandidat.getTermini();
        if (obstojeciTermini.contains(obstojecTermin)){
            obstojecTermin.setKandidat(null);
            obstojeciTermini.remove(obstojecTermin);
            iskanKandidat.setTermini(obstojeciTermini);
            terminDao.save(obstojecTermin);
        }

        return kandidatDao.save(iskanKandidat);
    }

    // Dodaj Avtosolo Kandidatu
    @PostMapping("/dodajAvtosolo/{kandidat_id}")
    public Kandidat dodajAvtosolo(@PathVariable(name = "kandidat_id")Long kandidat_id, @RequestBody Avtosola avtosola){
        Kandidat iskanKandidat = kandidatDao.findById(kandidat_id).orElseThrow(() -> new ResourceNotFoundException("Kandidat ne obstaja z id: " + kandidat_id));
        iskanKandidat.setAvtosola(avtosola);

        Avtosola dodanaAvtosola = avtosolaDao.findById(avtosola.getId()).orElseThrow(() -> new ResourceNotFoundException("Avtosola ne obstaja z id: " + avtosola.getId()));
        dodanaAvtosola.getKandidati().add(iskanKandidat);
        avtosolaDao.save(dodanaAvtosola);

        return kandidatDao.save(iskanKandidat);
    }

    // Spremeni Kandidat lastnosti - Upravljanje profila
    @PutMapping("/{kandidat_id}")
    public Kandidat spremeniKandidata(@PathVariable(name = "kandidat_id")Long kandidat_id, @RequestBody Kandidat kandidat){
        Kandidat posodobljenKandidat = kandidatDao.findById(kandidat_id).orElseThrow(() -> new ResourceNotFoundException("Kandidat ne obstaja z id: " + kandidat_id));

        posodobljenKandidat.setIme(kandidat.getIme());
        posodobljenKandidat.setPriimek(kandidat.getPriimek());
        posodobljenKandidat.setNaslov(kandidat.getNaslov());
        posodobljenKandidat.setE_naslov(kandidat.getE_naslov());
        posodobljenKandidat.setTelefonska_st(kandidat.getTelefonska_st());

        return kandidatDao.save(posodobljenKandidat);
    }

    // Izbrisi Kandidata po Id
    @DeleteMapping("/izbrisiKandidata/{kandidat_id}")
    public String izbrisiKandidata(@PathVariable(name = "kandidat_id")Long kandidat_id){
        kandidatDao.deleteById(kandidat_id);
        return "Succesfully deleted user with id: " + kandidat_id;
    }
}
