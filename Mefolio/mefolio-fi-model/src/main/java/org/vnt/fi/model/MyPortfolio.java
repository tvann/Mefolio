/**
 * 
 */
package org.vnt.fi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;

/**
 * It represents a portfolio of a user who can has many portolios. 
 * A portfolio holds asset of user such as share, bond, sicav and so on.
 * @author <a href="mailto:nghia.tranv@gmail.com">Van Nghia Tran</a>
 * @Date 16 Apr 2013
 */
@Entity
@Table(name="portfolio")
public class MyPortfolio implements Serializable {
	private static final long serialVersionUID = 1292236189504821968L;
	
	private Long portId;
	private String name;  
	private Date createdDate;
    private String userName;
    private Set<Share> stock = new HashSet<Share>();

	/**
	 * 
	 */
	public MyPortfolio() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="port_id")
	public Long getPortId() {
		return portId;
	}
	public void setPortId(Long portId) {
		this.portId = portId;
	}
	@Column(nullable=false, length=50, unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="created_date", nullable=false, length=20)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Each user can have portfolio. It store the reference of user by userName. 
	 * The reference is not automatic to protect the security of user data and keep independency of design between appfuse and Mefolio.
	 * However when delete a user, it is necessary to implement a process to delete all protfolios because cascade operation is not automatic.
	 * It means no reference between the two tables of the database. 
	 * @return userName
	 */
    @Column(nullable = false, length = 50, unique = true)
    @Field
    public String getUserName() {
        return userName;
    }
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@OneToMany(mappedBy="portfolio", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public Set<Share> getStock() {
		return stock;
	}
	/**
	 * Add a share to the stock.
	 * @param a Share object
	 */
	public void addStock(Share share){
		getStock().add(share);
	}

	public void setStock(Set<Share> stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((portId == null) ? 0 : portId.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyPortfolio other = (MyPortfolio) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (portId == null) {
			if (other.portId != null)
				return false;
		} else if (!portId.equals(other.portId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	
	

	
	

}
