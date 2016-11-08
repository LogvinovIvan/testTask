package com.epam.lab.news.manager.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * Created by Ivan_Lohvinau on 10/12/2016.
 */
public class Comment {
    private Long id;
    private String text;
    private Date date;
    private User user;
    private Long idNews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdNews() {
        return idNews;
    }

    public void setIdNews(Long idNews) {
        this.idNews = idNews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(date, comment.date) &&
                Objects.equals(user, comment.user) &&
                Objects.equals(idNews, comment.idNews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date, user, idNews);
    }
}
