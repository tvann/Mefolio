/**
 * 
 */
package org.vnt.fi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * It represents a share of user's portfolio.
 * @author <a href="mailto:nghia.tranv@gmail.com">Van Nghia Tran</a>
 * @Date 16 Apr 2013
 */
@Entity
@Table(name="share")
public class Share {
	
	private Long shareId;
	private String name;
	private String symbol;
	private boolean status;
	private MyPortfolio myPortfolio;
	
	
	/**
	 * 
	 */
	public Share() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="share_id")
	public Long getShareId() {
		return shareId;
	}
	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}
	@Column(length=50, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=3, nullable=false, unique=true)
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	@Column(name="status")
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="port_id", nullable=false)
	public MyPortfolio getMyPortfolio() {
		return myPortfolio;
	}
	public void setMyPortfolio(MyPortfolio myPortfolio) {
		this.myPortfolio = myPortfolio;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((myPortfolio == null) ? 0 : myPortfolio.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((shareId == null) ? 0 : shareId.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		Share other = (Share) obj;
		if (myPortfolio == null) {
			if (other.myPortfolio != null)
				return false;
		} else if (!myPortfolio.equals(other.myPortfolio))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (shareId == null) {
			if (other.shareId != null)
				return false;
		} else if (!shareId.equals(other.shareId))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
	
	
	

}
