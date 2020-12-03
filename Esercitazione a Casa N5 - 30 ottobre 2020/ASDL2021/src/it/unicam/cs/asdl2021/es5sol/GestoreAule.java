package it.unicam.cs.asdl2021.es5sol;

import java.util.HashSet;
import java.util.Set;

/**
 * Un gestore di aule gestisce un insieme di aule e permette di cercare aule
 * libere con certe caratteristiche fra quelle che gestisce.
 * 
 * @author Luca Tesei
 *
 */
public class GestoreAule {

    private final Set<Aula> aule;

    /**
     * Crea un gestore vuoto.
     */
    public GestoreAule() {
        this.aule = new HashSet<Aula>();
    }

    /**
     * Aggiunge un'aula al gestore.
     * 
     * @param a
     *              una nuova aula
     * @return true se l'aula è stata aggiunta, false se era già presente.
     * @throws NullPointerException
     *                                  se l'aula passata è nulla
     */
    public boolean addAula(Aula a) {
        if (a == null)
            throw new NullPointerException(
                    "Tentativo di aggiungere al gestore un'aula nulla");
        return this.aule.add(a);
    }

    /**
     * @return the aule
     */
    public Set<Aula> getAule() {
        return aule;
    }

    /**
     * Cerca tutte le aule che soddisfano un certo insieme di facilities e che
     * siano libere in un time slot specificato.
     * 
     * @param requestedFacilities
     *                                insieme di facilities richieste che
     *                                un'aula deve soddisfare
     * @param ts
     *                                il time slot in cui un'aula deve essere
     *                                libera
     * 
     * @return l'insieme di tutte le aule gestite da questo gestore che
     *         soddisfano tutte le facilities richieste e sono libere nel time
     *         slot indicato. Se non ci sono aule che soddisfano i requisiti
     *         viene restituito un insieme vuoto.
     * @throws NullPointerException
     *                                  se una qualsiasi delle informazioni
     *                                  passate è nulla
     */
    public Set<Aula> cercaAuleLibere(Set<Facility> requestedFacilities,
            TimeSlot ts) {
        if (requestedFacilities == null || ts == null)
            throw new NullPointerException(
                    "Tentativo di cercare aule libere con facility richieste o time slot nulli");
        // Creo l'insieme risultato
        Set<Aula> risultato = new HashSet<Aula>();
        // Scorro tutte le aule
        for (Aula a: this.aule) {
            // Controllo se l'aula soddisfa i requisiti
            if (a.satisfiesFacilities(requestedFacilities) && a.isFree(ts))
                // Nel caso la aggiungo all'insieme risultato
                risultato.add(a);
        }
        return risultato;
    }

}
