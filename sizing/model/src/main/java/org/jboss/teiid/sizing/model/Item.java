package org.jboss.teiid.sizing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMS")
public class Item implements Serializable {
	
	private static final long serialVersionUID = -3545651969553394370L;
	
	@Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private int id;
	
	@ManyToOne
    @JoinColumn(name = "QUESTION_ID")
	private Question question;
	
	@Column(name = "VALUE")
	private int value;

	public Item(Question question, int value) {
		this.question = question;
		this.value = value;
	}
	
	public Item() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	

}
