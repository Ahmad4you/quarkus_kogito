package org.acme;

import org.acme.models.Order;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextProcessor {

    @PostConstruct
    public void init() {
        Order order = new Order();
        order.setCustomerName("Ahmad_");
    }

    public String toUpper(String eingabe) {

        return eingabe.toUpperCase();
    }

    public String toLower(String eingabe) {
        if (eingabe.length() < 5) {
            System.out.println("less than 5");
        }
        return eingabe.toLowerCase();
    }

}
