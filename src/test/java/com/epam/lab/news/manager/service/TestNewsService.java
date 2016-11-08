package com.epam.lab.news.manager.service;

import com.epam.lab.news.manager.entity.Tag;
import com.epam.lab.news.manager.repository.NewsRepository;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.specificaton.NewsSqlSpecification;
import com.epam.lab.news.manager.exception.ServiceException;
import com.epam.lab.news.manager.service.impl.CustomNewsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;

/**
 * Created by Ivan_Lohvinau on 10/13/2016.
 */
public class TestNewsService {
    @InjectMocks
    CustomNewsService customNewsService = new CustomNewsService();

    @Mock
    NewsRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test(expected = ServiceException.class)
    public void searchByTagsNotExitingTest() throws RepositoryException, ServiceException {
        Tag tag = new Tag();
        tag.setName("aaa");

        List<Tag> tags = new ArrayList<>();
        tags.add(tag);

        doThrow(new RepositoryException()).when(repository).search(any(NewsSqlSpecification.class));
        customNewsService.searchByTags(tags);
    }


}
