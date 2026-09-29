package it.epicode.noleggio.model;

/** Eredita da Auto e in piu' implementa Ricaricabile: ereditarieta' singola, interfacce multiple. */
public class AutoElettrica extends Auto implements Ricaricabile {

    public static final double SCONTO_GREEN = 0.10;

    private final int autonomiaKm;

    public AutoElettrica(String targa, String modello, double tariffaGiornaliera, int posti, int autonomiaKm) {
        super(targa, modello, tariffaGiornaliera, posti);
        if (autonomiaKm <= 0) {
            throw new IllegalArgumentException("Autonomia non valida: " + autonomiaKm);
        }
        this.autonomiaKm = autonomiaKm;
    }

    /** Riusa il calcolo della superclasse e applica lo sconto: override + super. */
    @Override
    public double calcolaCosto(int giorni) {
        return super.calcolaCosto(giorni) * (1 - SCONTO_GREEN);
    }

    @Override
    public String getTipo() {
        return "Auto elettrica";
    }

    @Override
    public int getAutonomiaKm() {
        return autonomiaKm;
    }
}
