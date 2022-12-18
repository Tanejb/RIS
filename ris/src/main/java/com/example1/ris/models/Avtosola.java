package com.example1.ris.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Avtosola {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String ime;
	private String naslov;
	private String telefonska_st;
	private String e_naslov;
	private String geslo;

	@OneToMany(mappedBy = "avtosola", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Collection<Izdelek> izdelki;

	@OneToMany(mappedBy = "avtosola", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Collection<Instruktor> instruktorji;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kraj_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Kraj kraj;

	@OneToMany(mappedBy = "avtosola", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Collection<Kandidat> kandidati;

	public void dodajIzdelek(Izdelek izdelek) {
		throw new UnsupportedOperationException();
	}

	public void izbrisiIzdelek(Izdelek izdelek) {
		throw new UnsupportedOperationException();
	}

	public void dodajInstruktorja(Instruktor instruktor) {
		throw new UnsupportedOperationException();
	}

	public void izbrisiInstruktorja(Instruktor instruktor) {
		throw new UnsupportedOperationException();
	}

	public void izdajRacun(Kandidat[] kandidati) {
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

	public Collection<Izdelek> getIzdelki() {
		return izdelki;
	}

	public void setIzdelki(Collection<Izdelek> izdelki) {
		this.izdelki = izdelki;
	}

	public Collection<Instruktor> getInstruktorji() {
		return instruktorji;
	}

	public void setInstruktorji(Collection<Instruktor> instruktorji) {
		this.instruktorji = instruktorji;
	}

	public Kraj getKraj() {
		return kraj;
	}

	public void setKraj(Kraj kraj) {
		this.kraj = kraj;
	}

	public Collection<Kandidat> getKandidati() {
		return kandidati;
	}

	public void setKandidati(Collection<Kandidat> kandidati) {
		this.kandidati = kandidati;
	}

	public String getGeslo() {
		return geslo;
	}

	public void setGeslo(String geslo) {
		this.geslo = geslo;
	}

	public String getE_naslov() {
		return e_naslov;
	}

	public void setE_naslov(String e_naslov) {
		this.e_naslov = e_naslov;
	}
}