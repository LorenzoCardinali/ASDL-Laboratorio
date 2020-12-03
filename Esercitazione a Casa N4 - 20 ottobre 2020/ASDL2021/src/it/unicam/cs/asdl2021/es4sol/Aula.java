package it.unicam.cs.asdl2021.es4sol;

/**
 * Un oggetto della classe aula rappresenta una certa aula con le sue facilities
 * e le sue prenotazioni.
 * 
 * @author Template: Luca Tesei, Implementation: Collective
 *
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
                    "Creazione di un'aula con nome null");
        if (location == null)
            throw new NullPointerException(
                    "Creazione di un'aula con location null");
        this.nome = nome;
        this.location = location;
        this.facilities = new Facility[INIT_NUM_FACILITIES];
        this.numFacilities = 0;
        this.prenotazioni = new Prenotazione[INIT_NUM_PRENOTAZIONI];
        this.numPrenotazioni = 0;
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
    public Facility[] getFacilities() {
        return this.facilities;
    }

    /**
     * @return il numero corrente di facilities
     */
    public int getNumeroFacilities() {
        return this.numFacilities;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * @return the prenotazioni
     */
    public Prenotazione[] getPrenotazioni() {
        return this.prenotazioni;
    }

    /**
     * @return il numero corrente di prenotazioni
     */
    public int getNumeroPrenotazioni() {
        return this.numPrenotazioni;
    }

    /**
     * Aggiunge una faciltity a questa aula. Controlla se la facility è già
     * presente, nel qual caso non la inserisce.
     * 
     * @param f
     *              la facility da aggiungere
     * @return true se la facility non era già presente e quindi è stata
     *         aggiunta, false altrimenti
     * @throws NullPointerException
     *                                  se la facility passata è nulla
     */
    public boolean addFacility(Facility f) {
        // Nota: attenzione! Per controllare se una facility è già presente
        // bisogna usare il metodo equals della classe Facility.
        // Nota: attenzione bis! Si noti che per le sottoclassi di Facility non
        // è richiesto di ridefinire ulteriormente il metodo equals...
        if (f == null)
            throw new NullPointerException("Aggiunta di una facility null");
        // Cerco se la facility è già presente
        for (int i = 0; i < this.numFacilities; i++)
            if (this.facilities[i].equals(f))
                return false; // la facility era già presente e non la aggiungo
        // la facility non è presente
        // controllo se c'è spazio
        if (this.numFacilities < this.facilities.length) {
            // c'è spazio e la inserisco
            this.facilities[this.numFacilities] = f;
            this.numFacilities++;
        } else {
            // non c'è spazio, raddoppio l'array
            this.facilities = (Facility[]) raddoppia(this.facilities);
            // ora c'è spazio e inserisco
            this.facilities[this.numFacilities] = f;
            this.numFacilities++;
        }
        return true;
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
        if (ts == null)
            throw new NullPointerException("Il timeslot passato è nullo");
        // cerco se c'è sovrapposizione con almeno una prenotazione
        for (int i = 0; i < numPrenotazioni; i++) {
            if (prenotazioni[i].getTimeSlot().overlapsWith(ts))
                return false;
        }
        // non c'è nessuna sovrapposizione
        return true;
    }

    /**
     * Determina se questa aula soddisfa tutte le facilities richieste
     * rappresentate da un certo insieme dato.
     * 
     * @param requestedFacilities
     *                                l'insieme di facilities richieste da
     *                                soddisfare, sono da considerare solo le
     *                                posizioni diverse da null
     * @return true se e solo se tutte le facilities di
     *         {@code requestedFacilities} sono soddisfatte da questa aula.
     * @throws NullPointerException
     *                                  se il set di facility richieste è nullo
     */
    public boolean satisfiesFacilities(Facility[] requestedFacilities) {
        if (requestedFacilities == null)
            throw new NullPointerException(
                    "Il set di facility richieste è nullo");
        // provo a soddisfare tutte le facilities richieste
        for (int i = 0; i < requestedFacilities.length; i++) {
            // ignoro eventuali posizioni null
            if (requestedFacilities[i] == null)
                continue;
            // scorro le facilities di questa aula e vedo se almeno una soddisfa
            // la facility richiesta i
            boolean trovata = false;
            int j = 0;
            while (j < this.numFacilities && !trovata)
                if (this.facilities[j].satisfies(requestedFacilities[i]))
                    trovata = true;
                else
                    j++;
            // vedo se non è stata trovata nessuna facility che soddisfa la
            // facility i
            if (!trovata)
                return false;
        }
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
        if (ts == null)
            throw new NullPointerException(
                    "TimeSlot della nuova prenotazione null");
        if (docente == null)
            throw new NullPointerException(
                    "Docente della nuova prenotazione null");
        if (motivo == null)
            throw new NullPointerException(
                    "Motivo della nuova prenotazione null");
        // Cerco se la prenotazione può essere effettuata
        for (int i = 0; i < this.numPrenotazioni; i++)
            if (this.prenotazioni[i].getTimeSlot().overlapsWith(ts))
                throw new IllegalArgumentException(
                        "Tentativo di aggiungere una prenotazione nell'aula in un timeslot che si sovrappone con un'altra prenotazione");
        // controllo se c'è spazio
        if (this.numPrenotazioni < this.prenotazioni.length) {
            // c'è spazio e la inserisco
            this.prenotazioni[this.numPrenotazioni] = new Prenotazione(this, ts,
                    docente, motivo);
            this.numPrenotazioni++;
        } else {
            // non c'è spazio, raddoppio l'array
            this.prenotazioni = (Prenotazione[]) raddoppia(this.prenotazioni);
            // ora c'è spazio e inserisco
            this.prenotazioni[this.numPrenotazioni] = new Prenotazione(this, ts,
                    docente, motivo);
            this.numPrenotazioni++;
        }
    }

    /*
     * Metodo per raddoppiare gli array facilities e prenotazioni
     */
    private Object[] raddoppia(Object[] array) {
        if (array == null)
            throw new NullPointerException("Array da raddoppiare nullo");
        if (array instanceof Facility[]) {
            Facility[] array2 = new Facility[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                array2[i] = (Facility) array[i];
            }
            return array2;
        }
        if (array instanceof Prenotazione[]) {
            Prenotazione[] array2 = new Prenotazione[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                array2[i] = (Prenotazione) array[i];
            }
            return array2;
        }

        return null;
    }
}
