package com.example1.ris.controllers;

import com.example1.ris.dao.KrajRepository;
import com.example1.ris.exceprion.ResourceNotFoundException;
import com.example1.ris.models.Kraj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/kraji")
public class KrajController {

    @Autowired
    private KrajRepository krajDao;

    @GetMapping
    public Iterable<Kraj> vrniKraje(){
        return krajDao.findAll();
    }

    @GetMapping("/{kraj_id}")
    public Optional <Kraj> vrniKrajPoId(@PathVariable(name = "kraj_id") Long kraj_id){
        return krajDao.findById(kraj_id);
    }

    @GetMapping("/imeDrzave/{imeDrzave}/{postnaSt}/{imeKraja}")
    public Iterable<Kraj> vrniKrajePoDrzaviInVelikostiPostneSt(@PathVariable(name = "imeDrzave") String imeDrzave, @PathVariable(name = "postnaSt") int postnaSt, @PathVariable(name = "imeKraja") String imeKraja){
        return krajDao.vrniKrajePoDrzaviInPostniStevilki(imeDrzave, postnaSt, imeKraja);
    }

    @PostMapping
    public Kraj dodajKraj(@RequestBody Kraj kraj){
        return krajDao.save(kraj);
    }

    @PutMapping("/{kraj_id}")
    public Kraj spremeniKraj(@PathVariable(name = "kraj_id") Long kraj_id, @RequestBody Kraj kraj){
        Kraj posodobljenKraj = krajDao.findById(kraj_id).orElseThrow(() -> new ResourceNotFoundException("Kraj ne obstaja z id: " + kraj_id));

        posodobljenKraj.setIme(kraj.getIme());
        posodobljenKraj.setPostna_st(kraj.getPostna_st());
        posodobljenKraj.setDrzava(kraj.getDrzava());

        return  krajDao.save(posodobljenKraj);
    }

    @DeleteMapping("/{kraj_id}")
    public void izbrisiKraj(@PathVariable(name = "kraj_id") Long kraj_id){
        krajDao.deleteById(kraj_id);
    }
}
