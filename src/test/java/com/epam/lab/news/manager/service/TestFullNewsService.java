package com.epam.lab.news.manager.service;

import com.epam.lab.news.manager.entity.*;
import com.epam.lab.news.manager.repository.*;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.exception.ServiceException;
import com.epam.lab.news.manager.service.impl.CustomFullNewsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Ivan_Lohvinau on 10/20/2016.
 */
public class TestFullNewsService {

    @InjectMocks
    FullNewsService fullNewsService = new CustomFullNewsService();

    @Mock
    AuthorRepository authorRepository;

    @Mock
    CommentRepository commentRepository;

    @Mock
    NewsRepository newsRepository;

    @Mock
    TagRepository tagRepository;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void findExitingFullNews() throws ParseException, RepositoryException, ServiceException {
        FullNews fullNews = new FullNews();


        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        author.setId(1l);
        author.setName("alex");
        author.setSurname("pushkin");
        authors.add(author);

        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setId(202l);
        comment.setText("aaa!!!");
        comments.add(comment);

        News news = new News();
        news.setId(1l);
        news.setMainTitle("Haha");
        news.setShortTitle("ha");
        news.setNewsText("Hahaha");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        news.setDate(new Date(sdf.parse("2016-05-23").getTime()));
        news.setMainPhoto("a");

        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag(1l,"sport"));

        fullNews.setComments(comments);
        fullNews.setAuthors(authors);
        fullNews.setTags(tagList);
        fullNews.setNews(news);



        when(newsRepository.read(1l)).thenReturn(news);
        when(authorRepository.findByNewsID(1l)).thenReturn(authors);
        when(commentRepository.findAllCommentsForNews(1l)).thenReturn(comments);
        when(tagRepository.findTagsForNews(1l)).thenReturn(tagList);

        assertEquals(fullNews, fullNewsService.findFullNews(1l));
    }

    @Test()
    public void findNonExitingNews() throws RepositoryException, ServiceException {
        when(newsRepository.read(-1l)).thenReturn(null);
        assertNull(fullNewsService.findFullNews(-1l));
    }



    @Test
    public void saveNewFullNewsTest() throws ParseException, RepositoryException, ServiceException {
        FullNews fullNews = new FullNews();


        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        author.setId(1l);
        author.setName("alex");
        author.setSurname("pushkin");
        authors.add(author);


        News news = new News();
        news.setId(1l);
        news.setMainTitle("Haha");
        news.setShortTitle("ha");
        news.setNewsText("Hahaha");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        news.setDate(new Date(sdf.parse("2016-05-23").getTime()));
        news.setMainPhoto("a");

        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag(1l,"sport"));


        fullNews.setAuthors(authors);
        fullNews.setTags(tagList);
        fullNews.setNews(news);



        when(newsRepository.create(news)).thenReturn(1l);
        when(newsRepository.addAuthor(1l,1l)).thenReturn(true);

        when(newsRepository.attachTagToNews(1l,1l)).thenReturn(true);



        assertEquals(true, fullNewsService.add(fullNews));
    }


}
