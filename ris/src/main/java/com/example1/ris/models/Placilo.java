package com.example1.ris.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Placilo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private double znesek;
	private String datum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nakup_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Nakup nakup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kandidat_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Kandidat kandidat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getZnesek() {
		return znesek;
	}

	public void setZnesek(double znesek) {
		this.znesek = znesek;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public Nakup getNakup() {
		return nakup;
	}

	public void setNakup(Nakup nakup) {
		this.nakup = nakup;
	}

	public Kandidat getKandidat() {
		return kandidat;
	}

	public void setKandidat(Kandidat kandidat) {
		this.kandidat = kandidat;
	}
}