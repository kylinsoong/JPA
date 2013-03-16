package com.kylin.man.po;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name="Man")
@Table(name="m_man")
@XmlRootElement
public class Man implements Serializable{

	private static final long serialVersionUID = 2800699943333944003L;
	private Long id;
	private String name;
	private List<Event> events;
	private List<Friend> friends;
	private UserCard userCard;
	private Wife wife;
	private Calendar createdDate;

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
			targetEntity = com.kylin.man.po.Event.class,
			fetch=FetchType.LAZY,
			cascade = { CascadeType.ALL })
	@JoinTable(name="k_user_event", 
			joinColumns = @JoinColumn(name = "USER_ID"), 
			inverseJoinColumns = @JoinColumn(name = "EVENT_ID"))
	@XmlElement(name = "event")
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@OneToMany (
			targetEntity = com.kylin.man.po.Friend.class,
			fetch=FetchType.LAZY,
			cascade = { CascadeType.ALL })
	@JoinTable(name="k_user_friend", 
			joinColumns = @JoinColumn(name = "USER_ID"), 
			inverseJoinColumns = @JoinColumn(name = "FRIEND_ID"))
	@XmlElement(name = "friend")
	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
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

	@OneToOne(
			targetEntity = com.kylin.man.po.Wife.class, 
    		fetch = FetchType.EAGER, 
    		cascade = { CascadeType.ALL })
	@JoinColumn(name = "Wife_id")
	@XmlElement
	public Wife getWife() {
		return wife;
	}

	public void setWife(Wife wife) {
		this.wife = wife;
	}

	@Column(name = "CREATEDDATE")
    @Temporal(TemporalType.DATE)
	@XmlElement
	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}


}
