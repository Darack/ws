/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.utils;

import de.hsb.shop.model.Product;

/**
 * Diese Klasse beinhaltet ein Produkt, die Anzahl des Produkts und den Gesamtpreis (Produkt * Preis)
 * @author Tobi
 */

public class shoppingCartSummary {

    Product product;

    Integer count;

    private double wholePrice;
    
    /**
     * Initialisiert das Produkt, setzt Anzahl auf 1 und den Preis auf den Einzelpreis.
     * @param product 
     */
    public shoppingCartSummary(Product product) {
        this.product = product;
        this.count = 1;
        if (product.getPrice() != null) {
            this.wholePrice = product.getPrice();
        } else {
            this.wholePrice = 0;
        }
        wholePrice = Utils.round(wholePrice,2);
    }

    /**
     * Erhöht die Anzahl um 1 und erhöht den Gesamtpreis um einmal den Einzelpreis
     */
    public void increment() {
        ++count;
        if (product.getPrice() != null) {
            wholePrice += product.getPrice();
        }
        wholePrice = Utils.round(wholePrice,2);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getWholePrice() {
        return wholePrice;
    }

    public void setWholePrice(double wholePrice) {
        this.wholePrice = wholePrice;
    }
}
