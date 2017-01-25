/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.controller;

import de.hsb.shop.model.Member;
import de.hsb.shop.model.RolleEnum;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author fiedler
 */
@ManagedBean
@SessionScoped
public class MemberHandler implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private DataModel<Member> members;
    private Member merkeMember = new Member();

    @PostConstruct
    public void init() {
        try {
//            utx.begin();
//            em.persist(new Member("admin", "admin", "admin", "admin", "admin",
//                    new GregorianCalendar(1970, 0, 2).getTime(), RolleEnum.ADMIN));
            members = new ListDataModel<>();
            members.setWrappedData(em.createNamedQuery("SelectMember").getResultList());
//            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(MemberHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
