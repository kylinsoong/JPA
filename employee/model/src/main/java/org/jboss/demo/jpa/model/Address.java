package org.jboss.demo.jpa.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "ADDRESS")
public class Address {
	
    @Id
    @GeneratedValue
    @Column(name = "ADDRESS_ID")
    private int id;

    @Basic
    private String city;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String country;
    
    @Basic
    private String province;
    
    @Basic
    @Column(name = "P_CODE")
    private String postalCode;
    
    @Basic
    private String street;
    
    @Version
    private long version;

    public Address() {
    }

    public Address(String city, String country, String province, String postalCode, String street) {
        super();
        this.city = city;
        this.country = country;
        this.province = province;
        this.postalCode = postalCode;
        this.street = street;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int addressId) {
        this.id = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String pCode) {
        this.postalCode = pCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

	public String toString() {
		return "[city=" + city + ", country=" + country + ", province="
				+ province + ", postalCode=" + postalCode + ", street="
				+ street + "]";
	}
}
