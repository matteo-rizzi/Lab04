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

public class StudenteDAO {

	public Studente getStudenteByMatricola(Studente s) {

		final String sql = "SELECT * FROM studente WHERE matricola = ?";

		Studente studente = new Studente();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Integer matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");

				studente = new Studente(matricola, cognome, nome, cds);
			}

			conn.close();

			return studente;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Integer matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");

				System.out.println(matricola + " " + cognome + " " + nome + " " + cds);

				Studente studente = new Studente(matricola, cognome, nome, cds);
				studenti.add(studente);
			}

			conn.close();

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	public List<Corso> getCorsiPerStudente(Studente studente) {
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd FROM corso AS c, iscrizione AS i WHERE c.codins=i.codins AND matricola=?";

		List<Corso> corsi = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");

				System.out.println(codins + " " + crediti + " " + nome + " " + pd);

				corsi.add(new Corso(codins, crediti, nome, pd));
			}

			conn.close();

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
