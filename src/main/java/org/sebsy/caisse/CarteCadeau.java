package org.sebsy.caisse;

import java.math.BigDecimal;

public record CarteCadeau(String libelle, BigDecimal montant) implements Article {

    private static final BigDecimal MONTANT_MAX = new BigDecimal("150");

    @Override
    public boolean accepte(Client client) {
        return montant.compareTo(MONTANT_MAX) <= 0;
    }
}
