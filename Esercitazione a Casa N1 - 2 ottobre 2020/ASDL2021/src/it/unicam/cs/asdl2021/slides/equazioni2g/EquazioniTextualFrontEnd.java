package it.unicam.cs.asdl2021.slides.equazioni2g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Front-end testuale per permettere l'uso delle classi di <i>core business
 * logic</i> che risolvono le equazioni di secondo grado, in particolare le
 * classi EquazioneSecondoGrado, SoluzioneEquazioneSecondoGrado e
 * RisolutoreEquazioniSecondoGrado.
 *
 * @author Luca Tesei
 */
public class EquazioniTextualFrontEnd {
    /*
     * Costante piccola per il confronto di due numeri double
     */
    private static final double EPSILON = 1.0E-15;

    public static void main(String[] args) {
        RisolutoreEquazioniSecondoGrado solver = new RisolutoreEquazioniSecondoGrado();
        boolean terminate = false;
        BufferedReader input = new BufferedReader(
                new InputStreamReader(System.in));
        Double a = null;
        Double b = null;
        Double c = null;
        while (!terminate) {
            // leggo il parametro a
            boolean retry;
            do {
                System.out.println(
                        "Inserisci il valore del parametro a e premi INVIO");
                /*
                cambio del valore di "retry" mancante all'interno del do-while
                (se inserito un valore errato "retry" non sarà mai "false", creando un loop all'interno del do-while)
                (stessa cosa per i parametri b e c)
                 */
                retry = false;
                try {
                    String aInput = input.readLine();
                    a = Double.parseDouble(aInput);
                    // controllo se il valore inserito è zero
                    if (Math.abs(a) < EPSILON) {
                        System.out.println(
                                "Errore: Il valore del parametro a non può essere zero! Ritenta...");
                        retry = true;
                    }
                } catch (IOException e) {
                    System.err.println("Errore di Input/Output!");
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Errore: Il valore del parametro a deve essere un numero! Ritenta...");
                    retry = true;
                }
            } while (retry);

            // leggo il parametro b
            do {
                System.out.println(
                        "Inserisci il valore del parametro b e premi INVIO");
                retry = false;
                try {
                    String aInput = input.readLine();
                    b = Double.parseDouble(aInput);
                } catch (IOException e) {
                    System.err.println("Errore di Input/Output!");
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Errore: Il valore del parametro b deve essere un numero! Ritenta...");
                    retry = true;
                }
            } while (retry);
            // leggo il parametro c
            do {
                System.out.println(
                        "Inserisci il valore del parametro c e premi INVIO");
                retry = false;
                try {
                    String aInput = input.readLine();
                    c = Double.parseDouble(aInput);
                } catch (IOException e) {
                    System.err.println("Errore di Input/Output!");
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Errore: Il valore del parametro c deve essere un numero! Ritenta...");
                    retry = true;
                }
            } while (retry);
            // Creo l'equazione e la risolvo
            EquazioneSecondoGrado eq = new EquazioneSecondoGrado(a, b, c);
            SoluzioneEquazioneSecondoGrado sol = solver.solve(eq);
            // Comunico la soluzione all'utente
            System.out.println(sol);
            // Chiedo se l'utente vuole continuare con un'altra equazione
            System.out.println(
                    "Vuoi risolvere un'altra equazione? Inserisci 's' o 'S' per continuare, qualsiasi altro carattere per uscire e premi INVIO");
            String resp = null;
            try {
                String aInput = input.readLine();
                resp = aInput.trim().toUpperCase();
            } catch (IOException e) {
                System.err.println("Errore di Input/Output!");
                System.exit(1);
            }
            if (!resp.equals("S"))
                terminate = true;
        }
    }

}
