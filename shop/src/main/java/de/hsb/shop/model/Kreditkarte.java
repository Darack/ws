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


@NamedQuery(name="SelectKreditkarte", query="Select k from Kreditkarte k")
@Entity

public class Kreditkarte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Kreditkartentyp typ;
	private String nummer;
	private String inhaber;

	@Temporal(TemporalType.DATE)
	Date gueltigBis;

	public Kreditkarte(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Kreditkartentyp getTyp() {
		return typ;
	}

	public void setTyp(Kreditkartentyp typ) {
		this.typ = typ;
	}

	public String getNummer() {
		return nummer;
	}

	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

	public String getInhaber() {
		return inhaber;
	}

	public void setInhaber(String inhaber) {
		this.inhaber = inhaber;
	}

	public Date getGueltigBis() {
		return gueltigBis;
	}

	public void setGueltigBis(Date gueltigBis) {
		this.gueltigBis = gueltigBis;
	}



}
