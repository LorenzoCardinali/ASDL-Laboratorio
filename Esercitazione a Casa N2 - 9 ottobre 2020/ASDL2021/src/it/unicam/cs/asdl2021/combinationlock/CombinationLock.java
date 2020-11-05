package it.unicam.cs.asdl2021.combinationlock;

/**
 * Un oggetto cassaforte con combinazione ha una manopola che può essere
 * impostata su certe posizioni contrassegnate da lettere maiuscole. La
 * serratura si apre solo se le ultime tre lettere impostate sono uguali alla
 * combinazione segreta.
 *
 * @author Luca Tesei
 */
public class CombinationLock {

    private char pos0;

    private char pos1;

    private char pos2;

    private String combinazione;

    private boolean aperta;

    /**
     * Costruisce una cassaforte <b>aperta</b> con una data combinazione
     *
     * @param aCombination la combinazione che deve essere una stringa di 3
     *                     lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     * stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public CombinationLock(String aCombination) {

        if (aCombination == null) {
            throw new NullPointerException("Coombinazione vuota");
        }
        if (aCombination.length() < 3) {
            throw new IllegalArgumentException("Combinazione troppo corta");
        }
        if (aCombination.length() > 3) {
            throw new IllegalArgumentException("Combinazione troppo lunga");
        }
        if ((!Character.isLetter(aCombination.charAt(0))) || (!Character.isUpperCase(aCombination.charAt(0)))) {
            throw new IllegalArgumentException("Lettera posizione 0 non valida");
        }
        if ((!Character.isLetter(aCombination.charAt(1))) || (!Character.isUpperCase(aCombination.charAt(1)))) {
            throw new IllegalArgumentException("Lettera posizione 1 non valida");
        }
        if ((!Character.isLetter(aCombination.charAt(2))) || (!Character.isUpperCase(aCombination.charAt(2)))) {
            throw new IllegalArgumentException("Lettera posizione 2 non valida");
        }

        combinazione = aCombination;

        aperta = true;

        pos0 = combinazione.charAt(0);
        pos1 = combinazione.charAt(1);
        pos2 = combinazione.charAt(2);
    }

    /**
     * Imposta la manopola su una certa posizione.
     *
     * @param aPosition un carattere lettera maiuscola su cui viene
     *                  impostata la manopola
     * @throws IllegalArgumentException se il carattere fornito non è una
     *                                  lettera maiuscola dell'alfabeto
     *                                  inglese
     */
    public void setPosition(char aPosition) {

        if (!Character.isLetter(aPosition)) {
            throw new IllegalArgumentException("Posizione vuota");
        }
        if (!Character.isUpperCase(aPosition)) {
            throw new IllegalArgumentException("Posizione non valida");
        }

        pos0 = pos1;
        pos1 = pos2;
        pos2 = aPosition;
    }

    /**
     * Tenta di aprire la serratura considerando come combinazione fornita le
     * ultime tre posizioni impostate.
     */
    public void open() {
        if ((combinazione.charAt(0) == pos0) && (combinazione.charAt(1) == pos1) && (combinazione.charAt(2) == pos2)) {
            aperta = true;
        }
    }

    /**
     * Determina se la cassaforte è aperta.
     *
     * @return true se la cassaforte è attualmente aperta, false altrimenti
     */
    public boolean isOpen() {
        return aperta = true;
    }

    /**
     * Chiude la cassaforte senza modificare la combinazione attuale. Fa in modo
     * che se si prova a riaprire subito senza impostare nessuna nuova posizione
     * della manopola la cassaforte non si apre. Si noti che se la cassaforte
     * era stata aperta con la combinazione giusta le ultime posizioni impostate
     * sono proprio la combinazione attuale.
     */
    public void lock() {
        aperta = false;
        pos2 = '1';
    }

    /**
     * Chiude la cassaforte e modifica la combinazione. Funziona solo se la
     * cassaforte è attualmente aperta. Se la cassaforte è attualmente chiusa
     * rimane chiusa e la combinazione non viene cambiata.
     *
     * @param aCombination la nuova combinazione che deve essere una stringa
     *                     di 3 lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     * stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public void lockAndChangeCombination(String aCombination) {
        if (aperta) {

            if (aCombination == null) {
                throw new NullPointerException("Coombinazione vuota");
            }
            if (aCombination.length() < 3) {
                throw new IllegalArgumentException("Combinazione troppo corta");
            }
            if (aCombination.length() > 3) {
                throw new IllegalArgumentException("Combinazione troppo lunga");
            }
            if ((!Character.isLetter(aCombination.charAt(0))) || (!Character.isUpperCase(aCombination.charAt(0)))) {
                throw new IllegalArgumentException("Lettera posizione 0 non valida");
            }
            if ((!Character.isLetter(aCombination.charAt(1))) || (!Character.isUpperCase(aCombination.charAt(1)))) {
                throw new IllegalArgumentException("Lettera posizione 1 non valida");
            }
            if ((!Character.isLetter(aCombination.charAt(2))) || (!Character.isUpperCase(aCombination.charAt(2)))) {
                throw new IllegalArgumentException("Lettera posizione 2 non valida");
            }

            combinazione = aCombination;

            aperta = false;
            pos2 = '1';
        }

    }
}