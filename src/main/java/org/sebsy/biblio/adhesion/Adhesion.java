package org.sebsy.biblio.adhesion;

/** Ce dont dépend la cotisation. Remplace six paramètres positionnels (Introduce Parameter Object). */
public record Adhesion(int age, int quotientFamilial, boolean etudiant, int enfants, Integer id, Integer idParrain) {

    boolean parraineParUnAutre() {
        // equals et non == : Java ne met en cache les Integer qu'entre -128 et 127.
        return idParrain != null && !idParrain.equals(id);
    }
}
