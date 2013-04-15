package org.appfuse.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.appfuse.Constants;
import org.appfuse.model.Address;
import org.appfuse.model.Role;
import org.appfuse.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.annotation.ExpectedException;
//import org.springframework.test.annotation.NotTransactional;
//import org.springframework.dao.DataAccessException;
//import org.springframework.test.annotation.Rollback;

public class UserDaoTest extends BaseDaoTestCase {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    //@ExpectedException(DataAccessException.class)
    public void testGetUserInvalid() throws Exception {
        // should throw DataAccessException
        assertNull(userDao.get(1000L));
    }

    @Test
    public void testGetUser() throws Exception {
        User user = userDao.get(-1L);

        assertNotNull(user);
        assertEquals(1, user.getRoles().size());
        assertTrue(user.isEnabled());
    }

    @Test
    public void testGetUserPassword() throws Exception {
        User user = userDao.get(-1L);
        String password = userDao.getUserPassword(user.getId());
        assertNotNull(password);
        log.debug("password: " + password);
    }

    @Test(expected=DataIntegrityViolationException.class)
    //@ExpectedException(DataIntegrityViolationException.class)
    public void testUpdateUser() throws Exception {
        User user = userDao.get(-1L);

        Address address = user.getAddress();
        address.setAddress("new address");

        userDao.saveUser(user);
        flush();

        user = userDao.get(-1L);
        assertEquals(address, user.getAddress());
        assertEquals("new address", user.getAddress().getAddress());

        // verify that violation occurs when adding new user with same username
        User user2 = new User();
        user2.setAddress(user.getAddress());
        user2.setConfirmPassword(user.getConfirmPassword());
        user2.setEmail(user.getEmail());
        user2.setFirstName(user.getFirstName());
        user2.setLastName(user.getLastName());
        user2.setPassword(user.getPassword());
        user2.setPasswordHint(user.getPasswordHint());
        user2.setRoles(user.getRoles());
        user2.setUsername(user.getUsername());
        user2.setWebsite(user.getWebsite());

        // should throw DataIntegrityViolationException
        userDao.saveUser(user2);
    }

    @Test
    public void testAddUserRole() throws Exception {
        User user = userDao.get(-1L);
        assertEquals(1, user.getRoles().size());

        Role role = roleDao.getRoleByName(Constants.ADMIN_ROLE);
        user.addRole(role);
        userDao.saveUser(user);
        flush();

        user = userDao.get(-1L);
        assertEquals(2, user.getRoles().size());

        //add the same role twice - should result in no additional role
        user.addRole(role);
        userDao.saveUser(user);
        flush();

        user = userDao.get(-1L);
        assertEquals("more than 2 roles", 2, user.getRoles().size());

        user.getRoles().remove(role);
        userDao.saveUser(user);
        flush();

        user = userDao.get(-1L);
        assertEquals(1, user.getRoles().size());
    }

    @Test
    //@ExpectedException(DataAccessException.class)
    //@Rollback(false)
    public void testAddAndRemoveUser() throws Exception {
        User user = new User("testuser");
        user.setPassword("testpass");
        user.setFirstName("Test");
        user.setLastName("Last");
        Address address = new Address();
        address.setCity("Denver");
        address.setProvince("CO");
        address.setCountry("USA");
        address.setPostalCode("80210");
        user.setAddress(address);
        user.setEmail("testuser@appfuse.org");
        user.setWebsite("http://raibledesigns.com");
        user.setVersion(null);

        Role role = roleDao.getRoleByName(Constants.USER_ROLE);
        assertNotNull(role.getId());
        user.addRole(role);

        user = userDao.saveUser(user);
        flush();

        assertNotNull(user.getId());
        user = userDao.get(user.getId());
        assertEquals("testpass", user.getPassword());

        userDao.remove(user);
        flush();

        // should throw DataAccessException
        userDao.get(user.getId());
    }

    @Test
    public void testUserExists() throws Exception {
        boolean b = userDao.exists(-1L);
        assertTrue(b);
    }

    @Test
    public void testUserNotExists() throws Exception {
        boolean b = userDao.exists(111L);
        assertFalse(b);
    }

    @Test
    public void testUserSearch() throws Exception {
        // reindex all the data
        userDao.reindex();

        List<User> found = userDao.search("Matt");
        assertEquals(1, found.size());
        User user = found.get(0);
        assertEquals("Matt", user.getFirstName());

        // test mirroring
        user = userDao.get(-2L);
        user.setFirstName("MattX");
        userDao.saveUser(user);
        flush();
        flushSearchIndexes();

        // now verify it is reflected in the index
        found = userDao.search("MattX");
        assertEquals(1, found.size());
        user = found.get(0);
        assertEquals("MattX", user.getFirstName());
    }
}
