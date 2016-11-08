package com.epam.lab.news.manager.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * Created by Ivan_Lohvinau on 10/12/2016.
 */
public class News {
    private Long id;
    private String mainTitle;
    private String shortTitle;
    private String newsText;
    private Date date;

    private String mainPhoto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) &&
                Objects.equals(mainTitle, news.mainTitle) &&
                Objects.equals(shortTitle, news.shortTitle) &&
                Objects.equals(newsText, news.newsText) &&
                Objects.equals(date, news.date) &&
                Objects.equals(mainPhoto, news.mainPhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mainTitle, shortTitle, newsText, date, mainPhoto);
    }
}
