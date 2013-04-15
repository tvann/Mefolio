/**
 * 
 */
package org.appfuse.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;

/**
 * @author VNT
 * @Date 11 Feb 2013
 */
@Entity
@Table(name="department")
public class Department {
	
	private Long id;
	private String name;
	private String tel;
	private String description;
	
	private Set<Personal> personals = new HashSet<Personal>();
	
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
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "depart_pers",
            joinColumns = { @JoinColumn(name = "depart_id") },
            inverseJoinColumns = @JoinColumn(name = "pers_id")
    )
    public Set<Personal> getPersonals() {
        return this.personals;
    }
    
    public void setPersonals(Set<Personal> personals) {
        this.personals = personals;
    }
    
    public void addPersonal(Personal personal) {
        getPersonals().add(personal);
    }

}
