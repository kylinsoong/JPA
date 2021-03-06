package com.kylin.man.po;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name="Friend")
@Table(name="m_friend")
@XmlRootElement
public class Friend implements Serializable{

	private static final long serialVersionUID = -8835708240212475441L;
	private Long id;
	private String name;
	private UserCard userCard;

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

	public String toString() {
		return "Friend [id=" + id + ", name=" + name + "]";
	}
}
