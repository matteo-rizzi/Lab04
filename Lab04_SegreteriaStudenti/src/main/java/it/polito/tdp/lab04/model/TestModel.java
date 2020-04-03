package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		System.out.println(model.getElencoCorsi());
		System.out.println("----");
		System.out.println(model.getStudenteByMatricola(new Studente(146101, "VIGGIANO", "CLAUDIO","CIN1T3")));
		System.out.println("----");
		System.out.println(model.getStudentiIscrittiAlCorso(new Corso("01KSUPG", 8,"Gestione dell'innovazione e sviluppo prodotto",2)));
		
	}

}
