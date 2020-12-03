package it.unicam.cs.asdl2021.es2sol;

/**
 * Un oggetto cassaforte con combinazione ha una manopola che può essere
 * impostata su certe posizioni contrassegnate da lettere maiuscole. La
 * serratura si apre solo se le ultime tre lettere impostate sono uguali alla
 * combinazione segreta.
 * 
 * @author Luca Tesei
 */
public class CombinationLock {

    private String combinazione;

    private boolean chiuso;

    private char ultimaPosizioneImpostata;

    private char penultimaPosizioneImpostata;

    private char terzultimaPosizioneImpostata;

    /**
     * Costruisce una cassaforte <b>aperta</b> con una data combinazione.
     * 
     * @param aCombination
     *                         la combinazione che deve essere una stringa di 3
     *                         lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public CombinationLock(String aCombination) {
        if (aCombination == null)
            throw new NullPointerException(
                    "Tentativo di creare una cassaforte con combinazione nulla");
        if (aCombination.length() != 3)
            throw new IllegalArgumentException("Combinazione non di 3 lettere");
        // E' lunga 3
        if (!Character.isLetter(aCombination.charAt(0))
                || !Character.isUpperCase(aCombination.charAt(0)))
            throw new IllegalArgumentException(
                    "Primo simbolo della combinazione non valido");
        if (!Character.isLetter(aCombination.charAt(1))
                || !Character.isUpperCase(aCombination.charAt(1)))
            throw new IllegalArgumentException(
                    "Secondo simbolo della combinazione non valido");
        if (!Character.isLetter(aCombination.charAt(2))
                || !Character.isUpperCase(aCombination.charAt(2)))
            throw new IllegalArgumentException(
                    "Terzo simbolo della combinazione non valido");
        combinazione = aCombination;
        chiuso = false;
    }

    /**
     * Imposta la manopola su una certaposizione.
     * 
     * @param aPosition
     *                      un carattere lettera maiuscola su cui viene
     *                      impostata la manopola
     * @throws IllegalArgumentException
     *                                      se il carattere fornito non è una
     *                                      lettera maiuscola dell'alfabeto
     *                                      inglese
     */
    public void setPosition(char aPosition) {
        if (!(Character.isLetter(aPosition)
                && Character.isUpperCase(aPosition)))
            throw new IllegalArgumentException(
                    "Posizinamento di carattere non consentito: " + aPosition);
        // Faccio scorrere le altre posizioni
        terzultimaPosizioneImpostata = penultimaPosizioneImpostata;
        penultimaPosizioneImpostata = ultimaPosizioneImpostata;
        ultimaPosizioneImpostata = aPosition;
    }

    /**
     * Tenta di aprire la serratura considerando come combinazione fornita le
     * ultime tre posizioni impostate.
     */
    public void open() {
        if (combinazione.charAt(0) == terzultimaPosizioneImpostata
                && combinazione.charAt(1) == penultimaPosizioneImpostata
                && combinazione.charAt(2) == ultimaPosizioneImpostata)
            chiuso = false;
        else // resetto la combinazione impostata fino ad ora
            ultimaPosizioneImpostata = 0;
    }

    /**
     * Determina se la cassaforte è aperta.
     * 
     * @return true se la cassaforte è attualmente aperta, false altrimenti
     */
    public boolean isOpen() {
        return !chiuso;
    }

    /**
     * Chiude la cassaforte senza modificare la combinazione attuale. Fa in modo
     * che se si prova a riaprire subito senza impostare nessuna nuova posizione
     * della manopola la cassaforte non si apre. Si noti che se la cassaforte
     * era stata aperta con la combinazione giusta le ultime posizioni impostate
     * sono proprio la combinazione attuale.
     */
    public void lock() {
        chiuso = true;
        // Faccio in modo che, una volta chiusa,
        // non si possa riaprire immediatamente
        ultimaPosizioneImpostata = 0;
    }

    /**
     * Chiude la cassaforte e modifica la combinazione. Funziona solo se la
     * cassaforte è attualmente aperta. Se la cassaforte è attualmente chiusa
     * rimane chiusa e la combinazione non viene cambiata.
     * 
     * @param aCombination
     *                         la nuova combinazione che deve essere una stringa
     *                         di 3 lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public void lockAndChangeCombination(String aCombination) {
        // se la cassaforte è attualmente aperta faccio l'operazione richiesta
        if (!chiuso) {
            if (aCombination == null)
                throw new NullPointerException(
                        "Tentativo di creare una cassaforte con combinazione nulla");
            if (aCombination.length() != 3)
                throw new IllegalArgumentException(
                        "Combinazione non di 3 lettere");
            // E' lunga 3
            if (!Character.isLetter(aCombination.charAt(0))
                    || !Character.isUpperCase(aCombination.charAt(0)))
                throw new IllegalArgumentException(
                        "Primo simbolo della combinazione non valido");
            if (!Character.isLetter(aCombination.charAt(1))
                    || !Character.isUpperCase(aCombination.charAt(1)))
                throw new IllegalArgumentException(
                        "Secondo simbolo della combinazione non valido");
            if (!Character.isLetter(aCombination.charAt(2))
                    || !Character.isUpperCase(aCombination.charAt(2)))
                throw new IllegalArgumentException(
                        "Terzo simbolo della combinazione non valido");
            combinazione = aCombination;
            // Chiudo e faccio in modo che non sia
            // Immediatamente riapribile
            chiuso = true;
            ultimaPosizioneImpostata = 0;
        }
        // altrimenti non faccio assolutamente niente
    }
}