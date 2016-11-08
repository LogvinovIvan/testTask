package com.epam.lab.news.manager.service.impl;

import com.epam.lab.news.manager.entity.Tag;
import com.epam.lab.news.manager.repository.Repository;
import com.epam.lab.news.manager.exception.RepositoryException;
import com.epam.lab.news.manager.service.TagService;
import com.epam.lab.news.manager.exception.ServiceException;

import java.util.List;

/**
 * Created by Ivan_Lohvinau on 10/17/2016.
 */
public class CustomTagService implements TagService {
    private Repository<Tag, Long> repository;

    @Override
    public boolean add(Tag tag) throws ServiceException {
        boolean result;
        try {
            Long idTag = repository.create(tag);
            result = idTag != null;
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean delete(Long idTag) throws ServiceException {
        boolean result;
        try {
            result = repository.delete(idTag);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean update(Tag tag) throws ServiceException {
        boolean result;
        try {
            result = repository.update(tag);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Tag search(Long id) throws ServiceException {
        Tag tag;
        try {
            tag = repository.read(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return tag;
    }

    @Override
    public List<Tag> searchAll() throws ServiceException {
        List<Tag> tags;
        try {
            tags = repository.readAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return tags;
    }

    public void setRepository(Repository<Tag, Long> repository) {
        this.repository = repository;
    }
}
