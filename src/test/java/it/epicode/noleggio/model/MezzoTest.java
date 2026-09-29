package it.epicode.noleggio.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

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

    //Scrivo i casi di test per gestire i seguenti criteri di accettazione: 
    // // Criteri di accettazione
    // 1: avvio contratto di noleggio di 7 giorni --> il sistema non applica uno sconto 
    @Test 
    void noScontoPerSetteGiorni() {
        Scooter s = new Scooter("SC777XX", "Vespa", 25);
        assertEquals(175.0, s.calcolaCosto(7), 0.001);   // 7 * 25 = 175, nessuno sconto
    }
    // 2:avvio contratto di noleggio in un range di 8 o più giorni --> il sistema applica uno sconto
    @Test
    void scontoPerOttoGiorni() {
        Scooter s = new Scooter("SC777XX", "Vespa", 25);
        assertEquals(160.0, s.calcolaCosto(8), 0.001);   // 200 - 20%
    }
    // 3: noleggio annullato l'ottavo giorno
    @Test
    void annullamentoOttavoGiorno() {
        Scooter s = new Scooter("SC777XX", "Vespa", 25);
        s.noleggia();
        s.annullaNoleggio();
        assertEquals(0.0, s.calcolaCosto(0), 0.001);   // noleggio annullato, costo 0
    }

}
