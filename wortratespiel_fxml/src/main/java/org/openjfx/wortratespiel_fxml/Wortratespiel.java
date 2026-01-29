package org.openjfx.wortratespiel_fxml;

import java.util.Random;
import java.util.Set;

import javafx.scene.image.Image;

import java.util.HashSet;
import java.util.Objects;

public class Wortratespiel {

	private String[] wortliste = { "Haus", "Auto", "Schule", "Hund", "Katze", "Maus", "Buch", "Tisch", "Stuhl",
			"Fenster", "Garten", "Blume", "Baum", "Computer", "Lampe", "Apfel", "Banane", "Wolke", "Sonne", "Regen",
			"Vogel", "Fisch", "Berg", "Tal", "Straße", "Brücke", "Zug", "Bus", "Uhr", "Kuchen", "Glas", "Becher", "Tür",
			"Bett", "Decke", "Kissen", "Hemd", "Jacke", "Schuh", "Hose", "Freund", "Kind", "Mutter", "Vater", "Lehrer",
			"Arzt", "Feuer", "Wasser", "Wind", "Erde", "Brot", "Milch", "Käse", "Ei", "Butter", "Zucker", "Salz",
			"Pfanne", "Topf", "Löffel", "Fahrrad", "Ball", "Spiel", "Musik", "Tanz", "Film", "Bilder", "Traum", "Herz",
			"Licht", "Wald", "Meer", "Fluss", "Insel", "Boot", "Autohaus", "Blatt", "Schnee", "Eis", "Wiese", "Sommer",
			"Winter", "Frühling", "Herbst", "Abend", "Morgen", "Nacht", "Tag", "Woche", "Jahr" };

	private Random random = new Random();
	private String originalWort;
	private Set<String> nichtErrateneBuchstaben;
	private Set<String> gerateneBuchstaben;
	private int anzahlVersuche;
	private int fehlerZaehler;
	public int getFehlerZaehler() {
		return fehlerZaehler;
	}

	public void setFehlerZaehler(int fehlerZaehler) {
		this.fehlerZaehler = fehlerZaehler;
	}

	private int maxFehler = 7;
	private String bildpfad = "org/openjfx/wortratespiel_fxml/images/pinguin" + fehlerZaehler + 1 + ".png";

	public void starten() {
		setAnzahlVersuche(0);
		setOriginalWort(wortliste[random.nextInt(wortliste.length)]);
		setNichtErrateneBuchstaben(new HashSet<>());
		gerateneBuchstaben = new HashSet<>();
		String[] zeichenInWort = getOriginalWort().split("");
		for (String zeichen : zeichenInWort) {
			getNichtErrateneBuchstaben().add(zeichen);
		}
		fehlerZaehler = 0;
	}

	public String getBildpfad() {
		return bildpfad;
	}

	public Image aktualisiereBild() {
		int bildzahl = fehlerZaehler + 1;
		String bildPfad = "/org/openjfx/wortratespiel_fxml/images/pinguin" + bildzahl + ".png";

		return new Image(
				Objects.requireNonNull(getClass().getResourceAsStream(bildPfad), "Bild nicht gefunden: " + bildPfad));

	}

	public String bestimmeBisherErratenesWort() {
		String bisherErratenesWort = getOriginalWort();
		for (String zeichen : getNichtErrateneBuchstaben()) {
			bisherErratenesWort = bisherErratenesWort.replaceAll(zeichen, "*");
		}
		return bisherErratenesWort;
	}

	public void raten(String eingabe) {

		if (eingabe.length() == 1) {
			eingabe = eingabe.toUpperCase();
			getNichtErrateneBuchstaben().remove(eingabe);
			eingabe = eingabe.toLowerCase();
			getNichtErrateneBuchstaben().remove(eingabe);
			setAnzahlVersuche(getAnzahlVersuche() + 1);
			gerateneBuchstaben.add(eingabe);
		} else {
			if (eingabe.equalsIgnoreCase(getOriginalWort())) {
				getNichtErrateneBuchstaben().clear();
				beenden();
			} else {
				setAnzahlVersuche(getAnzahlVersuche() + 1);
			}
		}

		if (!getOriginalWort().toLowerCase().contains(eingabe.toLowerCase())) {
			fehlerZaehler++;
			if (fehlerZaehler >= getMaxFehler()) {
				getNichtErrateneBuchstaben().clear();
				verloren();
			}
		}
	}

	public String getGerateneBuchstaben() {
		if (gerateneBuchstaben.isEmpty()) {
			return "noch keine";
		} else
			return String.join(", ", gerateneBuchstaben);
	}

	public boolean istSpielZuEnde() {
		return getNichtErrateneBuchstaben().isEmpty();
	}

	public boolean hatSpielerVerloren() {
		return fehlerZaehler >= getMaxFehler();
	}

	public String beenden() {
		return "Du hast " + getAnzahlVersuche() + " Versuche gebraucht.";
		

	}

	public String verloren() {
		return "Du hast leider verloren! Das Wort war: " + getOriginalWort() + ".";
		
	}

	public Set<String> getNichtErrateneBuchstaben() {
		return nichtErrateneBuchstaben;
	}

	public void setNichtErrateneBuchstaben(Set<String> nichtErrateneBuchstaben) {
		this.nichtErrateneBuchstaben = nichtErrateneBuchstaben;
	}

	public int getAnzahlVersuche() {
		return anzahlVersuche;
	}

	public void setAnzahlVersuche(int anzahlVersuche) {
		this.anzahlVersuche = anzahlVersuche;
	}

	public String getOriginalWort() {
		return originalWort;
	}

	public void setOriginalWort(String originalWort) {
		this.originalWort = originalWort;
	}

	public int getMaxFehler() {
		return maxFehler;
	}

	public void setMaxFehler(int maxFehler) {
		this.maxFehler = maxFehler;
	}

}
