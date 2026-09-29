package it.epicode.noleggio.model;

public class Auto extends Mezzo {

    private final int posti;

    public Auto(String targa, String modello, double tariffaGiornaliera, int posti) {
        super(targa, modello, tariffaGiornaliera);
        if (posti < 2 || posti > 9) {
            throw new IllegalArgumentException("Numero di posti non valido: " + posti);
        }
        this.posti = posti;
    }

    @Override
    public double calcolaCosto(int giorni) {
        return costoBase(giorni);
    }

    @Override
    public String getTipo() {
        return "Auto";
    }

    public int getPosti() {
        return posti;
    }
}
