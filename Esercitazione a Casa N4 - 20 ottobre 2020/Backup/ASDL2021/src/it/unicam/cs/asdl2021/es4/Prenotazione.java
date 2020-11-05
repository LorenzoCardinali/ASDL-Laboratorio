package it.unicam.cs.asdl2021.es4;

import java.util.Date;
import java.util.Objects;

/**
 * Una prenotazione riguarda una certa aula per un certo time slot.
 *
 * @author Template: Luca Tesei Implementazione: collettiva
 */
public class Prenotazione implements Comparable<Prenotazione> {

    private final Aula aula;

    private final TimeSlot timeSlot;

    private final String docente;

    private final String motivo;

    /**
     * Costruisce una prenotazione.
     *
     * @param aula     l'aula a cui la prenotazione si riferisce
     * @param timeSlot il time slot della prenotazione
     * @param docente  il nome del docente che ha prenotato l'aula
     * @param motivo   il motivo della prenotazione
     * @throws NullPointerException se uno qualsiasi degli oggetti passati è null
     */
    public Prenotazione(Aula aula, TimeSlot timeSlot, String docente, String motivo) {
        if (aula == null) {
            throw new NullPointerException("Parametro 'aula' null");
        }
        if (timeSlot == null) {
            throw new NullPointerException("Parametro 'timeSlot' null");
        }
        if (docente == null) {
            throw new NullPointerException("Parametro 'docente' null");
        }
        if (motivo == null) {
            throw new NullPointerException("Parametro 'motivo' null");
        }

        this.aula = aula;
        this.timeSlot = timeSlot;
        this.docente = docente;
        this.motivo = motivo;
    }

    /**
     * @return the aula
     */
    public Aula getAula() {
        return aula;
    }

    /**
     * @return the timeSlot
     */
    public TimeSlot getTimeSlot() {
        return this.timeSlot;
    }

    /**
     * @return the docente
     */
    public String getDocente() {
        return this.docente;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return this.motivo;
    }

    /*
     * Ridefinire in accordo con equals
     */
    @Override
    public int hashCode() {
        return Objects.hash(getAula(),getTimeSlot());
    }

    /*
     * L'uguaglianza è data solo da stessa aula e stesso time slot.
     * Non sono ammesse prenotazioni diverse con stessa aula e stesso time slot.
     *
     * true = aula == time slot
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Prenotazione)) {
            return false;
        }
        Prenotazione o = (Prenotazione) obj;

        return this.hashCode() == o.hashCode();
    }

    /*
     * Una prenotazione precede un altra in base all'ordine dei time slot.
     * Se due prenotazioni hanno lo stesso time slot allora una precede l'altra in base all'ordine tra le aule.
     */
    @Override
    public int compareTo(Prenotazione o) {
        if(o == null) {
            throw new NullPointerException("prenotazione nulla");
        }

        if(this.timeSlot.hashCode() == o.timeSlot.hashCode()) {
            return this.aula.compareTo(o.aula);
        }
        return this.timeSlot.compareTo(o.timeSlot);
    }

    @Override
    public String toString() {
        return "Prenotazione [aula = " + aula + ", time slot =" + timeSlot + ", docente=" + docente + ", motivo=" + motivo + "]";
    }

}
