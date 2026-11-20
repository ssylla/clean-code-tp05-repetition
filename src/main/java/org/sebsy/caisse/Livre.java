package org.sebsy.caisse;

public record Livre(String libelle) implements Article {

    @Override
    public boolean accepte(Client client) {
        return true;
    }
}
