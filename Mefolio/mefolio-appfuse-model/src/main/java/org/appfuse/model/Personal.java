/**
 * 
 */
package org.appfuse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;

/**
 * @author VNT
 * @Date 11 Feb 2013
 */
@Entity
@Table(name="personal")
public class Personal {
		
	private Long id;
	private String lastName;
	private String tel;
	private String firstName;
	
	@Column(nullable=true, name="telephone", length=25)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@DocumentId
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false, name="lastName", length=75)
	@Field
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(length = 250)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
