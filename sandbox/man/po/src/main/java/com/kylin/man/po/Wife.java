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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name="Wife")
@Table(name="m_wife")
@XmlRootElement
public class Wife implements Serializable{

	private static final long serialVersionUID = -7528771205144558481L;
	private Long id;
	private String name;
	private UserCard userCard;
	private List<Pet> pets;

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

	@OneToOne(
			targetEntity = com.kylin.man.po.UserCard.class, 
    		fetch = FetchType.EAGER, 
    		cascade = { CascadeType.ALL })
	@JoinColumn(name = "UserCard_id")
	@XmlElement
	public UserCard getUserCard() {
		return userCard;
	}

	public void setUserCard(UserCard userCard) {
		this.userCard = userCard;
	}

	@OneToMany (
			targetEntity = com.kylin.man.po.Pet.class,
			fetch=FetchType.LAZY,
			cascade = { CascadeType.ALL })
	@JoinTable(name="k_wife_pet", 
			joinColumns = @JoinColumn(name = "WIFE_ID"), 
			inverseJoinColumns = @JoinColumn(name = "PET_ID"))
	@XmlElement(name = "pet")
	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	
	
}
