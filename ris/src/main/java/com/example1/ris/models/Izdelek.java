package com.example1.ris.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Collection;

@Entity
public class Izdelek {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String naziv;
	private String opis;
	private double cena;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avtosola_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Avtosola avtosola;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Avtosola getAvtosola() {
		return avtosola;
	}

	public void setAvtosola(Avtosola avtosola) {
		this.avtosola = avtosola;
	}
}