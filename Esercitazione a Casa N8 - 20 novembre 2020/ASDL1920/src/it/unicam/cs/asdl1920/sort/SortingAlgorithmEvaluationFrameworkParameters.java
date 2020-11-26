package it.unicam.cs.asdl1920.sort;

/**
 * Parametri generali del framework di valutazione degli algoritmi di
 * ordinamento.
 *
 * @author Luca Tesei
 */
public interface SortingAlgorithmEvaluationFrameworkParameters {
    /**
     * Lunghezza minima delle sequenze da generare
     */
    int MIN_LENGTH = 10;

    /**
     * Passo di incremento della lunghezza da MIN_LENGTH a MAX_LENGTH
     */
    int INCREMENTO_LUNGHEZZA = 10;

    /**
     * Lunghezza massima delle sequenze da generare
     */
    int MAX_LENGTH = 100;

    /**
     * Numero di sequenze da generare per lunghezza
     */
    int NUMBER_OF_SAMPLES_PER_LENGTH = 100;

    /**
     * Maximum non-negative integer number randomly generated in sample
     * sequences
     */
    int MAX_GENERATED_INTEGER = 300;

}
