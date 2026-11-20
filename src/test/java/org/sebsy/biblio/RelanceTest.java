package org.sebsy.biblio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RelanceTest {

    private final Mediatheque mediatheque = new Mediatheque();

    @Test
    void vipJamaisRelance() {
        mediatheque.enregistrer(new Emprunt(TypeDocument.DVD, "Alien", "Linus", 30, false));
        String saisi = new StringBuilder("VIP").toString();
        assertThat(mediatheque.relance("Linus", saisi)).isEqualTo("OK");
    }

    @Test
    void adherentQuiDoitPlusDUnEuroRelance() {
        mediatheque.enregistrer(new Emprunt(TypeDocument.DVD, "Alien", "Linus", 30, false));
        assertThat(mediatheque.relance("Linus", "STANDARD")).isEqualTo("RELANCE");
    }

    @Test
    void adherentAJourPasRelance() {
        assertThat(mediatheque.relance("Ada", "STANDARD")).isEqualTo("OK");
    }
}
