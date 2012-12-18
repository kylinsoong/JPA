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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="Wife")
@Table(name="k_wife")
public class Wife implements Serializable{

	private static final long serialVersionUID = -7528771205144558481L;
	private Long id;
	private String name;
	private UserCard userCard;
	private List<Pet> pets;

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

	@OneToOne(
			targetEntity = com.kylin.jpa.po.UserCard.class, 
    		fetch = FetchType.EAGER, 
    		cascade = { CascadeType.ALL })
	@JoinColumn(name = "UserCard_id")
	public UserCard getUserCard() {
		return userCard;
	}

	public void setUserCard(UserCard userCard) {
		this.userCard = userCard;
	}

	@OneToMany (
			targetEntity = com.kylin.jpa.po.Pet.class,
			fetch=FetchType.LAZY,
			cascade = { CascadeType.ALL })
	@JoinTable(name="k_wife_pet", 
			joinColumns = @JoinColumn(name = "WIFE_ID"), 
			inverseJoinColumns = @JoinColumn(name = "PET_ID"))
	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	
	
}
