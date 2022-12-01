package com.example1.ris.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Nakup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String datum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "izdelek_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Izdelek izdelek;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kandidat_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Kandidat kandidat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placilo_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Placilo placilo;

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

	public Izdelek getIzdelek() {
		return izdelek;
	}

	public void setIzdelek(Izdelek izdelek) {
		this.izdelek = izdelek;
	}

	public Kandidat getKandidat() {
		return kandidat;
	}

	public void setKandidat(Kandidat kandidat) {
		this.kandidat = kandidat;
	}

	public Placilo getPlacilo() {
		return placilo;
	}

	public void setPlacilo(Placilo placilo) {
		this.placilo = placilo;
	}
}