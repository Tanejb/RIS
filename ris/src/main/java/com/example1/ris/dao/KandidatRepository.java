package com.example1.ris.dao;

import com.example1.ris.models.Kandidat;
import com.example1.ris.models.Kraj;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface KandidatRepository extends CrudRepository<Kandidat, Long> {
    // Pridobi Kandidate katerih ime je podobno (imeKandidata), priimek kandidata je podoben (priimekKandidata) in ime kraja kandidata je enako (imeKraja)
    @Query("select k from Kandidat k where k.ime like ?1% and k.priimek like ?2% and k.kraj = (select k.id from Kraj k where k.ime = ?3)")
    Iterable<Kandidat> vrniKandidataPoImenuInPriimkuInKraju(String ime, String priimek, String kraj);

    // Pridobi Kandidate katerih Kraj je enak (imeKraja)
    @Query("select k from Kandidat k where k.kraj in (select k.id from Kraj k where k.ime like ?1%)")
    Iterable<Kandidat> vrniKandidatePoKraju(String imeKraja);

    //Pridobi kandidata, ki je v avtosoli(Avtosola) in kraju(Kraj)
    @Query("select k from Kandidat k where k.avtosola in (select a.id from Avtosola a where a.ime like ?1%) and k.kraj in (select kr.id from Kraj kr where kr.ime like ?2%)")
    Iterable<Kandidat> vrniKandidateIzAvtosoleIzKraja(String imeAvtosole, String imeKraja);
}
