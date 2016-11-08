package com.epam.lab.news.manager.repository;

import com.epam.lab.news.manager.entity.Role;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.impl.OracleRoleRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by Ivan_Lohvinau on 10/19/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:testDatabaseContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:data/roleData.xml")
@DatabaseTearDown(value = "classpath:data/roleData.xml",
        type = DatabaseOperation.DELETE_ALL)
public class RoleRepositoryTest {

    @Autowired
    OracleRoleRepository repository;

    @Test
    @ExpectedDatabase(value = "classpath:results/expectedRoleInsert.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void createRoleTest() throws RepositoryException {
        Role role = new Role();
        role.setName("DOCTOR");
        assertNotNull(repository.create(role));
    }


    @Test(expected = RepositoryException.class)
    public void createExitingRoleTest() throws RepositoryException {
        Role role = new Role();
        role.setName("USER");
        repository.create(role);
    }

    @Test
    public void readByIdTest() throws RepositoryException {
        Role role = new Role();
        role.setId(1l);
        role.setName("USER");
        Role expectedRole = repository.read(1l);
        assertEquals(role,expectedRole);
    }

    @Test
    public void updateRoleTest() throws RepositoryException {
        Role role = new Role();
        role.setId(1l);
        role.setName("SUPERMAN");
        assertTrue(repository.update(role));
    }

    @Test
    public void updateNotExitingRoleTest() throws RepositoryException {
        Role role = new Role();
        role.setId(-1l);
        role.setName("SUPERMAN");
        assertFalse(repository.update(role));
    }

    @Test
    public void deleteRoleTest() throws RepositoryException {
        assertTrue(repository.delete(1l));
    }


    @Test
    public void deleteNoExitingRole() throws RepositoryException {
        assertFalse(repository.delete(-1l));
    }
}
