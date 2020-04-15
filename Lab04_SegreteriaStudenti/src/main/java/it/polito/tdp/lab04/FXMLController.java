package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<Corso> boxCorso;

	@FXML
	private Button btnCercaIscritti;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnCompleta;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnReset;

	@FXML
	void cercaCorsi(ActionEvent event) {
		txtResult.clear();
		int matricola = -1;

		try {
			matricola = Integer.parseInt(txtMatricola.getText());
		} catch (NumberFormatException e) {
			txtResult.setText("Hai inserito una matricola con un formato errato! Usa solo caratteri numerici.");
			return;
		}

		Studente studente = this.model.getStudenteByMatricola(new Studente(matricola, null, null, null));

		List<Corso> corsi = this.model.getCorsiPerStudente(studente);
		if (corsi == null) {
			txtResult.setText("ERRORE! La matricola inserita non corrisponde ad alcuno studente");
			return;
		} else {
			for (Corso c : corsi) {
				txtResult.appendText(c.descriviti() + "\n");
			}
			return;
		}

	}

	@FXML
	void cercaIscritti(ActionEvent event) {
		txtResult.clear();

		Corso corso = this.boxCorso.getValue();
		if (corso.equals(new Corso())) {
			txtResult.setText("Errore! Non hai selezionato alcun corso.\n");
			return;
		} else {
			for (Studente s : this.model.getStudentiIscrittiAlCorso(corso))
				txtResult.appendText(s.toString() + "\n");
			return;
		}

	}

	@FXML
	void completaInserimento(ActionEvent event) {
		txtResult.clear();
		int matricola = -1;

		try {
			matricola = Integer.parseInt(txtMatricola.getText());
		} catch (NumberFormatException e) {
			txtResult.setText("Hai inserito una matricola con un formato errato! Usa solo caratteri numerici.");
			return;
		}

		Studente studente = this.model.getStudenteByMatricola(new Studente(matricola, null, null, null));

		if (studente.getMatricola() == null) {
			txtResult.setText("La matricola inserita non corrisponde ad alcuno studente!");
			return;
		}
		txtNome.setText(studente.getNome());
		txtCognome.setText(studente.getCognome());

	}

	@FXML
	void doReset(ActionEvent event) {
		this.txtMatricola.clear();
		this.txtCognome.clear();
		this.txtNome.clear();
		this.txtResult.clear();
	}

	@FXML
	void iscriviStudente(ActionEvent event) {
		txtResult.clear();
		int matricola = -1;
		Corso corso = this.boxCorso.getValue();

		try {
			matricola = Integer.parseInt(txtMatricola.getText());
		} catch (NumberFormatException e) {
			txtResult.setText("Hai inserito una matricola con un formato errato! Usa solo caratteri numerici.");
			return;
		}

		Studente studente = this.model.getStudenteByMatricola(new Studente(matricola, null, null, null));
		if (this.model.studenteIscrittoACorso(studente, corso))
			txtResult.setText("Studente già iscritto a questo corso");
		else if (this.model.iscriviStudenteACorso(studente, corso))
			txtResult.setText("Studente iscritto al corso!");
		else
			txtResult.setText("Errore nell'inserimento dello studente al corso!");

	}

	@FXML
	void initialize() {
		assert boxCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCompleta != null : "fx:id=\"btnCompleta\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		Corso vuoto = new Corso();
		boxCorso.getItems().add(vuoto);
		boxCorso.getItems().addAll(this.model.getElencoCorsi());
		boxCorso.setValue(vuoto);
	}
}
