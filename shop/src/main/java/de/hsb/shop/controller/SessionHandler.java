/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.controller;

<<<<<<< HEAD
=======
import de.hsb.shop.model.Adress;
import de.hsb.shop.model.Kreditkarte;
import de.hsb.shop.model.Kreditkartentyp;
import de.hsb.shop.model.Member;
import de.hsb.shop.model.Product;
import de.hsb.shop.model.ProductCategory;
import de.hsb.shop.model.Role;
import de.hsb.shop.utils.Anrede;
>>>>>>> branch 'master' of ssh://git@github.com/Darack/ws.git
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hsb.shop.model.Adress;
import de.hsb.shop.model.Member;
import de.hsb.shop.model.Product;
import de.hsb.shop.model.ProductCategory;
import de.hsb.shop.model.Role;
import de.hsb.shop.utils.Anrede;

/**
 *
 * @author fiedler
 */
@ManagedBean(name = "sessionHandler")
@SessionScoped
public class SessionHandler implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(SessionHandler.class);
    private static final long serialVersionUID = 1L;
    private String username;
    private String passwort;
    private Member member;
    private String language = "de";
    private List<Product> pL;
    private List<ProductCategory> categorys;
    private ArrayList<Product> warenkorb;

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private MenuModel model;
    private String productHead = "Alle";

    @PostConstruct
    public void init() {
<<<<<<< HEAD
        warenkorb = new ArrayList<Product>();
        try {

            utx.begin();
            ProductCategory mu = new ProductCategory("Muster");
            em.persist(mu);
            utx.commit();

            utx.begin();
            ProductCategory ei = new ProductCategory("Einfarbig");
            em.persist(ei);
            utx.commit();

            utx.begin();
            ProductCategory me = new ProductCategory("Merchandise");
            em.persist(me);
            utx.commit();

            utx.begin();
            Product pro = new Product("Rote Tasse");
            pro.setProductCategory(ei);
            pro.setDescription("Formschöne Kaffeebecher in Rot, Fassungsvermögen: 325 ml, Für Kaffee, Tee und andere Heiß- und Kaltgetränke, Maße: Höhe ca 10 cm, Ø ca 8 cm, konische Form");
            pro.setPrice(9.99f);
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Blaue Tasse");
            pro.setProductCategory(ei);
            pro.setDescription("Formschöne Kaffeebecher in Blau, Fassungsvermögen: 325 ml, Für Kaffee, Tee und andere Heiß- und Kaltgetränke, Maße: Höhe ca 10 cm, Ø ca 8 cm, konische Form");
            pro.setPrice(9.99f);
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Gelbe Tasse");
            pro.setProductCategory(ei);
            pro.setDescription("Formschöne Kaffeebecher in Gelb, Fassungsvermögen: 325 ml, Für Kaffee, Tee und andere Heiß- und Kaltgetränke, Maße: Höhe ca 10 cm, Ø ca 8 cm, konische Form");
            pro.setPrice(9.99f);
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Punktmuster Tasse");
            pro.setProductCategory(mu);
            pro.setDescription("Beschreibung");
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Streifenmuster Tasse");
            pro.setProductCategory(mu);
            pro.setDescription("Beschreibung");
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Tassenpullover");
            pro.setProductCategory(me);
            pro.setDescription("Beschreibung");
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Tassenhose");
            pro.setProductCategory(me);
            pro.setDescription("Beschreibung");
            em.persist(pro);
            utx.commit();

            utx.begin();
            Role r1 = new Role("Member");
            em.persist(r1);
            utx.commit();

            utx.begin();
            Adress a = new Adress("Adminsallee", "999999", "Adminshaven", "8");
            em.persist(a);
            Role r2 = new Role("Admin");
            em.persist(r2);
            Member m = new Member("Admin", "John", "der Admin", "admin", "Admin@webshop.de",
                    new GregorianCalendar(1970, 0, 2).getTime());
            m.setAnrede(Anrede.FIRMA.toString());
            ArrayList<Adress> al = new ArrayList<Adress>();
            al.add(a);
            m.setAdressList(al);
            m.setRole(r2);
            em.persist(m);
            utx.commit();

            createMainMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
=======
        warenkorb = new ArrayList();
        createDumb();
        createMainMenu();
        warenkorb.add(p);
    }

    public String goToStartpage() {
        return "/startpage.xhtml?faces-redirect=true";
    }

    public void createDumb() {
        try {
            utx.begin();
            ProductCategory mu = new ProductCategory("Muster");
            em.persist(mu);
            utx.commit();

            utx.begin();
            ProductCategory ei = new ProductCategory("Einfarbig");
            em.persist(ei);
            utx.commit();

            utx.begin();
            ProductCategory me = new ProductCategory("Merchandise");
            em.persist(me);
            utx.commit();

            utx.begin();
            Product pro = new Product("Rote Tasse");
            pro.setProductCategory(ei);
            pro.setDescription("Formschöner Kaffeebecher in Rot, Fassungsvermögen: 325 ml, Für Kaffee, Tee und andere Heiß- und Kaltgetränke, Maße: Höhe ca 10 cm, Ø ca 8 cm, konische Form");
            pro.setPrice(9.99f);
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Blaue Tasse");
            pro.setProductCategory(ei);
            pro.setDescription("Formschöner Kaffeebecher in Blau, Fassungsvermögen: 325 ml, Für Kaffee, Tee und andere Heiß- und Kaltgetränke, Maße: Höhe ca 10 cm, Ø ca 8 cm, konische Form");
            pro.setPrice(9.99f);
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Gelbe Tasse");
            pro.setProductCategory(ei);
            pro.setDescription("Formschöner Kaffeebecher in Gelb, Fassungsvermögen: 325 ml, Für Kaffee, Tee und andere Heiß- und Kaltgetränke, Maße: Höhe ca 10 cm, Ø ca 8 cm, konische Form");
            pro.setPrice(9.99f);
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Punktmuster Tasse");
            pro.setProductCategory(mu);
            pro.setDescription("Formschöner Kaffeebecher mit Punktmuster, Fassungsvermögen: 325 ml, Für Kaffee, Tee und andere Heiß- und Kaltgetränke, Maße: Höhe ca 10 cm, Ø ca 8 cm, konische Form");
            pro.setPrice(11.99f);
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Streifenmuster Tasse");
            pro.setProductCategory(mu);
            pro.setDescription("Formschöner Kaffeebecher mit Streifenmuster, Fassungsvermögen: 325 ml, Für Kaffee, Tee und andere Heiß- und Kaltgetränke, Maße: Höhe ca 10 cm, Ø ca 8 cm, konische Form");
            pro.setPrice(12.72f);
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Tassenpullover");
            pro.setProductCategory(me);
            pro.setDescription("Ein Pullover mit einem feschen Aufdruck einer Tasse");
            pro.setPrice(23.99f);
            em.persist(pro);
            utx.commit();

            utx.begin();
            pro = new Product("Tassenhose");
            pro.setProductCategory(me);
            pro.setDescription("Eine Hose in Form einer Tasse. Jetzt auch mit Griff");
            pro.setPrice(29.99f);
            em.persist(pro);
            utx.commit();

            utx.begin();
            Role r1 = new Role("Member");
            em.persist(r1);
            utx.commit();

//            utx.begin();
//            Kreditkarte k = new Kreditkarte();
//            k.setTyp(Kreditkartentyp.VISA.getLabel());
//            k.setGueltigBis(new Date());
//            k.setInhaber("John der Admin");
//            k.setNummer("9876 5432 1234 5678");
//            em.persist(k);
//            utx.commit();

            utx.begin();
            Adress a = new Adress("Adminsallee", "937483", "Adminshaven", "8e");
            em.persist(a);
            Role r2 = new Role("Admin");
            em.persist(r2);
            Member m = new Member("Admin", "John", "der Admin", "admin", "Admin@webshop.de",
                    new GregorianCalendar(1970, 0, 2).getTime());
            m.setAnrede(Anrede.FIRMA.toString());
            ArrayList<Adress> al = new ArrayList();
            al.add(a);
            m.setAdressList(al);
            m.setRole(r2);
//            m.setKreditkarte(k);
            em.persist(m);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public List<Product> getpL() {
        return pL;
    }

    public void setpL(List<Product> pL) {
        this.pL = pL;
    }

    public List<ProductCategory> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<ProductCategory> categorys) {
        this.categorys = categorys;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public String getProductHead() {
        return productHead;
    }

    public void setProductHead(String productHead) {
        this.productHead = productHead;
    }

    public ArrayList<Product> getWarenkorb() {
        return warenkorb;
    }

    public void setWarenkorb(ArrayList<Product> warenkorb) {
        this.warenkorb = warenkorb;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
