package com.example1.ris.dao;

import com.example1.ris.models.Kraj;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KrajRepository extends CrudRepository<Kraj, Long> {
    @Query("select k from Kraj k where k.drzava = ?1 and k.postna_st >= ?2 and k.ime like ?3%")
    List<Kraj> vrniKrajePoDrzaviInPostniStevilki(String drzava, int postna_st, String imeKraja);
}
