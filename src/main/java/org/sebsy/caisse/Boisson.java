package org.sebsy.caisse;

public record Boisson(String libelle, boolean alcoolisee) implements Article {

    @Override
    public boolean accepte(Client client) {
        return !alcoolisee || client.majeur();
    }
}
