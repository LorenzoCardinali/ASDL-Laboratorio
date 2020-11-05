package it.unicam.cs.asdl2021.combinationlock;

/**
 * Uno scassinatore è un oggetto che prende una certa cassaforte e trova la
 * combinazione utilizzando la "forza bruta".
 *
 * @author Luca Tesei
 */
public class Burglar {

    private final CombinationLock cassaforte;

    private int nTentativi;

    /**
     * Costruisce uno scassinatore per una certa cassaforte.
     *
     * @param aCombinationLock
     * @throw NullPointerException se la cassaforte passata è nulla
     */
    public Burglar(CombinationLock aCombinationLock) {
        if (aCombinationLock == null) throw new NullPointerException("Cassaforte nulla");
        cassaforte = aCombinationLock;
        cassaforte.lock();

        nTentativi = -1;
    }

    /**
     * Forza la cassaforte e restituisce la combinazione.
     *
     * @return la combinazione della cassaforte forzata.
     */
    public String findCombination() {

        cassaforte.lock();
        nTentativi = 1;

        char i, j, z;

        StringBuilder combinazione = new StringBuilder();

        for (i = 'A'; i <= 'Z'; i++) {
            for (j = 'A'; j <= 'Z'; j++) {
                for (z = 'A'; z <= 'Z'; z++) {
                    cassaforte.setPosition(i);
                    cassaforte.setPosition(j);
                    cassaforte.setPosition(z);
                    cassaforte.open();

                    if (cassaforte.isOpen()) {
                        combinazione.append(i);
                        combinazione.append(j);
                        combinazione.append(z);
                        return (combinazione.toString());
                    }

                    nTentativi = nTentativi + 1;
                }
            }
        }

        return null;
    }

    /**
     * Restituisce il numero di tentativi che ci sono voluti per trovare la
     * combinazione. Se la cassaforte non è stata ancora forzata restituisce -1.
     *
     * @return il numero di tentativi che ci sono voluti per trovare la
     * combinazione, oppure -1 se la cassaforte non è stata ancora
     * forzata.
     */
    public long getAttempts() {
        return nTentativi;
    }
}
