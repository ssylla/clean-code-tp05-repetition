package org.sebsy.caisse;

/** Ajouté sans toucher à Caisse ni à Article : le principe ouvert/fermé, vérifié par CaisseTest. */
public record Jeu(String libelle, int ageMinimum) implements Article {

    @Override
    public boolean accepte(Client client) {
        return client.age() >= ageMinimum;
    }
}
