package com.epam.lab.news.manager.repository;

import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.specificaton.NewsSqlSpecification;
import com.epam.lab.news.manager.entity.News;
import com.epam.lab.news.manager.entity.Tag;

import java.util.List;

/**
 * Created by Ivan_Lohvinau on 10/13/2016.
 */
public interface NewsRepository extends Repository<News, Long> {
    List<News> search(NewsSqlSpecification sqlSpecification) throws RepositoryException;
    Integer searchTotalCountNews() throws RepositoryException;
    Integer searchCountNewsForTheme(Tag tag) throws RepositoryException;
    boolean attachTagToNews(Long tagId, Long newsId) throws RepositoryException;
    boolean addAuthor(Long authorId, Long newsId) throws RepositoryException;
    boolean removeAuthor(Long idNews, Long idAuthor) throws RepositoryException;
    boolean removeTag(Long idNews, Long idTag) throws RepositoryException;
}
