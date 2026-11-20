package org.sebsy.biblio;

import java.lang.System.Logger.Level;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** Emprunts de la médiathèque et pénalités de retard dues. */
public class Mediatheque {

    private static final System.Logger LOG = System.getLogger(Mediatheque.class.getName());
    private static final String STATUT_VIP = "VIP";
    // Règlement : on ne relance qu'au-delà d'un euro dû.
    private static final BigDecimal SEUIL_DE_RELANCE = BigDecimal.ONE;

    // Une liste par instance : les tests ne partagent plus d'emprunts par un champ statique.
    private final List<Emprunt> emprunts = new ArrayList<>();

    private final CalculateurPenalite calculateur = new CalculateurPenalite();

    public void enregistrer(Emprunt emprunt) {
        emprunts.add(emprunt);
    }

    public BigDecimal totalDu(String adherent) {
        return emprunts.stream()
                .filter(emprunt -> emprunt.adherent().equals(adherent))
                .map(calculateur::penalite)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String relance(String adherent, String statut) {
        if (STATUT_VIP.equals(statut)) {
            return "OK";
        }
        if (totalDu(adherent).compareTo(SEUIL_DE_RELANCE) > 0) {
            LOG.log(Level.INFO, () -> "Relance envoyée à " + adherent);
            return "RELANCE";
        }
        return "OK";
    }
}
