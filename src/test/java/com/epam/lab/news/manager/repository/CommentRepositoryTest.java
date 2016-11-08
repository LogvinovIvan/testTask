package com.epam.lab.news.manager.repository;

import com.epam.lab.news.manager.entity.Comment;
import com.epam.lab.news.manager.entity.User;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.impl.OracleCommentRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.apache.commons.collections.CollectionUtils;
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
@DatabaseSetup("classpath:data/commentData.xml")
@DatabaseTearDown(value = "classpath:data/commentData.xml",
        type = DatabaseOperation.DELETE_ALL)
public class CommentRepositoryTest {
    @Autowired
    private OracleCommentRepository repository;

    @Test

    public void testCreateComment() throws ParseException, RepositoryException {
        Comment insertComment = new Comment();
        insertComment.setIdNews(1l);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "2016-05-23 00:00:00";
        insertComment.setText("CCCC");
        User user = new User();
        user.setId(1l);

        insertComment.setUser(user);

        insertComment.setDate(new Date(sdf.parse(dateInString).getTime()));
        assertNotNull(repository.create(insertComment));
    }

    @Test
    public void testReadByIdComment() throws ParseException, RepositoryException {
        Comment comment = new Comment();
        User user = new User();
        user.setId(1l);
        comment.setText("AAAA");
        comment.setIdNews(1l);
        comment.setId(1l);
        comment.setUser(user);
        String dateInString = "2016-05-23 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        comment.setDate(new Date(sdf.parse(dateInString).getTime()));

        Comment expectedComment = repository.read(1l);
        Assert.assertTrue(comment.equals(expectedComment));

    }

    @Test
    public void testUpdateComment() throws ParseException, RepositoryException {
        Comment comment = new Comment();
        User user = new User();
        user.setId(1l);
        comment.setText("AAAAaa");
        comment.setIdNews(1l);
        comment.setId(1l);
        comment.setUser(user);
        String dateInString = "2016-05-23 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        comment.setDate(new Date(sdf.parse(dateInString).getTime()));

        Assert.assertTrue(repository.update(comment));
    }

    @Test
    public void testUpdateNotExitingComment() throws ParseException, RepositoryException {
        Comment comment = new Comment();
        User user = new User();
        user.setId(1l);
        comment.setText("AAAAaa");
        comment.setIdNews(1l);
        comment.setId(-1l);
        comment.setUser(user);
        String dateInString = "2016-05-23 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        comment.setDate(new Date(sdf.parse(dateInString).getTime()));

        Assert.assertFalse(repository.update(comment));
    }

    @Test
    public void testDeleteComment() throws RepositoryException {
        Long idComment = 1l;
        Assert.assertTrue(repository.delete(idComment));
    }

    @Test
    public void deleteNotExitingComment() throws RepositoryException {
        Long idNotExitingComment = -1l;
        Assert.assertFalse(repository.delete(idNotExitingComment));
    }

    @Test
    public void testReadCommentByIdNews() throws ParseException, RepositoryException {
        Comment comment = new Comment();
        User user = new User();
        user.setId(1l);
        user.setLogin("fox");
        comment.setText("AAAA");
        comment.setIdNews(1l);
        comment.setId(1l);
        comment.setUser(user);
        String dateInString = "2016-05-23 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        comment.setDate(new Date(sdf.parse(dateInString).getTime()));

        Comment comment1 = new Comment();
        comment1.setText("AAAA");
        comment1.setIdNews(1l);
        comment1.setId(2l);
        comment1.setUser(user);
        comment1.setDate(new Date(sdf.parse(dateInString).getTime()));

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment1);

        List<Comment> expectedComments = repository.findAllCommentsForNews(1l);

        Assert.assertTrue(CollectionUtils.isEqualCollection(expectedComments,comments));
    }


    public void setRepository(OracleCommentRepository repository) {
        this.repository = repository;
    }
}
