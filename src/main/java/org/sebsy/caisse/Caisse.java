package org.sebsy.caisse;

import java.util.ArrayList;
import java.util.List;

public class Caisse {

    private final List<String> panier = new ArrayList<>();

    public void scanner(Article article, Client client) {
        if (article.accepte(client)) {
            panier.add(article.libelle());
        }
    }

    public List<String> panier() {
        return List.copyOf(panier);
    }
}
