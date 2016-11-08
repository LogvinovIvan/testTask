package com.epam.lab.news.manager.service;

import com.epam.lab.news.manager.repository.Repository;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.entity.Tag;
import com.epam.lab.news.manager.exception.ServiceException;
import com.epam.lab.news.manager.service.impl.CustomTagService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Ivan_Lohvinau on 10/13/2016.
 */
public class TestTagService {
    @InjectMocks
    TagService tagService = new CustomTagService();

    @Mock
    Repository<Tag, Long> repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ServiceException.class)
    public void testAddExitingTag() throws RepositoryException, ServiceException {
        Tag tag = new Tag();
        tag.setName("SPORT");

        doThrow(new RepositoryException()).when(repository).create(tag);
        tagService.add(tag);
    }

    @Test
    public void testAddRightTag() throws RepositoryException, ServiceException {
        Tag tag = new Tag();
        tag.setName("CULTURE");
        Long expectedIdTag = 1l;

        when(repository.create(tag)).thenReturn(expectedIdTag);
        assertEquals(true, tagService.add(tag));
    }

    @Test
    public void testDeleteExitingTag() throws RepositoryException, ServiceException {
        Long idExitingTag = 1l;
        when(repository.delete(idExitingTag)).thenReturn(true);

        assertEquals(true, tagService.delete(idExitingTag));
    }

    @Test()
    public void testDeleteNonExistentTag() throws RepositoryException, ServiceException {
        Long idNoneExitingTag = -200l;

        when(repository.delete(idNoneExitingTag)).thenReturn(false);
        assertFalse(tagService.delete(idNoneExitingTag));
    }


    @Test
    public void testUpdateExitingTagWithUniqueName() throws RepositoryException, ServiceException {
        Tag tag = new Tag(1l, "ASTRONOMY");
        when(repository.update(tag)).thenReturn(true);

        assertEquals(true, tagService.update(tag));
    }

    @Test(expected = ServiceException.class)
    public void testUpdateExitingTagWithNonUniqueName() throws RepositoryException, ServiceException {
        Tag tag = new Tag(1l, "SPORT");
        doThrow(new RepositoryException()).when(repository).update(tag);
        tagService.update(tag);
    }

    @Test()
    public void testUpdateNonExistentTag() throws RepositoryException, ServiceException {
        Tag tag = new Tag(-1l, "SPORT");
        when(repository.update(tag)).thenReturn(false);
        assertFalse(tagService.update(tag));
    }


    @Test(expected = ServiceException.class)
    public void testSearchNoneExitingTag() throws RepositoryException, ServiceException {
        Long idNotExitingTag = -1l;
        doThrow(new RepositoryException()).when(repository).read(idNotExitingTag);
        tagService.search(idNotExitingTag);
    }


}
