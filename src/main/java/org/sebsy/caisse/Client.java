package org.sebsy.caisse;

public record Client(int age) {

    private static final int MAJORITE = 18;

    public boolean majeur() {
        return age >= MAJORITE;
    }
}
