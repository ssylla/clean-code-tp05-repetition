package org.sebsy.biblio;

import java.math.BigDecimal;

/** Types de document, chacun avec ses jours de grâce et son tarif journalier. Un type de plus, une ligne de plus. */
public enum TypeDocument {
    LIVRE(21, new BigDecimal("0.15")),
    BD(14, new BigDecimal("0.15")),
    DVD(7, new BigDecimal("0.50"));

    private final int joursDeGrace;
    private final BigDecimal tarifJournalier;

    TypeDocument(int joursDeGrace, BigDecimal tarifJournalier) {
        this.joursDeGrace = joursDeGrace;
        this.tarifJournalier = tarifJournalier;
    }

    int joursDeRetard(int joursEcoules) {
        return Math.max(0, joursEcoules - joursDeGrace);
    }

    BigDecimal tarifJournalier() {
        return tarifJournalier;
    }
}
