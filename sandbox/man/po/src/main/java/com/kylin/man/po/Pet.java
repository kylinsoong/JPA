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

@Entity(name="Pet")
@Table(name="m_pet")
@XmlRootElement
public class Pet implements Serializable{

	private static final long serialVersionUID = 2147896361623827725L;
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
	@JoinTable(name="m_pet_property", 
			joinColumns = @JoinColumn(name = "EVENT_ID"), 
			inverseJoinColumns = @JoinColumn(name = "PROPERTY_ID"))
	@XmlElement(name = "property")
	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
}
