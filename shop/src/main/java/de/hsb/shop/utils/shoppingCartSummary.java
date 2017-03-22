/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.utils;

import de.hsb.shop.controller.shoppingCartController;
import de.hsb.shop.model.Product;

public class shoppingCartSummary {

    Product product;

    Integer count;

    private double wholePrice;

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
