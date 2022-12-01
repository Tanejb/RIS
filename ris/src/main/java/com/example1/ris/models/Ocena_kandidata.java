package com.example1.ris.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;

@Entity
public class Ocena_kandidata {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String komentar;
	private int ocena;
	private String datum;

	@ManyToOne
	@JoinColumn(name = "kandidat_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Kandidat kandidati;

	public void oceniKandidata(Kandidat kandidat) {
		throw new UnsupportedOperationException();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public Kandidat getKandidati() {
		return kandidati;
	}

	public void setKandidati(Kandidat kandidati) {
		this.kandidati = kandidati;
	}
}