package org.sebsy.biblio.adhesion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests de caractérisation, écrits contre le calcul() legacy avant toute modification, puis
 * réécrits par l'IDE pendant le refactoring. Seule la ligne de l'id 200 a changé, au commit fix.
 */
class CotisationTest {

    private final Cotisation cotisation = new Cotisation();

    @ParameterizedTest(name = "{0}")
    @CsvSource(nullValues = "null", textBlock = """
            adulte sans parrain,             30, 800, false, 0, 42,  null, 30.0
            autoparrainage id 42,            30, 800, false, 0, 42,  42,   30.0
            autoparrainage id 200,           30, 800, false, 0, 200, 200,  30.0
            parrain distinct,                30, 800, false, 0, 200, 7,    25.0
            mineur parraine avec 2 enfants,  12, 300, false, 2, 5,   9,    15.0
            etudiant au quotient bas,        22, 500, true,  0, 5,   null, 7.5
            senior avec 5 enfants,           70, 800, false, 5, 5,   null, 50.0
            mineur seul parraine,            12, 800, false, 0, 5,   9,    0.0
            senior au quotient bas,          70, 500, false, 1, 5,   null, 20.0
            """)
    void montant(String cas, int age, int qf, boolean etudiant, int enfants, Integer id, Integer parrain, double attendu) {
        assertThat(cotisation.montant(new Adhesion(age, qf, etudiant, enfants, id, parrain))).isEqualTo(attendu);
    }
}
