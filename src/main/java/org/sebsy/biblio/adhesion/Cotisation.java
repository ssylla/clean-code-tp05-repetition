package org.sebsy.biblio.adhesion;

/** Cotisation annuelle, selon la grille tarifaire de la médiathèque. */
public class Cotisation {

    private static final int AGE_MAJORITE = 18;
    private static final int AGE_SENIOR = 65;
    private static final double TARIF_PLEIN = 30;
    private static final double TARIF_ETUDIANT = 15;
    private static final double TARIF_SENIOR = 20;
    private static final int SEUIL_QUOTIENT_FAMILIAL = 600;
    private static final double REDUCTION_QUOTIENT_FAMILIAL = 0.5;
    private static final double SUPPLEMENT_PAR_ENFANT = 10;
    private static final int ENFANTS_FACTURES_MAX = 3;
    private static final double REMISE_PARRAINAGE = 5;

    public double montant(Adhesion adhesion) {
        double montant = tarifIndividuel(adhesion)
                + supplementFamille(adhesion.enfants())
                - remiseParrainage(adhesion);
        return Math.max(0, montant);
    }

    private double tarifIndividuel(Adhesion adhesion) {
        if (adhesion.age() < AGE_MAJORITE) {
            return 0;
        }
        double tarif = tarifDeBase(adhesion);
        return adhesion.quotientFamilial() < SEUIL_QUOTIENT_FAMILIAL ? tarif * REDUCTION_QUOTIENT_FAMILIAL : tarif;
    }

    private double tarifDeBase(Adhesion adhesion) {
        if (adhesion.etudiant()) {
            return TARIF_ETUDIANT;
        }
        if (adhesion.age() >= AGE_SENIOR) {
            return TARIF_SENIOR;
        }
        return TARIF_PLEIN;
    }

    private double supplementFamille(int enfants) {
        return Math.clamp(enfants, 0, ENFANTS_FACTURES_MAX) * SUPPLEMENT_PAR_ENFANT;
    }

    private double remiseParrainage(Adhesion adhesion) {
        return adhesion.parraineParUnAutre() ? REMISE_PARRAINAGE : 0;
    }
}
