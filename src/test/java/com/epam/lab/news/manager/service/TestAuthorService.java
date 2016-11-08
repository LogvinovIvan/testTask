package com.epam.lab.news.manager.service;

import com.epam.lab.news.manager.repository.AuthorRepository;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.entity.Author;
import com.epam.lab.news.manager.exception.ServiceException;
import com.epam.lab.news.manager.service.impl.CustomAuthorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Ivan_Lohvinau on 10/13/2016.
 */
public class TestAuthorService {
    @InjectMocks
    AuthorService authorService = new CustomAuthorService();

    @Mock
    AuthorRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testDeleteExitingAuthor() throws RepositoryException, ServiceException {
        Long idExitingAuthor= 1l;
        when(repository.delete(idExitingAuthor)).thenReturn(true);

        assertEquals(true, authorService.deleteAuthor(idExitingAuthor));
    }

    @Test()
    public void testDeleteNonExistentAuthor() throws RepositoryException, ServiceException {
        Long idNoneExitingAuthor = -200l;

        when(repository.delete(idNoneExitingAuthor)).thenReturn(false);
        assertFalse(authorService.deleteAuthor(idNoneExitingAuthor));
    }


    @Test
    public void testUpdateExitingAuthor() throws RepositoryException, ServiceException {
        Author author = new Author();
        author.setId(1l);
        author.setName("Mark");
        author.setSurname("Twen");
        when(repository.update(author)).thenReturn(true);

        assertEquals(true, authorService.updateAuthor(author));
    }



    @Test()
    public void testUpdateNonExistentAuthor() throws RepositoryException, ServiceException {
        Author author = new Author();
        author.setId(-1l);
        author.setName("Mark");
        author.setSurname("Twen");
        when(repository.update(author)).thenReturn(false);
        assertFalse(authorService.updateAuthor(author));
    }


    @Test()
    public void testSearchNoneExitingAuthor() throws ServiceException, RepositoryException {
        when(repository.read(-1l)).thenReturn(null);
        Author author = authorService.findAuthor(-1l);
        assertNull(author);
    }


}
