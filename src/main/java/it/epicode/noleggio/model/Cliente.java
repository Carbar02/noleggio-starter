package it.epicode.noleggio.model;

/** Incapsulamento: stato privato, validazione nel costruttore e nel setter. */
public class Cliente {

    private final String codiceFiscale;
    private final String nome;
    private String email;

    public Cliente(String codiceFiscale, String nome, String email) {
        if (codiceFiscale == null || codiceFiscale.length() != 16) {
            throw new IllegalArgumentException("Codice fiscale non valido: " + codiceFiscale);
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome e' obbligatorio");
        }
        this.codiceFiscale = codiceFiscale.toUpperCase();
        this.nome = nome;
        this.email = validaEmail(email);
    }

    public void setEmail(String email) {
        this.email = validaEmail(email);
    }

    private static String validaEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email non valida: " + email);
        }
        return email;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nome + " <" + email + ">";
    }
}
