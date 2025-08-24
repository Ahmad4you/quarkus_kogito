package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextProcessor {
    public String toUpper(String eingabe) {

        return eingabe.toUpperCase();
    }

}
