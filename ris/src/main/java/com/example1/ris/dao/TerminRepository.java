package com.example1.ris.dao;

import com.example1.ris.models.Termin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TerminRepository extends CrudRepository<Termin, Long> {
    // Pridobi polne termine
    @Query("select t from Termin t, Instruktor i, Kandidat k where t.instruktor = i and t.kandidat = k")
    Iterable<Termin> vrniTermineKiSoPolni();

    // Pridobi termine brez kandidata
    @Query("select t from Termin t, Instruktor i, Kandidat k where t.instruktor = i and t.kandidat = null")
    Iterable<Termin> vrniPrazneTermine();

    // Pridobi termine po ID od Instruktorja in Kandidata
    @Query("select t from Termin t where t.instruktor = (select i from Instruktor i where i.id = ?1) and t.kandidat = (select k from Kandidat k where k.id = ?2)")
    Iterable<Termin> vrniTerminePoIdINstruktorInKandidat(Long id_instruktor, Long id_kandidat);
}
