/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.utils;

/**
 *
 * @author Tobi
 */
public class Utils {
    
    /**
     * Rundet auf 2 Stellen nach dem Komma
     * @param value
     * @param places
     * @return 
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
