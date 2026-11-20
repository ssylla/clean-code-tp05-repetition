package org.sebsy.biblio.catalogue;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/** Lit l'export du catalogue : un document par ligne, séparateur point-virgule, en-tête en première ligne. */
public class ImportCatalogue {

    private static final Logger LOG = System.getLogger(ImportCatalogue.class.getName());
    private static final Set<String> TYPES_CONNUS = Set.of("LIVRE", "BD", "DVD");
    private static final String SEPARATEUR = ";";
    private static final int NOMBRE_DE_COLONNES = 4;
    private static final int COLONNE_TITRE = 1;
    private static final int COLONNE_TYPE = 2;

    public List<String[]> lire(String chemin) {
        List<String> contenu;
        try {
            // readAllLines ouvre et ferme le fichier : le FileReader du legacy n'était jamais fermé.
            contenu = Files.readAllLines(Path.of(chemin));
        } catch (IOException e) {
            // Un catalogue absent est un catalogue vide ; à l'appelant de dire si c'est une erreur.
            LOG.log(Level.WARNING, () -> "Catalogue illisible : " + chemin, e);
            return List.of();
        }
        List<String[]> documents = new ArrayList<>();
        for (int numero = 2; numero <= contenu.size(); numero++) {
            String[] champs = contenu.get(numero - 1).split(SEPARATEUR);
            if (champs.length != NOMBRE_DE_COLONNES) {
                continue;
            }
            if (TYPES_CONNUS.contains(champs[COLONNE_TYPE])) {
                documents.add(champs);
            } else {
                int ligne = numero;
                LOG.log(Level.WARNING, () -> "Type inconnu ligne " + ligne);
            }
        }
        return documents;
    }

    public String resume(List<String[]> documents) {
        StringBuilder resume = new StringBuilder();
        for (String[] champs : documents) {
            resume.append(champs[COLONNE_TITRE]).append(" (").append(champs[COLONNE_TYPE]).append(")\n");
        }
        return resume.toString();
    }
}
