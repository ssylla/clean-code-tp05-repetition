package org.sebsy.caisse;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class CaisseTest {

    private final Caisse caisse = new Caisse();
    private final Client mineur = new Client(16);
    private final Client majeur = new Client(30);

    @Test
    void cidreRefuseAUnMineur() {
        caisse.scanner(new Boisson("Cidre", true), mineur);
        assertThat(caisse.panier()).isEmpty();
    }

    @Test
    void cidreAccepteAUnMajeur() {
        caisse.scanner(new Boisson("Cidre", true), majeur);
        assertThat(caisse.panier()).containsExactly("Cidre");
    }

    @Test
    void livreToujoursAccepte() {
        caisse.scanner(new Livre("Dune"), mineur);
        assertThat(caisse.panier()).containsExactly("Dune");
    }

    @Test
    void carteCadeauPlafonneeA150() {
        caisse.scanner(new CarteCadeau("Carte 150", new BigDecimal("150")), majeur);
        caisse.scanner(new CarteCadeau("Carte 200", new BigDecimal("200")), majeur);
        assertThat(caisse.panier()).containsExactly("Carte 150");
    }

    @Test
    void jeuInterditSousSonAgeMinimum() {
        caisse.scanner(new Jeu("Dixit", 8), new Client(6));
        caisse.scanner(new Jeu("Dixit", 8), new Client(9));
        assertThat(caisse.panier()).containsExactly("Dixit");
    }
}
