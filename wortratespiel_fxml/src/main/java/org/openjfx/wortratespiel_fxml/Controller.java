package org.openjfx.wortratespiel_fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

	Wortratespiel spiel = new Wortratespiel();

	@FXML private Label lbl1;

	@FXML private Button btn;

	@FXML private Button btnRestart;

	@FXML private Label lbl2;
	
	@FXML private Label lbl3;

	@FXML private TextField txt;

	@FXML
	private void initialize() {

		spiel.starten();
		lbl1.setText("Bitte gib einen Buchstaben ein: ");
		lbl2.setText("Zu erratenes Wort: " + spiel.bestimmeBisherErratenesWort());
		lbl3.setVisible(false);
		
		txt.setOnAction(e -> handleButtonActionOK());
		btnRestart.setVisible(false);
	    btnRestart.setManaged(false); 
	}

	@FXML
	private void handleButtonActionOK() {

		String text = txt.getText();
		spiel.raten(text);
		lbl2.setText(spiel.bestimmeBisherErratenesWort());
		lbl1.setText("Weiter raten! Gib den nächsten Buchstaben oder das ganze Wort ein: ");
		lbl3.setVisible(true);
		lbl3.setText("Diese Buchstaben hast du schon versucht: \n" + spiel.getGerateneBuchstaben());
		txt.clear();
		if (spiel.istSpielZuEnde()) {
			lbl1.setText("Glückwunsch! Du hast das Wort " + spiel.bestimmeBisherErratenesWort() + " erraten!");
			lbl2.setText(spiel.beenden());
			lbl3.setVisible(false);
			txt.setVisible(false);
			btn.setVisible(false);
			txt.setManaged(false);
			btn.setManaged(false);
			btnRestart.setVisible(true);
	        btnRestart.setManaged(true);
	  		}
		if (spiel.hatSpielerVerloren()) {
            lbl1.setText(spiel.verloren());
            lbl2.setText("Versuche es doch noch einmal!");
            lbl3.setVisible(false);
            txt.setVisible(false);
            btn.setVisible(false);
            txt.setManaged(false);
            btn.setManaged(false);
            btnRestart.setVisible(true);
            btnRestart.setManaged(true);
		}
	}
	
	@FXML
	private void onNeuStarten() {
	    spiel = new Wortratespiel();
	    spiel.starten();

	    
	    lbl1.setText("Neues Spiel! Bitte gib einen Buchstaben ein:");
	    lbl2.setText(spiel.bestimmeBisherErratenesWort());
	    lbl3.setVisible(false);
	    lbl3.setText("Diese Buchstaben hast du schon versucht: \n" + spiel.getGerateneBuchstaben());
	    txt.setDisable(false);
	    btn.setDisable(false);
	    txt.setVisible(true);
		btn.setVisible(true);
		txt.setManaged(true);
		btn.setManaged(true);
	    txt.clear();
	    txt.requestFocus();
	    btnRestart.setVisible(false);
	    btnRestart.setManaged(false);
	}

}
