package it.epicode.noleggio.service;

import it.epicode.noleggio.exception.MezzoNonDisponibileException;
import it.epicode.noleggio.model.Auto;
import it.epicode.noleggio.model.AutoElettrica;
import it.epicode.noleggio.model.Cliente;
import it.epicode.noleggio.model.Noleggio;
import it.epicode.noleggio.model.Scooter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgenziaNoleggioTest {

    private AgenziaNoleggio agenzia;
    private Cliente anna;

    @BeforeEach
    void setUp() {
        agenzia = new AgenziaNoleggio("Test");
        agenzia.aggiungiMezzo(new Auto("AB123CD", "Panda", 40, 5));
        agenzia.aggiungiMezzo(new AutoElettrica("EL001EV", "Model 3", 90, 5, 450));
        agenzia.aggiungiMezzo(new Scooter("SC777XX", "Vespa", 25));
        anna = new Cliente("RSSNNA85M41H501Z", "Anna Rossi", "anna@mail.it");
    }


    @Test
    void noleggioRendeIlMezzoNonDisponibile() throws MezzoNonDisponibileException {
        agenzia.noleggia("AB123CD", anna, 2);
        assertEquals(2, agenzia.getDisponibili().size());
    }

    @Test
    void noleggioCalcolaIlCosto() throws MezzoNonDisponibileException {
        Noleggio n = agenzia.noleggia("AB123CD", anna, 3);
        assertEquals(120.0, n.getCosto(), 0.001);
    }

    @Test
    void mezzoGiaNoleggiatoLanciaEccezione() throws MezzoNonDisponibileException {
        agenzia.noleggia("AB123CD", anna, 2);
        assertThrows(MezzoNonDisponibileException.class, () -> agenzia.noleggia("AB123CD", anna, 1));
    }



    @Test
    void incassoSommaTuttiINoleggi() throws MezzoNonDisponibileException {
        agenzia.noleggia("AB123CD", anna, 3);    // 120
        agenzia.noleggia("SC777XX", anna, 8);    // 160
        assertEquals(280.0, agenzia.incassoTotale(), 0.001);
    }

    @Test
    void targaDuplicataRifiutata() {
        assertThrows(IllegalArgumentException.class,
                () -> agenzia.aggiungiMezzo(new Auto("AB123CD", "Punto", 35, 5)));
    }

    @Test
    void soloIMezziElettriciSonoRicaricabili() {
        assertEquals(1, agenzia.getRicaricabili().size());   // solo l'auto elettrica
    }

    @Test
    void laFlottaNonSiModificaDallEsterno() {
        assertThrows(UnsupportedOperationException.class, () -> agenzia.getFlotta().clear());
    }
}
