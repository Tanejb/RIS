package com.example1.ris.dao;

import com.example1.ris.models.Instruktor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface InstruktorRepository extends CrudRepository<Instruktor, Long> {
    //Pridobima instruktorja iz avtosole X, ki ima ocene vecjo od Y
    @Query("select i from Instruktor i where i.avtosola in(select a.id from Avtosola a where a.ime like ?1%)and i.ocena in(select o.id from Ocena_instruktorja o where o.ocena >= ?2)")
    Iterable<Instruktor> instruktorjiIzAvtosoleZOceno(String imeAvtosole, int ocena);
}
