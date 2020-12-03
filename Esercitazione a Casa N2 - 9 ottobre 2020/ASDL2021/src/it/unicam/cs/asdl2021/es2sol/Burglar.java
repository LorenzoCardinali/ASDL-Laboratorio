package it.unicam.cs.asdl2021.es2sol;

/**
 * Uno scassinatore è un oggetto che prende una certa cassaforte e trova la
 * combinazione utilizzando la "forza bruta".
 * 
 * @author Luca Tesei
 *
 */
public class Burglar {

    private final CombinationLock cassaforte;

    private int tentativi;

    /**
     * Costruisce uno scassinatore per una certa cassaforte.
     * 
     * @param aCombinationLock
     * @throw NullPointerException se la cassaforte passata è nulla
     */
    public Burglar(CombinationLock aCombinationLock) {
        if (aCombinationLock == null)
            throw new NullPointerException(
                    "Tentativo di costruire uno scassinatore per una cassaforte nulla");
        cassaforte = aCombinationLock;
        tentativi = -1;
    }

    /**
     * Forza la cassaforte e restituisce la combinazione.
     * 
     * @return la combinazione della cassaforte forzata.
     */
    public String findCombination() {
        this.cassaforte.lock();
        tentativi = 0;
        char c1, c2, c3;
        for (c1 = 'A'; c1 <= 'Z'; c1++)
            for (c2 = 'A'; c2 <= 'Z'; c2++)
                for (c3 = 'A'; c3 <= 'Z'; c3++) {
                    tentativi++;
                    cassaforte.setPosition(c1);
                    cassaforte.setPosition(c2);
                    cassaforte.setPosition(c3);
                    cassaforte.open();
                    if (cassaforte.isOpen()) {
                        StringBuffer s = new StringBuffer();
                        s.append(c1);
                        s.append(c2);
                        s.append(c3);
                        return s.toString();
                    }
                }
        return null;
    }

    /**
     * Restituisce il numero di tentativi che ci sono voluti per trovare la
     * combinazione. Se la cassaforte non è stata ancora forzata restituisce -1.
     * 
     * @return il numero di tentativi che ci sono voluti per trovare la
     *         combinazione, oppure -1 se la cassaforte non è stata ancora
     *         forzata.
     */
    public long getAttempts() {
        return tentativi;
    }
}
