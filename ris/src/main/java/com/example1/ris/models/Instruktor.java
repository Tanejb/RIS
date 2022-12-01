package com.example1.ris.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Instruktor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String ime;
	private String priimek;
	private String telefonska_st;
	private String e_naslov;
	private double cena_voznje;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avtosola_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Avtosola avtosola;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ocena_instruktorja_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Ocena_instruktorja ocena;

	@OneToMany(mappedBy = "instruktor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Collection<Termin> termini;

	public double spremeniCeno(double cena) {
		throw new UnsupportedOperationException();
	}

	public void dodajTermin(Termin termin) {
		throw new UnsupportedOperationException();
	}

	public void zbrisiTermin(Termin termin) {
		throw new UnsupportedOperationException();
	}

	public void upravljanjeProfila(String ime, String priimek, String telefonska_st, String e_naslov) {
		throw new UnsupportedOperationException();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPriimek() {
		return priimek;
	}

	public void setPriimek(String priimek) {
		this.priimek = priimek;
	}

	public String getTelefonska_st() {
		return telefonska_st;
	}

	public void setTelefonska_st(String telefonska_st) {
		this.telefonska_st = telefonska_st;
	}

	public String getE_naslov() {
		return e_naslov;
	}

	public void setE_naslov(String e_naslov) {
		this.e_naslov = e_naslov;
	}

	public double getCena_voznje() {
		return cena_voznje;
	}

	public void setCena_voznje(double cena_voznje) {
		this.cena_voznje = cena_voznje;
	}

	public Avtosola getAvtosola() {
		return avtosola;
	}

	public void setAvtosola(Avtosola avtosola) {
		this.avtosola = avtosola;
	}

	public Ocena_instruktorja getOcena() {
		return ocena;
	}

	public void setOcena(Ocena_instruktorja ocena) {
		this.ocena = ocena;
	}

	public Collection<Termin> getTermini() {
		return termini;
	}

	public void setTermini(Collection<Termin> termini) {
		this.termini = termini;
	}
}