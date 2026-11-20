package org.sebsy.biblio;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CalculateurPenalite {

    // Règlement : les abonnés paient moitié prix, et aucune pénalité ne dépasse dix euros.
    private static final BigDecimal PLAFOND = new BigDecimal("10.00");
    private static final BigDecimal REDUCTION_ABONNE = new BigDecimal("0.5");

    public BigDecimal penalite(Emprunt emprunt) {
        TypeDocument type = emprunt.type();
        BigDecimal brute = type.tarifJournalier()
                .multiply(BigDecimal.valueOf(type.joursDeRetard(emprunt.joursEcoules())));
        BigDecimal due = emprunt.abonne() ? brute.multiply(REDUCTION_ABONNE) : brute;
        return due.min(PLAFOND).setScale(2, RoundingMode.HALF_UP);
    }
}
