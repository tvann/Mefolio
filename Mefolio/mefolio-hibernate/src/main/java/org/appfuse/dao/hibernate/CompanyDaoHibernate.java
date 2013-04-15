/**
 * 
 */
package org.appfuse.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.dao.CompanyDao;
import org.appfuse.dao.SearchException;
import org.appfuse.model.Company;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;



/**
 * @author VNT
 * @Date 13 Feb 2013
 */
@Repository
public class CompanyDaoHibernate implements CompanyDao{
	protected final Log log = LogFactory.getLog(getClass());
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return sess;
    }

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	public CompanyDaoHibernate(){}
	
	@Override
	public Company save(Company com) {
        if (log.isDebugEnabled()) {
            log.debug("Company's id: " + com.getId());
        }
        getSession().saveOrUpdate(com);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return com;
	}
	
	/* (non-Javadoc)
	 * @see org.appfuse.dao.GenericDao#get(java.io.Serializable)
	 */
	@Override
	public Company get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.appfuse.dao.GenericDao#getAll()
	 */
	@Override
	public List<Company> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.appfuse.dao.GenericDao#exists(java.io.Serializable)
	 */
	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.appfuse.dao.GenericDao#getAllDistinct()
	 */
	@Override
	public List<Company> getAllDistinct() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.appfuse.dao.GenericDao#findByNamedQuery(java.lang.String, java.util.Map)
	 */
	@Override
	public List<Company> findByNamedQuery(String queryName,
			Map<String, Object> queryParams) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.appfuse.dao.GenericDao#reindex()
	 */
	@Override
	public void reindex() {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see org.appfuse.dao.GenericDao#reindexAll(boolean)
	 */
	@Override
	public void reindexAll(boolean async) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see org.appfuse.dao.GenericDao#remove(java.lang.Object)
	 */
	@Override
	public void remove(Company object) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see org.appfuse.dao.GenericDao#remove(java.io.Serializable)
	 */
	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see org.appfuse.dao.GenericDao#search(java.lang.String)
	 */
	@Override
	public List<Company> search(String searchTerm) throws SearchException {
		// TODO Auto-generated method stub
		return null;
	}

}


