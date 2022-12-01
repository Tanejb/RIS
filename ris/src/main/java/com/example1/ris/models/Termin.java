package com.example1.ris.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Termin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String datum;
	private String ura_pricetka;
	private double trajanje;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instruktor_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Instruktor instruktor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kandidat_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Kandidat kandidat;

	public void prijavaNaTermin(Kandidat kandidat, Instruktor instruktor) {
		throw new UnsupportedOperationException();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getUra_pricetka() {
		return ura_pricetka;
	}

	public void setUra_pricetka(String ura_pricetka) {
		this.ura_pricetka = ura_pricetka;
	}

	public double getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}

	public Instruktor getInstruktor() {
		return instruktor;
	}

	public void setInstruktor(Instruktor instruktor) {
		this.instruktor = instruktor;
	}

	public Kandidat getKandidat() {
		return kandidat;
	}

	public void setKandidat(Kandidat kandidat) {
		this.kandidat = kandidat;
	}
}