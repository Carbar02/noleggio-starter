package it.epicode.noleggio.model;

public class Scooter extends Mezzo {

    public static final int GIORNI_SOGLIA_SCONTO = 7;
    public static final double SCONTO_LUNGO_PERIODO = 0.20;
    private boolean noleggioAnnullato;

    public Scooter(String targa, String modello, double tariffaGiornaliera) {
        super(targa, modello, tariffaGiornaliera);
    }

    /** Oltre 7 giorni (dall'ottavo in poi) sconto del 20% sull'intero noleggio. */
    @Override
    public double calcolaCosto(int giorni) {
        if (noleggioAnnullato) {
            return 0;
        }
        double costo = costoBase(giorni);
        if (giorni > GIORNI_SOGLIA_SCONTO) {
            costo = costo * (1 - SCONTO_LUNGO_PERIODO);
        }
        return costo;
    }

    @Override
    public void noleggia() {
        super.noleggia();
        noleggioAnnullato = false;
    }

    public void annullaNoleggio() {
        noleggioAnnullato = true;
        restituisci();
    }

    @Override
    public String getTipo() {
        return "Scooter";
    }
}
