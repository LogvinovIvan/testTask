package com.epam.lab.news.manager.repository;

import com.epam.lab.news.manager.entity.Author;
import com.epam.lab.news.manager.exception.RepositoryException;

import java.util.List;

/**
 * Created by Ivan_Lohvinau on 10/17/2016.
 */
public interface AuthorRepository extends Repository<Author,Long> {
    List<Author> findByNewsID(Long newsID) throws RepositoryException;
}
