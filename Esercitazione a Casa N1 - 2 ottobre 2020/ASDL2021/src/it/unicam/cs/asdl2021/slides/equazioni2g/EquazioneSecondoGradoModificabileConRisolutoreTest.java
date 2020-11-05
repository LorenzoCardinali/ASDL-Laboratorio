package it.unicam.cs.asdl2021.slides.equazioni2g;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Template: Luca Tesei, Implementation: Studente
 */
class EquazioneSecondoGradoModificabileConRisolutoreTest {

    private static final double EPSILON = 1.0E-15;

    /**
     * Test method for {@link it.unicam.cs.asdl2021.slides.equazioni2g.EquazioneSecondoGradoModificabileConRisolutore#EquazioneSecondoGradoModificabileConRisolutore(double, double, double)}.
     */
    @Test
    final void testEquazioneSecondoGradoModificabileConRisolutore() {

        // controllo eccezione
        assertThrows(IllegalArgumentException.class,
                () -> new EquazioneSecondoGradoModificabileConRisolutore(0, 1, 1));

        // controllo senza eccezione
        EquazioneSecondoGradoModificabileConRisolutore eqtest = new EquazioneSecondoGradoModificabileConRisolutore(10, 5, 7);

        // controllo valori inseriti
        assertEquals(10.0, eqtest.getA());
        assertEquals(5.0, eqtest.getB());
        assertEquals(7.0, eqtest.getC());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.slides.equazioni2g.EquazioneSecondoGradoModificabileConRisolutore#getA()}.
     */
    @Test
    final void testGetA() {
        // inserisco equazione
        EquazioneSecondoGradoModificabileConRisolutore eqtest = new EquazioneSecondoGradoModificabileConRisolutore(10, 5, 7);

        // controllo valore di A
        assertEquals(10.0, eqtest.getA());

        assertTrue(10 - Math.abs(eqtest.getA()) < EPSILON);
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.slides.equazioni2g.EquazioneSecondoGradoModificabileConRisolutore#setA(double)}.
     */
    @Test
    final void testSetA() {
        // controllo eccezione
        assertThrows(IllegalArgumentException.class,
                () -> new EquazioneSecondoGrado(0, 1, 1));

        // inserisco equazione
        EquazioneSecondoGradoModificabileConRisolutore eqtest = new EquazioneSecondoGradoModificabileConRisolutore(10, 5, 7);

        //setto A
        eqtest.setA(15);

        // controllo aggiornamento valore di A
        assertEquals(15.0, eqtest.getA());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.slides.equazioni2g.EquazioneSecondoGradoModificabileConRisolutore#getB()}.
     */
    @Test
    final void testGetB() {
        // inserisco equazione
        EquazioneSecondoGradoModificabileConRisolutore eqtest = new EquazioneSecondoGradoModificabileConRisolutore(10, 5, 7);

        // controllo valore di B
        assertEquals(5.0, eqtest.getB());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.slides.equazioni2g.EquazioneSecondoGradoModificabileConRisolutore#setB(double)}.
     */
    @Test
    final void testSetB() {

        // inserisco equazione
        EquazioneSecondoGradoModificabileConRisolutore eqtest = new EquazioneSecondoGradoModificabileConRisolutore(10, 5, 7);

        //setto B
        eqtest.setB(3);

        // controllo aggiornamento valore di B
        assertEquals(3.0, eqtest.getB());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.slides.equazioni2g.EquazioneSecondoGradoModificabileConRisolutore#getC()}.
     */
    @Test
    final void testGetC() {
        // inserisco equazione
        EquazioneSecondoGradoModificabileConRisolutore eqtest = new EquazioneSecondoGradoModificabileConRisolutore(10, 5, 7);

        // controllo valore di B
        assertEquals(5.0, eqtest.getB());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.slides.equazioni2g.EquazioneSecondoGradoModificabileConRisolutore#setC(double)}.
     */
    @Test
    final void testSetC() {

        // inserisco equazione
        EquazioneSecondoGradoModificabileConRisolutore eqtest = new EquazioneSecondoGradoModificabileConRisolutore(10, 5, 7);

        //setto C
        eqtest.setC(2);

        // controllo aggiornamento valore di C
        assertEquals(2.0, eqtest.getC());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.slides.equazioni2g.EquazioneSecondoGradoModificabileConRisolutore#isSolved()}.
     */
    @Test
    final void testIsSolved() {

        // inserisco equazione
        EquazioneSecondoGradoModificabileConRisolutore eqtest = new EquazioneSecondoGradoModificabileConRisolutore(10, 5, 7);

        // test equazione non risolta
        assertFalse(eqtest.isSolved());

        // controllo risoluzione equazione
        eqtest.solve();
        assertTrue(eqtest.isSolved());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.slides.equazioni2g.EquazioneSecondoGradoModificabileConRisolutore#solve()}.
     */
    @Test
    final void testSolve() {

        // inserisco equazione
        EquazioneSecondoGradoModificabileConRisolutore eqtest = new EquazioneSecondoGradoModificabileConRisolutore(1, 5, 6);

        // test equazione non risolta
        assertFalse(eqtest.isSolved());

        // risolve e controlla equazione
        eqtest.solve();
        assertEquals(-2, eqtest.getSolution().getS1());
        assertEquals(-3, eqtest.getSolution().getS2());
    }

    /**
     * Test method for {@link it.unicam.cs.asdl2021.slides.equazioni2g.EquazioneSecondoGradoModificabileConRisolutore#getSolution()}.
     */
    @Test
    final void testGetSolution() {

        // inserisco equazione
        EquazioneSecondoGradoModificabileConRisolutore eqtest = new EquazioneSecondoGradoModificabileConRisolutore(1, 5, 6);

        // test getSolution
        assertThrows(IllegalStateException.class, eqtest::getSolution);
    }

}
