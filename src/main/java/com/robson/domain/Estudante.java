package com.robson.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Estudante {

	@Id
	@Column(name = "matricula")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long matricula;

	@Column(name = "nome")
	@NotBlank(message = "O nome é obrigatório!")
	private String nome;

	@Email
	@Column(name = "email", unique = true)
	@NotBlank(message = "O email é obrigatório!")
	private String email;

	public Estudante() {
		super();
	}

	public Estudante(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}