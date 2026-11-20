package org.sebsy.biblio;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class MediathequeTest {

    private final Mediatheque mediatheque = new Mediatheque();

    @Test
    void totalDuAdditionneLesEmpruntsDeLAdherent() {
        mediatheque.enregistrer(new Emprunt(TypeDocument.DVD, "Alien", "Grace", 10, false));
        mediatheque.enregistrer(new Emprunt(TypeDocument.LIVRE, "Dune", "Grace", 31, false));
        assertThat(mediatheque.totalDu("Grace")).isEqualByComparingTo("3.00");
    }

    @Test
    void enregistrerNAffichePlusRien() {
        PrintStream sortie = System.out;
        ByteArrayOutputStream capture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capture));
        try {
            mediatheque.enregistrer(new Emprunt(TypeDocument.BD, "Blacksad", "Linus", 3, false));
        } finally {
            System.setOut(sortie);
        }
        assertThat(capture.toString()).isEmpty();
    }
}
