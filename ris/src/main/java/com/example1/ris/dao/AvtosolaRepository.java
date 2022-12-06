package com.example1.ris.dao;

import com.example1.ris.models.Avtosola;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AvtosolaRepository extends CrudRepository<Avtosola, Long> {
    // Pridobi Avtošole kjer je ime podobno kot (imeAvtosole) in ime kraja avtošole je (imeKraj)
    @Query("select a from Avtosola a where a.ime like ?1% and a.kraj = (select k.id from Kraj k where k.ime = ?2)")
    Iterable<Avtosola> vrniAvtosolePoImenuInKraju(String ime, String imeKraja);

    // Pridobi Avtosole, ki imajo Instruktorje
    @Query("select a from Avtosola a, Instruktor i where i.avtosola = a")
    Iterable<Avtosola> vrniAvtosoleZVecInstruktorji();
}
