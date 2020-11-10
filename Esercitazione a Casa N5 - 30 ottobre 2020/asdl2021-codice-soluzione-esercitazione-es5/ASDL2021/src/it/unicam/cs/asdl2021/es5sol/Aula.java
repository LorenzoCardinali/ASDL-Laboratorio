package it.unicam.cs.asdl2021.es5sol;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Un oggetto della classe aula rappresenta una certa aula con le sue facilities
 * e le sue prenotazioni.
 * 
 * @author Template: Luca Tesei, Implementazione: Collettiva
 *
 */
public class Aula implements Comparable<Aula> {
    // Identificativo unico di un'aula
    private final String nome;

    // Location dell'aula
    private final String location;

    // Insieme delle facilities di quest'aula
    private final Set<Facility> facilities;

    // Insieme delle prenotazioni per quest'aula, segue l'ordinamento naturale
    // delle prenotazioni
    private final SortedSet<Prenotazione> prenotazioni;

    /**
     * Costruisce una certa aula con nome e location. Il set delle facilities è
     * vuoto. L'aula non ha inizialmente nessuna prenotazione.
     * 
     * @param nome
     *                     il nome dell'aula
     * @param location
     *                     la location dell'aula
     * 
     * @throws NullPointerException
     *                                  se una qualsiasi delle informazioni
     *                                  richieste è nulla
     */
    public Aula(String nome, String location) {
        if (nome == null)
            throw new NullPointerException(
                    "Tentativo di costruire un'aula senza nome");
        if (location == null)
            throw new NullPointerException(
                    "Tentativo di costruire un'aula senza location");
        this.location = location;
        this.nome = nome;
        this.prenotazioni = new TreeSet<Prenotazione>();
        this.facilities = new HashSet<Facility>();
    }

    /**
     * Costruisce una certa aula con nome, location e insieme delle facilities.
     * L'aula non ha inizialmente nessuna prenotazione.
     * 
     * @param nome
     *                       il nome dell'aula
     * @param location
     *                       la location dell'aula
     * @param facilities
     *                       l'insieme delle facilities dell'aula
     * @throws NullPointerException
     *                                  se una qualsiasi delle informazioni
     *                                  richieste è nulla
     */
    public Aula(String nome, String location, Set<Facility> facilities) {
        if (nome == null)
            throw new NullPointerException(
                    "Tentativo di costruire un'aula senza nome");
        if (location == null)
            throw new NullPointerException(
                    "Tentativo di costruire un'aula senza location");
        if (facilities == null)
            throw new NullPointerException(
                    "Tentativo di costruire un'aula senza facilities");
        this.nome = nome;
        this.location = location;
        this.facilities = facilities;
        this.prenotazioni = new TreeSet<Prenotazione>();
    }

