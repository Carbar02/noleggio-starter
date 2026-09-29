package it.epicode.noleggio.model;

public class Furgone extends Mezzo {

    public static final double COSTO_PULIZIA = 25.0;

    private final int portataKg;

    public Furgone(String targa, String modello, double tariffaGiornaliera, int portataKg) {
        super(targa, modello, tariffaGiornaliera);
        if (portataKg <= 0) {
            throw new IllegalArgumentException("Portata non valida: " + portataKg);
        }
        this.portataKg = portataKg;
    }

    /** Tariffa per giorni piu' un costo fisso di pulizia, indipendente dalla durata. */
    @Override
    public double calcolaCosto(int giorni) {
        return costoBase(giorni) + COSTO_PULIZIA;
    }

    @Override
    public String getTipo() {
        return "Furgone";
    }

    public int getPortataKg() {
        return portataKg;
    }
}
