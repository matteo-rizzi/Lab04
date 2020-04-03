package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				Corso corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(corso);
			}

			conn.close();

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(Corso corso) {
		final String sql = "SELECT * FROM corso WHERE codins=?";

		Corso c = new Corso();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
			}

			conn.close();

			return c;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS FROM studente AS s, iscrizione AS i WHERE s.matricola=i.matricola AND i.codins=?";

		List<Studente> studenti = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matr = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");

				System.out.println(matr + " " + cognome + " " + nome + " " + cds);

				studenti.add(new Studente(matr, cognome, nome, cds));
			}

			conn.close();

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		final String sql = "INSERT INTO iscrizione(matricola,codins) VALUES (?,?)";
		boolean trovato = false;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());

			int rs = st.executeUpdate();

			if (rs != 0)
				trovato = true;

			conn.close();
			return trovato;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	public boolean studenteIscrittoACorso(Studente studente, Corso corso) {
		final String sql = "SELECT codins, matricola FROM iscrizione WHERE matricola=? AND codins=?";
		boolean trovato = false;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Integer matr = rs.getInt("matricola");
				String codins = rs.getString("codins");

				System.out.println(matr + " " + codins);
				if (matr != null && codins != null)
					trovato = true;
			}

			conn.close();
			return trovato;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
