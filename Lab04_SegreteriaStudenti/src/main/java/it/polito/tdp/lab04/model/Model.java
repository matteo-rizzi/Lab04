package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	List<Studente> studenti = new ArrayList<>();

	public Model() {
		this.addStudenti();
	}

	public void addStudenti() {
		StudenteDAO dao = new StudenteDAO();
		studenti.addAll(dao.getTuttiGliStudenti());
	}

	public List<Corso> getElencoCorsi() {
		CorsoDAO dao = new CorsoDAO();
		return dao.getTuttiICorsi();
	}

	public Studente getStudenteByMatricola(Studente s) {
		StudenteDAO dao = new StudenteDAO();
		Studente studente = new Studente();
		if (!this.studentePresente(s.getMatricola()))
			studente = null;
		else
			studente = dao.getStudenteByMatricola(s);
		return studente;
	}

	private boolean studentePresente(int matricola) {
		Studente sTemp = new Studente(matricola);
		boolean trovato = false;
		for (Studente s : studenti) {
			if (s.equals(sTemp))
				trovato = true;
		}
		return trovato;
	}

	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		CorsoDAO dao = new CorsoDAO();
		return dao.getStudentiIscrittiAlCorso(corso);
	}

	public List<Corso> getCorsiPerStudente(Studente studente) {
		StudenteDAO dao = new StudenteDAO();
		if (!this.studentePresente(studente.getMatricola()))
			return null;
		else
			return dao.getCorsiPerStudente(studente);
	}
	
	public boolean studenteIscrittoACorso(Studente studente, Corso corso) {
		CorsoDAO dao = new CorsoDAO();
		return dao.studenteIscrittoACorso(studente, corso);
	}
	
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		CorsoDAO dao = new CorsoDAO();
		return dao.iscriviStudenteACorso(studente, corso);
	}
	
	public Studente cercaStudente(int matricola) {
		Studente sTemp = null;
		for (Studente s : studenti)
			if (s.getMatricola()==matricola)
				sTemp = s;
		return sTemp;
	}

}
