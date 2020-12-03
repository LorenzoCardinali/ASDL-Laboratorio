/**
 * 
 */
package it.unicam.cs.asdl2021.es4sol;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Un time slot è un intervallo di tempo continuo che può essere associato ad
 * una prenotazione o a una facility. Gli oggetti della classe sono immutabili.
 * Non sono ammessi time slot che iniziano e finiscono nello stesso istante.
 * 
 * @author Template: Luca Tesei, Implementation: Collective
 *
 */
public class TimeSlot implements Comparable<TimeSlot> {

    /**
     * Rappresenta la soglia di tolleranza da considerare nella sovrapposizione
     * di due Time Slot. Se si sovrappongono per un numero di minuti minore o
     * uguale a questa soglia allora NON vengono considerati sovrapposti.
     */
    public static final int MINUTES_OF_TOLERANCE_FOR_OVERLAPPING = 5;

    private final GregorianCalendar start;

    private final GregorianCalendar stop;

    /**
     * Crea un time slot tra due istanti di inizio e fine
     * 
     * @param start
     *                  inizio del time slot
     * @param stop
     *                  fine del time slot
     * @throws NullPointerException
     *                                      se uno dei due istanti, start o
     *                                      stop, è null
     * @throws IllegalArgumentException
     *                                      se start è uguale o successivo a
     *                                      stop
     */
    public TimeSlot(GregorianCalendar start, GregorianCalendar stop) {
        if (start == null)
            throw new NullPointerException(
                    "Tentativo di creare un time slot con inizio nullo");
        if (stop == null)
            throw new NullPointerException(
                    "Tentativo di creare un time slot con fine nulla");
        if (start.compareTo(stop) >= 0)
            throw new IllegalArgumentException(
                    "Tentativo di creare un time slot con inizio maggiore o uguale di fine");
        this.start = start;
        this.stop = stop;
    }

    /**
     * @return the start
     */
    public GregorianCalendar getStart() {
        return start;
    }

    /**
     * @return the stop
     */
    public GregorianCalendar getStop() {
        return stop;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((stop == null) ? 0 : stop.hashCode());
        return result;
    }

    /*
     * Un time slot è uguale a un altro se rappresenta esattamente lo stesso
     * intervallo di tempo, cioè se inizia nello stesso istante e termina nello
     * stesso istante.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof TimeSlot))
            return false;
        TimeSlot other = (TimeSlot) obj;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
        if (stop == null) {
            if (other.stop != null)
                return false;
        } else if (!stop.equals(other.stop))
            return false;
        return true;
    }

    /*
     * Un time slot precede un altro se inizia prima. Se due time slot iniziano
     * nello stesso momento quello che finisce prima precede l'altro. Se hanno
     * stesso inizio e stessa fine sono uguali, in compatibilità con equals.
     */
    @Override
    public int compareTo(TimeSlot o) {
        if (o == null)
            throw new NullPointerException(
                    "Tentativo di comparare un time slot nullo");
        int cmp = this.start.compareTo(o.start);
        if (cmp != 0)
            // i due time slot non iniziano nello stesso momento, quindi cmp dà
            // il valore giusto di ritorno
            return cmp;
        // i due time slot iniziano nello stesso momento
        cmp = this.stop.compareTo(o.stop);
        // se zero allora sono uguali, altrimenti cmp dà il valore giusto di
        // ritorno
        return cmp;
    }

    /**
     * Determina se questo time slot si sovrappone a un altro time slot dato,
     * considerando la soglia di tolleranza.
     * 
     * @param o
     *              il time slot che viene passato per il controllo di
     *              sovrapposizione
     * @return true se questo time slot si sovrappone per più di
     *         MINUTES_OF_TOLERANCE_FOR_OVERLAPPING minuti a quello passato
     * @throws NullPointerException
     *                                  se il time slot passato è nullo
     */
    public boolean overlapsWith(TimeSlot o) {
        if (o == null)
            throw new NullPointerException(
                    "Tentativo di controllare se questo time slot si sovrappone con un time slot nullo");
        // Controllo tutti i casi di sovrapposizione
        int cmp1 = this.start.compareTo(o.start);
        int cmp2 = this.start.compareTo(o.stop);
        int cmp3 = this.stop.compareTo(o.start);
        int cmp4 = this.stop.compareTo(o.stop);
        long overlappingMilliseconds;
        if (cmp1 <= 0 && cmp3 >= 0 && cmp4 <= 0) {
            // questo timeslot inizia prima di quello passato e termina dopo che
            // quello passato è iniziato
            // this.start ... [o.start ... this.stop] ... o.stop
            overlappingMilliseconds = this.stop.getTimeInMillis()
                    - o.start.getTimeInMillis();
            if (overlappingMilliseconds > MINUTES_OF_TOLERANCE_FOR_OVERLAPPING
                    * 60 * 1000)
                // c'è sovrapposizione
                return true;
            else // la sovrapposizione non raggiunge la soglia
                return false;
        }
        if (cmp1 <= 0 && cmp4 >= 0) {
            // questo timeslot inizia prima di quello passato e termina dopo che
            // quello passato è finito
            // this.start ... [o.start ... o.stop] ... this.stop
            overlappingMilliseconds = o.stop.getTimeInMillis()
                    - o.start.getTimeInMillis();
            if (overlappingMilliseconds > MINUTES_OF_TOLERANCE_FOR_OVERLAPPING
                    * 60 * 1000)
                // c'è sovrapposizione
                return true;
            else // la sovrapposizione non raggiunge la soglia
                return false;
        }

        if (cmp1 >= 0 && cmp2 <= 0 && cmp4 >= 0) {
            // questo timeslot inizia dopo di quello passato e termina dopo che
            // quello passato è terminato
            // o.start ... [this.start ... o.stop] ... this.stop
            overlappingMilliseconds = o.stop.getTimeInMillis()
                    - this.start.getTimeInMillis();
            if (overlappingMilliseconds > MINUTES_OF_TOLERANCE_FOR_OVERLAPPING
                    * 60 * 1000)
                // c'è sovrapposizione
                return true;
            else // la sovrapposizione non raggiunge la soglia
                return false;
        }
        if (cmp1 >= 0 && cmp4 <= 0) {
            // questo timeslot inizia prima di quello passato e termina dopo che
            // quello passato è finito
            // o.start ... [this.start ... this.stop] ... o.stop
            overlappingMilliseconds = this.stop.getTimeInMillis()
                    - this.start.getTimeInMillis();
            if (overlappingMilliseconds > MINUTES_OF_TOLERANCE_FOR_OVERLAPPING
                    * 60 * 1000)
                // c'è sovrapposizione
                return true;
            else // la sovrapposizione non raggiunge la soglia
                return false;
        }
        // non c'è sovrapposizione
        return false;
    }

    /*
     * Esempio di stringa: [4/11/2019 11.0 - 4/11/2019 13.0] Esempio di stringa:
     * [10/11/2019 11.15 - 10/11/2019 23.45]
     */
    @Override
    public String toString() {
        return "[" + start.get(Calendar.DAY_OF_MONTH) + "/"
                + (start.get(Calendar.MONTH) + 1) + "/"
                + start.get(Calendar.YEAR) + " "
                + start.get(Calendar.HOUR_OF_DAY) + "."
                + start.get(Calendar.MINUTE) + " - "
                + stop.get(Calendar.DAY_OF_MONTH) + "/"
                + (stop.get(Calendar.MONTH) + 1) + "/" + stop.get(Calendar.YEAR)
                + " " + stop.get(Calendar.HOUR_OF_DAY) + "."
                + stop.get(Calendar.MINUTE) + "]";
    }

}
