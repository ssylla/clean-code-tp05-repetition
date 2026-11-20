package org.sebsy.biblio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** Emprunts de la médiathèque et pénalités de retard dues. */
public class Mediatheque {

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
        if (statut == "VIP") {
            return "OK";
        }
        BigDecimal du = totalDu(adherent);
        if (du.compareTo(BigDecimal.ONE) > 0) {
            System.out.println("Relance envoyée à " + adherent);
            return "RELANCE";
        }
        return "OK";
    }
}
