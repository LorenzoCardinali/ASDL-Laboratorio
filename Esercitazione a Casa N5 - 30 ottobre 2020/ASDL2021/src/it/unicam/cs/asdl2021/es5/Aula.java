package it.unicam.cs.asdl2021.es5;

import java.sql.SQLOutput;
import java.sql.Time;
import java.util.*;

/**
 * Un oggetto della classe aula rappresenta una certa aula con le sue facilities
 * e le sue prenotazioni.
 *
 * @author Template: Luca Tesei, Implementazione: Collettiva
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
     * @param nome     il nome dell'aula
     * @param location la location dell'aula
     * @throws NullPointerException se una qualsiasi delle informazioni richieste è nulla
     */
    public Aula(String nome, String location) {
        if (nome == null) {
            throw new NullPointerException("Valore 'nome' null");
        }
        if (location == null) {
            throw new NullPointerException("Valore 'location' null");
        }

        this.nome = nome;
        this.location = location;

        this.prenotazioni = new TreeSet<>();
        this.facilities = new HashSet<>();
    }

    /**
     * Costruisce una certa aula con nome, location e insieme delle facilities.
     * L'aula non ha inizialmente nessuna prenotazione.
     *
     * @param nome       il nome dell'aula
     * @param location   la location dell'aula
     * @param facilities l'insieme delle facilities dell'aula
     * @throws NullPointerException se una qualsiasi delle informazioni richieste è nulla
     */
    public Aula(String nome, String location, Set<Facility> facilities) {
        if (nome == null) {
            throw new NullPointerException("Valore 'nome' null");
        }
        if (location == null) {
            throw new NullPointerException("Valore 'location' null");
        }
        if (facilities == null) {
            throw new NullPointerException("Valore 'facilities' null");
        }

        this.location = location;
        this.nome = nome;

        this.prenotazioni = new TreeSet<>();
        this.facilities = new HashSet<>(facilities);
    }

    /*
     * Ridefinire in accordo con equals
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    /*
     * Due aule sono uguali se e solo se hanno lo stesso nome
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return nome.equals(aula.nome);
    }

    /* L'ordinamento naturale si basa sul nome dell'aula */
    @Override
    public int compareTo(Aula o) {
        return this.getNome().compareTo(o.getNome());
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
     * @param f la facility da aggiungere
     * @return true se la facility non era già presente e quindi è stata
     * aggiunta, false altrimenti
     * @throws NullPointerException se la facility passata è nulla
     */
    public boolean addFacility(Facility f) {
        if (f == null) {
            throw new NullPointerException("facility nulla");
        }
        return facilities.add(f);
    }

    /**
     * Determina se l'aula è libera in un certo time slot.
     *
     * @param ts il time slot da controllare
     * @return true se l'aula risulta libera per tutto il periodo del time slot specificato
     * @throws NullPointerException se il time slot passato è nullo
     */
    public boolean isFree(TimeSlot ts) {
        if (ts == null) {
            throw new NullPointerException("timeslot nullo");
        }

        for (Prenotazione temp : prenotazioni) {
            //Dovrebbe ottimizzare ma il tempo di esecuzione è maggiore
            if (temp.getTimeSlot().getStart().after(ts.getStop())) {
                return true;
            }
            if (ts.overlapsWith(temp.getTimeSlot())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determina se questa aula soddisfa tutte le facilities richieste
     * rappresentate da un certo insieme dato.
     *
     * @param requestedFacilities l'insieme di facilities richieste da soddisfare
     * @return true se e solo se tutte le facilities di
     * {@code requestedFacilities} sono soddisfatte da questa aula.
     * @throws NullPointerException se il set di facility richieste è nullo
     */
    public boolean satisfiesFacilities(Set<Facility> requestedFacilities) {
        if (requestedFacilities == null) {
            throw new NullPointerException("set di facility richieste è nullo");
        }

        boolean test;

        if (facilities.isEmpty()) {
            return true;
        }

        for (Facility temp : requestedFacilities) {
            test = false;
            if (facilities.contains(temp)) {
                test = true;
            }
            if (!test) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prenota l'aula controllando eventuali sovrapposizioni.
     *
     * @param ts
     * @param docente
     * @param motivo
     * @throws IllegalArgumentException se la prenotazione comporta una sovrapposizione con un'altra
     *                                  prenotazione nella stessa aula.
     * @throws NullPointerException     se una qualsiasi delle informazioni richieste è nulla.
     */
    public void addPrenotazione(TimeSlot ts, String docente, String motivo) {

        if (ts == null) {
            throw new NullPointerException("timeslot nullo");
        }
        if (docente == null) {
            throw new NullPointerException("docente nullo");
        }
        if (motivo == null) {
            throw new NullPointerException("motivo nullo");
        }

        if (!(isFree(ts))) {
            throw new IllegalArgumentException("prenotazione sovrapposta");
        } else {
            Prenotazione p = new Prenotazione(this, ts, docente, motivo);
            prenotazioni.add(p);
        }
    }

    /**
     * Cancella una prenotazione di questa aula.
     *
     * @param p la prenotazione da cancellare
     * @return true se la prenotazione è stata cancellata, false se non era presente.
     * @throws NullPointerException se la prenotazione passata è nulla.
     */
    public boolean removePrenotazione(Prenotazione p) {
        return prenotazioni.remove(p);
    }

    /**
     * Rimuove tutte le prenotazioni di questa aula che iniziano prima (o esattamente in)
     * di un punto nel tempo specificato.
     *
     * @param timePoint un certo punto nel tempo
     * @return true se almeno una prenotazione è stata cancellata, false altrimenti.
     * @throws NullPointerException se il punto nel tempo passato è nullo.
     */
    public boolean removePrenotazioniBefore(GregorianCalendar timePoint) {
        if (timePoint == null) {
            throw new NullPointerException("timepoint nullo");
        }

        boolean flag = false;
        TreeSet<Prenotazione> eliminare = new TreeSet<>();

        for (Prenotazione temp : prenotazioni) {
            if (temp.getTimeSlot().getStart().after(timePoint)) {
                break;
            }
            if (temp.getTimeSlot().getStart().before(timePoint) || temp.getTimeSlot().getStart().equals(timePoint)) {
                eliminare.add(temp);
                flag = true;
            }
        }

        if (flag) {
            for (Prenotazione temp : eliminare) {
                prenotazioni.remove(temp);
            }
        }
        eliminare.removeAll(prenotazioni);

        return flag;
    }
}