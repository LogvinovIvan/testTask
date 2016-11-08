package com.epam.lab.news.manager.repository;

import com.epam.lab.news.manager.entity.News;
import com.epam.lab.news.manager.entity.Tag;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.specificaton.impl.NewsByTagsSpecification;
import com.epam.lab.news.manager.repository.specificaton.impl.NewsOrderByCommentSpecificaion;
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

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
@DatabaseSetup("classpath:data/newsData.xml")
@DatabaseTearDown(value = "classpath:data/newsData.xml",
        type = DatabaseOperation.DELETE_ALL)
public class NewsRepositoryTest {

    private final static String NEWS_MAIN_TITLE = "Chicago nhl winners";
    private final static String NEWS_SHORT_TITLE = "Blackhawks won steanley cup";
    private final static String NEWS_DATE_PUBLISHING = "2016-05-23";
    private final static String NEWS_MAIN_PHOTO = "chicago.png";


    @Autowired
    private NewsRepository repository;

    @Test
    @ExpectedDatabase(value = "classpath:results/expectedNews.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void createNewsTest() throws ParseException, RepositoryException {
        News news = new News();
        news.setMainTitle("Haha");
        news.setShortTitle("ha");
        news.setNewsText("Hahaha");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        news.setDate(new Date(sdf.parse("2016-05-23").getTime()));
        news.setMainPhoto("a");

        assertNotNull(repository.create(news));
    }

    @Test
    public void deleteNewsTest() throws RepositoryException {
        Long idNews = 1l;
        Assert.assertTrue(repository.delete(idNews));
    }

    @Test
    public void deleteNotExitingNewsTest() throws RepositoryException {
        Long idNotExitingNews = -1l;
        Assert.assertFalse(repository.delete(idNotExitingNews));
    }


    @Test
    public void updateNewsTest() throws ParseException, RepositoryException {
        News news = new News();
        news.setMainTitle("Haha");
        news.setShortTitle("ha");
        news.setNewsText("Hahaha");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        news.setDate(new Date(sdf.parse("2016-05-23 15:05:45").getTime()));
        news.setMainPhoto("a");
        news.setId(1l);

        Assert.assertTrue(repository.update(news));
    }

    @Test
    public void updateNotExitingNewsTest() throws RepositoryException, ParseException {
        News news = new News();
        news.setMainTitle("Haha");
        news.setShortTitle("ha");
        news.setNewsText("Hahaha");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        news.setDate(new Date(sdf.parse("2016-05-23 15:05:45").getTime()));
        news.setMainPhoto("a");
        news.setId(-1l);

        Assert.assertFalse(repository.update(news));
    }

    @Test
    @DatabaseSetup("classpath:data/newsOrderTestData.xml")
    @DatabaseTearDown(value = "classpath:data/newsOrderTestData.xml",
            type = DatabaseOperation.DELETE_ALL)
    public void searchAllNewOrderByCommentNewsTest() throws ParseException, RepositoryException {

        List<News> expectedNews = createNewsForTest();
        List<News> newsResult = repository.search(new NewsOrderByCommentSpecificaion());
        Assert.assertTrue(expectedNews.equals(newsResult));

    }

    @Test
    public void searchByTagsTest() throws ParseException, RepositoryException {

        List<Tag> searchingTags = createTagsForTest();

        List<News> expectedNews = createNewsForTest();

        List<News> result = repository.search(new NewsByTagsSpecification(searchingTags));
        Assert.assertTrue(expectedNews.equals(result));

    }


    @Test
    public void searchTotalCountNewsTest() throws RepositoryException {
        Integer expectedCount = 3;
        Integer resultCount = repository.searchTotalCountNews();
        Assert.assertTrue(expectedCount.equals(resultCount));
    }

    @Test
    public void searchCountNewForThemeTest() throws RepositoryException {
        Tag tag = new Tag();
        tag.setId(1l);
        tag.setName("culture");

        Integer expectedResult = 2;

        Integer result = repository.searchCountNewsForTheme(tag);

        Assert.assertTrue(expectedResult.equals(result));
    }

    @Test
    public void attachTagToNewsTest() throws RepositoryException {
        Long idNews = 3l;
        Long idTag = 1l;
        Assert.assertTrue(repository.attachTagToNews(idNews, idTag));
    }

    @Test(expected = RepositoryException.class)
    public void attachNotExitingTagToNews() throws RepositoryException {
        Long idNotExitingTag = -1l;
        Long idNews = 1l;
        repository.attachTagToNews(idNotExitingTag, idNews);
    }

    @Test(expected = RepositoryException.class)
    public void attachTagToNotExitingNews() throws RepositoryException {
        Long idTag = 1l;
        Long idNotExitingNews = -1l;
        repository.attachTagToNews(idTag, idNotExitingNews);
    }

    @Test
    public void addAuthorToNewsTest() throws RepositoryException {
        Long idNews = 1l;
        Long idAuthor = 1l;
        Assert.assertTrue(repository.addAuthor(idNews, idAuthor));
    }

    @Test
    public void removeAuthorFromNewsTest() throws RepositoryException {
        Long idNews = 1l;
        Long idAuthor = 2l;
        Assert.assertTrue(repository.removeAuthor(idNews, idAuthor));
    }

    @Test
    public void removeTagFromNewsTest() throws RepositoryException {
        Long idNews = 1l;
        Long idTag = 1l;
        Assert.assertTrue(repository.removeTag(idNews, idTag));
    }

    @Test
    public void removeTagFromNotExitingNewsTest() throws RepositoryException {
        Long idNotExitingNews = -1l;
        Long idTag = 1l;
        Assert.assertFalse(repository.removeTag(idNotExitingNews, idTag));
    }


    @Test
    public void removeNotExitingTagFromNewsTest() throws RepositoryException {
        Assert.assertFalse(repository.removeTag(1l, -1l));
    }

    @Test(expected = RepositoryException.class)
    public void addNotExitingAuthorToNewsTest() throws RepositoryException {
        Long idNotExitingAuthor = -1l;
        Long idNews = 1l;
        repository.addAuthor(idNotExitingAuthor, idNews);
    }

    @Test(expected = RepositoryException.class)
    public void addAuthorToNotExitingNewsTest() throws RepositoryException {
        Long idNotExitingNews = -1l;
        Long idAuthor = 1l;
        repository.addAuthor(idAuthor, idNotExitingNews);
    }


    public void setRepository(NewsRepository repository) {
        this.repository = repository;
    }


    private List<News> createNewsForTest() throws ParseException {
        List<News> expectedNews = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            News news = new News();
            news.setMainTitle(NEWS_MAIN_TITLE);
            news.setShortTitle(NEWS_SHORT_TITLE);


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            news.setDate(new Date(sdf.parse(NEWS_DATE_PUBLISHING).getTime()));
            news.setMainPhoto(NEWS_MAIN_PHOTO);
            news.setId((long) i);
            expectedNews.add(news);
        }
        return expectedNews;
    }

    private List<Tag> createTagsForTest() {
        List<Tag> tags = new ArrayList<>();

        Tag tag = new Tag();
        tag.setId(1l);
        tag.setName("sport");

        Tag tag2 = new Tag();
        tag2.setId(3l);
        tag2.setName("medicine");

        tags.add(tag);
        tags.add(tag2);

        return tags;
    }

}
