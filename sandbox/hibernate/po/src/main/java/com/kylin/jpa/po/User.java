package com.kylin.jpa.po;

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

@Entity(name="User")
@Table(name="k_user")
public class User implements Serializable{

	private static final long serialVersionUID = 2800699943333944003L;
	private Long id;
	private String name;
	private List<Event> events;
	private List<Friend> friends;
	private UserCard userCard;
	private Wife wife;
	private Calendar createdDate;
	private Boolean isMale;

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
			targetEntity = com.kylin.jpa.po.Event.class,
			fetch=FetchType.LAZY,
			cascade = { CascadeType.ALL })
	@JoinTable(name="k_user_event", 
			joinColumns = @JoinColumn(name = "USER_ID"), 
			inverseJoinColumns = @JoinColumn(name = "EVENT_ID"))
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@OneToMany (
			targetEntity = com.kylin.jpa.po.Friend.class,
			fetch=FetchType.LAZY,
			cascade = { CascadeType.ALL })
	@JoinTable(name="k_user_friend", 
			joinColumns = @JoinColumn(name = "USER_ID"), 
			inverseJoinColumns = @JoinColumn(name = "FRIEND_ID"))
	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
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

	@OneToOne(
			targetEntity = com.kylin.jpa.po.Wife.class, 
    		fetch = FetchType.EAGER, 
    		cascade = { CascadeType.ALL })
	@JoinColumn(name = "Wife_id")
	public Wife getWife() {
		return wife;
	}

	public void setWife(Wife wife) {
		this.wife = wife;
	}

	@Column(name = "CREATEDDATE")
    @Temporal(TemporalType.DATE)
	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	@Column
	public Boolean getIsMale() {
		return isMale;
	}

	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}


}
