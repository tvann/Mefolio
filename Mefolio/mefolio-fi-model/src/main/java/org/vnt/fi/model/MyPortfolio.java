/**
 * 
 */
package org.vnt.fi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.appfuse.model.User;
import org.hibernate.annotations.Cache;

/**
 * @author VNT
 * @Date 16 Apr 2013
 */
@Entity
@Table(name="portfolio")
public class MyPortfolio implements Serializable {
	private static final long serialVersionUID = 1292236189504821968L;
	
	private Long portId;
	private String name;  
	private Date createdDate;
	private User user;
	/**
	 * 
	 */
	public MyPortfolio() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="portId")
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
	@Column(name="createdDate", nullable=false,length=20)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
//	@OneToOne(mappedBy="portfolio", fetch=FetchType.LAZY)
//	@Cache({org.hibernate.annotations.CascadeType.})
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	
	

}
