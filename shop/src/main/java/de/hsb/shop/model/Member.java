package de.hsb.shop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hsb.shop.controller.LoginHandler;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamedQuery(name = "SelectMember", query = "Select m from Member m")
@Entity
public class Member implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(LoginHandler.class);
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    private String username;
    
    private String email;
    
    private String passwort;
    
    private String nachname;
    
    private String vorname;

    private Boolean newsletter;

    @JoinColumn(name = "role", referencedColumnName = "id")
    @ManyToOne
    private Role role;

    @Temporal(TemporalType.DATE)
    private Date geburtsdatum;

    public Member() {
    }

    public Member(String username, String vorname, String nachname, String passwort, String email, Date geburtsdatum) {
        super();
        this.username = username;
        this.vorname = vorname;
        this.nachname = nachname;
        this.passwort = passwort;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Boolean getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger aLogger) {
        logger = aLogger;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
