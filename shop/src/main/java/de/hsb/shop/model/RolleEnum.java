/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.model;

/**
 *
 * @author fiedler
 */
public enum RolleEnum {
    ADMIN("Admin"), MEMBER("Member");

    private final String label;

    private RolleEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
