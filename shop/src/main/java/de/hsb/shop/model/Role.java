/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsb.shop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hsb.shop.controller.LoginHandler;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@NamedQuery(name = "SelectRole", query = "Select r from Role r")
@Entity
public class Role implements Serializable{

    private static Logger logger = LoggerFactory.getLogger(LoginHandler.class);
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 255)
    @Column(name = "label")
    private String label;
    
    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<Member> memberList;
    
    public Role(){}
    
    public Role(String label){
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }
}
