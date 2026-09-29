package it.epicode.noleggio.model;

/** Oggetto immutabile: il costo si calcola una volta, al momento del noleggio. */
public class Noleggio {

    private final Mezzo mezzo;
    private final Cliente cliente;
    private final int giorni;
    private final double costo;

    public Noleggio(Mezzo mezzo, Cliente cliente, int giorni) {
        this.mezzo = mezzo;
        this.cliente = cliente;
        this.giorni = giorni;
        this.costo = mezzo.calcolaCosto(giorni);   // chiamata polimorfica
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getGiorni() {
        return giorni;
    }

    public double getCosto() {
        return costo;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s %s per %d giorni: %.2f euro",
                cliente.getNome(), mezzo.getTipo(), mezzo.getModello(), giorni, costo);
    }
}
