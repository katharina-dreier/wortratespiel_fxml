package org.openjfx.wortratespiel_fxml;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

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
	
	private String originalWort;
	private Set<String> nichtErrateneBuchstaben;
	private int anzahlVersuche;
	
	public void starten() {
		anzahlVersuche = 0;
		originalWort = wortliste[random.nextInt(wortliste.length)];
		nichtErrateneBuchstaben = new HashSet<String>();
		String[] zeichenInWort = originalWort.split("");
		for (String zeichen : zeichenInWort) {
			nichtErrateneBuchstaben.add(zeichen);
		}
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
		}
	}
	
	public boolean istSpielZuEnde() {
		return nichtErrateneBuchstaben.isEmpty();
	}
	
	public String beenden() {
		String ende = "Du hast " + anzahlVersuche + " Versuche gebraucht.";
		return ende;
		
	}
	
}
