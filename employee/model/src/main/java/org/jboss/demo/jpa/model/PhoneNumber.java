package org.jboss.demo.jpa.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PHONE")
public class PhoneNumber implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "PHONE_ID")
    private int id;
    
    @Basic
    @Column(updatable = false)
    private String type;
    
    @Basic
    @Column(name = "AREA_CODE")
    private String areaCode;
    
    @Basic
    @Column(name = "P_NUMBER")
    private String number;
    
    @ManyToOne
    @JoinColumn(name = "EMP_ID")
    private Employee owner;

    public PhoneNumber() {
    }

    public PhoneNumber(String type, String areaCode, String number) {
        this();
        setType(type);
        setAreaCode(areaCode);
        setNumber(number);
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int empId) {
        this.id = empId;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String pNumber) {
        this.number = pNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Employee getOwner() {
        return this.owner;
    }

    protected void setOwner(Employee employee) {
        this.owner = employee;
        if (employee != null) {
            this.id = employee.getId();
        }
    }

	@Override
	public String toString() {
		return "[type=" + type + ", areaCode=" + areaCode + ", number=" + number + "]";
	}

   
}
