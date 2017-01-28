/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.controller;

import de.hsb.shop.model.Member;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author fiedler
 */
@ManagedBean
@SessionScoped
public class MemberHandler implements Serializable {

    @ManagedProperty(value = "#{loginHandler}")
    private LoginHandler loginhandler;

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private Member merkeMember = new Member();

    @PostConstruct
    public void init() {}

    public String neu() {
        merkeMember = new Member();
        return "register.xhtml?faces-redirect=true";
    }

    public String speichern() {
        try {
            utx.begin();
            merkeMember = em.merge(merkeMember);
            em.persist(merkeMember);
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(MemberHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index.xhtml?faces-redirect=true";
    }

    public String edit() {
        Query query = em.createQuery("select m from Member m "
                + "where m.username = :username");
        query.setParameter("username", loginhandler.getMember().getUsername());
        merkeMember = (Member)query.getSingleResult();
        return "profil.xhtml?faces-redirect=true";
    }

    public void saveProfileChanges() {

        try {
            utx.begin();
            merkeMember = em.merge(merkeMember);
            em.persist(merkeMember);
            utx.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Änderungen gespeichert"));
        } catch (Exception ex) {
            Logger.getLogger(MemberHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void info() {

    }

    public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Sie benötigen mindestens eine Lieferadresse um eine Bestellung zu tätigen!"));
    }

    public void warn1() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Sie benötigen mindestens eine Zahlungsart um eine Bestellung zu tätigen!"));
    }

    public Member getMerkeMember() {
        return merkeMember;
    }

    public void setMerkeMember(Member merkeMember) {
        this.merkeMember = merkeMember;
    }

    public LoginHandler getLoginhandler() {
        return loginhandler;
    }

    public void setLoginhandler(LoginHandler loginhandler) {
        this.loginhandler = loginhandler;
    }
}
