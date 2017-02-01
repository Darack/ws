/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.controller;

import de.hsb.shop.model.Member;
import de.hsb.shop.model.Role;
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

/**
 *
 * @author fiedler
 */
@ManagedBean
@ViewScoped
public class RegisterHandler implements Serializable {

    @ManagedProperty(value = "#{loginHandler}")
    private LoginHandler loginhandler;

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private Member member;

    @PostConstruct
    public void init() {
        if (loginhandler.isLogged()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("startpage.xhtml?faces-redirect=true");
            } catch (IOException ex) {
            }
        }
        member = new Member();

        Query query = em.createQuery("select r from Role r where r.label = 'Member'");
        System.out.println(query);
        Role r = (Role) query.getSingleResult();
        member.setRole(r);
    }

    public String save() {
        try {
            utx.begin();
            member = em.merge(member);
            em.persist(member);
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(RegisterHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "startpage.xhtml?faces-redirect=true";
    }

    public Member getMerkeMember() {
        return member;
    }

    public void setMerkeMember(Member merkeMember) {
        this.member = merkeMember;
    }

    public LoginHandler getLoginhandler() {
        return loginhandler;
    }

    public void setLoginhandler(LoginHandler loginhandler) {
        this.loginhandler = loginhandler;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
