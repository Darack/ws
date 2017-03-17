/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.controller;

import de.hsb.shop.model.Product;
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

    @ManagedProperty(value = "#{loginHandler}")
    private LoginHandler loginhandler;
    private Collection<shoppingCartSummary> shList;
    private boolean finished;

    @PostConstruct
    public void init() {
        finished = false;
        HashMap<Integer,shoppingCartSummary> shMap = new HashMap();
        for(Product p : loginhandler.getWarenkorb()){        
            if(shMap.containsKey(p.getId())){
                shMap.get(p.getId()).increment();
            }else{
                shMap.put(p.getId(), new shoppingCartSummary(p));
            }
        }
        shList = shMap.values();
    }
    
    public void finishShopping(){
        finished = true;
        loginhandler.getWarenkorb().clear();
    }

    public LoginHandler getLoginhandler() {
        return loginhandler;
    }

    public void setLoginhandler(LoginHandler loginhandler) {
        this.loginhandler = loginhandler;
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


}
