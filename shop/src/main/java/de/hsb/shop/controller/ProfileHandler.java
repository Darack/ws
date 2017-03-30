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
import de.hsb.shop.model.Creditcard;
import de.hsb.shop.model.Creditcardtype;
import de.hsb.shop.model.Member;

/**
 * Verwaltet die Profile.xhtml
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

	private Adress tmpAdress;
	private Creditcard tmpCreditcard;

	/**
	 * Holt den zu bearbeitenden Member aus dem Session-Handler
	 */
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

	/**
	 * Speichert die Änderungen der Daten des Members in die Datenbank und
	 * navigiert anschließend zur profile.xhtml
	 * 
	 * @return "profil.xhtml?faces-redirect=true"
	 */
	public String update() {
		try {
			utx.begin();
			member = em.merge(member);
			em.persist(member);
			utx.commit();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Änderungen gespeichert"));
		} catch (Exception ex) {
			Logger.getLogger(ProfileHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "profil.xhtml?faces-redirect=true";
	}

	public void createAdress() {
		tmpAdress = new Adress();
		addAdress = true;
	}

	public void editAdress(Adress adress) {
		tmpAdress = adress;
		addAdress = true;
	}

	public void createCreditCard() {
		tmpCreditcard = new Creditcard();
		addCreditCard = true;
	}

	public void editCreditCard(Creditcard creditCard) {
		tmpCreditcard = creditCard;
		addCreditCard = true;
	}

	/**
	 * Speichert die Adresse in der Datenbank und fügt sie dem Member hinzu
	 * 
	 * @return "profil.xhtml?faces-redirect=true"
	 */
	public String saveAdress() {
		try {
			utx.begin();
			if (!member.getAdressList().contains(tmpAdress)) {
				member.getAdressList().add(tmpAdress);
			}
			member = em.merge(member);
			em.persist(member);
			utx.commit();
			addAdress = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Änderungen gespeichert"));
		} catch (Exception ex) {
			Logger.getLogger(ProfileHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "profil.xhtml?faces-redirect=true";
	}

	/**
	 * Löscht die übergebene Adresse aus der Datenbank
	 * 
	 * @param adress
	 * @return "profil.xhtml?faces-redirect=true"
	 */
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

	/**
	 * Speichert die Kreditkarte in der Datenbank und fügt sie dem Member hinzu
	 * 
	 * @return "profil.xhtml?faces-redirect=true"
	 */
	public String saveCreditcard() {
		try {
			member.setCreditcard(tmpCreditcard);
			utx.begin();
			member = em.merge(member);
			tmpCreditcard = em.merge(tmpCreditcard);
			em.persist(member);
			// em.persist(tmpCreditcard);
			utx.commit();
			addCreditCard = false;
		} catch (Exception ex) {
			Logger.getLogger(ProfileHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "profil.xhtml?faces-redirect=true";
	}

	public Creditcardtype[] getCreditcardtypeValues() {
		return Creditcardtype.values();
	}

	public SessionHandler getSessionhandler() {
		return sessionhandler;
	}

	public void setSessionhandler(SessionHandler sessionhandler) {
		this.sessionhandler = sessionhandler;
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

	public Boolean getAddCreditCard() {
		return addCreditCard;
	}

	public void setAddCreditCard(Boolean addCreditCard) {
		this.addCreditCard = addCreditCard;
	}

	public Adress getTmpAdress() {
		return tmpAdress;
	}

	public void setTmpAdress(Adress tmpAdress) {
		this.tmpAdress = tmpAdress;
	}

	public Creditcard getTmpCreditcard() {
		return tmpCreditcard;
	}

	public void setTmpCreditcard(Creditcard tmpCreditcard) {
		this.tmpCreditcard = tmpCreditcard;
	}

}
