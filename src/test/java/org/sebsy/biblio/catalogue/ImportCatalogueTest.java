package org.sebsy.biblio.catalogue;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class ImportCatalogueTest {

    private final ImportCatalogue importCatalogue = new ImportCatalogue();

    @Test
    void garderLesTroisTypesConnus() {
        List<String[]> lignes = importCatalogue.lire("src/test/resources/catalogue.csv");
        assertThat(lignes).extracting(ligne -> ligne[1]).containsExactly("Dune", "Blacksad", "Alien");
    }

    @Test
    void resumerUnTitreParLigne() {
        List<String[]> lignes = importCatalogue.lire("src/test/resources/catalogue.csv");
        assertThat(importCatalogue.resume(lignes)).isEqualTo("Dune (LIVRE)\nBlacksad (BD)\nAlien (DVD)\n");
    }

    @Test
    void fichierAbsentDonneUneListeVide() {
        assertThat(importCatalogue.lire("introuvable.csv")).isEmpty();
    }
}
