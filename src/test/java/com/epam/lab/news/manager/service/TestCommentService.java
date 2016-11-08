package com.epam.lab.news.manager.service;

import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.entity.Comment;
import com.epam.lab.news.manager.entity.User;
import com.epam.lab.news.manager.repository.impl.OracleCommentRepository;
import com.epam.lab.news.manager.exception.ServiceException;
import com.epam.lab.news.manager.service.impl.CustomCommentService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Calendar;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Ivan_Lohvinau on 10/13/2016.
 */
public class TestCommentService {

    @InjectMocks
    CommentService commentService = new CustomCommentService();

    @Mock
    OracleCommentRepository baseRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testAddRightComment() throws RepositoryException, ServiceException {
        Comment comment = new Comment();
        comment.setText("Зачем взяли этого Китарова");
        comment.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        comment.setUser(new User());
        comment.setIdNews(1L);

        when(baseRepository.create(comment)).thenReturn(123L);
        assertEquals(true, commentService.addComment(comment));
    }

    @Test
    public void testDeleteExitingTag() throws RepositoryException, ServiceException {
        Long idExitingComment = 1L;
        when(baseRepository.delete(idExitingComment)).thenReturn(true);

        assertEquals(true, commentService.deleteComment(idExitingComment));
    }

    @Test()
    public void testDeleteNonExistentComment() throws RepositoryException, ServiceException {
        Long idNoneExitingComment= -200L;

        when(baseRepository.delete(idNoneExitingComment)).thenReturn(false);
        assertFalse(commentService.deleteComment(idNoneExitingComment));
    }




    @Test
    public void testUpdateExitingComment() throws RepositoryException, ServiceException {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setText("AAAAAA");
        when(baseRepository.update(comment)).thenReturn(true);
        assertEquals(true, commentService.editComment(comment));
    }

    @Test()
    public void testUpdateNonExistentTag() throws RepositoryException, ServiceException {
        Comment comment = new Comment();
        comment.setId(-1L);
        comment.setText("AAAAAAAAA");
        when(baseRepository.update(comment)).thenReturn(false);
        Assert.assertFalse(commentService.editComment(comment));
    }


    @Test()
    public void testSearchNoneExitingTag() throws RepositoryException, ServiceException {
        when(baseRepository.read(-1L)).thenReturn(null);
        assertNull(commentService.findComment(-1L));
    }
}
