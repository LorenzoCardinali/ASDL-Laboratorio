package it.unicam.cs.asdl2021.es5;

import com.sun.source.tree.CaseTree;

import javax.xml.transform.SourceLocator;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Un time slot è un intervallo di tempo continuo che può essere associato ad
 * una prenotazione o a una facility. Gli oggetti della classe sono immutabili.
 * Non sono ammessi time slot che iniziano e finiscono nello stesso istante.
 *
 * @author Template: Luca Tesei, Implementazione: collettiva
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
     * @param start inizio del time slot
     * @param stop  fine del time slot
     * @throws NullPointerException     se uno dei due istanti, start o stop, è null
     * @throws IllegalArgumentException se start è uguale o successivo a stop
     */
    public TimeSlot(GregorianCalendar start, GregorianCalendar stop) {
        if (start == null) {
            throw new NullPointerException("Instante 'start' null");
        }
        if (stop == null) {
            throw new NullPointerException("Instante 'stop' null");
        }
        if (start.compareTo(stop) == 0) {
            throw new IllegalArgumentException("L'instante start e stop sono uguali");
        }
        if (start.compareTo(stop) > 0) {
            throw new IllegalArgumentException("L'instante start si presenta dopo l'instante stop");
        }

        this.start = start;
        this.stop = stop;
    }

    /**
     * @return the start
     */
    public GregorianCalendar getStart() {
        return this.start;
    }

    /**
     * @return the stop
     */
    public GregorianCalendar getStop() {
        return this.stop;
    }

    /*
     * Ridefinire in accordo con equals
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.start, this.stop);
    }

    /*
     * Un time slot è uguale a un altro se rappresenta esattamente lo stesso
     * intervallo di tempo, cioè se inizia nello stesso istante e termina nello
     * stesso istante.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimeSlot)) {
            return false;
        }
        TimeSlot timeSlot = (TimeSlot) obj;

        return this.hashCode() == timeSlot.hashCode();
    }

    /*
     * Un time slot precede un altro se inizia prima. Se due time slot iniziano
     * nello stesso momento quello che finisce prima precede l'altro. Se hanno
     * stesso inizio e stessa fine sono uguali, in compatibilità con equals.
     */
    @Override
    public int compareTo(TimeSlot o) {
        return this.start.compareTo(o.start);
    }

    /**
     * Determina se questo time slot si sovrappone a un altro time slot dato,
     * considerando la soglia di tolleranza.
     *
     * @param o il time slot che viene passato per il controllo di sovrapposizione
     * @return true se questo time slot si sovrappone per più di
     * MINUTES_OF_TOLERANCE_FOR_OVERLAPPING minuti a quello passato
     * @throws NullPointerException se il time slot passato è nullo
     */
    public boolean overlapsWith(TimeSlot o) {
        if (o == null) {
            throw new NullPointerException("TimeSlot nullo");
        }

        //timeslot uguali
        if (this.compareTo(o) == 0) {
            return true;
        }

        Date dataInserita = this.start.getTime();
        Date dataRicevuta = o.start.getTime();

        //controllo che le date siano uguali
        if (dataInserita.getDate() != dataRicevuta.getDate()) {
            return false;
        }
        if (dataInserita.getMonth() != dataRicevuta.getMonth()) {
            return false;
        }
        if (dataInserita.getYear() != dataRicevuta.getYear()) {
            return false;
        }

        Date start = new Date();
        Date stop = new Date();

        int startMin, stopMin, diff;

        //timeslot A precede timeslot B
        if ((this.start.compareTo(o.start) < 0) && (this.stop.compareTo(o.stop) < 0)) {
            stop = this.stop.getTime();
            start = o.start.getTime();
        } else
            //timeslot B precede timeslot A
            if ((this.start.compareTo(o.start) > 0) && (this.stop.compareTo(o.stop) > 0)) {
                start = this.start.getTime();
                stop = o.stop.getTime();
            } else
                //timeslot annidati (B dentro A)
                if ((this.start.compareTo(o.start) < 0) && (this.stop.compareTo(o.stop) > 0)) {
                    start = o.start.getTime();
                    stop = o.stop.getTime();
                } else
                    //timeslot annidati (A dentro B)
                    if ((this.start.compareTo(o.start) > 0) && (this.stop.compareTo(o.stop) < 0)) {
                        start = this.start.getTime();
                        stop = this.stop.getTime();
                    }

        stopMin = stop.getMinutes() + stop.getHours() * 60;
        startMin = start.getMinutes() + start.getHours() * 60;

        diff = stopMin - startMin;

        if (diff <= 0) {
            //timeslot separati e lontani
            return false;
        } else if (diff <= MINUTES_OF_TOLERANCE_FOR_OVERLAPPING) {
            //overlaps minore di MINUTES_OF_TOLERANCE_FOR_OVERLAPPING
            return false;
        } else {
            //overlaps maggiore di MINUTES_OF_TOLERANCE_FOR_OVERLAPPING
            return true;
        }
    }

    /*
     * Esempio di stringa: [4/11/2019 11.0 - 4/11/2019 13.0]
     * Esempio di stringa: [10/11/2019 11.15 - 10/11/2019 23.45]
     */

    @Override
    public String toString() {
        int yearStart, yearStop;
        String inizio, fine, range;

        Date start = this.start.getTime();
        Date stop = this.stop.getTime();

        yearStart = start.getYear() + 1900;
        yearStop = start.getYear() + 1900;

        inizio = start.getDate() + "/" + start.getMonth() + "/" + yearStart + " " + start.getHours() + "." + start.getMinutes();
        fine = stop.getDate() + "/" + stop.getMonth() + "/" + yearStop + " " + stop.getHours() + "." + stop.getMinutes();

        range = "[" + inizio + " - " + fine + "]";

        return range;
    }
}
