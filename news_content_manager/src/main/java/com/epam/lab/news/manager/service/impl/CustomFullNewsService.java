package com.epam.lab.news.manager.service.impl;

import com.epam.lab.news.manager.entity.*;
import com.epam.lab.news.manager.repository.AuthorRepository;
import com.epam.lab.news.manager.repository.CommentRepository;
import com.epam.lab.news.manager.repository.NewsRepository;
import com.epam.lab.news.manager.repository.TagRepository;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.repository.impl.OracleAuthorRepository;
import com.epam.lab.news.manager.repository.impl.OracleCommentRepository;
import com.epam.lab.news.manager.repository.impl.OracleTagRepository;
import com.epam.lab.news.manager.service.FullNewsService;
import com.epam.lab.news.manager.exception.ServiceException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ivan_Lohvinau on 10/17/2016.
 */
public class CustomFullNewsService implements FullNewsService {
    private AuthorRepository authorBaseRepository;
    private NewsRepository newsBaseRepository;
    private CommentRepository commentBaseRepository;
    private TagRepository tagBaseRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean add(FullNews fullNews) throws ServiceException {
        try {
            Long idNews = newsBaseRepository.create(fullNews.getNews());
            if(idNews!=null){
                for(Tag tag: fullNews.getTags()){
                    newsBaseRepository.attachTagToNews(tag.getId(),idNews);
                }

                for (Author author : fullNews.getAuthors()){
                    newsBaseRepository.addAuthor(idNews,author.getId());
                }
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public FullNews findFullNews(Long newsId) throws ServiceException {
        FullNews fullNews = null;
        try {
            News news = newsBaseRepository.read(newsId);
            if(news!=null){
                fullNews = new FullNews();
                fullNews.setNews(news);
                List<Tag> tags = tagBaseRepository.findTagsForNews(newsId);
                fullNews.setTags(tags);
                List<Author> authors = authorBaseRepository.findByNewsID(newsId);
                fullNews.setAuthors(authors);
                List<Comment> comments = commentBaseRepository.findAllCommentsForNews(newsId);
                fullNews.setComments(comments);
            }

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return fullNews;
    }

    public void setAuthorBaseRepository(OracleAuthorRepository authorBaseRepository) {
        this.authorBaseRepository = authorBaseRepository;
    }

    public void setNewsBaseRepository(NewsRepository newsBaseRepository) {
        this.newsBaseRepository = newsBaseRepository;
    }

    public void setCommentBaseRepository(OracleCommentRepository commentBaseRepository) {
        this.commentBaseRepository = commentBaseRepository;
    }

    public void setTagBaseRepository(OracleTagRepository tagBaseRepository) {
        this.tagBaseRepository = tagBaseRepository;
    }
}
