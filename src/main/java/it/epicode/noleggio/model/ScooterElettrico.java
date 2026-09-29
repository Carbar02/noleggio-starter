package it.epicode.noleggio.model;

/** Eredita la regola di sconto dello Scooter e aggiunge la capacita' Ricaricabile. */
public class ScooterElettrico extends Scooter implements Ricaricabile {

    private final int autonomiaKm;

    public ScooterElettrico(String targa, String modello, double tariffaGiornaliera, int autonomiaKm) {
        super(targa, modello, tariffaGiornaliera);
        if (autonomiaKm <= 0) {
            throw new IllegalArgumentException("Autonomia non valida: " + autonomiaKm);
        }
        this.autonomiaKm = autonomiaKm;
    }

    @Override
    public String getTipo() {
        return "Scooter elettrico";
    }

    @Override
    public int getAutonomiaKm() {
        return autonomiaKm;
    }
}
