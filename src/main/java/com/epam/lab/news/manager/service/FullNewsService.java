package com.epam.lab.news.manager.service;

import com.epam.lab.news.manager.entity.FullNews;
import com.epam.lab.news.manager.exception.ServiceException;

/**
 * Created by Ivan_Lohvinau on 10/17/2016.
 */
public interface FullNewsService {
    boolean add(FullNews fullNews) throws ServiceException;
    FullNews findFullNews(Long newsId) throws ServiceException;
}
