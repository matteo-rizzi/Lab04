package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getTuttiICorsi();
		
		System.out.println("----");
		
		StudenteDAO sdao = new StudenteDAO();
		sdao.getStudenteByMatricola(new Studente(146101, "VIGGIANO", "CLAUDIO","CIN1T3"));
		
		System.out.println("----");
		System.out.println(cdao.studenteIscrittoACorso(new Studente(146101, "VIGGIANO", "CLAUDIO","CIN1T3"), new Corso("02CIXPG", 8, "Sistemi informativi aziendali", 1)));
		System.out.println(cdao.studenteIscrittoACorso(new Studente(1464101, "sdas", "CLAUDIO","CIN1T3"), new Corso("02CIXPG", 8, "Sistemi informativi aziendali", 1)));

		System.out.println("----");
		System.out.println(cdao.iscriviStudenteACorso(new Studente(192959, "POLITO", "GENNARO","GESZT4"), new Corso("10BDAPG", 8, "Gestione dei progetti", 1)));
		System.out.println(cdao.iscriviStudenteACorso(new Studente(1929591, "POLITO", "GENNARO","GESZT4"), new Corso("10BDAPG", 8, "Gestione dei progetti", 1)));
		
		
		
	}

}
