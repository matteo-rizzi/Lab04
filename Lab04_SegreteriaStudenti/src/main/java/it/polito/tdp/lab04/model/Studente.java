package it.polito.tdp.lab04.model;

public class Studente {

	private Integer matricola;
	private String cognome;
	private String nome;
	private String corsoDiStudi;

	/**
	 * Crea un nuovo studente secondo i parametri inseriti
	 * 
	 * @param matricola    matricola dello studente
	 * @param cognome      cognome dello studente
	 * @param nome         nome dello studente
	 * @param corsoDiStudi corso di studi a cui lo studente è iscritto
	 */
	public Studente(Integer matricola, String cognome, String nome, String corsoDiStudi) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.corsoDiStudi = corsoDiStudi;
	}

	/**
	 * Crea uno studente vuoto
	 */
	public Studente() {
	}

	public Studente(int matricola) {
		this.matricola = matricola;

	}

	public Integer getMatricola() {
		return matricola;
	}

	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCorsoDiStudi() {
		return corsoDiStudi;
	}

	public void setCorsoDiStudi(String corsoDiStudi) {
		this.corsoDiStudi = corsoDiStudi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matricola;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%-10s", Integer.toString(this.matricola)) + String.format("%-20s", this.cognome)
				+ String.format("%-20s", this.nome) + String.format("%-20s", this.corsoDiStudi);
	}

}
