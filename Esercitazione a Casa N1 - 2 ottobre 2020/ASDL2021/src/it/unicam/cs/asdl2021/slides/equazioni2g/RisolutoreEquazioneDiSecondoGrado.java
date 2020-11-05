/**
 *
 */
package it.unicam.cs.asdl2021.slides.equazioni2g;

/**
 * Un oggetto di questa classe è un attore che risolve una equazione di secondo
 * grado cercando soluzioni reali. L'equazione da risolvere viene memorizzata
 * nello stato. Lo stato dell'oggetto è immutabile, quindi per risolvere
 * un'altra equazione bisogna creare un altro oggetto risolutore. L'oggetto non
 * ha stato e i suoi input vengono passati tramite i parametri dei metodi.
 * L'output viene dato come valore di ritorno dei metodi.
 *
 * @author Luca Tesei
 *
 */
public class RisolutoreEquazioneDiSecondoGrado {

    private final EquazioneSecondoGrado e;

    /**
     * Costruisce un risolutore per una data equazione.
     *
     * @param e
     *              l'equazione di questo risolutore
     * @throws NullPointerException
     *                                  se l'equazione passata è nulla
     */
    public RisolutoreEquazioneDiSecondoGrado(EquazioneSecondoGrado e) {
        if (e == null)
            throw new NullPointerException(
                    "Tentativo di costruire un risolutore di una equazione nulla");
        this.e = e;
    }

    /**
     * @return l'equazione di questo risolutore
     */
    public EquazioneSecondoGrado getE() {
        return e;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((e == null) ? 0 : e.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof RisolutoreEquazioneDiSecondoGrado))
            return false;
        RisolutoreEquazioneDiSecondoGrado other = (RisolutoreEquazioneDiSecondoGrado) obj;
        if (e == null) {
            return other.e == null;
        } else return e.equals(other.e);
    }

    /**
     * Restituisce la soluzione della equazione di secondo grado di questo
     * risolutore.
     *
     * @return la soluzione dell'equazione
     */
    public SoluzioneEquazioneSecondoGrado getSolution() {
        double delta = e.getB() * e.getB() - 4 * e.getA() * e.getC();
        if (delta < 0)
            // ritorna la soluzione vuota
            return new SoluzioneEquazioneSecondoGrado(e);
        // delta >= 0
        if (delta == 0)
            // ritorna la soluzione con due valori coincidenti
            return new SoluzioneEquazioneSecondoGrado(e,
                    (-e.getB()) / 2 * e.getA());
        // delta > 0
        double tmp = Math.sqrt(delta);
        // ritorna le due soluzioni calcolate
        return new SoluzioneEquazioneSecondoGrado(e,
                (-e.getB() + tmp) / 2 * e.getA(),
                (-e.getB() - tmp) / 2 * e.getA());
    }

}
