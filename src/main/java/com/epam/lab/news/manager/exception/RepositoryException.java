package com.epam.lab.news.manager.exception;

/**
 * Created by Ivan_Lohvinau on 10/13/2016.
 */
public class RepositoryException extends Exception {

    public RepositoryException() {
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }
}
