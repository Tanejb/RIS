package com.example1.ris.dao;

import com.example1.ris.models.Kandidat;
import com.example1.ris.models.Kraj;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface KandidatRepository extends CrudRepository<Kandidat, Long> {
    @Query("select k from Kandidat k where k.ime like ?1% and k.priimek like ?2% and k.kraj = (select k.id from Kraj k where k.ime = ?3)")
    Iterable<Kandidat> vrniKandidataPoImenuInPriimkuInKraju(String ime, String priimek, String kraj);
}
