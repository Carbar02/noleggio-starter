package it.epicode.noleggio.service;

import it.epicode.noleggio.exception.MezzoNonDisponibileException;
import it.epicode.noleggio.model.Cliente;
import it.epicode.noleggio.model.Mezzo;
import it.epicode.noleggio.model.Noleggio;
import it.epicode.noleggio.model.Ricaricabile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AgenziaNoleggio {

    private final String nome;
    private final List<Mezzo> flotta = new ArrayList<>();
    private final List<Noleggio> storico = new ArrayList<>();

    public AgenziaNoleggio(String nome) {
        this.nome = nome;
    }

    public void aggiungiMezzo(Mezzo mezzo) {
        if (flotta.contains(mezzo)) {            // usa equals() basato sulla targa
            throw new IllegalArgumentException("Targa gia' presente: " + mezzo.getTarga());
        }
        flotta.add(mezzo);
    }

    public List<Mezzo> getDisponibili() {
        List<Mezzo> risultato = new ArrayList<>();
        for (int i = 1; i < flotta.size(); i++) {
            if (flotta.get(i).isDisponibile()) {
                risultato.add(flotta.get(i));
            }
        }
        return risultato;
    }

    public Noleggio noleggia(String targa, Cliente cliente, int giorni) throws MezzoNonDisponibileException {
        Mezzo mezzo = cercaPerTarga(targa);
        if (!mezzo.isDisponibile()) {
            throw new MezzoNonDisponibileException("Il mezzo " + targa + " e' gia' noleggiato");
        }
        Noleggio noleggio = new Noleggio(mezzo, cliente, giorni);   // calcola il costo (valida i giorni)
        mezzo.noleggia();
        storico.add(noleggio);
        return noleggio;
    }

    public void restituisci(String targa) {
        Mezzo mezzo = cercaPerTarga(targa);
        if (mezzo == null) {
            throw new IllegalArgumentException("Nessun mezzo con targa " + targa);
        }
        mezzo.restituisci();
    }

    public double incassoTotale() {
        double totale = 0;
        for (Noleggio n : storico) {
            totale += n.getCosto();
        }
        return totale;
    }

    /** instanceof per filtrare i mezzi che hanno una capacita' in piu'. */
    public List<Ricaricabile> getRicaricabili() {
        List<Ricaricabile> risultato = new ArrayList<>();
        for (Mezzo m : flotta) {
            if (m instanceof Ricaricabile) {
                risultato.add((Ricaricabile) m);
            }
        }
        return risultato;
    }

    public List<Mezzo> getFlotta() {
        return Collections.unmodifiableList(flotta);   // nessuno puo' modificare la lista dall'esterno
    }

    public List<Noleggio> getStorico() {
        return Collections.unmodifiableList(storico);
    }

    public String getNome() {
        return nome;
    }

    private Mezzo cercaPerTarga(String targa) {
        for (Mezzo m : flotta) {
            if (m.getTarga().equalsIgnoreCase(targa)) {
                return m;
            }
        }
        return null;
    }
}
