package com.epam.lab.news.manager.repository.specificaton.impl;

import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.specificaton.NewsSqlSpecification;

import java.sql.PreparedStatement;

/**
 * Created by Ivan_Lohvinau on 10/17/2016.
 */
public class NewsOrderByCommentSpecificaion extends NewsSqlSpecification {
    private String SELECT_NEWS_ORDER_BY_COMMENTS = "SELECT DISTINCT\n" +
            "  N_ID,\n" +
            "  N_MAIN_TITLE,\n" +
            "  N_SHORT_TITLE,\n" +
            "  N_DATE_PUBLISHING,\n" +
            "  N_MAIN_PHOTO,\n" +
            "  COUNT(C_ID) AS COUNT_COMMENTS\n" +
            "FROM COMMENT_NEWS\n" +
            "  RIGHT JOIN NEWS ON NEWS.N_ID = COMMENT_NEWS.C_NEWS\n" +
            "  GROUP BY N_ID, N_MAIN_TITLE, N_SHORT_TITLE, N_DATE_PUBLISHING, N_MAIN_PHOTO\n" +
            "ORDER BY COUNT_COMMENTS DESC";

    @Override
    public String toSqlQuery() {
        return SELECT_NEWS_ORDER_BY_COMMENTS;
    }

    @Override
    public void fillPrepareStatement(PreparedStatement preparedStatement) throws RepositoryException {

    }


}
