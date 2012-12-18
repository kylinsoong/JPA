package com.kylin.jpa.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Audit")
@Table(name="k_audit")
public class Audit implements Serializable {

	private static final long serialVersionUID = -2164326920876525528L;
	private Long id;	
	private String content;

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
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
