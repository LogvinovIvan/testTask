package com.epam.lab.news.manager.service;


import com.epam.lab.news.manager.entity.News;
import com.epam.lab.news.manager.entity.Tag;
import com.epam.lab.news.manager.exception.ServiceException;

import java.util.List;

/**
 * Created by Ivan_Lohvinau on 10/12/2016.
 */
public interface NewsService {
    void add(News news) throws ServiceException;
    boolean attachTag(Long idNews, Long idTag) throws ServiceException;
    boolean addAuthorToNews(Long idNews, Long idAuthor) throws ServiceException;
    boolean removeTagFromNews(Long idNews, Long idTag) throws ServiceException;
    boolean removeAuthorFromNews(Long idNews, Long idAuthor) throws ServiceException;
    void deleteNews(Long id) throws ServiceException;
    void updateNews(News news) throws ServiceException;
    List<News> searchByTags(List<Tag> tags) throws ServiceException;
    List<News> findNewsSortedByDate() throws ServiceException;
    List<News> findNewsSortedByCountComments() throws ServiceException;
    News searchSingleNews(Long id) throws ServiceException;
    Integer searchTotalCountNews() throws ServiceException;
    Integer searchCountNewsByTheme(Tag tag) throws ServiceException;
    boolean deleteNews(List<Long> idList) throws ServiceException;

}
