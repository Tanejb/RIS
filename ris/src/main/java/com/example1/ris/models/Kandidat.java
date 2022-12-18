package com.example1.ris.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Kandidat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String ime;
	private String priimek;
	private String naslov;
	private String telefonska_st;
	private String e_naslov;
	private String geslo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ocena_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Ocena_kandidata ocena;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avtosola_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Avtosola avtosola;

	@OneToMany(mappedBy = "kandidat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Collection<Termin> termini;

	@OneToMany(mappedBy = "kandidat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Collection<Nakup> nakupi;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kraj_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Kraj kraj;

	public void placaj(Placilo placilo) {
		throw new UnsupportedOperationException();
	}

	public String[] pregledTerminov(Termin[] termini) {
		throw new UnsupportedOperationException();
	}

	public void upravljanjeProfila(String ime, String priimek, String telefonska_st, Kraj kraj, String naslov, String e_naslov) {
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

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
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

	public Ocena_kandidata getOcena() {
		return ocena;
	}

	public void setOcena(Ocena_kandidata ocena) {
		this.ocena = ocena;
	}

	public Collection<Termin> getTermini() {
		return termini;
	}

	public void setTermini(Collection<Termin> termini) {
		this.termini = termini;
	}

	public Collection<Nakup> getNakupi() {
		return nakupi;
	}

	public void setNakupi(Collection<Nakup> nakupi) {
		this.nakupi = nakupi;
	}

	public Kraj getKraj() {
		return kraj;
	}

	public void setKraj(Kraj kraj) {
		this.kraj = kraj;
	}

	public Avtosola getAvtosola() {
		return avtosola;
	}

	public void setAvtosola(Avtosola avtosola) {
		this.avtosola = avtosola;
	}

	public String getGeslo() {
		return geslo;
	}

	public void setGeslo(String geslo) {
		this.geslo = geslo;
	}
}