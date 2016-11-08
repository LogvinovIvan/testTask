package com.epam.lab.news.manager.repository.specificaton.impl;

import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.specificaton.NewsSqlSpecification;
import com.epam.lab.news.manager.entity.Tag;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan_Lohvinau on 10/17/2016.
 */
public class NewsByTagsSpecification extends NewsSqlSpecification {

    private final static String SEARCH_BY_TAGS_QUERY = "SELECT\n" +
            "  N_ID,\n" +
            "  N_MAIN_TITLE,\n" +
            "  N_SHORT_TITLE,\n" +
            "  N_DATE_PUBLISHING,\n" +
            "  N_MAIN_PHOTO\n" +
            "FROM NEWS_HAS_TAGS\n" +
            "  JOIN NEWS ON NEWS_HAS_TAGS.NEWS_ID = NEWS.N_ID\n" +
            "  JOIN ATTACHED_TAGS ON NEWS_HAS_TAGS.ATTACHED_TAGS_ID = ATTACHED_TAGS.T_ID\n" +
            "WHERE ATTACHED_TAGS.T_NAME IN (%1$s)\n" +
            "  GROUP BY (N_ID, N_MAIN_TITLE, N_SHORT_TITLE, N_DATE_PUBLISHING, N_MAIN_PHOTO)\n" +
            "HAVING COUNT(DISTINCT ATTACHED_TAGS.T_NAME) >= ?"+
            "ORDER BY N_ID";
    private List<Tag> tagList = new ArrayList<>();

    public NewsByTagsSpecification(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toSqlQuery() {
        StringBuilder stringForTags = new StringBuilder();
        for (int i = 1; i <= tagList.size(); i++) {
            stringForTags.append("?");
            if (i != tagList.size()) {
                stringForTags.append(",");
            }
        }

        return String.format(SEARCH_BY_TAGS_QUERY, stringForTags);
    }

    @Override
    public void fillPrepareStatement(PreparedStatement preparedStatement) throws RepositoryException {
        try {
            int numberParameters = 1;
            for (Tag tag : tagList) {
                preparedStatement.setString(numberParameters, tag.getName());
                numberParameters++;
            }
            preparedStatement.setInt(numberParameters,tagList.size());
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }
}
