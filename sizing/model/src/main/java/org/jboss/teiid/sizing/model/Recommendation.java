package org.jboss.teiid.sizing.model;

import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "RECOMMENDATIONS")
public class Recommendation implements Serializable {

	private static final long serialVersionUID = -4517265272685641455L;
	
	@Id
    @GeneratedValue
    @Column(name = "RECOMMENDATION_ID")
    private int id;
	
	@Column(name = "USER_ID")
    private String userId;
	
	@Temporal(DATE)
	@Column(name = "REQUEST_DATE")
	private Date date;
	
	@OneToMany(targetEntity=Item.class, fetch=FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "RECOMMENDATION_ID") 
	private List<Item> items = new ArrayList<Item>();

	public Recommendation(String userId, Date date) {
		this.userId = userId;
		this.date = date;
	}
	
	public Recommendation(String userId, Date date, List<Item> items ) {
		this.userId = userId;
		this.date = date;
		this.items = items;
	}
	
	public Recommendation() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	

}
