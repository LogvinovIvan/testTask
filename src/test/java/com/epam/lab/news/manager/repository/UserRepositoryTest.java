package com.epam.lab.news.manager.repository;

import com.epam.lab.news.manager.entity.Role;
import com.epam.lab.news.manager.entity.User;
import com.epam.lab.news.manager.repository.UserRepository;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Ivan_Lohvinau on 10/19/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:testDatabaseContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:data/userData.xml")
@DatabaseTearDown(value = "classpath:data/userData.xml",
        type = DatabaseOperation.DELETE_ALL)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void findUserByLoginTest() throws RepositoryException {
        User user = new User();
        user.setId(1l);
        user.setLogin("fox");
        user.setPassword("12345");

        User expectedUser = repository.findByLogin("fox");

        Assert.assertTrue(user.equals(expectedUser));

    }

    @Test
    @ExpectedDatabase(value = "classpath:results/expectedUserData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void createUserTest() throws RepositoryException {
        User user = new User();
        user.setLogin("ivan");
        user.setPassword("12345");

        Role role = new Role();
        role.setId(1l);
        user.setRole(role);

        assertNotNull(repository.create(user));
    }

    @Test(expected = RepositoryException.class)
    public void createUserWithExitingLogin() throws RepositoryException {
        User user = new User();
        user.setLogin("fox");
        user.setPassword("12345");

        Role role = new Role();
        role.setId(1l);
        user.setRole(role);

        repository.create(user);
    }


    @Test
    public void findUserById() throws RepositoryException {
        User user = new User();
        user.setId(1l);
        user.setLogin("fox");
        user.setPassword("12345");

        User expectedUser = repository.read(1l);

        Assert.assertTrue(user.equals(expectedUser));
    }

    @Test
    public void deleteUserTest() throws RepositoryException {
        Long idUser = 1l;
        Assert.assertTrue(repository.delete(idUser));
    }

    @Test
    public void deleteNotExitingUserTest() throws RepositoryException {
        Long idNotExitingUser = -1l;
        Assert.assertFalse(repository.delete(idNotExitingUser));
    }



    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }
}
