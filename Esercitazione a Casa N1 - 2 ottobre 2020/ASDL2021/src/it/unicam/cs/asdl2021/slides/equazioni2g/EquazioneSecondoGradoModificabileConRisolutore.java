/**
 *
 */
package it.unicam.cs.asdl2021.slides.equazioni2g;

/**
 * Un oggetto di questa classe permette di rappresentare una equazione di
 * secondo grado e di trovarne le soluzioni reali. I valori dei parametri
 * dell'equazione possono cambiare nel tempo. All'inizio e ogni volta che viene
 * cambiato un parametro l'equazione deve essere risolta di nuovo con la
 * chiamata del metodo opportuno.
 *
 * @author Luca Tesei
 *
 */
public class EquazioneSecondoGradoModificabileConRisolutore {
    /*
     * Costante piccola per il confronto di due numeri double
     */
    private static final double EPSILON = 1.0E-15;

    private double a;

    private double b;

    private double c;

    private boolean solved;

    private SoluzioneEquazioneSecondoGrado lastSolution;

    /**
     * Costruisce una equazione di secondo grado.
     *
     *
     * @param a
     *              coefficiente del termine x^2, deve essere diverso da zero.
     * @param b
     *              coefficiente del termine x
     * @param c
     *              termine noto
     * @throws IllegalArgumentException
     *                                      se il parametro <code>a</code> è
     *                                      zero
     *
     */
    public EquazioneSecondoGradoModificabileConRisolutore(double a, double b,
                                                          double c) {
        if (Math.abs(a) < EPSILON) // controllo se uguale a zero
            throw new IllegalArgumentException("L'equazione di secondo grado"
                    + " non può avere coefficiente a uguale a zero");
        this.a = a;
        this.b = b;
        this.c = c;
        this.solved = false; // all'inizio l'equazione non è risolta
        this.lastSolution = null; // all'inizion non viene calcolata nessuna
        // soluzione
    }

    /**
     * @return il valore corrente del parametro a
     */
    public double getA() {
        return a;
    }

    /**
     * Cambia il parametro a di questa equazione. Dopo questa operazione
     * l'equazione va risolta di nuovo.
     *
     * @param a
     *              il nuovo valore del parametro a
     * @throws IllegalArgumentException
     *                                      se il nuovo valore è zero
     */
    public void setA(double a) {
        if (Math.abs(a) < EPSILON) // controllo se uguale a zero
            throw new IllegalArgumentException("L'equazione di secondo grado"
                    + " non può avere coefficiente a uguale a zero");
        this.a = a;
        this.solved = false;
    }

    /**
     * @return il valore corrente del parametro b
     */
    public double getB() {
        return b;
    }

    /**
     * Cambia il parametro b di questa equazione. Dopo questa operazione
     * l'equazione va risolta di nuovo.
     *
     * @param b
     *              il nuovo valore del parametro b
     */
    public void setB(double b) {
        this.b = b;
        this.solved = false;
    }

    /**
     * @return il valore corrente del parametro c
     */
    public double getC() {
        return c;
    }

    /**
     * Cambia il parametro c di questa equazione. Dopo questa operazione
     * l'equazione va risolta di nuovo.
     *
     * @param c
     *              il nuovo valore del parametro c
     */
    public void setC(double c) {
        this.c = c;
        this.solved = false;
    }

    /**
     * Determina se l'equazione, nel suo stato corrente, è già stata risolta.
     *
     * @return true se l'equazione è risolta, false altrimenti
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * Risolve l'equazione risultante dai parametri a, b e c correnti.
     */
    public void solve() {

        // se l'equazione è già stata risolta non fa nulla
        if (this.solved)
            return;

        // risolve l'equazione
        double delta = this.b * this.b - 4 * this.a * this.c;

        if (delta < 0)
            // salva la soluzione vuota
            this.lastSolution = new SoluzioneEquazioneSecondoGrado(
                    new EquazioneSecondoGrado(this.a, this.b, this.c));
        else
            // delta >= 0
            if (delta == 0)
                // salva la soluzione con due valori coincidenti
                this.lastSolution = new SoluzioneEquazioneSecondoGrado(
                        new EquazioneSecondoGrado(this.a, this.b, this.c),
                        (-this.b) / 2 * this.a);
            else {
                // delta > 0
                double tmp = Math.sqrt(delta);
                // salva la soluzione con due valori distinti
                this.lastSolution = new SoluzioneEquazioneSecondoGrado(
                        new EquazioneSecondoGrado(this.a, this.b, this.c),
                        (-this.b + tmp) / 2 * this.a, (-this.b - tmp) / 2 * this.a);
                // mette a true il flag solved
            }
        this.solved = true;
    }

    /**
     * Restituisce la soluzione dell'equazione risultante dai parametri
     * correnti. L'equazione con i parametri correnti deve essere stata
     * precedentemente risolta.
     *
     * @return la soluzione
     * @throws IllegalStateException se l'equazione non è stata risolta
     *                               precedentemente, all'inizio o dopo un
     *                               cambiamento di almeno uno dei parametri
     */
    public SoluzioneEquazioneSecondoGrado getSolution() {
        if (!this.solved)
            throw new IllegalStateException(
                    "Richiesta di soluzione di equazione non risolta");
        return this.lastSolution;
    }

}
