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
 * @Date 9 Feb 2013
 */
@Entity
@Table(name="company")
public class Company {
	private Long id;
	private String name;
	private String tel;
	
	@Column(nullable=true, name="telephone", length=25)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	private String description;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@DocumentId
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false, name="name", length=75)
	@Field
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length = 250)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
