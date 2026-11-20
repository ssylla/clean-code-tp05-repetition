package org.sebsy.exercices;

import java.time.LocalDate;

/** Une entreprise telle que le registre la connaît. Champs privés : on passe par les accesseurs. */
public class Entreprise {

    public static final int CAPITAL_MAX = 3_000_000;

    private final String siret;
    private final String nom;
    private final String adresse;
    private final LocalDate dateCreation;

    public Entreprise(String siret, String nom, String adresse, LocalDate dateCreation) {
        this.siret = siret;
        this.nom = nom;
        this.adresse = adresse;
        this.dateCreation = dateCreation;
    }

    // Le SIRET est un identifiant de 14 chiffres, pas un nombre : un int en tient 10 au plus.
    public String siret() {
        return siret;
    }

    public String nom() {
        return nom;
    }

    public String adresse() {
        return adresse;
    }

    public LocalDate dateCreation() {
        return dateCreation;
    }

    public String statut() {
        return nom + " (" + siret + ")";
    }
}
