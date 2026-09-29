package it.epicode.noleggio.exception;

/** Eccezione checked: chi chiama noleggia() e' obbligato a gestire il caso. */
public class MezzoNonDisponibileException extends Exception {

    private static final long serialVersionUID = 1L;

    public MezzoNonDisponibileException(String messaggio) {
        super(messaggio);
    }
}
