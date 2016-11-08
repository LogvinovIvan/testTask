package com.epam.lab.news.manager.repository.impl;

import com.epam.lab.news.manager.repository.NewsRepository;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.specificaton.NewsSqlSpecification;
import com.epam.lab.news.manager.entity.News;
import com.epam.lab.news.manager.entity.Tag;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Ivan_Lohvinau on 10/14/2016.
 */

public class OracleNewsRepository implements NewsRepository {
    private final static String INSERT_NEWS_QUERY = "INSERT INTO NEWS (N_MAIN_TITLE, N_SHORT_TITLE, N_CONTENT, N_DATE_PUBLISHING, N_MAIN_PHOTO) VALUES (?,?,?,?,?)";
    private final static String SELECT_NEWS_BY_ID_QUERY = "SELECT N_ID,N_MAIN_TITLE, N_SHORT_TITLE, N_CONTENT, N_DATE_PUBLISHING, N_MAIN_PHOTO FROM NEWS WHERE N_ID = ?";
    private final static String SELECT_TOTAL_COUNT_NEWS_QUERY = "SELECT COUNT(*) AS TOTAL_COUNT_NEWS FROM NEWS";
    private final static String SELECT_COUNT_NEWS_FOR_TAG = "SELECT COUNT(N_ID) AS TOTAL_COUNT_NEWS\n" +
            "FROM NEWS_HAS_TAGS\n" +
            "  JOIN NEWS ON NEWS_HAS_TAGS.NEWS_ID = NEWS.N_ID\n" +
            "  JOIN ATTACHED_TAGS ON NEWS_HAS_TAGS.ATTACHED_TAGS_ID = ATTACHED_TAGS.T_ID\n" +
            "WHERE T_NAME = ?\n" +
            "GROUP BY (T_NAME)";
    private final static String DELETE_NEWS_QUERY = "DELETE FROM NEWS WHERE N_ID =?";
    private final static String UPDATE_NEWS_QUERY = "UPDATE NEWS SET N_CONTENT = ?, N_DATE_PUBLISHING = ?, N_MAIN_PHOTO = ?, N_MAIN_TITLE = ?, N_SHORT_TITLE = ? WHERE N_ID = ?";
    private final static String ADD_AUTHOR_TO_NEWS_QUERY = "INSERT INTO NEWS_HAS_AUTHORS (AUTHORS_ID, NEWS_ID) VALUES (?,?)";
    private final static String ATTACH_TAG_TO_NEWS_QUERY = "INSERT INTO NEWS_HAS_TAGS (NEWS_ID, ATTACHED_TAGS_ID)  VALUES (?,?)";
    private final static String DELETE_TAG_FROM_NEWS = "DELETE FROM NEWS_HAS_TAGS WHERE NEWS_ID = ? AND ATTACHED_TAGS_ID = ?";
    private final static String DELETE_AUTHOR_FROM_NEWS = "DELETE FROM NEWS_HAS_AUTHORS WHERE NEWS_ID = ? AND AUTHORS_ID = ?";

    private DataSource dataSource;


    public List<News> search(NewsSqlSpecification sqlSpecification) throws RepositoryException {

        List<News> result;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlSpecification.toSqlQuery())
        ) {
            sqlSpecification.fillPrepareStatement(statement);
            ResultSet rs = statement.executeQuery();
            result = sqlSpecification.parseResulSet(rs);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return result;
    }

    @Override
    public Integer searchTotalCountNews() throws RepositoryException {
        Integer result = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TOTAL_COUNT_NEWS_QUERY)
        ) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("TOTAL_COUNT_NEWS");
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return result;
    }

    @Override
    public Integer searchCountNewsForTheme(Tag tag) throws RepositoryException {
        Integer result = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_COUNT_NEWS_FOR_TAG)
        ) {
            statement.setString(1, tag.getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TOTAL_COUNT_NEWS");
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return result;
    }

    @Override
    public boolean attachTagToNews(Long idTag, Long idNews) throws RepositoryException {
        return changeLinkTable(ATTACH_TAG_TO_NEWS_QUERY, idTag, idNews);

    }

    @Override
    public boolean addAuthor(Long idNews, Long idAuthor) throws RepositoryException {
        return changeLinkTable(ADD_AUTHOR_TO_NEWS_QUERY, idAuthor, idNews);
    }

    @Override
    public boolean removeAuthor(Long idNews, Long idAuthor) throws RepositoryException {
        return changeLinkTable(DELETE_AUTHOR_FROM_NEWS, idNews, idAuthor);
    }

    @Override
    public boolean removeTag(Long idNews, Long idTag) throws RepositoryException {
        return changeLinkTable(DELETE_TAG_FROM_NEWS, idNews, idTag);
    }


    public Long create(News news) throws RepositoryException {
        Long newsID = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEWS_QUERY, new String[]{"N_ID"})) {

            statement.setString(1, news.getMainTitle());
            statement.setString(2, news.getShortTitle());
            statement.setString(3, news.getNewsText());
            statement.setDate(4, news.getDate());
            statement.setString(5, news.getMainPhoto());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                newsID = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return newsID;
    }

    public News read(Long id) throws RepositoryException {
        News news = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NEWS_BY_ID_QUERY)
        ) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                news = new News();
                news.setId(rs.getLong("N_ID"));
                news.setMainTitle(rs.getString("N_MAIN_TITLE"));
                news.setShortTitle(rs.getString("N_SHORT_TITLE"));
                news.setNewsText(rs.getString("N_CONTENT"));
                news.setDate(rs.getDate("N_DATE_PUBLISHING"));
                news.setMainPhoto(rs.getString("N_MAIN_PHOTO"));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return news;
    }

    public List<News> readAll() {
        throw new UnsupportedOperationException();
    }

    public boolean update(News news) throws RepositoryException {
        boolean result;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_NEWS_QUERY)
        ) {
            statement.setString(1, news.getNewsText());
            statement.setDate(2, news.getDate());
            statement.setString(3, news.getMainPhoto());
            statement.setString(4, news.getMainTitle());
            statement.setString(5, news.getShortTitle());
            statement.setLong(6, news.getId());
            result = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

        return result;
    }

    public boolean delete(Long id) throws RepositoryException {
        boolean result = false;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_NEWS_QUERY)
        ) {
            statement.setLong(1, id);
            if (statement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return result;
    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private boolean changeLinkTable(String sqlQuery, Long sourceId, Long targetId) throws RepositoryException {
        boolean result;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)
        ) {
            statement.setLong(1, sourceId);
            statement.setLong(2, targetId);
            result = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return result;
    }
}
