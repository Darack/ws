/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.controller;

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

import de.hsb.shop.model.Adress;
import de.hsb.shop.model.Kreditkarte;
import de.hsb.shop.model.Kreditkartentyp;
import de.hsb.shop.model.Member;

/**
 *
 * @author fiedler
 */
@ManagedBean
@ViewScoped
public class ProfileHandler implements Serializable {

    @ManagedProperty(value = "#{sessionHandler}")
    private SessionHandler sessionhandler;

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private Member member;

    private Boolean addAdress;
    private Boolean addCreditCard;

    private Adress merkeAdresse;
    private Kreditkarte merkeKreditkarte;

    @PostConstruct
    public void init() {
        if (!sessionhandler.isLogged()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("startpage.xhtml?faces-redirect=true");
            } catch (IOException ex) {
            }
        }
        member = sessionhandler.getMember();
    }

    public String update() {
        try {
            utx.begin();
            member = em.merge(member);
            em.persist(member);
            utx.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Änderungen gespeichert"));
        } catch (Exception ex) {
            Logger.getLogger(ProfileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "profil.xhtml?faces-redirect=true";
    }

    public void createAdress() {
        merkeAdresse = new Adress();
        addAdress = true;
    }

    public void editAdress(Adress adress) {
        merkeAdresse = adress;
        addAdress = true;
    }

    public void createCreditCard() {
        merkeKreditkarte = new Kreditkarte();
        addCreditCard = true;
    }

    public void editCreditCard(Kreditkarte creditCard) {
        merkeKreditkarte = creditCard;
        addCreditCard = true;
    }

    public String saveAdress() {
        try {
            utx.begin();
            if (!member.getAdressList().contains(merkeAdresse)) {
                member.getAdressList().add(merkeAdresse);
            }
            member = em.merge(member);
            em.persist(member);
            utx.commit();
            addAdress = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Änderungen gespeichert"));
        } catch (Exception ex) {
            Logger.getLogger(ProfileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "profil.xhtml?faces-redirect=true";
    }

    public String deleteAdress(Adress adress) {
        try {
            utx.begin();
            em.remove(em.merge(adress));
            member.getAdressList().remove(adress);
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(ProfileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "profil.xhtml?faces-redirect=true";
    }

    public String kreditkarteSpeichern() {
        try {
            member.setKreditkarte(merkeKreditkarte);
            utx.begin();
            member = em.merge(member);
            merkeKreditkarte = em.merge(merkeKreditkarte);
            em.persist(member);
            em.persist(merkeKreditkarte);
            utx.commit();
            addCreditCard = false;
        } catch (Exception ex) {
            Logger.getLogger(ProfileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "profil.xhtml?faces-redirect=true";
    }

    public Kreditkartentyp[] getKreditkartentypValues() {
        return Kreditkartentyp.values();
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Boolean getAddAdress() {
        return addAdress;
    }

    public void setAddAdress(Boolean addAdress) {
        this.addAdress = addAdress;
    }

    public Adress getMerkeAdresse() {
        return merkeAdresse;
    }

    public void setMerkeAdresse(Adress merkeAdresse) {
        this.merkeAdresse = merkeAdresse;
    }

    public Kreditkarte getMerkeKreditkarte() {
        return merkeKreditkarte;
    }

    public void setMerkeKreditkarte(Kreditkarte merkeKreditkarte) {
        this.merkeKreditkarte = merkeKreditkarte;
    }

    public Boolean getAddCreditCard() {
        return addCreditCard;
    }

    public void setAddCreditCard(Boolean addCreditCard) {
        this.addCreditCard = addCreditCard;
    }

    public SessionHandler getSessionhandler() {
        return sessionhandler;
    }

    public void setSessionhandler(SessionHandler sessionhandler) {
        this.sessionhandler = sessionhandler;
    }
}
