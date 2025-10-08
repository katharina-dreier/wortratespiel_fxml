package org.openjfx.wortratespiel_fxml;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Controller {

	Wortratespiel spiel = new Wortratespiel();
	@FXML private StackPane imgPane;
	@FXML private ImageView img;
	@FXML private Label ttl;
	@FXML private Label lbl1;
	@FXML private Label lbl2;
	@FXML private Label lbl3;
	@FXML private Button btn;
	@FXML private Button btnRestart;
	@FXML private Button btnStart;
	@FXML private TextField txt;
	@FXML private Separator sep;

	@FXML
	private void initialize() {

		ttl.setText("🐧 Willkommen zum Wortratespiel!");
		lbl1.setText("Finde das geheime Wort, bevor das Eis unter dem Pinguin vollständig schmilzt!\r\n"
				+ "Du kannst einzelne Buchstaben raten oder das ganze Wort auf einmal versuchen.\r\n"
				+ "Jeder falsche Buchstabe bringt den kleinen Pinguin seinem kalten Bad ein Stück näher – also wähle weise!\r\n"
				+ "\r\n"
				+ "Viel Spaß beim Knobeln und viel Glück beim Retten des Pinguins!");
	}
	
	@FXML
	void onStarten() {
		spiel.starten();
		imgPane.setVisible(true);
	    imgPane.setManaged(true);
	    img.setVisible(true);
	    img.setManaged(true);
	    Image bild = spiel.aktualisiereBild();
		img.setImage(bild);
		ttl.setText("Rette den Pinguin! 🐧");
		lbl1.setText("Bitte gib einen Buchstaben ein: ");
		lbl2.setVisible(true);
		lbl2.setManaged(true);
		lbl2.setText("Zu erratenes Wort: " + spiel.bestimmeBisherErratenesWort());
		lbl3.setVisible(false);
		lbl3.setManaged(false);
		btn.setVisible(true);
		btn.setManaged(true);
		btnRestart.setVisible(false);
	    btnRestart.setManaged(false); 
	    btnStart.setVisible(false);
	    btnStart.setManaged(false); 
		sep.setVisible(true);
		sep.setManaged(true);
		txt.setVisible(true);
		txt.setManaged(true);
        txt.requestFocus();
		txt.setOnAction(e -> handleButtonActionOK());
		
	}

	@FXML
	private void handleButtonActionOK() {
		String text = txt.getText();
		spiel.raten(text);
		Image bild = spiel.aktualisiereBild();
		img.setImage(bild);
		lbl1.setText("Weiter raten! Gib den nächsten Buchstaben oder das ganze Wort ein: ");
		lbl2.setText(spiel.bestimmeBisherErratenesWort());
		lbl3.setVisible(true);
		lbl3.setManaged(true);
		lbl3.setText("Diese Buchstaben hast du schon versucht: \n" + spiel.getGerateneBuchstaben() + "\nFehler: "
				+ spiel.fehlerZaehler);
		txt.clear();
		txt.requestFocus();
		
		if (spiel.istSpielZuEnde()) {
			lbl1.setText("Glückwunsch! Du hast das Wort " + spiel.bestimmeBisherErratenesWort() + " erraten!");
			lbl2.setText(spiel.beenden());
			lbl3.setVisible(false);
			btn.setVisible(false);
			btn.setManaged(false);
			btnRestart.setVisible(true);
	        btnRestart.setManaged(true);
			txt.setVisible(false);
			txt.setManaged(false);
	  		}
		if (spiel.hatSpielerVerloren()) {
			bild = spiel.aktualisiereBild();
			img.setImage(bild);
            lbl1.setText(spiel.verloren());
            lbl2.setText("Versuche es doch noch einmal!");
            lbl3.setVisible(false);
            btn.setVisible(false);
            btn.setManaged(false);
            btnRestart.setVisible(true);
            btnRestart.setManaged(true);
            txt.setVisible(false);
            txt.setManaged(false); 
		}
	}
	
	@FXML
	private void onNeuStarten() {
	    spiel = new Wortratespiel();
	    spiel.starten();
	    Image bild = spiel.aktualisiereBild();
		img.setImage(bild);
	    
	    lbl1.setText("Neues Spiel! Bitte gib einen Buchstaben ein:");
	    lbl2.setText(spiel.bestimmeBisherErratenesWort());
	    lbl3.setVisible(false);
	    btn.setVisible(true);
	    btn.setDisable(false);
	    btn.setManaged(true);
	    btnRestart.setVisible(false);
	    btnRestart.setManaged(false);
	    txt.setVisible(true);
	    txt.setDisable(false);
		txt.setManaged(true);
	    txt.clear();
	    txt.requestFocus();
	   
	}

}
