/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.utils;

public enum Anrede {
    HERR("Herr"), FRAU("Frau"), FIRMA("Firma");
    private final String label;

    private Anrede(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }   
}

