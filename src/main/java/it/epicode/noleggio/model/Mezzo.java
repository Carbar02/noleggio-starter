package it.epicode.noleggio.model;

import java.util.Objects;

/**
 * Classe astratta: raccoglie stato e comportamento comuni a tutti i mezzi.
 * Non si istanzia: "un mezzo generico" non esiste, esistono auto, scooter, furgoni.
 */
public abstract class Mezzo implements Noleggiabile {

    private final String targa;
    private final String modello;
    private final double tariffaGiornaliera;
    private boolean disponibile = true;

    protected Mezzo(String targa, String modello, double tariffaGiornaliera) {
        if (targa == null || targa.isBlank()) {
            throw new IllegalArgumentException("La targa e' obbligatoria");
        }
        if (tariffaGiornaliera <= 0) {
            throw new IllegalArgumentException("La tariffa deve essere positiva: " + tariffaGiornaliera);
        }
        this.targa = targa.toUpperCase();
        this.modello = modello;
        this.tariffaGiornaliera = tariffaGiornaliera;
    }

    /** Ogni sottoclasse sa come si calcola il proprio costo: e' il punto del polimorfismo. */
    @Override
    public abstract double calcolaCosto(int giorni);

    public abstract String getTipo();

    /** Metodo di supporto per le sottoclassi: protected, non fa parte dell'interfaccia pubblica. */
    protected double costoBase(int giorni) {
        if (giorni < 1) {
            throw new IllegalArgumentException("I giorni devono essere almeno 1: " + giorni);
        }
        return tariffaGiornaliera * giorni;
    }

    @Override
    public boolean isDisponibile() {
        return disponibile;
    }

    @Override
    public void noleggia() {
        if (!disponibile) {
            throw new IllegalStateException("Mezzo gia' noleggiato: " + targa);
        }
        disponibile = false;
    }

    @Override
    public void restituisci() {
        disponibile = true;
    }

    public String getTarga() {
        return targa;
    }

    public String getModello() {
        return modello;
    }

    public double getTariffaGiornaliera() {
        return tariffaGiornaliera;
    }

    @Override
    public String toString() {
        return String.format("%s %s [%s] - %.2f euro/giorno%s",
                getTipo(), modello, targa, tariffaGiornaliera, disponibile ? "" : " (noleggiato)");
    }

    /** Due mezzi sono lo stesso mezzo se hanno la stessa targa. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mezzo)) {
            return false;
        }
        return targa.equals(((Mezzo) o).targa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targa);
    }
}
