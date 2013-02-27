package com.kylin.jpa.po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="Event")
@Table(name="k_event")
public class Event implements Serializable{
	
	private static final long serialVersionUID = 9008407234837711050L;
	private Long id;
	private String name;
	private List<Property> properties;

	@Column
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany (
			targetEntity = com.kylin.jpa.po.Property.class,
			fetch=FetchType.LAZY,
			cascade = { CascadeType.ALL })
	@JoinTable(name="k_event_property", 
			joinColumns = @JoinColumn(name = "EVENT_ID"), 
			inverseJoinColumns = @JoinColumn(name = "PROPERTY_ID"))
	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

}