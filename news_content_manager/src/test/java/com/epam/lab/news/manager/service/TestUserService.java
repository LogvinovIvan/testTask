package com.epam.lab.news.manager.service;

import com.epam.lab.news.manager.entity.Role;
import com.epam.lab.news.manager.repository.UserRepository;
import com.epam.lab.news.manager.entity.User;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.exception.ServiceException;
import com.epam.lab.news.manager.service.impl.CustomUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Ivan_Lohvinau on 10/13/2016.
 */
public class TestUserService {
    @InjectMocks
    UserService userService = new CustomUserService();

    @Mock
    UserRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testSignInWithErrorPassword() throws RepositoryException, ServiceException {
        User user = new User();
        user.setLogin("logvinov_ivan@gmail.com");
        user.setPassword("123654788");

        User expectedUser = new User();
        expectedUser.setLogin("logvinov_ivan@gmail.com");
        expectedUser.setPassword("agywefysydgfygsfgsdgdsfg");
        expectedUser.setId(20l);

        Role role = new Role();
        role.setId(1l);
        role.setName("user");
        expectedUser.setRole(role);

        when(repository.findByLogin("logvinov_ivan@gmail.com")).thenReturn(expectedUser);
        assertNull(userService.signIn(user));
    }

    @Test
    public void testSignInWithRightData() throws RepositoryException, ServiceException {
        User user = new User();
        user.setLogin("logvinov_ivan@gmail.com");
        user.setPassword("123654789");

        User expectedUser = new User();
        expectedUser.setLogin("logvinov_ivan@gmail.com");
        expectedUser.setPassword("8b4cf0258846b23e0a8272bee22c38dd");
        expectedUser.setId(1l);

        Role role = new Role();
        role.setId(1l);
        role.setName("user");
        expectedUser.setRole(role);


        when(repository.findByLogin("logvinov_ivan@gmail.com")).thenReturn(expectedUser);
        assertEquals(expectedUser, userService.signIn(user));
    }


    @Test
    public void testSignUpWithExitingUser() throws RepositoryException, ServiceException {
        User user = new User();
        user.setLogin("logvinov_ivan@gmail.com");
        user.setPassword("123654789");

        when(repository.findByLogin("logvinov_ivan@gmail.com")).thenReturn(new User(1L,"logvinov_ivan@gmail.ocm","xvxjxchchdshsdh"));
        assertNull(userService.signUp(user));
    }




}
