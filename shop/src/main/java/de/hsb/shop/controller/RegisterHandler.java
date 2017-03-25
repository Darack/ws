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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import de.hsb.shop.model.Member;
import de.hsb.shop.model.Role;

/**
 *
 * @author fiedler
 */
@ManagedBean
@ViewScoped
public class RegisterHandler implements Serializable {

    @ManagedProperty(value = "#{sessionHandler}")
    private SessionHandler sessionHandler;

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private Member member;

    @PostConstruct
    public void init() {
        if (sessionHandler.isLogged()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("startpage.xhtml?faces-redirect=true");
            } catch (IOException ex) {
            }
        }
        member = new Member();
        Query query = em.createQuery("select r from Role r where r.label = 'Member'");
        Role r = (Role) query.getSingleResult();
        member.setRole(r);
    }

    public String saveAndLogin() {
        try {
            utx.begin();
            member = em.merge(member);
            em.persist(member);
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(RegisterHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        sessionHandler.setUsername(member.getUsername());
        sessionHandler.setPasswort(member.getPasswort());
        return sessionHandler.login();
    }

    public Member getMerkeMember() {
        return member;
    }

    public void setMerkeMember(Member merkeMember) {
        this.member = merkeMember;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public SessionHandler getSessionHandler() {
        return sessionHandler;
    }

    public void setSessionHandler(SessionHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }
}
