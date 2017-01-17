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
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
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
public class LoginHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String passwort;
    private Member member;
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    @PostConstruct
    public void init() {
        try {
            utx.begin();
            em.persist(new Member("admin", "admin", "admin",
                  "admin", "admin", new GregorianCalendar(1970, 0, 2).getTime()));
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String login() {
        Query query = em.createQuery("select m from Member m "
                + "where m.username = :username and m.passwort = :passwort "
                + "and m.rolle = " + RolleEnum.ADMIN.ordinal());
        query.setParameter("username", username);
        query.setParameter("passwort", passwort);
        System.out.println(username + " " + passwort);
        List<Member> members = query.getResultList();
        if (members.size() == 1) {
            member = members.get(0);
            return "/index.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

    public void checkLoggedIn(ComponentSystemEvent cse) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (member == null) {
            context.getApplication().getNavigationHandler().
                    handleNavigation(context, null,
                            "/index.xhtml?faces-redirect=true");
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance()
                .getExternalContext().invalidateSession();
        return "/index.xhtml?faces -redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public UserTransaction getUtx() {
        return utx;
    }

    public void setUtx(UserTransaction utx) {
        this.utx = utx;
    }
}
