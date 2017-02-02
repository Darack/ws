/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.controller;

import de.hsb.shop.model.Adress;
import de.hsb.shop.model.Member;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author fiedler
 */
@ManagedBean
@ViewScoped
public class ProfileHandler implements Serializable {

    @ManagedProperty(value = "#{loginHandler}")
    private LoginHandler loginhandler;

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private Member member;

    private Boolean addAdress;

    private String street;

    private String city;

    private String zipCode;

    @PostConstruct
    public void init() {
        if (!loginhandler.isLogged()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("startpage.xhtml?faces-redirect=true");
            } catch (IOException ex) {
            }
        }
        member = loginhandler.getMember();
    }

    public void update() {
        try {
            utx.begin();
            member = em.merge(member);
            em.persist(member);
            utx.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Änderungen gespeichert"));
        } catch (Exception ex) {
            Logger.getLogger(ProfileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveAdress() {
        try {
            utx.begin();
            Adress a = new Adress(street, zipCode, city);
            em.persist(a);
            member.getAdressList().add(a);
            member = em.merge(member);
            em.persist(member);
            utx.commit();
            addAdress = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Änderungen gespeichert"));
        } catch (Exception ex) {
            Logger.getLogger(ProfileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LoginHandler getLoginhandler() {
        return loginhandler;
    }

    public void setLoginhandler(LoginHandler loginhandler) {
        this.loginhandler = loginhandler;
    }

    public Boolean getAddAdress() {
        return addAdress;
    }

    public void setAddAdress(Boolean addAdress) {
        this.addAdress = addAdress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
