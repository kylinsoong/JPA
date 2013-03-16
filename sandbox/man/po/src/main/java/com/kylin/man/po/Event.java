package com.kylin.man.po;

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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Entity(name="Event")
@Table(name="m_event")
@XmlRootElement
public class Event implements Serializable{
	
	private static final long serialVersionUID = 9008407234837711050L;
	private Long id;
	private String name;
	private List<Property> properties;

	@Column
	@Id
	@GeneratedValue
	@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany (
			targetEntity = com.kylin.man.po.Property.class,
			fetch=FetchType.LAZY,
			cascade = { CascadeType.ALL })
	@JoinTable(name="k_event_property", 
			joinColumns = @JoinColumn(name = "EVENT_ID"), 
			inverseJoinColumns = @JoinColumn(name = "PROPERTY_ID"))
	@XmlElement(name = "properties")
	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

}