    /*
     * Ridefinire in accordo con equals
     */
    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }

    /* Due aule sono uguali se e solo se hanno lo stesso nome */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Aula) {
            Aula other = (Aula) obj;
            return this.nome.equals(other.nome);
        }
        return false;
    }

    /* L'ordinamento naturale si basa sul nome dell'aula */
    @Override
    public int compareTo(Aula o) {
        return this.nome.compareTo(o.nome);
    }

    /**
     * @return the facilities
     */
    public Set<Facility> getFacilities() {
        return facilities;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the prenotazioni
     */
    public SortedSet<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * Aggiunge una faciltity a questa aula.
     * 
     * @param f
     *              la facility da aggiungere
     * @return true se la facility non era già presente e quindi è stata
     *         aggiunta, false altrimenti
     * @throws NullPointerException
     *                                  se la facility passata è nulla
     */
    public boolean addFacility(Facility f) {
        if (f == null)
            throw new NullPointerException(
                    "Tentativo di aggiungere una facility nulla");
        return this.facilities.add(f);
    }

    /**
     * Determina se l'aula è libera in un certo time slot.
     * 
     * 
     * @param ts
     *               il time slot da controllare
     * 
     * @return true se l'aula risulta libera per tutto il periodo del time slot
     *         specificato
     * @throws NullPointerException
     *                                  se il time slot passato è nullo
     */
    public boolean isFree(TimeSlot ts) {
        // NOTA: sfruttare l'ordinamento tra le prenotazioni per
        // rispondere in maniera efficiente
        if (ts == null)
            throw new NullPointerException(
                    "Tentativo di testare se un'aula è libera in un time slot nullo");
        // Scorro l'insieme ordinato delle prenotazioni fino a quando non ne
        // trovo una che ha un time slot maggiore stretto di quello passato
        for (Prenotazione p : this.prenotazioni)
            if (p.getTimeSlot().overlapsWith(ts))
                // l'aula già contiene una prenotazione nel time slot
                // specificato o in una sua parte per un periodo sopra la soglia
                // di tolleranza
                return false;
            else if (p.getTimeSlot().compareTo(ts) > 0)
                // poiché le prenotazioni sono in ordine crescente di time slot
                // se arrivo a una prenotazione che segue il time slot
                // specificato posso concludere che l'aula è libera nel time
                // slot desiderato e posso interrompere il ciclo
                return true;
        // ho terminato le prenotazioni e nessuna si è sovrapposta
        return true;
    }

    /**
     * Determina se questa aula soddisfa tutte le facilities richieste
     * rappresentate da un certo insieme dato.
     * 
     * @param requestedFacilities
     *                                l'insieme di facilities richieste da
     *                                soddisfare
     * @return true se e solo se tutte le facilities di
     *         {@code requestedFacilities} sono soddisfatte da questa aula.
     * @throws NullPointerException
     *                                  se il set di facility richieste è nullo
     */
    public boolean satisfiesFacilities(Set<Facility> requestedFacilities) {
        if (requestedFacilities == null)
            throw new NullPointerException(
                    "Richiesta di controllare se un insieme di facility nullo è soddisfatto");
        // scorro le facilities richieste
        boolean satisfied;
        for (Facility rf : requestedFacilities) {
            satisfied = false;
            // scorro le facilities di questa aula e controllo se ce n'è una che
            // soddisfa la facility richiesta corrente
            for (Facility f : this.facilities)
                if (f.satisfies(rf)) {
                    // la facility richiesta corrente è stata soddisfata
                    satisfied = true;
                    // esco dal for interno
                    break;
                }
            if (!satisfied)
                // la facility richiesta non è soddisfatta, quindi rispondo
                // false
                return false;
        }
        // tutte le facility richieste sono state soddisfatte, quindi rispondo
        // true
        return true;
    }

    /**
     * Prenota l'aula controllando eventuali sovrapposizioni.
     * 
     * @param ts
     * @param docente
     * @param motivo
     * @throws IllegalArgumentException
     *                                      se la prenotazione comporta una
     *                                      sovrapposizione con un'altra
     *                                      prenotazione nella stessa aula.
     * @throws NullPointerException
     *                                      se una qualsiasi delle informazioni
     *                                      richieste è nulla.
     */
    public void addPrenotazione(TimeSlot ts, String docente, String motivo) {
        if (ts == null || docente == null || motivo == null)
            throw new NullPointerException(
                    "Tentativo di prenotare un aula con time slot, docente o motivo nullo");
        // Controllo sovrapposizione
        if (!this.isFree(ts))
            throw new IllegalArgumentException(
                    "Tentativo di prenotare un aula in un time slot già occupato");
        // Creo la prenotazione e la aggiungo al set
        this.prenotazioni.add(new Prenotazione(this, ts, docente, motivo));
    }

    /**
     * Cancella una prenotazione di questa aula.
     * 
     * @param p
     *              la prenotazione da cancellare
     * @return true se la prenotazione è stata cancellata, false se non era
     *         presente.
     * @throws NullPointerException
     *                                  se la prenotazione passata è null
     */
    public boolean removePrenotazione(Prenotazione p) {
        if (p == null)
            throw new NullPointerException(
                    "Tentativo di cancellare una prenotazione nulla");
        return this.prenotazioni.remove(p);
    }

    /**
     * Rimuove tutte le prenotazioni di questa aula che iniziano prima (o
     * esattamente in) di un punto nel tempo specificato.
     * 
     * @param timePoint
     *                      un certo punto nel tempo
     * @return true se almeno una prenotazione è stata cancellata, false
     *         altrimenti.
     * @throws NullPointerException
     *                                  se il punto nel tempo passato è nullo.
     */
    public boolean removePrenotazioniBefore(GregorianCalendar timePoint) {
        // NOTA: sfruttare l'ordinamento tra le prenotazioni per
        // eseguire in maniera efficiente
        if (timePoint == null)
            throw new NullPointerException(
                    "Tentativo di rimuovere le prenotazioni che precedono un punto nel tempo nullo");
        // uso un flag per determinare se sono state fatte cancellazioni
        boolean ret = false;
        // scorro le prenotazioni nell'ordine canonico che è in base al tempo di
        // inizio
        // uso un iteratore perché devo cancellare alcune prenotazioni durante
        // lo scorrimento
        Iterator<Prenotazione> i = this.prenotazioni.iterator();
        while (i.hasNext()) {
            Prenotazione p = i.next();
            int cmp = timePoint.compareTo(p.getTimeSlot().getStart());
            if (cmp >= 0) {
                // la prenotazione è da cancellare
                i.remove();
                ret = true;
            } else // ho raggiunto una prenotazione con tempo di inizio maggiore
                   // del tempo indicato e quindi posso smettere la procedura.
                return ret;
        }
        return false;
    }
}
