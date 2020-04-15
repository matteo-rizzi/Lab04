package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	//List<Studente> studenti = new ArrayList<>();
	StudenteDAO studenteDAO;
	CorsoDAO corsoDAO;

	public Model() {
		// this.addStudenti();
		studenteDAO = new StudenteDAO();
		corsoDAO = new CorsoDAO();
	}

/*	public void addStudenti() {
		StudenteDAO dao = new StudenteDAO();
		studenti.addAll(dao.getTuttiGliStudenti());
	} */

	public List<Corso> getElencoCorsi() {
		return this.corsoDAO.getTuttiICorsi();
	}

	public Studente getStudenteByMatricola(Studente s) {
	/*	Studente studente = new Studente();
		if (!this.studentePresente(s.getMatricola()))
			studente = null;
		else
			studente = dao.getStudenteByMatricola(s); */
		return this.studenteDAO.getStudenteByMatricola(s);
	}

/*	private boolean studentePresente(int matricola) {
		Studente sTemp = new Studente(matricola);
		boolean trovato = false;
		for (Studente s : studenti) {
			if (s.equals(sTemp))
				trovato = true;
		}
		return trovato;
	} */

	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		CorsoDAO dao = new CorsoDAO();
		return dao.getStudentiIscrittiAlCorso(corso);
	}

	public List<Corso> getCorsiPerStudente(Studente studente) {
	/*	if (!this.studentePresente(studente.getMatricola()))
			return null;
		else
			return dao.getCorsiPerStudente(studente); */
		return this.studenteDAO.getCorsiPerStudente(studente);
	}
	
	public boolean studenteIscrittoACorso(Studente studente, Corso corso) {
		return this.corsoDAO.studenteIscrittoACorso(studente, corso);
	}
	
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		return this.corsoDAO.iscriviStudenteACorso(studente, corso);
	}
	
/*	public Studente cercaStudente(int matricola) {
		Studente sTemp = null;
		for (Studente s : studenti)
			if (s.getMatricola()==matricola)
				sTemp = s;
		return sTemp;
	} */

}
