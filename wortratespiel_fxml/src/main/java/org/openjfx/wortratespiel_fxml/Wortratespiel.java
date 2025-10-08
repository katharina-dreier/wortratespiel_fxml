package org.openjfx.wortratespiel_fxml;
import java.util.Random;
import java.util.Set;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


public class Wortratespiel {
	
	private String [] wortliste = {"Haus", "Auto", "Schule", "Hund", "Katze", "Maus", "Buch", "Tisch", "Stuhl", "Fenster",
			 "Garten", "Blume", "Baum", "Computer", "Lampe", "Apfel", "Banane", "Wolke", "Sonne", "Regen",
			 "Vogel", "Fisch", "Berg", "Tal", "Straße", "Brücke", "Zug", "Bus", "Uhr", "Kuchen",
			 "Glas", "Becher", "Tür", "Bett", "Decke", "Kissen", "Hemd", "Jacke", "Schuh", "Hose",
			 "Freund", "Kind", "Mutter", "Vater", "Lehrer", "Arzt", "Feuer", "Wasser", "Wind", "Erde",
			 "Brot", "Milch", "Käse", "Ei", "Butter", "Zucker", "Salz", "Pfanne", "Topf", "Löffel",
			 "Fahrrad", "Ball", "Spiel", "Musik", "Tanz", "Film", "Bilder", "Traum", "Herz", "Licht",
			 "Wald", "Meer", "Fluss", "Insel", "Boot", "Autohaus", "Blatt", "Schnee", "Eis", "Wiese",
			 "Sommer", "Winter", "Frühling", "Herbst", "Abend", "Morgen", "Nacht", "Tag", "Woche", "Jahr"};
	
	private Random random = new Random();
	private List<String> bilder = new ArrayList<String>();
	private String originalWort;
	private Set<String> nichtErrateneBuchstaben;
	private Set<String> gerateneBuchstaben;
	private int anzahlVersuche;
	public int fehlerZaehler;
	private int maxFehler = 7;
	private String bildpfad = "org/openjfx/wortratespiel_fxml/images/pinguin" + fehlerZaehler+1 + ".png";
	

	
	public void starten() {
		anzahlVersuche = 0;
		originalWort = wortliste[random.nextInt(wortliste.length)];
		nichtErrateneBuchstaben = new HashSet<String>();
		gerateneBuchstaben = new HashSet<String>();
		String[] zeichenInWort = originalWort.split("");
		for (String zeichen : zeichenInWort) {
			nichtErrateneBuchstaben.add(zeichen);
		}
		fehlerZaehler = 0;}
		
	public String getBildpfad() {
		return bildpfad;
	}
	
	public Image aktualisiereBild() {
        int bildzahl = fehlerZaehler+1;
		String bildPfad = String.format(
            "/org/openjfx/wortratespiel_fxml/images/pinguin" + bildzahl + ".png");

        Image bild = new Image(Objects.requireNonNull(
            getClass().getResourceAsStream(bildPfad),
            "Bild nicht gefunden: " + bildPfad
        ));

        return bild;
    }
	
	public String bestimmeBisherErratenesWort() {
		String bisherErratenesWort = new String(originalWort);
		for (String zeichen : nichtErrateneBuchstaben) {
			bisherErratenesWort = bisherErratenesWort.replaceAll(zeichen, "*");
		}
		return bisherErratenesWort;
	}
	
	public void raten(String eingabe) {
		
		if (eingabe.length() == 1) {
			eingabe = eingabe.toUpperCase();
			nichtErrateneBuchstaben.remove(eingabe);
			eingabe = eingabe.toLowerCase();
			nichtErrateneBuchstaben.remove(eingabe);
			anzahlVersuche++;
			gerateneBuchstaben.add(eingabe);
		}
		else {if (eingabe.equalsIgnoreCase(originalWort)) {
            nichtErrateneBuchstaben.clear();
            beenden();
        	} 
		else {anzahlVersuche++;}
			}
        
			if (!originalWort.toLowerCase().contains(eingabe.toLowerCase())) {
				fehlerZaehler++;
				if (fehlerZaehler >= maxFehler) {
					nichtErrateneBuchstaben.clear();
					verloren();
				}
			}
	}

	public String getGerateneBuchstaben() {
		if (gerateneBuchstaben.isEmpty()) {
			return "noch keine";
		}
		else return String.join(", ", gerateneBuchstaben);
	}
	
	public boolean istSpielZuEnde() {
		return nichtErrateneBuchstaben.isEmpty();
	}
	
	public boolean hatSpielerVerloren() {
		return fehlerZaehler >= maxFehler;
	}
	
	public String beenden() {
		String ende = "Du hast " + anzahlVersuche + " Versuche gebraucht.";
		return ende;
		
	}
	
	public String verloren() {
		String verloren = "Du hast leider verloren! Das Wort war: " + originalWort + ".";
		return verloren;
	}
	
}
