package com.epam.lab.news.manager.repository.specificaton.impl;

import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.specificaton.NewsSqlSpecification;

import java.sql.PreparedStatement;

/**
 * Created by Ivan_Lohvinau on 10/17/2016.
 */
public class NewsOrderByDateSpecification extends NewsSqlSpecification {
    private final static  String SELECT_ORDER_BY_DATE = "SELECT DISTINCT\n" +
            "  N_ID,\n" +
            "  N_MAIN_TITLE,\n" +
            "  N_SHORT_TITLE,\n" +
            "  N_CONTENT,\n" +
            "  N_DATE_PUBLISHING,\n" +
            "  N_MAIN_PHOTO\n" +
            "FROM NEWS \n" +
            "ORDER BY N_DATE_PUBLISHING DESC;";
    @Override
    public String toSqlQuery() {
        return SELECT_ORDER_BY_DATE;
    }

    @Override
    public void fillPrepareStatement(PreparedStatement preparedStatement) throws RepositoryException {

    }
}
