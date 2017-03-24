/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.controller;

import de.hsb.shop.model.Product;
import de.hsb.shop.utils.Utils;
import de.hsb.shop.utils.shoppingCartSummary;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class shoppingCartController implements Serializable {

    @ManagedProperty(value = "#{sessionHandler}")
    private SessionHandler sessionHandler;
    private Collection<shoppingCartSummary> shList;
    private boolean finished;
    private double articleSummaryPrice;
    private double finalPrice;
    private double deliveryCosts = 3.99;

    @PostConstruct
    public void init() {
        finished = false;
        HashMap<Integer, shoppingCartSummary> shMap = new HashMap();
        for (Product p : sessionHandler.getWarenkorb()) {
            if (shMap.containsKey(p.getId())) {
                shMap.get(p.getId()).increment();
            } else {
                shMap.put(p.getId(), new shoppingCartSummary(p));
            }
        }
        shList = shMap.values();
        articleSummaryPrice = 0;
        for (shoppingCartSummary s : shList) {
            articleSummaryPrice += s.getWholePrice();
        }
        articleSummaryPrice = Utils.round(articleSummaryPrice,2);
        finalPrice = Utils.round(articleSummaryPrice+deliveryCosts,2);
    }

    public void finishShopping() {
        finished = true;
        sessionHandler.getWarenkorb().clear();
    }

    public Collection<shoppingCartSummary> getShList() {
        return shList;
    }

    public void setShList(Collection<shoppingCartSummary> shList) {
        this.shList = shList;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public double getWholePrice() {
        return articleSummaryPrice;
    }

    public void setWholePrice(double wholePrice) {
        this.articleSummaryPrice = wholePrice;
    }

    public SessionHandler getSessionHandler() {
        return sessionHandler;
    }

    public void setSessionHandler(SessionHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

}
