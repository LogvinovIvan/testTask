package com.epam.lab.news.manager.repository.specificaton;

import com.epam.lab.news.manager.entity.News;
import com.epam.lab.news.manager.exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan_Lohvinau on 10/19/2016.
 */
public abstract class NewsSqlSpecification implements SqlSpecification {

    public List<News> parseResulSet(ResultSet resultSet) throws RepositoryException {
        List<News> newsList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                News news = new News();
                news.setId(resultSet.getLong("N_ID"));
                news.setMainTitle(resultSet.getString("N_MAIN_TITLE"));
                news.setShortTitle(resultSet.getString("N_SHORT_TITLE"));
                news.setDate(resultSet.getDate("N_DATE_PUBLISHING"));
                news.setMainPhoto(resultSet.getString("N_MAIN_PHOTO"));

                newsList.add(news);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return newsList;
    }
}

