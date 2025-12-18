package org.openjfx.wortratespiel_fxml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_Wortratespiel {

	@Test
	void testHatSpielerVerloren_Bei2Fehler() {
		Wortratespiel spiel = new Wortratespiel();
		spiel.fehlerZaehler = 2;
		assertFalse(spiel.hatSpielerVerloren());
	}
	
	@Test
	void testHatSpielerVerloren_Bei7Fehler() {
		Wortratespiel spiel = new Wortratespiel();
		spiel.fehlerZaehler = 7;
		assertTrue(spiel.hatSpielerVerloren());
	}

	@Test
	void testGerateneBuchstaben_Keine() {
		Wortratespiel spiel = new Wortratespiel();
		spiel.starten();
		assertEquals("noch keine", spiel.getGerateneBuchstaben());
	}
	
	@Test
	void testGerateneBuchstaben_Mehrere() {
		Wortratespiel spiel = new Wortratespiel();
		spiel.starten();
		spiel.raten("a");
		spiel.raten("b");
		spiel.raten("c");
		assertEquals("a, b, c", spiel.getGerateneBuchstaben());
	}
	
	@Test
	void testIstSpielZuEnde_WortErraten() {
		Wortratespiel spiel = new Wortratespiel();
		spiel.starten();
		spiel.getNichtErrateneBuchstaben().clear(); // Simuliere, dass alle Buchstaben erraten wurden
		assertTrue(spiel.istSpielZuEnde());
	}
	
	@Test
	void testIstSpielZuEnde_WortNichtErraten() {
		Wortratespiel spiel = new Wortratespiel();
		spiel.starten();
		assertFalse(spiel.istSpielZuEnde());
	}
	
	@Test 
	void testRaten_FalschesWort() {
		Wortratespiel spiel = new Wortratespiel();
		spiel.starten();
		int anzahlVersucheVorher = spiel.getAnzahlVersuche();
		spiel.raten("falschesWort");
		assertEquals(anzahlVersucheVorher + 1, spiel.getAnzahlVersuche());
	}
	
	@Test
	void testRaten_RichtigesWort() {
		Wortratespiel spiel = new Wortratespiel();
		spiel.starten();
		String korrektesWort = spiel.getOriginalWort();
		spiel.raten(korrektesWort);
		assertTrue(spiel.istSpielZuEnde());
	}
	
	@Test
    void testRaten_maxFehlerErreicht() {
		Wortratespiel spiel = new Wortratespiel();
		spiel.starten();
		for (int i = 0; i < spiel.getMaxFehler(); i++) {
			spiel.raten("falscherBuchstabe" + i);
		}
		assertTrue(spiel.hatSpielerVerloren());
		
	}
}
