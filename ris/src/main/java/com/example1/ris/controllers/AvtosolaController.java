package com.example1.ris.controllers;

import com.example1.ris.PDFGeneratorService;
import com.example1.ris.dao.AvtosolaRepository;
import com.example1.ris.exceprion.ResourceNotFoundException;
import com.example1.ris.models.Avtosola;
import com.example1.ris.models.Kraj;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/avtosole")
public class AvtosolaController {
    private  final PDFGeneratorService pdfGeneratorService;

    @Autowired
    private AvtosolaRepository avtosolaDao;

    public AvtosolaController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping
    public Iterable<Avtosola> vrniAvtosole(){
        return avtosolaDao.findAll();
    }
    @GetMapping("/pdf/generiraj")
    public void generatePDF(HttpServletResponse response) throws IOException, URISyntaxException {
        response.setContentType("aplikacija/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + " .pdf";
        response.setHeader(headerKey, headerValue);
        this.pdfGeneratorService.export(response);
    }
    @GetMapping("/{avtosola_id}")
    public Avtosola vrniAvtosoloPoId(@PathVariable(name = "avtosola_id") Long avtosola_id){
        Avtosola iskanaAvtosola = avtosolaDao.findById(avtosola_id).orElseThrow(() -> new ResourceNotFoundException("Avtosola ne obstaja z id: " + avtosola_id));

        return iskanaAvtosola;
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
