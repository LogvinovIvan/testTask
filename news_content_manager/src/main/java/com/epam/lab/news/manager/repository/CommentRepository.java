package com.epam.lab.news.manager.repository;

import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.entity.Comment;

import java.util.List;

/**
 * Created by Ivan_Lohvinau on 10/17/2016.
 */
public interface CommentRepository extends Repository<Comment,Long> {
    List<Comment> findAllCommentsForNews(Long idNews) throws RepositoryException;
}
