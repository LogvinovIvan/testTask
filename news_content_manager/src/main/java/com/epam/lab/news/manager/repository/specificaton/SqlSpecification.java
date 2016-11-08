package com.epam.lab.news.manager.repository.specificaton;

import com.epam.lab.news.manager.exception.RepositoryException;

import java.sql.PreparedStatement;

/**
 * Created by Ivan_Lohvinau on 10/17/2016.
 */
public interface SqlSpecification {
    String toSqlQuery();
    void fillPrepareStatement(PreparedStatement preparedStatement) throws RepositoryException;
}
