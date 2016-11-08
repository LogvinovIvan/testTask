package com.epam.lab.news.manager.repository;

import com.epam.lab.news.manager.entity.Author;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.impl.OracleAuthorRepository;
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
import static org.junit.Assert.*;

/**
 * Created by Ivan_Lohvinau on 10/18/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:testDatabaseContext.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:data/authorData.xml")
@DatabaseTearDown(value = "classpath:data/authorData.xml",
        type = DatabaseOperation.DELETE_ALL)

public class AuthorRepositoryTest {

    @Autowired
    private OracleAuthorRepository repository;


    @Test
    @ExpectedDatabase(value = "classpath:results/expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void saveTest() throws RepositoryException {
        Author author = new Author();
        author.setName("Lev");
        author.setSurname("Tolstoy");

        assertNotNull(repository.create(author));
    }


    @Test
    @ExpectedDatabase(value = "classpath:results/expectedAuthorUpdate.xml",assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updateAuthorTest() throws RepositoryException {
        Author author =  new Author();
        author.setName("Mario");
        author.setSurname("Puzo");
        author.setId(1l);

        assertTrue(repository.update(author));
    }

    @Test
    public void updateNotExitingAuthorTest() throws RepositoryException {
        Author author =  new Author();
        author.setName("Mario");
        author.setSurname("Puzo");
        author.setId(-1l);

        assertFalse(repository.update(author));
    }

    @Test
    public void readByIdTest() throws RepositoryException {
        Author author = new Author();
        author.setName("ALEX");
        author.setSurname("Pushkin");
        author.setId(1l);
        Author expectedAuthor = repository.read(1l);
        assertTrue(author.equals(expectedAuthor));
    }

    @Test
    public void findAuthorsByNewsIdTest() throws RepositoryException {

        List<Author> authors = new ArrayList<>();

        Author author = new Author();
        author.setId(2l);
        author.setSurname("Turgenev");
        author.setName("Ivan");

        Author author1 = new Author();
        author1.setId(3l);
        author1.setSurname("Twen");
        author1.setName("Mark");

        authors.add(author);
        authors.add(author1);

        List<Author> expectedAuthors = repository.findByNewsID(1l);

        Assert.assertTrue(expectedAuthors.equals(authors));

    }

    @Test
    @ExpectedDatabase(value = "classpath:results/expectedAuthorDelete.xml",assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteAuthor() throws RepositoryException {
        assertTrue(repository.delete(1l));
    }

    @Test
    public void deleteNotExitingAuthor() throws RepositoryException {
        assertFalse(repository.delete(-1l));
    }



    public void setRepository(OracleAuthorRepository repository) {
        this.repository = repository;
    }
}
