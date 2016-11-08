package com.epam.lab.news.manager.entity;

import java.util.List;
import java.util.Objects;

/**
 * Created by Ivan_Lohvinau on 10/17/2016.
 */
public class FullNews {
    private News news;
    private List<Author> authors;
    private List<Tag> tags;
    private List<Comment> comments;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullNews fullNews = (FullNews) o;
        return Objects.equals(news, fullNews.news) &&
                Objects.equals(authors, fullNews.authors) &&
                Objects.equals(tags, fullNews.tags) &&
                Objects.equals(comments, fullNews.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(news, authors, tags, comments);
    }
}
