/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.controller;

import de.hsb.shop.model.Product;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProductHandler implements Serializable {

    @ManagedProperty(value = "#{loginHandler}")
    private LoginHandler loginhandler;
    private List<Product> productList;
    
    @PostConstruct
    public void init() {

    }

    public LoginHandler getLoginhandler() {
        return loginhandler;
    }

    public void setLoginhandler(LoginHandler loginhandler) {
        this.loginhandler = loginhandler;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}
