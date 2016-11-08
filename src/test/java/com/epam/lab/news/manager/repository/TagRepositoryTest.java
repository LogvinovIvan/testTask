package com.epam.lab.news.manager.repository;

import com.epam.lab.news.manager.entity.Tag;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.impl.OracleTagRepository;
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
@DatabaseSetup("classpath:data/tagData.xml")
@DatabaseTearDown(value = "classpath:data/tagData.xml",
        type = DatabaseOperation.DELETE_ALL)
public class TagRepositoryTest {

    @Autowired
    OracleTagRepository repository;

    @Test
    @ExpectedDatabase(value = "classpath:results/expectedTag.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void createTagTest() throws RepositoryException {
        Tag tag = new Tag();
        tag.setName("medicine");
        assertNotNull(repository.create(tag));
    }

    @Test
    public void updateTagTest() throws RepositoryException {
        Tag tag = new Tag();
        tag.setId(1l);
        tag.setName("culture1");

        Assert.assertTrue(repository.update(tag));
    }

    @Test
    public void updateNotExitingTag() throws RepositoryException {
        Tag tag = new Tag();
        tag.setId(-1l);
        tag.setName("culture1");
        Assert.assertFalse(repository.update(tag));
    }

    @Test (expected = RepositoryException.class)
    public void updateTagWithSimilarName() throws RepositoryException {
        Tag tag = new Tag();
        tag.setId(1l);
        tag.setName("culture");
        repository.update(tag);
    }

    @Test(expected = RepositoryException.class)
    public void deleteTagTest() throws RepositoryException {
        Assert.assertTrue(repository.delete(1l));
    }

    @Test
    public void readByIdTest() throws RepositoryException {
        Tag tag = new Tag();
        tag.setId(1l);
        tag.setName("sport");

        Tag expectedTag = repository.read(1l);
        Assert.assertTrue(tag.equals(expectedTag));
    }

    @Test
    public void findTagsByNewsId() throws RepositoryException {
        Tag tag = new Tag();
        tag.setId(1l);
        tag.setName("sport");

        Tag tag1 = new Tag();
        tag1.setId(2l);
        tag1.setName("culture");

        List<Tag> tags= new ArrayList<>();

        tags.add(tag);
        tags.add(tag1);

        List<Tag> expectedTags = repository.findTagsForNews(1l);

        Assert.assertTrue(tags.equals(expectedTags));
    }
}
