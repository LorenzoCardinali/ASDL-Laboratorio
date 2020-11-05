package it.unicam.cs.asdl2021.es4;

import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import java.util.Objects;

/**
 * Un oggetto della classe aula rappresenta una certa aula con le sue facilities
 * e le sue prenotazioni.
 *
 * @author Template: Luca Tesei, Implementation: Collective
 */
public class Aula implements Comparable<Aula> {

    // numero iniziale delle posizioni dell'array facilities. Se viene richiesto
    // di inserire una facility e l'array è pieno questo viene raddoppiato. La
    // costante è protected solo per consentirne l'accesso ai test JUnit
    protected static final int INIT_NUM_FACILITIES = 5;

    // numero iniziale delle posizioni dell'array prenotazioni. Se viene
    // richiesto di inserire una prenotazione e l'array è pieno questo viene
    // raddoppiato. La costante è protected solo per consentirne l'accesso ai
    // test JUnit.
    protected static final int INIT_NUM_PRENOTAZIONI = 100;

    // Identificativo unico di un'aula
    private final String nome;

    // Location dell'aula
    private final String location;

    // Insieme delle facilities di quest'aula. L'array viene creato all'inizio
    // della dimensione specificata nella costante INIT_NUM_FACILITIES. Il
    // metodo addFacility(Facility) raddoppia l'array qualora non ci sia più
    // spazio per inserire la facility.
    private Facility[] facilities;

    // numero corrente di facilities inserite
    private int numFacilities;

    // Insieme delle prenotazioni per quest'aula. L'array viene creato
    // all'inizio
    // della dimensione specificata nella costante INIT_NUM_PRENOTAZIONI. Il
    // metodo addPrenotazione(TimeSlot, String, String) raddoppia l'array
    // qualora non ci sia più
    // spazio per inserire la prenotazione.
    private Prenotazione[] prenotazioni;

    // numero corrente di prenotazioni inserite
    private int numPrenotazioni;

    /**
     * Costruisce una certa aula con nome e location. Il set delle facilities è
     * vuoto. L'aula non ha inizialmente nessuna prenotazione.
     *
     * @param nome     il nome dell'aula
     * @param location la location dell'aula
     * @throws NullPointerException se una qualsiasi delle informazioni richieste è nulla
     */
    // Nota: i due assegnamenti che seguono servono solo
    // per non creare un errore in questo template del codice.
    // Vanno modificati.
    public Aula(String nome, String location) {
        if (nome == null) {
            throw new NullPointerException("Valore 'nome' null");
        }
        if (location == null) {
            throw new NullPointerException("Valore 'location' null");
        }

        this.nome = nome;
        this.location = location;

        prenotazioni = new Prenotazione[INIT_NUM_PRENOTAZIONI];
        facilities = new Facility[INIT_NUM_FACILITIES];
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
    public Facility[] getFacilities() {
        return facilities;
    }

    /**
     * @return il numero corrente di facilities
     */
    public int getNumeroFacilities() {
        return numFacilities;
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
    public Prenotazione[] getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * @return il numero corrente di prenotazioni
     */
    public int getNumeroPrenotazioni() {
        return numPrenotazioni;
    }

    /**
     * Aggiunge una faciltity a questa aula. Controlla se la facility è già
     * presente, nel qual caso non la inserisce.
     *
     * @param f la facility da aggiungere
     * @return true se la facility non era già presente e quindi è stata
     * aggiunta, false altrimenti
     * @throws NullPointerException se la facility passata è nulla
     */
    // Nota: attenzione! Per controllare se una facility è già presente
    // bisogna usare il metodo equals della classe Facility.
    // Nota: attenzione bis! Si noti che per le sottoclassi di Facility non
    // è richiesto di ridefinire ulteriormente il metodo equals...
    public boolean addFacility(Facility f) {
        if (f == null) {
            throw new NullPointerException("facility nulla");
        }

        for (int i = 0; i < getNumeroFacilities(); i++) {
            if (f.equals(this.facilities[i])) {
                return false;
            }
        }

        try {
            this.facilities[numFacilities] = f;
            numFacilities++;
            return true;
        } catch (Exception ArrayIndexOutOfBoundsException) {
            Facility[] copyfacilities = new Facility[numFacilities];

            for (int i = 0; i < numFacilities; i++) {
                copyfacilities[i] = facilities[i];
            }

            facilities = new Facility[numFacilities * 2];

            for (int i = 0; i < copyfacilities.length; i++) {
                facilities[i] = copyfacilities[i];
            }

            facilities[numFacilities] = f;
            numFacilities++;
            return true;
        }
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

        for (int i = 0; i < getNumeroPrenotazioni(); i++) {
            if ((ts.overlapsWith(prenotazioni[i].getTimeSlot()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determina se questa aula soddisfa tutte le facilities richieste
     * rappresentate da un certo insieme dato.
     *
     * @param requestedFacilities l'insieme di facilities richieste da
     *                            soddisfare, sono da considerare solo le
     *                            posizioni diverse da null
     * @return true se e solo se tutte le facilities di
     * {@code requestedFacilities} sono soddisfatte da questa aula.
     * @throws NullPointerException se il set di facility richieste è nullo
     */
    public boolean satisfiesFacilities(Facility[] requestedFacilities) {
        if (requestedFacilities == null) {
            throw new NullPointerException("set di facility richieste è nullo");
        }

        boolean nonNullo = false;
        boolean test = false;

        for (int i = 0; i < requestedFacilities.length; i++) {
            if (requestedFacilities[i] != null) {
                nonNullo = true;
            }
        }

        if (!nonNullo) {
            return true;
        }

        for (int i = 0; i < requestedFacilities.length; i++) {
            test = false;
            if (!requestedFacilities[i].equals(null)) {
                for (int j = 0; j < numFacilities; j++) {
                    if (requestedFacilities[i].equals(facilities[j])) {
                        test = true;
                    }
                }
                if (!test) {
                    return false;
                }
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
            Aula a = new Aula(this.nome, this.location);
            Prenotazione pr = new Prenotazione(a, ts, docente, motivo);

            try {
                prenotazioni[numPrenotazioni] = pr;
                numPrenotazioni++;
            } catch (Exception ArrayIndexOutOfBoundsException) {
                Prenotazione[] copyprenotazioni = new Prenotazione[numPrenotazioni];

                for (int i = 0; i < numPrenotazioni; i++) {
                    copyprenotazioni[i] = prenotazioni[i];
                }

                prenotazioni = new Prenotazione[numPrenotazioni * 2];

                for (int i = 0; i < copyprenotazioni.length; i++) {
                    prenotazioni[i] = copyprenotazioni[i];
                }

                prenotazioni[numPrenotazioni] = pr;
                numPrenotazioni++;
            }
        }
    }
}
