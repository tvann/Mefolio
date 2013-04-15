package org.appfuse.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.appfuse.Constants;
import org.appfuse.model.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleDaoTest extends BaseDaoTestCase {
    @Autowired
    private RoleDao roleDao;

    @Test
    public void testGetRoleInvalid() throws Exception {
        Role role = roleDao.getRoleByName("badrolename");
        assertNull(role);
    }

    @Test
    public void testGetRole() throws Exception {
        Role role = roleDao.getRoleByName(Constants.USER_ROLE);
        assertNotNull(role);
    }

    @Test
    public void testUpdateRole() throws Exception {
        Role role = roleDao.getRoleByName("ROLE_USER");
        role.setDescription("test descr");
        roleDao.save(role);
        flush();
        
        role = roleDao.getRoleByName("ROLE_USER");
        assertEquals("test descr", role.getDescription());
    }

    @Test
    public void testAddAndRemoveRole() throws Exception {
    	String roleTest = "testrole";
        Role role = new Role(roleTest);
        role.setDescription("new role descr");
        roleDao.save(role);
        flush();
        
        role = roleDao.getRoleByName(roleTest);
        assertNotNull(role.getDescription());

        roleDao.removeRole(roleTest);
        flush();

        role = roleDao.getRoleByName(roleTest);
        assertNull(role);
    }

    @Test
    public void testFindByNamedQuery() {
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("name", Constants.USER_ROLE);
        List<Role> roles = roleDao.findByNamedQuery("findRoleByName", queryParams);
        assertNotNull(roles);
        assertTrue(roles.size() > 0);
    }
}
