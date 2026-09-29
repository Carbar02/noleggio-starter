package it.epicode.noleggio.model;

/** Capacita' trasversale: non tutti i mezzi sono elettrici, quindi e' un'interfaccia e non una classe. */
public interface Ricaricabile {

    int getAutonomiaKm();

    /** Metodo default: implementato una volta sola, valido per tutte le classi che implementano l'interfaccia. */
    default boolean autonomiaSufficiente(int kmPrevisti) {
        return kmPrevisti <= getAutonomiaKm();
    }
}
