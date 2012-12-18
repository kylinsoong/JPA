package com.kylin.jpa.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Property")
@Table(name="k_property")
public class Property implements Serializable{

	private static final long serialVersionUID = -6548261410073409925L;
	private Long id;
	private String name;
	
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

	public String toString() {
		return "Property [id=" + id + ", name=" + name + "]";
	}

}
