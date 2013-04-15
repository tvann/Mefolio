/**
 * 
 */
package org.appfuse.dao;

import static org.junit.Assert.*;

import org.appfuse.model.Company;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @author VNT
 * @Date 13 Feb 2013
 */
public class CompanyDaoTest extends BaseDaoTestCase {
	
	@Autowired
	public CompanyDao comDao;
	
	@Test
	@Rollback(true)
	public void testAddCom(){
		Company com = new Company();
		com.setName("Artenit");
		com.setTel("02 888 04 90");
		com.setDescription("The Art of Engineering in IT");
		
		comDao.save(com);
		flush();
		assertNotNull(com.getId());
		
	}

}
