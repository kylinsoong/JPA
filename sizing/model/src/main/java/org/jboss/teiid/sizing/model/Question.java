package org.jboss.teiid.sizing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTIONS")
public class Question implements Serializable {

	private static final long serialVersionUID = -9130233751386541066L;
	
	@Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    private int id;
	
	@Column(name = "QUESTION")
    private String question;
	
	public Question() {
		
	}

	public Question(String question) {
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return id + ". " + question;
	}

}
