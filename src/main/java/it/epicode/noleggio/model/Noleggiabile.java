package it.epicode.noleggio.model;

/** Contratto di tutto cio' che si puo' noleggiare: stato di disponibilita' e costo. */
public interface Noleggiabile {

    boolean isDisponibile();

    void noleggia();

    void restituisci();

    double calcolaCosto(int giorni);
}
