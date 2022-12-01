package com.example1.ris.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Kraj {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String ime;
	private int postna_st;
	private String drzava;

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

	public int getPostna_st() {
		return postna_st;
	}

	public void setPostna_st(int postna_st) {
		this.postna_st = postna_st;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
}