package it.epicode.noleggio.app;

import it.epicode.noleggio.exception.MezzoNonDisponibileException;
import it.epicode.noleggio.model.Auto;
import it.epicode.noleggio.model.AutoElettrica;
import it.epicode.noleggio.model.Cliente;
import it.epicode.noleggio.model.Furgone;
import it.epicode.noleggio.model.Mezzo;
import it.epicode.noleggio.model.Ricaricabile;
import it.epicode.noleggio.model.Scooter;
import it.epicode.noleggio.model.ScooterElettrico;
import it.epicode.noleggio.service.AgenziaNoleggio;

public class Main {

    public static void main(String[] args) {
        AgenziaNoleggio agenzia = new AgenziaNoleggio("Epicode Rent");
        agenzia.aggiungiMezzo(new Auto("AB123CD", "Panda", 40, 5));
        agenzia.aggiungiMezzo(new AutoElettrica("EL001EV", "Model 3", 90, 5, 450));
        agenzia.aggiungiMezzo(new Scooter("SC777XX", "Vespa", 25));
        agenzia.aggiungiMezzo(new ScooterElettrico("SE100EL", "Elettrica", 30, 100));
        agenzia.aggiungiMezzo(new Furgone("FG555ZZ", "Ducato", 70, 1200));

        Cliente anna = new Cliente("RSSNNA85M41H501Z", "Anna Rossi", "anna@mail.it");

        System.out.println("== Flotta");
        for (Mezzo m : agenzia.getFlotta()) {
            System.out.println(m);
        }

        System.out.println("== Preventivi per 10 giorni (polimorfismo)");
        for (Mezzo m : agenzia.getFlotta()) {
            System.out.printf("%-18s %8.2f euro%n", m.getTipo(), m.calcolaCosto(10));
        }

        System.out.println("== Noleggi");
        try {
            System.out.println(agenzia.noleggia("AB123CD", anna, 3));
            System.out.println(agenzia.noleggia("SC777XX", anna, 6));
            agenzia.noleggia("AB123CD", anna, 2);           // gia' noleggiata
        } catch (MezzoNonDisponibileException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        System.out.println("== Disponibili: " + agenzia.getDisponibili().size());
        System.out.printf("== Incasso totale: %.2f euro%n", agenzia.incassoTotale());

        System.out.println("== Ricaricabili");
        for (Ricaricabile r : agenzia.getRicaricabili()) {
            System.out.println(r + " -> 300 km ok? " + r.autonomiaSufficiente(300));
        }
    }
}
