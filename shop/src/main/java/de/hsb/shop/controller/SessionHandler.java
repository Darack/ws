/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package de.hsb.shop.controller;

import java.io.Serializable;
import java.util.ArrayList;
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
import de.hsb.shop.model.Title;

/**
 *
 * @author fiedler
 */
@ManagedBean(name = "sessionHandler")
@SessionScoped
public class SessionHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private Member member;
    private String language = "de";
    private List<Product> productList;
    private List<ProductCategory> categorys;
    private ArrayList<Product> shoppingCart;
    private MenuModel model;
    private String productHead = "Alle";

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    @PostConstruct
    public void init() {
        shoppingCart = new ArrayList();
//        createDump();
        createMainMenu();
    }

    public boolean isLogged() {
        return member != null;
    }

    public String login() {
        Query query = em.createQuery("select m from Member m "
                + "where LOWER(m.username) = :username and m.password = :password ");
        query.setParameter("username", username.toLowerCase());
        query.setParameter("password", password);
        List<Member> members = query.getResultList();

        if (members.size() == 1) {
            member = members.get(0);
            return goToStartpage();
        } else {
            return null;
        }
    }

    public boolean isAdmin() {
        if (member != null) {
            return member.getRole().getLabel().equals("Admin");
        }
        return false;
    }

    public void checkLoggedIn(ComponentSystemEvent cse) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (member == null) {
            context.getApplication().getNavigationHandler().
                    handleNavigation(context, null,
                            goToStartpage());
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance()
                .getExternalContext().invalidateSession();
//        warenkorb.clear();
//        member = null;
        return goToStartpage();
    }

    public Title[] getTitleValues() {
        return Title.values();
    }

    public String goToProductPage() {
        productHead = "Alle";
        Query query = em.createNamedQuery("SelectProduct", Product.class);
        productList = query.getResultList();
        return "/products.xhtml?faces-redirect=true";
    }

    public String goToProductPage(String category) {
        productHead = category;
        Query query = em.createNamedQuery("SelectProductByProductCategory", Product.class);
        query.setParameter("productCategory", category);
        productList = query.getResultList();
        return "/products.xhtml?faces-redirect=true";
    }

    public String emptyShoppingCart() {
        shoppingCart.clear();
        return "/shoppingCart.xhtml?faces-redirect=true";
    }

    private void createMainMenu() {
        Query query = em.createNamedQuery("SelectProductCategory", ProductCategory.class);
        categorys = query.getResultList();

        model = new DefaultMenuModel();

        DefaultMenuItem all = new DefaultMenuItem("Stöbern");
        all.setCommand("#{sessionHandler.goToProductPage()}");
        model.addElement(all);

        //First submenu
        DefaultSubMenu categorySubMenu = new DefaultSubMenu("Alle Kategorien");
        for (ProductCategory pc : categorys) {
            DefaultMenuItem item = new DefaultMenuItem(pc.getName());
            item.setCommand("#{sessionHandler.goToProductPage('" + pc.getName() + "')}");
            categorySubMenu.addElement(item);
        }
        model.addElement(categorySubMenu);

        DefaultMenuItem redu = new DefaultMenuItem("Angebote");
        redu.setDisabled(true);
        model.addElement(redu);

        DefaultMenuItem help = new DefaultMenuItem("Hilfe");
        help.setDisabled(true);
        model.addElement(help);
    }

    public void putProductIntoShoppingCart(Product p) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("", p.getName() + " wurde hinzugefügt"));
        shoppingCart.add(p);
    }

    public String goToStartpage() {
        return "/startpage.xhtml?faces-redirect=true";
    }

    public void createDump() {
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
            pro = new Product("Grüne Tasse");
            pro.setProductCategory(ei);
            pro.setDescription("Formschöner Kaffeebecher in Grün, Fassungsvermögen: 325 ml, Für Kaffee, Tee und andere Heiß- und Kaltgetränke, Maße: Höhe ca 10 cm, Ø ca 8 cm, konische Form");
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
            pro = new Product("Schwarze Tasse");
            pro.setProductCategory(ei);
            pro.setDescription("Formschöner Kaffeebecher in Schwarz, Fassungsvermögen: 325 ml, Für Kaffee, Tee und andere Heiß- und Kaltgetränke, Maße: Höhe ca 10 cm, Ø ca 8 cm, konische Form");
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
            pro = new Product("Tassentasse");
            pro.setProductCategory(mu);
            pro.setDescription("Formschöner Premium Kaffeebecher mit einer Tassenabbildung, Fassungsvermögen: 700 ml, Für Kaffee, Tee und andere Heiß- und Kaltgetränke, Maße: Höhe ca 20 cm, Ø ca 8 cm, konische Form");
            pro.setPrice(39.99f);
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
            pro = new Product("Tassenschuhe");
            pro.setProductCategory(me);
            pro.setDescription("Schuhe mit Henkel. Damit fühlt sich jeder Untergrund wie Porzellan an");
            pro.setPrice(24.99f);
            em.persist(pro);
            utx.commit();

            utx.begin();
            Role r1 = new Role("Member");
            em.persist(r1);
            utx.commit();

            utx.begin();
            Adress a = new Adress("Adminsallee", "937483", "Adminshaven", "8e");
            em.persist(a);
            Role r2 = new Role("Admin");
            em.persist(r2);
            Member m = new Member("Admin", "John", "der Admin", "admin", "Admin@webshop.de",
                    new GregorianCalendar(1970, 0, 2).getTime());
            m.setTitle(Title.FIRMA.toString());
            ArrayList<Adress> al = new ArrayList();
            al.add(a);
            m.setAdressList(al);
            m.setRole(r2);
            m.setNewsletter(true);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<ProductCategory> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<ProductCategory> categorys) {
        this.categorys = categorys;
    }

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
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

}
