package org.sebsy.biblio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CalculateurPenaliteTest {

    private final CalculateurPenalite calculateur = new CalculateurPenalite();

    @Test
    void livreRenduATemps() {
        Emprunt emprunt = new Emprunt(TypeDocument.LIVRE, "Dune", "Ada", 21, false);
        assertThat(calculateur.penalite(emprunt)).isEqualByComparingTo("0.00");
    }

    @Test
    void bdSixJoursDeRetard() {
        Emprunt emprunt = new Emprunt(TypeDocument.BD, "Blacksad", "Ada", 20, false);
        assertThat(calculateur.penalite(emprunt)).isEqualByComparingTo("0.90");
    }

    @Test
    void bdSaisieDansUnFormulaire() {
        TypeDocument saisi = TypeDocument.valueOf(new StringBuilder("BD").toString());
        Emprunt emprunt = new Emprunt(saisi, "Blacksad", "Ada", 20, false);
        assertThat(calculateur.penalite(emprunt)).isEqualByComparingTo("0.90");
    }

    @Test
    void abonnePaieMoitie() {
        Emprunt emprunt = new Emprunt(TypeDocument.DVD, "Alien", "Ada", 10, true);
        assertThat(calculateur.penalite(emprunt)).isEqualByComparingTo("0.75");
    }

    @Test
    void plafondDeDixEuros() {
        Emprunt emprunt = new Emprunt(TypeDocument.DVD, "Alien", "Ada", 60, false);
        assertThat(calculateur.penalite(emprunt)).isEqualByComparingTo("10.00");
    }
}
