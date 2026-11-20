package org.sebsy.caisse;

/** Chaque article décide s'il peut être vendu à un client : la caisse n'a plus à connaître les types. */
public interface Article {

    String libelle();

    boolean accepte(Client client);
}
