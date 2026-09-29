package it.epicode.noleggio.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MezzoTest {

    @Test
    void autoCostaTariffaPerGiorni() {
        Auto auto = new Auto("AB123CD", "Panda", 40, 5);
        assertEquals(120.0, auto.calcolaCosto(3), 0.001);
    }

    @Test
    void autoElettricaHaDiecePerCentoDiSconto() {
        AutoElettrica e = new AutoElettrica("EL001EV", "Model 3", 100, 5, 450);
        assertEquals(270.0, e.calcolaCosto(3), 0.001);
    }


    @Test
    void scooterConScontoDallOttavoGiorno() {
        Scooter s = new Scooter("SC777XX", "Vespa", 25);
        assertEquals(160.0, s.calcolaCosto(8), 0.001);   // 200 - 20%
    }

    @Test
    void furgoneAggiungeCostoPulizia() {
        Furgone f = new Furgone("FG555ZZ", "Ducato", 70, 1200);
        assertEquals(165.0, f.calcolaCosto(2), 0.001);
    }

    @Test
    void giorniZeroNonAmmessi() {
        Auto auto = new Auto("AB123CD", "Panda", 40, 5);
        assertThrows(IllegalArgumentException.class, () -> auto.calcolaCosto(0));
    }

    @Test
    void tariffaNegativaRifiutata() {
        assertThrows(IllegalArgumentException.class, () -> new Auto("AB123CD", "Panda", -1, 5));
    }

    @Test
    void mezzoGiaNoleggiatoNonSiRinoleggia() {
        Auto auto = new Auto("AB123CD", "Panda", 40, 5);
        auto.noleggia();
        assertThrows(IllegalStateException.class, auto::noleggia);
    }

    @Test
    void stessaTargaStessoMezzo() {
        assertEquals(new Auto("ab123cd", "Panda", 40, 5), new Furgone("AB123CD", "Ducato", 70, 900));
    }

    @Test
    void emailSenzaChiocciolaRifiutata() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente("RSSNNA85M41H501Z", "Anna", "anna.mail.it"));
    }
}
