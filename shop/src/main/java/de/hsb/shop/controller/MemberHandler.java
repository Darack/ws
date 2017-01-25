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
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author fiedler
 */
@ManagedBean
@ViewScoped
public class MemberHandler implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private DataModel<Member> members;
    private Member merkeMember = new Member();

    public String neu() {
        merkeMember = new Member();
        return "register.xhtml?faces-redirect=true";
    }

    public String speichern() {
        try {
            utx.begin();
            merkeMember = em.merge(merkeMember);
            em.persist(merkeMember);
            members.setWrappedData(em.createNamedQuery("SelectMember").getResultList());
            utx.commit();          
        } catch (Exception ex) {
            Logger.getLogger(MemberHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index.xhtml?faces-redirect=true";
    }

    public Member getMerkeMember() {
        return merkeMember;
    }

    public void setMerkeMember(Member merkeMember) {
        this.merkeMember = merkeMember;
    }
}
